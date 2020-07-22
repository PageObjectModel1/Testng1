package utils;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.List;

public class ORReading
{
    public MultiMap locData;

    // TODO: 15-07-2020 - debug and analysit 
    public ORReading()
    {
        locData = new MultiValueMap();
    }

    public void loadFromXML(String fileName)
    {
        String key,type,text;

        try {
            File fXmlFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            NodeList nList = doc.getElementsByTagName("entry");
            for(int itr=0;itr<nList.getLength();itr++){
                Node nNode= nList.item(itr);
                Element eElement = (Element) nNode;
                key=eElement.getAttribute("key");

                type = eElement.getAttribute("type");
                locData.put(key, type);

                text= eElement.getTextContent();
                locData.put(key, text);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Element not found "+e.getMessage());
        }
    }

    public String getPropertyType(String key)
    {
        return((List<?>) locData.get(key)).get(0).toString();
    }


    public String getPropertyValue(String key)
    {
        return((List<?>) locData.get(key)).get(1).toString();
    }
}
