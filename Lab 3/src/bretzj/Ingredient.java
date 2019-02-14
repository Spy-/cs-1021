/*
 * Course: CS1021
 * Winter 2018-2019
 * Lab 3
 * Name: John Bretz
 * Created: 6 Dec 2018
 */
package bretzj;

/**
 * \Interface for an Ingredient
 */
public interface Ingredient {
    /**
     * returns number of calories
     * @return calories
     */
    double getCalories();

    /**
     * returns the number of cups
     * @return cups
     */
    double getCups();

    /**
     * returns the name of the ingredient
     * @return name
     */
    String getName();

    /**
     * returns True if the ingredient is dry
     * @return dry
     */
    boolean isDry();

    /**
     * prints the ingredients recipe
     */
    void printRecipe();
}
