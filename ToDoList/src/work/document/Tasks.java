package work.document;

import java.util.*;

public class Tasks {

    private StorageData storageData;

    public Tasks(String nameFile) {
        storageData = new StorageData(nameFile);
    }

    public void print(String nameParametr, String value) {
        if (storageData.isFileExists()) {
            for (Map.Entry<String, Map<String, String>> entry : storageData.getDataStorage().entrySet()) {
                if (value.equals(entry.getValue().get(nameParametr))) {

                    for (Map.Entry<String, String> entry1 : entry.getValue().entrySet()) {
                        if (!entry1.getValue().isEmpty())
                            System.out.println(changeText(entry1.getKey()) + ", " + entry1.getValue());
                    }

                    System.out.println("");
                }
            }
        }
    }


    public void printAll() {
        if (storageData.isFileExists()) {
            for (Map.Entry<String, Map<String, String>> entry : storageData.getDataStorage().entrySet()) {

                for (Map.Entry<String, String> entry1 : entry.getValue().entrySet()) {
                    if (!entry1.getValue().isEmpty())

                        System.out.println(changeText(entry1.getKey()) + ", " + entry1.getValue());
                }

                System.out.println("");
            }
        }
    }

    public void add(String caption, String description, String priority, String deadline, String status, String complete) {

        if (storageData.isFileExists()) {
            String id = storageData.getFreeId();
            Map<String, String> element = new LinkedHashMap<>();
            element.put("caption", caption);
            element.put("Description", description);
            element.put("Priority", priority);
            element.put("Deadline", deadline);
            element.put("Status", status);
            element.put("Complete", complete);
            storageData.getDataStorage().put(id, element);

            storageData.addData(id);
        }
    }

    public void edit(String id, String nameParametr, String data) {
        if (storageData.getListId().contains(id)) {
            storageData.getDataStorage().get(id).put(nameParametr, data);
            storageData.dataReplacement();
        } else {
            System.out.println("Задачи с таким id не существует");
        }
    }

    public void remove(String id) {
        if (storageData.getListId().contains(id)) {
            storageData.removeData(id);
            System.out.println("Успешное удаление");
        } else {
            System.out.println("Задачи с таким id не существует");
        }
    }

    public boolean hasId(String id) {
        if (storageData.getListId().contains(id)) {
            return true;
        } else {
            return false;
        }
    }

    private String changeText(String text) {
        if (text.equals("caption"))
            return "Заголовок";
        if (text.equals("Description"))
            return "Описание";
        if (text.equals("Priority"))
            return "Важность";
        if (text.equals("Deadline"))
            return "Срок";
        if (text.equals("Status"))
            return "Статус";
        if (text.equals("Complete"))
            return "Дата когда задача была выполнена";
        return text;
    }




}
