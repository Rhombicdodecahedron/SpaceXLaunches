package spacexlaunches.main.utils;

import com.codename1.io.JSONParser;
import com.codename1.io.rest.Response;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class RestUtils {

    public static Map<String, Object> responseToMap(Response<byte[]> response) throws Exception {
        Map<String, Object> result = new HashMap<>();
            if (response != null) {
                JSONParser p = new JSONParser();
                InputStreamReader r = new InputStreamReader(new ByteArrayInputStream(response.getResponseData()));
                result.putAll(p.parseJSON(r));
            }
        return result;
    }

    public static Map<String, Object> responseToMap(byte[] response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        if (response != null) {
            JSONParser p = new JSONParser();
            InputStreamReader r = new InputStreamReader(new ByteArrayInputStream(response));
            result.putAll(p.parseJSON(r));
        }
        return result;
    }

}
