package itmo.s283566.prog2lab5.commands;

import itmo.s283566.prog2lab5.collection.Flat;
import itmo.s283566.prog2lab5.collection.FlatsCollection;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**Выводит квартиры, значение имени которых начинается с заданной подстроки SUBNAME.
 */
public class FilterStartsWithName implements Command {

    @Override
    public void execute(BufferedReader reader, FlatsCollection collection, String[] lineArgs)
            throws IllegalArgumentException, IOException {
        checkCommandArgsAmount(lineArgs, 1);
        Iterator<Map.Entry<String, Flat>> iter = collection.iterator();
        System.out.println("Квартиры из коллекции, имена которых начинаются на " + lineArgs[1] + ":");
        int counter = 0;
        while (iter.hasNext()) {
            Map.Entry<String, Flat> entry = iter.next();
            String flatName = entry.getValue().getFields().getName();
            String subName = lineArgs[1];
            if (flatName.length() >= subName.length()) {
                String flatSubName = flatName.substring(0, subName.length());
                if (flatSubName.equals(subName)) {
                    entry.getValue().show();
                    counter++;
                }
            }
        }
        if (counter == 0) {
            System.out.println("Таких нет, к сожалению.");
        }
    }
}
