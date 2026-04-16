package br.com.anderson.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "FooBar Controller", description = "Endpoints para teste de resiliência")
@Slf4j
@RestController
@RequestMapping("/book-service")
public class FooBarController {

    @GetMapping("/foo-bar")
    //@Retry(name = "default")
    //@Retry(name = "foo-bar")
    //@Retry(name = "foo-bar", fallbackMethod = "fallbackMethod")
    //@CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod")
    //@RateLimiter(name = "default")
    @Bulkhead(name = "default")
    public String fooBar() {

        log.info("Request to /foo-bar endpoint received.");
        /*var response = new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class);

        return response.getBody();*/
        return "foo-bar";
    }

    public String fallbackMethod(Exception e) {
        log.error("Fallback method called due to: {}", e.getMessage());
        return "Fallback response: Service is currently unavailable. Please try again later.";
    }
}
