package itmo.s283566.prog2lab5.collection.inputers;

import itmo.s283566.prog2lab5.collection.*;
import itmo.s283566.prog2lab5.collection.parsers.CoordinatesParser;
import itmo.s283566.prog2lab5.collection.parsers.FlatFieldsParser;
import itmo.s283566.prog2lab5.collection.parsers.FlatStatesParser;
import itmo.s283566.prog2lab5.collection.parsers.HouseParser;

import java.io.BufferedReader;
import java.io.IOException;

/**Класс, обеспечивающий создание или изменение квартиры из входного потока.
 */
public class FlatFromInput {

    /**Изменение квартиры введенными частями {@link FlatFields}, {@link FlatStates}, {@link FlatObjects}.
     * @param reader объект входного потока {@code System.in}.
     * @param flat изменяемая квартира.
     * @throws IllegalArgumentException при неверном задании частей квартиры.
     * @throws IOException при наличии проблем с потоками для чтения / записи.
     */
    public static void setFlat(BufferedReader reader, Flat flat)
            throws IllegalArgumentException, IOException {
        FlatPart[] parts = makeFlatParts(reader);

        flat.setFlat((FlatFields) parts[0],
                (FlatStates) parts[1], (FlatObjects) parts[2]);
    }

    /**Добавление квартиры с введенными частями {@link FlatFields}, {@link FlatStates}, {@link FlatObjects}.
     * @param reader объект входного потока {@code System.in}.
     * @param collection коллекция, в пределах которой создана квартира.
     * @param key ключ, по которому квартира добавляется.
     * @throws IllegalArgumentException при неверном задании частей квартиры.
     * @throws IOException при наличии проблем с потоками для чтения / записи.
     */
    public static void newFlat(BufferedReader reader, FlatsCollection collection, String key)
            throws IllegalArgumentException, IOException {
        FlatPart[] parts = makeFlatParts(reader);

        collection.put(key, collection.newFlat((FlatFields) parts[0],
                (FlatStates) parts[1], (FlatObjects) parts[2]));
    }

    //создание 3-х частей квартиры через ввод данных из стандартного входного потока.
    private static FlatPart[] makeFlatParts(BufferedReader reader)
            throws IllegalArgumentException, IOException {
        FlatPart[] parts = new FlatPart[3];

        System.out.println("Ввод полей квартиры. Строгий формат ввода в 1 строчку через запятые:\n" +
                "[имя(String)],[площадь(flat)],[количество комнат(int)],[новизна(boolean)]");
        String[] fieldsArr = reader.readLine().trim().split(",");
        FlatFields fields = FlatFieldsParser.parseLineArray(fieldsArr, 0);

        System.out.println("Ввод состояний квартиры. Строгий формат ввода в 1 строчку через запятые:\n" +
                "[FEW/NONE/NORMAL/ENOUGH(Transport)],[STREET/PARK/BAD/GOOD(View)]");
        String[] statesArr = reader.readLine().trim().split(",");
        FlatStates states = FlatStatesParser.parseLineArray(statesArr, 0);

        System.out.println("Ввод объектов квартиры.");

        System.out.println("Ввод координат. Строгий формат ввода в 1 строчку через запятые:\n" +
                "[x(double)],[y(int)]");
        String[] coordinatesArr = reader.readLine().trim().split(",");
        Coordinates coordinates = CoordinatesParser.parseLineArray(coordinatesArr, 0);

        System.out.println("Ввод дома (пустая строка, если его нет). " +
                "Строгий формат ввода в 1 строчку через запятые:\n" +
                "[имя(String)],[год(long),[количество лифтов(int)]");
        String[] houseArr = reader.readLine().trim().split(",");
        House house = null;
        if (houseArr.length > 1) {house = HouseParser.parseLineArray(houseArr, 0);}

        FlatObjects objects = new FlatObjects(coordinates, house);

        parts[0] = fields;
        parts[1] = states;
        parts[2] = objects;

        return parts;
    }
}
