package com.trinadh.ci_cd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CiCdApplication  {


	/*@GetMapping("/greetings")
	public void greetings(){
		System.out.println("hello");
	}
*/
	@GetMapping("/greetings/{name}")
	public String greetings(@PathVariable("name") String name){
		return "Hello "+name + " Congratulations you have successfully completed jenkins CI/CD Demo ..!";
	}

	public static void main(String[] args) {
		SpringApplication.run(CiCdApplication.class, args);
		System.out.println("hello");
	}
}
