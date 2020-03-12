package com.stefanini.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import com.stefanini.dao.abstracao.GenericDAO;
import com.stefanini.model.Endereco;
import com.stefanini.model.Perfil;


public class EnderecoDAO extends GenericDAO<Endereco, Long> {

	public EnderecoDAO() {
		super(Endereco.class);
	}
	
	// utilizando jpql
	public Optional<List<Endereco>> buscarSePessoaPossuiEndereco(Long id) {
		Query query = getEntityManager().createQuery(" select ends from Endereco ends where ends.idPessoa = :idPessoa ");
		query.setParameter("idPessoa", id);
		return Optional.of(query.getResultList());
	}
}
