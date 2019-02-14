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
 * Class that allows a circle to be drawn onto the canvas
 */
public class Circle extends Shape {

    private final double radius;

    /**
     * Constructor for a circle object
     * @param x center posiotion
     * @param y center position
     * @param r radius
     * @param color the color the circle should be
     */
    public Circle(double x, double y, double r, Color color) {
        super(x, y, color);
        this.radius = r;
    }

    /**
     * Draws a circle onto the canvas
     * @param plotter the WinPlotterFX
     */
    public void draw(WinPlotterFX plotter) {
        super.setPenColor(plotter);
        double x, y;
        plotter.moveTo(this.x + radius, this.y);
        for (int i=5; i<360; i+=5) {
            x = this.x + radius * Math.cos(i * Math.PI/180);
            y = this.y + radius * Math.sin(i * Math.PI/180);
            plotter.drawTo(x, y);
        }
        plotter.drawTo(this.x + radius, this.y);
    }
}
