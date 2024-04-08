package zombicide.util.listchooser;

import java.util.List;

public class NoneListChooser<T> extends RandomListChooser<T> {
    /**
     * Allows one to choose the "0 - none" option from a list of items.
     * If the list of items is empty, null is returned.
     *
     * @param msg The message to display.
     * @param list The list of items.
     * @return null if "0 - none" is chosen, otherwise the first item from the list.
     */
    @Override
    public T choose(String msg, List<? extends T> list) {
        System.out.println(msg);
        System.out.println("0 - none");

        return null;
    }
}
