package com.example.smartcar.teslaOath2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("teslas/are/special")
public class TeslaOath2Controller {

    private final TeslaOauth2Service teslaOauth2Service;

    public TeslaOath2Controller(TeslaOauth2Service teslaOauth2Service) {
        this.teslaOauth2Service = teslaOauth2Service;
    }


    @GetMapping("/login")
    public String initiate() {
        teslaOauth2Service.initiate();
        return "";
    }

}
