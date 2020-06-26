package NameProviders;

import java.io.File;

public class StaticNameProvider implements NameProvider{

    String name;

    public StaticNameProvider (String name) { this.name = name; }

    @Override
    public void resetState() { }

    @Override
    public void inferOldState() { }

    @Override
    public String getName(File file) {
        return name;
    }
}
