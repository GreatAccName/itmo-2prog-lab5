package itmo.s283566.prog2lab5.commands;

import itmo.s283566.prog2lab5.collection.Flat;
import itmo.s283566.prog2lab5.collection.FlatsCollection;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**Удаляет из коллекции все квартиры, площадь которых меньше заданной AREA.
 */
public class RemoveLower implements Command {

    @Override
    public void execute(BufferedReader reader, FlatsCollection collection, String[] lineArgs)
            throws IllegalArgumentException, IOException {
        checkCommandArgsAmount(lineArgs, 1);
        float sharedArea;
        try {sharedArea = Float.parseFloat(lineArgs[1]);} catch (NumberFormatException e) {
            throw new IllegalArgumentException("Аргумент команды \"" + lineArgs[1] +
                    "\" не может быть площадью квартиры.");
        }
        Iterator<Map.Entry<String, Flat>> iter = collection.iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Flat> entry = iter.next();
            Flat collectionFlat = entry.getValue();
            if (Float.compare(collectionFlat.getFields().getArea(), sharedArea) < 0) {
                iter.remove();
            }
        }
        System.out.println("Квартиры, площадь которых меньше " + sharedArea + ", успешно удалены.");
    }
}
