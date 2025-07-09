package FileHandler;

import java.io.File;

public class ExtensionHandler
{
   public static final String[] ADJUSTABLE_TYPES = {".MOV"};

   /**
    * Determines whether the given file name corresponds to a video file.
    *
    * @param file the file to be checked
    * @return true if the file name ends with ".mp4", false otherwise
    */
   public static boolean isVideo(File file)
   {
      if (file == null)
         return false;

      return file.getName().toUpperCase().endsWith(".MP4");
   }

   /**
    * Determines whether the given file name corresponds to an image file.
    *
    * @param file the file to be checked
    * @return true if the file name ends with ".gif", ".jpg", ".png", or ".jpeg", false otherwise
    */
   public static boolean isImage(File file)
   {
      if (file == null)
         return false;

      String fileName = file.getName().toUpperCase();

      return fileName.toUpperCase().endsWith(".GIF") ||
            fileName.toUpperCase().endsWith(".JPG") ||
            fileName.toUpperCase().endsWith(".PNG") ||
            fileName.toUpperCase().endsWith(".JPEG");
   }

   /**
    * Determines whether the given file name is acceptable as either a video or an image file.
    *
    * @param file the file to be checked
    * @return true if the file name corresponds to a video or an image file, false otherwise
    */
   public static boolean isAcceptable(File file)
   {
      return isVideo(file) || isImage(file);
   }
}
