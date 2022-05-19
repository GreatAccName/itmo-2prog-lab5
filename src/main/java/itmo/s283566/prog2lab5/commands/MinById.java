package itmo.s283566.prog2lab5.commands;

import itmo.s283566.prog2lab5.collection.Flat;
import itmo.s283566.prog2lab5.collection.FlatsCollection;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**Выводит квартиру с минимальным ID.
 */
public class MinById implements Command {

    @Override
    public void execute(BufferedReader reader, FlatsCollection collection, String[] lineArgs)
            throws IllegalArgumentException, IOException {
        checkCommandArgsAmount(lineArgs, 0);
        if (collection.size() == 0) {
            System.out.println("Коллекция пустая.");
            return;
        }
        Iterator<Map.Entry<String, Flat>> iter = collection.iterator();
        Integer minId = collection.allFlatsSize();
        Flat minFlat = null;
        while (iter.hasNext()) {
            Map.Entry<String, Flat> entry = iter.next();
            Flat flat = entry.getValue();
            if (flat.getId().compareTo(minId) < 0) {
                minId = flat.getId();
                minFlat = flat;
            }
        }
        if (minFlat == null) {
            System.out.println("Не получилось найти квартиру с минимальным ID.");
            return;
        }
        System.out.print("Квартира с минимальным id: ");
        minFlat.show();
    }
}
