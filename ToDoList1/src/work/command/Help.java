package work.command;

public class Help {

    public static void getHelp() {
        System.out.println("Справочник по командам");
        System.out.println("Вывод справки по командам: help");
        System.out.println("Вывод новых задач: list -s new");
        System.out.println("Вывод выполненных задач: list -s done");
        System.out.println("Вывод всех задач: list");
        System.out.println("Пометить задачу как выполненную: complete \"идентификатор\", проставить дату завершения текущей датой.");
        System.out.println("Добавить новую задачу: new и вводим значения \"Заголовок\", \"Описание\", \"Важность\", \"Срок\" в диалоге. Статус автоматически устанавливается в new.");
        System.out.println("Редактировать: edit \"идентификатор\" и вводим значения \"Заголовок\", \"Описание\", \"Важность\", \"Срок\"  в диалоге, предусмотреть возможность оставить текущее значение без изменений.");
        System.out.println("Удалить: remove \"идентификатор\"");
    }
}
