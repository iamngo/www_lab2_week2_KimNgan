package vn.edu.ihu.fit.wwwlab2.resoures;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.ihu.fit.wwwlab2.models.Employee;
import vn.edu.ihu.fit.wwwlab2.services.EmployeeService;

import java.util.List;


@Path("/employees")
public class EmployeeResource {
    private final EmployeeService employeeService;

    public EmployeeResource() {
        employeeService=new EmployeeService();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEmployee(Employee employee) {
        boolean isSuccess = employeeService.create(employee);
        if (isSuccess) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{employeeId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmployee(@PathParam("employeeId") long employeeId, Employee employee) {
        employee.setId(employeeId);
        boolean isSuccess = employeeService.update(employee);
        if (isSuccess) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{employeeId}")
    public Response deleteEmployee(@PathParam("employeeId") long employeeId) {
        boolean isSuccess = employeeService.delete(employeeId);
        if (isSuccess) {
            System.out.println();
            return Response.status(Response.Status.OK).entity(true).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(false  ).build();
        }
    }

    @GET
    @Path("/{employeeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeById(@PathParam("employeeId") long employeeId) {
        Employee employee = employeeService.getByID(Employee.class,employeeId);
        if(employee!=null){
            System.out.println(employee);
            return Response.status(Response.Status.OK).entity(employee).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployees() {
        List<Employee> employeess = employeeService.getAll(Employee.class);
        System.out.println(employeess);
        return Response.status(Response.Status.OK).entity(employeess).build();
    }
}
