package itmo.s283566.prog2lab5.collection;

/**Описывает дополнительные возможности каждой части квартиры:
 * {@link FlatFields}, {@link FlatStates}, {@link FlatObjects}.
 */
public interface FlatPart {

    /**Метод выводит в стандартный поток вывода в удобном для чтения виде информацию о части квартиры.
     */
    void show();

    /**Метод для представления части квартиры в удобном для передачи в формат .csv виде.
     * @return представления части квартиры в удобном для передачи в формат .csv виде.
     */
    String getSimple();
}
