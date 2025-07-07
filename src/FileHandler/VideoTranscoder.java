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
      try (FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inFile.toString());
           FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(
                 outFile.toString(),
                 grabber.getImageWidth(), grabber.getImageHeight(),
                 grabber.getAudioChannels()))
      {

         grabber.start();

         recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
         recorder.setFormat("mp4");
         recorder.setFrameRate(grabber.getFrameRate());
         recorder.setVideoBitrate(4_000_000);// ~4Mbps
         recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
         recorder.setAudioBitrate(128_000);// 128kbps
         recorder.start();

         Frame frame;
         while ((frame = grabber.grabFrame()) != null) {
            recorder.record(frame);
         }
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

   public void shutdown() {
      exec.shutdown();
   }
}
