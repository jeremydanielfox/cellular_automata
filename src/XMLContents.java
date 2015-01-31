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

public class XMLContents {
	private File myFile;
	private String myModel;
	private String myAuthor;
	private String myTitle;
	private Map<String, Double> myParameters;
	private List<ConfigCellInfo> cellsToConfigure;
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

		myModel = extractSpecifiedTag("Model");

		myAuthor = extractSpecifiedTag("Author");

		myTitle = extractSpecifiedTag("Title");

		myParameters.put("rows",
				Double.parseDouble(extractSpecifiedTag("GridRows")));

		myParameters.put("columns",
				Double.parseDouble(extractSpecifiedTag("GridColumns")));

		extractConfig();
		extractParams();

	}

	private void extractParams() {
		System.out.println("ExtractingParams");
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
							System.out.println(tag + " " + param);
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
		System.out.println("ExtractingCellsToConfig");
		NodeList configList = myDoc.getElementsByTagName("Configuration");
		Element firstConfig = (Element) configList.item(0);
		for (int i = 0; i < configList.getLength(); i++) {
			Node node = configList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) node;

				NodeList configCells = elem.getElementsByTagName("Cell");

				for (int m = 0; m < configCells.getLength(); m++) {
					int row = Integer.parseInt(configCells.item(m)
							.getAttributes().getNamedItem("x").getNodeValue());
					int col = Integer.parseInt(configCells.item(m)
							.getAttributes().getNamedItem("y").getNodeValue());
					String state = (configCells.item(m).getAttributes()
							.getNamedItem("state").getNodeValue());
					cellsToConfigure.add(new ConfigCellInfo(row, col, state));
					System.out.println(row + " " + col + " " + state);
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

	private String extractSpecifiedTag(String tag) {
		NodeList myList = myDoc.getElementsByTagName(tag);
		return myList.item(0).getChildNodes().item(0).getNodeValue();
	}

	public String getModel() {
		return myModel;
	}
}
