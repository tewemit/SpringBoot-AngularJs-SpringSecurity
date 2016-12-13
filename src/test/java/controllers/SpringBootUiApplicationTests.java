/*
package controllers;

import com.google.gson.internal.$Gson$Types;
import FaqController;
import UserController;
import Faq;
import FaqDao;
import UserDao;
import Faq;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import com.google.gson.*;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, FaqController.class,UserController.class})
//@WebMvcTest(value = FaqController.class)
@EntityScan
@ComponentScan(basePackages = {"demo.controllers","demo.models","demo.config","demo.persisten","demo.services"})
//@ComponentScan({"demo.config","demo.controllers","demo.models","demo.repository","demo.services"})
@ComponentScan ({"Faq.class","User.class","UserDao.class","FaqDao.class","UserController.class",
		"demo.services.UserDetailService.class","FaqController.class"})
@WebAppConfiguration
@RestClientTest
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class, EmbeddedMongoAutoConfiguration.class})

public class SpringBootUiApplicationTests  {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));
	private static Gson gson = new Gson();

	@Autowired
	private MockMvc mockMvc;

	private String userName = "test";

	private HttpMessageConverter mappingJackson2HttpMessageConverter;


	private List<Faq> faqs = new ArrayList<>();

	@Mock
	private UserDao userdao;
	@Mock
	private FaqDao faqDao;



	// the webapplication context setup is usable if there is a running webserver
	@Autowired
	private WebApplicationContext webApplicationContext;



	*/
/*void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
				.findAny()
				.orElse(null);

		assertNotNull("the JSON message converter must not be null",
				this.mappingJackson2HttpMessageConverter);
	}


	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
 	//initialise some test data
 	    Faq test = new Faq();
		test.initTestData();
		this.UserDao.save(faq);


	}*//*



	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = standaloneSetup(new  UserController(), new FaqController()).build();
	}

	@Test
	public void readAllFaqs() throws Exception {

		mockMvc.perform(get("/faqs")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				//+ this.faqs.get(0).getId()))
				.andExpect(status().isOk())
				//.andExpect(content().json(faqstr))//contentType(MediaType.MULTIPART_FORM_DATA))
				//.andExpect(jsonPath("$", hasSize(4)))
				.andReturn();

				//.andExpect(jsonPath("$.category", is(this.faqs.get(1).getCategory())))
				//.andExpect(jsonPath("$.description", is("A description")));

	}

	*/
/*
	@Test
	public void addFaq() throws Exception {
		ArrayList faq = new ArrayList<>(Arrays.asList("1", "vad är far1?", "cat1", "det kommer snart"));

		Faq test = new Faq();
		test.initTestData();
		this.faqDao.save(test);
		Map<String,Object> body = new HashMap<>();
		body.put("Faq",test);
		System.out.println("Original Json in Test body is: " + gson.toJson(body));
		this.mockMvc.perform(post("/addfaq")
				//+ this.faqs.get(0).getId()))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(gson.toJson(body))

				)
				//.andExpect(status().isOk())
				.andExpect(status().isCreated())
				//.andExpect(jsonPath("$", notNullValue()))
				.andReturn();
		//.andExpect(jsonPath("$.id", is(this.faqs.get(0).getId().intvalue())))
		//.andExpect(jsonPath("$.category", is(this.faqs.get(1).getCategory())))
		//.andExpect(jsonPath("$.description", is("A description")));

	}

*//*


	@Test
	public void contextLoads() {
	}
	public void testFaqs(){
		//ArrayList testarray=

	}
	*/
/*
// Test mysql stuff

	@Test
	public void getUsers() throws Exception
	{
		//Iterable<demo.model.User> users = userRepo.findAll();
		//assertEquals("Did not get all games", 3, gson.toJson(users).length());
		mockMvc.perform(get("/users"))
				.andExpect(status().isOk());
		//.andExpect(content().contentType(contentType));
		//.andExpect(jsonPath("$"),hasItem(user1));
	}

*//*


}
*/
