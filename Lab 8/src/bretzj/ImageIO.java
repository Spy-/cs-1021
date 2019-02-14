/*
 * CS1021
 * Winter 2018-2019
 * Lab 9: Final Project
 * Name: John Bretz
 * Created 2/7/19
 */
package bretzj;

import javafx.scene.control.Alert;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;

import static bretzj.Util.*;

/**
 * Contains methods for saving and reading custom formats
 */
public class ImageIO {
    /**
     * Saves the image to the given .msoe path
     *
     * @param path the path
     * @throws FileNotFoundException if the file does not exist
     */
    static void saveMsoeFile(Path path, ImageView viewport) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(path.toString());
        Image image = viewport.getImage();
        PixelReader pr = image.getPixelReader();
        pw.println("MSOE");
        pw.println((int) image.getWidth() + " " + (int) image.getHeight());

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                String color = intToHex(pr.getColor(x, y));
                pw.print(color + " ");
            }
            pw.println();
        }
        pw.close();
    }

    /**
     * reads a .msoe file and creates an image object with the data
     *
     * @param file the file
     * @return the image object
     * @throws FileNotFoundException if the file does not exist
     */
    static Image readMsoeImage(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        try {
            if (scanner.nextLine().equals("MSOE")) {

                Scanner size = new Scanner(scanner.nextLine());
                int width = size.nextInt();
                int height = size.nextInt();

                WritableImage constructedImage = new WritableImage(width, height);
                PixelWriter pw = constructedImage.getPixelWriter();

                for (int y = 0; y < height; y++) {
                    Scanner line = new Scanner(scanner.nextLine());
                    for (int x = 0; x < width; x++) {
                        Color color = Color.web(line.next());
                        pw.setColor(x, y, color);
                    }
                }

                return constructedImage;
            }
        } catch (IllegalArgumentException e) {
            throwAlert(Alert.AlertType.ERROR, "Error opening file", "The file is corrupted");
        }
        return null;
    }

    /**
     * Converts a BMsoe file into a javafx image
     *
     * @param file the file
     * @return the image
     * @throws IOException if the file doesn't exist
     */
    static Image readBMsoeImage(File file) throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));

        try {
            in.readByte();
            in.readByte();
            in.readByte();
            in.readByte();
            in.readByte();
            int width = in.readInt();
            int height = in.readInt();

            WritableImage constructedImage = new WritableImage(width, height);
            PixelWriter pw = constructedImage.getPixelWriter();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    pw.setColor(x, y, intToColor(in.readInt()));
                }
            }

            return constructedImage;
        } catch (IllegalArgumentException e) {
            throwAlert(Alert.AlertType.ERROR, "Error opening file", "The file is corrupted");
            return null;
        }
    }

    /**
     * Saves the current image to a bmsoe file
     *
     * @param path     the path to the file
     * @param viewport the viewport containing the image
     * @throws IOException if the path is not valid
     */
    static void saveBMsoeFile(Path path, ImageView viewport) throws IOException {
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path.toFile())));
        Image image = viewport.getImage();
        PixelReader pr = image.getPixelReader();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        out.writeBytes("BMSOE");
        out.writeInt(width);
        out.writeInt(height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                out.writeInt(pr.getArgb(x, y));
            }
        }

        out.close();
    }

}
