package bretzj;

public class cakeTest {

    private static final Ingredient BUTTER =
            new SimpleIngredient("Butter", 810, 0.5, false);
    private static final Ingredient CREAM =
            new SimpleIngredient("Cream", 104, 1/8., false);
    private static final Ingredient MILK =
            new SimpleIngredient("Milk", 103, 1, false);
    private static final Ingredient SUGAR =
            new SimpleIngredient("Sugar", 773, 1, true);
    private static final Ingredient FLOUR =
            new SimpleIngredient("Flour", 455, 1, true);
    /** The multiplier indicating how the cake's volume expands while baking */
    public static final double CAKE_EXPANSION_FACTOR = 1.2;

    public static void main(String[] args) {
        Ingredient butter = BUTTER;
        Ingredient flour = FLOUR;
        Ingredient sugar = SUGAR;
        Ingredient milk = MILK;

        // https://www.allrecipes.com/recipe/17481/simple-white-cake/
        Mix batter = new Mix("Batter");
        batter.addIngredient(new Measure(1, sugar));
        batter.addIngredient(new Measure(1, 2, butter));
        batter.addIngredient(new Measure(3, 2, flour));
        batter.addIngredient(new Measure(1, 2, milk));

        Mix frosting = new Mix("Frosting");
        frosting.addIngredient(new Measure(4, sugar));
        frosting.addIngredient(BUTTER); // two sticks of butter...
        frosting.addIngredient(BUTTER);

        Mix cake = new Mix("Cake");
        cake.addIngredient(new BakedIngredient(batter, CAKE_EXPANSION_FACTOR));
        cake.addIngredient(new Measure(3, 2, frosting));

        cake.printRecipe();
    }
}
