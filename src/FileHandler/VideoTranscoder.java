package FileHandler;

//Imports
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VideoTranscoder
{
   private final ExecutorService exec = Executors.newFixedThreadPool(2);

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

         //Add Parallelism
         Frame frame;
         while ((frame = grabber.grabFrame()) != null)
         {
            recorder.record(frame);
         }
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


   public void processFolder(Path folder) throws Exception {
      Files.list(folder)
            .filter(f -> f.toString().toUpperCase().endsWith(".MOV"))
            .forEach(in -> {
               Path out = in.resolveSibling(
                     in.getFileName().toString().replaceFirst("\\.\\w+$", ".mp4"));
               exec.submit(() -> {
                  try {
                     VideoTranscoder.transcodeToMp4(in, out);
                     Files.delete(in);   // remove old file
                     System.out.println("Converted: " + in);
                  } catch (Exception e) {
                     e.printStackTrace();
                  }
               });
            });
   }

   public void shutdown()
   {
      exec.shutdown();
   }
}