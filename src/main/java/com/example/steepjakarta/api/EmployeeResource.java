package com.example.steepjakarta.api;

import com.example.steepjakarta.domain.dataaccess.EmployeeRepository;
import com.example.steepjakarta.domain.datatransfer.ApiResponseDTO;
import com.example.steepjakarta.domain.datatransfer.CreateEmployeeDTO;
import com.example.steepjakarta.domain.datatransfer.EmployeeDTO;
import com.example.steepjakarta.domain.service.EmployeeService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.logging.Logger;

@Path("/employee")
public class EmployeeResource {
    private static final Logger log = Logger.getLogger(EmployeeResource.class.getName());

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
    public Response createEmployee(CreateEmployeeDTO employeeDTO) {
        employeeService.create(employeeDTO);
        return Response.ok(employeeDTO).build();
    }

    @PUT
    @Path("/{displayID}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmployee(@PathParam("displayID") String displayID, CreateEmployeeDTO employeeDTO) {
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
