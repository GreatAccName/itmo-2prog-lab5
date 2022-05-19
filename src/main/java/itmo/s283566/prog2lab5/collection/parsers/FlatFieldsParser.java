package itmo.s283566.prog2lab5.collection.parsers;

import itmo.s283566.prog2lab5.collection.FlatFields;
import itmo.s283566.prog2lab5.collection.limits.FlatFieldsLimits;

/**Parser для {@link FlatFields}. Формат строки: {@code ...,field1,field2,...}.
 */
public class FlatFieldsParser {

    /**Разбирает переданный массив {@link String} объектов и выдает объект {@link FlatFields}.
     * @param lineArray массив всех {@link String} объектов.
     * @param beginIndex индекс массива {@code lineArray}, с которого начинается парсинг.
     * @return полученный объект {@link FlatFields}.
     * @throws IllegalArgumentException при некорректном задании полей.
     */
    public static FlatFields parseLineArray(String[] lineArray, int beginIndex)
            throws IllegalArgumentException {
        String name;
        try {name = lineArray[beginIndex];} catch (RuntimeException e) {
            throw new IllegalArgumentException(FlatFieldsLimits.getNameLimitInfo());
        }
        float area;
        try {area = Float.parseFloat(lineArray[beginIndex + 1]);} catch (RuntimeException e) {
            throw new IllegalArgumentException(FlatFieldsLimits.getAreaLimitInfo());
        }
        int numberOfRooms;
        try {numberOfRooms = Integer.parseInt(lineArray[beginIndex + 2]);} catch (RuntimeException e) {
            throw new IllegalArgumentException(FlatFieldsLimits.getNumberOfRoomsLimitInfo());
        }
        Boolean isNew;
        try {isNew = Boolean.valueOf(lineArray[beginIndex + 3]);} catch (RuntimeException e) {
            throw new IllegalArgumentException(FlatFieldsLimits.getIsNewLimitInfo());
        }
        return new FlatFields(name, area, numberOfRooms, isNew);
    }
}
