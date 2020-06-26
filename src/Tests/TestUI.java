package Tests;

import NameProviders.*;
import FileHandler.*;

import java.io.File;


public class TestUI {
    public static void main (String [] args) throws Exception {
        NameProvider np = new MultiNameProvider(new DateNameProvider(), new CounterNameProvider(), "-");

        //creating testFile
        FileHandle f = new FileHandle(new File("Test.txt"));
        //Setting the modified attribute to last modified right now
        f.setLastModified(0L);

        //renaming the file
        f.renameFile(np.getName(f.getFile()));
    }
}
