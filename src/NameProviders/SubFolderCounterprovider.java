package NameProviders;

import java.io.File;

public class SubFolderCounterprovider implements NameProvider{

    long counter;

    public SubFolderCounterprovider(){
        this.counter = 0;
    }

    @Override
    public void resetState() {
       counter++;
    }

    @Override
    public void inferOldState() {
        //When we leave the subfolder we don't do anything
    }

    @Override
    public String getName(File file) {
        //we return the subfolder counter
        return Long.toString(counter);
    }
}
