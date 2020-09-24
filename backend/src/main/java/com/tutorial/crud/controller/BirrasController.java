package com.tutorial.crud.controller;


import com.tutorial.crud.dto.MeetingInfo;
import com.tutorial.crud.jwt.JWTTokenFilter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/birras")
@CrossOrigin

public class BirrasController {
    private final static Logger logger= LoggerFactory.getLogger(JWTTokenFilter.class);

    @Value("${beersPerPack}")
    private int beersPerPack;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/calcular")
    public ResponseEntity calcular(@Valid @RequestBody MeetingInfo meetingInfo, BindingResult bindingResult) throws IOException {
        double sixPacksNeeded = 0;
        double birrasNeeded = 0;
        logger.info(meetingInfo.toString());

        double temperature = meetingInfo.getTemperature();
        int numberOfPeople = meetingInfo.getNumberOfPeople();

        if(temperature >= 20 && temperature <= 24 ){
            birrasNeeded = numberOfPeople * 1;
            sixPacksNeeded = round(birrasNeeded/beersPerPack);

        }
        if(temperature < 20 ){
            birrasNeeded = round(numberOfPeople * 0.75);
            sixPacksNeeded = round(birrasNeeded/beersPerPack);

        }

        if(temperature > 24 ){
            birrasNeeded = numberOfPeople * 3;
            sixPacksNeeded = round(birrasNeeded/beersPerPack);

        }
        int birrasNeededR = (int) birrasNeeded;
        int sixPacksNeededR = (int) sixPacksNeeded;
        BirrasResponse birrasResponse = new BirrasResponse(sixPacksNeededR,birrasNeededR,beersPerPack);

        return  new ResponseEntity(birrasResponse, HttpStatus.OK);

    }
    private int round(double d){
        double dAbs = Math.abs(d);
        int i = (int) dAbs;
        double result = dAbs - (double) i;
        if(result<0.5){
            return d<0 ? -i : i;
        }else{
            return d<0 ? -(i+1) : i+1;
        }
    }
    private class BirrasResponse{
        private int sixPacksNeeded = 0;
        private int birrasNeeded = 0;
        private int birrasPerPack = 0;

        public int getBirrasPerPack() {
            return birrasPerPack;
        }

        public void setBirrasPerPack(int birrasPerPack) {
            this.birrasPerPack = birrasPerPack;
        }

        public BirrasResponse(int sixPacksNeeded, int birrasNeeded, int birrasPerPack) {
            this.sixPacksNeeded = sixPacksNeeded;
            this.birrasNeeded = birrasNeeded;
            this.birrasPerPack = birrasPerPack;
        }

        public BirrasResponse(int sixPacksNeeded, int birrasNeeded) {
            this.sixPacksNeeded = sixPacksNeeded;
            this.birrasNeeded = birrasNeeded;
        }

        public int getSixPacksNeeded() {
            return sixPacksNeeded;
        }

        public void setSixPacksNeeded(int sixPacksNeeded) {
            this.sixPacksNeeded = sixPacksNeeded;
        }

        public int getBirrasNeeded() {
            return birrasNeeded;
        }

        public void setBirrasNeeded(int birrasNeeded) {
            this.birrasNeeded = birrasNeeded;
        }

        private BirrasResponse(){

        }
    }
}

