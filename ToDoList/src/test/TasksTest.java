package test;

import org.junit.Test;
import work.document.Tasks;

import static org.junit.Assert.*;

public class TasksTest {

    String fileName = "todotask.xml";

    @Test
    public void print() {
        Tasks tasks = new Tasks(fileName);
        tasks.print("Status", "new");
    }

    @Test
    public void printAll() {
        Tasks tasks = new Tasks(fileName);
        tasks.printAll();
    }

    @Test
    public void add() {
        Tasks tasks = new Tasks(fileName);
        tasks.add("Заголовок", "Описание", "33", "2019", "new", "2020");
    }

    @Test
    public void edit() {
        Tasks tasks = new Tasks(fileName);
        tasks.edit("3", "Priority", "44");
    }

    @Test
    public void remove() {
        Tasks tasks = new Tasks(fileName);
        tasks.remove("4");
    }

    @Test
    public void hasId() {

        Tasks tasks = new Tasks(fileName);

        assertTrue(tasks.hasId("5"));
        assertFalse(tasks.hasId("40"));

    }
}