package lab5.enums;

/**Перечисление вида из окна.*/
public enum View {
    STREET, PARK, BAD, GOOD;
    /**
     * Проверяет, есть ли такой элемент тут.
     * @param s строка имени поля.
     * @return {@code true} при наличии элемента в {@code View},
     * иначе {@code false}.
     */
    public static boolean isInView(String s) {
        if (s == null) { return false; }
        View[] vArr = View.values();
        for (View elem : vArr) {
            if (elem.toString().equals(s)) { return true; }
        }
        return false;
    }
}
