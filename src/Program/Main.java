package Program;

import FileHandler.FileHandle;
import NameProviders.*;

import java.io.File;

public class Main {
    public static void main (String [] args) {
        int levels = 2;
        String path = "C:\\Users\\The0M\\Desktop\\Gravity Falls";

        NameProvider namingPattern = new MultiNameProvider(new StaticNameProvider("Gravity Falls"), new MultiNameProvider(new PrefixNameProvider("E", new SubFolderCounterprovider()), new PrefixNameProvider("S", new CounterNameProvider()), "" ) , " - ");
        try {
            DirectoryIterator.iterate(new FileHandle(new File(path)),namingPattern, levels);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
