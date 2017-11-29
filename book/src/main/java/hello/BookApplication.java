package hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@SpringBootApplication
public class BookApplication {

  @Autowired
  private ShelfService shelfService;

  @RequestMapping(value = "/available")
  public ResponseEntity<String> available() {
    log.info("Book > /available");
    String response = shelfService.someRestCall("available");

    log.info("Book > response > {}", response);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @RequestMapping(value = "/checked-out")
  public ResponseEntity<String> checkedOut() {
    log.info("Book > /checked-out");
    String response = shelfService.someRestCall("checked-out");

    log.info("Book > response > {}", response);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  public static void main(String[] args) {
    SpringApplication.run(BookApplication.class, args);
  }
}
