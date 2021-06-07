package spacexlaunches.main.wrk;

import com.codename1.io.rest.Rest;
import spacexlaunches.main.Constantes;
import spacexlaunches.main.beans.Launch;
import spacexlaunches.main.beans.LaunchPad;
import spacexlaunches.main.beans.Rocket;
import spacexlaunches.main.enumeration.Sort;
import spacexlaunches.main.utils.RestUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Cette classe renferme le worker "WrkLaunches" de l'application.
 *
 * @author Stella Alexis
 * @version 1.0
 * @since 17.05.2021
 */
public class WrkLaunches implements Constantes {

    /**
     * Liste contenant les pads de lancement.
     */
    ArrayList<LaunchPad> launchPads;
    /**
     * Liste contenant les différentes fusées.
     */
    ArrayList<Rocket> rockets;

    private Wrk refWrk;

    /**
     * Constructeur de la classe WrkLaunches. Il défini les ArrayList launchPads et rockets.
     */
    public WrkLaunches() {
        launchPads = new ArrayList<>();
        rockets = new ArrayList<>();
    }

    public Launch getNextLaunch() throws Exception {
        Launch result = null;
        Map<String, Object> response = RestUtils.responseToMap(Rest.get(SPACEX_API_URL + "launches/next").acceptJson().getAsBytes());
        if (!response.isEmpty()) {
            Launch launch = createLaunchObject(response);
            if (launch != null) {
                result = launch;
            }
        }
        return result;
    }

    public ArrayList<Launch> getAllLaunches(Sort sort, ArrayList<Launch> launches) {
        if (sort != null && launches != null) {
            switch (sort) {
                case ASC_DATE:
                    Collections.sort(launches, Launch.dateComparator);
                    break;
                case DESC_DATE:
                    Collections.sort(launches, Launch.dateComparator);
                    Collections.reverse(launches);
                    break;
                case DESC_FLIGHT_NUMBER:
                    Collections.sort(launches, Launch.flightNumberComparator);
                    break;
                case ASC_FLIGHT_NUMBER:
                    Collections.sort(launches, Launch.flightNumberComparator);
                    Collections.reverse(launches);
                    break;
                case ASC_NAME:
                    Collections.sort(launches, Launch.nameComparator);
                    break;
                case DESC_NAME:
                    Collections.sort(launches, Launch.nameComparator);
                    Collections.reverse(launches);
                    break;
                case DEFAULT:
                    break;
            }
        }
        return launches;
    }

    public ArrayList<Launch> getAllLaunches() throws Exception {
        ArrayList<Launch> result = new ArrayList<>();
        Map<String, Object> out = RestUtils.responseToMap(Rest.get(SPACEX_API_URL + "launches/past")
                .onError(evt -> refWrk.showError(evt.getError().getMessage()))
                .acceptJson()
                .getAsBytes());

        if (getRockets().isEmpty() || getLaunchpads().isEmpty()) {
            setLaunchPads(getAllLaunchPads());
            setRockets(getAllRockets());
        }
        if (!out.isEmpty()) {
            List<Map<String, Object>> responses = (List<Map<String, Object>>) out.get(API_JSON_ROOT);
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
        Map<String, Object> rockets = RestUtils.responseToMap(Rest.get(SPACEX_API_URL + "rockets")
                .onError(evt -> refWrk.showError(evt.getError().getMessage()))
                .acceptJson()
                .getAsBytes()
        );
        if (!rockets.isEmpty()) {
            List<Map<String, Object>> responses = (List<Map<String, Object>>) rockets.get(API_JSON_ROOT);
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
        Map<String, Object> launchPads = RestUtils.responseToMap(Rest.get(SPACEX_API_URL + "launchpads")
                .onError(evt -> refWrk.showError(evt.getError().getMessage()))
                .acceptJson()
                .getAsBytes()
        );
        if (!launchPads.isEmpty()) {
            List<Map<String, Object>> responses = (List<Map<String, Object>>) launchPads.get(API_JSON_ROOT);
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


    /**
     * @param response
     * @return
     * @throws Exception
     */
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

    public void setRefWrk(Wrk refWrk) {
        this.refWrk = refWrk;
    }

    public ArrayList<LaunchPad> getLaunchpads() {
        return launchPads;
    }

    public ArrayList<Rocket> getRockets() {
        return rockets;
    }

    public void setLaunchPads(ArrayList<LaunchPad> launchPads) {
        this.launchPads = launchPads;
    }

    public void setRockets(ArrayList<Rocket> rockets) {
        this.rockets = rockets;
    }
}
