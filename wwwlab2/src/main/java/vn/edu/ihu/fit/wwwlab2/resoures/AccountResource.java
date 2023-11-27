package vn.edu.ihu.fit.wwwlab2.resoures;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.ihu.fit.wwwlab2.entities.Login;
import vn.edu.ihu.fit.wwwlab2.models.Account;
import vn.edu.ihu.fit.wwwlab2.services.AccountService;

import java.util.List;


@Path("/accounts")
public class AccountResource {
    private final AccountService accountService;

    public AccountResource() {
        accountService=new AccountService();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAccount(Account account) {
        boolean isSuccess = accountService.create(account);
        if (isSuccess) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Login login) {
        Account account = accountService.login(login);
        return Response.ok(account).build();
    }

    @PUT
    @Path("/{accountId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAccount(@PathParam("accountId") long accountId, Account account) {
        account.setAccountId(accountId);
        boolean isSuccess = accountService.update(account);
        if (isSuccess) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{accountId}")
    public Response deleteAccount(@PathParam("accountId") long accountId) {
        boolean isSuccess = accountService.delete(Account.class,accountId);
        if (isSuccess) {
            System.out.println();
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{accountId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountById(@PathParam("accountId") long accountId) {
        Account account = accountService.getByID(Account.class,accountId);
        if(account!=null){
            System.out.println(account);
            return Response.status(Response.Status.OK).entity(account).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/account/{phone}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountByPhone(@PathParam("phone") String phone) {
        Account account = accountService.getAccountByPhone(phone);
        return Response.ok(account).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAccounts() {
        List<Account> accounts = accountService.getAll(Account.class);
        System.out.println(accounts);
        return Response.status(Response.Status.OK).entity(accounts).build();
    }

    @GET
    @Path("/checkExist/{phoneNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkAccountExist(@PathParam("phoneNumber") String phoneNumber) {
        boolean isSuccess = accountService.checkAccountExist(phoneNumber);
        return Response.ok(isSuccess).build();
    }
}
