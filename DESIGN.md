# Introduction
 Sierra
# Overview
 Sierra
# User Interface
 Megan
# Design Details

In this section, we will describe each component introduced in the Overview, along with how these components work with each other and handle features given in the assignment specification.

###### SimBrain

We knew that, in order to follow the MVC format, we would need a controller class responsible for connecting the model and view classes. In our design, the SimBrain class is our controller. As such, it is responsible for loading and reading the XML file and sending the appropriate information to the model and view classes. It is also responsible for the managing the timeline, which means it can run the simulation indefinitely until the user closes the window or uploads a new XML file. The SimBrain is responsible for the overall simulation, visually updating each frame in the animation. Finally, it contains the methods needed to pause, resume, and change the simulation rate.

###### SimEngine

Although we have a class called BaseModel, in terms of the MVC framework, the SimEngine class is our model class, responsible for handling data manipulation and state calculations. We created this class to be able to run a simulation model given parameters that specified a specific model and grid layout. The actual model and parameters for the model are unknown to this class and therefore are not a dependency. This class is also responsible for combining the graph and the logic for the simulation models, which were kept independent in our program design. Finally, this class is responsible for updating cell states by calling methods from the models that contain the rules.

###### BaseModel

This class will contain the methods that will be implemented in specific models, such as criteria for considering cells neighbors and rules for updating cells. This class also makes it possible for the SimEngine to run without knowing what specific model the user is running, because the SimEngine can call methods from the BaseModel framework no matter which model is running.

###### Fire, Predator Prey, Segregation, Game of Life

These classes will each contain the implementation for the specific rules of each game. For instance, the Game of Life class will contain the rules and criteria necessary to run the Game Of Life game. These classes will also contain the different possible states for each game, which will be stored as constants within the classes.

###### ModelFactory

This class will be responsible for creating the model specified by the XML file. This class will use the new reflection abilities introduced in Java 8 to avoid creating large if-else trees.

###### GraphFactory

Similarly to the ModelFactory class, this class uses reflection to create a graph whose dimension and shape is specified by rules in the XML file.

###### BaseGraph

This is an abstract class whose purpose is to create a framework that can be called from SimEngine for any graph. This class will not extend Graph, but will rather contain an intance of a Graph. 

###### SquareGraph

This class will implement an algorithm to build a graph of cells with pointers to at most four other cells in order to build a "square" grid based on dimensions given by the user.

###### SimulationWindow

In terms of the MVC framework, this class represents our view. It is responsible for all of the visuals associated with our simulation, and as such, it contains the group, the scene, and the corresponding visual objects. It also contains the control panel with all of the buttons (pause, play, etc.) that the user presses to control the simulation (when buttons are pressed, methods are called in the brain to make the simulation changes).

# Design Considerations
 Megan
# Team Responsibilities
### High Level Plan
We plan to have every group member complete their primary portions early in each week so that we can then have the team members review their secondary portions the day after. We will test integrating various aspects as often as possible, we each group member trying to keep the master branch updated with their work. Group members should only ever commit fully functioning code to the master branch, which they will do after testing this code thoroughly. 

### Indvidual components
* Jeremy
	* Primary components
		* Cell
		* Base Graph
		* Square Graph 
	* Secondary components
		* Segregation
* Sierra
	* Primary components
		* SimWindow
		* SimBrain
	* Secondary components
		* Factories (reflection)
* Megan
	* Primary components
		* SimEngine
		* BaseModel
		* GameOfLife
	* Secondary components