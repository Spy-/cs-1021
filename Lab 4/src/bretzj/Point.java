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
 * Constructor for a point object
 */
public class Point extends Shape {
    /**
     * Constructor for a point object
     * @param x the coordinate
     * @param y the coordinate
     * @param color the color of the point
     */
    public Point(double x, double y, Color color) {
        super(x, y, color);
    }

    /**
     * Draws a point onto the canvas
     * @param plotter the WinPlotterFX object
     */
    public void draw(WinPlotterFX plotter) {
        super.setPenColor(plotter);
        plotter.drawPoint(this.x, this.y);
    }
}
