/*
 * CS1021
 * Winter 2018-2019
 * Lab 9: Final Project
 * Name: John Bretz
 * Created 2/7/19
 */
package bretzj;

import javafx.scene.control.Alert;
import javafx.scene.paint.Color;

import java.nio.file.Path;

/**
 * contains helper methods for converting colors, creating alerts and getting file extensions
 */
public class Util {
    /**
     * converts a javafx color to a hexadecimal color
     *
     * @param color the color
     * @return the hex string
     */
    static String intToHex(Color color) {
        return "#" + color.toString().substring(2, 8).toUpperCase();
    }

    /**
     * Converts a color object into an int
     * @param color the color
     * @return the int
     */
    static int colorToInt(Color color) {
        int red = ((int) (color.getRed() * 255)) & 0x000000FF;
        int green = ((int) (color.getGreen() * 255)) & 0x000000FF;
        int blue = ((int) (color.getBlue() * 255)) & 0x000000FF;
        int alpha = ((int) (color.getOpacity() * 255)) & 0x000000FF;
        return (alpha << 24) + (red << 16) + (green << 8) + blue;
    }

    /**
     * converts an int into a color object
     * @param color the int
     * @return the color
     */
    static Color intToColor(int color) {
        double red = ((color >> 16) & 0x000000FF) / 255.0;
        double green = ((color >> 8) & 0x000000FF) / 255.0;
        double blue = (color & 0x000000FF) / 255.0;
        double alpha = ((color >> 24) & 0x000000FF) / 255.0;
        return new Color(red, green, blue, alpha);
    }

    /**
     * Helper method for creating alerts
     *
     * @param type    the alert type
     * @param header  the header text
     * @param content the content text
     * @return the alert
     */
    static Alert throwAlert(Alert.AlertType type, String header, String content) {
        Alert alert = new Alert(type);
        alert.setContentText(content);
        alert.setHeaderText(header);
        return alert;
    }

    /**
     * Returns true if the path is a .msoe file
     *
     * @param path the path
     * @return whether or not it is a .msoe file
     */
    static boolean isMsoeFile(Path path) {
        return path.toString().toLowerCase().endsWith(".msoe");
    }

    /**
     * Whether or not the path is a bmsoe file
     *
     * @param path the path
     * @return true if it is a bmsoe file
     */
    static boolean isBMsoeFile(Path path) {
        return path.toString().toLowerCase().endsWith(".bmsoe");
    }
}
