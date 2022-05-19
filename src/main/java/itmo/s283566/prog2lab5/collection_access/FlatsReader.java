package itmo.s283566.prog2lab5.collection_access;

import itmo.s283566.prog2lab5.collection.FlatsCollection;
import itmo.s283566.prog2lab5.collection.FlatsOf90sStars;
import itmo.s283566.prog2lab5.collection.parsers.*;

import java.io.*;
import java.util.*;

/**1-ая часть программы. Читает данные о коллекции {@link FlatsCollection} из файла,
 * путь к которому хранится в переменной окружения, и записывает их в новую коллекцию.
 * Формат чтения: 1 "хэдер", каждая следующая строка в формате:
 * {@code key,name,area,numberOfRooms,isNew,transport,view,coordinateX,coordinateY,hName,hYear,hNumberOfLifts}.
 */
public class FlatsReader {

    /**Имя переменной окружения, хранящая путь к файлу для чтения.
     */
    public static final String READ_FLATS = "READ_FLATS";

    /**Читает данные о коллекции {@link FlatsCollection} из файла
     * по пути из переменной окружения и записывает в коллекцию. Parser: {@link FlatParser}.
     * В конце выводит сообщение об успешности чтения.
     * @return записанная коллекция.
     * @throws IOException либо при неправильном имени переменной окружения, либо при неправильном ее значении.
     * @throws IllegalArgumentException при некорректном чтении данных из файла. Parser: {@link FlatParser}.
     */
    public static FlatsCollection read() throws IOException, IllegalArgumentException {
        AbstractMap.SimpleEntry<String, File> pair = getPathAndFile(READ_FLATS);
        String filePath = pair.getKey();
        File file = pair.getValue();

        Scanner scanner = new Scanner(file);
        ArrayList<String> csvText = new ArrayList<>();
        while (scanner.hasNext()) {csvText.add(scanner.nextLine().trim());}
        scanner.close();

        FlatsCollection collection = new FlatsOf90sStars();
        for (int i = 1; i < csvText.size(); ++i) {
            String[] lineArray = csvText.get(i).split(",");
            if (lineArray.length > 0) {
                try {
                    collection.put(lineArray[0], FlatParser.parseLineArray(collection, lineArray));
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(e.getMessage() +
                            " [Файл=" + filePath + "]. [Строка=" + i + "].");
                }
            }
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        writer.write("Коллекция успешно создана и заполнена данными из файла \"" + filePath + "\".\n");
        writer.flush();
        return collection;
    }

    /**Метод получения пути файла и файла с помощью переменной окружения.
     * @param envVarName имя переменной окружения, в которой хранится путь к файлу.
     * @return пара значений: 1) путь к файлу; 2) файл {@link File} по данному пути.
     * @throws IOException либо при неправильном имени переменной окружения, либо при неправильном ее значении.
     */
    public static AbstractMap.SimpleEntry<String, File> getPathAndFile(String envVarName)
            throws IOException {
        Map<String, String> envVarsMap = System.getenv();
        String filePath = null;
        for (Map.Entry<String, String> entry : envVarsMap.entrySet()) {
            if (entry.getKey().equals(envVarName)) {filePath = entry.getValue();}
        }
        if (filePath == null) {
            throw new IOException("Переменная окружения \"" + envVarName + "\" не найдена.");
        }
        File file = new File(filePath);
        if (!file.canRead()) {
            throw new IOException("Не получается прочитать файл \"" + filePath + "\".");
        }
        return new AbstractMap.SimpleEntry<>(filePath, file);
    }
}
/*
EXECUTE_COMMANDS=data_files/execute_commands.csv
READ_FLATS=data_files/read_flats.csv
WRITE_FLATS=data_files/write_flats.csv

    Реализация для сторонней библиотеки:
ArrayList<String[]> csvText = new ArrayList<>();
Reader reader = new FileReader(filePath);
CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT);
for (CSVRecord line : parser) {
    csvText.add(new String[line.size()]);
    int i = 0;
    for (String elem : line) {
        csvText.get(csvText.size() - 1)[i++] = elem;
    }
    System.out.println();
}
reader.close();
parser.close();
for (String[] sArr : csvText) {System.out.println(Arrays.toString(sArr));}
*/