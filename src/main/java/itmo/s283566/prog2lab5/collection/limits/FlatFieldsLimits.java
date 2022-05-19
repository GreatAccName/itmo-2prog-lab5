package itmo.s283566.prog2lab5.collection.limits;

import itmo.s283566.prog2lab5.collection.FlatFields;

import java.time.LocalDate;

/**Класс, предоставляющий удобное взаимодействие с ограничениями {@link FlatFields}.
 */
public class FlatFieldsLimits {

    /**Метод, проверяющий поле {@code name} по ограничениям.
     * @param name аргумент поля квартиры.
     * @return {@code true}, если name не равно null и name.length() больше 0, иначе {@code false}.
     */
    public static boolean isNameGood(String name) {return name != null && name.length() > 0;}

    /**Метод, проверяющий поле {@code area} по ограничениям.
     * @param area аргумент поля квартиры.
     * @return {@code true}, если area больше 0, иначе {@code false}.
     */
    public static boolean isAreaGood(float area) {return area > 0;}

    /**Метод, проверяющий поле {@code numberOfRooms} по ограничениям.
     * @param numberOfRooms аргумент поля квартиры.
     * @return {@code true}, если numberOfRooms больше 0, иначе {@code false}.
     */
    public static boolean isNumberOfRoomsGood(int numberOfRooms) {return numberOfRooms > 0;}

    /**Метод, проверяющий поле {@code isNew} по ограничениям.
     * @param isNew аргумент поля квартиры.
     * @return {@code true}, если isNew != null, иначе {@code false}.
     */
    public static boolean isIsNewGood(Boolean isNew) {return isNew != null;}

    /**Метод, предоставляющий информацию о пределах поля {@code name}.
     * @return информация о пределах поля {@code name}.
     */
    public static String getNameLimitInfo() {return "Поле \"name\" НЕ должно равняться null или быть пустым.";}

    /**Метод, предоставляющий информацию о пределах поля {@code area}.
     * @return информация о пределах поля {@code area}.
     */
    public static String getAreaLimitInfo() {return "Поле \"area\" должно быть больше 0.";}

    /**Метод, предоставляющий информацию о пределах поля {@code numberOfRooms}.
     * @return информация о пределах поля {@code numberOfRooms}.
     */
    public static String getNumberOfRoomsLimitInfo() {
        return "Поле \"numberOfRooms\" должно быть больше 0.";
    }

    /**Метод, предоставляющий информацию о пределах поля {@code isNew}.
     * @return информация о пределах поля {@code isNew}.
     */
    public static String getIsNewLimitInfo() {return "Поле \"isNew\" НЕ должно равняться null.";}

    /**Метод, предоставляющий информацию о пределах даты создания квартиры.
     * @param creationDate дата создания квартиры.
     * @return информация о пределах поля {@code creationDate}.
     */
    public static String getCreationDateLimitInfo(LocalDate creationDate) {
        return "Поле \"creationDate\" не может быть null и его значение должно генерироваться автоматически.";
    }

    /**Метод, предоставляющий информацию о пределах {@code ID}.
     * @param id ID квартиры.
     * @return информация о пределах поля {@code ID}.
     */
    public static String getIdLimitInfo(Integer id) {
        return "Поле \"id\" не может быть null и его значение должно: " +
                "быть больше 0, быть уникальным, генерироваться автоматически.";
    }
}
