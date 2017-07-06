package com.alpha;

import com.alpha.bean.*;
import com.alpha.repository.BrandRepository;
import com.alpha.repository.ProductFeaturesRepository;
import com.alpha.repository.ProductRepository;
import com.alpha.repository.TypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WebApplication.class)
public class WebApplicationTests {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductRepository productRepository;

	@MockBean
	private ProductFeaturesRepository productFeaturesRepository;

	@MockBean
	private BrandRepository brandRepository;

	@MockBean
	private TypeRepository typeRepository;

	private Product product = new Product();
	private Brand brand = new Brand();
	private Type type = new Type();
	private Features features = new Features();
	private ProductFeatures productFeatures = new ProductFeatures();
	private List<Features> featuresList = new ArrayList<>();
	private Set<ProductFeatures> productFeaturesSet = new HashSet<>();

	@Test
	public void productGetOne() throws Exception {
		type.setId((long) 1);
		type.setTitle("Стиральная машина");
		brand.setId((long) 1);
		brand.setTitle("Samsung");
		features.setId((long) 1);
		features.setTitle("Габариты");
		featuresList.add(features);
		features = new Features();
		features.setId((long) 2);
		features.setTitle("Тип загрузки одежды");
		featuresList.add(features);

		product.setId((long) 1);
		product.setType(type);
		product.setBrand(brand);
		product.setTitle("Супер стиральная машина");
		product.setCount((long) 22);
		product.setPrice((long) 23500);

		productFeatures.setId((long) 1);
		productFeatures.setProduct(product);
		productFeatures.setFeature(featuresList.get(0));
		productFeatures.setValue("70x60x60");
		System.out.println("PrFR: " + productFeatures.toString());
		productFeaturesSet.add(productFeatures);
		productFeatures = new ProductFeatures();
		productFeatures.setId((long) 2);
		productFeatures.setProduct(product);
		productFeatures.setFeature(featuresList.get(1));
		productFeatures.setValue("Фронтальная загрузка");
		productFeaturesSet.add(productFeatures);

		product.setProductFeatures(productFeaturesSet);

		when(productRepository.findOne(1L)).thenReturn(product);
		mockMvc.perform(get("/products/1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().json("{\n" +
						"	\"id\": 5,\n" +
						"	\"type\": {\n" +
						"		\"id\": 3,\n" +
						"		\"title\": \"Стиральная машина\"\n" +
						"	},\n" +
						"	\"brand\": {\n" +
						"		\"id\": 3,\n" +
						"		\"title\": \"Bosch\"\n" +
						"	},\n" +
						"	\"title\": \"WLG 20060\",\n" +
						"	\"count\": 19,\n" +
						"	\"price\": 15005,\n" +
						"	\"productFeatures\": [\n" +
						"		{\n" +
						"			\"id\": 9,\n" +
						"			\"feature\": {\n" +
						"				\"id\": 5,\n" +
						"				\"title\": \"Габариты\"\n" +
						"			},\n" +
						"			\"value\": \"60x50x40\"\n" +
						"		},\n" +
						"		{\n" +
						"			\"id\": 10,\n" +
						"			\"feature\": {\n" +
						"				\"id\": 6,\n" +
						"				\"title\": \"Тип загрузки одежды\"\n" +
						"			},\n" +
						"			\"value\": \"Фронтальная загрузка\"\n" +
						"		}\n" +
						"	]\n" +
						"}"));
	}
}
