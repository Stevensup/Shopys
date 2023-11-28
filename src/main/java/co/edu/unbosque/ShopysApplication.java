package co.edu.unbosque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = { "http://localhost:4200", "*" })
public class ShopysApplication {

	public
	/**
	 * @param args
	 * @return
	 */
	static void main(String[] args) {
		SpringApplication.run(ShopysApplication.class, args);
	}

}
