package com.threepie.stocknewsticker;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class StockNewsController {

	@RequestMapping("/hell")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
