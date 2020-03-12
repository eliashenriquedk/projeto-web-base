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

import com.stefanini.dao.PerfilDAO;
import com.stefanini.model.Perfil;


	
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PerfilServico implements Serializable {

	
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PerfilDAO dao;
	
	//buscarNomePerfil
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Perfil salvar(@Valid Perfil perfil) throws Exception {
		if(dao.buscarNomePerfil(perfil.getNome()).get().isEmpty()) {
			return dao.salvar(perfil);			
		}
		throw new Exception(" Nome de Perfil já existe! ");
	}


	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Perfil atualizar(@Valid Perfil perfil) {
		return dao.atualizar(perfil);
	}
	

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(@Valid Long id) {
		dao.remover(id);
	}


	public Optional<List<Perfil>> getList() {
		return dao.getList();
	}

	public Optional<Perfil> encontrar(Long id) {
		return dao.encontrar(id);
	}
}
