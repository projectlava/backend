package com.zan.diary.rest.functional;

import com.zan.diary.rest.controller.fixture.RestDataFixture;
import com.zan.diary.rest.domain.User;

import org.junit.Test;
import org.springframework.http.*;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static junit.framework.TestCase.*;

public class userTests {

  @Test
  public void thatUsersCanBeAddedAndQueried() {

    ResponseEntity<User> entity = createUser();

    String path = entity.getHeaders().getLocation().getPath();

    assertEquals(HttpStatus.CREATED, entity.getStatusCode());
    assertTrue(path.startsWith("/diary/users/"));
    User user = entity.getBody();

    System.out.println ("The User ID is " + user.getId());
    System.out.println ("The Location is " + entity.getHeaders().getLocation());

    
    entity = getUser();

    path = entity.getHeaders().getLocation().getPath();

    assertEquals(HttpStatus.CREATED, entity.getStatusCode());
    assertTrue(path.startsWith("/diary/users/"));
    user = entity.getBody();

    System.out.println ("The User ID is " + user.getId());
    System.out.println ("The Location is " + entity.getHeaders().getLocation());
  }

  @Test
  public void thatUsersCannotBeAddedAndQueriedWithBadUser() {

    HttpEntity<String> requestEntity = new HttpEntity<String>(
        RestDataFixture.standardUserJSON(),
        getHeaders("zan" + ":" + "BADPASSWORD"));

    RestTemplate template = new RestTemplate();
    try {
      ResponseEntity<User> entity = template.postForEntity(
          "http://localhost:8080/diary/users",
          requestEntity, User.class);

      fail("Request Passed incorrectly with status " + entity.getStatusCode());
    } catch (HttpClientErrorException ex) {
      assertEquals(HttpStatus.UNAUTHORIZED, ex.getStatusCode());
    }
  }

  private ResponseEntity<User> getUser() {
	  RestTemplate template = new RestTemplate();
	  return template.getForEntity(
        "http://localhost:8080/diary/daniel",
         User.class);
  }
  
  private ResponseEntity<User> createUser() {
    HttpEntity<String> requestEntity = new HttpEntity<String>(
        RestDataFixture.standardUserJSON(),getHeaders("zan" + ":" + "diary"));

    RestTemplate template = new RestTemplate();
    return template.postForEntity(
        "http://localhost:8080/diary/users",
        requestEntity, User.class);
  }

  static HttpHeaders getHeaders(String auth) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

    byte[] encodedAuthorisation = Base64.encode(auth.getBytes());
    headers.add("Authorization", "Basic " + new String(encodedAuthorisation));

    return headers;
  }
}


