# Refactoring Cell Society to include lambda expressions

We refactored the BaseGraph and SimEngine classes to use a lambda expression inside a .forEach() method. Previously, we had been passing an Iterable of all the Cells in the graph, iterating over them in the model, and calling a function on them in the Model, but now, the Graph can iterate over all of them and use a lambda given to it by the model. This represents a much more encapsulated way to manipulate the graph, as the model no longer needs to receive all the Cells. 

### With Love, Jeremy, Sierra and Megan