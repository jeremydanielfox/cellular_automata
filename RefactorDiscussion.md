Team: Sierra Smith and Kevin He

We are refactoring the contructors for the model classes.  Currently, each constructor gets the "stateToInt" and "stateToColor" map from the abstract base class and puts in it's own strings and and colors and integers, which are hard coded in each model class.  While the constants are unique to the models and can't change, the structure of the constructor of each model is repeated.  

We decided to fix this section of duplicated code because the list of duplicated code we had wasn't very long, and we felt that there were minor but critical differences in the other sections of duplicated code that prevented us from easily extracting or condensing methods.  

To fix this, we are going to create 3 Lists: a list of string values, a list of colors, and an list of integers, and pass them to the super contructor to add them to their respective maps for us.  This will get rid of duplicated code by extracting the code to a method in the parent class.  Technically the 3 lines of code that will create these three lists will be duplicated in structure, but we have to create these lists somewhere and each model has different constants that it puts into the list.  
