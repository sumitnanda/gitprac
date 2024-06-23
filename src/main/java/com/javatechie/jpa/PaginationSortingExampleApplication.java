package com.javatechie.jpa;

import com.javatechie.jpa.dto.APIResponse;
import com.javatechie.jpa.dto.TestResponseDto;
import com.javatechie.jpa.entity.Product;
import com.javatechie.jpa.repository.ProductRepository;
import com.javatechie.jpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/products")
public class PaginationSortingExampleApplication {

    @Autowired
    private ProductService service;
    
    @Autowired
    private ProductRepository repository;

    @GetMapping
    private APIResponse<List<Product>> getProducts() {
        List<Product> allProducts = service.findAllProducts();
        return new APIResponse<>(allProducts.size(), allProducts);
    }

    @GetMapping("/{field}")
    private APIResponse<List<Product>> getProductsWithSort(@PathVariable String field) {
        List<Product> allProducts = service.findProductsWithSorting(field);
        return new APIResponse<>(allProducts.size(), allProducts);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    private APIResponse<Page<Product>> getProductsWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Product> productsWithPagination = service.findProductsWithPagination(offset, pageSize);
        return new APIResponse<>(productsWithPagination.getSize(), productsWithPagination);
    }

    @GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
    private APIResponse<Page<Product>> getProductsWithPaginationAndSort(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String field) {
        Page<Product> productsWithPagination = service.findProductsWithPaginationAndSorting(offset, pageSize, field);
        int size = productsWithPagination.getSize();
        List<Product> content = productsWithPagination.getContent();
        Pageable pageable = productsWithPagination.getPageable();
        productsWithPagination.getTotalPages();
        productsWithPagination.getTotalElements();
        productsWithPagination.getTotalPages();
        productsWithPagination.getSize();
        productsWithPagination.getNumber();
        productsWithPagination.getNumberOfElements();
        productsWithPagination.getSize();
        System.out.println("content : "+content);
        System.out.println("pageble : "+pageable.toString());
        System.out.println("total pages :"+productsWithPagination.getTotalPages());
        
        
        
        return new APIResponse<>(productsWithPagination.getSize(), productsWithPagination);
    }
    
    
//    @PostMapping("/criteriaSearch")
//    public String test(@RequestBody TestResponseDto testResponseDto) {
//    	
//    	Product pr=new Product();
//         
//    	List<Product> findAll = repository.findAll();
//    	
//    	
//    	return "working ";
//    }
    
    
    @GetMapping("/criteriaSearch")
    public List<Product> getProduct() {
 //       @RequestParam(required = false) String name
//    	  @RequestParam(required = false) String category,
//          @RequestParam(required = false) Double minPrice
    	String name="product2";
    	String category="sanju";
    	Double minPrice=1234d;
    	
        return service.getProducts(name, category, minPrice);
    }



    public static void main(String[] args) {
        SpringApplication.run(PaginationSortingExampleApplication.class, args);
    }

}
