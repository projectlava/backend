package com.zan.diary.config;

import com.zan.diary.config.CoreConfig;
import com.zan.diary.config.MVCConfig;
import com.zan.diary.rest.controller.fixture.RestDataFixture;
import com.zan.diary.rest.domain.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.zan.diary.rest.controller.fixture.RestDataFixture.standardDiaryJSON;
import static com.zan.diary.rest.controller.fixture.RestDataFixture.standardUserJSON;
import static com.zan.diary.rest.controller.fixture.RestDataFixture.standardUser1JSON;
import static com.zan.diary.rest.controller.fixture.RestDataFixture.standardUserQueryJSON;
import static com.zan.diary.rest.controller.fixture.RestDataFixture.standardDiaryQueryJSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//TODOCUMENT We have already asserted the correctness of the collaboration.
//This is to check that the wiring in MVCConfig works.
//We do this by inference, via hitting URLs in the system and checking they work as expected
//given a well known infrastructure and system state.
//this is a minimal set, as we've checked the actual behaviour of rendering, http status handling
//and URL mapping separately.

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {CoreConfig.class, JPAConfiguration.class, MongoConfiguration.class, MVCConfig.class})
public class RestDomainIntegrationTest {

  @Autowired
  WebApplicationContext wac;

  private MockMvc mockMvc;

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

//  @Test
//  public void addANewDiaryToTheSystem() throws Exception  {
//    this.mockMvc.perform(
//            post("/diary/diaries")
//                    .content(standardDiaryJSON())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .accept(MediaType.APPLICATION_JSON))
//            .andDo(print())
//            .andExpect(status().isCreated());
//  }
//  
  
//  @Test
//  public void addANewUserToTheSystem() throws Exception  {
//    this.mockMvc.perform(
//            post("/diary/users")
//                    .content(standardUserJSON())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .accept(MediaType.APPLICATION_JSON))
//            .andDo(print())
//            .andExpect(status().isCreated());
//    
//    this.mockMvc.perform(
//            post("/diary/users")
//                    .content(standardUser1JSON())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .accept(MediaType.APPLICATION_JSON))
//            .andDo(print())
//            .andExpect(status().isCreated());
//    
//    this.mockMvc.perform(
//            post("/diary/getusers")
//                    .content(standardUserQueryJSON())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .accept(MediaType.APPLICATION_JSON))
//            .andDo(print())
//            .andExpect(status().isOk());
//    
//    this.mockMvc.perform(
//            post("/diary/getusergroups")
//                    .content(standardUserQueryJSON())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .accept(MediaType.APPLICATION_JSON))
//            .andDo(print())
//            .andExpect(status().isOk());
//    }
  
  @Test
  public void addANewDiaryToTheSystem() throws Exception  {
	  
	  this.mockMvc.perform(
			  post("/diary/users")
			  .content(standardUserJSON())
			  .contentType(MediaType.APPLICATION_JSON)
			  .accept(MediaType.APPLICATION_JSON))
			  .andDo(print())
			  .andExpect(status().isCreated());

	  this.mockMvc.perform(
			  post("/diary/diaries")
			  .content(standardDiaryJSON())
			  .contentType(MediaType.APPLICATION_JSON)
			  .accept(MediaType.APPLICATION_JSON))
			  .andDo(print())
			  .andExpect(status().isCreated());
	  
	  this.mockMvc.perform(
			  post("/diary/diaries")
			  .content(standardDiaryJSON())
			  .contentType(MediaType.APPLICATION_JSON)
			  .accept(MediaType.APPLICATION_JSON))
			  .andDo(print())
			  .andExpect(status().isCreated());
	  
	  MvcResult result = this.mockMvc.perform(
			  post("/diary/getusers")
			  .content(standardUserQueryJSON())
			  .contentType(MediaType.APPLICATION_JSON)
			  .accept(MediaType.APPLICATION_JSON))
			  .andDo(print())
			  .andExpect(status().isOk())
			  .andReturn();
	  
	  String content = result.getResponse().getContentAsString();
	  int index1 = content.indexOf("\"userDiaries\":[");
	  int index2 = content.indexOf("\"",index1+14);
	  int index3 = content.indexOf("\"",index2+1);
	  String id = content.substring(index2+1,index3);
	  
	  String con = standardDiaryQueryJSON(id);
	  
	  this.mockMvc.perform(
			  post("/diary/getdiaries")
			  .content(con)
			  .contentType(MediaType.APPLICATION_JSON)
			  .accept(MediaType.APPLICATION_JSON))
			  .andDo(print())
			  .andExpect(status().isOk())
			  .andReturn();
    
  }
}
