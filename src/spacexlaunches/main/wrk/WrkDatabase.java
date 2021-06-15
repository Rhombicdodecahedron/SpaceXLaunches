package spacexlaunches.main.wrk;

import com.codename1.io.rest.Rest;
import spacexlaunches.main.Constantes;
import spacexlaunches.main.exception.RestException;
import spacexlaunches.main.utils.RestUtils;
import spacexlaunches.main.utils.SystemLib;

import java.util.Map;

/**
 * Cette classe renferme le worker "WrkDatabase" de l'application.
 *
 * @author Stella Alexis
 * @version 1.0
 * @since 17.05.2021
 */
public class WrkDatabase implements Constantes {

    private Wrk refWrk;

    /**
     * Constructeur de la classe WrkDatabase.
     */
    public WrkDatabase() {
    }

    public Map<String, Object> postUserLogin(String email, String password) throws RestException {
        Map<String, Object> result = null;
        if (email != null && password != null) {
            try {
                result = RestUtils.responseToMap(Rest.post(REST_SERVER_URL)
                        .queryParam(ACTION, POST_USER_LOGIN)
                        .queryParam(EMAIL, email)
                        .queryParam(PASSWORD, password)
                        .onError(evt -> refWrk.showError(evt.getError().getMessage()))
                        .acceptJson()
                        .getAsBytes()
                );
            } catch (Exception e) {
                throw new RestException(SystemLib.getCurrentMethod(), e.getMessage());
            }
        }
        return result;
    }

    public Map<String, Object> postUserRegister(String firstname, String lastname, String email, String password) throws RestException {
        Map<String, Object> result = null;
        if (firstname != null && lastname != null && email != null && password != null) {
            try {
                result = RestUtils.responseToMap(Rest.post(REST_SERVER_URL)
                        .onError(evt -> refWrk.showError(evt.getError().getMessage()))
                        .queryParam(ACTION, POST_USER_REGISTER)
                        .queryParam(FIRSTNAME, firstname)
                        .queryParam(LASTNAME, lastname)
                        .queryParam(EMAIL, email)
                        .queryParam(PASSWORD, password)
                        .acceptJson()
                        .getAsBytes()
                );
            } catch (Exception e) {
                throw new RestException(SystemLib.getCurrentMethod(), e.getMessage());
            }
        }
        return result;
    }

    public boolean isUserConnected() throws RestException {
        boolean result = false;
        try {
            Map<String, Object> response = RestUtils.responseToMap(Rest.get(REST_SERVER_URL)
                    .onError(evt -> refWrk.showError(evt.getError().getMessage()))
                    .queryParam(ACTION, IS_USER_CONNECTED)
                    .jsonContent()
                    .getAsBytes()
            );
            result = Boolean.parseBoolean(response.get("isUserConnected").toString());
        } catch (Exception e) {
            throw new RestException(SystemLib.getCurrentMethod(), e.getMessage());
        }
        return result;
    }

    public boolean disconnect() throws RestException {
        boolean result = false;
        try {
            Map<String, Object> response = RestUtils.responseToMap(Rest.get(REST_SERVER_URL)
                    .onError(evt -> refWrk.showError(evt.getError().getMessage()))
                    .queryParam(ACTION, GET_USER_LOGOUT)
                    .jsonContent()
                    .getAsBytes()
            );
            result = !Boolean.parseBoolean(response.get(ERROR).toString());
        } catch (Exception e) {
            throw new RestException(SystemLib.getCurrentMethod(), e.getMessage());
        }
        return result;
    }

    public void setRefWrk(Wrk refWrk) {
        this.refWrk = refWrk;
    }

}
