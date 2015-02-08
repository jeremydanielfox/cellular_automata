import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Exceptions.CellSocietyException;
import Graphs.ConfigCellInfo;

/**
 * This class takes in an XML file and parses the file into different fields
 * based on the expected contents/tags in the file. It then stores that
 * information and hands it out to other classes who ask for it.
 * 
 * @author sierrasmith95
 *
 */
public class XMLContents {
	private File myFile;
	private String myModel;
	private String myAuthor;
	private String myTitle;
	private String myGraphType;
	private String myEdgeType;
	private String myGridLines;
	private String myRandomConfig;
	private Map<String, Double> myParameters;
	private List<ConfigCellInfo> cellsToConfigure;
	private Map<String, Double> myInitialProportions;
	private Document myDoc;

	public XMLContents(File file) throws ParserConfigurationException,
	SAXException, IOException {
		myFile = file;
		cellsToConfigure = new ArrayList<>();
		myParameters = new HashMap<>();
		readXML();
	}

	public void readXML() throws ParserConfigurationException, SAXException,
	IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		myDoc = builder.parse(myFile);
		try{
			myModel = extractSpecifiedTag("Model");
			myParameters.put("rows",
					Double.parseDouble(extractSpecifiedTag("GridRows")));

			myParameters.put("columns",
					Double.parseDouble(extractSpecifiedTag("GridColumns")));
		}catch(NullPointerException e){
			throw new CellSocietyException(CellSocietyException.MISSING_INFO_MESSAGE);
		}
		readAuthor();
		readTitle();
		readGraphType();
		readEdgeType();
		readGridLines();
		extractConfig();
		extractParams();
		extractInitialProportions();
		readRandomTag();
		readGridLines();
	}

	//private void setSpecifiedVariable(String variable, )

	private void extractInitialProportions() {
		myInitialProportions = new HashMap<String, Double>();
		
		//do this just like extracting params

	}

	private void readGridLines(){
		try{
			myGridLines = extractSpecifiedTag("GridLines");
		}catch(NullPointerException e){
			myGridLines = "Off";
		}
	}

	private void readRandomTag(){
		try{
			myRandomConfig = extractSpecifiedTag("Random");
		}catch(NullPointerException e){
			myRandomConfig = "NO";
		}
	}

	private void readGraphType(){
		try{
			myGraphType = extractSpecifiedTag("GraphType");
		}catch(NullPointerException e){
			myGraphType = "FourNeighborSquareGraph";
		}
	}

	private void readEdgeType(){
		try{
			myEdgeType = extractSpecifiedTag("EdgeType");
		}catch(NullPointerException e){
			myEdgeType = "Finite";
		}
	}

	private void readAuthor(){
		try{
			myAuthor = extractSpecifiedTag("Author");
		}catch(NullPointerException e){
			myAuthor = "User";
		}
	}

	private void readTitle(){
		try{
			myTitle = extractSpecifiedTag("Title");
		}catch(NullPointerException e){
			myTitle = myModel;
		}
	}

	private void extractParams() {
		NodeList paramList = myDoc.getElementsByTagName("Parameters");
		if (paramList.getLength() != 0) {
			Element firstParam = (Element) paramList.item(0);
			for (int k = 0; k < paramList.getLength(); k++) {
				Node node = paramList.item(k);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) node;
					NodeList specificParams = elem.getChildNodes();
					for (int q = 0; q < specificParams.getLength(); q++) {
						Node paramNode = specificParams.item(q);
						if (paramNode.getNodeType() == Node.ELEMENT_NODE) {
							Element paramElem = (Element) paramNode;
							String tag = paramElem.getTagName();
							double param = Double.parseDouble(paramElem
									.getChildNodes().item(0).getNodeValue());
							myParameters.put(tag, param);
						}
					}
				}
			}
		}
	}

	public Map<String, Double> getParams() {
		return myParameters;
	}

	private void extractConfig() {
		NodeList configList = myDoc.getElementsByTagName("Configuration");
		if (configList.getLength() != 0) {
			Element firstConfig = (Element) configList.item(0);
			for (int i = 0; i < configList.getLength(); i++) {
				Node node = configList.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) node;
					NodeList configCells = elem.getElementsByTagName("Cell");
					if (configCells.getLength() != 0) {
						for (int m = 0; m < configCells.getLength(); m++) {
							int row = Integer.parseInt(configCells.item(m)
									.getAttributes().getNamedItem("x")
									.getNodeValue());
							int col = Integer.parseInt(configCells.item(m)
									.getAttributes().getNamedItem("y")
									.getNodeValue());
							String state = (configCells.item(m).getAttributes()
									.getNamedItem("state").getNodeValue());
							cellsToConfigure.add(new ConfigCellInfo(row, col,
									state));
						}
					}
				}
			}
		}
	}

	public List<ConfigCellInfo> getCellsToConfig() {
		return cellsToConfigure;
	}

	public String getTitle() {
		return myTitle;
	}

	public String getAuthor() {
		return myAuthor;
	}

	public String getEdgeType(){
		return myEdgeType;
	}

	public String getGraphType(){
		return myGraphType;
	}

	public String getGridLines(){
		return myGridLines;
	}

	private String extractSpecifiedTag(String tag) {
		NodeList myList = myDoc.getElementsByTagName(tag);
		String result = myList.item(0).getChildNodes().item(0).getNodeValue();
		System.out.println(result);
		return result;
	}

	public String getModel() {
		return myModel;
	}

	public String getRandomConfig(){
		return myRandomConfig;
	}
	
	public Map<String, Double> getInitialProportions(){
		return myInitialProportions;
	}
}
