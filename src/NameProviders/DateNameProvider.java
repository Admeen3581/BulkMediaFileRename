package NameProviders;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
public class DateNameProvider implements NameProvider {

    @Override
    public void resetState() {/* We do nothing here as the class is stateless */}

    @Override
    public void inferOldState() {/* We do not have state, we do nothing */}

    @Override
    public String getName(File file) { return DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(new Date(file.lastModified())); }
}
