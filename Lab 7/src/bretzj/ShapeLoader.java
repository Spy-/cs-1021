/**
 * CS1021
 * Winter 2018-2019
 * Name: John Bretz
 * Created 1/24/19
 */
package bretzj;

import javafx.scene.control.Alert;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads shapes from file and calls the appropriate methods to draw them to the canvas
 */
public class ShapeLoader {

    private Scanner scanner;
    private ArrayList<String[]> data = new ArrayList<String[]>();

    /**
     * Constructor
     *
     * @param file a file object
     * @throws FileNotFoundException if the file does not exist
     */
    public ShapeLoader(File file) throws FileNotFoundException {
        this.scanner = new Scanner(file);
        parseFile();
    }

    /**
     * Parses the file and extracts information about each shape
     */
    private void parseFile() {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().replaceAll("(\\s){1}(\\s)*", " ");
            String[] text = line.split(" ", 7);
            data.add(text);
        }
    }

    /**
     * Returns the size of the window
     *
     * @return the size of window
     */
    public double[] getSize() {
        String[] size = data.get(1);
        return new double[]{Double.parseDouble(size[0]), Double.parseDouble(size[1])};
    }

    /**
     * Sets the title of window
     *
     * @param stage the stage to set the title of
     */
    public void handleHeader(ShapeCreator stage) {
        String title = String.join(" ", data.get(0));
        stage.setTitle(title);
    }

    /**
     * Sets the background of the window
     *
     * @param sc a ShapeCreator object
     */
    public void setBackground(ShapeCreator sc) {
        double[] size = getSize();
        String color = data.get(2)[0];

        sc.fillRect(0, 0, size[0], size[1], Color.web(color));
    }

    /**
     * draws all the shapes to the canvas
     *
     * @param sc a ShapeCreator object
     */
    public void drawShapes(ShapeCreator sc) {
        for (int i = 3; i < data.size(); i++) {
            String[] text = data.get(i);
            try {
                String shape = text[0].substring(0, text[0].length() - 1);

                double x = Double.parseDouble(text[1]);
                double y = Double.parseDouble(text[2]);
                Color color = Color.web(text[3]);
                double widthBaseRadius = 0;
                double height = 0;
                String label = "";

                try {
                    widthBaseRadius = Double.parseDouble(text[4]);
                    height = Double.parseDouble(text[5]);
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }

                try {
                    label = text[6];
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
                if (x >= 0 && y >= 0 && widthBaseRadius >= 0 && height >= 0) {
                    switch (shape) {
                        case "P":
                            sc.Point(x, y, color);
                            break;
                        case "C":
                            sc.Circle(x, y, color, widthBaseRadius);
                            break;
                        case "T":
                            sc.Tri(x, y, color, widthBaseRadius, height);
                            break;
                        case "R":
                            sc.Rect(x, y, color, widthBaseRadius, height);
                            break;
                        case "LT":
                            sc.LabelTri(x, y, color, widthBaseRadius, height, label);
                            break;
                        case "LR":
                            sc.LabelRect(x, y, color, widthBaseRadius, height, label);
                            break;
                        default:
                            break;
                    }
                } else {
                    System.out.println("There is an invalid argument on line: " + (i + 1));
//                    throwAlert(Alert.AlertType.ERROR, "Invalid Argument", "An argument is negative on line: " + (i + 1)).show();
                }
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException ignored) {
            }
        }
    }

    /**
     * Helper method to easily create Alert objects
     *
     * @param type    the AlertType
     * @param header  the header text
     * @param content the content of the alert window
     * @return the Alert object
     */
    private Alert throwAlert(Alert.AlertType type, String header, String content) {
        Alert alert = new Alert(type);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert;
    }
}
