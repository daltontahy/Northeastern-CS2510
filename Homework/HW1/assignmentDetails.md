### Assignment 1: Designing complex data
## Goals: Practice designing the representation of complex data.

# 1.1 Instructions
Be very, very careful with naming! The solution files expect your submissions to be named a certain way, so that they can define their own Examples class with which to test your code. Therefore, whenever the assignment specifies:
the names of classes, the names and types of the fields within classes, the names, types and order of the arguments to the constructor, or filenames, ...be sure that your submission uses exactly those names. Additionally, make sure you follow the course style guidelines. 
For now the most important ones are: using spaces instead of tabs, indenting by 2 characters, following the naming conventions (data type names start with a capital letter, interfaces begin with an uppercase I, names of fields and methods start with a lower case letter), 
and having spaces before curly braces.

You will submit this assignment by the deadlines using the course handin server. Follow A Complete Guide to the Handin Server for information on how to use the handin server. You may submit as many times as you wish. Be aware of the fact that close to the deadline the 
server may slow down to handle many submissions, so try to finish early. There will be a separate submission for each problem - it makes it easier to grade each problem, and to provide you with the feedback for each problem you work on.

Due Date: Thursday, January 18th, 9:00 pm

## Problem 1: Problem: Statues
# Design a class Statue to represent the following information about statues:

name: the name of the statue, as a String
artist: to be represented as a String
year: when the statue was made int
weight: the weight the statue in pounds, represented as a double
whole: a boolean representing whether or not the statue still has all of its parts

Make at least three examples of instances of this class, in the class ExamplesStatue. Two of the examples should be objects named statueOfLiberty and venusDeMilo and should represent the following two statues:

Statue Of Liberty, designed by Bartholdi, 1886, 450,000 pounds, whole
Venus de Milo, designed by Alexandros of Antioch, 115 BC, 1984.16 pounds, missing arms

Note: If the year 2024 is represented as 2024, and 1886 as 1886, what number makes sense to use for 115 BC?

What to submit
You should submit your data definitions and examples in a file named Statues.java

Remember to check the feedback from the Style and Checker tests in handins!

## Problem 2: Problem: Soups
# Here is a data definition in DrRacket:

;; A Soup is one of:
;; -- Broth
;; -- Ingredient
 
;; A Broth is a (make-broth String)
(define-struct broth [type])
 
;; An Ingredient is a (make-ingredient Soup String)
(define-struct ingredient [more name])
 
Draw the class diagram that represents this data definition. You may draw this as ASCII-art and include it in your submission, if you wish. Or you can just draw it on paper and not submit it. Regardless, we think it will help you in visualizing how the data is organized.

Convert this data definition into Java. Make sure you use the same names for data types and for the fields, as are used in the DrRacket data definitions, converted into Java style standards. Make sure that the constructor arguments are given in the same order as shown.

Include in your examples the following soups:

– a "chicken" soup with "carrots" and "celery" and "noodles"
– a "vanilla" soup with "horseradish" and "hot dogs" and "plum sauce"

Make sure the two sample soups given above are named yummy and noThankYou.
Name the class that holds the examples of your soup data ExamplesSoup.

What to submit
You should submit your data definitions and examples in a file named Soups.java

Remember to check the feedback from the Style and Checker tests in handins!

## Problem 3: Traveling
# We’ve been asked to help build a new medieval adventure game, Summer Is Coming. We’re trying to figure out the gameplay mechanics, so we’re starting with representations for travel around the game world. Players can live in three types of housing: a Hut, an Inn, and a Castle.

A Hut has a capacity and the current count of its population. The population must be less than the capacity.
An Inn has a name, capacity and the current count of its population as well as the number of stalls in its stable. The population must be less than the capacity.
A Castle has a name, the family-name of the owners, the current count of its population as well as the number of carriages it can hold in its carriage-house.

There are 2 types of transportation in this game:

- Horse
- Carriage

Each type of transportation should have a from and a to housing. Horses also have a name and a color (which you may represent using the color’s name). They can only go to an inn if there is room in the stables, but they can go to any hut or castle.

Carriages can only carry a limited supply of tonnage and only travel from Inns to Castles or vice versa. When they go to a Castle there must be room for them in the carriage house.

Define six examples of housing, including:

hovel: Capacity 5, population 1
winterfell: Named "Winterfell", family name "Stark", population 500, can hold 6 carriages
crossroads: Named "Inn At The Crossroads", capacity 40, population 20, 12 stalls

The others can be whatever you wish.
Define four types of travel, two of each kind.

Name your examples horse1, carriage2, etc., and your examples class ExamplesTravel.

We’re placing a lot of restrictions on the data, such as the population being less than capacity, possible destinations of carriages, etc. However we aren’t (yet) actually enforcing these in the code. The ways to enforce these constraints will be further explored later in the semester. For now, you are expected to create examples that conform to these constraints.

What to submit

You should submit your data definitions and examples in a file named Travel.java
Remember to check the feedback from the Style and Checker tests in handins!
