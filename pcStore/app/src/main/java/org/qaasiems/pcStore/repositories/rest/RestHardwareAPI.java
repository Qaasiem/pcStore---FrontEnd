package org.jabaar91.pcStore.repositories.rest;

import org.jabaar91.pcStore.model.Hardware;
import org.jabaar91.pcStore.repositories.RestAPIHardware;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Faizel on 2015/09/24.
 */
public class RestHardwareAPI implements RestAPIHardware {

    final String BASE_URL = "http://computerstore-jabaar91.rhcloud.com/api/";

    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();

    public Hardware getHardwareItem (Long id) {

        final String url = BASE_URL + "hardware/" + id.toString();

        HttpEntity<Hardware> requestEntity = new HttpEntity<Hardware>(requestHeaders);
        ResponseEntity<Hardware> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Hardware.class);
        Hardware hardware = responseEntity.getBody();

        return hardware;
    }

    public List<Hardware> getAllHardwareItems() {

        final String url = BASE_URL + "hardwareList/";

        List<Hardware> hardwareList = new ArrayList<>();
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        ResponseEntity<Hardware[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Hardware[].class);
        Hardware[] results = responseEntity.getBody();

        for (Hardware hardware : results) {

            hardwareList.add(hardware);
        }

        return hardwareList;
    }

    public String insertHardwareItem(Hardware hardware) {

        final String url = BASE_URL + "hardware/create";

        HttpEntity<Hardware> requestEntity = new HttpEntity<Hardware>(hardware, requestHeaders);

        try {

            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            String result = responseEntity.getBody();

            return result;
        }
        catch (Exception e) {

            return "Hardware item already exists.";
        }
    }

    public String deleteHardwareItem(Hardware hardware) {

        final String url = BASE_URL + "hardware/delete" + hardware.getId().toString();

        try {

            HttpEntity<Hardware> requestEntity = new HttpEntity<>(hardware, requestHeaders);
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
            String result = responseEntity.getBody();

            return result;
        }
        catch (Exception ex) {

            return "Error deleting item.";
        }
    }
}
