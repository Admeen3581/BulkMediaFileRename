package Program;

import FileHandler.FileHandle;
import NameProviders.*;

import java.io.File;

public class Main {
    public static void main (String [] args) {
        int levels = 2;
        String path = "Path";

        NameProvider namingPattern = new MultiNameProvider(new StaticNameProvider("Name"), new MultiNameProvider(new PrefixNameProvider("Prefix", new SubFolderCounterprovider()), new PrefixNameProvider("Prefix", new CounterNameProvider(1)), "" ) , " - ");
        try {
            DirectoryIterator.iterate(new FileHandle(new File(path)),namingPattern, levels);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
