package com.joseluisestevez.msa.items;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@RibbonClient(name = "service-products")
public class SpringbootServiceItemsApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiceItemsApplication.class, args);
	}

}
