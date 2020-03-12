package com.stefanini.dao;

import com.stefanini.dao.abstracao.GenericDAO;
import com.stefanini.model.PessoaPerfil;

public class PessoaPerfilDAO extends GenericDAO<PessoaPerfil, Long> {

	public PessoaPerfilDAO() {
		super(PessoaPerfil.class);
	}
	
}
