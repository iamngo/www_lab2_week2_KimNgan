package vn.edu.ihu.fit.wwwlab2.resoures;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.ihu.fit.wwwlab2.models.Customer;
import vn.edu.ihu.fit.wwwlab2.services.CustomerService;

import java.util.List;


@Path("/customers")
public class CustomerResource {
    private final CustomerService customerService;

    public CustomerResource() {
        customerService=new CustomerService();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCustomer(Customer customer) {
        boolean isSuccess = customerService.create(customer);
        if (isSuccess) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{customerId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCustomer(@PathParam("customerId") long customerId, Customer customer) {
        customer.setId(customerId);
        boolean isSuccess = customerService.update(customer);
        if (isSuccess) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{customerId}")
    public Response deleteCustomer(@PathParam("customerId") long customerId) {
        boolean isSuccess = customerService.delete(Customer.class,customerId);
        if (isSuccess) {
            System.out.println();
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("customerId") long customerId) {
        Customer customer = customerService.getByID(Customer.class,customerId);
        if(customer!=null){
            System.out.println(customer);
            return Response.status(Response.Status.OK).entity(customer).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCustomers() {
        List<Customer> customers = customerService.getAll(Customer.class);
        System.out.println(customers);
        return Response.status(Response.Status.OK).entity(customers).build();
    }
}
