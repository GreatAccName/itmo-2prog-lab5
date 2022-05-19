package itmo.s283566.prog2lab5.commands;

import itmo.s283566.prog2lab5.collection.FlatsCollection;
import itmo.s283566.prog2lab5.collection_access.FlatsReader;

import java.io.*;
import java.util.AbstractMap;
import java.util.Scanner;

/**Исполняет скрипт из указанного файла (можно не указывать).*/
public class ExecuteScript implements Command {

    @Override
    public void execute(BufferedReader reader, FlatsCollection collection, String[] lineArgs)
            throws IllegalArgumentException, IOException {
        if (lineArgs.length != 1 && lineArgs.length != 2) {
            throw new IllegalArgumentException("Аргументов команды " + (lineArgs.length - 1) +
                    ", а надо 0 или 1.");
        }

        File file = null;
        if (lineArgs.length == 2) {
            file = new File(lineArgs[1]);
        }
        else {
            AbstractMap.SimpleEntry<String, File> pair;
            try {pair = FlatsReader.getPathAndFile("EXECUTE_COMMANDS");}
            catch (IOException e) {throw new IllegalArgumentException(e.getMessage());}
            file = pair.getValue();
        }

        BufferedReader fileReader = new BufferedReader(new FileReader(file));
        String line = fileReader.readLine().trim();
        System.out.println("Прочитанная команда: " + line);
        while (fileReader.ready() && !line.equals("exit")) {
            String[] lineArr = line.split("\\h+");
            if (lineArr[0].equals("execute_script")) {
                System.out.println("Во избежание бесконечной рекурсии нельзя вызывать " +
                        "команду \"execute_script\" из файла.");
                line = fileReader.readLine().trim();
                System.out.println("\nПрочитанная команда: " + line);
                continue;
            }
            try {
                AllCommands.getCommand(lineArr[0]).execute(fileReader, collection, lineArr);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " \"help\" для справки.");
            }
            line = fileReader.readLine().trim();
            System.out.println("\nПрочитанная команда: " + line);
        }
    }
}
