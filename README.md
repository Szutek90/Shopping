_# Shopping program

This program is a tool enabling effective management of customer purchases, adapting them to individual preferences and financial possibilities, while offering extensive functions of purchasing data analysis.

![Install](https://img.shields.io/badge/install-passing-green)
![Install](https://img.shields.io/badge/coverage-95%25-light%20green)

## Technologies and libraries used

* Java
* Maven
* Lombok
* JUnit
* AssertJ

## Jacoco Coverage

![App Screenshot](src/test/resources/jacoco_raport.PNG)

## Required files

* Clients file with pattern:

  NAME;SURNAME;AGE;CASH;PREFERENCES

* Preferences with pattern:

  ID CATEGORY

* Products with pattern:
ID:NAME;QUANTITY;PRICE;CATEGORY

## Description

1. Input Files:

* clients.csv:

File containing customer data. Each line contains data of one customer in the following format: NAME, SURNAME, AGE, CASH, PREFERENCES. Preferences are numbers designating product categories that the customer wants to buy in a specific order.

* preferences.csv:

A file containing a mapping of numbers to product category names that represent customer preferences. Line format: ID CATEGORY_NAME.

* products.csv:

File with a list of available products, in the format: ID;NAME;QUANTITY_IN_STOCK;UNIT_PRICE;CATEGORY.

2. Classes:

   * Client class:

     Fields: name, surname, age, cash, preferences.
      Preferences determine what products a customer wants to buy and in what order.

   * Product Class:
    
      Fields: name, quantity in stock, unit price, category.

   * Purchase Class:

      Fields: product, quantity.

   * Shop class:

    Fields: customer list, product list, preference mapping.

    Methods: loading data from files, purchasing simulation, generating statements.


3. Purchasing Simulation Process:

* Loading Data:
  * Customers, products and preferences are loaded from the appropriate text files.

* Customer Purchases:
  * Products are selected for purchase for each customer, based on their preferences and available budget.
  * Customers buy products according to their preferences (prioritized in order of preference).
  * Customers choose products with the best price-to-quantity ratio.

* Listings:
  * The customer who purchased the most products.
  * The customer who spent the most money.
  * Product breakdown: How many times each product was purchased.
  * The most frequently and least frequently chosen products.
  * Product category popularity, sorted in descending order.

4. Additional Functionalities:

* Customer who bought the most products: Analyze customer purchase data to find the customer who bought the most products.
* Customer who spent the most money: Similar analysis to find the customer who spent the most money.
* Product Summary: Information about how many times each product was purchased by all customers.
* Product Category Popularity: Count how often products in each category were chosen and sort this data descending by popularity.