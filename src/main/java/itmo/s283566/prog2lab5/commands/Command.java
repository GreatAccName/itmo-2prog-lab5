package itmo.s283566.prog2lab5.commands;

import itmo.s283566.prog2lab5.collection.FlatsCollection;

import java.io.BufferedReader;
import java.io.IOException;

/**Описывает основные возможности команд. Является интерфейсом для вызова любой команды программы.
 * Команды выполняются над {@link FlatsCollection}.
 */
public interface Command {

    /**Проверяет количество аргументов команды. Если что-то не так, швыряет исключение.
     * @param lineArgs строчный массив с именем команды и аргументами.
     * @param correctCommandArgsAmount правильное количество аргументов команды.
     * @throws IllegalArgumentException если количество аргументов команды неудовлетворительное.
     */
    default void checkCommandArgsAmount(String[] lineArgs, int correctCommandArgsAmount)
            throws IllegalArgumentException {
        if (lineArgs.length != correctCommandArgsAmount + 1) {
            throw new IllegalArgumentException("Аргументов команды " + (lineArgs.length - 1) +
                    ", а надо " + correctCommandArgsAmount + ".");
        }
    }

    /**Исполняющий метод команды.
     * @param reader объект входного потока {@code System.in}.
     * @param collection коллекция, над которой выполняются команды.
     * @param lineArgs строчный массив с названием команды и ее аргументами.
     * @throws IllegalArgumentException при неудовлетворительных аргументах.
     * @throws IOException при неполадках с потоками ввода / ввода.
     */
    void execute(BufferedReader reader, FlatsCollection collection, String[] lineArgs)
            throws IllegalArgumentException, IOException;
}
