package itmo.s283566.prog2lab5.console;

import itmo.s283566.prog2lab5.collection.FlatsCollection;
import itmo.s283566.prog2lab5.commands.AllCommands;
import itmo.s283566.prog2lab5.commands.Command;

import java.io.*;

/**2-ая часть программы. Обеспечивает интерактивный ввод в консоли.
 */
public class Console {

    /** Запускает интерактивный консольный ввод команд.
     * @param collection коллекция квартир, над которыми выполняются команды {@link Command}.
     */
    public static void run(FlatsCollection collection) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Введите команду: ");
            String line = reader.readLine();
            while (!line.equals("exit")) {
                String[] lineArr = line.split("\\h+");
                try {
                    AllCommands.getCommand(lineArr[0]).execute(reader, collection, lineArr);
                }
                catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage() + " \"help\" для справки.");
                }
                System.out.print("\nВведите команду: ");
                line = reader.readLine().trim();
            }
        } catch (IOException e) {e.printStackTrace();}
    }
}
