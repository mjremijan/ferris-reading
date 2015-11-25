package org.ferris.reading.main;

import java.io.File;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class FileTool {

    public static final void deleteDirectory(File directory) {
        if (!directory.exists()) {
            return;
        }

        String[] list = directory.list();

        if (list != null) {
            for (int i = 0; i < list.length; i++) {
                File entry = new File(directory, list[i]);
                if (entry.isDirectory()) {
                    deleteDirectory(entry);
                } else {
                    deleteFile(entry);
                }
            }
        }
        deleteFile(directory);
    }

    public static final void deleteFile(File entry) {
        for (entry.delete(); entry.exists(); entry.delete()) {
            System.out.printf("Sleeping while trying to delete \"%s\"\n", entry.getPath());
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static final File makeDirectory(File directory) {
        for (directory.mkdirs(); !directory.exists(); directory.mkdirs()) {
            System.out.printf("Sleeping while trying to mkdirs \"%s\"\n", directory.getPath());
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
        return directory;
    }

    public static final File exists(File f) {
        if (f.exists()) {
            return f;
        } else {
            throw new RuntimeException(
                String.format(
                    "File does not exist: \"%s\" ", f.getAbsolutePath()));
        }
    }
}
