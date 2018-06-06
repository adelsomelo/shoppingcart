package com.adelsomelo.shoppingcart;

import com.adelsomelo.shoppingcart.controller.ShoppingCartController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class ShoppingCartTest {

    @Bean
    private TestRestTemplate testRestTemplate(){

        return new TestRestTemplate();
    }

    @Bean
    private ShoppingCartController shoppingCartController(){
         return new ShoppingCartController();
    }

    private TestRestTemplate testRestTemplate = testRestTemplate();

    private ShoppingCartController shoppingController = shoppingCartController();

    @Test
    public void testContexLoads() throws Exception {
        assertThat(shoppingController).isNotNull();
    }

    @Test
    public void testEmptyCart(){
        HttpEntity<List<String>> request = new HttpEntity<>(new ArrayList<>());
        assertThat(this.testRestTemplate.postForObject("http://localhost:8081/api/checkout",
                request, String.class)).contains("Cart is empty");

    }

    @Test
    public void testOneProduct(){
        HttpEntity<List<String>> request = new HttpEntity<>(Arrays.asList("orange"));
        assertThat(this.testRestTemplate.postForObject("http://localhost:8081/api/checkout",
                request, String.class)).contains("£0.25");

    }

    @Test
    public void testMultipleCart(){
        HttpEntity<List<String>> request = new HttpEntity<>(Arrays.asList("orange", "apple", "apple"));
        assertThat(this.testRestTemplate.postForObject("http://localhost:8081/api/checkout",
                request, String.class)).contains("£1.45");

    }
}
