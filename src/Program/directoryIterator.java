package Program;

import FileHandler.FileHandle;
import NameProviders.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DirectoryIterator {

    public static void iterate(FileHandle f, NameProvider np, int levels) throws IOException {
        if(levels >0)
        {
            if (!f.isDirectory()) {
                throw new FileNotFoundException("The File: " + f.getName() + " is not a directory");
            }

            String contents[] = f.list();
            np.resetState();
            for (String s : contents) {
                FileHandle currentFile = new FileHandle(new File(f.getPath() + "\\" +s));
                if (currentFile.isDirectory()) iterate(currentFile, np, --levels);
                else currentFile.renameFile(f.getPath() + "\\"  + np.getName(f.getFile()));
            }

            np.inferOldState();
        }
    }
}
