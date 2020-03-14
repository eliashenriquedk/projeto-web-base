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

import com.stefanini.dao.PessoaPerfilDAO;
import com.stefanini.model.PessoaPerfil;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PessoaPerfilServico implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private PessoaPerfilDAO PessoaPerfildao;

	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public PessoaPerfil salvar(@Valid PessoaPerfil pessoa) {
		return PessoaPerfildao.salvar(pessoa);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public PessoaPerfil atualizar(@Valid PessoaPerfil entity) {
		return PessoaPerfildao.atualizar(entity);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(@Valid Long id) {
		PessoaPerfildao.remover(id);
	}

	
	public Optional<List<PessoaPerfil>> getList() {
		return PessoaPerfildao.getList();
	}

	
	public Optional<PessoaPerfil> encontrar(Long id) {
		return PessoaPerfildao.encontrar(id);
	}

}
