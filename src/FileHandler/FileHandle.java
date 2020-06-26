package FileHandler;

import NameProviders.NameProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

public class FileHandle {

    File file;

    public FileHandle (File f) { this.file = f; }

    public boolean renameFile(String name) throws IOException {
        //Handling if the source file does not exist
        if(!file.exists())
            throw new FileNotFoundException("the file " + file.getName() + " does not exist");

        File desitnation = new File(name+ "." + fileExtension(file));

        //Handling that the destination already esists
        if(desitnation.exists())
            throw  new FileAlreadyExistsException("The file "+file.getName() + "was supposed to be renamed to: " + desitnation.getName());

        boolean success = file.renameTo(desitnation);

        if(success)
            this.file = desitnation;

        return success;
    }

    public String fileExtension (File file) {
        int dotIndex = file.getName().lastIndexOf('.');
        return (dotIndex == -1) ? "" : file.getName().substring(dotIndex + 1);
    }

    public String getName() {return file.getName();}

    public File getFile() {return file;}

    public void setLastModified(Long lastModified) {file.setLastModified(lastModified);}

    public boolean isDirectory() {return file.isDirectory();}

    public String [] list () {return file.list();}

    public String getPath () {return file.getPath();}
}
