package com.alpha;

import com.alpha.bean.*;
import com.alpha.repository.*;
import com.alpha.web.ProductController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.Charset;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
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

	@MockBean
	private FeaturesRepository featuresRepository;

	private Product product = new Product();
	private Brand brand = new Brand();
	private Type type = new Type();
	private Features features = new Features();
	private ProductFeatures productFeatures = new ProductFeatures();
	private List<Features> featuresList = new ArrayList<>();
	private Set<ProductFeatures> productFeaturesSet = new HashSet<>();

	@Test
	public void productGetOneTest() throws Exception {
		type.setId((long) 1);
		type.setTitle("Стиральная машина");
		brand.setId((long) 1);
		brand.setTitle("Bosch");
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
		product.setTitle("WLG 20060");
		product.setCount((long) 19);
		product.setPrice((long) 15005);

		productFeatures.setId((long) 1);
		productFeatures.setProduct(product);
		productFeatures.setFeature(featuresList.get(0));
		productFeatures.setValue("60x50x40");
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
				.contentType(contentType)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json("{\n" +
						"	\"id\": 1,\n" +
						"	\"type\": {\n" +
						"		\"id\": 1,\n" +
						"		\"title\": \"Стиральная машина\"\n" +
						"	},\n" +
						"	\"brand\": {\n" +
						"		\"id\": 1,\n" +
						"		\"title\": \"Bosch\"\n" +
						"	},\n" +
						"	\"title\": \"WLG 20060\",\n" +
						"	\"count\": 19,\n" +
						"	\"price\": 15005,\n" +
						"	\"productFeatures\": [\n" +
						"		{\n" +
						"			\"id\": 1,\n" +
						"			\"feature\": {\n" +
						"				\"id\": 1,\n" +
						"				\"title\": \"Габариты\"\n" +
						"			},\n" +
						"			\"value\": \"60x50x40\"\n" +
						"		},\n" +
						"		{\n" +
						"			\"id\": 2,\n" +
						"			\"feature\": {\n" +
						"				\"id\": 2,\n" +
						"				\"title\": \"Тип загрузки одежды\"\n" +
						"			},\n" +
						"			\"value\": \"Фронтальная загрузка\"\n" +
						"		}\n" +
						"	]\n" +
						"}"));

		System.out.println("'Product get one' test been passed!");
	}

	@Test
	public void productNotFoundTest() throws Exception {
		type.setId((long) 1);
		type.setTitle("Стиральная машина");
		brand.setId((long) 1);
		brand.setTitle("Bosch");
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
		product.setTitle("WLG 20060");
		product.setCount((long) 19);
		product.setPrice((long) 15005);

		productFeatures.setId((long) 1);
		productFeatures.setProduct(product);
		productFeatures.setFeature(featuresList.get(0));
		productFeatures.setValue("60x50x40");
		productFeaturesSet.add(productFeatures);
		productFeatures = new ProductFeatures();
		productFeatures.setId((long) 2);
		productFeatures.setProduct(product);
		productFeatures.setFeature(featuresList.get(1));
		productFeatures.setValue("Фронтальная загрузка");
		productFeaturesSet.add(productFeatures);

		product.setProductFeatures(productFeaturesSet);

		when(productRepository.findOne(1L)).thenReturn(product);
		MvcResult result = mockMvc.perform(get("/products/2")
				.contentType(contentType)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();

		int resultStatus = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status",
				HttpStatus.NOT_FOUND.value(), resultStatus);

		System.out.println("'Product not found' test been passed!");
	}

	@Test
	public void produtDeleteTest() throws Exception {
		type.setId(1L);
		type.setTitle("Стиральная машина");
		brand.setId(1L);
		brand.setTitle("Bosch");
		features.setId(1L);
		features.setTitle("Габариты");
		featuresList.add(features);
		features = new Features();
		features.setId(2L);
		features.setTitle("Тип загрузки одежды");
		featuresList.add(features);

		product.setId(1L);
		product.setType(type);
		product.setBrand(brand);
		product.setTitle("WLG 20060");
		product.setCount(19L);
		product.setPrice(15005L);

		productFeatures.setId(1L);
		productFeatures.setProduct(product);
		productFeatures.setFeature(featuresList.get(0));
		productFeatures.setValue("60x50x40");
		productFeaturesSet.add(productFeatures);
		productFeatures = new ProductFeatures();
		productFeatures.setId(2L);
		productFeatures.setProduct(product);
		productFeatures.setFeature(featuresList.get(1));
		productFeatures.setValue("Фронтальная загрузка");
		productFeaturesSet.add(productFeatures);

		product.setProductFeatures(productFeaturesSet);

		when(productRepository.findOne(1L)).thenReturn(product);
		MvcResult result = mockMvc.perform(delete("/products/1/delete"))
				.andReturn();

		int resultStatus = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), resultStatus);

		verify(productRepository).delete(product);

		System.out.println("'Product delete' test been passed!");
	}

	@Test
	public void productAddTest() throws Exception {
		MvcResult result = mockMvc.perform(put("/products?typeId=1&brandId=1&title=WLG 20060&count=19&price=15005")
				.contentType(contentType)
				.accept(MediaType.APPLICATION_JSON)
				.content(("{\n" +
						"	\"id\": 1,\n" +
						"	\"type\": {\n" +
						"		\"id\": 1,\n" +
						"		\"title\": \"Стиральная машина\"\n" +
						"	},\n" +
						"	\"brand\": {\n" +
						"		\"id\": 1,\n" +
						"		\"title\": \"Bosch\"\n" +
						"	},\n" +
						"	\"title\": \"WLG 20060\",\n" +
						"	\"count\": 19,\n" +
						"	\"price\": 15005\n" +
						"}"
				)))
				.andReturn();

		int resultStatus = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), resultStatus);

		verify(productRepository).save(any(Product.class));

		System.out.println("'Product add' test been passed!");
	}
}
