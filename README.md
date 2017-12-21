# market-checkout-microservice

Web App / Spring Boot / Hibernate / REST API / PostgreSQL

Market checkout application which provides functionality: 

- item scanning, 
- adding items to basket,
- returning total price of products in basket,
- discount mechanism (if you buy 2 products of the same type - the second will cost you only 50% of its price).

Every basket is connected with customer.
It is possible to add specific products to basket only if they exist in database. You can add your own products - list of URL's is at the bottom.

Application is deployed in cloud (Heroku)

You can access it here:
http://market-checkout-microservice.herokuapp.com

URL:

Products:

- Show all available products: /product/find/all
- Add your own product to database: /product/add/{productName}/{price}/{productType}*/{quantity}

(*) Here you type a value, which Java Enum will convert to a type. 
  List of types:
  
  - 1: Fruit,
  - 2: Vegetable,
  - 3: Juice,
  - 4: Soda,
  - 5: Dairy product,
  - any other: Other type

Customer:

- Show all customers: /customer/find/all
- Find customer by ID: /customer/{customerId}
- Add new customer: /customer/add/new/{firstName}/{lastName}/{email}/{password}
- Show products in customer's basket (by customer ID): /customer/{customerId}/basket
- Add product to basket (it must exist in database): /customer/{customerId}/basket/add/{productName}/{quantity}
- Return a total price for products from specific basket: /customer/{customerId}/basket/total
- Clear basket: /customer/{customerId}/basket/clear


If something is screwed up... forgive me, I'm just a junior dev :)
