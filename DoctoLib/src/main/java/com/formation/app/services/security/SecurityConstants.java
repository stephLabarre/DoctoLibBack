package com.formation.app.services.security;

public class SecurityConstants {
        public static final String SECRET = "formation@akka.eu";
        public static final long EXPIRATION_TIME = 864_000_000; // 10 days
        public static final String TOKEN_PREFIX = "Akka";
        public static final String HEADER_STRING = "Authorization";
}
