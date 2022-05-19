package itmo.s283566.prog2lab5.commands;

import itmo.s283566.prog2lab5.collection.Flat;
import itmo.s283566.prog2lab5.collection.FlatFields;
import itmo.s283566.prog2lab5.collection.FlatsCollection;

import java.io.BufferedReader;
import java.io.IOException;

/**Заменяет значение площади квартиры по ключу, если новое значение больше старого.
 */
public class ReplaceIfGreater implements Command {

    @Override
    public void execute(BufferedReader reader, FlatsCollection collection, String[] lineArgs)
            throws IllegalArgumentException, IOException {
        checkCommandArgsAmount(lineArgs, 2);
        float sharedArea;
        try {sharedArea = Float.parseFloat(lineArgs[2]);} catch (NumberFormatException e) {
            throw new IllegalArgumentException("Аргумент команды \"" + lineArgs[1] +
                    "\" не может быть площадью квартиры.");
        }

        Flat flat = collection.get(lineArgs[1]);
        if (Float.compare(sharedArea, flat.getFields().getArea()) > 0) {
            flat.setFlat(
                    new FlatFields(flat.getFields().getName(), sharedArea,
                            flat.getFields().getNumberOfRooms(), flat.getFields().getIsNew()),
                    flat.getStates(),
                    flat.getObjects()
            );
            System.out.println("Площадь квартиры с ключом \"" + lineArgs[1] +
                    "\" успешно заменена на значение " + sharedArea + ".");
            return;
        }
        System.out.println("Площадь квартиры с ключом \"" + lineArgs[1] +
                "\" НЕ заменена на значение " + sharedArea + ".");
    }
}
