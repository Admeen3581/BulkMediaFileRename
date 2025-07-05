package FileHandler;

import javax.swing.*;
import java.io.File;
import java.net.URI;

public class DirectoryBrowser
{
   /**
    * Opens a directory selector dialog to allow the user to select a directory.
    * The dialog allows only directory selection and does not permit the selection of files.
    * By default, it opens in the user's primary "Documents" directory, if accessible.
    *
    * @param title the title displayed in the dialog window
    * @return the selected directory as a File object, or null if the user cancels the selection
    */
   public static File selectDirectory(String title)
   {
      JFileChooser chooser = new JFileChooser();
      chooser.setDialogTitle(title);
      chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      chooser.setAcceptAllFileFilterUsed(false);

      //Select User's Primary Directory (Unless previous dir is remembered)
      try
      {
         chooser.setCurrentDirectory(new File(System.getProperty("user.home"), "Documents"));
      }
      catch (IndexOutOfBoundsException e)
      {
         //Let it default
      }

      int result = chooser.showOpenDialog(null);

      if(result == JFileChooser.APPROVE_OPTION)
         return chooser.getSelectedFile();

      return null;//User cancel action
   }
}
