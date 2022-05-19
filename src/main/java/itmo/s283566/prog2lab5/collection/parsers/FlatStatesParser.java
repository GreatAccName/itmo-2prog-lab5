package itmo.s283566.prog2lab5.collection.parsers;

import itmo.s283566.prog2lab5.collection.FlatStates;
import itmo.s283566.prog2lab5.collection.Transport;
import itmo.s283566.prog2lab5.collection.View;
import itmo.s283566.prog2lab5.collection.limits.FlatStatesLimits;

/**Parser для {@link FlatStates}. Формат строки: {@code ...,state1,state2,...}.
 */
public class FlatStatesParser {

    /**Разбирает переданный массив {@link String} объектов и выдает объект {@link FlatStates}.
     * @param lineArray массив всех {@link String} объектов.
     * @param beginIndex индекс массива {@code lineArray}, с которого начинается парсинг.
     * @return полученный объект {@link FlatStates}.
     * @throws IllegalArgumentException при некорректном задании состояний.
     */
    public static FlatStates parseLineArray(String[] lineArray, int beginIndex) throws IllegalArgumentException {
        Transport transport;
        try {transport = Transport.valueOf(lineArray[beginIndex]);} catch (RuntimeException e) {
            throw new IllegalArgumentException(FlatStatesLimits.getTransportLimitInfo());
        }
        View view;
        try {view = View.valueOf(lineArray[beginIndex + 1]);} catch (RuntimeException e) {
            throw new IllegalArgumentException(FlatStatesLimits.getViewLimitInfo());
        }
        return new FlatStates(transport, view);
    }
}
