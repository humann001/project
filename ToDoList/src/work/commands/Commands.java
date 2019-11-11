package work.commands;

import work.document.Information;
import work.document.Tasks;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Commands {

    private String nameFile;
    private Tasks tasks;
    private boolean commandExists = false;


    public Commands(String nameFile) {
        this.nameFile = nameFile;
        tasks = new Tasks(nameFile);
    }

    public boolean isCommandExists() {
        return commandExists;
    }

    public void setCommandExists(boolean commandExists) {
        this.commandExists = commandExists;
    }


    public void help(String command) {
        if (command.equals("help")) {

            System.out.println("Ввели команду: " + command + "\n");
            Information.getHelp();

            commandExists = true;
        }
    }

    public void listNew(String command) {
        if (command.equals("list -s new")) {

            System.out.println("Ввели команду: " + command + "\n");
            tasks.print("Status", "new");

            commandExists = true;
        }
    }

    public void listDone(String command) {
        if (command.equals("list -s done")) {

            System.out.println("Ввели команду: " + command + "\n");
            tasks.print("Status", "done");

            commandExists = true;
        }
    }

    public void list(String command) {
        if (command.equals("list")) {

            System.out.println("Ввели команду: " + command + "\n");
            tasks.printAll();

            commandExists = true;
        }
    }

    public void complete(String command, Scanner sc) {
        if (command.equals("complete")) {

            System.out.println("Ввели команду: " + command + "\n");

            System.out.println("Введите идентификатор: ");
            String s1 = sc.nextLine();

            Date date = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
            String s2 = formatForDateNow.format(date);

            tasks.edit(s1, "Complete", s2);

            commandExists = true;
        }
    }

    public void newTask(String command, Scanner sc) {
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

            tasks.add(s1, s2, s3, s4, "new", "");

            commandExists = true;
        }
    }

    public void edit(String command, Scanner sc) {
        if (command.equals("edit")) {

            System.out.println("Ввели команду: " + command + "\n");

            System.out.println("Введите идентификатор: ");
            String id = sc.nextLine();

            if (tasks.hasId(id)) {

                System.out.println("Хотите редактировать заголовок? Если да - введите 1, иначе - 0");

                if (sc.nextLine().equals("1")) {
                    System.out.println("Введите заголовок: ");
                    String caption = sc.nextLine();
                    tasks.edit(id, "caption", caption);
                }

                System.out.println("Хотите редактировать описание? Если да - введите 1, иначе - 0");

                if (sc.nextLine().equals("1")) {
                    System.out.println("Введите описание: ");
                    String description = sc.nextLine();
                    tasks.edit(id, "Description", description);
                }

                System.out.println("Хотите редактировать важность? Если да - введите 1, иначе - 0");

                if (sc.nextLine().equals("1")) {
                    System.out.println("Введите важность: ");
                    String priority = sc.nextLine();
                    tasks.edit(id, "Priority", priority);
                }

                System.out.println("Хотите редактировать срок? Если да - введите 1, иначе - 0");

                if (sc.nextLine().equals("1")) {
                    System.out.println("Введите срок: ");
                    String deadline = sc.nextLine();
                    tasks.edit(id, "Deadline", deadline);
                }

                System.out.println("Успешное редактирование");
            } else {
                System.out.println("Задачи с таким id не существует");
            }

            commandExists = true;
        }
    }

    public void remove(String command, Scanner sc) {
        if (command.equals("remove")) {

            System.out.println("Ввели команду: " + command + "\n");
            System.out.println("Введите id: ");
            String id = sc.nextLine();
            tasks.remove(id);

            commandExists = true;
        }
    }
}

