package work.document;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Storage {

    private String nameFile;
    private String id;
    private String freeId;
    private ArrayList<String> listId = new ArrayList<>();
    private Map<String, Map<String, String>> dataStorage;
    private NodeList tasks;
    private Node root;
    private Document document;

    public Storage(String nameFile) {
        this.nameFile = nameFile;
        dataParsing();
    }

    public Map<String, Map<String, String>> getDataStorage() {
        return dataStorage;
    }

    public ArrayList<String> getListId() {
        return listId;
    }

    public String getFreeId() {
        for (int i = 1; i <= listId.size() + 1; i++) {
            if (!listId.contains(String.valueOf(i))) {
                freeId = String.valueOf(i);
                break;
            }
        }
        return freeId;
    }

    private void dataParsing() {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            Document document = documentBuilder.parse(nameFile);
            this.document = document;

            Node root = document.getDocumentElement();
            this.root = root;

            NodeList tasks = root.getChildNodes();
            this.tasks = tasks;

            Map<String, Map<String, String>> dataStorage = new HashMap<>();

            for (int i = 0; i < tasks.getLength(); i++) {

                Node task = tasks.item(i);

                if (task.getNodeType() != Node.TEXT_NODE) {

                    String idTask = null;

                    if (task.getAttributes().getLength() != 0) {
                        idTask = task.getAttributes().getNamedItem("id").getNodeValue();
                        listId.add(idTask);
                        Map<String, String>  elementStorage = new LinkedHashMap<>();
                        elementStorage.put(task.getAttributes().getNamedItem("caption").getNodeName(), task.getAttributes().getNamedItem("caption").getNodeValue());
                        dataStorage.put(idTask, elementStorage);
                    } else {
                        return;
                    }

                    NodeList taskProps = task.getChildNodes();

                    for (int j = 0; j < taskProps.getLength(); j++) {

                        Node taskProb = taskProps.item(j);

                        if (taskProb.getNodeType() != Node.TEXT_NODE) {
                            dataStorage.get(idTask).put(taskProb.getNodeName(), taskProb.getTextContent());
                        }
                    }
                }
            }

            this.dataStorage = dataStorage;

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }

    }


    public void dataReplacement() {
        for (int i = 0; i < tasks.getLength(); i++) {
            for (Map.Entry<String, Map<String, String>> entry : dataStorage.entrySet()) {
                if (tasks.item(i).getNodeType() != Node.TEXT_NODE) {
                    if (entry.getKey().equals(tasks.item(i).getAttributes().getNamedItem("id").getTextContent())) {

                        for (Map.Entry<String, String> entry1 : entry.getValue().entrySet()) {
                            if (entry1.getKey().equals("caption")) {
                                tasks.item(i).getAttributes().getNamedItem("id").setTextContent(entry.getKey());
                                tasks.item(i).getAttributes().getNamedItem("caption").setTextContent(entry1.getValue());
                            }
                            else {
                                for (int j = 0; j < tasks.item(i).getChildNodes().getLength(); j++) {
                                    Node node = tasks.item(i).getChildNodes().item(j);
                                    if (entry1.getKey().equals(node.getNodeName())) {

                                        tasks.item(i).getChildNodes().item(j).setTextContent(entry1.getValue());

                                    }
                                }
                            }
                        }
                    }
//                    root.appendChild(tasks.item(i));
                }
            }

        }
        writeDocument(document);
    }

    public void addData(String id) {
        Element element = document.createElement("Task");
        element.setAttribute("id", id);

        for (Map.Entry<String, String> entry : dataStorage.get(id).entrySet()) {
            if (entry.getKey().equals("caption"))
                element.setAttribute("caption", entry.getValue());
            else {
                Element element1 = document.createElement(entry.getKey());
                element1.setTextContent(entry.getValue());
                element.appendChild(element1);
            }
        }
        listId.add(id);
        root.appendChild(element);
        writeDocument(document);
    }


    public void removeData(String id) {
        for (int i = 0; i < tasks.getLength(); i++) {
            if (tasks.item(i).getNodeType() != Node.TEXT_NODE) {
                String s = root.getChildNodes().item(i).getAttributes().getNamedItem("id").getNodeValue();
                if (id.equals(s)) {
                    Element element1 = (Element) root.getChildNodes().item(i);
                    root.removeChild(element1);
                }
                dataStorage.remove(id);
            }
        }
        writeDocument(document);
    }

    private void writeDocument(Document document) throws TransformerFactoryConfigurationError {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream(nameFile);
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
    }

}
