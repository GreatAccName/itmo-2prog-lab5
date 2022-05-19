package itmo.s283566.prog2lab5.commands;

import itmo.s283566.prog2lab5.collection.FlatsCollection;
import itmo.s283566.prog2lab5.collection_access.FlatsWriter;

import java.io.BufferedReader;
import java.io.IOException;

/**Сохраняет коллекцию в файл.
 */
public class Save implements Command {

    @Override
    public void execute(BufferedReader reader, FlatsCollection collection, String[] lineArgs)
            throws IllegalArgumentException, IOException {
        checkCommandArgsAmount(lineArgs, 0);
        try {FlatsWriter.write(collection);} catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
