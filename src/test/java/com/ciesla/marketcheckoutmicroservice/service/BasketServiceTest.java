package com.ciesla.marketcheckoutmicroservice.service;

import com.ciesla.marketcheckoutmicroservice.ProductType;
import com.ciesla.marketcheckoutmicroservice.entity.Basket;
import com.ciesla.marketcheckoutmicroservice.entity.Product;
import com.ciesla.marketcheckoutmicroservice.repository.BasketRepository;
import com.ciesla.marketcheckoutmicroservice.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BasketServiceTest {

    private BasketService testedObject;

    @Mock
    private BasketRepository mockedBasketRepository;

    @Mock
    private ProductRepository mockedProductRepository;

    @Mock
    private DiscountService mockedDiscountService;

    @Before
    public void setUp() throws Exception {
        testedObject = new BasketService(mockedBasketRepository, mockedProductRepository, mockedDiscountService);
    }

    @Test
    public void shouldReturnSpecificBasketWhenBasketIdPassed() throws Exception {
        Basket basketWeLookingFor = new Basket();
        basketWeLookingFor.setId(6);

        when(mockedBasketRepository.findBasketById(6)).thenReturn(basketWeLookingFor);

        Basket foundBasket = testedObject.getBasketById(6);

        assertThat(foundBasket).isNotNull();
        assertThat(foundBasket).isEqualTo(basketWeLookingFor);
    }

    @Test
    public void shouldReturnTotalPriceOfProductsFromBasket() throws Exception {
        Set<Product> testProducts = getProducts();
        Basket testBasket = new Basket();
        testBasket.setId(1);
        testBasket.setProductList(testProducts);

        when(mockedDiscountService.getTotalPriceWithDiscounts(testBasket.getProductList())).thenReturn(27.0);
        when(mockedBasketRepository.findBasketById(1)).thenReturn(testBasket);
        Double totalPrice = testedObject.getTotalPrice(1);

        assertThat(totalPrice).isEqualTo(27);
    }

    @Test
    public void shouldClearBasket() throws Exception {
        Basket testBasket = getBasket();

        when(mockedBasketRepository.findBasketById(6)).thenReturn(testBasket);

        testedObject.clear(6);

        verify(mockedBasketRepository).save(testBasket);
    }

    private Set<Product> getProducts() {
        Set<Product> testProducts = new HashSet<>();
        testProducts.add(new Product("testProduct1", 9.0, ProductType.OTHER));
        testProducts.add(new Product("testProduct2", 9.0, ProductType.OTHER));
        testProducts.add(new Product("testProduct3", 9.0, ProductType.OTHER));

        return testProducts;
    }

    private Basket getBasket() {
        Basket testBasket = new Basket();
        testBasket.setId(6);
        testBasket.setProductList(getProducts());
        return testBasket;
    }
}
