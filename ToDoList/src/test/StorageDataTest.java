package test;

import org.junit.Test;
import work.document.StorageData;
import static org.junit.Assert.*;

public class StorageDataTest {

    String fileName = "todotask.xml";

    @Test
    public void getDataStorage() {
        StorageData storageData = new StorageData(fileName);
        storageData.getDataStorage();
    }

    @Test
    public void getListId() {
        StorageData storageData = new StorageData(fileName);
        storageData.getListId();


    }

    @Test
    public void getFreeId() {
        StorageData storageData = new StorageData(fileName);

    }

    @Test
    public void dataReplacement() {
        StorageData storageData = new StorageData(fileName);
    }

    @Test
    public void addData() {
        StorageData storageData = new StorageData(fileName);
    }

    @Test
    public void removeData() {
        StorageData storageData = new StorageData(fileName);
    }
}