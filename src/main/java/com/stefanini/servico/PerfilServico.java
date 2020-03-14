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
	private PerfilDAO perfilDao;
	
	
	//buscarNomePerfil serve para que seja possível incluir um perfil já existente
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Perfil salvar(@Valid Perfil perfil) throws Exception {
		if(perfilDao.buscarNomePerfil(perfil.getNome()).get().isEmpty()) {
			return perfilDao.salvar(perfil);			
		}
		throw new Exception(" Nome de Perfil já existe! ");
	}


	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Perfil atualizar(@Valid Perfil perfil) {
		return perfilDao.atualizar(perfil);
	}
	

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(@Valid Long id) {
		perfilDao.remover(id);
	}


	public Optional<List<Perfil>> getList() {
		return perfilDao.getList();
	}

	public Optional<Perfil> encontrar(Long id) {
		return perfilDao.encontrar(id);
	}
}
