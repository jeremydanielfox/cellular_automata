# cellsociety
Empty repository for CellSociety project

Names: Jeremy Fox, Megan Gutter, Sierra Smith

Date Started: January 22, 2015

Date Finished: February 8, 2015

Hours Worked: about 70 hrs each

Roles: We all collaborated on all aspects of the project, but Jeremy was mainly responsible for the graph implementation, Sierra was mainly responsible for the XML parsing/visual implementation, and Megan was mainly responsible for the model implementations. We met often to code/work through bugs together and make decisions. 

Resources: Java Docs, Oracle Docs, stackoverflow, http://www.java2s.com/Tutorial/Java/0261__2D-Graphics/Convertsagivenstringintoacolor.htm
http://code.makery.ch/java/javafx-8-tutorial-part5/
http://www.w3schools.com/dom/dom_nodes.asp
http://examples.javacodegeeks.com/core-java/xml/java-xml-parser-tutorial/
http://blog.codinghorror.com/understanding-model-view-controller/

Class Containing Main: SimBrain

Test Files: Located in "Demo_files"

Using the program: After running the main method in SimBrain, click on the "Upload XML File" and choose one of the XML files located in the "Demo_files" folder. The initial configuration will be loaded on the screen. To start the simulation either press "play" or "step". 

To change the starting configuration of any model using the XML, change these tags to any of the given options (in the ommission of a tag the default states will be implemented:

* GraphType: can be FourNeighborSquareGraph, FourNeighborTriangleGraph, EightNeighborTriangleGraph, or EightNeighor
* EdgeType: can be Toroidal or Finite
* Model: can be GameOfLife, Fire, Segregation, WaTorWorld, or Sugarscape
* Author: your name
* Title: your title
* GridRows: put the number of rows you want in the grid
* GridColumns: put the number of columns you want in the grid
* Random (optional, if you want a completely random configuration): YES
* RandomWithParams (optional, if you want to set proportions of random configuration): create a subtag using the name of the state with the proportion in decimal form (example: <tree>0.5</tree>)
* Parameters (optional): create a subtag using the name of the parameter containing its value (example: <probCatch>0.5</probCatch>). The parameters you can input for each model are as follows.
    * Fire: probCatch
    * Segregation: HappinessRatio
    * WaTorWorld: energyLevel, timeTillReproduce
    * Sugarscape: numAgents
* Configuration: create a subtag called Cell with the attributes x, y, and state for the row, column, and intitial state respectively (example: <Cell x="1" y="4" state="group_two"> </Cell>).  The states you can input for each model are as follows.
    * GameOfLife: alive, dead
    * Fire: burning, tree, empty
    * Segregation: empty, group_one, group_two
    * WaTorWorld: water, fish, shark
    * Sugarscape: integer representing the maximum sugar level a cell can hold
* Color (optional, if you want to choose colors for states): create a subtag using the name of the state, as listed above, with the value being a color in all capital letters.



Note, completely random overrides random with parameters which overrides any specific configuration of cells.

Known bugs/problems with functionality: You have to literally slide the parameter sliders to make that parameter change, if you just click to a certain point on the slider it won't change.  In addition, the cells in the triangle grid do not match up perfectly; the simulation still works as normal, but it may look a little funky because the triangles aren't quiet lined up.

Extra Features: see closed issues on github

Impressions: 

We really enjoyed working on this project and telling all of our friends about the cool simulations we were building.  One challenge we had was that not being able to access the web pages with the rules for the simulations at the beginning of the project.  Because of that, we weren't able to properly anticipate all features of a simulation, but it wasn't a major issue.  We also think it would be helpful to learn a few more design features in depth, such as use of hierarchies, earlier in the project so that we can implement a better design from the start rather than having so many design changes.
