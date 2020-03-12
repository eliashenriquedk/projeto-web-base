package com.stefanini.dao;

import com.stefanini.dao.abstracao.GenericDAO;
import com.stefanini.model.Endereco;

/**
 * O Unico objetivo da Dao 
 * @author joaopedromilhome
 *
 */
public class EnderecoDAO extends GenericDAO<Endereco, Long> {

	public EnderecoDAO() {
		super(Endereco.class);
	}

}
