# Assignment 5: World programs
## Goals: Design the ZType game

## 5.1 Instructions
This is a partner assignment.

Make sure you follow the style guidelines that we enforce. For now the most important ones are: using spaces instead of tabs, indenting by 2 characters, following the naming 
conventions (data type names start with a capital letter, names of fields and methods start with a lower case letter), and having spaces before curly braces. You will submit this 
assignment by the deadline using the online submission system. You may submit as many times as you wish. Be aware of the fact that close to the deadline the system may slow down to
handle many submissions - so make sure to submit at least a few minutes before the deadline.

As always, be sure to follow the Design Recipe and avoid accessing fields of fields, getters, type-checking and casting. Dynamic dispatch is your friend!  

The submissions will be organized as follows:
Assignment 5: All the files needed for your game, in one .zip file

Due Dates:

Part 1: Friday, February 16th, 9:00pm. Complete tasks 1 through 5 below for this part.
Part 2: Thursday, February 22nd, 9:00pm. Submit your complete working game for this part.

## The javalib library
The javalib library provides the support for the design of interactive games and creating images composed by combining geometric shapes as well as image files. See The Image Library 
for more information. To use the library, download the javalib file above and add it to your project the same way you have added the tester library. At the top of the .java file where 
the library is used, add the following import statements:

import tester.*;                // The tester library
import javalib.worldimages.*;   // images, like RectangleImage or OverlayImages
import javalib.funworld.*;      // the abstract World class and the big-bang library
import java.awt.Color;          // general colors (as triples of red,green,blue values)
                                // and predefined colors (Red, Green, Yellow, Blue, Black, White)  
<br/><br/>

Note that you should not use any mutation (or any other techniques that we have not covered in class) in this assignment.

ZType

Implement the game ZType. ZType is a typing game where the player must type words presented on the screen quickly to destroy them before they reach the bottom of the screen. Here are 
some tasks to help you get started:

Review the DotAnimationExample file on Piazza which is a world program. You will also need to review the The Image Library documentation mentioned above. Make sure you understand how 
the animation works.

Decide if you need to define any constants for the game. These are things that do not change throughout the life of the program, such as height and width of the game’s background. 
Review Lecture 10: Customizing constructors for correctness and convenience about defining constants.

Design the ZTypeWorld class. This should extend the World class from javalib. What fields should be included in this class? The field(s) of this class will represent what can change
in the world program. ~Hint: The ILoWord definition from Assignment 3 should be helpful.~

Don’t forget the template, especially those methods of fields!

Design the makeScene method which is required in the ZTypeWorld class. This will draw the current state of the world. Make sure to test this on at least three different examples of 
world states.

In a Utils class, design a method to produce a String of six alphabetic characters. To keep things simple the words in the game do not have to be real English. Instead, your method 
should use six randomly chosen characters. An accumulator method may be helpful for building these words.

Decide which events your ZTypeWorld class should handle.
Does the game need to respond to the passage of time? Then you need to override onTick in the ZTypeWorld class. A new random word should be added to the list of words periodically, 
so this is something that onTick will need to handle (among other things). You will also need to figure out how often a new word should be added. Adding one at each tick would be too
fast!

Does the game need to respond to key presses? Then you will need to override one of the key handler methods.

Review the The Image Library to see the documentation for onTick and to see which key handler methods are available.

Hint: Some of the methods that you implemented in Assignment 3 will be useful as helpers to your event handlers.

Your game should end if a word reaches the bottom of the scene. There should be a final scene indicating that the player has lost the game. Study The Image Library documentation for 
information about ending world programs.

If you wish to embellish the game, you can add additional features for extra credit in part 2. Here are some suggestions:
Keep score.

### Add multiple levels.
### Add an option to restart the game.
### Allow words of variable lengths (up to some reasonable limit).
### Enhance the graphics.

If you decide to implement any extra features, make sure to document them well. Remember – you will be graded for your program design, not making a cool video game. So whatever you 
add, make sure it’s well-designed. But have fun with it, too!

### A note about randomness
There are two ways to generate random numbers in Java. The easiest is to use Math.random(), which generates a double between 0 (inclusive) and 1 (exclusive). You can multiply this 
number by some integer to make it bigger, then coerce to an int to produce a random integer in the range you wish. However, this is not easily testable: you’ll get different random
values every time.

The better way to generate random numbers is: First, import java.util.Random at the top of your file. Next, create a new Random() object, and use its nextInt(int maxVal) method, which
will give you a random integer between zero (inclusive) and maxVal (exclusive).

This is known as a "pseudorandom number generator", since the numbers aren’t really random if they can be reliably repeated...

The reason this is better is because there is a second constructor, new Random(int initialSeed), which has the property that every time you create a Random with the same initial seed,
it will generate the same "random" numbers in the same order every time your program runs. You should therefore design your world classes with two constructors:
One of them, to be used for testing, should take in a Random object whose seed value you specify. This way your game will be utterly predictable every single time you test it.

The second constructor should not take in a Random object, but should call the other constructor, and pass along a really random object:

import java.util.Random;
import javalib.funworld.*;
 
class YourWorld extends World {
  Random rand
  // The constructor for use in "real" games
  YourWorld() { this(new Random()); }
  // The constructor for use in testing, with a specified Random object
  YourWorld(Random rand) { this.rand = rand; ... }
}
Now, your tests can be predictable while your game can still be random, and the rest of your code doesn’t need to change at all.

Submission notes
Include an additional file in your submission for part 2:
UserGuide.txt should be a short file describing how to play the game.

Be sure to include all your source files and all your image files (if you have any) in your submission, or else we won’t be able to run your game. If you have multiple files, you should submit the files as a single .zip file.
