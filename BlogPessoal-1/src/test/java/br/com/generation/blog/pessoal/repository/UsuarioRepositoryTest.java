package br.com.generation.blog.pessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.com.generation.blog.pessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	public void start() {
		
		/*  Instancia 4 objetos do tipo Usuario e grava no Banco de Dados
		 *  se o usuario for diferente de null*/
		
		Usuario usuario = new Usuario(0, "Carolina Rivera", "carol192","carol123");
		if(usuarioRepository.findByUsuario(usuario.getUsuario()) != null)
			usuarioRepository.save(usuario);
		
		
		usuario = new Usuario(0, "Maya Roux", "mayrox","maya0102");
		if(usuarioRepository.findByUsuario(usuario.getUsuario()) != null)
			usuarioRepository.save(usuario);
		
		usuario = new Usuario(0, "Gabriela Souza", "gabiSou","souza123");
		if(usuarioRepository.findByUsuario(usuario.getUsuario()) != null)
			usuarioRepository.save(usuario);
		
		usuario = new Usuario(0, "Luke Dunphy", "Lukdu","dunphy123");
		if(usuarioRepository.findByUsuario(usuario.getUsuario()) != null)
            usuarioRepository.save(usuario);
		}
		
	@Test
	@DisplayName("💾 Retorna o nome")
	public void findByNomeRetornaNome() throws Exception {

		Usuario usuario = usuarioRepository.findFirstByNome("Carolina Rivera");
		assertTrue(usuario.getNome().equals("Carolina Rivera"));
	}
	
    
	@Test
	@DisplayName("💾 Retorna 3 usuarios")
	public void findAllByUsuarioContainingIgnoreCaseRetornaTresUsuarios() {

		/** Caso a tabela esteja com muitos dados cadastrados, este teste poderá falhar */

		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByUsuarioContainingIgnoreCase("carol192");
		assertEquals(3, listaDeUsuarios.size());
	}
	
	@AfterAll
	public void end() {
		
		/** Apaga todos os dados */
		
		usuarioRepository.deleteAll();
	}

}
