package itmo.s283566.prog2lab5;

import itmo.s283566.prog2lab5.collection.*;
import itmo.s283566.prog2lab5.collection_access.FlatsReader;
import itmo.s283566.prog2lab5.console.Console;

import java.io.*;

/**Исполняющий класс программы. Выполняется в 2 этапа:
 * 1) запись коллекции из файла, путь к которому лежит в переменной окружения;
 * 2) интерактивный консольный ввод команд и их выполнение над коллекцией.
 */
public class Main {

    /**Исполняющий метод, куда ж без него.
     * @param args аргументы командной строки.
     */
    public static void main(String[] args) {
        FlatsCollection collection;
        try {
            collection = FlatsReader.read();
            Console.run(collection);
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace(System.err);
        }
    }
}
