package com.stefanini.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import com.stefanini.dao.abstracao.GenericDAO;
import com.stefanini.model.Pessoa;


public class PessoaDAO extends GenericDAO<Pessoa, Long> {

	public PessoaDAO() {
		super(Pessoa.class);
	}

	//utilizando jpql na namedQuery da classe Pessoa
	public Optional<List<Pessoa>> buscarEmailPessoa(String emailDaPessoa) {
		Query query = getEntityManager().createNamedQuery("Pessoa.findByEmail");
		query.setParameter("email", emailDaPessoa);
		return Optional.of(query.getResultList());
	}
	
}
