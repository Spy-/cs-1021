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
 * Class for a Rectangle object
 */
public class Rectangle extends Shape {

    protected final double height;
    protected final double width;

    /**
     * Constructor for a Rectangle object
     * @param x the lower left coordinate
     * @param y the lower left coordinate
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @param color the color of the rectangle
     */
    public Rectangle(double x, double y, double width, double height, Color color) {
        super(x,y,color);
        this.width = width;
        this.height = height;
    }

    /**
     * Draws the Rectangle onto the canvas
     * @param plotter the WinPlotterFX
     */
    public void draw(WinPlotterFX plotter) {
        super.setPenColor(plotter);
        plotter.moveTo(this.x, this.y);
        plotter.drawTo(this.x + width, this.y);
        plotter.drawTo(this.x + width, this.y + height);
        plotter.drawTo(this.x, this.y + height);
        plotter.drawTo(this.x, this.y);
    }
}
