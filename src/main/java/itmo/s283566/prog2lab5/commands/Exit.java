package itmo.s283566.prog2lab5.commands;

import itmo.s283566.prog2lab5.collection.FlatsCollection;

import java.io.BufferedReader;
import java.io.IOException;

/**Завершить программу (без сохранения в файл).
 */
public class Exit implements Command {

    @Override
    public void execute(BufferedReader reader, FlatsCollection collection, String[] lineArgs)
            throws IllegalArgumentException, IOException {
        System.out.println("Тут программа должна была завершиться. Чтоб остановить: CTRL+C или CTRL+D.");
    }
}
