package br.com.generation.blog.pessoal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.generation.blog.pessoal.model.UserLogin;
import br.com.generation.blog.pessoal.model.Usuario;
import br.com.generation.blog.pessoal.repository.UsuarioRepository;
import br.com.generation.blog.pessoal.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	@Autowired //Injeção de dependência - Classe Service (UsuarioService) 
	private UsuarioService usuarioService;
	
	
	@Autowired //Injeção de dependência - Classe Repositorio (UsuarioRepository)
	private UsuarioRepository usuarioRepository;
	
	//===================== Consultar Usuarios =====================//
	
	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> GetAll() {
		return ResponseEntity.ok(usuarioRepository.findAll());
	}

	//===================== Consultar Id Usuarios =====================//
	
    @GetMapping("/{id}")
	public ResponseEntity<Usuario> GetById(@PathVariable long id){
		return usuarioRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());				
	}
	
    /* Endpoint de autenticação (Login)
	 * Executa o método Logar da classe de serviço e verifica se tudo deu certo */
  
	@PostMapping("/logar")
	public ResponseEntity<UserLogin> Autenticantion(@RequestBody Optional<UserLogin> user) {
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	/* Endpoint de cadastro
	 * Executa o método CadastrarUsuario da classe de serviço e verifica se tudo deu certo */
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> post (@RequestBody Usuario usuario) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(usuarioService.CadastrarUsuario(usuario));
	}
	
	//===================== Atualizar Usuarios =====================//
	
	@PutMapping("/alterar")
	public ResponseEntity<Usuario> Put(@RequestBody Usuario usuario){
		Optional<Usuario> user = usuarioService.atualizarUsuario(usuario);
		try {
			return ResponseEntity.ok(user.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
