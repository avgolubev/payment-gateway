
package ru.multicard.paymentgateway;

import lombok.NoArgsConstructor;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@NoArgsConstructor
public class ServletInitializer extends SpringBootServletInitializer {
  
	@Override
	protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		return application.sources(PaymentGatewayApplication.class);
	}

}
