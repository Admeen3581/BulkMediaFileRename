package NameProviders;

import java.io.File;

public class MultiNameProvider implements NameProvider{

    NameProvider first;
    NameProvider second;
    String sepparator;

    public MultiNameProvider (NameProvider first, NameProvider second, String sepparator)
    {
        this.first = first;
        this.second = second;
        this.sepparator = sepparator;
    }

    @Override
    public void resetState() {
        first.resetState();
        second.resetState();
    }

    @Override
    public void inferOldState() { first.inferOldState(); second.inferOldState();}

    @Override
    public String getName(File file) {
        return first.getName(file) + sepparator + second.getName(file);
    }
}
