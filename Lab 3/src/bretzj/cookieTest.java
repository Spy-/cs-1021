package bretzj;

public class cookieTest {

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
    /** The ratio of the dried milk to the original milk's volume */
    public static final double DEHYDRATION_FACTOR = 0.2;
    /** The multiplier indicating how the cake's volume expands while baking */
    public static final double CAKE_EXPANSION_FACTOR = 1.2;

    public static void main(String[] args) {
        Ingredient butter = BUTTER;
        Ingredient flour = FLOUR;
        Ingredient sugar = SUGAR;

        Mix cookies = new Mix("Cookies");
        // https://joyfoodsunshine.com/the-most-amazing-chocolate-chip-cookies/
        cookies.addIngredient(new Measure(1, butter));
        cookies.addIngredient(new Measure(3, sugar));
        cookies.addIngredient(new Measure(3, flour));

        cookies.printRecipe();
    }
}
