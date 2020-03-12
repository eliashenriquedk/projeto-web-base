package com.stefanini.servico;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.validation.Valid;

import com.stefanini.dao.PessoaDAO;
import com.stefanini.model.Pessoa;

/**
 * 
 * Classe de servico, as regras de negocio devem estar nessa classe
 * 
 * @author joaopedromilhome
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PessoaServico implements Serializable {


	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private PessoaDAO dao;
	@Inject
	private EnderecoServico enderecoService;

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Pessoa salvar(@Valid Pessoa pessoa) throws Exception {
		if(dao.buscarEmailPessoa(pessoa.getEmail()).get().isEmpty()) {
			return dao.salvar(pessoa);
		}
		throw new Exception("Este email já existe");
	}


	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Pessoa atualizar(@Valid Pessoa entity) {
		return dao.atualizar(entity);
	}


	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(@Valid Long id) throws Exception {
		if(enderecoService.buscarSePessoaPossuiEndereco(id).get().isEmpty()) {
			dao.remover(id);	
		} else {
			throw new Exception("Usuario possui endereço vinculado!");			
		}
	}


	public Optional<List<Pessoa>> getList() {
		return dao.getList();
	}
	

	public Optional<Pessoa> encontrar(Long id) {
		return dao.encontrar(id);
	}
	


}
