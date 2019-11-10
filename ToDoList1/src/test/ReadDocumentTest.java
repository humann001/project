package test;

import org.junit.Test;
import work.command.Process;
import work.document.ReadDocument;

import static org.junit.Assert.*;

public class ReadDocumentTest {

    @Test
    public void testInputTask() {
        ReadDocument readDocument = new ReadDocument("todotask.xml");
        readDocument.inputTasks();
    }

    @Test
    public void testInputTaskParametrs() {
        ReadDocument readDocument = new ReadDocument("todotask.xml");
        readDocument.inputTasks("Status", "done");
    }

    @Test
    public void testAddNew() {
        ReadDocument readDocument = new ReadDocument("todotask.xml");
//        readDocument.addNewTask("adasd", "sadasd", "sad", "asd");
    }

    @Test
    public void testEdit() {
        ReadDocument readDocument = new ReadDocument("todotask.xml");
        readDocument.edit("7", "asdasd", "sds");
    }

    @Test
    public void testRemove() {
        ReadDocument readDocument = new ReadDocument("todotask.xml");
        readDocument.remove("3");
    }

}