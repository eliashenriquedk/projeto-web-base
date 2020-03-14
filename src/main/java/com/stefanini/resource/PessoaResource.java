package com.stefanini.resource;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.stefanini.model.Pessoa;
import com.stefanini.servico.PessoaServico;

@Path("pessoas")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PessoaResource {

	@Inject
	private PessoaServico pessoaServico;

	
	@GET
	public Response obterListaPessoas() {
		return Response.ok(pessoaServico.getList().get()).build();
	}

	@POST
	public Response salvarPessoa(@Valid Pessoa pessoa) throws Exception {
		return Response.ok(pessoaServico.salvar(pessoa)).build();
	}
	
	@PUT
	public Response atualizarPessoa(@Valid Pessoa pessoa) {
		return Response.ok(pessoaServico.atualizar(pessoa)).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response removerPessoa(@PathParam("id") Long id) throws Exception {
		pessoaServico.remover(id);
		return Response.ok().build();
	}
	
	
	@GET
	@Path("{id}")
	public Response obterPessoa(@PathParam("id") Long id) {
		return Response.ok(pessoaServico.encontrar(id).get()).build();
		//return Response.status(Status.INTERNAL_SERVER_ERROR).entity("deu ruim").build();
	}
	
	@GET
	@Path("/estado")
	public Response obterListaPessoasUf(@NotBlank @QueryParam("uf") String uf) {
		return Response.ok(pessoaServico.obterListaPessoasUf(uf).get()).build();
	}
	
	@GET
    @Path("/filtrar")
    public Response filtroPessoaNomeEmail(@QueryParam("nome") String nome, @QueryParam("email") String email) {
        return Response.ok(pessoaServico.filtroPessoaNomeEmail(nome, email).get()).build();
    }

	
	
}
