package itmo.s283566.prog2lab5.collection.limits;

import itmo.s283566.prog2lab5.collection.FlatStates;
import itmo.s283566.prog2lab5.collection.Transport;
import itmo.s283566.prog2lab5.collection.View;

/**Класс, предоставляющий удобное взаимодействие с ограничениями {@link FlatStates}.
 */
public class FlatStatesLimits {

    /**Метод, проверяющий поле {@code transport} по ограничениям.
     * @param transport аргумент поля состояния квартиры.
     * @return {@code true}, если transport != null, иначе {@code false}.
     */
    public static boolean isTransportGood(Transport transport) {return transport != null;}

    /**Метод, проверяющий поле {@code view} по ограничениям.
     * @param view аргумент поля состояния квартиры.
     * @return {@code true}, если view != null, иначе {@code false}.
     */
    public static boolean isViewGood(View view) {return view != null;}

    /**Метод, предоставляющий информацию о пределах поля {@code area}.
     * @return информация о пределах поля {@code area}.
     */
    public static String getTransportLimitInfo() {return "Поле состояния \"transport\" должно быть не null.";}

    /**Метод, предоставляющий информацию о пределах поля {@code area}.
     * @return информация о пределах поля {@code area}.
     */
    public static String getViewLimitInfo() {return "Поле состояния \"view\" должно быть не null.";}
}
