package itmo.s283566.prog2lab5.collection.parsers;

import itmo.s283566.prog2lab5.collection.Coordinates;
import itmo.s283566.prog2lab5.collection.limits.CoordinatesLimits;

/**Parser для {@link Coordinates}. Формат строки: {@code ...,coordinateField1,coordinateField2,...}.
 */
public class CoordinatesParser {

    /**Разбирает переданный массив {@link String} объектов и выдает объект {@link Coordinates}.
     * @param lineArray массив всех {@link String} объектов.
     * @param beginIndex индекс в массиве {@code lineArray}, с которого начинается парсинг.
     * @return полученный объект {@link Coordinates}.
     * @throws IllegalArgumentException при некорректном задании объектов.
     */
    public static Coordinates parseLineArray(String[] lineArray, int beginIndex)
            throws IllegalArgumentException {
        double x;
        try {x = Double.parseDouble(lineArray[beginIndex]);} catch (RuntimeException e) {
            throw new IllegalArgumentException(CoordinatesLimits.getXLimitInfo());
        }
        int y;
        try {y = Integer.parseInt(lineArray[beginIndex + 1]);} catch (RuntimeException e) {
            throw new IllegalArgumentException("Координата 'y' должна быть целым числом (тип int).");
        }
        return new Coordinates(x, y);
    }
}
