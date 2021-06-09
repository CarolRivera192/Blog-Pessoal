package br.com.generation.blog.pessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.generation.blog.pessoal.model.UserLogin;
import br.com.generation.blog.pessoal.model.Usuario;
import br.com.generation.blog.pessoal.repository.UsuarioRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class UsuarioControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private Usuario usuario; 
	private Usuario usuarioupd;
	
	@BeforeAll
	public void start() {
		usuarioRepository.deleteAll();
		usuario = new Usuario("Carolina Rivera", "carool92","carol123");
		usuarioupd = new Usuario("Maya Roux", "mayrox","maya0102");
	}

	@Test
	@Order(1)
	public void testCadastrar() {
		HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuario);
		
		ResponseEntity<Usuario> resposta = 
				testRestTemplate
				.exchange("/usuario/cadastrar", HttpMethod.POST,request,Usuario.class);
		
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
	}
	
	@Test
	@Order(2)
	public void testLogar() { // Método iniciando primeiro que os demais??? ordem: 2,1,3
				UserLogin usuariologin = new UserLogin(usuario);
		HttpEntity<UserLogin> request = new HttpEntity<UserLogin>(usuariologin);
		
		ResponseEntity<UserLogin> resposta = 
				testRestTemplate
				.exchange("/usuario/logar", HttpMethod.POST,request,UserLogin.class);
		
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Test
	@Order(3)
	public void testGetAll() {
				ResponseEntity<String> resposta = 
				testRestTemplate.exchange("/usuario", HttpMethod.GET, null, String.class);
		
		assertEquals(HttpStatus.UNAUTHORIZED, resposta.getStatusCode());
		// Para retornar OK é preciso passar o token na header;
	}
}
