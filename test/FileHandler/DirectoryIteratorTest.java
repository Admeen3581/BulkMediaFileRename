package FileHandler;

//Imports
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class DirectoryIteratorTest
{
   @TempDir
   Path tempDir;

   @Test
   public void testIsImage()
   {
      assertTrue(ExtensionHandler.isImage(new File("photo.jpg")));
      assertTrue(ExtensionHandler.isImage(new File("image.PNG")));
      assertTrue(ExtensionHandler.isImage(new File("animation.gif")));
      assertTrue(ExtensionHandler.isImage(new File("picture.jpeg")));

      assertFalse(ExtensionHandler.isImage(new File("video.mp4")));
      assertFalse(ExtensionHandler.isImage(new File("video.MOV")));
      assertFalse(ExtensionHandler.isImage(new File("file.txt")));
      assertFalse(ExtensionHandler.isImage(null));
   }

   @Test
   public void testIsVideo()
   {
      assertTrue(ExtensionHandler.isVideo(new File("video.mp4")));
      assertTrue(ExtensionHandler.isVideo(new File("video.MOV")));

      assertFalse(ExtensionHandler.isVideo(new File("photo.jpg")));
      assertFalse(ExtensionHandler.isVideo(new File("image.PNG")));
      assertFalse(ExtensionHandler.isVideo(new File("animation.gif")));
      assertFalse(ExtensionHandler.isVideo(new File("picture.jpeg")));
      assertFalse(ExtensionHandler.isVideo(new File("file.txt")));
      assertFalse(ExtensionHandler.isVideo(null));
   }

   @Test
   public void testIterate() throws IOException
   {
      // Create subdirectory
      Path subDir = tempDir.resolve("subdir");
      Files.createDirectory(subDir);

      // Create test files
      Files.createFile(tempDir.resolve("root_photo.jpg"));
      Files.createFile(tempDir.resolve("root_video.mp4"));
      Files.createFile(subDir.resolve("sub_image.png"));
      Files.createFile(subDir.resolve("sub_movie.jpeg"));
      Files.createFile(subDir.resolve("document.gif"));

      List<File> mediaFiles = DirectoryIterator.iterate(tempDir.toFile());

      assertEquals(5, mediaFiles.size());
      assertTrue(mediaFiles.stream().anyMatch(f -> f.getName().equals("root_photo.jpg")));
      assertTrue(mediaFiles.stream().anyMatch(f -> f.getName().equals("root_video.mp4")));
      assertTrue(mediaFiles.stream().anyMatch(f -> f.getName().equals("sub_image.png")));
      assertTrue(mediaFiles.stream().anyMatch(f -> f.getName().equals("sub_movie.jpeg")));
      assertFalse(mediaFiles.stream().anyMatch(f -> f.getName().equals("document.pdf")));
   }

}
