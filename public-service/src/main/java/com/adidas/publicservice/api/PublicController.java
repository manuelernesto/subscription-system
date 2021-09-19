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

    private final RestTemplate restTemplate;
    private final String URL = "http://subscription-service/adidas/api/v1/subscribers";

    @PostMapping
    public ResponseEntity<String> newS(@RequestBody Subscription subscription) {
        var request = getHttpEntity1(subscription);
        return restTemplate.exchange(URL, HttpMethod.POST, request, String.class);
    }

    @GetMapping
    public List<Object> getAll() {
        var request = getHttpEntity();
        ResponseEntity<Object[]> rateResponse = restTemplate.exchange(URL, HttpMethod.GET, request, Object[].class);
        return List.of(rateResponse.getBody());
    }

    @GetMapping("/{email}")
    public Object get(@PathVariable String email) {
        var request = getHttpEntity();
        ResponseEntity<Object> rateResponse = restTemplate.exchange(URL + "/" + email, HttpMethod.GET, request, Object.class);
        return rateResponse.getBody();
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
        headers.setBasicAuth("manuel", "manuelernest0");
        return new HttpEntity(headers);
    }

    private HttpEntity getHttpEntity1(Subscription subscription) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("manuel", "manuelernest0");
        return new HttpEntity(subscription, headers);
    }


}
