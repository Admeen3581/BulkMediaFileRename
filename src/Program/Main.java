package Program;

import FileHandler.FileHandle;
import NameProviders.CounterNameProvider;
import NameProviders.DateNameProvider;
import NameProviders.MultiNameProvider;

import java.io.File;

public class Main {
    public static void main (String [] args) {
        int levels = Integer.parseInt(args[0]);
        String path = args[1];

        try {
            directoryIterator.iterate(new FileHandle(new File(path)), new MultiNameProvider(new DateNameProvider(), new CounterNameProvider(), "-"), levels);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
