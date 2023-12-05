package com.prevalentware.prueba_tecnica.infrastructure.security;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Getter
@Setter
public class DecodedToken {
    private String sub;
    private String Role;

    public static DecodedToken getDecodedToken(String encodedToken) throws UnsupportedEncodingException {
        String[] pieces = encodedToken.split("\\.");
        String b64payload = pieces[1];
        String jsonString = new String(Base64.decodeBase64(b64payload), StandardCharsets.UTF_8);

        return new Gson().fromJson(jsonString, DecodedToken.class);
    }

    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
