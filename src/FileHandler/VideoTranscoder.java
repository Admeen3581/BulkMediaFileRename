package FileHandler;

//Imports
import ViewModel.Controllers.MasterFrameController;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.*;

public class VideoTranscoder
{
   /**
    * Transcodes a video file to MP4 format using the H.264 video codec and AAC audio codec.
    *
    * @param inFile  the input video file path (e.g. a .MOV or HEVC file)
    * @param outFile the output file path to write the .MP4
    * @throws Exception if an error occurs during grabbing, recording, or threading
    */
   public static void transcodeToMp4(Path inFile, Path outFile) throws Exception
   {
      FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inFile.toString());
      FFmpegFrameRecorder recorder = null;

      try
      {
         grabber.start();

         // Get source properties
         int width = grabber.getImageWidth();
         int height = grabber.getImageHeight();
         int audioChannels = grabber.getAudioChannels();
         double frameRate = grabber.getFrameRate();

         recorder = new FFmpegFrameRecorder(outFile.toString(), width, height);
         recorder.setFormat("mp4");

         //Codec Features
         recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
         recorder.setFrameRate(frameRate);
         recorder.setVideoBitrate(4_000_000);//4mbps

         if (audioChannels > 0)
         {
            recorder.setAudioChannels(audioChannels);
            recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
            recorder.setAudioBitrate(128_000);//128kps
            recorder.setSampleRate(grabber.getSampleRate());
         }

         recorder.start();

         BlockingQueue<Frame> frameQueue = new LinkedBlockingQueue<>(100);
         Frame poisonPill = new Frame(); //null pointer


         Thread grabberThread = new Thread(() ->
         {
            try
            {
               Frame frame;
               while ((frame = grabber.grabFrame()) != null)
               {
                  frameQueue.put(frame.clone());
               }

               frameQueue.put(poisonPill); //death to the thread.
            }
            catch (Exception e)
            {
               e.printStackTrace();
            }
         });

         grabberThread.start();

         Frame frame;
         while ((frame = frameQueue.take()) != poisonPill)
         {
            recorder.record(frame);

         }

         grabberThread.join();
      }
      finally
      {
         if (recorder != null)
         {
            try { recorder.stop(); } catch (Exception e) { /* ignore */ }
         }
         try { grabber.stop(); } catch (Exception e) { /* ignore */ }
      }
   }


   /**
    * Processes all `.MOV` files in the given folder by transcoding them to `.MP4` format in parallel.
    *
    * @param folder the folder containing .MOV files to transcode
    * @throws Exception if any IO or transcoding errors occur during folder scan or processing
    */
   public static void processFolder(Path folder) throws Exception
   {
      org.bytedeco.ffmpeg.global.avutil.av_log_set_level(avutil.AV_LOG_ERROR);//suppression of warnings
      long startTime = System.currentTimeMillis();//time logger

      ExecutorService exec = Executors.newFixedThreadPool(4);

      Files.list(folder).filter(f -> f.toString().toUpperCase().endsWith(".MOV")).forEach(in ->
      {
         Path out = in.resolveSibling(in.getFileName().toString().replaceFirst("\\.\\w+$", ".MP4"));
         exec.submit(() ->
         {
            try
            {
               VideoTranscoder.transcodeToMp4(in, out);
               Files.delete(in);
               new MasterFrameController().addToDirectory(out.toFile());
               System.out.println("\u001B[0;92mSTATUS: Converted - " + in +" @ "+ (System.currentTimeMillis()-startTime)/1000.0 +"s\u001B[0m");
            }
            catch (Exception e)
            {
               e.printStackTrace();
            }
         });
      });
      exec.shutdown();
      exec.awaitTermination(1, TimeUnit.SECONDS);
   }
}