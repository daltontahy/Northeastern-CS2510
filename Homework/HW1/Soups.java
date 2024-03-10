interface ISoup {
}

class Broth implements ISoup {
  String type;
  
  Broth(String type) {
    this.type = type;
  }
}


class Ingredient implements ISoup {
  ISoup more;
  String name;
  
  Ingredient(ISoup more, String name) {
    this.more = more;
    this.name = name;
  }
}

class ExamplesSoup {
  ISoup yummy;
  ISoup noThankYou;
  
  ExamplesSoup() {
    // Chicken soup with carrots, celery, and noodles
    ISoup chicken = new Broth("chicken");
    ISoup carrots = new Ingredient(chicken, "carrots");
    ISoup celery = new Ingredient(carrots, "celery");
    ISoup noodles = new Ingredient(celery, "noodles");
    yummy = noodles;
      
    // Vanilla soup with horseradish, hot dogs, and plum sauce
    ISoup vanilla = new Broth("vanilla");
    ISoup horseradish = new Ingredient(vanilla, "horseradish");
    ISoup hotDogs = new Ingredient(horseradish, "hot dogs");
    ISoup plumSauce = new Ingredient(hotDogs, "plum sauce");
    noThankYou = plumSauce;
  }
}
