package com.stefanini.dao;

import com.stefanini.dao.abstracao.GenericDAO;
import com.stefanini.model.Perfil;

public class PerfilDAO extends GenericDAO<Perfil, Long> {
	
	public PerfilDAO() {
		super(Perfil.class);
	}
}
