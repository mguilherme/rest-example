package com.guilherme.miguel.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * User Domain
 *
 * @author Miguel Guilherme
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
