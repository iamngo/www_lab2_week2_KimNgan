package vn.edu.ihu.fit.wwwlab2.resoures;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.ihu.fit.wwwlab2.entities.RequestOrderDate;
import vn.edu.ihu.fit.wwwlab2.entities.ResponseOrderByDateBetween;
import vn.edu.ihu.fit.wwwlab2.models.Order;
import vn.edu.ihu.fit.wwwlab2.repositories.OrderRepository;
import vn.edu.ihu.fit.wwwlab2.services.OrderService;


import java.util.List;


@Path("/orders")
public class OrderResource {
    private final OrderService orderService;
    private OrderRepository orderRepository;

    public OrderResource() {
        orderService = new OrderService();
        orderRepository = new OrderRepository();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrder(Order order) {
        boolean isSuccess = orderService.create(order);
        if (isSuccess) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{orderId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOrder(@PathParam("orderId") long orderId, Order order) {
        order.setOrderId(orderId);
        boolean isSuccess = orderService.update(order);
        if (isSuccess) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{orderId}")
    public Response deleteCustomer(@PathParam("orderId") long orderId) {
        boolean isSuccess = orderService.delete(Order.class,orderId);
        if (isSuccess) {
            System.out.println();
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderById(@PathParam("orderId") long orderId) {
        Order order = orderService.getByID(Order.class,orderId);
        if(order!=null){
            System.out.println(order);
            return Response.status(Response.Status.OK).entity(order).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrders() {
        List<Order> orders = orderService.getAll(Order.class);
        System.out.println(orders);
        return Response.status(Response.Status.OK).entity(orders).build();
    }

    @POST
    @Path("/orders-by-date-between")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderByDateBetWeen(RequestOrderDate requestOrderDate) {
        List<ResponseOrderByDateBetween> orders = orderRepository.getOrderByDateBetWeen(requestOrderDate);
        return Response.ok(orders).build();
    }
}
