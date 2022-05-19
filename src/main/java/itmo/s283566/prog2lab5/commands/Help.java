package itmo.s283566.prog2lab5.commands;

import itmo.s283566.prog2lab5.collection.FlatsCollection;

import java.io.BufferedReader;
import java.io.IOException;

/**Выводит справку по доступным командам.
 */
public class Help implements Command {

    @Override
    public void execute(BufferedReader reader, FlatsCollection collection, String[] lineArgs)
            throws IllegalArgumentException, IOException {
        checkCommandArgsAmount(lineArgs, 0);
        System.out.println(
                "help : вывести справку по доступным командам.\n" +
                        "info : вывести информацию о коллекции квартир.\n" +
                        "show : вывести все квартиры коллекции в строковом представлении.\n" +
                        "clear : очистить коллекцию.\n" +
                        "save : сохранить коллекцию в файл.\n" +
                        "exit : завершить программу (без сохранения в файл).\n" +
                        "print_descending : вывести квартиры коллекции в порядке убывания.\n" +
                        "min_by_id : вывести квартиру с минимальным ID.\n" +
                        "remove_lower [AREA] : удалить из коллекции все квартиры, площадь которых меньше заданной AREA.\n" +
                        "update [ID] : обновить данные квартиры коллекции по ID (далее ввод данных квартиры).\n" +
                        "execute_script [FILE_NAME] : исполнить скрипт из указанного файла (можно не указывать).\n" +
                        "insert [KEY] : добавить новую квартиру с заданным ключом (далее ввод данных квартиры).\n" +
                        "remove_key [KEY] : удалить квартиру из коллекции по его ключу KEY.\n" +
                        "filter_starts_with_name [SUBNAME] : вывести квартиры, значение имени которых начинается с заданной подстроки SUBNAME.\n" +
                        "replace_if_greater [KEY] [AREA] : заменить значение площади квартиры по ключу, если новое значение больше старого.\n" +
                        "replace_if_lower [KEY] [AREA] : заменить значение площади квартиры по ключу, если новое значение меньше старого."
        );
    }
}
