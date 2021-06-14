package br.com.generation.blog.pessoal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@RestController
@RequestMapping("/")
public class BlogPessoal1Application {

	/* Implementa um método Controller na Classe de inicialização do projeto
	 * abrindo o Swagger na raíz do projeto (http://localhost:8080: por exexmplo), 
	 * como se fosse a página inicial da Api (index ou home).*/
	
	@GetMapping
	public ModelAndView swaggerUi() {
	return new ModelAndView("redirect:/swagger-ui/");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BlogPessoal1Application.class, args);
	}

}
