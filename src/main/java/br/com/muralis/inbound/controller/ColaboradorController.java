package br.com.muralis.inbound.controller;

import br.com.muralis.core.usecase.colaborador.geral.BuscarColaborador;
import br.com.muralis.core.usecase.colaborador.geral.ListarColaboradores;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/v1/colaboradores")
public class ColaboradorController {

	@Inject
	BuscarColaborador buscarColaborador;

	@Inject
	ListarColaboradores listarColaboradores;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response buscar(@PathParam("id") String id) {
		var colaborador = buscarColaborador.execute(id);
		return Response.ok(colaborador).build();
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
		var colaboradores = listarColaboradores.execute(page, size);
		return Response.ok(colaboradores).build();
	}

}
