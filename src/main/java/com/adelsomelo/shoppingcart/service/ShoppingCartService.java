package com.adelsomelo.shoppingcart.service;


import com.adelsomelo.shoppingcart.controller.ShoppingCartController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartService {

    private static String PRODUCT_ORANGE = "orange";
    private static String PRODUCT_APPLE = "apple";

    private static BigDecimal PRICE_APPLE = new BigDecimal("0.60");
    private static BigDecimal PRICE_ORANGE = new BigDecimal( "0.25");

    private static Logger LOGGER = LoggerFactory.getLogger(ShoppingCartController.class);

    public String checkoutProduts(String[] produts) {

        LOGGER.debug("CheckoutProduts: calculation total value {}", produts.toString());

        List<String> oranges, apples;

        int orangeCount = 0;
        int appleCount = 0;

        BigDecimal totalOrange;

        BigDecimal totalApple;

        if (produts.length == 0)
            return "Cart is empty";


        orangeCount = Arrays.asList(produts).stream().filter(
                item -> PRODUCT_ORANGE.equals(item.toLowerCase())

        ).collect(Collectors.counting()).intValue();

        appleCount = Arrays.asList(produts).stream().filter(
                item -> PRODUCT_APPLE.equals(item.toLowerCase())

        ).collect(Collectors.counting()).intValue();


        totalOrange = calcutePrice(orangeCount, PRICE_ORANGE);

        totalApple = calcutePrice(appleCount, PRICE_APPLE);


        return "£" + (totalOrange.add(totalApple)).toString();


    }


    private BigDecimal calcutePrice(int itemCount, BigDecimal price){

        LOGGER.info("Calculating product value: Qt " + itemCount + "price: " + price);

        BigDecimal result = price.multiply(new BigDecimal(itemCount));

        return result;
    }


}
