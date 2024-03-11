import tester.*;                
// The tester library
import javalib.worldimages.*;   
// images, like RectangleImage or OverlayImages
import javalib.funworld.*;
// the abstract World class and the big-bang library
import java.awt.Color;          
// general colors (as triples of red,green,blue values)
// and predefined colors (Red, Green, Yellow, Blue, Black, White)
import javalib.worldcanvas.WorldCanvas;
import java.util.Random;


//represents a list of words
interface ILoWord {
  
  //produces a new list sorted by alphabetical order 
  // (case-insensitive) (insertion)
  ILoWord sort();
  
  //produces true if the list of IWords is sorted (case-insensitive)
  boolean isSorted();
  
  //takes in a string of length 1 and produces a list of words by removing the 
  // first letter of any *active* words in the list who matches the given string
  ILoWord checkAndReduce(String s);
  
  //takes in an IWord and adds that IWord to the end of the ILoWord
  ILoWord addToEnd(IWord w);
  
  //Takes in an ILoWord and removes all empty strings
  ILoWord filterOutEmpties();
  
  //inserts compareFirst infront of this if it comes first alphabetically, vversa
  ILoWord insert(IWord compareFirst);
  
  //Takes in a worldscene draws all of the ILoWords onto the worldscene
  WorldScene draw(WorldScene ws);
  
  //skips the first word and checks the rest
  ILoWord skipLoWordCheckEmpty();
  
  //checks if the first two are sorted
  boolean checkTwo(IWord comparePrevious);
  
}

//represents an empty list of words
class MtLoWord implements ILoWord {
  
  /* Template:
   * Fields:
   *... this.first ... -- IWord
   *... this.rest ... -- ILoWord
   * Methods:
   * this.sort() -- ILoWord
   * this.isSorted() -- boolean
   * this.checkAndReduce(String S) -- ILoWord
   * this.addToEnd(IWord w) -- ILoWord
   * this.filterOutEmpties() -- ILoWord
   * this.draw(WorldScene ws) -- WorldScene
   * this.insert(IWord compareFirst) -- ILoWord
   * this.skipLoWordCheckEmpty() -- ILoWord
   * this.checkTwo(IWord compareFirst) -- boolean
   * Methods of fields:
   * this.sort() -- ILoWord
   * this.checkAndReduce(String S) -- ILoWord
   * this.addToEnd(IWord w) -- ILoWord
   * this.filterOutEmpties() -- ILoWord
   * this.insert(IWord compareFirst) -- ILoWord
   * this.skipLoWordCheckEmpty() -- ILoWord
   */
  
  //produces a new list sorted by alphabetical order 
  // (case-insensitive) (insertion)
  public ILoWord sort() {
    /*---------------------------------------------------
    // TEMPLATE for this method:
    // EVERYTHING from our class-wide template...
    // PLUS methods on the parameters
       n/a
    ---------------------------------------------------*/
    // TODO Auto-generated method stub
    return this;
  }

  //produces true if the list of IWords is sorted (case-insensitive)
  public boolean isSorted() {
    /*---------------------------------------------------
    // TEMPLATE for this method:
    // EVERYTHING from our class-wide template...
    // PLUS methods on the parameters
       n/a
    ---------------------------------------------------*/
    // TODO Auto-generated method stub
    return true;
  }

  //takes in a string of length 1 and produces a list of words by removing the 
  // first letter of any *active* words in the list who matches the given string
  public ILoWord checkAndReduce(String s) {
    /*---------------------------------------------------
    // TEMPLATE for this method:
    // EVERYTHING from our class-wide template...
    // PLUS methods on the parameters
       n/a
    ---------------------------------------------------*/
    // TODO Auto-generated method stub
    return this;
  }

  //takes in an IWord and adds that IWord to the end of the ILoWord
  public ILoWord addToEnd(IWord w) {
    /*---------------------------------------------------
    // TEMPLATE for this method:
    // EVERYTHING from our class-wide template...
    // PLUS methods on the parameters
    *... this.checkEmpty() ... -- boolean
    *... this.returnReduce(String s) ... -- String
    *... this.reduce(String s) ... -- String
    *... this.compareWords(IWord comparePrevious) ... -- String
    *... this.compareStringsHelper(IWord nextWord, String next) ... -- boolean
    *... this.toImage(WorldScene ws) ... -- WorldScene
    ---------------------------------------------------*/
    // TODO Auto-generated method stub
    return new ConsLoWord(w, this);
  }

  //Takes in an ILoWord and removes all empty strings
  public ILoWord filterOutEmpties() {
    /*---------------------------------------------------
    // TEMPLATE for this method:
    // EVERYTHING from our class-wide template...
    // PLUS methods on the parameters
       n/a
    ---------------------------------------------------*/
    // TODO Auto-generated method stub
    return this;
  }

  //Takes in a worldscene draws all of the ILoWords onto the worldscene
  public WorldScene draw(WorldScene ws) {
    /*---------------------------------------------------
    // TEMPLATE for this method:
    // EVERYTHING from our class-wide template...
    // PLUS methods on the parameters
    *... draw(WorldScene ws) ... -- WorldScene
    *... toImage(WorldScene ws) ... -- WorldScene
    ---------------------------------------------------*/
    // TODO Auto-generated method stub
    return ws;
  }

  //inserts compareFirst infront of this if it comes first alphabetically, vversa
  public ILoWord insert(IWord compareFirst) {
    /*---------------------------------------------------
    // TEMPLATE for this method:
    // EVERYTHING from our class-wide template...
    // PLUS methods on the parameters
    *... this.checkEmpty() ... -- boolean
    *... this.returnReduce(String s) ... -- String
    *... this.reduce(String s) ... -- String
    *... this.compareWords(IWord comparePrevious) ... -- String
    *... this.compareStringsHelper(IWord nextWord, String next) ... -- boolean
    *... this.toImage(WorldScene ws) ... -- WorldScene
    ---------------------------------------------------*/
    // TODO Auto-generated method stub
    return new ConsLoWord(compareFirst, this);
  }

  //skips the first word and checks the rest
  public ILoWord skipLoWordCheckEmpty() {
    // TODO Auto-generated method stub
    return this;
  }
  
  //checks if the first two are sorted
  public boolean checkTwo(IWord comparePrevious) {
    /*---------------------------------------------------
    // TEMPLATE for this method:
    // EVERYTHING from our class-wide template...
    // PLUS methods on the parameters
    *... this.checkEmpty() ... -- boolean
    *... this.returnReduce(String s) ... -- String
    *... this.reduce(String s) ... -- String
    *... this.compareWords(IWord comparePrevious) ... -- String
    *... this.compareStringsHelper(IWord nextWord, String next) ... -- boolean
    *... this.toImage(WorldScene ws) ... -- WorldScene
    ---------------------------------------------------*/
    return true;
  }

  
}

class ConsLoWord implements ILoWord {
  IWord first;
  ILoWord rest;
  
  /* Template:
   * Fields:
   *... this.first ... -- IWord
   *... this.rest ... -- ILoWord
   * Methods:
   * this.sort() -- ILoWord
   * this.isSorted() -- boolean
   * this.checkAndReduce(String S) -- ILoWord
   * this.addToEnd(IWord w) -- ILoWord
   * this.filterOutEmpties() -- ILoWord
   * this.draw(WorldScene ws) -- WorldScene
   * this.insert(IWord compareFirst) -- ILoWord
   * this.skipLoWordCheckEmpty() -- ILoWord
   * this.checkTwo(IWord compareFirst) -- boolean
   * Methods of fields:
   * this.sort() -- ILoWord
   * this.checkAndReduce(String S) -- ILoWord
   * this.addToEnd(IWord w) -- ILoWord
   * this.filterOutEmpties() -- ILoWord
   * this.insert(IWord compareFirst) -- ILoWord
   * this.skipLoWordCheckEmpty() -- ILoWord
   */

  ConsLoWord(IWord first, ILoWord rest) {
    this.first = first;
    this.rest = rest;
  }

  //produces a new list sorted by alphabetical order 
  // (case-insensitive) (insertion)
  public ILoWord sort() {
    /*---------------------------------------------------
    // TEMPLATE for this method:
    // EVERYTHING from our class-wide template...
    // PLUS methods on the parameters
       n/a
    ---------------------------------------------------*/
    return this.rest.sort().insert(this.first);
  }

  //produces true if the list of IWords is sorted (case-insensitive)
  public boolean isSorted() {
    /*---------------------------------------------------
    // TEMPLATE for this method:
    // EVERYTHING from our class-wide template...
    // PLUS methods on the parameters
       n/a
    ---------------------------------------------------*/
    // TODO Auto-generated method stub
    return this.rest.sort().checkTwo(this.first);
  }

  //takes in a string of length 1 and produces a list of words by removing the 
  // first letter of any *active* words in the list who matches the given string
  public ILoWord checkAndReduce(String s) {
    /*---------------------------------------------------
    // TEMPLATE for this method:
    // EVERYTHING from our class-wide template...
    // PLUS methods on the parameters
       n/a
    ---------------------------------------------------*/
    // TODO Auto-generated method stub
    return new ConsLoWord(this.first.returnReduce(s), this.rest.checkAndReduce(s));
  }

  //takes in an IWord and adds that IWord to the end of the ILoWord
  public ILoWord addToEnd(IWord w) {
    /*---------------------------------------------------
    // TEMPLATE for this method:
    // EVERYTHING from our class-wide template...
    // PLUS methods on the parameters
    *... this.checkEmpty() ... -- boolean
    *... this.returnReduce(String s) ... -- String
    *... this.reduce(String s) ... -- String
    *... this.compareWords(IWord comparePrevious) ... -- String
    *... this.compareStringsHelper(IWord nextWord, String next) ... -- boolean
    *... this.toImage(WorldScene ws) ... -- WorldScene
    ---------------------------------------------------*/
    // TODO Auto-generated method stub
    return new ConsLoWord(this.first, this.rest.addToEnd(w));
  }

  //Takes in an ILoWord and removes all empty strings
  public ILoWord filterOutEmpties() {
    /*---------------------------------------------------
    // TEMPLATE for this method:
    // EVERYTHING from our class-wide template...
    // PLUS methods on the parameters
       n/a
    ---------------------------------------------------*/
    // TODO Auto-generated method stub
    if (this.first.checkEmpty()) {
      return this.rest.skipLoWordCheckEmpty();
    } else {
      return new ConsLoWord(this.first, this.rest.filterOutEmpties());
    }
  }

  //Takes in a worldscene draws all of the ILoWords onto the worldscene
  public WorldScene draw(WorldScene ws) {
    /*---------------------------------------------------
    // TEMPLATE for this method:
    // EVERYTHING from our class-wide template...
    // PLUS methods on the parameters
    *... draw(WorldScene ws) ... -- WorldScene
    *... toImage(WorldScene ws) ... -- WorldScene
    ---------------------------------------------------*/
    return this.first.toImage(this.rest.draw(ws));
  }

  //inserts compareFirst infront of this if it comes first alphabetically, vversa
  public ILoWord insert(IWord comparePrevious) {
    /*---------------------------------------------------
    // TEMPLATE for this method:
    // EVERYTHING from our class-wide template...
    // PLUS methods on the parameters
    *... this.checkEmpty() ... -- boolean
    *... this.returnReduce(String s) ... -- String
    *... this.reduce(String s) ... -- String
    *... this.compareWords(IWord comparePrevious) ... -- String
    *... this.compareStringsHelper(IWord nextWord, String next) ... -- boolean
    *... this.toImage(WorldScene ws) ... -- WorldScene
    ---------------------------------------------------*/
    // TODO Auto-generated method stub
    if (this.first.compareWords(comparePrevious)) {
      return new ConsLoWord(comparePrevious, this.rest.insert(this.first));
    } else {
      return new ConsLoWord(this.first, this.rest.insert(comparePrevious));
    } 
  }

  //skips the first word and checks the rest
  public ILoWord skipLoWordCheckEmpty() {
    // TODO Auto-generated method stub
    if (this.first.checkEmpty()) {
      return this.rest.skipLoWordCheckEmpty();
    } else {
      return new ConsLoWord(this.first, this.rest.filterOutEmpties());
    }
  }

  //checks if the first two are sorted
  public boolean checkTwo(IWord comparePrevious) {
    /*---------------------------------------------------
    // TEMPLATE for this method:
    // EVERYTHING from our class-wide template...
    // PLUS methods on the parameters
    *... this.checkEmpty() ... -- boolean
    *... this.returnReduce(String s) ... -- String
    *... this.reduce(String s) ... -- String
    *... this.compareWords(IWord comparePrevious) ... -- String
    *... this.compareStringsHelper(IWord nextWord, String next) ... -- boolean
    *... this.toImage(WorldScene ws) ... -- WorldScene
    ---------------------------------------------------*/
    // TODO Auto-generated method stub
    if (this.first.compareWords(comparePrevious)) {
      return this.rest.isSorted();
    } else {
      return false;
    }
    
  }
 
}








//represents a word in the ZType game
interface IWord {
  
  //check if word is empty String
  boolean checkEmpty();
  
  //get the reduced (if needed) word
  IWord returnReduce(String s);
  
  //reduce the word if needed
  String reduce(String s);
  
  //return true if the previous is in front of the next
  boolean compareWords(IWord comparePrevious);
  
  //return true if the previous is in front of the next
  boolean compareStringsHelper(IWord nextWord, String next);
  
  //returns a world scene with the word added
  WorldScene toImage(WorldScene ws);
 
}

//represents an active word in the ZType game
class ActiveWord implements IWord {
  String word;
  int x;
  int y;
  
  /* Template:
   * Fields:
   *... this.word ... -- String
   *... this.x ... -- int
   *... this.y ... -- int
   * Methods:
   *... this.checkEmpty() ... -- boolean
   *... this.returnReduce(String s) ... -- String
   *... this.reduce(String s) ... -- String
   *... this.compareWords(IWord comparePrevious) ... -- String
   *... this.compareStringsHelper(IWord nextWord, String next) ... -- boolean
   *... this.toImage(WorldScene ws) ... -- WorldScene
   * Methods of fields:
   * n/a
   */
  

  ActiveWord(String word, int x, int y) {
    this.word = word;
    this.x = x;
    this.y = y;
  }

  //check if word is empty String
  public boolean checkEmpty() {
    /*---------------------------------------------------
    // TEMPLATE for this method:
    // EVERYTHING from our class-wide template...
    // PLUS methods on the parameters
       n/a
    ---------------------------------------------------*/
    // TODO Auto-generated method stub
    return this.word.isEmpty();
  }
  
  //get the reduced (if needed) word
  public IWord returnReduce(String s) {
    /*---------------------------------------------------
    // TEMPLATE for this method:
    // EVERYTHING from our class-wide template...
    // PLUS methods on the parameters
       n/a
    ---------------------------------------------------*/
    // TODO Auto-generated method stub
    return new ActiveWord(reduce(s), this.x, this.y);
  }

  //reduce the word if needed
  public String reduce(String s) {
    /*---------------------------------------------------
    // TEMPLATE for this method:
    // EVERYTHING from our class-wide template...
    // PLUS methods on the parameters
       n/a
    ---------------------------------------------------*/
    // TODO Auto-generated method stub
    if (this.word.length() > 1 
        && this.word.toLowerCase().substring(0,1).equals(s.toLowerCase())) {
      if (this.word.length() > 1) {
        return this.word.substring(1);
      } else {
        return this.word;
      }
      
    } else {
      return this.word;
    }
  }
  
  //return true if the previous is in front of the next
  public boolean compareWords(IWord comparePrevious) {
    /*---------------------------------------------------
    // TEMPLATE for this method:
    // EVERYTHING from our class-wide template...
    // PLUS methods on the parameters
    *... this.checkEmpty() ... -- boolean
    *... this.returnReduce(String s) ... -- String
    *... this.reduce(String s) ... -- String
    *... this.compareWords(IWord comparePrevious) ... -- String
    *... this.compareStringsHelper(IWord nextWord, String next) ... -- boolean
    *... this.toImage(WorldScene ws) ... -- WorldScene
    ---------------------------------------------------*/
    // TODO Auto-generated method stub
    return comparePrevious.compareStringsHelper(this, this.word);
  }
  

  //return true if the previous is in front of the next
  public boolean compareStringsHelper(IWord nextWord, String next) {
    /*---------------------------------------------------
    // TEMPLATE for this method:
    // EVERYTHING from our class-wide template...
    // PLUS methods on the parameters
    *... this.checkEmpty() ... -- boolean
    *... this.returnReduce(String s) ... -- String
    *... this.reduce(String s) ... -- String
    *... this.compareWords(IWord comparePrevious) ... -- String
    *... this.compareStringsHelper(IWord nextWord, String next) ... -- boolean
    *... this.toImage(WorldScene ws) ... -- WorldScene
    ---------------------------------------------------*/
    // TODO Auto-generated method stub
    return (this.word.compareToIgnoreCase(next) == 0 
        || this.word.compareToIgnoreCase(next) < 0);
  }
  
  //returns a world scene with the word added
  public WorldScene toImage(WorldScene ws) {
    /*---------------------------------------------------
    // TEMPLATE for this method:
    // EVERYTHING from our class-wide template...
    // PLUS methods on the parameters
    *... draw(WorldScene ws) ... -- WorldScene
    *... toImage(WorldScene ws) ... -- WorldScene
    ---------------------------------------------------*/
    return ws.placeImageXY(
        new TextImage(this.word, Color.CYAN), this.x, this.y);
  }

}

//represents an inactive word in the ZType game
class InactiveWord implements IWord {
  String word;
  int x;
  int y;

  /* Template:
   * Fields:
   *... this.word ... -- String
   *... this.x ... -- int
   *... this.y ... -- int
   * Methods:
   *... this.checkEmpty() ... -- boolean
   *... this.returnReduce(String s) ... -- String
   *... this.reduce(String s) ... -- String
   *... this.compareWords(IWord comparePrevious) ... -- String
   *... this.compareStringsHelper(IWord nextWord, String next) ... -- boolean
   *... this.toImage(WorldScene ws) ... -- WorldScene
   * Methods of fields:
   * n/a
   */

  InactiveWord(String word, int x, int y) {
    this.word = word;
    this.x = x;
    this.y = y;
  }

  //check if word is empty String
  public boolean checkEmpty() {
    /*---------------------------------------------------
    // TEMPLATE for this method:
    // EVERYTHING from our class-wide template...
    // PLUS methods on the parameters
       n/a
    ---------------------------------------------------*/
    // TODO Auto-generated method stub
    return this.word.isEmpty();
  }

  //get the reduced (if needed) word
  public IWord returnReduce(String s) {
    /*---------------------------------------------------
    // TEMPLATE for this method:
    // EVERYTHING from our class-wide template...
    // PLUS methods on the parameters
       n/a
    ---------------------------------------------------*/
    // TODO Auto-generated method stub
    return this;
  }

  //reduce the word if needed
  public String reduce(String s) {
    /*---------------------------------------------------
    // TEMPLATE for this method:
    // EVERYTHING from our class-wide template...
    // PLUS methods on the parameters
       n/a
    ---------------------------------------------------*/
    // TODO Auto-generated method stub
    return this.word;
  }

  //return true if the previous is in front of the next
  public boolean compareWords(IWord comparePrevious) {
    /*---------------------------------------------------
    // TEMPLATE for this method:
    // EVERYTHING from our class-wide template...
    // PLUS methods on the parameters
    *... this.checkEmpty() ... -- boolean
    *... this.returnReduce(String s) ... -- String
    *... this.reduce(String s) ... -- String
    *... this.compareWords(IWord comparePrevious) ... -- String
    *... this.compareStringsHelper(IWord nextWord, String next) ... -- boolean
    *... this.toImage(WorldScene ws) ... -- WorldScene
    ---------------------------------------------------*/
    // TODO Auto-generated method stub
    return comparePrevious.compareStringsHelper(this, this.word);
  }
  

  //return true if the previous is in front of the next
  public boolean compareStringsHelper(IWord nextWord,
      String next) {
    /*---------------------------------------------------
    // TEMPLATE for this method:
    // EVERYTHING from our class-wide template...
    // PLUS methods on the parameters
    *... this.checkEmpty() ... -- boolean
    *... this.returnReduce(String s) ... -- String
    *... this.reduce(String s) ... -- String
    *... this.compareWords(IWord comparePrevious) ... -- String
    *... this.compareStringsHelper(IWord nextWord, String next) ... -- boolean
    *... this.toImage(WorldScene ws) ... -- WorldScene
    ---------------------------------------------------*/
    // TODO Auto-generated method stub
    return (this.word.compareToIgnoreCase(next) == 0 
        || this.word.compareToIgnoreCase(next) < 0);
  }
  
  //returns a world scene with the word added
  public WorldScene toImage(WorldScene ws) {
    /*---------------------------------------------------
    // TEMPLATE for this method:
    // EVERYTHING from our class-wide template...
    // PLUS methods on the parameters
    *... draw(WorldScene ws) ... -- WorldScene
    *... toImage(WorldScene ws) ... -- WorldScene
    ---------------------------------------------------*/
    return ws.placeImageXY(
        new TextImage(this.word, Color.RED), this.x, this.y);
  }
  
  
}

//represents the ZType game world
interface IZTypeWorld {
  int WORLD_HEIGHT = 500;
  int WORLD_WIDTH = 500;
}

//represents the ZType game world
class ZTypeWorld extends World implements IZTypeWorld {
  ILoWord words;
  WorldScene ws;
  Random rand;
  
  /* Template
   * Fields:
   * ... this.words ... -- ILoWord
   * ... this.ws ... -- WorldScene
   * ... this.rand ... -- Random
   * Methods:
   * ... this.makeScene() ... -- WorldScene
   * Methods of fields:
   * ... this.words.sort() ... -- ILoWord
   * ... this.words.isSorted() ... -- boolean
   * ... this.words.checkAndReduce(String s) ... -- ILoWord
   * ... this.words.addToEnd(IWord w) ... -- ILoWord
   * ... this.words.filterOutEmpties() ... -- ILoWord
   * ... this.words.insert(IWord compareFirst) ... -- ILoWord
   * ... this.words.draw(WorldScene ws) ... -- WorldScene
   * ... this.words.skipLoWordCheckEmpty() ... -- ILoWord
   * 
   */


  //for 'real' randoms
  ZTypeWorld(ILoWord words) {
    this.words = words;
    this.ws = new WorldScene(IZTypeWorld.WORLD_HEIGHT, IZTypeWorld.WORLD_WIDTH);
    this.rand = new Random(0);
  }
  
  //For testing (specified randoms)
  ZTypeWorld(ILoWord words, Random rand) {
    this.words = words;
    this.ws = new WorldScene(IZTypeWorld.WORLD_HEIGHT, IZTypeWorld.WORLD_WIDTH);
    this.rand = rand;
  }

  @Override
  public WorldScene makeScene() {
    return this.words.draw(this.ws);
  }
  
  /* for part 2
  public String createSix() {
    return new Utils().getSixLetterWord(rand);
  }
   */
  
  //for part 2
  /*
  public int createX() {
    //for part 2
    return new Utils().randomX(rand);
  }
  
  public ZTypeWorld onTick() {
    if (this.words.checkGameOver(this.WORLDHEIGHT)) {
      return new ZTypeWorld(ws.endOfWorld("Game Over"), ;
    }
    else {
      return new ZTypeWorld(this.words.filterOutEmpties()
          .addToEnd(
              new ActiveWord(createSix(), createX(), 0)),
          this.ws, this.rand);
    }
  }
  
  public IWord onSpawn() {
    return new ActiveWord(createSix(), createX(), 0);
  }
  
  public IWord onSpawnHelp(int acc) {
    return new ActiveWord(createSix(), createX(), 0);
  }

  public ZTypeWorld onKeyEvent(String key) {
    return new ZTypeWorld(this.words.checkAndReduce(key),
        this.ws, this.rand);
  }
  */
}

//utility interface for ZType
interface IUtils {
  String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
}

//utility class for ZType
class Utils implements IUtils {
  /* TEMPALTE
   * Fields:
   * n/a
   * Methods:
   * ... this.getSixLetterWord(Random rand) ... -- String
   * ... this.getSixLetterWordHelp(Random rand, int currNum, String wordAcc) ... -- String
   * Methods of fields:
   * n/a
   */
  
  Utils() { }
  
  String getSixLetterWord(Random rand) {
    return getSixLetterWordHelp(rand, rand.nextInt(25), "");
  }
  
  String getSixLetterWordHelp(Random rand, int currNum, String wordAcc) {
    if (wordAcc.length() == 6) {
      return wordAcc;
    } else {
      return getSixLetterWordHelp(rand, rand.nextInt(25), 
          wordAcc + IUtils.ALPHABET.substring(currNum, currNum + 1));
    }
  }
  
  //for part 2
  /*
  int randomX(Random rand) {
    return rand.nextInt(500);
  }
  */
  
  
  
}


















//all examples and tests for ILoWord
class ExamplesWordLists {
  IWord at = new ActiveWord("at", 0, 0);
  IWord be = new ActiveWord("Be", 100, 200);
  IWord cat = new ActiveWord("cAt", 0, 0);
  IWord dam = new InactiveWord("dam", 0, 0);
  IWord dot = new ActiveWord("dot", 0, 0);
  IWord all = new InactiveWord("All", 0, 0);
  //empty IWord
  IWord emptyW = new ActiveWord("", 0, 0);
  IWord emptyIW = new InactiveWord("", 0, 0);
  
  //empty
  ILoWord emptyL = new MtLoWord();
  //sorted list
  ILoWord sL1 = new ConsLoWord(be, emptyL);
  ILoWord sL2 = new ConsLoWord(at, sL1);
  //unsorted list
  ILoWord uL1 = new ConsLoWord(cat, emptyL);
  ILoWord uL2 = new ConsLoWord(dam, uL1);
  ILoWord uL3 = new ConsLoWord(dot, uL2);
  //different sized list
  ILoWord dSL1 = new ConsLoWord(dam, new ConsLoWord(all, 
      new ConsLoWord(be, new ConsLoWord(at, emptyL))));
  ILoWord dSL2 = new ConsLoWord(all, new ConsLoWord(at, 
      new ConsLoWord(be, new ConsLoWord(dam, emptyL))));
  
  //repeat sorted and unsorted
  ILoWord rSL1 = new ConsLoWord(all, sL2);
  ILoWord rUSL1 = new ConsLoWord(at, rSL1);
  ILoWord rSL2 = new ConsLoWord(at, sL2);
  ILoWord rSL3 = new ConsLoWord(at, rSL1);
  
  //emptyCons
  ILoWord emptyWL1 = new ConsLoWord(emptyW, emptyL);
  ILoWord emptyWL2 = new ConsLoWord(emptyIW, emptyL);
  //with empty strings
  ILoWord eSDSL1 = new ConsLoWord(dam, new ConsLoWord(emptyW, 
      new ConsLoWord(all, new ConsLoWord(be, new ConsLoWord(at, emptyL)))));
  ILoWord eSL1 = new ConsLoWord(at, new ConsLoWord(emptyW, sL1));
  ILoWord eSL2 = new ConsLoWord(emptyW, new ConsLoWord(at, sL1));
  //at, "", be, emptyL
  ILoWord eUSL2 = new ConsLoWord(emptyIW, new ConsLoWord(at, sL1));
  ILoWord eRUSL1 = new ConsLoWord(at, new ConsLoWord(emptyW, rSL1));
  ILoWord eRUSL2 = new ConsLoWord(emptyIW, new ConsLoWord(at, 
      new ConsLoWord(emptyW, rSL1)));
  
  WorldScene worldscene = new WorldScene(500,500);
  WorldCanvas worldcanvas = new WorldCanvas(500,500);
  
  Utils utils = new Utils();
  Random rand = new Random(0);
  
  //tests whether testSort sorts sorted and unsorted list (1-7 tests)
  boolean testSort(Tester t) {
    return t.checkExpect(this.emptyL.sort(), this.emptyL) //tests empty list
        && t.checkExpect(this.sL2.sort(), this.sL2) 
        //tests basic func. sorted list
        && t.checkExpect(this.sL1.sort(), this.sL1) 
        //tests basic func. sorted list
        && t.checkExpect(this.emptyWL1.sort(), this.emptyWL1) 
        //tests list w/empty word
        && t.checkExpect(this.eSL1.sort(), new ConsLoWord(this.emptyW, 
            new ConsLoWord(this.at, 
                new ConsLoWord(this.be, this.emptyL)))) //test list w/empty word
        && t.checkExpect(this.rUSL1.sort(), new ConsLoWord(this.all, 
            new ConsLoWord(this.at, this.sL2))) // tests basic func. with repeats
        && t.checkExpect(this.uL2.sort(), new ConsLoWord(this.cat, 
            new ConsLoWord(this.dam, this.emptyL))); 
    // tests basic func. unsorted list
  }
  
  //tests whether isSorted checks whether a list is sorted, is 
  // case-insensitive and handles repeats (8-16 tests)
  boolean testIsSorted(Tester t) {
    return t.checkExpect(this.emptyL.isSorted(), true) //tests empty list
        && t.checkExpect(this.sL2.isSorted(), true) 
        //tests basic func. sorted list
        && t.checkExpect(this.sL1.isSorted(), true) 
        //tests basic func. sorted list
        && t.checkExpect(this.uL2.isSorted(),false) 
        //tests basic func. unsorted list
        && t.checkExpect(this.dSL1.isSorted(),false) 
        //tests basic func. big unsorted list
        && t.checkExpect(this.rSL1.isSorted(),true) //tests basic func. repeats
        && t.checkExpect(this.rUSL1.isSorted(),false) 
        //tests basic func. repeats unsorted list
        && t.checkExpect(this.eSL1.isSorted(),false) 
        //tests basic func. sorted list w/empty string
        && t.checkExpect(this.rSL2.isSorted(),true); //tests basic func. repeats
  }

  //tests whether checkAndReduce properly reduces only active word in a 
  // list of words with the given string
  boolean testCheckAndReduce(Tester t) {
    return t.checkExpect(this.emptyL.checkAndReduce("a"), this.emptyL) 
        //test empty list
        && t.checkExpect(this.sL2.checkAndReduce("p"), this.sL2) 
        //tests basic func. non first letter, list of two
        && t.checkExpect(this.sL2.checkAndReduce("a"), new ConsLoWord(
            new ActiveWord("t",0,0), this.sL1))
        //tests basic func. true first letter, list of two
        && t.checkExpect(this.sL1.checkAndReduce("a"), this.sL1)
        //tests basic func. non first letter, list of one
        && t.checkExpect(this.sL1.checkAndReduce("b"), new ConsLoWord(
            new ActiveWord("e",100,200), this.emptyL))
        //tests basic func. true first letter, list of one
        && t.checkExpect(this.uL2.checkAndReduce("c"), new ConsLoWord(this.dam, 
            new ConsLoWord(new ActiveWord("At",0,0), this.emptyL)))
        //tests basic func. true first letter, list of incative
        && t.checkExpect(this.uL2.checkAndReduce("d"), new ConsLoWord(this.dam, 
            new ConsLoWord(new ActiveWord("cAt",0,0), this.emptyL)))
        //tests basic func. true first letter two steps down, list of two
        && t.checkExpect(this.dSL1.checkAndReduce("a"), new ConsLoWord(this.dam, 
            new ConsLoWord(this.all, new ConsLoWord(this.be, 
                new ConsLoWord(new ActiveWord("t",0,0), this.emptyL)))))
        // test basic func. true first letter a ways down
        && t.checkExpect(this.eSDSL1.checkAndReduce(""), this.eSDSL1)
        // test basic func. empty word
        && t.checkExpect(this.rSL2.checkAndReduce("a"), new ConsLoWord(
            new ActiveWord("t",0,0), new ConsLoWord(
                new ActiveWord("t",0,0), this.sL1))); 
    // test basic func. repeats list
        
  }
  
  //tests whether addToEnd properly appends the given word to the list of words
  boolean testAddToEnd(Tester t) {
    return t.checkExpect(this.emptyL.addToEnd(this.emptyW), 
        new ConsLoWord(this.emptyW, this.emptyL)) 
        //tests empty list with empty word
        && t.checkExpect(this.emptyL.addToEnd(this.at), 
            new ConsLoWord(this.at, this.emptyL)) 
        //tests empty list with word
        && t.checkExpect(this.sL2.addToEnd(this.dot), new ConsLoWord(this.at, 
            new ConsLoWord(this.be, new ConsLoWord(this.dot, this.emptyL)))) 
        //tests basic func. sorted size 2
        && t.checkExpect(this.sL1.addToEnd(this.all), new ConsLoWord(this.be, 
            new ConsLoWord(this.all, this.emptyL))) 
        //tests basic func. sorted size 1
        && t.checkExpect(this.dSL1.addToEnd(this.cat), 
            new ConsLoWord(this.dam, new ConsLoWord(this.all, 
            new ConsLoWord(this.be, new ConsLoWord(this.at, 
                new ConsLoWord(this.cat, this.emptyL)))))) 
        //tests basic func. big unsorted
        && t.checkExpect(this.rSL2.addToEnd(this.at), new ConsLoWord(this.at, 
            new ConsLoWord(this.at, new ConsLoWord(this.be, 
                new ConsLoWord(this.at, this.emptyL))))); //tests basic func. repeats
  }
  
  //tests whether filterOutEmpties properly removes any IWords in a list of 
  //words with an empty string
  boolean testFilterOutEmpties(Tester t) {
    return t.checkExpect(this.emptyL.filterOutEmpties(), this.emptyL) 
        //tests empty list
        && t.checkExpect(this.sL2.filterOutEmpties(), this.sL2) 
        //tests basic func. sorted
        && t.checkExpect(this.sL1.filterOutEmpties(), this.sL1) 
        //tests basic func. sorted
        && t.checkExpect(this.uL2.filterOutEmpties(),this.uL2) 
        //tests basic func. unsorted
        && t.checkExpect(this.dSL1.filterOutEmpties(),this.dSL1) 
        //tests basic func. big unsorted
        && t.checkExpect(this.rSL1.filterOutEmpties(),this.rSL1) 
        //tests basic func. repeats
        && t.checkExpect(this.rUSL1.filterOutEmpties(),this.rUSL1) 
        //tests basic func. repeats unsorted
        && t.checkExpect(this.eSL1.filterOutEmpties(),this.sL2) 
        //tests basic func. list w/empty string
        && t.checkExpect(this.eRUSL1.filterOutEmpties(),this.rUSL1)
        //tests basic func. unsorted list w/empty string
        && t.checkExpect(this.eRUSL2.filterOutEmpties(),this.rUSL1)
        //tests basic func. unsorted list w/empty string
        && t.checkExpect(this.rSL2.filterOutEmpties(),this.rSL2);
    //tests basic func. list w/repeats
  }
  
  //tests whether draw properly creates a WorldScene
  boolean testDraw(Tester t) {
    return t.checkExpect(this.emptyL.draw(this.worldscene), this.worldscene)
        //draw empty scene
        && t.checkExpect(this.sL2.draw(this.worldscene), 
            new WorldScene(500,500).placeImageXY(
                new TextImage("Be", Color.CYAN), 100, 200).placeImageXY(
                    new TextImage("at", Color.CYAN), 0, 0))
        //basic draw list
        && t.checkExpect(this.uL2.draw(this.worldscene), 
            new WorldScene(500,500).placeImageXY(
                new TextImage("cAt", Color.CYAN), 0, 0).placeImageXY(
                    new TextImage("dam", Color.RED), 0, 0))
        //basic draw with overlap
        //below is basic draw with empty string in middle
        && t.checkExpect(this.eSL1.draw(this.worldscene), 
            new WorldScene(500,500).placeImageXY(
                new TextImage("Be", Color.CYAN), 100, 200).placeImageXY(
                new TextImage("", Color.CYAN), 0, 0).placeImageXY(
                    new TextImage("at", Color.CYAN), 0, 0));
  }
  
  //tests draw such that it displays for dev purposes
  boolean xtestDrawDisplay(Tester t) {
    WorldScene s1 = new WorldScene(500, 500).placeImageXY(
        new RectangleImage(500, 500, "solid", Color.BLACK), 250, 250);
    return this.worldcanvas.drawScene(sL1.draw(s1)) && this.worldcanvas.show();
  }
  
  boolean testCheckEmpty(Tester t) {
    return t.checkExpect(this.at.checkEmpty(), false) 
        //tests if active word is empty
        && t.checkExpect(this.dam.checkEmpty(), false)
        //tests if inactive word is empty
        && t.checkExpect(this.emptyW.checkEmpty(), true);
    //tests if empty word is empty
  }
  
  //tests whether returnReduce returns an IWord thats reduced or not reduced
  // depending on activity
  boolean testReturnReduce(Tester t) {
    return t.checkExpect(this.at.reduce("s"), "at") //active, non match
        && t.checkExpect(this.at.reduce("a"), "t") //active, non match
        && t.checkExpect(this.at.reduce("t"), "at") //active, match
        && t.checkExpect(this.dam.reduce("d"), "dam");//inactive, match
  }
  
  //tests whether reduce returns a string that reduces by string it the word 
  //begins with that string and is an active word
  boolean testReduce(Tester t) {
    return t.checkExpect(this.at.returnReduce("s"), this.at) 
        //active, non match
        && t.checkExpect(this.at.returnReduce("a"), new ActiveWord("t", 0, 0))
        //active, match
        && t.checkExpect(this.at.returnReduce("t"), this.at)
        //active, non match
        && t.checkExpect(this.dam.returnReduce("d"), this.dam); //inactive, match
  }

  //checks if compareStringHelper correctly identifies order
  boolean testCompareWords(Tester t) {
    return t.checkExpect(this.at.compareWords(this.be), false)// checks unordered
        && t.checkExpect(this.be.compareWords(this.at), true) // checks ordered
        && t.checkExpect(this.dam.compareWords(this.at), true) //checks inactive
        && t.checkExpect(this.emptyW.compareWords(this.at), false); //checks empty
  }
  
  //checks if compareStringHelper correctly identifies order
  boolean testCompareStringsHelper(Tester t) {
    return t.checkExpect(this.at.compareStringsHelper(this.be, "be"), 
        true)// checks unordered
        && t.checkExpect(this.be.compareStringsHelper(this.at, "at"), 
            false) // checks ordered
        && t.checkExpect(this.dam.compareStringsHelper(this.at, "at"), 
            false) //checks inactive
        && t.checkExpect(this.emptyW.compareStringsHelper(this.at, "at"), 
            true); //checks empty
  }
  
  //checks if skipLoWordCheckEmpty correctly removes empty strings
  boolean testSkipLoWordCheckEmpty(Tester t) {
    return t.checkExpect(this.sL2.skipLoWordCheckEmpty(), 
        this.sL2) // checks ordered size 2
        && t.checkExpect(this.sL1.skipLoWordCheckEmpty(), 
            this.sL1)//checks ordered size 1
        && t.checkExpect(this.uL2.skipLoWordCheckEmpty(), 
            this.uL2)//checks unordered
        && t.checkExpect(this.eSL1.skipLoWordCheckEmpty(), 
            this.sL2) //checks emptyString
        && t.checkExpect(this.emptyL.skipLoWordCheckEmpty(), 
            this.emptyL); //checks empty list
  }
  
  //tests whether checkTwo checks the order of two iwords
  boolean testCheckTwo(Tester t) {
    return t.checkExpect(this.sL2.checkTwo(this.be), false) //checks unordered
        && t.checkExpect(this.emptyL.checkTwo(this.be), true) //checks empty list
        && t.checkExpect(this.sL2.checkTwo(this.all), true) //checks ordered
        && t.checkExpect(this.sL2.checkTwo(this.emptyW), true); 
    //checks empty ordered
  }

  //tests whether toImage creates a correct worldscene
  boolean testToImage(Tester t) {
    return t.checkExpect(emptyW.toImage(worldscene), worldscene.placeImageXY(
        new TextImage("", Color.CYAN), 0, 0)) //empty word
        && t.checkExpect(at.toImage(worldscene), worldscene.placeImageXY(
            new TextImage("at", Color.CYAN), 0, 0)) //basic
        && t.checkExpect(dam.toImage(worldscene), worldscene.placeImageXY(
            new TextImage("dam", Color.RED), 0, 0))
        && t.checkExpect(be.toImage(worldscene), worldscene.placeImageXY(
            new TextImage("Be", Color.CYAN), 100, 200)); //dif. placement
  }

  //tests the ztypeworld make scene
  boolean testMakeScene(Tester t) {
    //make scene empty
    return t.checkExpect(new ZTypeWorld(this.emptyL).makeScene(), new WorldScene(500,500))
        && t.checkExpect(new ZTypeWorld(this.sL2).makeScene(), 
            new WorldScene(500,500).placeImageXY(
                new TextImage("Be", Color.CYAN), 100, 200).placeImageXY(
                    new TextImage("at", Color.CYAN), 0, 0)) //basic draw
        && t.checkExpect(new ZTypeWorld(this.uL2).makeScene(), 
            new WorldScene(500,500).placeImageXY(
                new TextImage("cAt", Color.CYAN), 0, 0).placeImageXY(
                    new TextImage("dam", Color.RED), 0, 0)) //basic draw with overlap
        && t.checkExpect(new ZTypeWorld(this.eSL1).makeScene(), 
            new WorldScene(500,500).placeImageXY(
                new TextImage("Be", Color.CYAN), 100, 200).placeImageXY(
                    new TextImage("", Color.CYAN), 0, 0).placeImageXY(
                        new TextImage("at", Color.CYAN), 0, 0));
    //basic draw with empty string
  }

  //tests the utils make six letter word; getSixLetterWord
  boolean testGetSixLetterWord(Tester t) {
    // Test generating a 6-letter word
    return t.checkExpect(utils.getSixLetterWord(new Random(1)), "knwnee")
        //test a different seed
        && t.checkExpect(utils.getSixLetterWord(new Random(5)), "mryygf");
  }


  //tests the utils make letter from random; getSixLetterWordHelp
  boolean testGetSixLetterWordHelp(Tester t) { 
    // Test generating a 6-letter word with the first letter 'a'
    return t.checkExpect(utils.getSixLetterWordHelp(new Random(1), 0, ""), "aknwne")
        // Test generating a 6-letter word with the first letter 'f'
        && t.checkExpect(utils.getSixLetterWordHelp(new Random(1), 5, ""), "fknwne")
        // Test generating a 6-letter word with the first letter 'z' (wrap-around)
        && t.checkExpect(utils.getSixLetterWordHelp(new Random(1), 25, ""), "zknwne");
  }




}
