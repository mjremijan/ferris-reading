package org.ferris.reading.main;

import java.io.File;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class Clean {

    public static void all() {
        File workDir = FileProducer.work();
        System.out.printf("Cleaning work directory:\n  %s\n\n", workDir.getAbsolutePath());
        FileTool.deleteDirectory(workDir);
        FileTool.makeDirectory(workDir);
    }       
}
