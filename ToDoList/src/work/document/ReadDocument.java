package work.document;

import java.util.*;

public class ReadDocument {

    private Storage storage;

    public ReadDocument(String nameFile) {
        storage = new Storage(nameFile);
    }

    public void inputTasks(String nameParametr, String value) {
        for (Map.Entry<String, Map<String, String>> entry : storage.getDataStorage().entrySet()) {
            if (value.equals(entry.getValue().get(nameParametr))) {

                for (Map.Entry<String, String> entry1 : entry.getValue().entrySet()) {
                    if (!entry1.getValue().isEmpty())
                        System.out.println(changeText(entry1.getKey()) + ", " + entry1.getValue());
                }

                System.out.println("");
            }
        }
    }


    public void inputTasks() {
        for (Map.Entry<String, Map<String, String>> entry : storage.getDataStorage().entrySet()) {

            for (Map.Entry<String, String> entry1 : entry.getValue().entrySet()) {
                if (!entry1.getValue().isEmpty())

                    System.out.println(changeText(entry1.getKey()) + ", " + entry1.getValue());
            }

            System.out.println("");
        }
    }

    public void addTask(String caption, String description, String priority, String deadline, String status, String complete) {
//        System.out.println("Свободный id: " + storage.getFreeId());
        String id = storage.getFreeId();
        Map<String, String>  element = new LinkedHashMap<>();
        element.put("caption", caption);
        element.put("Description", description);
        element.put("Priority", priority);
        element.put("Deadline", deadline);
        element.put("Status", status);
        element.put("Complete", complete);
        storage.getDataStorage().put(id, element);

        storage.addData(id);
    }

    public void edit(String id, String nameParametr, String data) {
        if (storage.getListId().contains(id)) {
            storage.getDataStorage().get(id).put(nameParametr, data);
        } else {
            System.out.println("Задачи с таким id не существует");
        }
    }

    public void remove(String id) {
        if (storage.getListId().contains(id)) {
            storage.removeData(id);
            System.out.println("Успешное удаление");
        } else {
            System.out.println("Задачи с таким id не существует");
        }
    }

    public void writeDocument() {
        storage.dataReplacement();
    }


    public boolean hasId(String id) {
        if (storage.getListId().contains(id))
            return true;
        else
            return false;
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
