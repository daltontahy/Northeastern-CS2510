# Assignment 2: Designing methods for complex data
## Goals: Learn to design methods for complex class hierarchies. Practice designing the representation of complex data.

## Instructions
Be very, very careful with naming! Again, the solution files expect your submissions to be named a certain way, so that they can define their own Examples class with which to test your code. Therefore, whenever the assignment specifies:
the names of classes, the names and types of the fields within classes, the names, types and order of the arguments to the constructor, the names, types and order of arguments to methods, or filenames.

...be sure that your submission uses exactly those names. Additionally, make sure you follow the style guidelines that the handin server enforces. For now the most important ones are: using spaces instead of tabs, indenting by 2 characters, following the naming conventions (data type names start with a capital letter, names of fields and methods start with a lower case letter), and having spaces before curly braces.
You will submit this assignment by the deadlines using the course handin server. Follow A Complete Guide to the Handin Server for information on how to use the handin server. You may submit as many times as you wish. Be aware of the fact that close to the deadline the server may slow down to handle many submissions, so try to finish early. There will be a separate submission for each problem - it makes it easier to grade each problem, and to provide you with the feedback for each problem you work on.

Remember that you should avoid accessing fields of fields and using any type-checkers. Design your methods systematically using the Design Recipe as we have been doing in class!

As always, you may only use techniques that have been covered in lectures so far in your solutions.
<br/><br/>
You will submit this assignment in two parts:

### Part 1: Submit your data definitions, examples and tests for the methods. You should include stubs for the methods themselves. Although we would not expect the tests to pass yet, your code should compile. This will be partly graded on the completeness of your test cases. You may add examples if there are cases you want to test that are not covered by the examples described below. Please add a comment for each test case describing what you are testing.

After part 1 of Assignment 2, there is a self-eval assignment. This will give you a chance to review your own work. This will also help the TAs with grading because it will ask you to tag lines of code that we will be looking for (in this case, tests).

The self-eval will show up in handins after the deadline for part 1 has passed: at 10pm Monday night. Then you will have 24 hours to complete it.

Important: you will no longer be able to submit part 1 after clicking on the self-eval. Make sure you have submitted your final version of the assignment before doing the self-eval!

### Part 2: Submit everything from Part 1 but this time include complete implementations for the methods (including any helpers needed).

Due Dates:
Part 1: Monday, January 22nd, 9:00 pm (with self-eval due Tues by 10pm)
Part 2: Thursday, January 25th, 9:00 pm

## Problem 1: A Picture is worth a thousand words
Define the file Pictures.java that will contain the entire solution to this problem.

For this problem, you’re going to implement a small fragment of the image library you’ve been using for Fundies 1 and 2. Each picture is either a single Shape or a Combo that connects one or more pictures. Each Shape has a kind, which is a string describing what 
simple shape it is (e.g., "circle" or "square"), and a size. (For this problem, we will simplify and assume that each simple shape is as tall as it is wide.) A Combo has two fields: a name describing the resulting picture, and an operation which specifies how this
image was put together.

There are three kinds of operations: Scale (has a single picture to scale twice as large as the original), Beside (has two pictures to draw: picture1 to the left of picture2), and Overlay (has two pictures to draw top-picture on top of bottom-picture, with their 
centers aligned). Sketch the class diagrams for the classes and interfaces you will need to represent the information above. (You can draw this on paper, or in ASCII art as a comment in your submitted file. You do not need to hand this in, but doing this step will 
help clarify how the interfaces and classes are related to each other.)

Design the classes (and interfaces) needed to represent the given information.

In the ExamplesPicture class define example data that represents the following images (the colors don’t matter; they’re just for illustration here):

A circle - image, a single circle of size 20
A square - image, a single square of size 30
A big circle - image, the result of scaling circle
A square on circle - image
A doubled square on circle - image

You should define each picture by its name (e.g. square or bigCircle). Any combo image should use the description text given above as its description. Our test program will use this data to test your methods.

- Add to your examples one more example of picture for each of the possible operations. Do not modify the existing pictures.
- Design the method getWidth that computes the overall width of this picture.
  Hint: follow the design recipe... working through examples really helps.

- Design the method countShapes that computes the number of single shapes involved in producing the final image.
  Note: Make sure you count every shape each time it is used.

- Design the method comboDepth, that computes how deeply operations are nested in the construction of this picture. For example, the comboDepth of the last example picture above is 3.
- Design the method mirror. This should produce a picture like this one where it leaves the entire picture unchanged, except Beside combos, which should have their two sub-images flipped (all names can remain untouched). This mirroring should happen the entire way down the image.
- Tricky! Design the method pictureRecipe that takes an integer depth, and produces a String representing the contents of this picture, where the recipe for the picture has been expanded only depth times. For example, the pictureRecipe at depth 0 for the last example image above is
- "doubled square on circle", at depth 2 is "beside(overlay(square, big circle), overlay(square, big circle))", and at depth 3 or more is "beside(overlay(square, scale(circle)), overlay(square, scale(circle)))".
  In more detail: invoking pictureRecipe on a Combo produces its name if the given depth is less than or equal to 0, and the formula of its contents (at that depth) otherwise.

Hint: If you get stuck, you may want to use a wish list to determine subproblems that may be of use to you, and that you can delegate to when needed.
