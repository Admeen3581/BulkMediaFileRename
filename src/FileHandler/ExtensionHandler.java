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
      if(file == null)
         return false;

      //Note: we don't define a String var here due to an immutable type

      for(String type : ADJUSTABLE_TYPES)
      {
         if(file.getName().toUpperCase().endsWith(type))
         {
            return true;
         }
      }

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
      if(file == null)
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

   /**
    * Converts the given file to a compatible type, if possible. Currently, it converts:
    * .MOV, ...
    *
    * @param file the file to be converted
    * @return boolean dictating (un)successful renaming
    */
   public static File convertToCompatibleType(File file)
   {
      String fileName = file.getName().toUpperCase();
      switch (fileName.substring(fileName.lastIndexOf(".") + 1))
      {
         case "MOV":
            File updatedFile = new File(file.getParentFile(), file.getName().replace(".MOV", ".MP4"));
            file.renameTo(updatedFile);
            return updatedFile;
         default:
            System.out.println("Unsupported file type -- You can add it in ExtensionHandler.java 7:1 : " + fileName);
            return null;
      }
   }
}
