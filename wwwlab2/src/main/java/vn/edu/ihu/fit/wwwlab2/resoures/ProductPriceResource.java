package vn.edu.ihu.fit.wwwlab2.resoures;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.ihu.fit.wwwlab2.models.ProductPrice;
import vn.edu.ihu.fit.wwwlab2.services.ProductPriceService;


import java.util.List;


@Path("/productPrices")
public class ProductPriceResource {
    private final ProductPriceService productPriceService;

    public ProductPriceResource() {
        productPriceService=new ProductPriceService();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProductPrice(ProductPrice productPrice) {
        boolean isSuccess = productPriceService.create(productPrice);
        if (isSuccess) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{productPriceId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProductPrice(@PathParam("productPriceId") long productPriceId, ProductPrice productPrice) {
        productPrice.setPriceId(productPriceId);
        boolean isSuccess = productPriceService.update(productPrice);
        if (isSuccess) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{productPriceId}")
    public Response deleteCustomer(@PathParam("productPriceId") long productPriceId) {
        boolean isSuccess = productPriceService.delete(ProductPrice.class,productPriceId);
        if (isSuccess) {
            System.out.println();
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{productPriceId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductPriceById(@PathParam("productPriceId") long productPriceId) {
        ProductPrice productPrice = productPriceService.getByID(ProductPrice.class,productPriceId);
        if(productPrice!=null){
            System.out.println(productPrice);
            return Response.status(Response.Status.OK).entity(productPrice).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProductPrices() {
        List<ProductPrice> productPrices = productPriceService.getAll(ProductPrice.class);
        System.out.println(productPrices);
        return Response.status(Response.Status.OK).entity(productPrices).build();
    }
}
