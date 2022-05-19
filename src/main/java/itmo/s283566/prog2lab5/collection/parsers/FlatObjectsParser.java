package itmo.s283566.prog2lab5.collection.parsers;

import itmo.s283566.prog2lab5.collection.FlatObjects;
import itmo.s283566.prog2lab5.collection.House;

/**Parser для {@link FlatObjects}. Формат строки: {@code ...,[object1Fields],[object2Fields],...}.
 */
public class FlatObjectsParser {

    /**Разбирает переданный массив {@link String} объектов и выдает объект {@link FlatObjects}.
     * Поле объекта {@link House} может быть {@code null}.
     * @param lineArray массив всех {@link String} объектов.
     * @param beginIndex индекс массива {@code lineArray}, с которого начинается парсинг.
     * @return полученный объект {@link FlatObjects}.
     * @throws IllegalArgumentException при некорректном задании объектов.
     */
    public static FlatObjects parseLineArray(String[] lineArray, int beginIndex)
            throws IllegalArgumentException {
        if (lineArray.length - beginIndex == 2) { // при house == null вводится пустая строка
            return new FlatObjects(CoordinatesParser.parseLineArray(lineArray, beginIndex), null);
        }
        return new FlatObjects(CoordinatesParser.parseLineArray(lineArray, beginIndex),
                HouseParser.parseLineArray(lineArray, beginIndex + 2));
    }
}
