package itmo.s283566.prog2lab5.collection;

/**Описывает вида из окна. Является элементом {@link FlatStates}.
 */
public enum View {
    STREET, PARK, BAD, GOOD;

    /**Проверяет, есть ли данный элемент в {@code View}.
     * @param s строка имени поля.
     * @return {@code true} при наличии элемента в {@code View}, иначе {@code false}.
     */
    public static boolean isInView(String s) {
        if (s == null) { return false; }
        View[] vArr = View.values();
        for (View elem : vArr) { if (elem.toString().equals(s)) { return true; } }
        return false;
    }
}
