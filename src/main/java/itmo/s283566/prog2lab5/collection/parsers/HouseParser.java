package itmo.s283566.prog2lab5.collection.parsers;

import itmo.s283566.prog2lab5.collection.House;
import itmo.s283566.prog2lab5.collection.limits.HouseLimits;

/**Parser для {@link House}. Формат строки: {@code ...,houseField1,houseField2,...}.
 */
public class HouseParser {
    /**Разбирает переданный массив {@link String} объектов и выдает объект {@link House}.
     * @param lineArray массив всех {@link String} объектов.
     * @param beginIndex индекс массива {@code lineArray}, с которого начинается парсинг.
     * @return полученный объект {@link House}.
     * @throws IllegalArgumentException при некорректном задании объектов.
     */
    public static House parseLineArray(String[] lineArray, int beginIndex)
            throws IllegalArgumentException {
        String name;
        try {name = lineArray[beginIndex];} catch (RuntimeException e) {
            throw new IllegalArgumentException(HouseLimits.getNameLimitInfo());
        }
        long year;
        try {year = Long.parseLong(lineArray[beginIndex + 1]);} catch (RuntimeException e) {
            throw new IllegalArgumentException(HouseLimits.getYearLimitInfo());
        }
        int numberOfLifts;
        try {numberOfLifts = Integer.parseInt(lineArray[beginIndex + 2]);} catch (RuntimeException e) {
            throw new IllegalArgumentException(HouseLimits.getNumberOfLiftsLimitInfo());
        }
        return new House(name, year, numberOfLifts);
    }
}