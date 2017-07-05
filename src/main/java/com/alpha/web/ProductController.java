package com.alpha.web;


import com.alpha.bean.Product;
import com.alpha.bean.ProductFeatures;
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

    private final String error = "/error";

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Product>> products() {
        return new ResponseEntity<>
                (productRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ResponseEntity<Product> getProduct(@RequestParam("id") Long id) {
        Product product = productRepository.findOne(id);

        if (product != null)
            return new ResponseEntity<>
                    (product, HttpStatus.OK);
        else
            return new ResponseEntity<>
                    ((Product) null, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}/features", method = RequestMethod.GET)
    public ResponseEntity<Collection<ProductFeatures>> getProductFeatures(@PathVariable Long id) {
        Product product = productRepository.findOne(id);

        if (product != null)
            return new ResponseEntity<>
                    (product.getProductFeatures(), HttpStatus.OK);
        else
            return new ResponseEntity<>
                    ((Collection<ProductFeatures>) null, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}/features/{featureId}", method = RequestMethod.GET)
    public ResponseEntity<ProductFeatures> getFeature(@PathVariable Long id, @PathVariable Long featId) {
        ProductFeatures productFeatures = productFeaturesRepository.findOne(featId);

        Set<ProductFeatures> set = productRepository.findOne(id).getProductFeatures();

        if (set.contains(productFeatures)) {
            return new ResponseEntity<>
                    (productFeatures, HttpStatus.OK);
        } else {
            return new ResponseEntity<>
                    ((ProductFeatures) null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}/features/{featureId}", method = RequestMethod.PUT)
    public ResponseEntity<?> changeFeature
            (@PathVariable Long prodId,
             @PathVariable Long featId,
             @RequestParam("value") String value) {
        ProductFeatures productFeatures = productFeaturesRepository.findOne(featId);

        Set<ProductFeatures> set = productRepository.findOne(prodId).getProductFeatures();

        if (set.contains(productFeatures)) {
            productFeatures.setValue(value);
            return new ResponseEntity<>(productFeaturesRepository.save(productFeatures), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = error)
    public String error() {
        return "Error";
    }

    @Override
    public String getErrorPath() {
        return error;
    }
}