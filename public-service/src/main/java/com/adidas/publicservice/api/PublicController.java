package com.adidas.publicservice.api;

import com.adidas.publicservice.model.Subscription;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        return restTemplate.postForEntity( URL, subscription , String.class );
    }

    @GetMapping
    public List<Object> getAll() {
        ResponseEntity<Object[]> rateResponse = restTemplate.getForEntity(URL, Object[].class);
        return List.of(rateResponse.getBody());
    }

    @GetMapping("/{email}")
    public Object get(@PathVariable String email) {
        ResponseEntity<Object> rateResponse = restTemplate.getForEntity(URL + "/" + email, Object.class);
        return rateResponse.getBody();
    }

    @DeleteMapping("/{email}")
    public void del(@PathVariable String email) {
        restTemplate.delete(URL + "/" + email);
    }

}
