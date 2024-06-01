//CRUD inicial Validado

package com.ueg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.ueg.exception.ResourceNotFoundException;
import com.ueg.model.Cliente;
import com.ueg.repository.ClienteRepository;

@CrossOrigin (origins = "http://localhost:4200")
@RequestMapping ("/ccliente/")
@RestController
public class ClienteController {

	@Autowired
	private ClienteRepository clientRep;

	//Metodo para listar Cliente
	@GetMapping("/cliente")
	public List<Cliente> listar(){

		return this.clientRep.findAll();

	}

	//Metodo para consultar Cliente
	@GetMapping("/cliente/{idCliente}")
	public ResponseEntity<Cliente> consultar (@PathVariable Long idCliente) {

		Cliente cliente = this.clientRep.findById(idCliente).orElseThrow(()->
		new ResourceNotFoundException("Cliente não encontrado: " + idCliente));

		return ResponseEntity.ok(cliente);
	} 

	//Metodo para inserir
	@PostMapping("/cliente")
	public Cliente inserir (@RequestBody Cliente cliente) {
		return this.clientRep.save(cliente);
	}

	//Metodo para Alterar
	@PutMapping ("/cliente/{idCliente}")
	public ResponseEntity<Cliente> alterar (@PathVariable Long idCliente, @RequestBody Cliente cliente){

		Cliente clientetLocalizado = this.clientRep.findById(idCliente).orElseThrow(()->
		new ResourceNotFoundException("Cliente não encontrado: " + idCliente));

		//clientetLocalizado.setID(cliente.getID());
		clientetLocalizado.setNome(cliente.getNome());
		clientetLocalizado.setRG(cliente.getRG());
		clientetLocalizado.setOrgEmissor(cliente.getOrgEmissor());
		clientetLocalizado.setDtEmissao(cliente.getDtEmissao());
		clientetLocalizado.setCPFCNPJ(cliente.getCPFCNPJ());
		clientetLocalizado.setTelefone(cliente.getTelefone());
		clientetLocalizado.setCelular(cliente.getCelular());
		clientetLocalizado.setEmail(cliente.getEmail());
		clientetLocalizado.setSenha(cliente.getSenha());
		clientetLocalizado.setLogradouro(cliente.getLogradouro());
		clientetLocalizado.setBairro(cliente.getBairro());
		clientetLocalizado.setNumero(cliente.getNumero());
		clientetLocalizado.setComplemento(cliente.getComplemento());
		clientetLocalizado.setUF(cliente.getUF());
		clientetLocalizado.setCidade(cliente.getCidade());
		clientetLocalizado.setCep(cliente.getCep());
		//Relacionamento
		clientetLocalizado.setEmpresa(cliente.getEmpresa());
		clientetLocalizado.setOrgmilitar(cliente.getOrgmilitar());
		
		/*AVALIAR COMO DEVE SER TRATADO A QUESTÃO DO RELACIONAMENTO:
		 * AO CRIAR GET/POST PARA OS RELACIONAMENTOS, TEMOS LOOP NAS VALIDAÇÕES
		 * LOGO, DEVE-SE:
		 * 1- CRIAR UMA NOVA INSTÂNCIA MANTENDO OS DADOS E SINALIZANDO A NOVA "TITUALIDADE"
		 * 2- CRIAR UMA NOVA INSTÂNCIA EXCLUINDO OS DADOS E SINALIZANDO A NOVA "TITUALIDADE"
		 * 3- AVALIAR FORMAS DE ALTERAÇÃO DO PRÓPRIO DADO CADASTRADO (CHAVE EXTRANGEIRA)
		 * 
		 * 
		 * */
		

		Cliente atualizado = this.clientRep.save(clientetLocalizado);

		return ResponseEntity.ok(atualizado);

	}

	//Metodo para Deletar
	@DeleteMapping ("/cliente/{idCliente}")
	public ResponseEntity<Map<String, Boolean>> excluir (@PathVariable Long idCliente){

		Cliente ClienteLocalizado = this.clientRep.findById(idCliente).orElseThrow(()->
		new ResourceNotFoundException("Cliente não encontrado: " + idCliente));


		this.clientRep.delete(ClienteLocalizado);

		Map<String, Boolean> resposta = new HashMap<>();
		resposta.put("Cliente Excluida!", Boolean.TRUE);

		return ResponseEntity.ok(resposta);

	}
}
