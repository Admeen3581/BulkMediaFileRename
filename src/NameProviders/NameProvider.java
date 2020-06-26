package NameProviders;

import java.io.File;

public interface NameProvider {
    public void resetState ();
    public void inferOldState ();
    public String getName (File file);
}
