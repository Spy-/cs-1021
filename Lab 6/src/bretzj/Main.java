/**
 * CS1021
 * Winter 2018-2019
 * Name: John Bretz
 * Created 1/16/19
 */
package bretzj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("lab.fxml"));

        stage.setTitle("Website Tester");
        stage.setScene(new Scene(root, 600, 600));
        stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
