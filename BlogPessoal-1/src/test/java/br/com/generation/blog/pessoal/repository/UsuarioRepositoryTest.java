package br.com.generation.blog.pessoal.repository;

import org.junit.jupiter.api.BeforeAll;
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
		usuarioRepository.deleteAll();
		
		Usuario usuario = new Usuario("Carolina Rivera", "carool92","carol123");
		
		if(usuarioRepository.findByUsuario("carol.rivera@hotmail.com").isEmpty()) {
			usuarioRepository.save(usuario);
		}
		
		usuario = new Usuario("Maya Roux", "mayrox","maya0102");
		if(usuarioRepository.findByUsuario("maya.roux@hotmail.com").isEmpty()) {
			usuarioRepository.save(usuario);
		}
		
		usuario = new Usuario("Gabriela Souza", "gabiSou","souza123");
		if(usuarioRepository.findByUsuario("gabi.souza@hotmail.com").isEmpty()) {
			usuarioRepository.save(usuario);
		}
		
		usuario = new Usuario("Luke Dunphy", "Lukdu","dunphy123");
		if(usuarioRepository.findByUsuario("luke.dunphy@hotmail.com").isEmpty()) {
			usuarioRepository.save(usuario);
		}
		
		usuarioRepository.save(usuario);
	}

}
