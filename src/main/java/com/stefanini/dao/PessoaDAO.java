package com.stefanini.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.TypedQuery;

import com.stefanini.dao.abstracao.GenericDAO;
import com.stefanini.model.Pessoa;


public class PessoaDAO extends GenericDAO<Pessoa, Long> {

	public PessoaDAO() {
		super(Pessoa.class);
	}

	//utilizando jpql na namedQuery da classe Pessoa no modo antigo
//	public Optional<List<Pessoa>> buscarEmailPessoa(String emailDaPessoa) {
//		Query query = getEntityManager().createNamedQuery("Pessoa.findByEmail");
//		query.setParameter("email", emailDaPessoa);
//		return Optional.of(query.getResultList());
//	}
	
	
	// usando typedquery
	public Optional<List<Pessoa>> buscarEmailPessoa(String emailDaPessoa) {
		TypedQuery <Pessoa> query = getEntityManager().createNamedQuery("Pessoa.findByEmail", Pessoa.class);
		query.setParameter("email", emailDaPessoa);
		return Optional.of(query.getResultList());
	}
	
	
	
	// obter lista de pessoas filtradas pela uf 
	public Optional<List<Pessoa>> obterListaPessoasUf(String uf) {
		TypedQuery <Pessoa> query = getEntityManager().createQuery("SELECT DISTINCT p FROM Pessoa p JOIN FETCH p.enderecos ends WHERE ends.uf = :uf", Pessoa.class);
		query.setParameter("uf", uf);
		return Optional.of(query.getResultList());
	} 
	
	
	// Filtar pessoa por nome e email
	public Optional<List<Pessoa>> filtroPessoaNomeEmail(String nome, String email) {
		TypedQuery <Pessoa> query = getEntityManager().createQuery("select p from Pessoa p where p.nome = :nome and p.email = :email", Pessoa.class);
		query.setParameter("nome", nome);
		query.setParameter("email", email);
		return Optional.of(query.getResultList());
	}
	
}
