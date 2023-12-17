package br.com.muralis.inbound.controller;

import br.com.muralis.core.dto.empresa.AtualizarEmpresaCommand;
import br.com.muralis.core.dto.empresa.CadastrarEmpresaCommand;
import br.com.muralis.core.usecase.empresa.*;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

@Path("/v1/empresas")
public class EmpresaController {
    @Inject
    CadastrarEmpresa cadastrarEmpresa;

    @Inject
    AtualizarEmpresa atualizarEmpresa;

    @Inject
    BuscarEmpresa buscarEmpresa;

    @Inject
    ExcluirEmpresa excluirEmpresa;

    @Inject
    ListarEmpresas listarEmpresas;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(@Valid CadastrarEmpresaCommand command) {
        var empresa = cadastrarEmpresa.execute(command);
        return Response
                .status(Response.Status.CREATED)
                .entity(empresa)
                .location(UriBuilder.fromResource(EmpresaController.class).path(empresa.getId()).build())
                .build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response atualizar(@Valid AtualizarEmpresaCommand command, @PathParam("id") String id) {
        var empresa = atualizarEmpresa.execute(id, command);
        return Response
                .status(Response.Status.OK)
                .entity(empresa)
                .location(UriBuilder.fromResource(EmpresaController.class).path(empresa.getId()).build())
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response buscar(@PathParam("id") String id) {
        var empresa = buscarEmpresa.execute(id);
        return Response
                .ok(empresa)
                .build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response excluir(@PathParam("id") String id) {
        excluirEmpresa.execute(id);
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
        var empresas = listarEmpresas.execute(page, size);
        return Response
                .ok(empresas)
                .build();
    }
}
