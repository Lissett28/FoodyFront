package com.seniorproject.foody.controllers;

import com.seniorproject.foody.entities.Restaurant;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin("http://localhost:3300")//react is at 3300
@RestController
@RequestMapping("/api/locate")
public class APIController {

    @GetMapping(value="find/{address}&{radius}",produces = "application/json")
    public List<Object> findByAddressAndRadius(@PathVariable("address") String address,
                                                          @PathVariable("radius") String radius){




        // api constants
        String api_host = "https://api.yelp.com";
        String search_path = "/v3/businesses/search";
        String bussinese_path = "/v3/businesses/";

        // search terms
        String term = "dinner";
        String location = "Los+Angeles,+CA";
        int search_limits = 3; // limit to display 3 items

        return search(api_host,bussinese_path,term,location);
    }
    private List<Object> request(String host, String path, String url){
        url = host.concat(path);
        RestTemplate restTemplate = new RestTemplate();
        Object[] response = restTemplate.getForObject(url,Object[].class);
        return Arrays.asList(response);
    }

    private List<Object> search(String host,String path, String term,String locations){
        String search_url = "term" + term + "location" + locations + "limit" + 3;
        return request(host,path,search_url);
    }
}
