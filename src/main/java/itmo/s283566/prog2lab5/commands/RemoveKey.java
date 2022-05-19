package itmo.s283566.prog2lab5.commands;

import itmo.s283566.prog2lab5.collection.FlatsCollection;

import java.io.BufferedReader;
import java.io.IOException;

/**Удаляет квартиру из коллекции по его ключу KEY.
 */
public class RemoveKey implements Command {

    @Override
    public void execute(BufferedReader reader, FlatsCollection collection, String[] lineArgs)
            throws IllegalArgumentException, IOException {
        checkCommandArgsAmount(lineArgs, 1);
        collection.remove(lineArgs[1]);
        System.out.println("Квартира успешно удалена из коллекции по ключу [key=" + lineArgs[1] + "].");
    }
}
