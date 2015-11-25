package org.ferris.reading.main;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FileProducer extends File {

    private static final long serialVersionUID = 7491901906021288631L;

    private FileProducer(String path) {
        super(path);
    }
    
    public static File work() {
        return new File(get(), "work");
    }
    
    public static File burst() {
        return new File(work(), "burst");
    }
    
    public static List<File> burstPdfsSorted() {
        File [] array =
            burst().listFiles(new FilenameFilter() {
                public boolean accept(File arg0, String arg1) {
                    return arg1.endsWith(".pdf");
                }
            });
        List<File> list 
            = Arrays.asList(array);
            Collections.sort(list, new Comparator<File>(){
                @Override
                public int compare(File o1, File o2) {
                    Integer i1 = Integer.parseInt(o1.getName().split(("_"))[0]);
                    Integer i2 = Integer.parseInt(o2.getName().split(("_"))[0]);
                    return i1.compareTo(i2);
                }
            });
        return list;
    }
    
    public static File xml() {
        return new File(work(), "xml");
    }
    
    public static File pdf() {
        return new File(work(), "pdf");
    }
    
    public static File bin() {
        return new File(new File(get(), "pdfsam-2.2.1"), "bin");
    }
    
    public static File bat() {
        return
        new File(bin(), "run-console.bat");
    }
    
    
    private static FileProducer get() {
        // This code assumes the following directory structure
        //
        // /reading
        //    /bin
        //    /lib
        //      ferris-reading-app-1.0.0.0-SNAPSHOT.jar
        //
        // So the the application directory will be 1 
        // directory up from where the JAR file is located.
        try {
            URL jarURL = FileProducer.class.getProtectionDomain().getCodeSource().getLocation();
            URI jarURI = jarURL.toURI();
            File jarFile = new File(jarURI);
            File appFile = jarFile.getParentFile().getParentFile();
            FileProducer appDir = new FileProducer(appFile.getAbsolutePath());
            return appDir;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
