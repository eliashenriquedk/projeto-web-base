package com.stefanini.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.stefanini.dao.abstracao.GenericDAO;
import com.stefanini.model.Endereco;
import com.stefanini.model.Perfil;
import com.stefanini.model.Pessoa;


public class EnderecoDAO extends GenericDAO<Endereco, Long> {

	public EnderecoDAO() {
		super(Endereco.class);
	}
	
	// utilizando jpql com modelo antigo
	public Optional<List<Endereco>> buscarSePessoaPossuiEndereco(Long id) {
		Query query = getEntityManager().createQuery(" select ends from Endereco ends where ends.idPessoa = :idPessoa ");
		query.setParameter("idPessoa", id);
		return Optional.of(query.getResultList());
	}
	
	
}
