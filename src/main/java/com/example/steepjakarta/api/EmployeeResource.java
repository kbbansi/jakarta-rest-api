package com.example.steepjakarta.api;

import com.example.steepjakarta.domain.datatransfer.CreateEmployeeDTO;
import com.example.steepjakarta.domain.datatransfer.EmployeeDTO;
import com.example.steepjakarta.domain.service.EmployeeService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/employee")
public class EmployeeResource {

    @Inject
    EmployeeService employeeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAll();
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public EmployeeDTO getEmployeeById(@PathParam("id") Long id) {
        return employeeService.get(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEmployee(CreateEmployeeDTO employeeDTO) {
        employeeService.create(employeeDTO);
        return Response.ok(employeeDTO).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmployee(@PathParam("id") Long id, CreateEmployeeDTO employeeDTO) {
        employeeService.update(id, employeeDTO);
        return Response.ok(employeeDTO).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEmployee(@PathParam("id") Long id) {
        employeeService.delete(id);
        return Response.ok().build();
    }
}
