package com.alpha.web;


import com.alpha.bean.Brand;
import com.alpha.bean.Product;
import com.alpha.bean.ProductFeatures;
import com.alpha.repository.BrandRepository;
import com.alpha.repository.ProductFeaturesRepository;
import com.alpha.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/products")
public class ProductController implements ErrorController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductFeaturesRepository productFeaturesRepository;

    @Autowired
    private BrandRepository brandRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Product>> products() {
        return new ResponseEntity<>
                (productRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
        Product product = productRepository.findOne(productId);

        if (product != null)
            return new ResponseEntity<>
                    (product, HttpStatus.OK);
        else
            return new ResponseEntity<>
                    ((Product) null, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{productId}/features", method = RequestMethod.GET)
    public ResponseEntity<Collection<ProductFeatures>> getProductFeatures(@PathVariable Long productId) {
        Product product = productRepository.findOne(productId);

        if (product != null)
            return new ResponseEntity<>
                    (product.getProductFeatures(), HttpStatus.OK);
        else
            return new ResponseEntity<>
                    ((Collection<ProductFeatures>) null, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{productId}/feature/{featureId}", method = RequestMethod.GET)
    public ResponseEntity<ProductFeatures> getFeature(@PathVariable Long productId,
                                                      @PathVariable Long featureId) {
        ProductFeatures productFeatures = productFeaturesRepository.findOne(featureId);

        Set<ProductFeatures> set = productRepository.findOne(productId).getProductFeatures();

        if (set.contains(productFeatures)) {
            return new ResponseEntity<>
                    (productFeatures, HttpStatus.OK);
        } else {
            return new ResponseEntity<>
                    ((ProductFeatures) null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{productId}/feature/{featureId}", method = RequestMethod.PUT)
    public ResponseEntity<?> changeFeature(@PathVariable Long productId,
                                           @PathVariable Long featureId,
                                           @RequestParam("value") String value) {
        ProductFeatures productFeatures = productFeaturesRepository.findOne(featureId);

        Set<ProductFeatures> set = productRepository.findOne(productId).getProductFeatures();

        if (set.contains(productFeatures)) {
            productFeatures.setValue(value);
            return new ResponseEntity<Object>
                    (productFeaturesRepository.save(productFeatures), HttpStatus.OK);
        } else {
            return new ResponseEntity<>
                    (null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
    public ResponseEntity<?> changeProduct(@PathVariable Long productId,
                                           @RequestParam Long count,
                                           @RequestParam Long price) {
        Product product = productRepository.findOne(productId);

        if (product != null) {
            if (count != null)
                product.setCount(count);
            if (price != null)
                product.setPrice(price);
            return new ResponseEntity<Object>(productRepository.save(product), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{productId}/brand", method = RequestMethod.GET)
    public ResponseEntity<Brand> getBrand(@PathVariable Long productId) {
        Product product = productRepository.findOne(productId);

        if (product != null) {
            return new ResponseEntity<>(product.getBrand(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>((Brand) null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{productId}/brand", method = RequestMethod.PUT)
    public ResponseEntity<?> changeBrand(@PathVariable Long productId,
                                         @RequestParam Long brandId) {
        Brand brand = brandRepository.findOne(brandId);
        Product product = productRepository.findOne(productId);

        if (product != null && brand != null) {
            product.setBrand(brand);
            return new ResponseEntity<Object>(productRepository.save(product), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/error")
    public String error() {
        return "Error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}