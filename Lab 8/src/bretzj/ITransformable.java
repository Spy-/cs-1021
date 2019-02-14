/*
 * CS1021
 * Winter 2018-2019
 * Lab 9: Final Project
 * Name: John Bretz
 * Created 2/7/19
 */
package bretzj;

import javafx.scene.paint.Color;

/**
 * The transformable interface
 */
@FunctionalInterface
public interface ITransformable {
    /**
     * how to change the color
     * @param y the row number
     * @param color the pixel color
     * @return the new color
     */
    Color apply(int y, Color color);
}
