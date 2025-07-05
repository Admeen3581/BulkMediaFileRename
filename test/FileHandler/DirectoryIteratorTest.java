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
      assertTrue(ExtensionHandler.isImage("photo.jpg"));
      assertTrue(ExtensionHandler.isImage("image.PNG"));
      assertTrue(ExtensionHandler.isImage("animation.gif"));
      assertTrue(ExtensionHandler.isImage("picture.jpeg"));

      assertFalse(ExtensionHandler.isImage("video.mp4"));
      assertFalse(ExtensionHandler.isImage("file.txt"));
      assertFalse(ExtensionHandler.isImage(""));
      assertFalse(ExtensionHandler.isImage(null));
   }

   @Test
   public void testIsVideo()
   {
      assertTrue(ExtensionHandler.isVideo("video.mp4"));

      assertFalse(ExtensionHandler.isVideo("photo.jpg"));
      assertFalse(ExtensionHandler.isVideo("image.PNG"));
      assertFalse(ExtensionHandler.isVideo("animation.gif"));
      assertFalse(ExtensionHandler.isVideo("picture.jpeg"));
      assertFalse(ExtensionHandler.isVideo("file.txt"));
      assertFalse(ExtensionHandler.isVideo(""));
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
