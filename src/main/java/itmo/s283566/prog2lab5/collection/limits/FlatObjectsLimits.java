package itmo.s283566.prog2lab5.collection.limits;

import itmo.s283566.prog2lab5.collection.Coordinates;
import itmo.s283566.prog2lab5.collection.FlatObjects;

/**Класс, предоставляющий удобное взаимодействие с ограничениями {@link FlatObjects}.
 */
public class FlatObjectsLimits {

    /**Метод, проверяющий поле {@code coordinates} по ограничениям.
     * @param coordinates аргумент координат.
     * @return {@code true}, если {@code coordinates НЕ null}, иначе {@code false}.
     */
    public static boolean isCoordinatesGood(Coordinates coordinates) {return coordinates != null;}

    /**Метод, предоставляющий информацию о пределах поля {@code coordinates}.
     * @return информация о пределах поля {@code coordinates}.
     */
    public static String getCoordinatesLimitInfo() {return "Поле \"coordinates\" НЕ должно равняться null.";}

    /**Метод, предоставляющий информацию о пределах поля {@code house}.
     * @return информация о пределах поля {@code house}.
     */
    public static String getHouseLimitInfo() {return "Поле \"house\" МОЖЕТ быть null.";}
}
