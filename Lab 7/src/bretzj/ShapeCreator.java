/**
 * CS1021
 * Winter 2018-2019
 * Name: John Bretz
 * Created 1/24/19
 */
package bretzj;

import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

/**
 * Class that manages the stage and draws everything to the canvas
 */
public class ShapeCreator extends Stage {
    private double width;
    private double height;
    private Scale scale;
    private Scene scene;
    private Pane pane;
    private Canvas canvas;
    private GraphicsContext gc;

    /**
     * Default constructor
     */
    public ShapeCreator() {
        this(200.0, 200.0);
    }

    /**
     * Constructor
     *
     * @param size the size of the window
     */
    public ShapeCreator(double[] size) {
        this(size[0], size[1]);
    }

    /**
     * Constructor
     *
     * @param width  the width of the window
     * @param height the height of the window
     */
    public ShapeCreator(double width, double height) {
        this.width = width;
        this.height = height;

        this.canvas = new Canvas(width, height);
        this.gc = this.canvas.getGraphicsContext2D();
        this.setupRoot();
        this.setResizable(false);
    }

    /**
     * Set ups the root pane for the stage
     */
    private void setupRoot() {
        this.pane = new Pane();
        this.pane.getChildren().add(this.canvas);
        this.scale = new Scale();
        this.scale.setX(1.0);
        this.scale.setY(-1.0);
        // used from WinPlotterFX
        this.scale.pivotYProperty().bind(Bindings.createDoubleBinding(() -> this.pane.getBoundsInLocal().getMinY() + this.pane.getBoundsInLocal().getHeight() / 2.0D, this.pane.boundsInLocalProperty()));
        this.pane.getTransforms().add(this.scale);
    }

    /**
     * Shows the window
     */
    public void showPlot() {
        this.scene = new Scene(this.pane, this.width, this.height);
        this.setScene(this.scene);
        this.show();
    }

    /**
     * Create a filled rectangle. Used to set the background
     *
     * @param x      the x coordinate
     * @param y      the y coordinate
     * @param width  width of the rectangle
     * @param height height of the rectangle
     * @param color  the fill color
     */
    public void fillRect(double x, double y, double width, double height, Color color) {
        gc.setFill(color);
        gc.fillRect(x, y, width, height);
    }

    /**
     * Creates a point on the canvas
     *
     * @param x     the x coordinate
     * @param y     the y coordinate
     * @param color the color
     */
    public void Point(double x, double y, Color color) {
        gc.setFill(color);
        gc.fillOval(x - 5, y - 5, 10.0, 10.0);
    }

    /**
     * Creates a circle on the canvas
     *
     * @param x      the x coordinate
     * @param y      the y coordinate
     * @param color  the color
     * @param radius the radius
     */
    public void Circle(double x, double y, Color color, double radius) {
        gc.setStroke(color);
        gc.strokeOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    /**
     * Creates an isosceles triangle on the canvas
     *
     * @param x      the x coordinate
     * @param y      the y coordinate
     * @param color  the color
     * @param base   the base of the triangle
     * @param height the height of the triangle
     */
    public void Tri(double x, double y, Color color, double base, double height) {
        gc.setStroke(color);
        double[] xvalues = {x, x + base / 2, x + base};
        double[] yvalues = {y, y + height, y};
        gc.strokePolygon(xvalues, yvalues, 3);
    }

    /**
     * Creates a rectangle on the canvas
     *
     * @param x      the x coordinate
     * @param y      the y coordinate
     * @param color  the color
     * @param width  the width
     * @param height the height
     */
    public void Rect(double x, double y, Color color, double width, double height) {
        gc.setStroke(color);
        gc.strokeRect(x, y, width, height);
    }

    /**
     * Creates a labeled triangle on the canvas
     *
     * @param x      the x coordinate
     * @param y      the y coordinate
     * @param color  the color
     * @param base   the base
     * @param height the height
     * @param text   the label
     */
    public void LabelTri(double x, double y, Color color, double base, double height, String text) {
        Tri(x, y, color, base, height);
        printText(x + base / 2, y + height / 2, color, text);
    }

    /**
     * Creates a labeled rectangle on the canvas
     *
     * @param x      the x coordinate
     * @param y      the y coordinate
     * @param color  the color
     * @param width  the width
     * @param height the height
     * @param text   the label
     */
    public void LabelRect(double x, double y, Color color, double width, double height, String text) {
        Rect(x, y, color, width, height);
        printText(x + width / 2, y + height / 2, color, text);
    }

    /**
     * prints text to the canvas
     *
     * @param x     the x coordinate
     * @param y     the y coordinate
     * @param color the color
     * @param label the label
     */
    private void printText(double x, double y, Color color, String label) {
        Text text = new Text();
        text.setFont(new Font(14));
        text.setFill(color);
        text.setText(label);
        text.setScaleY(-1.0D);
        text.setLayoutX(x);
        text.setLayoutY(y);
        this.pane.getChildren().add(text);
    }
}