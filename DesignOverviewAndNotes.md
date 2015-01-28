### Design Overview and Notes
Make sure to use reflection!
#### Classes:
* Main
	* Creates SimulationEngine and calls "start"
* SimulationEngine
	* Call ModelFactory
	* Call GraphFactory
* Visuals
	* Potentially controls stage, scene.
	* Handles adding to group and updating group

* BaseModel
	* UpdateGraph(Graph) returns Graph
	* GameOfLife extends BaseModel
	* Fire extends BaseModel
	* Segregation extends BaseModel
	* PredatorPrey extends BaseModel
* Graph
	* Grid extends Graph
	* Initialize Cells method
	* Connect Cells method
		* Square extends Grid
		* Triangle extends Grid
		* Hexagon extends Grid
* Cell
	* On screen vertices
	* ID
	* Current state
	* Future state
	* Shape (probably uses polygon)
* ModelFactory
* GraphFactory


* Potential process...
	* Create a Graph
	* Create a Model
	* myGraph = BaseModel.updateGraph(myGraph);
	* Visuals takes in myGraph, does stuff with it