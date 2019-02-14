## Introduction: Describe the lab in your own words 
A bibliography manager. Allows the user to add information about books and articles and can print it in tbe BibTex format.
     
## Where in this design is there an example of aggregation?
the book and article classes are both part of a aggregate class with the reference class where the reference class is 
the master to the book and article

## Where in this design are there static methods?
the only static methods are in the driver class.
     
## Why is the myuniqueid attribute read only?
the unique id is read only because we dont want it to change onece a reference object has been intialized.
     
## Summarize what you learned during this lab
- Class aggregation
- Inheritance through the *extends* keyword
     
## Things you liked about the lab or suggestions for improvement
add a tests file so it is easy to tell if every method is functioning as intended