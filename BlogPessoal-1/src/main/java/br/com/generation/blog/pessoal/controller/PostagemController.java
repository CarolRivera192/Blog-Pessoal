package br.com.generation.blog.pessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.generation.blog.pessoal.model.Postagem;
import br.com.generation.blog.pessoal.repository.PostagemRepository;

@RestController //Indica que a classe é a controller(Recebe qualquer requisição HTTP e vai responde-las de acordo com os verbos)
@RequestMapping ("/postagens") //Indica um endpoint que vai ser acessado por essa classe(URL)
@CrossOrigin (origins = "*", allowedHeaders = "*") //Diz que a classe aceita requesições de qualquer origem (Front end)
public class PostagemController {
	
	@Autowired //Injeção de Dependência, passa o controle para o repository(cria e instancia objetos)
	private PostagemRepository repository;// acesso ao banco de dados

	//===================== Find All ========================//
	
	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	//=================== Find By Id ======================//
	
	@GetMapping ("/{id}")
	public ResponseEntity<Postagem> GetById(@PathVariable long id) { //PathVariable= variavel do caminho(URL) que pega o número do id
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp)) //ResponseEntity = resposta que ai returna
				.orElse(ResponseEntity.notFound().build());

	}
	
	//=================== Find By Titulo ======================//

	@GetMapping ("/titulo/{titulo}") //O que esta entre chaves é uma variavel 
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	//=================== Post Postagem ======================//
	
	@PostMapping
	public ResponseEntity<Postagem> post (@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
				
	}
	
	//=================== Put Postagem ======================//
	
	@PutMapping
	public ResponseEntity<Postagem> put (@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
				
	}
	
	//=================== Delete Postagem ======================//
	
	@DeleteMapping ("/{id}") //O que esta entre chaves é uma variavel 
	public void delete(@PathVariable long id) {// Void: não retorna nada 
		repository.deleteById(id);
	}
	

}
