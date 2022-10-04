package com.seniorproject.foody.controllers;

import com.seniorproject.foody.entities.Restaurant;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import okhttp3.*;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.hateoas.EntityModel;
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
                .addHeader("Authorization", "Bearer g8zhjraNOTgdIRg6MdVB0uKbAx_7tMjNe2AOges9qIWvTAke1HE0Lgllj_yo88BG1mr-OSsU_AdxlngZ6OaoqV1pXhdBp4eeTw_uCDpgY6O_wZGsTscznBI-Xig3Y3Yx")
                .build();
        Response response = client.newCall(request).execute();

        return response.body().string();
    }

}
