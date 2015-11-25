package org.ferris.reading.main;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class XML {

    public static void start(int totalReadingDays)
    {
        try {
            
            System.out.printf("\n\n*** XML START ***\n\n");
            
            File xmlDir
                = FileTool.makeDirectory(FileProducer.xml());
            
            String burstDirPath
                = FileProducer.burst().getAbsolutePath();
            
            List<File> list
                = FileProducer.burstPdfsSorted();
            
            int totalNumberOfPagesToRead
                = list.size();
            
            int pagesPerDayYouNeedToRead 
                = (int)Math.ceil((double)totalNumberOfPagesToRead / (double)totalReadingDays);
            System.out.printf("Pages per day you need to read: %d\n",pagesPerDayYouNeedToRead);
            
            Iterator<File> listItr
                = list.iterator();
            
            for (int day=1; day<=totalReadingDays; day++) 
            {
                StringBuilder sp = new StringBuilder();
                sp.append(String.format("<filelist>\n"));
                {
                    sp.append(String.format("  <fileset dir=\"%s\" usecurrentdir=\"false\">\n", burstDirPath));
                    {
                        for (int m=1; m<=pagesPerDayYouNeedToRead; m++) {
                            if (listItr.hasNext()) {
                                File pdf = listItr.next();
                                sp.append(String.format("    <file value=\"%s\" />\n", pdf.getName()));
                            }
                        }
                    }
                    sp.append(String.format("  </fileset>\n"));
                }
                sp.append(String.format("</filelist>\n"));
                
                File mergeXmlFile 
                    = new File(xmlDir, String.format("Day%03d.xml", day));
                System.out.printf("Creating XML %s\n",mergeXmlFile.getName());
                
                FileOutputStream fos = new FileOutputStream(mergeXmlFile);
                fos.write(sp.toString().getBytes());
                fos.flush();
                fos.close();
            }
            
            System.out.println("*** XML DONE ***");
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
