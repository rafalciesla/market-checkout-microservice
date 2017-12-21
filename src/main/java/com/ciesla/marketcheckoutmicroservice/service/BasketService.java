package com.ciesla.marketcheckoutmicroservice.service;

import com.ciesla.marketcheckoutmicroservice.ProductNotFoundException;
import com.ciesla.marketcheckoutmicroservice.NotEnoughProductsException;
import com.ciesla.marketcheckoutmicroservice.entity.Basket;
import com.ciesla.marketcheckoutmicroservice.entity.Product;
import com.ciesla.marketcheckoutmicroservice.repository.BasketRepository;
import com.ciesla.marketcheckoutmicroservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;


@Service
public class BasketService {

    private BasketRepository basketRepository;

    private ProductRepository productRepository;

    private DiscountService discountService;

    @Autowired
    public BasketService(BasketRepository basketRepository, ProductRepository productRepository, DiscountService discountService) {
        this.basketRepository = basketRepository;
        this.productRepository = productRepository;
        this.discountService = discountService;
    }

    @Transactional
    public Basket getBasketById(Integer id) {
        return basketRepository.findBasketById(id);
    }

    @Transactional
    public void addProductsToBasket(String productName, Integer itemsToBasket, Integer basketId) throws ProductNotFoundException, NotEnoughProductsException {

        Set<Product> foundProducts = productRepository.findProductsByNameAndBasket(productName, null);

        if(!foundProducts.isEmpty() && (foundProducts.size() >= itemsToBasket)) {
            Basket basket = basketRepository.findBasketById(basketId);
            for(Product product : foundProducts) {
                basket.getProductList().add(product);
                itemsToBasket--;
                if(itemsToBasket == 0){
                    break;
                }
            }
        } else if (!foundProducts.isEmpty() && foundProducts.size() < itemsToBasket) {
            throw new NotEnoughProductsException("Not enough products available");
        } else {
            throw new ProductNotFoundException("Product doesn't exist");
        }
    }

    @Transactional
    public Double getTotalPrice(Integer id) {
        Set<Product> products = basketRepository.findBasketById(id).getProductList();
        return discountService.getTotalPriceWithDiscounts(products);
    }

    @Transactional
    public void clear(Integer basketId) {
        Basket basket = basketRepository.findBasketById(basketId);
        Set<Product> products = basket.getProductList();
        for(Product product : products) {
            product.setBasket(null);
        }
        basketRepository.save(basket);
    }
}
