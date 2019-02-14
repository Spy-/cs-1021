/**
 * CS1021
 * Winter 2018-2019
 * Name: John Bretz
 * Created 1/24/19
 */
package bretzj;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Main entry point for lab
 */
public class Main extends Application {

    private File file = new File("data.txt");

    /**
     * Main method
     *
     * @param args arguments for program
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Called automatically by JavaFX
     *
     * @param stage the stage
     */
    @Override
    public void start(Stage stage) {
        try {
            ShapeLoader sl = new ShapeLoader(file);
            ShapeCreator sc = new ShapeCreator(sl.getSize());

            sl.handleHeader(sc);
            sl.setBackground(sc);
            sl.drawShapes(sc);
            sc.showPlot();
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("File does not exist.");
            alert.setContentText("The file does not exist");
            alert.show();
        } catch (IllegalArgumentException e) {
            System.out.println("A shape has an invalid argument.");
        }
    }
}
