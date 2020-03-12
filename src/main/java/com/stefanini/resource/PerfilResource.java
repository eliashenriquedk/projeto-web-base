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

import com.stefanini.model.Perfil;
import com.stefanini.servico.PerfilServico;

@Path("perfis")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PerfilResource {
	
	@Inject
	private PerfilServico perfilServico;


	@GET
	public Response obterListaPerfil() {
		return Response.ok(perfilServico.getList().get()).build();
	}

	@POST
	public Response salvarPerfil(@Valid Perfil perfil) {
		return Response.ok(perfilServico.salvar(perfil)).build();
	}
	
	@PUT
	public Response atualizarPerfil(@Valid Perfil perfil) {
		return Response.ok(perfilServico.atualizar(perfil)).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response removerPerfil(@PathParam("id") Long id) {
		perfilServico.remover(id);
		return Response.ok().build();
	}
	
	@GET
	@Path("{id}")
	public Response obterPerfil(@PathParam("id") Long id) {
		return Response.ok(perfilServico.encontrar(id).get()).build();
	}
}
