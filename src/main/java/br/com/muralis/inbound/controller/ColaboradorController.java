package br.com.muralis.inbound.controller;

import br.com.muralis.core.dto.CadastrarColaboradorCommand;
import br.com.muralis.core.usecase.BuscarColaborador;
import br.com.muralis.core.usecase.CadastrarColaborador;
import br.com.muralis.core.usecase.ExcluirColaborador;
import br.com.muralis.core.usecase.ListarColaboradores;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

@Path("/v1/colaborador")
public class ColaboradorController {

    @Inject
    CadastrarColaborador cadastrarColaborador;

    @Inject
    BuscarColaborador buscarColaborador;

    @Inject
    ExcluirColaborador excluirColaborador;

    @Inject
    ListarColaboradores listarColaboradores;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(@Valid CadastrarColaboradorCommand command) {
        var colaborador = cadastrarColaborador.execute(command);
        return Response
                .status(Response.Status.CREATED)
                .entity(colaborador)
                .location(UriBuilder.fromResource(ColaboradorController.class).path(colaborador.getId()).build())
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response buscar(@PathParam("id") String id) {
        var colaborador = buscarColaborador.execute(id);
        return Response
                .ok(colaborador)
                .build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response excluir(@PathParam("id") String id) {
        excluirColaborador.execute(id);
        return Response
                .ok()
                .build();
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
        return Response
                .ok(colaboradores)
                .build();
    }

}
