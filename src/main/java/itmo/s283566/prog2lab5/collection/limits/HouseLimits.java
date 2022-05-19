package itmo.s283566.prog2lab5.collection.limits;

import itmo.s283566.prog2lab5.collection.House;

import java.time.LocalDate;

/**Класс, предоставляющий удобное взаимодействие с ограничениями {@link House}.
 */
public class HouseLimits {

    /**Метод, проверяющий аргумент поле дома name по ограничениям.
     * @param name аргумент поля дома name.
     * @return true, если {@code name != null}, иначе false.
     */
    public static boolean isNameGood(String name) {return name != null;}

    /**Метод, проверяющий аргумент поле дома year по ограничениям.
     * @param year аргумент поля дома year.
     * @return true, если {@code year > 0 && year <= LocalDate.now().getYear()}, иначе false.
     */
    public static boolean isYearGood(long year) {return year > 0 && year <= LocalDate.now().getYear();}

    /**Метод, проверяющий поле аргумент дома numberOfLifts по ограничениям.
     * @param numberOfLifts аргумент поля дома numberOfLifts.
     * @return true, если {@code numberOfLifts > 0}, иначе false.
     */
    public static boolean isNumberOfLiftsGood(int numberOfLifts) {return numberOfLifts > 0;}

    /**Метод, предоставляющий информацию о пределах поля дома {@code name}.
     * @return информация о пределах поля дома {@code name}.
     */
    public static String getNameLimitInfo() {return "Поле дома \"name\" должно быть НЕ null.";}

    /**Метод, предоставляющий информацию о пределах поля дома {@code year}.
     * @return информация о пределах поля дома {@code year}.
     */
    public static String getYearLimitInfo() {
        return "Поле дома \"year\" должно быть больше 0 и меньше нынешнего года.";
    }

    /**Метод, предоставляющий информацию о пределах поля дома {@code numberOfLifts}.
     * @return информация о пределах поля дома {@code numberOfLifts}.
     */
    public static String getNumberOfLiftsLimitInfo() {return "Поле дома \"numberOfLifts\" должно быть больше 0.";}
}
