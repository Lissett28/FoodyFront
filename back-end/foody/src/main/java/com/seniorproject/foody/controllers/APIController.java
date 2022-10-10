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
import java.util.Random;

@CrossOrigin("http://localhost:4200")
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
                .addHeader("Authorization", "Bearer -cs0DFSzw1AdN4l9OS7cWTd1aUzaeXsuXa-Hjh_gv1zWo_iYFZ42d2KqRg6NfjR6Q5xikzlsIc-C46eKxVC3WHWJvQqQQVRAKMR7M7f2XqgbqqN_8ZkXT7DTt8c8Y3Yx")
                .build();

        Response response = client.newCall(request).execute();

        ObjectMapper objectMapper = new ObjectMapper();

        ResponseBody responseBody = response.body();
        ResponseResult responseResult = objectMapper.readValue(responseBody.string(), ResponseResult.class);

        responseResult = randomPicker(responseResult);
        return responseResult;
    }
    private ResponseResult randomPicker(ResponseResult responseResult){
        List<Business> businesses = responseResult.getBusinesses();
        // we would pick top 3 for example
        ResponseResult res = new ResponseResult();
        Random rand = new Random();
        res.setBusinesses(List.of(businesses.get(rand.nextInt(businesses.size()))));
        return res;
    }

}
