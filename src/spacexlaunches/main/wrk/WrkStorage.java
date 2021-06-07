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

    /**
     * Cette méthode permet de stocker une variable de tri pour que, lors du redémarrage, l'utilisateur ait toujours la même méthode de trie.
     *
     * @param sort représente le paramètre de trie à stocker.
     * @return true si l'enregistrement a correctement été effectué ou false.
     */
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
