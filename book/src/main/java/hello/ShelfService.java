package hello;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ShelfService {

  private static final String SHELF_API = "http://localhost:8091/";

  private final RestTemplate restTemplate;

  public ShelfService(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  public String someRestCall(String urlPath) {
    return this.restTemplate.getForObject(SHELF_API + urlPath, String.class);
  }

}
