package vn.edu.ihu.fit.wwwlab2.resoures;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.ihu.fit.wwwlab2.models.OrderDetail;
import vn.edu.ihu.fit.wwwlab2.services.OrderDetailService;


import java.util.List;


@Path("/orderDetails")
public class OrderDetailResource {
    private final OrderDetailService orderDetailService;

    public OrderDetailResource() {
        orderDetailService=new OrderDetailService();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrderDetail(OrderDetail orderDetail) {
        boolean isSuccess = orderDetailService.create(orderDetail);
        if (isSuccess) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{orderDetailId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOrderDetail(@PathParam("orderDetailId") long orderDetailId, OrderDetail orderDetail) {
        orderDetail.setOrderDetailId(orderDetailId);
        boolean isSuccess = orderDetailService.update(orderDetail);
        if (isSuccess) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{orderDetailId}")
    public Response deleteCustomer(@PathParam("orderDetailId") long orderDetailId) {
        boolean isSuccess = orderDetailService.delete(OrderDetail.class,orderDetailId);
        if (isSuccess) {
            System.out.println();
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{orderDetailId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderDetailById(@PathParam("orderDetailId") long orderDetailId) {
        OrderDetail orderDetail = orderDetailService.getByID(OrderDetail.class,orderDetailId);
        if(orderDetail!=null){
            System.out.println(orderDetail);
            return Response.status(Response.Status.OK).entity(orderDetail).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrderDetails() {
        List<OrderDetail> orderDetails = orderDetailService.getAll(OrderDetail.class);
        System.out.println(orderDetails);
        return Response.status(Response.Status.OK).entity(orderDetails).build();
    }
}
