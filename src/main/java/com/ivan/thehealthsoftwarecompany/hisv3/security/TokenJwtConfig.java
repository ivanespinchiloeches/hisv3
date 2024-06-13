package com.ivan.thehealthsoftwarecompany.hisv3.security;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;


public class TokenJwtConfig {
    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final long EXPIRATION_TIME = 1800000;
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String AUTHORITY = "authority";
    public static final String AUTHORITIES = "authorities";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_SUPERADMIN = "ROLE_SUPERADMIN";
    public static final String  ROLE_SECRETARY = "ROLE_SECRETARY";
    public static final String  ROLE_EDITOR = "ROLE_EDITOR";
    public static final String  ROLE_CREATOR = "ROLE_CREATOR";

}
