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

import com.stefanini.dao.PessoaDAO;
import com.stefanini.model.Pessoa;
 

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PessoaServico implements Serializable {


	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private PessoaDAO pessoaDao;
	@Inject
	private EnderecoServico enderecoServico;

	
	// Busca email da pessoa obtida para verificar se email já existe na tabela pessoa.
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Pessoa salvar(@Valid Pessoa pessoa) throws Exception {
		if(pessoaDao.buscarEmailPessoa(pessoa.getEmail()).get().isEmpty()) {
			return pessoaDao.salvar(pessoa);
		}
		throw new Exception("Este email já existe");
	}


	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Pessoa atualizar(@Valid Pessoa entity) {
		return pessoaDao.atualizar(entity);
	}

	
	// Busca se pessoa possui endereco vinculado... se optional estiver vazio é porque não existe endereco vinculado e será feita a remoção
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(@Valid Long id) throws Exception {
		if(enderecoServico.buscarSePessoaPossuiEndereco(id).get().isEmpty()) {
			pessoaDao.remover(id);	
		} else {
			throw new Exception("Usuario possui endereço vinculado!");
		}
	}

	public Optional<List<Pessoa>> getList() {
		return pessoaDao.getList();
	} 
	
	public Optional<Pessoa> encontrar(Long id) {
		return pessoaDao.encontrar(id);
	}
	
	
	//Obter pessoas pela uf 
	public Optional<List<Pessoa>> obterListaPessoasUf(String uf) {
		return pessoaDao.obterListaPessoasUf(uf);
	}
	
	//Filtrar  pessoas por nome e email
	public Optional<List<Pessoa>> filtroPessoaNomeEmail(String nome, String email) {
		return pessoaDao.filtroPessoaNomeEmail(nome, email);
	}


}
