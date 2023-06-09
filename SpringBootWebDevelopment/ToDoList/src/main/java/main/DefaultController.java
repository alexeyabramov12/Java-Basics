package main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class DefaultController {

    @RequestMapping("/")
    public String index() {
        return LocalDateTime.now().toString();
    }

}
