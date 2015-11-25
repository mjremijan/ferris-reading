package org.ferris.reading.main;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class Burst {

    /**
     * I need to execute this:
     * 
     * run-console.bat -f [pdf_to_burst] -o [output_directory] -s BURST -overwrite split
     *  
     * @param pdf The PDF file to burst into individual pages
     */
    public static void start(File pdf)
    {
        try {
            System.out.printf("\n\n*** BURST START ***\n\n");
            String bat = FileTool.exists(FileProducer.bat()).getAbsolutePath();
            String f = FileTool.exists(pdf).getAbsolutePath();
            String o = FileTool.makeDirectory(FileProducer.burst()).getAbsolutePath();
            String cmd = "split";

            ProcessBuilder pb = new ProcessBuilder(
                    bat, "-f", f, "-o", o, "-s", "BURST", "-overwrite", cmd
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

            System.out.println("*** BURST DONE ***");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public static void delete(int startReadingAtPage, int stopReadingAtPage)
    {
        try {
            System.out.printf("\n\n*** DELETE START ***\n\n");
            
            List<File> list
                = FileProducer.burstPdfsSorted();
            
            for (int i=1; i<startReadingAtPage; i++) {
                System.out.printf("Deleting pdf %s\n", list.get(i-1).getName());
                FileTool.deleteFile(list.get(i-1));
            }
            
            if (stopReadingAtPage != -1) {
                for (int i=list.size(); i>stopReadingAtPage; i--) {
                    System.out.printf("Deleting pdf %s\n", list.get(i-1).getName());
                    FileTool.deleteFile(list.get(i-1));
                }
            }

            System.out.println("*** DELETE DONE ***");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
