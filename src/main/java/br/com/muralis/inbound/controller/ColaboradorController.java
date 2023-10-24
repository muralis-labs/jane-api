package br.com.muralis.inbound.controller;

import br.com.muralis.core.dto.AtualizarColaboradorCommand;
import br.com.muralis.core.dto.CadastrarColaboradorCommand;
import br.com.muralis.core.usecase.*;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

@Path("/v1/colaboradores")
public class ColaboradorController {

    @Inject
    CadastrarColaborador cadastrarColaborador;

    @Inject
    AtualizarColaborador atualizarColaborador;

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

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response atualizar(@Valid AtualizarColaboradorCommand command, @PathParam("id") String id) {
        var colaborador = atualizarColaborador.execute(id, command);
        return Response
                .status(Response.Status.OK)
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
