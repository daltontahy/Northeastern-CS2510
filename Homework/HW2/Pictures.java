import tester.Tester;

interface IPicture {
  
  int getWidth();
  
  int countShapes();
  
  int comboDepth();
  
  IPicture mirror();
  
  String pictureRecipe(int depth);
}

interface IOperation extends IPicture {
}


class Shape implements IPicture {
  String kind;
  int size;
  
  Shape(String kind, int size) {
    this.kind = kind;
    this.size = size;
  }
  
  /* TEMPLATE:
  Fields:
  ... this.kind ...    -- String
  ... this.size ...    -- double
  Methods for fields:
  ... this.kind.xxx() ...   -- methods from the Shape class
  */

  
  // all following constructors for  "Shape" class are stubs and have no logic implemented.
  public int getWidth() {
    return size;
  }
  
  public int countShapes() {
    return 1;
  }
  
  public int comboDepth() {
    return 0;
  }
  
  public IPicture mirror() {
    return this;
  }
  
  public String pictureRecipe(int depth) {
    return this.kind;
  }
}

class Combo implements IPicture {
  String name;
  IOperation operation;
  
  Combo(String name, IOperation operation) {
    this.name = name;
    this.operation = operation;
  }
  
  /* TEMPLATE:
  Fields:
  ... this.name ...    -- String
  ... this.operation ...    -- String
  Methods for fields:
  ... this.name.xxx() ...   -- methods from the Combo class
  ... this.operation.xxx() ...   -- methods from the Combo class
  */

  
  public int getWidth() {
    return this.operation.getWidth();  
  }
  
  public int countShapes() {
    return this.operation.countShapes();
  }
  
  public int comboDepth() {
    return this.operation.comboDepth();
  }
  
  public IPicture mirror() {
    IOperation mirroredOperation = (IOperation)this.operation.mirror();
    return new Combo(this.name, mirroredOperation);
  }


  
  public String pictureRecipe(int depth) {
    if (depth <= 0) {
      return this.name;
    } else {
      return this.operation.pictureRecipe(depth);
    }
  }
}

class Scale implements IOperation {
  IPicture picture;

  Scale(IPicture pic) {
    this.picture = pic;
  }

  public int getWidth() {
    return picture.getWidth() * 2;
  }

  public int countShapes() {
    return picture.countShapes();
  }

  public int comboDepth() {
    return 1 + picture.comboDepth();
  }

  public IPicture mirror() {
    return new Scale(picture.mirror());
  }

  public String pictureRecipe(int depth) {
    if (depth <= 0) {
      return "Scale";
    } else {
      return "scale(" + this.picture.pictureRecipe(depth - 1) + ")";
    }
  }
}

class Beside implements IOperation {
  IPicture picture1;
  IPicture picture2;

  Beside(IPicture picture1, IPicture picture2) {
    this.picture1 = picture1;
    this.picture2 = picture2;
  }

  public int getWidth() {
    return picture1.getWidth() + picture2.getWidth();
  }

  public int countShapes() {
    return picture1.countShapes() + picture2.countShapes();
  }

  public int comboDepth() {
    return 1 + Math.max(picture1.comboDepth(), picture2.comboDepth());
  }

  public IPicture mirror() {
    return new Beside(picture2.mirror(), picture1.mirror());
  }

  public String pictureRecipe(int depth) {
    if (depth <= 0) {
      return "Beside";
    } else {
      return "beside(" + this.picture1.pictureRecipe(depth - 1) + 
          ", " + this.picture2.pictureRecipe(depth - 1) + ")";
    }
  }

}

class Overlay implements IOperation {
  IPicture topPicture;
  IPicture bottomPicture;

  Overlay(IPicture topPicture, IPicture bottomPicture) {
    this.topPicture = topPicture;
    this.bottomPicture = bottomPicture;
  }

  public int getWidth() {
    return Math.max(topPicture.getWidth(), bottomPicture.getWidth());
  }

  public int countShapes() {
    return topPicture.countShapes() + bottomPicture.countShapes();
  }

  public int comboDepth() {
    return 1 + Math.max(topPicture.comboDepth(), bottomPicture.comboDepth());
  }

  public IPicture mirror() {
    return new Overlay(topPicture.mirror(), bottomPicture.mirror());
  }

  public String pictureRecipe(int depth) {
    if (depth <= 0) {
      return "overlay";
    } else {
      return "overlay(" + this.topPicture.pictureRecipe(depth - 1) + ", " 
               + this.bottomPicture.pictureRecipe(depth - 1) + ")";
    }
  }


}

class ExamplesPicture {
  //shape examples:
  Shape circle = new Shape("circle", 20);
  Shape square = new Shape("square", 30);
  
  // combo examples:
  Combo bigCircle = new Combo("big circle", new Scale(circle));
  Combo squareOnCircle = new Combo("square on circle", new Overlay(square, bigCircle));
  Combo doubledSquareOnCircle = new Combo("doubled square on circle", 
      new Beside(squareOnCircle, squareOnCircle));
  Combo biggerSquare = new Combo("Bigger Square", new Scale(square));
  Combo circleBesideSquare = new Combo("Circle Beside Square", new Beside(circle, square));
  Combo circleOnSquare = new Combo("Circle on Square", new Overlay(circle, square));
  
  // scale, overlay, beside examples:
  Scale scaledCircle = new Scale(circle);
  Beside circleAndSquare = new Beside(circle, square);
  Overlay circleOverSquare = new Overlay(circle, square);
  
  // START TESTS FOR SHAPE CLASS:
  // tester for Shape.getWidth();
  boolean testGetWidth(Tester t) {
    return t.checkExpect(this.circle.getWidth(), 20.0, "Test failed: getWidth() for circle with"
        + " size 20")
      && t.checkExpect(this.square.getWidth(), 20.0, "Test failed: getWidth() for square with "
          + "size 20");
  }
  
  // tester for Shape.countShapes();
  boolean testCountShapes(Tester t) {
    return t.checkExpect(this.circle.countShapes(), 1, "Test failed: countShapes() for a single "
        + "circle should return 1")
      && t.checkExpect(this.square.countShapes(), 1, "Test failed: countShapes() for a single "
          + "square should return 1");
  }

  // tester for Shape.comboDepth();
  boolean testComboDepth(Tester t) {
    return t.checkExpect(this.circle.comboDepth(), 0, "Test failed: comboDepth() for a single"
        + " circle should return 0")
      && t.checkExpect(this.square.comboDepth(), 0, "Test failed: comboDepth() for a single square "
          + "should return 0");
  }

  // tester for Shape.mirror();
  boolean testMirror(Tester t) {
    return t.checkExpect(this.circle.mirror(), this.circle, "Test failed: mirror() for a circle "
        + "should return itself")
      && t.checkExpect(this.square.mirror(), this.square, "Test failed: mirror() for a square "
          + "should"
          + " return itself");
  }

  // tester for Shape.pictureRecipe(int depth);
  boolean testPictureRecipe(Tester t) {
    return t.checkExpect(this.circle.pictureRecipe(0), "Circle", "Test failed: pictureRecipe() at "
        + "depth 0 for Circle")
      && t.checkExpect(this.square.pictureRecipe(1), "Square", "Test failed: pictureRecipe() at "
          + "depth 1 for Square")
      && t.checkExpect(this.circle.pictureRecipe(5), "Circle", "Test failed: pictureRecipe() at "
          + "depth 5 for Circle");
  }
  // END TESTING FOR SHAPE CLASS
  
  // BEGIN TESTSING FOR COMBO CLASS:
  // tester for Combo.getWidth();
  boolean testGetWidthCombo(Tester t) {
    return t.checkExpect(this.bigCircle.getWidth(), 40.0, "Test failed: getWidth() for bigCircle")
      && t.checkExpect(this.squareOnCircle.getWidth(), 30.0, "Test failed: getWidth() for "
          + "squareOnCircle")
      && t.checkExpect(this.doubledSquareOnCircle.getWidth(), 60.0, "Test failed: getWidth() "
          + "for doubledSquareOnCircle");
  }

  // tester for Combo.countShapes
  boolean testCountShapesCombo(Tester t) {
    return t.checkExpect(this.bigCircle.countShapes(), 1, "Test failed: countShapes() for "
        + "bigCircle")
      && t.checkExpect(this.squareOnCircle.countShapes(), 2, "Test failed: countShapes() for "
          + "squareOnCircle")
      && t.checkExpect(this.doubledSquareOnCircle.countShapes(), 4, "Test failed: countShapes() "
          + "for doubledSquareOnCircle")
      && t.checkExpect(this.biggerSquare.countShapes(), 1, "Test failed: countShapes() for "
          + "bigCircle")
      && t.checkExpect(this.circleBesideSquare.countShapes(), 2, "Test failed: countShapes() "
          + "for bigCircle")
      && t.checkExpect(this.circleOnSquare.countShapes(), 2, "Test failed: countShapes() for "
          + "bigCircle");
  }

  // tester for Combo.comboDepth(); 
  boolean testComboDepthCombo(Tester t) {
    return t.checkExpect(this.bigCircle.comboDepth(), 1, "Test failed: comboDepth() for "
        + "bigCircle")
      && t.checkExpect(this.squareOnCircle.comboDepth(), 2, "Test failed: comboDepth() for "
          + "squareOnCircle")
      && t.checkExpect(this.doubledSquareOnCircle.comboDepth(), 3, "Test failed: comboDepth() "
          + "for doubledSquareOnCircle")
      && t.checkExpect(this.biggerSquare.comboDepth(), 1, "Test failed: comboDepth() for "
          + "biggerSquare")
      && t.checkExpect(this.circleBesideSquare.comboDepth(), 1, "Test failed: comboDepth() for "
          + "circleBesideSquare")
      && t.checkExpect(this.circleOnSquare.comboDepth(), 1, "Test failed: comboDepth() for "
          + "circleOnSquare");
  }
  
  // tester for Combo.mirror();
  boolean testMirrorCombo(Tester t) {
    Combo nestedBesideCombo = new Combo("Nested Beside Combo",
        new Beside(new Beside(circle, square), new Beside(square, circle)));
    Combo mirroredNestedBesideCombo = new Combo("Mirrored Nested Beside Combo",
        new Beside(new Beside(square, circle), new Beside(circle, square)));

    // example for doubledSquareOnCircle - assuming we swap the order of squareOnCircle
    Combo mirroredDoubledSquareOnCircle = new Combo("Mirrored Doubled Square On Circle", 
        new Beside(this.squareOnCircle, this.squareOnCircle));
    
    return t.checkExpect(this.doubledSquareOnCircle.mirror(), mirroredDoubledSquareOnCircle, 
        "Test failed: mirror() for doubledSquareOnCircle")
      &&  t.checkExpect(nestedBesideCombo.mirror(), mirroredNestedBesideCombo,
          "Test failed: mirror() for Combo with nested Beside operations")
      && t.checkExpect(this.circleBesideSquare.mirror(), new Combo("Mirrored Circle Beside "
          + "Square", 
          new Beside(this.square, this.circle)), "Test failed: mirror() for circleBesideSquare")
      && t.checkExpect(this.circleOnSquare.mirror(), this.circleOnSquare, "Test failed: "
          + "mirror() "
          + "for circleOnSquare (Overlay operation does not change)");
  }

  //tester for Combo.pictureRecipe(int depth);
  boolean testPictureRecipeCombo(Tester t) {
    return t.checkExpect(this.bigCircle.pictureRecipe(0), "Big Circle", "Test failed:"
        + " pictureRecipe()"
        + " at depth 0 for bigCircle")
      && t.checkExpect(this.squareOnCircle.pictureRecipe(1), "Overlay of Square and Big Circle", 
          "Test failed: pictureRecipe() at depth 1 for squareOnCircle")
      && t.checkExpect(this.doubledSquareOnCircle.pictureRecipe(2), "Beside of two Square on "
          + "Circle", 
          "Test failed: pictureRecipe() at depth 2 for doubledSquareOnCircle")
      // adding additional tests for other depths and combo types
      && t.checkExpect(this.biggerSquare.pictureRecipe(0), "Bigger Square", "Test failed: "
          + "pictureRecipe() at depth 0 for biggerSquare")
      && t.checkExpect(this.circleBesideSquare.pictureRecipe(1), "Beside of Circle and Square", 
          "Test failed: pictureRecipe() at depth 1 for circleBesideSquare")
      && t.checkExpect(this.circleOnSquare.pictureRecipe(2), "Overlay of Circle on Square", 
          "Test failed: pictureRecipe() at depth 2 for circleOnSquare");
  }
  // END TESTS FOR COMBO CLASS
  
  // BEGIN TESTS FOR SCALE CLASS
  // tester for Scale.getWidth();
  boolean testGetWidthScale(Tester t) {
    return t.checkExpect(this.scaledCircle.getWidth(), 40.0, "Test failed: getWidth() for "
        + "scaledCircle (double size of circle)")
        && t.checkExpect(new Scale(new Shape("Rectangle", 15)).getWidth(), 30.0, "Test failed: "
            + "getWidth() for a scaled rectangle of size 15");
  }

  // tester for Scale.countShapes();
  boolean testCountShapesScale(Tester t) {
    return t.checkExpect(this.scaledCircle.countShapes(), 1, "Test failed: countShapes() for "
        + "scaledCircle (only one shape scaled)")
        && t.checkExpect(new Scale(new Shape("Rectangle", 15)).countShapes(), 1, "Test failed: "
            + "countShapes() for a scaled rectangle (only one shape scaled)");
  }
  
  // tester for Scale.comboDepth();
  boolean testComboDepthScale(Tester t) {
    return t.checkExpect(this.scaledCircle.comboDepth(), 1, "Test failed: comboDepth() for "
        + "scaledCircle (one level of scaling)")
        && t.checkExpect(new Scale(new Scale(new Shape("Rectangle", 15))).comboDepth(), 2, 
            "Test "
            + "failed: comboDepth() for double-scaled rectangle (two levels of scaling)");
  }

  // tester for Scale.mirror();
  boolean testMirrorScale(Tester t) {
    return t.checkExpect(this.scaledCircle.mirror(), this.scaledCircle, "Test failed: mirror() "
        + "for "
        + "scaledCircle (scaling does not change orientation)")
        && t.checkExpect(new Scale(new Shape("Rectangle", 15)).mirror(), 
            new Scale(new Shape("Rectangle", 15)), "Test failed: mirror() for a scaled "
                + "rectangle "
                + "(scaling does not change orientation)");
  }
  
  // tester for Scale.pictureDepth(int depth);
  boolean testPictureRecipeScale(Tester t) {
    return t.checkExpect(this.scaledCircle.pictureRecipe(0), "Scaled Circle", "Test failed: "
        + "pictureRecipe() at depth 0 for scaledCircle")
        && t.checkExpect(this.scaledCircle.pictureRecipe(1), "Scaled Circle", "Test failed: "
            + "pictureRecipe() at depth 1 for scaledCircle")
        && t.checkExpect(new Scale(new Shape("Rectangle", 15)).pictureRecipe(0), "Scaled "
            + "Rectangle", 
            "Test failed: pictureRecipe() at depth 0 for a scaled rectangle")
        && t.checkExpect(new Scale(new Shape("Rectangle", 15)).pictureRecipe(1), "Scaled "
            + "Rectangle", 
            "Test failed: pictureRecipe() at depth 1 for a scaled rectangle");
  }
  // END TESTS FOR SCALE CLASS
  
  // BEGIN TESTS FOR BESIDE CLASS
  // tester for Beside.getWidth();
  boolean testGetWidthBeside(Tester t) {
    return t.checkExpect(this.circleAndSquare.getWidth(), 40.0, "Test failed: getWidth() for "
        + "circleAndSquare (sum of circle and square widths)")
        && t.checkExpect(new Beside(new Shape("Circle", 15), new Shape("Square", 10)).getWidth(), 
            25.0, "Test failed: getWidth() for a beside of circle (15) and square (10)");
  }

  // tester for Beside.countShapes();
  boolean testCountShapesBeside(Tester t) {
    return t.checkExpect(this.circleAndSquare.countShapes(), 2, "Test failed: countShapes() "
        + "for "
        + "circleAndSquare (1 circle + 1 square)")
        && t.checkExpect(new Beside(new Shape("Circle", 15), 
            new Shape("Square", 10)).countShapes(), 
            2, "Test failed: countShapes() for a beside of circle and square");
  }
  
  // tester for Beside.comboDepth();
  boolean testComboDepthBeside(Tester t) {
    return t.checkExpect(this.circleAndSquare.comboDepth(), 1, "Test failed: comboDepth() for "
        + "circleAndSquare (one level of beside)")
        && t.checkExpect(new Beside(new Scale(new Shape("Circle", 15)), 
            new Shape("Square", 10)).comboDepth(), 2, "Test failed: comboDepth() for beside of "
                + "scaled circle and square");
  }

  // tester for Beside.mirror();
  boolean testMirrorBeside(Tester t) {
    Beside mirroredCircleAndSquare = new Beside(this.square, this.circle);
    return t.checkExpect(this.circleAndSquare.mirror(), mirroredCircleAndSquare, "Test failed:"
        + " mirror() for circleAndSquare (swap circle and square)")
        && t.checkExpect(new Beside(new Shape("Circle", 15), new Shape("Square", 10)).mirror(), 
            new Beside(new Shape("Square", 10), new Shape("Circle", 15)), "Test failed:"
                + " mirror() "
                + "for a beside of circle and square (swap them)");
  }
  
  // tester for Beside.pictureDepth(int depth);
  boolean testPictureRecipeBeside(Tester t) {
    return t.checkExpect(this.circleAndSquare.pictureRecipe(0), "Circle Beside Square", 
        "Test failed: pictureRecipe() at depth 0 for circleAndSquare")
        && t.checkExpect(this.circleAndSquare.pictureRecipe(1), "Circle Beside Square", 
            "Test failed: pictureRecipe() at depth 1 for circleAndSquare")
        && t.checkExpect(new Beside(new Shape("Circle", 15), 
            new Shape("Square", 10)).pictureRecipe(0), "Circle Beside Square", "Test failed: "
                + "pictureRecipe() at depth 0 for a beside of circle and square");
  }
  // END TESTS FOR BESIDE CLASS
  
  // BEGIN TESTS FOR OVERLAY CLASS
  // examples
  Shape largeRectangle = new Shape("Rectangle", 30);
  Shape smallTriangle = new Shape("Triangle", 10);
  Overlay rectangleOverTriangle = new Overlay(largeRectangle, smallTriangle);
  Overlay nestedOverlay = new Overlay(new Overlay(circle, square), largeRectangle);

  // tester getWidth();
  boolean testGetWidthOverlay(Tester t) {
    return t.checkExpect(circleOverSquare.getWidth(), 20.0, "Test failed: getWidth() for "
          + "circleOverSquare (width of the larger shape, circle)")
          && t.checkExpect(rectangleOverTriangle.getWidth(), 30.0, "Test failed: getWidth() "
              + "for "
              + "rectangleOverTriangle (width of the larger shape, rectangle)")
          && t.checkExpect(nestedOverlay.getWidth(), 30.0, "Test failed: getWidth() for "
              + "nestedOverlay (width of the largest shape, rectangle)");
  }

  // tester countShapes();
  boolean testCountShapesOverlay(Tester t) {
    return t.checkExpect(circleOverSquare.countShapes(), 2, "Test failed: countShapes() for "
          + "circleOverSquare (1 circle + 1 square)")
          && t.checkExpect(rectangleOverTriangle.countShapes(), 2, "Test failed: countShapes() "
              + "for rectangleOverTriangle (1 rectangle + 1 triangle)")
          && t.checkExpect(nestedOverlay.countShapes(), 3, "Test failed: countShapes() for "
              + "nestedOverlay (1 circle + 1 square + 1 rectangle)");
  }

  // tester comboDepth();
  boolean testComboDepthOverlay(Tester t) {
    return t.checkExpect(circleOverSquare.comboDepth(), 1, "Test failed: comboDepth() for "
          + "circleOverSquare (one level of overlay)")
          && t.checkExpect(rectangleOverTriangle.comboDepth(), 1, "Test failed: comboDepth() "
              + "for rectangleOverTriangle (one level of overlay)")
          && t.checkExpect(nestedOverlay.comboDepth(), 2, "Test failed: comboDepth() for"
              + " nestedOverlay (two levels of overlay)");
  }

  // tester mirror();
  boolean testMirrorOverlay(Tester t) {
    return t.checkExpect(circleOverSquare.mirror(), circleOverSquare, "Test failed: mirror() "
          + "for circleOverSquare (overlay does not change in mirroring)")
          && t.checkExpect(rectangleOverTriangle.mirror(), rectangleOverTriangle, "Test failed: "
              + "mirror() for rectangleOverTriangle (overlay does not change in mirroring)")
          && t.checkExpect(nestedOverlay.mirror(), nestedOverlay, "Test failed: mirror() for "
              + "nestedOverlay (overlay does not change in mirroring)");
  }

  // tester pictureRecipe(int depth);
  boolean testPictureRecipeOverlay(Tester t) {
    return t.checkExpect(circleOverSquare.pictureRecipe(0), "Circle on Square", "Test failed: "
          + "pictureRecipe() at depth 0 for circleOverSquare")
          && t.checkExpect(rectangleOverTriangle.pictureRecipe(1), "Rectangle on Triangle", 
              "Test "
              + "failed: pictureRecipe() at depth 1 for rectangleOverTriangle")
          && t.checkExpect(nestedOverlay.pictureRecipe(2), "Overlay of Circle on Square on "
              + "Rectangle", "Test failed: pictureRecipe() at depth 2 for nestedOverlay");
  }
  //END TESTS FOR OVERLAY
}
