interface IHousing {
}

class Hut implements IHousing {
  int capacity;
  int population;
  
  Hut(int capacity, int population) {
    if (capacity < population) {
      throw new IllegalArgumentException("Hut population must be less than capacity");
    }
    this.population = population;
    this.capacity = capacity;
  }
}

class Inn implements IHousing {
  String name;
  int stalls;
  int capacity;
  int population;
  
  Inn(String name, int capacity, int population, int stalls) {
    if (capacity < population) {
      throw new IllegalArgumentException("Inn population must be less than capacity");
    }
    this.capacity = capacity;
    this.population = population;
    this.name = name;
    this.stalls = stalls;
  }
  
  public int getStalls() {
    return this.stalls;
  }
}

class Castle implements IHousing {
  String name;
  String familyName;
  int population;
  int carriageHouse; 
  /* In this program, I am defining carriageHouse to be the 
   * number of carriages that can be held UNTIL the carriage house is full.
   * With all examples to be created, it is assumed that there are no carriages housed. */
  
  Castle(String name, String familyName, int population, int carriageHouse) {
    this.name = name;
    this.familyName = familyName;
    this.population = population;
    this.carriageHouse = carriageHouse;
  }
  
  public int getCarriages() {
    return this.carriageHouse;
  }
}

interface ITransportation {
  boolean canTravel();
}

class Horse implements ITransportation {
  String name; 
  String color;
  IHousing to;
  IHousing from;
  
  public boolean canTravel() {
    if (to instanceof Inn) {
      Inn inn = (Inn) to;  
      int availableStalls = inn.getStalls();
      return availableStalls > 0;
    }
    return true;
  }
  
  Horse(IHousing to, IHousing from, String name, String color) {
    this.to = to;
    this.from = from;
    this.name = name;
    this.color = color;
    
    if (!canTravel()) {
      travelRestriction();
    }
  }
  
  private void travelRestriction() {
    throw new IllegalArgumentException("Horse cannot travel to an Inn with no stalls!");
  }
}

class Carriage implements ITransportation {
  int tonnage;
  IHousing to;
  IHousing from;
  
  public boolean canTravel() {
    if (to instanceof Castle && from instanceof Inn) {
      Castle castle = (Castle) to;
      return castle.getCarriages() > 0; 
    }
    else 
      if (to instanceof Inn && from instanceof Castle) {
        return true;
      }
    return false;
  }
  
  Carriage(IHousing to, IHousing from, int tonnage) {
    this.to = to;
    this.from = from;
    this.tonnage = tonnage;
    
    if (!canTravel()) {
      travelRestriction();
    }
  }
  
  private void travelRestriction() {
    throw new IllegalArgumentException("Carriage can only travel from an Inn and to a Castle, "
        + "or vise versa!");
  }
}

class ExamplesTravel {
  IHousing hovel = new Hut(5, 1);
  IHousing winterfell = new Castle("Winterfell", "Stark", 500, 6);
  IHousing crossroads = new Inn("Inn At The Crossroads", 40, 20, 12);
  IHousing single = new Hut(1, 1);
  IHousing grandBudapest = new Castle("Grand Budapest", "Moustafa", 1000, 50);
  IHousing singer = new Inn("Singer Inn", 150, 20, 20);
  
  Horse horse1 = new Horse(this.winterfell, this.hovel, "Tucker", "White");
  Horse horse2 = new Horse(this.hovel, this.crossroads, "Night", "Black");
  Carriage carriage1 = new Carriage(this.crossroads, this.grandBudapest, 1);
  Carriage carriage2 = new Carriage(this.grandBudapest, this.singer, 2);
}
