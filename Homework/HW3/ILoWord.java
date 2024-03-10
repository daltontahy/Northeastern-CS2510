import tester.Tester;
import javalib.funworld.WorldScene;
import java.awt.Color; 
import javalib.worldimages.*;

//represents a list of words
interface ILoWord {
  
  // returns a new list with all words sorted in alphabetical order
  ILoWord sort();
  
  // returns a boolean representing if a list of words is sorted in alphabetical order.
  boolean isSorted();
  
  // takes this list of words and a given list of words, and produces a new list where the first, third,
  // fifth, etc words are from this, and the second, forth, 6th, etc words are from lst.
  ILoWord interleave(ILoWord lst);

  // merges this *sorted list of and a given sorted list of IWords, returning a sorted list of IWords
  // that contains all items in both original lists including duplicates, treating all words as lower case
  ILoWord merge(ILoWord lst);

  // takes a string of length 1 and produces an ILoWord where any active words in this are reduced by removing
  // the first letter if the first letter matches str
  ILoWord checkAndReduce(String str);

  // takes an IWord and returns a ILoWord that is this with the word added onto the end.
  ILoWord addToEnd(IWord word);

  // produces a ILoWord with all IWords that have an empty string filtered out
  ILoWord filterOutEmpties();
  
  // takes in a worldscene and draws all the words in this ILoWord onto given wordscene
  WorldScene draw(WorldScene scene);
}

//represents an empty list of words
class MtLoWord implements ILoWord {
  
  /* Template:
   * Fields:
   * n/a
   *
   * Methods:
   * this.sort() -- ILoWord
   * this.isSorted() -- boolean
   * this.interleave(ILoWord lst) -- ILoWord
   * this.merge(ILoWord lst) -- ILoWord
   * this.checkAndReduce(String str) -- ILoWord
   * this.addToEnd(IWord word) -- ILoWord
   * this.filterOutEmpties() -- ILoWord
   * this.draw(WorldScene scene); -- WorldScene
   * 
   * Methods of fields:
   * n/a
   */
  
  MtLoWord() {
    
  }
  
  public ILoWord sort() {
    return this;
  }
  
  public boolean isSorted() {
    return true;
  }
  
  public ILoWord interleave(ILoWord lst) {
    return lst;
  }
  
  public ILoWord merge(ILoWord lst) {
    return lst;
  }
  
  public ILoWord checkAndReduce(String str) {
    return this;
  }
  
  public ILoWord addToEnd(IWord word) {
    return new ConsLoWord(word, this);
  }
  
  public ILoWord filterOutEmpties() {
    return this;
  }
  
public WorldScene draw(WorldScene scene) {
    return scene;
  
}

class ConsLoWord implements ILoWord {
  IWord first;
  ILoWord rest;

  ConsLoWord(IWord first, ILoWord rest) {
    this.first = first;
    this.rest = rest;
  }

  public ILoWord sort() {
    if (this.rest instanceof MtLoWord) {
      return this;
    } else {
      ILoWord sortedRest = this.rest.sort();

      return insertSorted(this.first, sortedRest);
    }
  }

  // helper method for insertion in sorted order
  private ILoWord insertSorted(IWord word, ILoWord sortedList) {
    if (sortedList instanceof MtLoWord) {
      return new ConsLoWord(word, sortedList);
    } else {
      ConsLoWord consList = (ConsLoWord) sortedList;
      if (word.getWord().compareToIgnoreCase(consList.first.getWord()) <= 0) {
        return new ConsLoWord(word, sortedList);
      } else {
        return new ConsLoWord(consList.first, insertSorted(word, consList.rest));
      }
    }
  }


  public boolean isSorted() {
    if (this.rest instanceof MtLoWord) {
      return true; 
    } else if (this.rest instanceof ConsLoWord) {
      ConsLoWord next = (ConsLoWord) this.rest;
      return this.first.getWord().compareToIgnoreCase(next.first.getWord()) <= 0 && next.isSorted();
    }
    return false;
  }

  
  public ILoWord interleave(ILoWord lst) {
    if (lst instanceof MtLoWord) {
      return this;
    } else if (lst instanceof ConsLoWord) {
      ConsLoWord otherList = (ConsLoWord) lst;
      return new ConsLoWord(this.first, otherList.interleave(this.rest));
    }
    return this; 
  }

  
  public ILoWord merge(ILoWord lst) {
    if (lst instanceof MtLoWord) {
      return this;
    } else if (lst instanceof ConsLoWord) {
      ConsLoWord otherList = (ConsLoWord) lst;
      if (this.first.getWord().compareToIgnoreCase(otherList.first.getWord()) <= 0) {
        return new ConsLoWord(this.first, this.rest.merge(lst));
      } else {
        return new ConsLoWord(otherList.first, this.merge(otherList.rest));
      }
    }
    return this; 
  }

  
  public ILoWord checkAndReduce(String str) {
    IWord reducedFirst = this.first.reduceIfStartsWith(str);
    return new ConsLoWord(reducedFirst, this.rest.checkAndReduce(str));
  }

  
  public ILoWord addToEnd(IWord word) {
    if (this.rest instanceof MtLoWord) {
      return new ConsLoWord(this.first, new ConsLoWord(word, new MtLoWord()));
    } else {
      return new ConsLoWord(this.first, this.rest.addToEnd(word));
    }
  }

  
  public ILoWord filterOutEmpties() {
    if (this.first.getWord().isEmpty()) {
      return this.rest.filterOutEmpties();
    } else {
      return new ConsLoWord(this.first, this.rest.filterOutEmpties());
    }
  }

  
  public WorldScene draw(WorldScene scene) {
    return this.first.toImage(this.rest.draw(scene));
  }
  }

  
}

//represents a word in the ZType game
interface IWord {
  
  String getWord();
  
  IWord reduceIfStartsWith(String str);
  
  WorldScene toImage(WorldScene scene);


}

//represents an active word in the ZType game
class ActiveWord implements IWord {
  String word;
  int x;
  int y;

  ActiveWord(String word, int x, int y) {
    this.word = word;
    this.x = x;
    this.y = y;
  }
  
  public String getWord() {
    return this.word;
  }
  
  public IWord reduceIfStartsWith(String str) {
    if (this.word.toLowerCase().startsWith(str.toLowerCase())) {
      if (this.word.length() > 1) {
        return new ActiveWord(this.word.substring(1), this.x, this.y);
      } else {
        return new ActiveWord("", this.x, this.y);
      }
    } else {
      return this;
    }
  }
  
  public WorldScene toImage(WorldScene scene) {
    return scene.placeImageXY(
        new TextImage(this.word, Color.CYAN), this.x, this.y);
  }

}

//represents an inactive word in the ZType game
class InactiveWord implements IWord {
  String word;
  int x;
  int y;


  InactiveWord(String word, int x, int y) {
    this.word = word;
    this.x = x;
    this.y = y;
  }
  
  public String getWord() {
    return this.word;
  }
  
  public IWord reduceIfStartsWith(String str) {
    if (this.word.toLowerCase().startsWith(str.toLowerCase())) {
      if (this.word.length() > 1) {
        return new InactiveWord(this.word.substring(1), this.x, this.y);
      } else {
        return new InactiveWord("", this.x, this.y);
      }
    } else {
      return this;
    }
  }
  
  public WorldScene toImage(WorldScene scene) {
    return scene.placeImageXY(
        new TextImage(this.word, Color.CYAN), this.x, this.y);
  }
  
}









//all examples and tests for ILoWord
class ExamplesWordLists {
  IWord activeWord1 = new ActiveWord("apple", 10, 20);
  IWord activeWord2 = new ActiveWord("banana", 30, 40);
  IWord inactiveWord1 = new InactiveWord("cherry", 50, 60);
  IWord inactiveWord2 = new InactiveWord("date", 70, 80);
 
  ILoWord emptyList = new MtLoWord();
  MtLoWord emptyLoWord = new MtLoWord();
  ILoWord wordList1 = new ConsLoWord(activeWord1, new ConsLoWord(activeWord2, emptyList));
  ILoWord wordList2 = new ConsLoWord(inactiveWord1, new ConsLoWord(inactiveWord2, emptyList));
  ILoWord mixedList = new ConsLoWord(activeWord1, new ConsLoWord(inactiveWord1, emptyList));
  IWord activeWord3 = new ActiveWord("apricot", 15, 25);
  IWord inactiveWord3 = new InactiveWord("date", 70, 80);
  ILoWord wordList3 = new ConsLoWord(activeWord3, emptyList);
  ILoWord wordList4 = new ConsLoWord(inactiveWord3, emptyList);
  ILoWord unsortedWordList = new ConsLoWord(activeWord2, new ConsLoWord(activeWord1, emptyList));


 
  boolean testSortMtLoWord(Tester t) {
    return t.checkExpect(this.emptyLoWord.sort(), emptyLoWord, "Test Failed: MtLoWord sort");
  }
 
  boolean testIsSortedMtLoWord(Tester t) {
    return t.checkExpect(this.emptyLoWord.isSorted(), true, "Test Failed: MtLoWord isSorted");
  }
 
  boolean testInterleaveMtLoWord(Tester t) {
    return 
       // Test interleave with empty list
       t.checkExpect(this.emptyLoWord.interleave(emptyList), emptyList, 
           "Test Failed: MtLoWord interleave w/ empty.") 
       &&
       
       // Test interleave with nonempty list
       t.checkExpect(this.emptyLoWord.interleave(wordList1), wordList1, 
           "Test Failed: MtLoWord interleave w/nonempty");
  }
 
  boolean testMergeMtLoWord(Tester t) {
    return
       // Test merging empty with empty
       t.checkExpect(this.emptyLoWord.merge(this.emptyList), this.emptyList,
           "Test Failed: empty merged with empty should be empty.") 
       &&

       // Test merging empty with a list of active words
       t.checkExpect(this.emptyLoWord.merge(this.wordList1), this.wordList1,
           "Test Failed: empty merged with wordList1 should be wordList1.")
       &&

       // Test merging empty with a list of inactive words
       t.checkExpect(this.emptyLoWord.merge(this.wordList2), this.wordList2,
           "Test Failed: empty merged with wordList2 should be wordList2.") 
       &&

       // Test merging empty with a mixed list of active and inactive words
       t.checkExpect(this.emptyLoWord.merge(this.mixedList), this.mixedList,
           "Test Failed: empty merged with mixedList should be mixedList.");
  }
 
  boolean testCheckAndReduceMtLoWord(Tester t) {
    return
      // Test reducing empty list with a letter that could be in active words
      t.checkExpect(this.emptyLoWord.checkAndReduce("a"), this.emptyLoWord,
          "Test Failed: empty list reduced with 'a' should remain empty.") 
      &&

      // Test reducing empty list with a letter that could be in inactive words
      t.checkExpect(this.emptyLoWord.checkAndReduce("c"), this.emptyLoWord,
          "Test Failed: empty list reduced with 'c' should remain empty.") 
      &&

      // Test reducing empty list with a letter not present in any words
      t.checkExpect(this.emptyLoWord.checkAndReduce("z"), this.emptyLoWord,
          "Test Failed: empty list reduced with 'z' should remain empty.");
  }
 
  boolean testAddToEndMtLoWord(Tester t) {
    return
      // Test adding an active word to the empty list
      t.checkExpect(this.emptyLoWord.addToEnd(this.activeWord1), 
          new ConsLoWord(this.activeWord1, this.emptyLoWord),
          "Test Failed: empty list with 'activeWord1' added should contain 'activeWord1'.") 
      &&

      // Test adding an inactive word to the empty list
      t.checkExpect(this.emptyLoWord.addToEnd(this.inactiveWord1), 
          new ConsLoWord(this.inactiveWord1, this.emptyLoWord),
          "Test Failed: empty list with 'inactiveWord1' added should contain 'inactiveWord1'.");
  }

  boolean testFilterOutEmptiesMtLoWord(Tester t) {
    return
      // Test filtering an empty list
      t.checkExpect(this.emptyLoWord.filterOutEmpties(), this.emptyLoWord,
          "Test Failed: Filtering an empty list should result in an empty list.");
  }
 
  boolean testDrawMtLoWord(Tester t) {
    WorldScene scene = new WorldScene(500, 300); 
    this.emptyLoWord.draw(scene); 
   
    return true; 
  }
  
  // Start tests for the ConsLoWord Class
  
  boolean testSortConsLoWord(Tester t) {
    ILoWord sortedWordList = new ConsLoWord(activeWord1, new ConsLoWord(activeWord2, 
        new ConsLoWord(activeWord3, emptyList)));
    IWord word1 = new ActiveWord("Apple", 10, 20);
    IWord word2 = new ActiveWord("banana", 30, 40);
    IWord word3 = new ActiveWord("Cherry", 50, 60);
    ILoWord mixedCaseList = new ConsLoWord(word3, new ConsLoWord(word1, 
        new ConsLoWord(word2, new MtLoWord())));
    ILoWord sortedList = new ConsLoWord(word1, new ConsLoWord(word2, 
        new ConsLoWord(word3, new MtLoWord())));

    return
      // Test sorting an unsorted list
      t.checkExpect(unsortedWordList.sort(), wordList1,
        "Test Failed: Sorting unsortedWordList should result in a sorted list (wordList1).") 
      &&
      
      // testing upper and lowercase, the case i didnt have before.
      t.checkExpect(mixedCaseList.sort(), sortedList, "Test Failed: sort with mixed"
          + " case strings")
      &&

      // Test sorting a list with a single element
      t.checkExpect(wordList3.sort(), wordList3,
        "Test Failed: Sorting a single-element list (wordList3) should result in the same list.") 
      &&

      // Test sorting an already sorted list
      t.checkExpect(wordList1.sort(), wordList1,
        "Test Failed: Sorting an already sorted list (wordList1) should result in the same list.");
  }
  
  boolean testIsSortedConsLoWord(Tester t) {
    IWord word1 = new ActiveWord("Apple", 10, 20);
    IWord word2 = new ActiveWord("banana", 30, 40);
    IWord word3 = new ActiveWord("Cherry", 50, 60);
    ILoWord sortedList = new ConsLoWord(word1, new ConsLoWord(word2, 
        new ConsLoWord(word3, new MtLoWord())));

    return
        
      // test on mixed case words, the test i didnt have before
      t.checkExpect(sortedList.isSorted(), true, "Test Failed: isSorted with mixed case strings")
      &&
      
      // Test on an already sorted list
      t.checkExpect(wordList1.isSorted(), true,
        "Test Failed: wordList1 should be considered sorted.") 
      &&

      // Test on an unsorted list
      t.checkExpect(unsortedWordList.isSorted(), false,
        "Test Failed: Unsorted list should not be considered sorted.") 
      &&

      // Test on a list with a single element
      t.checkExpect(wordList3.isSorted(), true,
        "Test Failed: A single-element list (wordList3) should always be considered sorted.");
  }
  
  boolean testInterleaveConsLoWord(Tester t) {
    ILoWord interleavedList1And3 = new ConsLoWord(activeWord1, new ConsLoWord(activeWord3, 
        new ConsLoWord(activeWord2, emptyList)));
    ILoWord interleavedList3And1 = new ConsLoWord(activeWord3, new ConsLoWord(activeWord1, 
        new ConsLoWord(activeWord2, emptyList)));
    IWord word1 = new ActiveWord("Apple", 10, 20);
    IWord word2 = new ActiveWord("banana", 30, 40);
    ILoWord nonEmptyList = new ConsLoWord(word1, new ConsLoWord(word2, new MtLoWord()));
    ILoWord emptyList = new MtLoWord();

    return
      // interleave with empty when invoked by nonempty
      t.checkExpect(nonEmptyList.interleave(emptyList), nonEmptyList, "Test Failed:"
          + " interleave non-empty with empty")
      &&
      
      // Test interleaving two lists of different sizes
      t.checkExpect(wordList1.interleave(wordList3), interleavedList1And3,
        "Test Failed: Interleaving wordList1 and wordList3 should correctly alternate elements.")
      &&

      // Test interleaving with the roles of the lists reversed
      t.checkExpect(wordList3.interleave(wordList1), interleavedList3And1,
        "Test Failed: Interleaving wordList3 and wordList1 should correctly alternate elements, "
        + "starting with wordList3.");
  }
  
  boolean testMergeConsLoWord(Tester t) {
    ILoWord mergedList1And4 = new ConsLoWord(activeWord1, new ConsLoWord(activeWord2, 
        new ConsLoWord(inactiveWord2, emptyList))); 
    IWord word1 = new ActiveWord("Apple", 10, 20);
    IWord word2 = new ActiveWord("banana", 30, 40);
    ILoWord sortedList = new ConsLoWord(word1, new ConsLoWord(word2, new MtLoWord()));
    ILoWord emptyList = new MtLoWord();

    return
        
      // testing sorted with empty
      t.checkExpect(sortedList.merge(emptyList), sortedList, "Test Failed:"
          + " merge sorted non-empty with empty")
      &&
    
      // Test merging with a list that includes a duplicate
      t.checkExpect(wordList1.merge(wordList4), mergedList1And4,
        "Test Failed: Merging wordList1 and wordList4 should handle duplicates correctly, "
        + "resulting in mergedList1And4.") 
      &&

      // Test merging two single-element lists
      t.checkExpect(wordList3.merge(wordList4), new ConsLoWord(activeWord3, 
          new ConsLoWord(inactiveWord3, emptyList)),
        "Test Failed: Merging two single-element lists (wordList3 and wordList4) should "
        + "result in a list with both elements.");
  }
  
  boolean testCheckAndReduceConsLoWord(Tester t) {
    ILoWord reducedWordList1 = new ConsLoWord(new ActiveWord("pple", 10, 20), 
        new ConsLoWord(this.activeWord2, this.emptyList)); 
    ILoWord reducedMixedList = new ConsLoWord(new ActiveWord("pple", 10, 20), 
        new ConsLoWord(this.inactiveWord1, this.emptyList)); 

    return
      // Test reducing a list with a matching first letter
      t.checkExpect(this.wordList1.checkAndReduce("a"), reducedWordList1,
        "Test Failed: Reducing wordList1 with 'a' should remove 'a' from 'apple'.") 
      &&

      // Test reducing a mixed list with a matching first letter in an active word
      t.checkExpect(this.mixedList.checkAndReduce("a"), reducedMixedList,
        "Test Failed: Reducing mixedList with 'a' should remove 'a' from 'apple'.") 
      &&

      // Test reducing a list with a non-matching letter
      t.checkExpect(this.wordList1.checkAndReduce("z"), this.wordList1,
        "Test Failed: Reducing wordList1 with 'z' should result in no change.");
  }
  
  boolean testAddToEndConsLoWord(Tester t) {
    ILoWord extendedWordList1 = new ConsLoWord(this.activeWord1, new ConsLoWord(this.activeWord2, 
        new ConsLoWord(this.inactiveWord3, this.emptyList))); 

    return
      // Test adding a word to the end of a non-empty list
      t.checkExpect(this.wordList1.addToEnd(this.inactiveWord3), extendedWordList1,
        "Test Failed: Adding inactiveWord3 to wordList1 should append it to the end.") 
      &&

      // Test adding a word to the end of a mixed list
      t.checkExpect(this.mixedList.addToEnd(this.activeWord3), new ConsLoWord(this.activeWord1, 
          new ConsLoWord(this.inactiveWord1, new ConsLoWord(this.activeWord3, this.emptyList))),
        "Test Failed: Adding activeWord3 to mixedList should append it to the end.");
  }
  
  boolean testFilterOutEmptiesConsLoWord(Tester t) {
    ILoWord listWithEmpty = new ConsLoWord(new ActiveWord("", 0, 0), this.wordList1);
    ILoWord cleanedList = this.wordList1;
    return
      // Test filtering a list that includes an empty word
      t.checkExpect(listWithEmpty.filterOutEmpties(), cleanedList,
        "Test Failed: Filtering listWithEmpty should remove the empty word, "
        + "resulting in wordList1.");
  }
  
  boolean testDrawConsLoWord(Tester t) {
    WorldScene scene = new WorldScene(500, 300);     
    this.wordList1.draw(scene); 
    
    return true;
  }
}
