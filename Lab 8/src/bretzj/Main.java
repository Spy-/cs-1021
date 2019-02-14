/*
 * CS1021
 * Winter 2018-2019
 * Lab 8: Final Project
 * Name: John Bretz
 * Created 2/1/19
 */
package bretzj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class
 */
public class Main extends Application {

    static Stage stage;

    /**
     * Main entry point into javafx
     *
     * @param stage the main stage
     * @throws Exception some exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("bretzj.fxml"));
        stage.setTitle("Image Manipulator");
        stage.setScene(new Scene(root, 600, 700));
        stage.setResizable(false);
        Main.stage = stage;
        stage.show();
    }

    /**
     * main method
     *
     * @param args cmdline arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
