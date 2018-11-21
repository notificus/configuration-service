package configuration.service.user.persistent.microservices;

import configuration.service.user.persistent.UserEntity;
import net.minidev.json.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class MicroServiceRepository {

    public UserEntity getUserFullName(String cip) {
        final String uri = String.format("http://zeus.gel.usherbrooke.ca:8080/ms/rest/etudiant_groupe?inscription=2000-01-01&cip_etudiant=%s", cip);

        UserEntity userEntity = new UserEntity();
        userEntity.setCip(cip);
        userEntity.setFirstName("PlaceHolder");
        userEntity.setLastName("PlaceHolder");

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        try {
            JSONArray json = new JSONArray(result);

            if(json.length() > 0) {
                JSONObject user = json.getJSONObject(0);

                userEntity.setFirstName(user.getString("prenom"));
                userEntity.setLastName(user.getString("nom"));
            }
        } catch (JSONException e) {

        }

        return userEntity;
    }
}
