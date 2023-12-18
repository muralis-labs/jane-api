package br.com.muralis.inbound.controller;

import br.com.muralis.core.dto.colaborador.juridico.AtualizarColaboradorJuridicoCommand;
import br.com.muralis.core.dto.colaborador.juridico.CadastrarColaboradorJuridicoCommand;
import br.com.muralis.core.usecase.colaborador.juridico.*;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

@Path("/v1/colaboradores/juridico")
public class ColaboradorJuridicoController {

	@Inject
	CadastrarColaboradorJuridico cadastrarColaboradorJuridico;

	@Inject
	AtualizarColaboradorJuridico atualizarColaboradorJuridico;

	@Inject
	BuscarColaboradorJuridico buscarColaboradorJuridico;

	@Inject
	ExcluirColaboradorJuridico excluirColaboradorJuridico;

	@Inject
	ListarColaboradoresJuridico listarColaboradoresJuridico;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(@Valid CadastrarColaboradorJuridicoCommand command) {
		var colaborador = cadastrarColaboradorJuridico.execute(command);
		return Response.status(Response.Status.CREATED)
			.entity(colaborador)
			.location(UriBuilder.fromResource(ColaboradorJuridicoController.class).path(colaborador.getId()).build())
			.build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response atualizar(@Valid AtualizarColaboradorJuridicoCommand command, @PathParam("id") String id) {
		var colaborador = atualizarColaboradorJuridico.execute(id, command);
		return Response.status(Response.Status.OK)
			.entity(colaborador)
			.location(UriBuilder.fromResource(ColaboradorJuridicoController.class).path(colaborador.getId()).build())
			.build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response buscar(@PathParam("id") String id) {
		var colaborador = buscarColaboradorJuridico.execute(id);
		return Response.ok(colaborador).build();
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response excluir(@PathParam("id") String id) {
		excluirColaboradorJuridico.execute(id);
		return Response.ok().build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listar(@QueryParam("page") Integer page, @QueryParam("size") Integer size) {
		if (page == null) {
			page = 0;
		}
		if (size == null) {
			size = 10;
		}
		var colaboradores = listarColaboradoresJuridico.execute(page, size);
		return Response.ok(colaboradores).build();
	}

}
