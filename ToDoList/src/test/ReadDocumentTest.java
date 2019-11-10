package test;

import org.junit.Test;
import work.command.Process;
import work.document.ReadDocument;

import static org.junit.Assert.*;

public class ReadDocumentTest {


    String nameFile = "todotask1.xml";

    @Test
    public void testInputTask() {
        ReadDocument readDocument = new ReadDocument(nameFile);
        readDocument.inputTasks();
    }

    @Test
    public void testInputTaskParametrs() {
        ReadDocument readDocument = new ReadDocument(nameFile);
        readDocument.inputTasks("Status", "dfdf");
    }

    @Test
    public void testAddTask() {
        ReadDocument readDocument = new ReadDocument(nameFile);
//        readDocument.addNewTask("adasd", "sadasd", "sad", "asd");
    }

    @Test
    public void testEdit() {
        ReadDocument readDocument = new ReadDocument(nameFile);
        readDocument.edit("7", "asdasd", "sds");
    }

    @Test
    public void testRemove() {
        ReadDocument readDocument = new ReadDocument(nameFile);
        readDocument.remove("5");
    }

}