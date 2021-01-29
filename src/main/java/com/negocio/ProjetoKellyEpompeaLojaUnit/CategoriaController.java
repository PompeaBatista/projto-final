package com.negocio.ProjetoKellyEpompeaLojaUnit;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.basica.ProjetoKellyEpompeaLojaUnit.Categoria;
//import com.basica.ProjetoKellyEpompeaLojaUnit.Cliente;
import com.repositorio.ProjetoKellyEpompeaLojaUnit.CategoriaRepositorio;

@Controller 
@RequestMapping(path="/categoria") 
public class CategoriaController {
	
	
	@Autowired
	private CategoriaRepositorio categoriaRepository;
	@PostMapping(path="/add") // Map ONLY POST Requests
	public @ResponseBody String addNewCategoria(@RequestParam Integer idCategoria, @RequestParam String nomeCategoria, @RequestParam Boolean ativo) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Categoria c = new Categoria();
		c.setIdCategoria(idCategoria);
		c.setNomeCategoria(nomeCategoria);
		c.setAtivo(ativo);
		

		categoriaRepository.save(c);
		return "Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Categoria> getAllUsers() {
		// This returns a JSON or XML with the users
		return categoriaRepository.findAll();
	}
	@GetMapping(path = "/find/{id}")
	public @ResponseBody Optional<Categoria> getCategoriaById(@PathVariable("id") Integer id) {
		return categoriaRepository.findById(id);
	}

	@DeleteMapping(path = "/delete/{id}")
	public @ResponseBody String deleteClienteById(@PathVariable("id") Integer id) {
		if (categoriaRepository.existsById(id)) {
			categoriaRepository.deleteById(id);
			return "Categoria excluida com Sucesso";
		}
		return "Categoria não encontrado!";
	}

	@DeleteMapping(path = "/delete/all")
	public @ResponseBody String deleteAll() {
		categoriaRepository.deleteAll();
		return "Categoria excluida com Sucesso!";
	}

	@PutMapping(path = "/update/{id}")
	public @ResponseBody String updateCategoriaById(@RequestParam Integer idCategoria, @RequestParam String nomeCategoria, @RequestParam Boolean ativo) {

		if (categoriaRepository.existsById(idCategoria)) {

			Categoria c = new Categoria();
			c.setIdCategoria(idCategoria);
			c.setNomeCategoria(nomeCategoria);
			c.setAtivo(ativo);
			
			categoriaRepository.save(c);
			return "Categoria atualizado com Sucesso!";
		}

		return "Categoria não encontrada";
	}


	

}
