package itmo.s283566.prog2lab5.commands;

import itmo.s283566.prog2lab5.collection.Flat;
import itmo.s283566.prog2lab5.collection.FlatsCollection;
import itmo.s283566.prog2lab5.collection.inputers.FlatFromInput;

import java.io.BufferedReader;
import java.io.IOException;

/**Обновляет данные квартиры коллекции по ID (далее ввод данных квартиры).
 */
public class Update implements Command {

    @Override
    public void execute(BufferedReader reader, FlatsCollection collection, String[] lineArgs)
            throws IllegalArgumentException, IOException {
        checkCommandArgsAmount(lineArgs, 1);
        Integer id;
        try {id = Integer.valueOf(lineArgs[1]);} catch (NumberFormatException e) {
            throw new IllegalArgumentException("Аргумент [ID] должен быть целым числом (int).");
        }
        Flat flat = collection.getById(id);
        FlatFromInput.setFlat(reader, flat);
        System.out.println("Данные квартиры по ID=" + id + " успешно обновлены.");
    }
}
