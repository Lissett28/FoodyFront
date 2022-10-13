package com.seniorproject.foody.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {


    @Override
    public boolean test(String s) {
        // TODO: Regex to validate email or we can do it on the front
        return true;
    }
}
