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

import com.stefanini.dao.EnderecoDAO;
import com.stefanini.model.Endereco;



@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class EnderecoServico implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EnderecoDAO enderecoDao;


	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Endereco salvar(@Valid Endereco entity) {
		return enderecoDao.salvar(entity);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Endereco atualizar(@Valid Endereco entity) {
		return enderecoDao.atualizar(entity);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(@Valid Long id) {
	enderecoDao.remover(id);
	}

	public Optional<List<Endereco>> getList() {
		return enderecoDao.getList();
	}
	
	public Optional<Endereco> encontrar(Long id) {
		return enderecoDao.encontrar(id);
	}
	
	

	public Optional<List<Endereco>> buscarSePessoaPossuiEndereco(Long id) {
		return enderecoDao.buscarSePessoaPossuiEndereco(id);
	}
	
	
	
}
