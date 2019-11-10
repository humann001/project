package test;

import org.junit.Test;
import work.document.Storage;

import static org.junit.Assert.*;

public class StorageTest {

    @Test
    public void getDataStorage() {
        Storage storage = new Storage("todotask1.xml");
        storage.getDataStorage();
    }

    @Test
    public void getListId() {
    }

    @Test
    public void getFreeId() {
    }

    @Test
    public void dataReplacement() {
    }

    @Test
    public void addData() {
    }

    @Test
    public void removeData() {
    }
}