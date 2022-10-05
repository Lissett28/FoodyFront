package com.seniorproject.foody.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seniorproject.foody.entities.Business;
import com.seniorproject.foody.entities.ResponseResult;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin("http://localhost:3300")//react is at 3300
@RestController
@RequestMapping("/api/locate")
public class APIController {

    @GetMapping(value="find/{address}&{radius}",produces = "application/json")
    public ResponseResult findByAddressAndRadius(@PathVariable("address") String address,
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

        ObjectMapper objectMapper = new ObjectMapper();

        ResponseBody responseBody = response.body();
        ResponseResult responseResult = objectMapper.readValue(responseBody.string(), ResponseResult.class);

        responseResult = pick3(responseResult);
        return responseResult;
    }
    private ResponseResult pick3(ResponseResult responseResult){
        List<Business> businesses = responseResult.getBusinesses();
        // we would pick top 3 for example
        ResponseResult res = new ResponseResult();
        res.setBusinesses(businesses.subList(0,3));
        return res;
    }

}
