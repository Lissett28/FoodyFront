package com.seniorproject.foody.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seniorproject.foody.entities.Restaurant;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import okhttp3.*;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.hateoas.EntityModel;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin("http://localhost:3300")//react is at 3300
@RestController
@RequestMapping("/api/locate")
public class APIController {

    @GetMapping(value="find/{address}&{radius}",produces = "application/json")
    public String findByAddressAndRadius(@PathVariable("address") String address,
                                               @PathVariable("radius") String radius) throws IOException {




        // api constants
        String api_host = "https://api.yelp.com";
        String bussinese_path = "/v3/businesses/";
        // search terms
        String term = "dinner";
        String location = address;
        int rad = Integer.valueOf(radius);
        String search_url = api_host + bussinese_path + "search?" + "term=" + term + "&" + "location=" + location + "&" + "radius=" + rad;

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(search_url)
                .addHeader("Authorization", "Bearer <api-key-here>")
                .build();

        Response response = client.newCall(request).execute();
        /* mapping to business entity
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseBody responseBody = response.body();
        Business business = objectMapper.readValue(responseBody.string(), Busness.class);

        Assert.assertNotNull(business);
        Assert.assertEquals(sampleResponse.getName(), business.getName());
        */
        return response.body().string();
    }

}
