# Introduction

The goal of this project is to create a cellular automata simulator that can run a simulation based on any of a number of predefined models and user input from an XML file.  In designing this simulator, one of our main goals was to have as few dependencies as possible so that any changes we might need to make regarding design choices or data structures would be fairly isolated.  We also wanted to keep features and different logical parts of the program as independent as possible so they would be easily extendable or changeable.  Another part of our design goals is to make it very simple to add more models that the simulator can support.

There are four primary parts to the architecture of our program - the visuals/display component, the logic for the simulation, the component that links the simulation to the display window, and data structures and model rule implementations.  In keeping with our design goals, we separated the logic of the simulation and the visual display so that a change in one doesn't affect the other.  The logic that runs the simulation is completely unaware of the visualization component and vice versa.  Because these two pieces are independent, we have a component that links the two together.  

The element that links the display to the logic of the simulation is highly integrated with the visual component.  As a result, if any of the visuals were to change at some point, it would likely affect both components, making them both "open."  In contrast, the logic that runs the simulation should not change and is therefore mostly "closed."  Perhaps the one exception to this is that the logic of the simulation is dependent upon certain data structures, and would need to be modified if the data structures were drastically changed.

Finally, our design includes data structures and models that are used by other components to run the simulation, mainly the simulation logic component.  These structures are "closed for modification but open to extension" because they include basic properties and methods that are required to serve their purpose, but they don't implement any restrictive methods, and thus could easily be added on to and repurposed.  

# Overview

This program contains 4 main components: the visuals class, the class with the simulation logic, the class that links them together, and the data structure and model implementation classes.  Our program is divided this way in an effort to create minimal dependencies, so that any necessary changes in the future will be isolated, and so that each element is as open to extensions as possible.  

The PDF in the same folder as this document illustrates the relationship between each of the classes, further described below.

####SimBrain
This class creates and links the simulation logic, found in SimEngine, with the visual components of the simulator, implemented both in this class and in SimWindow.  It contains all of the action methods for the buttons in the control panel of the display that allow the user to control the animation of the simulation.  These methods are in this class because it is the only class with access to both the SimEngine and the SimWindow, and the buttons in the control panel make changes to both.  One of these buttons is the "Upload File" button,  and as a result the SimBrain also contains the method for reading a file and starting a new simulation.  

This class also contains the Timeline object that is responsible for the game loop and essentially running the simulation.  The Timeline is in this class because the method that is called to update each frame must call methods in both the SimEngine and SimWindow, which can only both be accessed through this class. 

Methods in this class:

 - public void main()
	 - displays the window for the simulation visuals and waits for user input
 - public void runSim()
	 - creates the Timeline and starts the animation
 - public void startNewSim()
	 - calls uploadFile() to have the use select a file, calls readFile(File) on the file to get a list of parameters, passes those parameters to a new SimEngine object, and calls runSim()
 - public File uploadFile()
	 - creates a FileChooser object and returns a file selected by the user or null of no file is created 
 - public List String readFile(File)
	 - reads the XML file and returns parameters from the file as a list of strings
 - public HBox makeControlPanel()
	 - creates buttons for the control panel, puts them in an HBox, and returns the HBox
 - public void incFrameRate()
	 - increases frame rate
 - public void decFrameRate()
	 - decreases frame rate
 - public void pauseAnimation()
	 - pauses the animation/timeline if it isn't already paused
 - public void playAnimation()
	 - resumes the animation if it isn't already playing
 - public void updateSim()
	 - this method is called each time the frame is updated
	 - calls the updateCells() method in the SimEngine and then calls paintCellsInSim() in the SimWindow, passing it a list of cells


####SimEngine

####SimWindow

####Cell

####BaseModel

####Model Classes (Fire, PredPray, Segregation, GameOfLife)

####ModelFactory

####BaseGraph

####SquareGraph

####GraphFactory
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