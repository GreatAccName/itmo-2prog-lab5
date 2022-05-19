package itmo.s283566.prog2lab5.commands;

import itmo.s283566.prog2lab5.collection.Flat;
import itmo.s283566.prog2lab5.collection.FlatsCollection;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

/**Выводит квартиры коллекции в порядке убывания.
 */
public class PrintDescending implements Command {

    @Override
    public void execute(BufferedReader reader, FlatsCollection collection, String[] lineArgs)
            throws IllegalArgumentException, IOException {
        checkCommandArgsAmount(lineArgs, 0);
        Map<String, Flat> descendingFlats = collection.unmodifiableDescendingCollection();
        System.out.println("Далее следует вывод квартир коллекции в порядке убывания.");
        descendingFlats.forEach((k, v) -> {
            System.out.println("\t[key=" + k + "]: ");
            v.show();
        });
    }
}
