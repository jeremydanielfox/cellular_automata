# Introduction

The goal of this project is to create a cellular automata simulator that can run a simulation based on any of a number of predefined models and user input from an XML file.  In designing this simulator, one of our main goals was to have as few dependencies as possible so that any changes we might need to make regarding design choices or data structures would be fairly isolated.  We also wanted to keep features and different logical parts of the program as independent as possible so they would be easily extendable or changeable.  Another part of our design goals is to make it very simple to add more models that the simulator can support.

There are four primary parts to the architecture of our program - the visuals/display component, the logic for the simulation, the component that links the simulation to the display window, and data structures and model rule implementations.  In keeping with our design goals, we separated the logic of the simulation and the visual display so that a change in one doesn't affect the other.  The logic that runs the simulation is completely unaware of the visualization component and vice versa.  Because these two pieces are independent, we have a component that links the two together.  

The element that links the display to the logic of the simulation is highly integrated with the visual component.  As a result, if any of the visuals were to change at some point, it would likely affect both components, making them both "open."  In contrast, the logic that runs the simulation should not change and is therefore mostly "closed."  Perhaps the one exception to this is that the logic of the simulation is dependent upon certain data structures, and would need to be modified if the data structures were drastically changed.

Finally, our design includes data structures and models that are used by other components to run the simulation, mainly the simulation logic component.  These structures are "closed for modification but open to extension" because they include basic properties and methods that are required to serve their purpose, but they don't implement any restrictive methods, and thus could easily be added on to and repurposed.  

# Overview
 Sierra
# User Interface
 Megan
# Design Details
Jeremy
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
