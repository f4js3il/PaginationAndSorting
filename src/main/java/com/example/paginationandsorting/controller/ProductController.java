package com.example.paginationandsorting.controller;

import com.example.paginationandsorting.dto.ProductDto;
import com.example.paginationandsorting.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value="products")
    public ResponseEntity<List<ProductDto>> getProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping(value="products/sort/{fields}")
    public @ResponseBody ResponseEntity<List<ProductDto>> getSortedProducts(@PathVariable List<String> fields){
        return ResponseEntity.ok(productService.getAllProductsSorted(fields));
    }

    @GetMapping(value="products/pagination/{offset}/{page}")
    public @ResponseBody ResponseEntity<List<ProductDto>> getPaginatedProducts(@PathVariable Integer offset, @PathVariable Integer page){
        return ResponseEntity.ok(productService.getPaginatedProducts(offset,page));
    }
}
