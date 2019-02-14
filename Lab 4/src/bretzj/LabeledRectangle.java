/*
 * Course: CS1021
 * Winter 2018
 * Lab 4 - A Christmas Wish...
 * Name: John Bretz
 * Created: 12/14/2018
 */
package bretzj;

import edu.msoe.winplotterfx.WinPlotterFX;
import javafx.scene.paint.Color;

/**
 * The class for a LabeledRectangle
 */
public class LabeledRectangle extends Rectangle {

    private final String name;

    /**
     * Constructor for a LabeledRectangle object
     * @param x the lower left coordinate
     * @param y the lower left coordinate
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @param color the color of the rectangle
     * @param name the text that will be placed in the middle of the rectangle
     */
    public LabeledRectangle(double x, double y, double width, double height, Color color, String name) {
        super(x, y, width, height, color);
        this.name = name;
    }

    public void draw(WinPlotterFX plotter) {
        super.draw(plotter);
        plotter.printAt(this.x + width/3.0, this.y + height/2.0, this.name);
    }
}
