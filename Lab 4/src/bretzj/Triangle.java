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
 * Class for a triangle object
 */
public class Triangle extends Shape {

    protected final double base;
    protected final double height;

    /**
     * Constructor for a Triangle object
     * @param x the lower left coordinate
     * @param y the lower left coordinate
     * @param base the width of the triangle
     * @param height the height of the triangle
     * @param color the color of the triangle
     */
    public Triangle(double x, double y, double base, double height, Color color) {
        super(x,y,color);
        this.base = base;
        this.height = height;
    }

    /**
     * Draws the triangle onto the canvas
     * @param plotter a WinPlotterFX
     */
    public void draw(WinPlotterFX plotter) {
        super.setPenColor(plotter);
        plotter.moveTo(this.x, this.y);
        plotter.drawTo(this.x + base, this.y);
        plotter.drawTo(this.x + base/2.0, this.y + height);
        plotter.drawTo(this.x, this.y);
    }
}
