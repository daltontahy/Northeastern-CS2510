import tester.Tester;

/**
 * Defines a cake recipe.
 */
public class CakeRecipe {
  double flour;
  double sugar;
  double eggs;
  double butter;
  double milk;
  
  /**
   * Creates a cake recipe with specified ingredient amounts.
   *
   * @param flour Weight of flour in ounces.
   * @param sugar Weight of sugar in ounces.
   * @param eggs Weight of eggs in ounces.
   * @param butter Weight of butter in ounces.
   * @param milk Weight of milk in ounces.
   */
  public CakeRecipe(double flour, double sugar, double eggs, double butter, double milk) {
    if (flour == sugar && eggs == butter && (eggs + milk == sugar || eggs + milk == flour)) {
      this.flour = flour;
      this.sugar = sugar;
      this.eggs = eggs;
      this.butter = butter;
      this.milk = milk;
    } else {
      throw new IllegalArgumentException("Invalid cake recipe. Ratios do not match.");
    }
  }
  
  /**
   * Creates a cake recipe with specified amounts of flour, eggs, and milk, setting 
   * sugar and butter to zero.
   *
   * @param flour Weight of flour in ounces.
   * @param eggs Weight of eggs in ounces.
   * @param milk Weight of milk in ounces.
   */
  public CakeRecipe(double flour, double eggs, double milk) {
    this(flour, flour, eggs, eggs, milk); // Reuse the main constructor
  }
  
  /**
   * Creates a cake recipe using volume measurements for flour, eggs, and milk, 
   * converting them to weights.
   *
   * @param flourVolume Volume of flour in cups.
   * @param eggsVolume Number of eggs (each egg considered as one unit of volume).
   * @param milkVolume Volume of milk in cups.
   * @param areVolumes Flag indicating the measurements are in volumes.
   */
  public CakeRecipe(double flourVolume, double eggsVolume, double milkVolume, boolean areVolumes) {
    if (!areVolumes) {
      throw new IllegalArgumentException("Volumes flag not set.");
    }

    double convertedFlour = flourVolume * 4.25;
    double convertedEggs = eggsVolume * 1.75;
    double convertedMilk = milkVolume * 8.0;
    double convertedSugar = convertedFlour; 
    double convertedButter = convertedEggs;

    if ((convertedEggs + convertedMilk != convertedSugar)
        && (convertedEggs + convertedMilk != convertedFlour)) {
      throw new IllegalArgumentException("Invalid cake recipe. Converted volume of "
          + "milk and eggs does not match converted volume of sugar or flour.");
    }


    this.flour = convertedFlour;
    this.sugar = convertedSugar;
    this.eggs = convertedEggs;
    this.butter = convertedButter;
    this.milk = convertedMilk;
  }

  
  /**
   * Checks if this recipe is the same as another recipe, within a
   *  small tolerance for ingredient amounts.
   *
   * @param other Another CakeRecipe object to compare with.
   * @return true if the recipes are considered the same, false otherwise.
   */
  public boolean sameRecipe(CakeRecipe other) {
    double tolerance = 0.001;

    boolean flourCheck = Math.abs(this.flour - other.flour) <= tolerance;
    boolean sugarCheck = Math.abs(this.sugar - other.sugar) <= tolerance;
    boolean eggsCheck = Math.abs(this.eggs - other.eggs) <= tolerance;
    boolean butterCheck = Math.abs(this.butter - other.butter) <= tolerance;
    boolean milkCheck = Math.abs(this.milk - other.milk) <= tolerance;

    return flourCheck && sugarCheck && eggsCheck && butterCheck && milkCheck;
  }
}

class ExamplesCakes {
  CakeRecipe recipe1 = new CakeRecipe(1.5, 1.5, 1.5, 1.5, 1.5);
  CakeRecipe recipe2 = new CakeRecipe(2.0, 2.0, 2.0);
  CakeRecipe recipe3 = new CakeRecipe(1.0, 2.0, 1.0, true);
  CakeRecipe recipe4 = new CakeRecipe(1.0, 1.0, 1.0, 1.0, 1.0);
  CakeRecipe recipe5 = new CakeRecipe(3.0, 3.0, 3.0);
  CakeRecipe recipe6 = new CakeRecipe(3.0, 3.0, 3.0, true);



  boolean testConstructors(Tester t) {
    return
      t.checkConstructorException(IllegalArgumentException, "CakeRecipe", recipe1);
  }
      



  
  boolean testSameRecipe(Tester t) {
    return 
        // testing if perfect construct equals non perfect
        t.checkExpect(recipe1.sameRecipe(recipe2), false, "test failed") 
        
        &&
        
        // testing if perfect construct equals non perfect volume construct
        t.checkExpect(recipe1.sameRecipe(recipe3), false, "test failed")
        
        &&
        // testing if non perfect construct equals non perfect with volume
        t.checkExpect(recipe2.sameRecipe(recipe3), false, "test failed")
        
        &&
        
        // reversing order of previous test 
        t.checkExpect(recipe3.sameRecipe(recipe2), false, "test failed")
        
        &&
        
        // testing same for first construct
        t.checkExpect(recipe1.sameRecipe(recipe1), true, "test failed")
        
        &&
        
        // testing same for second construct
        t.checkExpect(recipe2.sameRecipe(recipe2), true, "test failed")
        
        &&
        
        // testing same for third construct
        t.checkExpect(recipe3.sameRecipe(recipe3), true, "test failed")
        
        &&
        
        // testing same construct, unequal values
        t.checkExpect(recipe1.sameRecipe(recipe4), true, "test failed")
        
        
        &&
        
        // testing same construct, unequal values
        t.checkExpect(recipe2.sameRecipe(recipe5), true, "test failed")
        
        &&
        
        // testing same construct, unequal values
        t.checkExpect(recipe3.sameRecipe(recipe6), true, "test failed");
        
  }
}
