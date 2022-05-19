package itmo.s283566.prog2lab5.commands;

import itmo.s283566.prog2lab5.collection.FlatsCollection;

import java.io.BufferedReader;
import java.io.IOException;

/**Выводит информацию о коллекции квартир.
 */
public class Info implements Command {

    @Override
    public void execute(BufferedReader reader, FlatsCollection collection, String[] lineArgs)
            throws IllegalArgumentException, IOException {
        checkCommandArgsAmount(lineArgs, 0);
        System.out.println(
                "Название коллекции: " + collection.getTitle() + ".\n" +
                        "Тип коллекции: " + collection.getType().getName() + ".\n" +
                        "Дата создания: " + collection.getCreationDate() + ".\n" +
                        "Количество элементов: " + collection.size() + ".\n" +
                        "Всего создано квартир в пределах коллекции: " + collection.allFlatsSize() + "."
        );
    }
}
