package com.stefanini.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import com.stefanini.dao.abstracao.GenericDAO;
import com.stefanini.model.Perfil;

public class PerfilDAO extends GenericDAO<Perfil, Long> {
	
	public PerfilDAO() {
		super(Perfil.class);
	}
	
	// utilizando jpql
	public Optional<List<Perfil>> buscarNomePerfil(String nomeDoPerfil) {
		Query query = getEntityManager().createQuery(" select p from Perfil p where p.nome = :nomePerfil ");
		query.setParameter("nomePerfil", nomeDoPerfil);
		return Optional.of(query.getResultList());
	}
	
}
