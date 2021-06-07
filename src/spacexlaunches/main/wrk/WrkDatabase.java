package spacexlaunches.main.wrk;

import com.codename1.io.rest.Rest;
import spacexlaunches.main.Constantes;
import spacexlaunches.main.beans.User;
import spacexlaunches.main.utils.RestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Cette classe renferme le worker "WrkDatabase" de l'application.
 *
 * @author Stella Alexis
 * @version 1.0
 * @since 17.05.2021
 */
public class WrkDatabase implements Constantes {

    /**
     * Objet User
     */
    private User user;
    private Wrk refWrk;

    /**
     * Constructeur de la classe WrkDatabase. Il défini l'objet User à null.
     */
    public WrkDatabase() {
        user = null;
    }

    /**
     * Cette méthode permet de tester si la combinaison email et mot de passe passés en paramètre sont corrects.
     *
     * @param email    représente l'email de connexion.
     * @param password représente le mot de passe de connexion.
     * @return une Map de String d'Object si tout se passe bien ou null;
     * @throws Exception si une erreur se produit.
     */
    public Map<String, Object> postUserLogin(String email, String password) throws Exception {
        Map<String, Object> result = null;
        if (email != null && password != null) {
            result = RestUtils.responseToMap(Rest.post(REST_SERVER_URL)
                    .queryParam(ACTION, POST_USER_LOGIN)
                    .queryParam(EMAIL, email)
                    .queryParam(PASSWORD, password)
                    .getAsBytes()
            );
        }
        return result;
    }

    /**
     * @param firstname représente le prénom de l'utilisateur.
     * @param lastname  représente le nom de l'utilisateur.
     * @param email     représente l'adresse de courrier de l'utilisateur.
     * @param password  représente le mot de passe de l'utilisateur.
     * @return une liste contenant les informations du JSON retournées par le serveur PHP.
     * @throws Exception si une erreur survient.
     */
    public Map<String, Object> postUserRegister(String firstname, String lastname, String email, String password) throws Exception {
        Map<String, Object> result = null;
        if (firstname != null && lastname != null && email != null && password != null) {
            result = RestUtils.responseToMap(Rest.post(REST_SERVER_URL)
                    .queryParam(ACTION, POST_USER_REGISTER)
                    .queryParam(FIRSTNAME, firstname)
                    .queryParam(LASTNAME, lastname)
                    .queryParam(EMAIL, email)
                    .queryParam(PASSWORD, password)
                    .getAsBytes());
        }
        return result;
    }


    /**
     * @return
     * @throws Exception
     */
    public boolean isUserConnected() throws Exception {
        Map<String, Object> response = RestUtils.responseToMap(Rest.get(REST_SERVER_URL)
                .queryParam(ACTION, IS_USER_CONNECTED)
                .onError(evt -> refWrk.showError(evt.getError().getMessage()))
                .getAsBytes()
        );
        return Boolean.parseBoolean(response.get("isUserConnected").toString());
    }

    /**
     * @return
     * @throws Exception
     */
    public boolean disconnect() throws Exception {
        Map<String, Object> response = RestUtils.responseToMap(Rest.get(REST_SERVER_URL).queryParam(ACTION, GET_USER_LOGOUT).acceptJson().getAsBytes());
        return !Boolean.parseBoolean(response.get(ERROR).toString());
    }

    /**
     * @return
     * @throws Exception
     */
    public User getSession() throws Exception {
        User result = null;
        Map<String, Object> response = RestUtils.responseToMap(Rest.get(REST_SERVER_URL).queryParam(ACTION, "GET_SESSION").acceptJson().getAsBytes());
        Map<String, Object> user = (Map<String, Object>) response.get(USER);
        if (user != null) {
            int pk = Integer.parseInt(user.get("pk_user").toString());
            String firstname = (String) user.get(FIRSTNAME);
            String lastname = (String) user.get(LASTNAME);
            String email = (String) user.get(EMAIL);
            Date timestamp = new SimpleDateFormat(USER_DATE_FORMAT).parse(user.get(TIMESTAMP).toString());
            result = new User(pk, firstname, lastname, email, timestamp);
            setUser(result);
        }
        return result;
    }

    public User getUser() {
        return user;
    }

    public void setRefWrk(Wrk refWrk) {
        this.refWrk = refWrk;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
