package com.zan.diary.rest.functional;


import com.zan.diary.rest.controller.fixture.RestDataFixture;
import com.zan.diary.rest.domain.RestDiary;

import org.junit.Test;
import org.springframework.http.*;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static junit.framework.TestCase.*;

public class diaryTests {

  @Test
  public void thatDiaryCanBeAddedAndQueried() {

    ResponseEntity<RestDiary> entity = createDiary();

    String path = entity.getHeaders().getLocation().getPath();

    assertEquals(HttpStatus.CREATED, entity.getStatusCode());
    assertTrue(path.startsWith("/diary/diaries"));
    RestDiary diary = entity.getBody();

    System.out.println ("The User ID is " + diary.getId());
    System.out.println ("The Location is " + entity.getHeaders().getLocation());
  }

  @Test
  public void thatUsersCannotBeAddedAndQueriedWithBadUser() {

    HttpEntity<String> requestEntity = new HttpEntity<String>(
        RestDataFixture.standardDiaryJSON(),
        getHeaders("zan" + ":" + "BADPASSWORD"));

    RestTemplate template = new RestTemplate();
    try {
      ResponseEntity<RestDiary> entity = template.postForEntity(
          "http://localhost:8080/diary/users",
          requestEntity, RestDiary.class);

      fail("Request Passed incorrectly with status " + entity.getStatusCode());
    } catch (HttpClientErrorException ex) {
      assertEquals(HttpStatus.UNAUTHORIZED, ex.getStatusCode());
    }
  }

  private ResponseEntity<RestDiary> createDiary() {
    HttpEntity<String> requestEntity = new HttpEntity<String>(
        RestDataFixture.standardDiaryJSON(),getHeaders("zan" + ":" + "diary"));

    RestTemplate template = new RestTemplate();
    return template.postForEntity(
        "http://localhost:8080/diary/diaries",
        requestEntity, RestDiary.class);
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


