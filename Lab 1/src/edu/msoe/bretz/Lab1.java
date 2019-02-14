/*
 * Course: CS1021 - 031
 * Winter 2018
 * Lab 1: Wav Files
 * Name: John Bretz
 * Created: 11/27/2018
 */

package edu.msoe.bretz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;



/**
 * Lab 1
 */
public class Lab1 {

    private static Scanner scanner = new Scanner(System.in);
    private static int option;
    private static String filename = "";
    private static final String EXTENSION = ".wav";
    private static String writeName = "";
    private static WavFile writeFile;
    private static WavFile file;

    private static ArrayList<Double> data = new ArrayList<>();
    private static final int BITS = 16;
    private static final int RATE = 22050;

    public static void main(String[] args) {
        chooseOption();
    }

    /**
     * Handles Option 1
     * reverse order of samples in wav file
     */
    private static void caseOne() {
        writeName = filename + "Rev" + EXTENSION;
        data = file.getSamples();
        Collections.reverse(data);

        int numChannels = file.getNumChannels();
        long numFrames = file.getNumFrames();
        int validBits = file.getValidBits();
        long sampleRate = file.getSampleRate();


        writeFile = new WavFile(writeName, numChannels, numFrames, validBits, sampleRate);
        writeFile.setSamples(data);
        writeFile.close();

        chooseOption();
    }

    /**
     * Handles option 2
     * Creates a wav file of a specified frequency for one second
     */
    private static void caseTwo() {
        writeName = filename + EXTENSION;

        // get a frequency
        long freq = getFrequency();

        // Calculate the sample data
        for (int i = 0; i < RATE; i++) {
            data.add(Math.sin(2 * Math.PI * i * freq / (double) RATE));
        }

        writeFile = new WavFile(writeName, 1, RATE, BITS, RATE);
        writeFile.setSamples(data);
        writeFile.close();
        System.out.println(data);

        // clear the arraylist
        data.clear();

        // Pick options again
        chooseOption();
    }

    /**
     * Handles option 3
     * Creates a wav file using two frequencies on two separate channels
     */
    private static void caseThree() {
        writeName = filename + EXTENSION;

        // Retrieve frequencies
        long freqOne = getFrequency();
        long freqTwo = getFrequency();

        // Calculate the sample data
        for (int i = 0; i < RATE; i++) {
            if (i % 2 == 0) {
                data.add(Math.sin(2 * Math.PI * i * freqOne / (double) RATE));
            } else {
                data.add(Math.sin(2 * Math.PI * i * freqTwo / (double) RATE));
            }
        }

        writeFile = new WavFile(writeName, 2, RATE, BITS, RATE);
        writeFile.setSamples(data);
        writeFile.close();

        // clear the arraylist
        data.clear();

        // pick options again
        chooseOption();
    }

    /**
     * Sets the filename to be whatever the user inputs
     */
    private static void setFilename() {
        System.out.println("Enter a filename");
        filename = scanner.nextLine();
    }

    /**
     * Creates the WavFile object so it can be read
     */
    private static void readFile() {
        String name = filename + EXTENSION;
        file = new WavFile(name);
    }

    /**
     * Asks the user for a valid frequency
     * @return freq
     */
    private static long getFrequency() {
        System.out.println("Enter a frequency");
        String input = scanner.nextLine();
        long freq;
        try {
            freq = Long.parseLong(input);
        } catch (NumberFormatException nfe) {
            freq = getFrequency();
        }
        return freq;
    }

    /**
     * Allows the user to pick an option
     */
    private static void chooseOption() {
        boolean correctInput = false;
        System.out.println("Enter 0, 1, 2 or 3");

        while (!correctInput) {
            String userInput = scanner.nextLine();

            try {
                int userOption = Integer.parseInt(userInput);

                if (userOption < 0 || userOption > 3) {
                    chooseOption();
                } else {
                    option = userOption;
                    correctInput = true;
                }
            } catch (NumberFormatException e) {
                chooseOption();
            }
        }

        switch (option) {
            case 0:
                System.exit(0);
            case 1:
                setFilename();
                readFile();
                caseOne();
                break;
            case 2:
                setFilename();
                caseTwo();
                break;
            case 3:
                setFilename();
                caseThree();
                break;
            default:
                System.exit(0);
        }
    }

}
