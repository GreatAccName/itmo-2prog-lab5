package itmo.s283566.prog2lab5.collection;

import itmo.s283566.prog2lab5.collection.limits.FlatStatesLimits;

import java.util.Objects;

/**Класс состояний квартир. Ограничения: {@link FlatStatesLimits}.
 * Включает в себя {@link Transport}, {@link View}. Является частью {@link Flat}.
 */
public class FlatStates implements FlatPart {

    private Transport transport;
    private View view;

    protected FlatStates(){}

    /**Единственный доступный конструктор, задающий и проверяющий все состояния.
     * Использует {@link #setFlatObjects(Transport, View)}.
     * @param aTransport аргумент состояния transport.
     * @param aView аргумент состояния view.
     * @throws IllegalArgumentException в случае неудачной проверки полей.
     */
    public FlatStates(Transport aTransport, View aView) throws IllegalArgumentException {
        setFlatObjects(aTransport, aView);
    }

    public Transport getTransport() {return transport;}

    public View getView() {return view;}

    /**Единственный доступный сеттер, меняющий и проверяющий все состояния.
     * @param aTransport аргумент состояния transport.
     * @param aView аргумент состояния view.
     * @throws IllegalArgumentException в случае неудачной проверки полей.
     */
    public void setFlatObjects(Transport aTransport, View aView) throws IllegalArgumentException {
        if (!FlatStatesLimits.isTransportGood(aTransport)) {
            throw new IllegalArgumentException(FlatStatesLimits.getTransportLimitInfo());
        }
        if (!FlatStatesLimits.isViewGood(aView)) {
            throw new IllegalArgumentException(FlatStatesLimits.getViewLimitInfo());
        }
        transport = aTransport; view = aView;
    }

    @Override
    public void show() {
        System.out.print("Состояния:");
        System.out.print(" [transport=" + transport + "],");
        System.out.println(" [view=" + view + "].");
    }
    @Override
    public String getSimple() {return transport + "," + view;}

    @Override
    public String toString() {return "[transport=" + transport + ",view=" + view + "]";}

    @Override
    public int hashCode() {return Objects.hash(transport, view);}
}
