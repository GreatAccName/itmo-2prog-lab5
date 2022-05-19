package itmo.s283566.prog2lab5.collection;

/**Описывает количества транспорта рядом. Является элементом {@link FlatStates}.
 */
public enum Transport {
    FEW, NONE, NORMAL, ENOUGH;

    /**Проверяет, есть ли данный элемент в {@code Transport}.
     * @param s строка имени поля.
     * @return {@code true} при наличии элемента в {@code Transport}, иначе {@code false}.
     */
    public static boolean isInTransport(String s) {
        if (s == null) { return false; }
        Transport[] tArr = Transport.values();
        for (Transport elem : tArr) { if (elem.toString().equals(s)) { return true; } }
        return false;
    }
}
