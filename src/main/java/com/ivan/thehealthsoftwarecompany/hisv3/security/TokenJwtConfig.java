package com.ivan.thehealthsoftwarecompany.hisv3.security;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;


public class TokenJwtConfig {
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String  ROLE_EDITOR = "ROLE_EDITOR";
    public static final String  ROLE_CREATOR = "ROLE_CREATOR";

}
