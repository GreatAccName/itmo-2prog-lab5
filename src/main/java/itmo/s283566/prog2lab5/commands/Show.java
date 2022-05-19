package itmo.s283566.prog2lab5.commands;

import itmo.s283566.prog2lab5.collection.FlatsCollection;

import java.io.BufferedReader;
import java.io.IOException;

/**Выводит все квартиры коллекции в строковом представлении.
 */
public class Show implements Command {

    @Override
    public void execute(BufferedReader reader, FlatsCollection collection, String[] lineArgs)
            throws IllegalArgumentException, IOException {
        checkCommandArgsAmount(lineArgs, 0);
        System.out.println("Квартиры коллекции:");
        collection.forEach((k, v) -> {
            System.out.println("\t[key=" + k + "]: ");
            v.show();
        });
    }
}
