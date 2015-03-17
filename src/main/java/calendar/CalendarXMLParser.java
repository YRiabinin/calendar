package calendar;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ZGoryn
 * Date: 12.03.15
 * Time: 22:57
 * To change this template use File | Settings | File Templates.
 */
public class CalendarXMLParser {
    private ClassLoader loader = this.getClass().getClassLoader();

    private void createFile() {
        File file = new File(loader.getResource("calendar.xml").getFile());
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -calendar.get(Calendar.MONTH));

            Document doc = getNewDocument();
            Element rootElement = doc.createElement("year");
            doc.appendChild(rootElement);

            Element selectedMonth = doc.createElement("selected");
            rootElement.appendChild(selectedMonth);

            for (int i = 0; i < 12; i++) {
                Element month = doc.createElement("month");
                String name = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
                month.setAttribute("id", name);
                rootElement.appendChild(month);
                for (Integer dayNum = 1; dayNum <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH); dayNum++) {
                    Element day = doc.createElement("day");
                    day.appendChild(doc.createTextNode("0"));
                    day.setAttribute("date", dayNum.toString());
                    month.appendChild(day);
                }
                calendar.add(Calendar.MONTH, 1);
            }
            editXML(file, doc, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, Integer> getDays(String monthName) {
        Map<Integer, Integer> days = new TreeMap<>();

        File file = new File(loader.getResource("calendar.xml").getFile());
        synchronized (file) {
            if (!file.exists()) {
                createFile();
            }
        }

        Document doc = getParseDocument(file);
        Node year = doc.getFirstChild();

        NodeList monthList = year.getChildNodes();
        for (int i = 0; i < monthList.getLength(); i++) {
            Node month = monthList.item(i);
            if ((month instanceof Element) && month.getNodeName().equals("month")) {
                NamedNodeMap monthAttrList = month.getAttributes();
                Node monthAttr = monthAttrList.getNamedItem("id");
                if (monthAttr.getNodeValue().equals(monthName)) {
                    NodeList nodeList = month.getChildNodes();
                    for (int j = 0; j < nodeList.getLength(); j++) {
                        Node day = nodeList.item(j);
                        if (day instanceof Element) {
                            NamedNodeMap attrList = day.getAttributes();
                            Node attr = attrList.getNamedItem("date");
                            if (day.getNodeName().equals("day") && day.getTextContent() != null && day.getTextContent() != "false") {
                                days.put(Integer.parseInt(attr.getTextContent()), Integer.parseInt(day.getTextContent()));
                            }
                        }
                    }
                    break;
                }
            }
        }
        return days;
    }

    public synchronized void selectDay(String monthName, Integer dayNum, boolean value) {
        File file = new File(loader.getResource("calendar.xml").getFile());
        if (!file.exists()) {
            createFile();
        }

        Document doc = getParseDocument(file);
        Node year = doc.getFirstChild();
        monthListIterate(year.getChildNodes(), monthName, dayNum, value);
        if (editXML(file, doc, false)) {
            CalendarEntity calendarEntity = CalendarEntity.getCalendarEntity();
            calendarEntity.editDay(monthName, dayNum);
        }
    }

    public synchronized void selectMonth(String monthName) {
        File file = new File(loader.getResource("calendar.xml").getFile());
        if (!file.exists()) {
            createFile();
        }

        Document doc = getParseDocument(file);
        Node selected = doc.getElementsByTagName("selected").item(0);
        selected.setTextContent(monthName);

        if (editXML(file, doc, false)) {
            CalendarEntity calendarEntity = CalendarEntity.getCalendarEntity();
            calendarEntity.selectMonth(monthName);
        }
    }

    public boolean isSelected(String monthName) {
        File file = new File(loader.getResource("calendar.xml").getFile());
        if (!file.exists()) {
            createFile();
        }

        Document doc = getParseDocument(file);
        Node selected = doc.getElementsByTagName("selected").item(0);
        return selected.getTextContent().equals(monthName) ? true : false;
    }

    private void monthListIterate(NodeList monthList, String monthName, Integer dayNum, boolean value) {
        for (int i = 0; i < monthList.getLength(); i++) {
            Node month = monthList.item(i);
            if ((month instanceof Element) && month.getNodeName().equals("month")) {
                NamedNodeMap monthAttrList = month.getAttributes();
                Node monthId = monthAttrList.getNamedItem("id");

                if (monthId.getNodeValue().equals(monthName)) {
                    NodeList nodeList = month.getChildNodes();
                    daysListIterate(nodeList, dayNum, value);
                }

            }
        }
    }

    private void daysListIterate(NodeList nodeList, Integer dayNum, boolean value) {
        for (int j = 0; j < nodeList.getLength(); j++) {
            Node day = nodeList.item(j);

            if (day instanceof Element) {
                NamedNodeMap attrList = day.getAttributes();
                Node attr = attrList.getNamedItem("date");
                if (attr.getTextContent().equals(dayNum.toString())) {
                    day.setTextContent(value ? "1" : "0");
                }
            }

        }
    }

    private boolean editXML(File file, Document doc, boolean isCreate) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            if (isCreate) {
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            }
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
            return false;
        } catch (TransformerException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private Document getNewDocument() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Document doc = null;

        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            doc = documentBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return doc;
    }

    private Document getParseDocument(File file) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Document doc = null;

        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            doc = documentBuilder.parse(file);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return doc;
    }
}
