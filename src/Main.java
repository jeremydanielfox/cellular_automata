
/**
 * This is the main program, it is basically boilerplate to create an animated scene.
 *
 * @author Robert C. Duvall
 */
public class Main {
    public static void printNeighbors(BaseGraph myGraph) {
    	Iterable<Cell> mySet = myGraph.getAllCells();
    	for (Cell current:mySet)
    		System.out.println(current.getID());
    	for (Cell current: mySet){
    		System.out.println(current.getID());
    		for (Cell other:current.getNeighbors()) {
    			if (other!=null)
    			System.out.println(other.getID())	;
    		}
    		System.out.println();
    	}
    }
    public static void main (String[] args) {

        BaseGraph tester = new SquareGraph(2,2,100,100);
        
    	printNeighbors(tester);
    }
}
