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
 * Master class that is the basis for drawing shapes onto the canvas
 */
public abstract class Shape {

    double x;
    double y;
    private Color color;

    /**
     * Constructor for a Shape object
     * @param x the coordinate
     * @param y the coordinate
     * @param color the color
     */
    public Shape(double x, double y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    /**
     * Default constructor for a shape object
     */
    public Shape() {
        this.x = 0;
        this.y = 0;
        this.color = new Color(1,1,1,1);
    }

    /**
     * abstract draw method
     * @param plotter a WinPlotterFX
     */
    public abstract void draw(WinPlotterFX plotter);

    /**
     * Sets the pen color to the current color of the object
     * @param plotter the WinPlotterFX
     */
    public void setPenColor(WinPlotterFX plotter) {
        double r = this.color.getRed();
        double g = this.color.getGreen();
        double b = this.color.getBlue();
        plotter.setPenColor(r, g, b);
    }

    /**
     * Sets the color to a new color
     * @param color the new color
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
