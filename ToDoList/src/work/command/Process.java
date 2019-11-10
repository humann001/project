package work.command;

import work.document.ReadDocument;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Process {

    private String nameFile;
    private ReadDocument readDocument;
    private boolean commandExists = false;

    public Process (String nameFile) {
        this.nameFile = nameFile;
        readDocument = new ReadDocument(nameFile);
    }



    public void start() {

        Scanner sc = new Scanner(System.in);

        int n = 1;

        while (n > 0) {

            System.out.println("Введите команду: ");
            String command = sc.nextLine().toString();

            help(command);

            listNew(command);

            listDone(command);

            list(command);

            complete(command, sc);

            newTask(command, sc);

            edit(command, sc);

            remove(command, sc);

            if (!commandExists)
                System.out.println("error");
            commandExists = false;
        }


        sc.close();

    }



    private void help(String command) {
        if (command.equals("help")) {

            System.out.println("Ввели команду: " + command + "\n");
            Help.getHelp();


            commandExists = true;
        }
    }

    private void listNew(String command) {
        if (command.equals("list -s new")) {

            System.out.println("Ввели команду: " + command + "\n");
            readDocument.inputTasks("Status", "new");

            commandExists = true;
        }
    }

    private void listDone(String command) {
        if (command.equals("list -s done")) {

            System.out.println("Ввели команду: " + command + "\n");
            readDocument.inputTasks("Status", "done");

            commandExists = true;
        }
    }

    private void list(String command) {
        if (command.equals("list")) {

            System.out.println("Ввели команду: " + command + "\n");
            readDocument.inputTasks();

            commandExists = true;
        }
    }

    private void complete(String command, Scanner sc) {
        if (command.equals("complete")) {

            System.out.println("Ввели команду: " + command + "\n");
            System.out.println("Введите идентификатор: ");
            String s1 = sc.nextLine().toString();

//                System.out.println("Введите дату завершения: ");
//                String s2 = sc.nextLine().toString();

            Date date = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
            String s2 = formatForDateNow.format(date);

            readDocument.edit(s1, "Complete", s2);

            readDocument.writeDocument();

            commandExists = true;
        }
    }

    private void newTask(String command, Scanner sc) {
        if (command.equals("new")) {

            System.out.println("Ввели команду: " + command + "\n");

            System.out.println("Введите заголовок: ");
            String s1 = sc.nextLine();

            System.out.println("Введите описание: ");
            String s2 = sc.nextLine();

            System.out.println("Введите важность: ");
            String s3 = sc.nextLine();

            System.out.println("Введите срок: ");
            String s4 = sc.nextLine();

            readDocument.addTask(s1, s2, s3, s4, "new", "");
//                readDocument.addNewTask("sad", "asdasdas", "55", "2019");

            commandExists = true;
        }
    }

    private void edit(String command, Scanner sc) {
        if (command.equals("edit")) {

            System.out.println("Ввели команду: " + command + "\n");

            System.out.println("Введите идентификатор: ");
            String id = sc.nextLine().toString();

            if (readDocument.hasId(id)) {

                System.out.println("Хотите редактировать заголовок? Если да - введите 1, иначе - 0");

                if (sc.nextLine().equals("1")) {
                    System.out.println("Введите заголовок: ");
                    String caption = sc.nextLine();
                    readDocument.edit(id, "caption", caption);
                }

                System.out.println("Хотите редактировать описание? Если да - введите 1, иначе - 0");

                if (sc.nextLine().equals("1")) {
                    System.out.println("Введите описание: ");
                    String description = sc.nextLine();
                    readDocument.edit(id, "Description", description);
                }

                System.out.println("Хотите редактировать важность? Если да - введите 1, иначе - 0");

                if (sc.nextLine().equals("1")) {
                    System.out.println("Введите важность: ");
                    String priority = sc.nextLine();
                    readDocument.edit(id, "Priority", priority);
                }

                System.out.println("Хотите редактировать срок? Если да - введите 1, иначе - 0");

                if (sc.nextLine().equals("1")) {
                    System.out.println("Введите срок: ");
                    String deadline = sc.nextLine();
                    readDocument.edit(id, "Deadline", deadline);
                }

                readDocument.writeDocument();

                System.out.println("Успешное редактирование");
            } else {
                System.out.println("Задачи с таким id не существует");
            }

            commandExists = true;
        }
    }

    private void remove(String command, Scanner sc) {
        if (command.equals("remove")) {

            System.out.println("Ввели команду: " + command + "\n");
            System.out.println("Введите id: ");
            String id = sc.nextLine().toString();
            readDocument.remove(id);

            commandExists = true;
        }
    }



}
