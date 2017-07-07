package com.alpha.web;


import com.alpha.bean.*;
import com.alpha.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
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

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private FeaturesRepository featuresRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Product>> showAll() {
        return new ResponseEntity<>
                (productRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/brands", method = RequestMethod.GET)
    public ResponseEntity<Set<Brand>> showBrands() {
        return new ResponseEntity<>
                (brandRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/types", method = RequestMethod.GET)
    public ResponseEntity<Set<Type>> showTypes() {
        return new ResponseEntity<>
                (typeRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> addProduct(@RequestParam Long typeId,
                                        @RequestParam Long brandId,
                                        @RequestParam String title,
                                        @RequestParam Long count,
                                        @RequestParam Long price) {
        Type type = typeRepository.findOne(typeId);
        Brand brand = brandRepository.findOne(brandId);
        Product product = new Product();
        product.setType(type);
        product.setBrand(brand);
        product.setTitle(title);
        product.setCount(count);
        product.setPrice(price);

        return new ResponseEntity<>(productRepository.save(product), HttpStatus.OK);
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

        System.out.println(product);

        if (product != null)
            return new ResponseEntity<>
                    (product.getProductFeatures(), HttpStatus.OK);
        else
            return new ResponseEntity<>
                    ((Collection<ProductFeatures>) null, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{productId}/features", method = RequestMethod.PUT)
    public ResponseEntity<?> addFeaturesToProduct(@PathVariable Long productId,
                                                @RequestParam Long featureId,
                                                @RequestParam String value) {
        Product product = productRepository.findOne(productId);
        Features feature = featuresRepository.findOne(featureId);

        ProductFeatures productFeatures = new ProductFeatures();

        if (product != null && feature != null) {
            productFeatures.setProduct(product);
            productFeatures.setFeature(feature);
            productFeatures.setValue(value);
            return new ResponseEntity<>
                    (productFeaturesRepository.save(productFeatures), HttpStatus.OK);
        } else
            return new ResponseEntity<>
                    (null, HttpStatus.NOT_FOUND);

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
            return new ResponseEntity<>(productRepository.save(product), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{productId}/title", method = RequestMethod.GET)
    public ResponseEntity<String> getTitle(@PathVariable Long productId) {
        Product product = productRepository.findOne(productId);

        if (product != null)
            return new ResponseEntity<>(product.getTitle(), HttpStatus.OK);
        else
            return new ResponseEntity<>((String) null, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{productId}/title", method = RequestMethod.PUT)
    public ResponseEntity<?> changeTitle(@PathVariable Long productId,
                                         @RequestParam @NotNull String title) {
        Product product = productRepository.findOne(productId);

        if (product != null) {
            product.setTitle(title);
            return new ResponseEntity<>(productRepository.save(product), HttpStatus.OK);
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
            return new ResponseEntity<>(productRepository.save(product), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{productId}/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        Product product = productRepository.findOne(productId);

        if (product != null) {
            productRepository.delete(product);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{productId}/type", method = RequestMethod.GET)
    public ResponseEntity<Type> getType(@PathVariable Long productId) {
        Product product = productRepository.findOne(productId);

        if (product != null) {
            return new ResponseEntity<>(product.getType(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>((Type) null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{productId}/type", method = RequestMethod.PUT)
    public ResponseEntity<?> changeType(@PathVariable Long productId,
                                        @RequestParam Long typeId) {
        Type type = typeRepository.findOne(typeId);
        Product product = productRepository.findOne(productId);

        if (product != null && type != null) {
            product.setType(type);
            return new ResponseEntity<>(productRepository.save(product), HttpStatus.OK);
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