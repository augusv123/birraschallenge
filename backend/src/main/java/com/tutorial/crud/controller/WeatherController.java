package com.tutorial.crud.controller;

import com.tutorial.crud.dto.JWTDto;
import com.tutorial.crud.dto.LogginUser;
import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.jwt.JWTProvider;
import com.tutorial.crud.jwt.JWTTokenFilter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/weather")
@CrossOrigin(origins = "*")
public class WeatherController {

    private final static Logger logger= LoggerFactory.getLogger(JWTTokenFilter.class);



    @GetMapping("/today")
    public ResponseEntity prueba() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://community-open-weather-map.p.rapidapi.com/weather?type=like&units=metric&q=buenos%20aires")
                .get()
                .addHeader("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "a0d0dcd544msh064e3b40d5bbb9ap1906f7jsn97303fc88999")
                .build();


        Response response = client.newCall(request).execute();

        return  new ResponseEntity(response.body().string(),HttpStatus.OK);

    }
    @PostMapping("/getDatesWeather")
    public ResponseEntity getDatesWeather(@Valid @RequestBody String days, BindingResult bindingResult) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://community-open-weather-map.p.rapidapi.com/forecast/daily?q=san%20francisco%252Cus&lat=35&lon=139&cnt=10&units=metric%20or%20imperial")
                .get()
                .addHeader("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "a0d0dcd544msh064e3b40d5bbb9ap1906f7jsn97303fc88999")
                .build();


        Response response = client.newCall(request).execute();

        return  new ResponseEntity(response.body().string(),HttpStatus.OK);
    }

}
