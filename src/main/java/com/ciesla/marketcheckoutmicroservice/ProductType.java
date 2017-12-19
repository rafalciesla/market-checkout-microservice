package com.ciesla.marketcheckoutmicroservice;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.LinkedList;
import java.util.List;

public enum ProductType {
    FRUIT, VEGETABLE, JUICE, SODA, DAIRY_PRODUCT, OTHER;

    public static ProductType fromInteger(Integer value) {
        switch (value) {
            case 1:
                return FRUIT;
            case 2:
                return VEGETABLE;
            case 3:
                return JUICE;
            case 4:
                return SODA;
            case 5:
                return DAIRY_PRODUCT;
            default:
                return OTHER;
        }
    }
}
