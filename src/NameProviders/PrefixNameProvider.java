package NameProviders;

import java.io.File;

public class PrefixNameProvider implements NameProvider {
    String prefix;
    NameProvider nameProvider;

    public PrefixNameProvider(String prefix, NameProvider nameProvider)
    {
        this.prefix = prefix;
        this.nameProvider = nameProvider;
    }

    @Override
    public void resetState() { nameProvider.resetState(); }

    @Override
    public void inferOldState() { nameProvider.inferOldState(); }

    @Override
    public String getName(File file) { return prefix + nameProvider.getName(file); }
}
