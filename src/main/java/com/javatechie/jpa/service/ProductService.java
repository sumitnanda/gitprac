package com.javatechie.jpa.service;

import com.javatechie.jpa.entity.Product;
import com.javatechie.jpa.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.PublicKey;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

//    @PostConstruct
//    public void initDB() {
//        List<Product> products = IntStream.rangeClosed(1, 200)
//                .mapToObj(i -> new Product("product" + i, new Random().nextInt(100), new Random().nextInt(50000)))
//                .collect(Collectors.toList());
//        repository.saveAll(products);
//    }


    public List<Product> findAllProducts() {
        return repository.findAll();
    }


    public List<Product> findProductsWithSorting(String field){
        return  repository.findAll(Sort.by(Sort.Direction.ASC,field));
    }


    public Page<Product> findProductsWithPagination(int offset,int pageSize){
        Page<Product> products = repository.findAll(PageRequest.of(offset, pageSize));
        return  products;
    }

    public Page<Product> findProductsWithPaginationAndSorting(int offset,int pageSize,String field){
        Page<Product> products = repository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return  products;
    }
    
    
    public List<Product> getProducts(String name, String category, Double minPrice) {
        Specification<Product> spec = Specification.where(null);

        if (name != null) {
            spec = spec.and(ProductSpecification.hasName(name));
        }
        
//        if (category != null) {
//            spec = spec.and(ProductSpecification.hasPCompanyName(category));
//        }
//        if (minPrice != null) {
//            spec = spec.and(ProductSpecification.hasPriceGreaterThan(minPrice));
//        }
        
        if (category != null) {
          spec = spec.and(ProductSpecification.belongsToBusinessGroup(category));
      }

//        List<Product> findAll = repository.findAll(spec);
//        System.out.println("list of :"+findAll);
        PageRequest withSort = PageRequest.of(0, 1).withSort(Sort.by(Sort.Direction.ASC,"name"));
       // Page<Product> findAll2 = repository.findAll(withSort);
        Page<Product> findAll3 = repository.findAll(spec, withSort);
        //System.out.println(findAll2.toString());
      
       // return findAll2.getContent();
    //    return findAll;
        return findAll3.getContent();
        //return repository.findAll(spec);
    }

}
