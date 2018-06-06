package com.adelsomelo.shoppingcart.controller;

import com.adelsomelo.shoppingcart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCartController {


    @Autowired
    private ShoppingCartService shoppingCartService;


    @GetMapping("/")
    public String greeting(){

        return "Hello Adelso";
    }

    @PostMapping(value = "/checkout", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String checkoutProducts(@RequestBody String[] produts){

        return shoppingCartService.checkoutProduts(produts);
    }
}
