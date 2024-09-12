package com.example.steepjakarta.api;

import com.example.steepjakarta.domain.datatransfer.ApiResponseDTO;
import com.example.steepjakarta.domain.datatransfer.CreateEmployeeDTO;
import com.example.steepjakarta.domain.datatransfer.EmployeeDTO;
import com.example.steepjakarta.domain.service.EmployeeService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
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
    @Path("/{displayID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeById(@PathParam("displayID") String displayID) {
        EmployeeDTO employeeDTO = employeeService.get(displayID);
        ApiResponseDTO<EmployeeDTO> response = new ApiResponseDTO<>(200, employeeDTO);
        return Response.ok(response).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEmployee(@Valid CreateEmployeeDTO employeeDTO) {
        String displayID = employeeService.create(employeeDTO);
        ApiResponseDTO<String> response = new ApiResponseDTO<>(201, "Employee Created Successfully at: http://localhost:8080/SteepJakarta/api/v1/employee/"+ displayID);
        return Response.ok(response).build();
    }

    @PUT
    @Path("/{displayID}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmployee(@PathParam("displayID") String displayID, @Valid CreateEmployeeDTO employeeDTO) {
        employeeService.update(displayID, employeeDTO);
        return Response.ok(employeeDTO).build();
    }

    @DELETE
    @Path("/{displayID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEmployee(@PathParam("displayID") String displayID) {
        employeeService.delete(displayID);
        ApiResponseDTO<String> response = new ApiResponseDTO<>(200, "Employee with ID: "+ displayID +" successfully deleted");

        return Response.ok(response).build();
    }
}
