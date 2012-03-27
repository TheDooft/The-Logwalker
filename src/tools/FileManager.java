package tools;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import boss.Boss;

public class FileManager {
	public FileManager() {
	}

	static public ArrayList<Boss> readBossXml() {
		ArrayList<Boss> bossList = new ArrayList<Boss>();
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder;
			Document doc;

			docBuilder = docBuilderFactory.newDocumentBuilder();
			doc = docBuilder.parse(new File("./data/Boss.xml"));
			doc.getDocumentElement().normalize();

			NodeList bossNodeList = doc.getElementsByTagName("boss");
			for (int i = 0; i < bossNodeList.getLength(); i++) {
				Node bossNode = bossNodeList.item(i);

				if (bossNode.getNodeType() == Node.ELEMENT_NODE) {
					Element bossElement = (Element) bossNode;

					NodeList uidNodeList = bossElement
							.getElementsByTagName("uid");
					ArrayList<Integer> uidList = new ArrayList<Integer>();
					for (int j = 0; j < uidNodeList.getLength(); j++) {
						NodeList nlList = uidNodeList.item(j).getChildNodes();
						Node uidNode = (Node) nlList.item(0);
						uidList.add(Integer.parseInt(uidNode.getNodeValue()));
					}
					bossList.add(new Boss(
							getTagValue("name", bossElement),
							Integer.parseInt(getTagValue("idleEnd", bossElement)),
							getTagValue("name", bossElement), getTagValue(
									"name", bossElement), uidList));
				}

			}

		} catch (SAXParseException err) {
			System.out.println("** Parsing error" + ", line "
					+ err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println(" " + err.getMessage());

		} catch (SAXException e) {
			Exception x = e.getException();
			((x == null) ? e : x).printStackTrace();

		} catch (Throwable t) {
			t.printStackTrace();
		}
		return bossList;
	}

	private static String getTagValue(String sTag, Element eElement) {
		NodeList elementsByTagName = eElement.getElementsByTagName(sTag);
		if (elementsByTagName.getLength() == 0) {
			return null;
		}
		NodeList nlList = elementsByTagName.item(0).getChildNodes();

		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();
	}

}
