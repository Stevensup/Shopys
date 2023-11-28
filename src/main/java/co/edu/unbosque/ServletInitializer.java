package co.edu.unbosque;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	/**
	 * @param application
	 * @return
	 */

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ShopysApplication.class);
	}

}
