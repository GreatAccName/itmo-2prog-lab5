package itmo.s283566.prog2lab5.collection.limits;

import itmo.s283566.prog2lab5.collection.Coordinates;

/**Класс, предоставляющий удобное взаимодействие с ограничениями {@link Coordinates}.
 */
public class CoordinatesLimits {

    /**Максимальное значение поля координаты {@code x}.
     */
    public static final double MAX_X = 165D;
    /**Метод, проверяющий поле координаты {@code x} по ограничениям.
     * @param x аргумент поля координаты.
     * @return {@code true}, если {@code x <= 165}, иначе {@code false}.
     */
    public static boolean isXGood(double x) {return Double.compare(x, MAX_X) <= 0;}

    /**Метод, предоставляющий информацию о пределах поля координаты {@code x}.
     * @return информация о пределах поля координаты {@code x}.
     */
    public static String getXLimitInfo() {return "Необходимо, чтоб координата \"x\" была меньше или равна 165.";}
}
