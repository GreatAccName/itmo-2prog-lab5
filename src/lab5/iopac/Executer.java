package lab5.iopac;

import java.io.*;
import java.util.*;

import lab5.datapac.*;
import lab5.exceptions.BadInputException;
import lab5.exceptions.NullException;
import lab5.interf.Executable;

/**Класс, выполняющий команды.*/
public class Executer extends Commands implements Executable {
    /**Предел количества ввода.*/
    private static int READ_LIMIT = 65535;
    public static int getReadLimit() { return READ_LIMIT; }

    /**Запрещает вызов конструктора по умолчанию.*/
    protected Executer() {}
    /**
     * Инициализирует поле переданной ссылкой на коллекцию.
     * @param toLink ссылка на коллекцию.
     * @throws NullException при {@code envVarName == null},
     * {@code envVarPath == null} или {@code toLink == null}.
     */
    public Executer(FlatsCollection toLink)
    throws NullException {
        super(toLink);
    }

    @Override
    public void executeFromInput() {
        Scanner sc = new Scanner(System.in);
        int i;
        for (i = 0; i < READ_LIMIT; ++i) {
            System.out.print("Команда: ");
            String line = sc.nextLine();
            String[] lineArr;
            try {
                lineArr = RWXbase.makeArrFrom(line);
                String key, subName;
                Flat flat;
                Integer id;
                Map<String, Flat> flats = getCollLink().getFlats();
                switch (lineArr[0]) {
                    case "exit": exit(); sc.close(); return;
                    case "help": help(); break;
                    case "info": info(); break;
                    case "show": show(); break;
                    case "insert":
                        key = lineArr[1];
                        if (flats.containsKey(key)) {
                            System.out.println("Ключ \"" + key +
                                "\" уже добавлен.");
                            continue;
                        }
                        flat = Flat.flatFromInput(sc);
                        insert(key, flat);
                        break;
                    case "update":
                        id = Integer.valueOf(lineArr[1]);
                        flat = Flat.flatFromInput(sc);
                        update(id, flat);
                        break;
                    case "remove_key":
                        key = lineArr[1];
                        remove_key(key);
                        break;
                    case "clear": clear(); break;
                    case "save": save(); break;
                    case "execute_script":
                        if (lineArr.length == 1) { executeFromEnv(); }
                        else { executeFrom(lineArr[1]); }
                        break;
                    case "remove_lower":
                        id = Integer.valueOf(lineArr[1]);
                        remove_lower(id, (a, b) -> {
                            return Float.compare(a.getArea(), b.getArea());
                        });
                        break;
                    case "replace_if_greater":
                        key = lineArr[1];
                        if (!flats.containsKey(key)) {
                            System.out.println("Ключа \"" +
                                key + "\" нет в коллекции.");
                            continue;
                        }
                        flat = Flat.flatFromInput(sc);
                        replace_if_greater(key, flat, (a, b) -> {
                            return Float.compare(a.getArea(), b.getArea());
                        });
                        break;
                    case "replace_if_lower":
                        key = lineArr[1];
                        if (!flats.containsKey(key)) {
                            System.out.println("Ключа \"" +
                                key + "\" нет в коллекции.");
                            continue;
                        }
                        flat = Flat.flatFromInput(sc);
                        replace_if_lower(key, flat, (a, b) -> {
                            return Float.compare(a.getArea(), b.getArea());
                        });
                        break;
                    case "min_by_id": min_by_id(); break;
                    case "filter_starts_with_name":
                        subName = lineArr[1];
                        filter_starts_with_name(subName);
                        break;
                    case "print_descending":
                        print_descending();
                        break;
                    
                    default: throw new BadInputException(
                        "Нет команды \"" + lineArr[0] + "\"."
                    );
                }
            } catch (BadInputException |
                     NullException |
                     IOException e) {
                System.out.println(e.getMessage());
                System.out.println("\"help\" для справки.");
                continue;
            } catch (RuntimeException e) {
                System.out.println(
                    "Плохой ввод. \"help\" для справки."
                );
                continue;
            }
        }
        if (i >= READ_LIMIT) {
            System.out.println("Достигнут предел ввода.");
        }
        sc.close();
    }
    @Override
    public void executeFromEnv()
    throws IOException, NullException {
        executeFrom(envVarPath);
    }
    @Override
    public void executeFrom(String filePath)
    throws IOException, NullException {
        // opening the file
        Scanner sc = openScanner(filePath);

        int i = 0;
        for (i = 0; i < READ_LIMIT && sc.hasNext(); ++i) {
            String line = sc.nextLine();
            String[] lineArr;
            try {
                lineArr = RWXbase.makeArrFrom(line);
                String key, subName;
                Flat flat;
                Integer id;
                Map<String, Flat> flats = getCollLink().getFlats();
                switch (lineArr[0]) {
                    case "exit": sc.close(); return;
                    case "help": help(); break;
                    case "info": info(); break;
                    case "show": show(); break;
                    case "insert":
                        key = lineArr[1];
                        if (flats.containsKey(key)) {
                            System.out.println("Ключ \"" + key +
                                "\" уже добавлен.");
                            continue;
                        }
                        line = sc.nextLine();
                        ++i;
                        flat = Flat.parseLine(line);
                        insert(key, flat);
                        break;
                    case "update":
                        id = Integer.valueOf(lineArr[1]);
                        line = sc.nextLine();
                        ++i;
                        flat = Flat.parseLine(line);
                        update(id, flat);
                        break;
                    case "remove_key":
                        key = lineArr[1];
                        remove_key(key);
                        break;
                    case "clear": clear(); break;
                    case "save": save(); break;
                    case "execute_script":
                        throw new BadInputException(
                            "Во избежание бесконечной рекурсии, " +
                            "вызов \"execute_script\" из скрипта запрещен."
                        );
                    case "remove_lower":
                        id = Integer.valueOf(lineArr[1]);
                        remove_lower(id, (a, b) -> {
                            return Float.compare(a.getArea(), b.getArea());
                        });
                        break;
                    case "replace_if_greater":
                        key = lineArr[1];
                        if (!flats.containsKey(key)) {
                            System.out.println("Ключа \"" +
                                key + "\" нет в коллекции.");
                            continue;
                        }
                        line = sc.nextLine();
                        ++i;
                        flat = Flat.parseLine(line);
                        replace_if_greater(key, flat, (a, b) -> {
                            return Float.compare(a.getArea(), b.getArea());
                        });
                        break;
                    case "replace_if_lower":
                        key = lineArr[1];
                        if (!flats.containsKey(key)) {
                            System.out.println("Ключа \"" +
                                key + "\" нет в коллекции.");
                            continue;
                        }
                        line = sc.nextLine();
                        ++i;
                        flat = Flat.parseLine(line);
                        replace_if_lower(key, flat, (a, b) -> {
                            return Float.compare(a.getArea(), b.getArea());
                        });
                        break;
                    case "min_by_id": min_by_id(); break;
                    case "filter_starts_with_name":
                        subName = lineArr[1];
                        filter_starts_with_name(subName);
                        break;
                    case "print_descending":
                        print_descending();
                        break;
                    
                    default: throw new BadInputException(
                        "Нет команды \"" + lineArr[0] + "\"."
                    );
                }
            } catch (BadInputException |
                     NullException e) {
                e.printStackTrace();
                System.out.println(
                    "\tфайл: " + filePath + ";\n" +
                    "\tстрока: " + (i + 1) + ".");
                continue;
            } catch (RuntimeException e) {
                System.out.println("Плохой ввод.");
                e.printStackTrace();
                System.out.println(
                    "\tфайл: " + filePath + ";\n" +
                    "\tстрока: " + (i + 1) + ".");
                continue;
            }
        }
        if (i >= READ_LIMIT) {
            System.out.println("Достигнут предел ввода.");
        }
        sc.close();
    }
}
