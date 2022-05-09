package lab5.enums;

/**Перечисление количества транспорта рядом.*/
public enum Transport {
    FEW, NONE, NORMAL, ENOUGH;
    /**
     * Проверяет, есть ли такой элемент тут.
     * @param s строка имени поля.
     * @return {@code true} при наличии элемента в {@code Transport},
     * иначе {@code false}.
     */
    public static boolean isInTransport(String s) {
        if (s == null) { return false; }
        Transport[] tArr = Transport.values();
        for (Transport elem : tArr) {
            if (elem.toString().equals(s)) {
                return true;
            }
        }
        return false;
    }
}
