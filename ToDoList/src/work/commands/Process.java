package work.commands;

import java.util.Scanner;

public class Process {

    private String nameFile;

    public Process (String nameFile) {
        this.nameFile = nameFile;
    }



    public void start() {

        Scanner sc = new Scanner(System.in);

        Commands commands = new Commands(nameFile);

        int n = 1;

        while (n > 0) {

            System.out.println("Введите команду: ");
            String command = sc.nextLine();

            commands.help(command);

            commands.listNew(command);

            commands.listDone(command);

            commands.list(command);

            commands.complete(command, sc);

            commands.newTask(command, sc);

            commands.edit(command, sc);

            commands.remove(command, sc);

            if (!commands.isCommandExists())
                System.out.println("error");
            commands.setCommandExists(false);
        }


        sc.close();

    }

}
