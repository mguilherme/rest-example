package com.guilherme.miguel.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by mguilherme on 05-02-2017.
 */
@Data
@AllArgsConstructor
public class User {

    Long id;
    String name;
    Contact contact;

    @Data
    @AllArgsConstructor
    public static class Contact {
        String address;
        String phone;
    }

}
