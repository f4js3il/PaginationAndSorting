package com.example.paginationandsorting.dto.test;

import com.example.paginationandsorting.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamOperations {

    @Autowired
    private ProductRepository productRepository;

    public void getAll(){
        List<Product> products = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
//        Obtain a list of products belongs to category “Books” with price > 100
        products.stream()
                .filter(product->product.getCategory().equals("Books"))
                .filter(pdt->pdt.getPrice()>100 )
                .collect(Collectors.toList());

//Obtain a list of order with products belong to category “Baby”
        orders.stream()
                .filter(order-> order.getProducts()
                        .stream().anyMatch(product->product.getCategory().equals("Baby"))).collect(Collectors.toList());

        //Obtain a list of product with category = “Toys” and then apply 10% discount
        products.stream().filter(product->product.getCategory().equals("Toys"))
                .map(p-> p.getPrice()).collect(Collectors.toList());


        //Get the cheapest products of “Books” category
        products.stream().filter(pdt->pdt.getCategory().equals("Books")).sorted(Comparator.comparing(Product::getPrice)).findFirst();

        //or
        products.stream().filter(pdt->pdt.getCategory().equals("Books")).min(Comparator.comparing(Product::getPrice));

        //Get the expensive products of “Books” category
        products.stream().filter(pdt->pdt.getCategory().equals("Books")).sorted(Comparator.comparing(Product::getPrice).reversed()).findFirst();

        //Get the 3 most recent placed order
        orders.stream().sorted(Comparator.comparing(Order::getOrderDate).reversed()).limit(3).collect(Collectors.toList());

        //Get a list of orders which were ordered on 15-Mar-2021, log the order records to the console and then return its product list
        orders.stream().filter(order-> order.getOrderDate().isEqual(LocalDate.of(2021, 3, 15)))
                .peek(o-> System.out.println(o.toString())).flatMap(o-> o.getProducts().stream()).distinct().collect(Collectors.toList());

        //Calculate total lump sum of all orders placed in Feb 2021
        orders.stream()
                .filter(o -> o.getOrderDate().compareTo(LocalDate.of(2021, 2, 1)) >= 0)
                .filter(o -> o.getOrderDate().compareTo(LocalDate.of(2021, 3, 1)) < 0)
                .flatMap(order -> order.getProducts().stream()).mapToDouble(product->product.getPrice()).sum();

        // Calculate order average payment placed on 14-Mar-2021
        orders.stream().filter(order-> order.getOrderDate().isEqual(LocalDate.of(2021,3,14)))
                .flatMap(order-> order.getProducts().stream()).mapToDouble(product-> product.getPrice()).average().getAsDouble();

        //Obtain a collection of statistic figures (i.e. sum, average, max, min, count) for all products of category “Books”
        products.stream().filter(p -> p.getCategory().equalsIgnoreCase("Books"))
                .mapToDouble(p -> p.getPrice()).summaryStatistics();


    }
}
