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

    private Wrk refWrk;

    /**
     * Constructeur de la classe WrkLaunches.
     */
    public WrkLaunches() {

    }

    public Launch getNextLaunch() throws Exception {
        Launch result = null;
        Map<String, Object> response = RestUtils.responseToMap(Rest.get(REST_SERVER_URL)
                .onError(evt -> refWrk.showError(evt.getError().getMessage()))
                .queryParam(ACTION, GET_NEXT_LAUNCH)
                .jsonContent()
                .getAsBytes()
        );
        if (!response.isEmpty()) {

            Map<String, Object> launchResponse = (Map<String, Object>) response.get("data");

            Launch launch = createLaunchObject(launchResponse);
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
        Map<String, Object> out = RestUtils.responseToMap(Rest.get(REST_SERVER_URL)
                .onError(evt -> refWrk.showError(evt.getError().getMessage()))
                .queryParam(ACTION, GET_ALL_LAUNCHES)
                .jsonContent()
                .getAsBytes());
        if (!out.isEmpty()) {
            List<Map<String, Object>> responses = (List<Map<String, Object>>) out.get("data");
            for (Map<String, Object> response : responses) {
                Launch launch = createLaunchObject(response);
                if (launch != null) {
                    result.add(launch);
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
            String img = (String) response.get("img");
            String id = (String) response.get("id");
            String name = (String) response.get("name");
            int flightNumber = (int) Double.parseDouble(response.get("flightNumber").toString());

            Map<String, Object> rocketInfos = (Map<String, Object>) response.get("rocket");
            Rocket rocket = null;

            if (rocketInfos != null) {
                String rocketId = (String) rocketInfos.get("rocketId");
                String rocketName = (String) rocketInfos.get("name");
                String type = (String) rocketInfos.get("type");
                rocket = new Rocket(rocketId, rocketName, type);
            }

            Map<String, Object> launchPadsInfo = (Map<String, Object>) response.get("launchPad");
            LaunchPad launchPad = null;

            if (launchPadsInfo != null) {
                String launchPadName = (String) launchPadsInfo.get("name");
                String fullName = (String) launchPadsInfo.get("fullName");
                String locality = (String) launchPadsInfo.get("locality");
                String region = (String) launchPadsInfo.get("region");
                String launchPadId = (String) launchPadsInfo.get("id");
                launchPad = new LaunchPad(launchPadId, launchPadName, fullName, locality, region);
            }

            Date launchDate = new SimpleDateFormat(API_FORMAT_DATE).parse(response.get("launchDate").toString());
            boolean success = Boolean.parseBoolean(response.get("success").toString());
            String details = (String) response.get("details");
            result = new Launch(img, id, name, flightNumber, rocket, launchDate, success, launchPad, details);
        }
        return result;
    }

    public void setRefWrk(Wrk refWrk) {
        this.refWrk = refWrk;
    }
}
