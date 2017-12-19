package com.ciesla.marketcheckoutmicroservice.service;

import com.ciesla.marketcheckoutmicroservice.ProductType;
import com.ciesla.marketcheckoutmicroservice.entity.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DiscountService {

    public Double getTotalPriceWithDiscounts(Set<Product> productList) {
        Double totalPrice = 0.0;
        ProductType currentProductType = ProductType.OTHER;

        List<Product> sortedProducts = new ArrayList<>(productList);
        sortedProducts.sort(Comparator.comparing(Product::getProductType));
        for(Product product : sortedProducts) {
            if(!product.getProductType().equals(ProductType.OTHER) && currentProductType.equals(product.getProductType())) {
                totalPrice += product.getPrice() / 2;
                currentProductType = ProductType.OTHER;
                continue;
            }
            totalPrice += product.getPrice();
            currentProductType = product.getProductType();
        }

        return totalPrice;
    }
}
