package itmo.s283566.prog2lab5.collection_access;

import itmo.s283566.prog2lab5.collection.Flat;
import itmo.s283566.prog2lab5.collection.FlatsCollection;

import java.io.*;
import java.util.*;

/**Записывает данные коллекции {@link FlatsCollection} в файла, путь к которому хранится в переменной окружения.
 * Формат записи: 1 "хэдер", каждая следующая строка в формате: key,id,creationDate,
 * name,area,numberOfRooms,isNew,transport,view,coordinatesX,coordinatesY,houseName,houseYear,houseNumberOfLifts.
 */
public class FlatsWriter {

    /**Имя переменной окружения, хранящая путь к файлу для чтения.
     */
    public static final String WRITE_FLATS = "WRITE_FLATS";

    /**Записывает данные о коллекции {@link FlatsCollection} в файл переменной окружения.
     * В конце выводит сообщение об успешности записи.
     * @param collection коллекция квартир {@link FlatsCollection} для записи.
     * @throws IOException либо при неправильном имени переменной окружения,
     * либо при неправильном ее значении, либо при неудачной попытке записи.
     */
    public static void write(FlatsCollection collection) throws IOException {
        AbstractMap.SimpleEntry<String, File> pair = FlatsReader.getPathAndFile(WRITE_FLATS);
        String filePath = pair.getKey();
        File file = pair.getValue();

        FileWriter writer = new FileWriter(file);
        writer.write("key,id,creationDate,name,area,numberOfRooms,isNew,transport," +
                "view,coordinatesX,coordinatesY,houseName,houseYear,houseNumberOfLifts\n"); //header
        writer.flush();
        Iterator<Map.Entry<String, Flat>> iter = collection.iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Flat> entry = iter.next();
            Flat flat = entry.getValue();
            writer.write(entry.getKey() + "," + flat.getId() + "," +
                    flat.getCreationDate() + "," + flat.getSimple() + "\n");
            writer.flush();
        }
        writer.close();
        System.out.println("Коллекция успешно записана в файл: " + filePath + ".");
    }
}
