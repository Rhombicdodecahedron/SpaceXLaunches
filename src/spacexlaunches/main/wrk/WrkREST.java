package spacexlaunches.main.wrk;

import spacexlaunches.main.Constantes;
import spacexlaunches.main.beans.*;
import com.codename1.io.JSONParser;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

public class WrkREST implements Constantes {


    ArrayList<Rocket> rockets;
    ArrayList<LaunchPad> launchPads;


    public WrkREST() {

    }


    public Map<String, Object> postUserLogin(String email, String password) throws Exception {
        Map<String, Object> result = null;
        if (email != null && password != null) {
            result = responseToMap(Rest.post(REST_SERVER_URL).queryParam("action", "POST_USER_LOGIN").queryParam("email", email).queryParam("password", password).getAsBytes());
        }
        return result;
    }

    public Map<String, Object> postUserRegister(String firstname, String lastname, String email, String password) throws Exception {
        Map<String, Object> result = null;
        if (firstname != null && lastname != null && email != null && password != null) {
            result = responseToMap(Rest.post(REST_SERVER_URL)
                    .queryParam("action", "POST_USER_REGISTER")
                    .queryParam("firstname", firstname)
                    .queryParam("lastname", lastname)
                    .queryParam("email", email)
                    .queryParam("password", password)
                    .getAsBytes());
        }
        return result;
    }


    public boolean isUserConnected() throws Exception {
        Map<String, Object> response = responseToMap(Rest.get(REST_SERVER_URL).queryParam("action", "IS_USER_CONNECTED").getAsBytes());
        return Boolean.parseBoolean(response.get("isUserConnected").toString());
    }


    public boolean disconnect() throws Exception {

        //FAIRE RETOURNER LA MAP DE STRING D'OBJECT ET ENSUITE DANS LE CONTROLLEUR VERIFIER SI C'EST OK SINON AFFICHER LE MESSAGE D'ERREUR.
        Map<String, Object> response = responseToMap(Rest.get(REST_SERVER_URL).queryParam("action", "GET_USER_LOGOUT").acceptJson().getAsBytes());
        return !Boolean.parseBoolean(response.get("error").toString());
    }

    public User getSession() throws Exception {
        User result = null;

        Map<String, Object> response = responseToMap(Rest.get(REST_SERVER_URL).queryParam("action", "GET_SESSION").acceptJson().getAsBytes());
        System.out.println(response);

        Map<String, Object> user = (Map<String, Object>) response.get("user");

        if (user != null) {
            int pk = Integer.parseInt(user.get("pk_user").toString());
            String firstname = (String) user.get("firstname");
            String lastname = (String) user.get("lastname");
            String email = (String) user.get("email");
            Date timestamp = new SimpleDateFormat("yyyy-MM-dd").parse(user.get("timestamp").toString());
            result = new User(pk, firstname, lastname, email, timestamp);
        }
        return result;
    }


    public Launch getNextLaunch() throws Exception {
        Launch result = null;
        Map<String, Object> response = responseToMap(Rest.get(SPACEX_API_URL + "launches/next").acceptJson().getAsBytes());
        if (!response.isEmpty()) {
            Launch launch = createLaunchObject(response);
            if (launch != null) {
                result = launch;
            }
        }
        return result;
    }


    public ArrayList<Launch> getAllLaunches() throws Exception {
        ArrayList<Launch> result = new ArrayList<>();
        Map<String, Object> out = responseToMap(Rest.get(SPACEX_API_URL + "launches/past").acceptJson().getAsBytes());
        if (rockets == null && launchPads == null) {
            rockets = getAllRockets();
            launchPads = getAllLaunchPads();
        }
        if (!out.isEmpty()) {
            List<Map<String, Object>> responses = (List<Map<String, Object>>) out.get("root");
            for (Map<String, Object> response : responses) {
                Launch launch = createLaunchObject(response);
                if (launch != null) {
                    result.add(launch);
                }
            }
        }
        return result;
    }


    public ArrayList<Rocket> getAllRockets() throws Exception {
        ArrayList<Rocket> result = new ArrayList<>();
        Map<String, Object> rockets = responseToMap(Rest.get(SPACEX_API_URL + "rockets").acceptJson().getAsBytes());
        if (!rockets.isEmpty()) {
            List<Map<String, Object>> responses = (List<Map<String, Object>>) rockets.get("root");
            if (responses != null && !responses.isEmpty()) {
                for (Map<String, Object> response : responses) {
                    if (response != null && !response.isEmpty()) {
                        String rocketId = response.get("id").toString();
                        String name = response.get("name").toString();
                        String type = response.get("type").toString();
                        result.add(new Rocket(rocketId, name, type));
                    }
                }
            }
        }
        return result;
    }


    public ArrayList<LaunchPad> getAllLaunchPads() throws Exception {
        ArrayList<LaunchPad> result = new ArrayList<>();
        Map<String, Object> launchPads = responseToMap(Rest.get(SPACEX_API_URL + "launchpads").acceptJson().getAsBytes());
        if (!launchPads.isEmpty()) {
            List<Map<String, Object>> responses = (List<Map<String, Object>>) launchPads.get("root");
            if (responses != null && !responses.isEmpty()) {
                for (Map<String, Object> response : responses) {
                    if (response != null && !response.isEmpty()) {
                        String id = response.get("id").toString();
                        String name = response.get("name").toString();
                        String fullName = response.get("full_name").toString();
                        String locality = response.get("locality").toString();
                        String region = response.get("region").toString();
                        result.add(new LaunchPad(id, name, fullName, locality, region));
                    }
                }
            }
        }
        return result;
    }


    public Rocket getRocket(String id) {
        Rocket result = null;
        if (id != null) {
            if (rockets != null && !rockets.isEmpty()) {
                for (Rocket rocket : rockets) {
                    if (rocket != null) {
                        if (rocket.getRocketId().equalsIgnoreCase(id)) {
                            result = rocket;
                        }
                    }
                }
            }
        }
        return result;
    }

    public LaunchPad getLaunchPad(String id) {
        LaunchPad result = null;
        if (id != null) {
            if (launchPads != null && !launchPads.isEmpty()) {
                for (LaunchPad launchPad : launchPads) {
                    if (launchPad != null) {
                        if (launchPad.getId().equalsIgnoreCase(id)) {
                            result = launchPad;
                        }
                    }
                }
            }
        }
        return result;
    }


    private Launch createLaunchObject(Map<String, Object> response) throws Exception {
        Launch result = null;
        if (response != null) {
            Map<String, Object> links = (Map<String, Object>) response.get("links");
            Map<String, Object> patch = (Map<String, Object>) links.get("patch");
            String img = (String) patch.get("small");
            String id = (String) response.get("id");
            String name = (String) response.get("name");
            int flightNumber = (int) Double.parseDouble(response.get("flight_number").toString());
            Rocket rocket = getRocket(response.get("rocket").toString());
            Date launchDate = new SimpleDateFormat(API_FORMAT_DATE).parse(response.get("date_utc").toString());
            boolean success = false;
            if (response.get("success") != null) {
                success = Boolean.parseBoolean(response.get("success").toString());
            }
            LaunchPad launchPad = getLaunchPad(response.get("launchpad").toString());
            String details = (String) response.get("details");
            result = new Launch(img, id, name, flightNumber, rocket, launchDate, success, launchPad, details);
        }
        return result;
    }

    private Map<String, Object> responseToMap(Response<byte[]> response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        if (response != null) {
            JSONParser p = new JSONParser();
            InputStreamReader r = new InputStreamReader(new ByteArrayInputStream(response.getResponseData()));
            result.putAll(p.parseJSON(r));
        }
        return result;
    }


}
