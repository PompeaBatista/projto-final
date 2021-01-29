package com.negocio.ProjetoKellyEpompeaLojaUnit;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.basica.ProjetoKellyEpompeaLojaUnit.Cliente;
import com.repositorio.ProjetoKellyEpompeaLojaUnit.ClienteRepositorio;


@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/clientes") 
public class ClienteController {

	@Autowired
	private ClienteRepositorio clienteRepository;
	
	@PostMapping(path="/add") // Map ONLY POST Requests
	@ResponseStatus(code=HttpStatus.CREATED)
	public @ResponseBody  String addNewCliente (@RequestParam String nome, 
			@RequestParam String email, 
			@RequestParam String nomeSocial, 
			@RequestParam String apelido,
			@RequestParam String cpf, 
			@RequestParam String telefone,
			@RequestParam String sexo,
			@RequestParam String dataNascimento) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Cliente c = new Cliente();
		c.setNome(nome);
		c.setEmail(email);
		c.setNomeSocial(nomeSocial);
		c.setApelido(apelido);
		c.setSexo(sexo);
		c.setDataNascimento(dataNascimento);
		c.setTelefone(telefone);
		c.setCpf(cpf);
		c.setSexo(sexo);

		clienteRepository.save(c);
		return "Cliente salvo.";
	}

	@GetMapping(path="/all")//to list
		public @ResponseBody Iterable<Cliente> getAllUsers() {
		return clienteRepository.findAll();
	}
	@GetMapping(path = "/find/{id}")
	public @ResponseBody Optional<Cliente> getClienteById(@PathVariable("id") Integer id) {
		return clienteRepository.findById(id);
	}

	@DeleteMapping(path = "/delete/{id}")
	@ResponseStatus(code=HttpStatus.ACCEPTED)
	public @ResponseBody String deleteClienteById(@PathVariable("id") Integer id) {
		if (clienteRepository.existsById(id)) {
			clienteRepository.deleteById(id);
			return "Cliente excluido!";
		}
		return "Cliente não encontrado!";
	}

	@DeleteMapping(path = "/delete/all")
	@ResponseStatus(code=HttpStatus.ACCEPTED)
	public @ResponseBody String deleteAll() {
		clienteRepository.deleteAll();
		return "Clientes excluidos.";
	}

	@PutMapping(path = "/update/{id}")
	public @ResponseBody String updateClienteById(@RequestParam String nome, @RequestParam String cpf,
			@RequestParam String email, @RequestParam String dataNascimento, @RequestParam String sexo,
			@RequestParam String nomeSocial, @RequestParam String apelido, @RequestParam String telefone,
			@PathVariable("id") Integer id) {

		if (clienteRepository.existsById(id)) {

			Cliente c = new Cliente();
			c.setNome(nome);
			c.setEmail(email);
			c.setNomeSocial(nomeSocial);
			c.setApelido(apelido);
			c.setSexo(sexo);
			c.setDataNascimento(dataNascimento);
			c.setTelefone(telefone);
			c.setCpf(cpf);

			clienteRepository.save(c);
			return "Cliente atualizado.";
		}

		return "Cliente não encontrado";
	}


}
