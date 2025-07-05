package FileHandler;

public class ExtensionHandler
{
   /**
    * Determines whether the given file name corresponds to a video file.
    *
    * @param fileName the name of the file to be checked
    * @return true if the file name ends with ".mp4", false otherwise
    */
   public static boolean isVideo(String fileName)
   {
      if(fileName == null)
         return false;

      return fileName.toUpperCase().endsWith(".MP4");
   }

   /**
    * Determines whether the given file name corresponds to an image file.
    *
    * @param fileName the name of the file to be checked
    * @return true if the file name ends with ".gif", ".jpg", ".png", or ".jpeg", false otherwise
    */
   public static boolean isImage(String fileName)
   {
      if(fileName == null)
         return false;

      return fileName.toUpperCase().endsWith(".GIF") ||
             fileName.toUpperCase().endsWith(".JPG") ||
             fileName.toUpperCase().endsWith(".PNG") ||
             fileName.toUpperCase().endsWith(".JPEG");
   }

   /**
    * Determines whether the given file name is acceptable as either a video or an image file.
    *
    * @param fileName the name of the file to be checked
    * @return true if the file name corresponds to a video or an image file, false otherwise
    */
   public static boolean isAcceptable(String fileName)
   {
      return isVideo(fileName) || isImage(fileName);
   }
}
