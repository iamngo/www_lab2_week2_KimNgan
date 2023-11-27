package vn.edu.ihu.fit.wwwlab2.resoures;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.ihu.fit.wwwlab2.models.ProductImage;
import vn.edu.ihu.fit.wwwlab2.services.ProductImageService;


import java.util.List;


@Path("/productImages")
public class ProductImageResource {
    private final ProductImageService productImageService;

    public ProductImageResource() {
        productImageService=new ProductImageService();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProductImage(ProductImage productImage) {
        boolean isSuccess = productImageService.create(productImage);
        if (isSuccess) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{productImageId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProductImage(@PathParam("productImageId") long productImageId, ProductImage productImage) {
        productImage.setImageId(productImageId);
        boolean isSuccess = productImageService.update(productImage);
        if (isSuccess) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{productImageId}")
    public Response deleteCustomer(@PathParam("productImageId") long productImageId) {
        boolean isSuccess = productImageService.delete(ProductImage.class,productImageId);
        if (isSuccess) {
            System.out.println();
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{productImageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductImageById(@PathParam("productImageId") long productImageId) {
        ProductImage productImage = productImageService.getByID(ProductImage.class,productImageId);
        if(productImage!=null){
            System.out.println(productImage);
            return Response.status(Response.Status.OK).entity(productImage).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProductImages() {
        List<ProductImage> productImages = productImageService.getAll(ProductImage.class);
        System.out.println(productImages);
        return Response.status(Response.Status.OK).entity(productImages).build();
    }
}
