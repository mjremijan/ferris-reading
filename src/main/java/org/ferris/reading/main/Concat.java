package org.ferris.reading.main;

import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.util.Map;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class Concat {

    /**
     * I need to execute this:
     * 
     * run-console.bat -o .\day001.pdf -l l.xml concat
     *  
     */
    public static void start()
    {
        try {
            System.out.println("\n\n*** CONCAT START ***\n\n");
            
            File[] xmlFiles = FileProducer.xml().listFiles(new FilenameFilter() {
                public boolean accept(File arg0, String arg1) {
                    return arg1.endsWith(".xml");
                }
            });
            
            for (File xmlFile : xmlFiles) 
            {
                String bat = FileTool.exists(FileProducer.bat()).getAbsolutePath();
                String o = String.format("%s\\%s.pdf", FileTool.makeDirectory(FileProducer.pdf()).getAbsolutePath(), xmlFile.getName().split("\\.")[0]);
                String l = String.format("%s", xmlFile.getAbsolutePath());
                String cmd = "concat";

                ProcessBuilder pb = new ProcessBuilder(
                    bat, "-o", o, "-l", l, "-overwrite", cmd
                );
                pb.directory(FileProducer.bin());
                Map<String, String> env = pb.environment();
                env.put("NOPAUSE", "true");
                Process process = pb.start();

                InputStream inputStream = process.getInputStream();
                StreamPrinter.print(inputStream, "Input Stream");
                inputStream.close();
                InputStream errorStream = process.getErrorStream();
                StreamPrinter.print(errorStream, "Error Stream");
                errorStream.close();
            }
            
            System.out.println("*** CONCAT DONE ***");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
