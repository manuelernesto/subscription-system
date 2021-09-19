package com.adidas.publicservice.api;

import com.adidas.publicservice.model.Subscription;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Manuel Ernesto (manuelernest0)
 * @version 1.0
 * @date 19/09/21 14:56
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/v1/public")
public class PublicController {

    private static final String USERNAME = "manuel";
    private static final String PASSWORD = "manuelernest0";
    private final RestTemplate restTemplate;
    private final String URL = "http://subscription-service/adidas/api/v1/subscribers";

    @PostMapping
    public ResponseEntity<String> newS(@RequestBody Subscription subscription) {
        var request = getHttpEntity(subscription);
        return restTemplate.exchange(URL, HttpMethod.POST, request, String.class);
    }

    @GetMapping
    public List<Object> getAll() {
        var request = getHttpEntity();
        ResponseEntity<Object[]> rateResponse = restTemplate.exchange(URL, HttpMethod.GET, request, Object[].class);
        return List.of(rateResponse.getBody());
    }

    @GetMapping("/{email}")
    public ResponseEntity<Object> get(@PathVariable String email) {
        try {
            var request = getHttpEntity();
            ResponseEntity<Object> rateResponse = restTemplate.exchange(URL + "/" + email, HttpMethod.GET, request, Object.class);
            return ResponseEntity.ok(rateResponse.getBody());
        } catch (HttpStatusCodeException ex) {
            if (ex.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
                return ResponseEntity.notFound().build();
            }
        }

        return null;
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> del(@PathVariable String email) {
        try {
            var request = getHttpEntity();
            restTemplate.exchange(URL + "/" + email, HttpMethod.DELETE, request, Void.class);
        } catch (HttpStatusCodeException ex) {
            if (ex.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.noContent().build();
    }


    private HttpEntity getHttpEntity() {

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(USERNAME, PASSWORD);
        return new HttpEntity(headers);
    }

    private HttpEntity getHttpEntity(Subscription subscription) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(USERNAME, PASSWORD);
        return new HttpEntity(subscription, headers);
    }

}
