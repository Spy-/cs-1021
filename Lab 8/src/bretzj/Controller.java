/*
 * CS1021
 * Winter 2018-2019
 * Lab 8: Final Project
 * Name: John Bretz
 * Created 2/1/19
 */
package bretzj;

import edu.msoe.cs1021.ImageUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static bretzj.ImageIO.*;
import static bretzj.Util.*;

/**
 * The JavaFX controller
 */
public class Controller {
    public ImageView viewport;
    private Image image;

    /**
     * opens an image file chosen by the user
     */
    public void open() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.tiff", "*.msoe", "*.bmsoe")
        );

        File selectedFile = fileChooser.showOpenDialog(Main.stage);

        try {
            Path path = selectedFile.toPath();
            if (path.toString().toLowerCase().endsWith(".msoe")) {
                image = readMsoeImage(selectedFile);
            } else if (path.toString().toLowerCase().endsWith(".bmsoe")) {
                image = readBMsoeImage(selectedFile);
            } else {
                image = ImageUtil.readImage(path);
            }
        } catch (IOException e) {
            throwAlert(Alert.AlertType.ERROR, "Error opening file", "The file does not exist").show();
        } catch (NullPointerException ignored) {
        }
        viewport.setImage(image);
    }

    /**
     * saves the image to the selected file
     */
    public void save() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.tiff", "*.msoe", "*.bmsoe"),
                new FileChooser.ExtensionFilter("All Files", "*")
        );

        File selectedFile = fileChooser.showOpenDialog(Main.stage);
        Path path = selectedFile.toPath();

        try {
            if (isMsoeFile(path)) {
                saveMsoeFile(path, viewport);
            } else if (isBMsoeFile(path)) {
                saveBMsoeFile(path, viewport);
            } else {
                ImageUtil.writeImage(path, viewport.getImage());
            }
        } catch (IOException e) {
            throwAlert(Alert.AlertType.ERROR, "Error opening file", "The file does not exist").show();
        }
    }

    /**
     * reloads the original image
     */
    public void reload() {
        viewport.setImage(image);
    }

    /**
     * converts the current image to grayscale
     */
    public void gray() {
        viewport.setImage(
                transformImage(viewport.getImage(), (y, color) -> {
                    int pixel = colorToInt(color);
                    int red = ((pixel >> 16) & 0xff);
                    int green = ((pixel >> 8) & 0xff);
                    int blue = (pixel & 0xff);

                    int grayLevel = (int) (0.2162 * (double) red + 0.7152 * (double) green + 0.0722 * (double) blue);
                    grayLevel = 255 - grayLevel; // Inverted the grayLevel value here.
                    int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;

                    return intToColor(-gray);
                })
        );
    }

    /**
     * converts the current image to a negative of it
     */
    public void neg() {
        viewport.setImage(
                transformImage(viewport.getImage(), (y, color) ->
                        Color.rgb((int) (255 - color.getRed() * 255), (int) (255 - color.getGreen() * 255), (int) (255 - color.getBlue() * 255))
                )
        );
    }

    /**
     * convert image to a red shift
     */
    public void red() {
        viewport.setImage(transformImage(viewport.getImage(), (y, color) -> Color.rgb((int) (color.getRed() * 255), 0, 0)));
    }

    /**
     * alternate every line from grayscale to red shift
     */
    public void redGray() {
        viewport.setImage(
                transformImage(viewport.getImage(), ((y, color) -> {
                    if (y % 2 == 0) {
                        int pixel = colorToInt(color);
                        int red = ((pixel >> 16) & 0xff);
                        int green = ((pixel >> 8) & 0xff);
                        int blue = (pixel & 0xff);

                        int grayLevel = (int) (0.2162 * (double) red + 0.7152 * (double) green + 0.0722 * (double) blue);
                        grayLevel = 255 - grayLevel;
                        int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;

                        return intToColor(-gray);
                    } else {
                        return Color.rgb((int) (color.getRed() * 255), 0, 0);
                    }
                }))
        );
    }

    /**
     * shows the filter window
     *
     * @throws IOException if the fxml file doesn't exist
     */
    public void showFilter() throws IOException {
        Stage filterStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("filter.fxml"));
        Pane root = loader.load();
        FilterController filter = loader.getController();
        filter.injectMainController(this);
        filterStage.setScene(new Scene(root, 300, 200));
        filterStage.setTitle("Filter Kernel");
        filterStage.setResizable(false);
        filterStage.show();
    }

    /**
     * transforms the image given the transform function passed to it
     *
     * @param image     the image to modify
     * @param transform the transform interface
     * @return the modified image
     */
    private static Image transformImage(Image image, ITransformable transform) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        WritableImage newImage = new WritableImage(width, height);
        PixelReader pr = image.getPixelReader();
        PixelWriter pw = newImage.getPixelWriter();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pw.setColor(x, y, transform.apply(y, pr.getColor(x, y)));
            }
        }

        return newImage;
    }

    /**
     * Preload an image into the program for testing purposes
     */
    @FXML
    public void initialize() {
        try {
            image = readBMsoeImage(new File("specs.bmsoe"));
            viewport.setImage(image);
        } catch (Exception ignored) {
        }
    }
}
