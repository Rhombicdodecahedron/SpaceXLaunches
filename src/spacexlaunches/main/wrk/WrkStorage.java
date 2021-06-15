package spacexlaunches.main.wrk;

import com.codename1.io.Storage;
import spacexlaunches.main.Constantes;
import spacexlaunches.main.enumeration.Sort;

/**
 * Cette classe renferme le worker "WrkStorage" de l'application.
 *
 * @author Stella Alexis
 * @version 1.0
 * @since 17.05.2021
 */
public class WrkStorage implements Constantes {

    public boolean writeSortToStorage(String sort) {
        boolean result = false;
        if (sort != null) {
            try {
                result = Storage.getInstance().writeObject(STORAGE_SORT_VARIABLE, sort);
            } catch (Exception ignored) {
            }
        }
        return result;
    }

    public Sort readSortFromStorage() {
        Sort result = Sort.DEFAULT;
        String sortString = (String) Storage.getInstance().readObject(STORAGE_SORT_VARIABLE);
        if (sortString != null) {
            try {
                result = Sort.valueOf(sortString);
            } catch (Exception ignored) {
            }
        }
        return result;
    }
}
