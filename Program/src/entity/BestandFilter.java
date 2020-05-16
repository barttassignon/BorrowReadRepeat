package entity;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class BestandFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        if(f.isDirectory() || f.getName().endsWith(".csv"))
            return true;
        return false;
    }

    @Override
    public String getDescription() {
        return "CSV bestand";
    }
}
