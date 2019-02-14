/*
 * CS1021
 * Winter 2018-2019
 * Lab Game Of Life
 * Name: John Bretz
 * Created 1/9/19
 */
package bretz;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Main class to run the Game of Life
 */
public class GameOfLife extends Application {

    private final int WIDTH = 1200;
    private final int HEIGHT = 900;
    public static Text score = new Text();

    private Button randomize = new Button("Randomize");
    private Button step = new Button("Step");
    private Button play = new Button("Toggle Run");
    private Button clear = new Button("Clear");
    private Pane game = new Pane();
    private LifeGrid grid = new LifeGrid(game, WIDTH, HEIGHT);

    private boolean run = false;

    /**
     * Creates the application window and implements all functionality
     *
     * @param stage the primary stage
     */
    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        HBox toolbar = new HBox();
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        toolbar.getChildren().addAll(randomize, step, play, clear, score);
        root.getChildren().addAll(toolbar, game);

        // Add user input
        randomize.setOnAction(actionEvent -> grid.randomize(stage));
        step.setOnAction(actionEvent -> grid.iterate(stage));
        play.setOnAction(actionEvent -> run = !run);
        clear.setOnAction(actionEvent -> grid.clear(stage));
        scene.setOnMouseClicked(MouseEvent -> grid.setTitle(stage));

        // Add key presses because I'm too lazy to actually press the buttons in the application
        handleKeyReleased(stage, scene);

        // start with the grid randomize and with an alive & dead counter
        grid.randomize(stage);

        // Start the animation loop
        startAnimation(stage);

        // Show the application window
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Starts the animation and updates the counter for alive and dead cells
     *
     * @param stage the main stage
     */
    private void startAnimation(Stage stage) {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (run) {
                    grid.iterate(stage);
                }
            }
        };
        timer.start();
    }

    /**
     * Key commands because I'm too lazy to press the buttons
     *
     * @param stage the main stage
     * @param scene the main scene
     */
    private void handleKeyReleased(Stage stage, Scene scene) {
        scene.setOnKeyReleased(keyEvent -> {
            switch (keyEvent.getCode().toString()) {
                case "S":
                    if (!run) grid.iterate(stage);
                    break;
                case "R":
                    grid.randomize(stage);
                    break;
                case "P":
                    run = !run;
                    break;
                case "C":
                    grid.clear(stage);
                    break;
            }
        });
    }

    /**
     * main method
     *
     * @param args args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
