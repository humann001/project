package test;

import org.junit.Test;
import work.document.StorageData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class StorageDataTest {

    String fileName = "todotask.xml";
    String fileName2 = "todotask1.xml";

    @Test
    public void getDataStorage() {
        StorageData storageData = new StorageData(fileName);
        storageData.getDataStorage();
    }

    @Test
    public void getListId() {
        StorageData storageData = new StorageData(fileName);
        StorageData storageData1 = new StorageData("todotask1.xml");

        assertFalse(storageData.getListId().equals(storageData1.getListId()));
    }

    @Test
    public void getFreeId() {
        StorageData storageData = new StorageData(fileName2);

        if (!storageData.getListId().contains("1"))
            assertTrue(storageData.getFreeId().equals("1"));
        assertFalse(storageData.getFreeId().equals("12"));
    }

    @Test
    public void dataReplacement() {
        StorageData storageData = new StorageData(fileName);
        StorageData storageData1 = new StorageData(fileName);


        if (storageData.getDataStorage().get("5").get("Status").equals("new")) {
            storageData.dataReplacement("5", "Status", "done");
        } else {
            storageData.dataReplacement("5", "Status", "new");
        }



        assertFalse(storageData.getDataStorage().equals(storageData1.getDataStorage()));

    }

    @Test
    public void addData() {
        StorageData storageData = new StorageData(fileName);
        StorageData storageData1 = new StorageData(fileName);

        Map<String, String> element = new LinkedHashMap<>();
        element.put("caption", "asdad222222");
        element.put("fdff", "asdad111111");
        element.put("Complete", "asdad");
        if (!storageData.getListId().contains("2")) {
            storageData.addData("2", element);
            assertFalse(storageData.getDataStorage().equals(storageData1.getDataStorage()));
        }


    }

    @Test
    public void removeData() {
        StorageData storageData = new StorageData(fileName);

        storageData.removeData("2");

        assertFalse(storageData.getListId().contains("6"));
    }
}