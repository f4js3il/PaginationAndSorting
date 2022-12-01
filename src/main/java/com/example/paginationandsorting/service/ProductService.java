package com.example.paginationandsorting.service;

import com.example.paginationandsorting.dto.ProductDto;
import com.example.paginationandsorting.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> getAllProducts(){
        return productRepository.findAll().stream().map(ProductDto:: new).collect(Collectors.toList());
    }

    public List<ProductDto> getAllProductsSorted(List<String> fields){
        List<Sort.Order> sorts = new ArrayList<>();
        fields.forEach(field-> sorts.add(new Sort.Order(Sort.Direction.ASC,field)));
        return productRepository.findAll(Sort.by(sorts)).stream().map(ProductDto:: new).collect(Collectors.toList());
    }

    public List<ProductDto> getPaginatedProducts(Integer offset, Integer page){
        return productRepository.findAll(PageRequest.of(offset,page)).stream().map(ProductDto:: new).collect(Collectors.toList());
    }

    public List<ProductDto> getPaginatedProductsAndSort(Integer offset, Integer page, String field){
        return productRepository.findAll(PageRequest.of(offset,page).withSort( Sort.by(field))).stream().map(ProductDto:: new).collect(Collectors.toList());
    }
}
