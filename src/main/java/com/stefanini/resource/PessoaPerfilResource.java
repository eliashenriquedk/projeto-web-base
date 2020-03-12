package com.stefanini.resource;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.stefanini.model.PessoaPerfil;
import com.stefanini.servico.PessoaPerfilServico;

@Path("pessoa-perfil")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PessoaPerfilResource {
	
	
	@Inject
	private PessoaPerfilServico pessoaPerfilServico;

	@GET
	public Response obterListaPerfilPessoa() {
		return Response.ok(pessoaPerfilServico.getList().get()).build();
	}

	@POST
	public Response salvarPerfilPessoa(@Valid PessoaPerfil pessoaPerfil) {
		return Response.ok(pessoaPerfilServico.salvar(pessoaPerfil)).build();
	}
	
	@PUT
	public Response atualizarPerfilPessoa(@Valid PessoaPerfil pessoaPerfil) {
		return Response.ok(pessoaPerfilServico.atualizar(pessoaPerfil)).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response removerPerfilPessoa(@PathParam("id") Long id) {
		pessoaPerfilServico.remover(id);
		return Response.ok().build();
	}
	
	
	@GET
	@Path("{id}")
	public Response obterPerfilPessoa(@PathParam("id") Long id) {
		//return Response.status(Status.INTERNAL_SERVER_ERROR).entity("deu ruim").build();
		return Response.ok(pessoaPerfilServico.encontrar(id).get()).build();
	}
}
