package FileHandler;

//Imports
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DirectoryIterator
{
    /**
     * Gets all media files from a directory recursively
     *
     * @param directory the directory to search
     * @return list of media files
     */
    public static List<File> iterate(File directory)
    {
        if (directory == null)
            return null;

        if (!directory.exists() || !directory.isDirectory())
            return new ArrayList<>();

        try
        {
            VideoTranscoder.processFolder(Paths.get(directory.getPath()));//Converts before files are displayed.
        }
        catch (Exception e)
        {
            System.err.println("Failed to convert folder to MP4: " + directory.getPath() + "\n" + e.getMessage());
            return null;
        }

        List<File> mediaFiles = new ArrayList<>();
        File[] files = directory.listFiles();

        if(files == null)
            return mediaFiles;


        for (File file : files)
        {
            if (file.isFile() && ExtensionHandler.isAcceptable(file))
            {
                mediaFiles.add(file);
            }
            else if (file.isDirectory())
            {
                mediaFiles.addAll(iterate(file));
            }
        }
        return mediaFiles;
    }
}
