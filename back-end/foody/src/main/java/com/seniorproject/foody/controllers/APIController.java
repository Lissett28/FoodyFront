package com.seniorproject.foody.controllers;

import com.seniorproject.foody.entities.Restaurant;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3300")//react is at 3300
@RestController
@RequestMapping("/api/locate")
public class APIController {

    @GetMapping(value="find/{address}&{radius}",produces = "application/json")
    public String findByAddressAndRadius(@PathVariable("address") String address,
                                                          @PathVariable("radius") String radius){



        return address + "and" + radius;
    }
}
