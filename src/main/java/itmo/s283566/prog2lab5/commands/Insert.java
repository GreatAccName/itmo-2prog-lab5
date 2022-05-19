package itmo.s283566.prog2lab5.commands;

import itmo.s283566.prog2lab5.collection.FlatsCollection;
import itmo.s283566.prog2lab5.collection.inputers.FlatFromInput;

import java.io.BufferedReader;
import java.io.IOException;

/**Добавляет новую квартиру с заданным ключом (далее ввод данных квартиры).
 */
public class Insert implements Command {

    @Override
    public void execute(BufferedReader reader, FlatsCollection collection, String[] lineArgs)
            throws IllegalArgumentException, IOException {
        checkCommandArgsAmount(lineArgs, 1);
        FlatFromInput.newFlat(reader, collection, lineArgs[1]);
        System.out.println("Новая квартира успешно добавлена в коллекцию " +
                "по ключу " + lineArgs[1] + ".");
    }
}
