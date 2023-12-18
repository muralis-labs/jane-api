package br.com.muralis.inbound.controller;

import br.com.muralis.core.dto.colaborador.fisico.AtualizarColaboradorFisicoCommand;
import br.com.muralis.core.dto.colaborador.fisico.CadastrarColaboradorFisicoCommand;
import br.com.muralis.core.usecase.colaborador.fisico.*;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

@Path("/v1/colaboradores/fisico")
public class ColaboradorFisicoController {

	@Inject
	CadastrarColaboradorFisico cadastrarColaboradorFisico;

	@Inject
	AtualizarColaboradorFisico atualizarColaboradorFisico;

	@Inject
	BuscarColaboradorFisico buscarColaboradorFisico;

	@Inject
	ExcluirColaboradorFisico excluirColaboradorFisico;

	@Inject
	ListarColaboradoresFisico listarColaboradoresFisico;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(@Valid CadastrarColaboradorFisicoCommand command) {
		var colaborador = cadastrarColaboradorFisico.execute(command);
		return Response.status(Response.Status.CREATED)
			.entity(colaborador)
			.location(UriBuilder.fromResource(ColaboradorFisicoController.class).path(colaborador.getId()).build())
			.build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response atualizar(@Valid AtualizarColaboradorFisicoCommand command, @PathParam("id") String id) {
		var colaborador = atualizarColaboradorFisico.execute(id, command);
		return Response.status(Response.Status.OK)
			.entity(colaborador)
			.location(UriBuilder.fromResource(ColaboradorFisicoController.class).path(colaborador.getId()).build())
			.build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response buscar(@PathParam("id") String id) {
		var colaborador = buscarColaboradorFisico.execute(id);
		return Response.ok(colaborador).build();
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response excluir(@PathParam("id") String id) {
		excluirColaboradorFisico.execute(id);
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
		var colaboradores = listarColaboradoresFisico.execute(page, size);
		return Response.ok(colaboradores).build();
	}

}
