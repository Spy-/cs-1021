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
 * The class for a LabeledTriangle object
 */
public class LabeledTriangle extends Triangle {

    private final String name;

    /**
     * Constuctor for a LabeledTriangle
     * @param x the lower left coordinate
     * @param y the lower left coordinate
     * @param base the length of the base of the triangle
     * @param height the height of the triangle
     * @param color the color of the triangle
     * @param name the text that should be placed in the center of the triangle
     */
    public LabeledTriangle(double x, double y, double base, double height, Color color, String name) {
        super(x, y, base, height, color);
        this.name = name;
    }

    public void draw(WinPlotterFX plotter) {
        super.draw(plotter);
        plotter.printAt(this.x + base/3.0, this.y + height/2.0, this.name);
    }
}
