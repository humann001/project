package work.document;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class StorageData {

    private String nameFile;
    private String freeId;
    private ArrayList<String> listId = new ArrayList<>();
    private Map<String, Map<String, String>> dataStorage = new HashMap<>();
    private NodeList tasks;
    private Node root;
    private Document document;
    private boolean fileExists = false;


    public StorageData(String nameFile) {
        this.nameFile = nameFile;

        File file = new File(nameFile);
        if (file.exists()) {
            dataParsing();
            fileExists = true;
        } else {
            System.out.println("Файл не существует");
            fileExists = false;
        }
    }

    public boolean isFileExists() {
        return fileExists;
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

            for (int i = 0; i < tasks.getLength(); i++) {

                Node task = tasks.item(i);

                if (task.getNodeType() != Node.TEXT_NODE && hasAttributes(task, "id") && hasAttributes(task, "caption")) {

                    String taskId = task.getAttributes().getNamedItem("id").getNodeValue();
                    Map<String, String>  elementStorage = new LinkedHashMap<>();
                    elementStorage.put(task.getAttributes().getNamedItem("caption").getNodeName(), task.getAttributes().getNamedItem("caption").getNodeValue());
                    dataStorage.put(taskId, elementStorage);

                    NodeList taskProps = task.getChildNodes();

                    listId.add(taskId);

                    for (int j = 0; j < taskProps.getLength(); j++) {
                        Node taskProb = taskProps.item(j);
                        if (taskProb.getNodeType() != Node.TEXT_NODE) {
                            dataStorage.get(taskId).put(taskProb.getNodeName(), taskProb.getTextContent());
                        }
                    }
                }
            }
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }

    }


    public void dataReplacement(String id, String nameParametr, String data) {
        for (int i = 0; i < tasks.getLength(); i++) {
            if (tasks.item(i).getNodeType() != Node.TEXT_NODE && hasAttributes(tasks.item(i), "id" ) && dataStorage.containsKey(id)) {
                String nameId = tasks.item(i).getAttributes().getNamedItem("id").getTextContent();
                dataStorage.get(id).put(nameParametr, data);
                if (nameId.equals(id)) {
                    if (nameParametr.equals("caption") && hasAttributes(tasks.item(i), "caption")) {
                        tasks.item(i).getAttributes().getNamedItem("caption").setTextContent(data);
                    } else {
                        for (int j = 0; j < tasks.item(i).getChildNodes().getLength(); j++) {
                            Node node = tasks.item(i).getChildNodes().item(j);
                            if (nameParametr.equals(node.getNodeName())) {
                                tasks.item(i).getChildNodes().item(j).setTextContent(data);
                            }
                        }
                    }
                }
            }
        }
        writeDocument(document);
    }

    public void addData(String id, Map<String, String> data) {
        dataStorage.put(id, data);
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
            if (tasks.item(i).getNodeType() != Node.TEXT_NODE && hasAttributes(tasks.item(i), "id")) {
                String s = root.getChildNodes().item(i).getAttributes().getNamedItem("id").getNodeValue();
                if (id.equals(s)) {
                    Element element1 = (Element) root.getChildNodes().item(i);
                    root.removeChild(element1);
                    dataStorage.remove(id);
                    listId.remove(id);
                }
            }
        }
        writeDocument(document);
    }

    private void writeDocument(Document document) throws TransformerFactoryConfigurationError {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream(nameFile);
            StreamResult result = new StreamResult(fos);
            transformer.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private boolean hasAttributes(Node value, String nameAttribute) {
        boolean check = false;
        for (int i = 0; i < value.getAttributes().getLength(); i++) {
            if (value.getAttributes().item(i).getNodeName().equals(nameAttribute)) {
                check = true;
            }
        }
        return check;
    }

}
