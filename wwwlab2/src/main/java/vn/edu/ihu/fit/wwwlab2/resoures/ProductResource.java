package vn.edu.ihu.fit.wwwlab2.resoures;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.ihu.fit.wwwlab2.entities.InforProduct;
import vn.edu.ihu.fit.wwwlab2.models.Product;
import vn.edu.ihu.fit.wwwlab2.services.ProductService;


import java.util.List;


@Path("/products")
public class ProductResource {
    private final ProductService productService;

    public ProductResource() {
        productService=new ProductService();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(Product product) {
        boolean isSuccess = productService.create(product);
        if (isSuccess) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{productId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("productId") long productId, Product product) {
        product.setProductId(productId);
        boolean isSuccess = productService.update(product);
        if (isSuccess) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{productId}")
    public Response deleteCustomer(@PathParam("productId") long productId) {
        boolean isSuccess = productService.delete(Product.class,productId);
        if (isSuccess) {
            System.out.println();
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("productId") long productId) {
        Product product = productService.getByID(Product.class,productId);
        if(product!=null){
            System.out.println(product);
            return Response.status(Response.Status.OK).entity(product).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts() {
        List<Product> products = productService.getAll(Product.class);
        System.out.println(products);
        return Response.status(Response.Status.OK).entity(products).build();
    }

    @GET
    @Path("/getInfoProduct")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInfoProduct() {
        List<InforProduct> infoProduct = productService.getInfoProduct();
        return Response.status(Response.Status.OK).entity(infoProduct).build();
    }
}
