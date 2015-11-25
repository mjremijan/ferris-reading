package org.ferris.reading.main;


import java.io.File;


/**
 * @author  Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class Main {

    /**
     * @param args Command line parameters
     */
    public static void main(String[] args) {
        // Welcome
        System.out.printf("\nWelcome to Ferris Reading\n\n");
        
        // First, clean the work directory
        Clean.all();
        
        // Get PDF to split
        File pdf
            = Prompt.file("PDF file to split");
        
        // Get number of days to read
        int totalReadingDays
            = Prompt.integer("Number of days to read");
        
        // Start reading at page of PDF
        int startReadingAtPage
            = Prompt.integer("Start reading at page (0)", 0);
        
        // Stop reading at page of PDF
        int stopReadingAtPage
            = Prompt.integer("Stop reading at page (last)", -1);
        
        // Burst the PDF into individual files
        Burst.start(pdf);
        
        // Delete uneeded PDF files
        Burst.delete(startReadingAtPage, stopReadingAtPage);
        
        // Generate XML configuration files
        XML.start(totalReadingDays);
        
        // Concatenate the individual PDFs into read day PDFs
        Concat.start();
        
        System.out.printf("\nSUCCESS!\n\nPDFs available at:\n%s\n\n", FileProducer.pdf().getAbsolutePath());
        System.out.printf("Good bye!\n");
    }
}
