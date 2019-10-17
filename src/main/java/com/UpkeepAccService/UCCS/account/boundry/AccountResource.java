package com.UpkeepAccService.UCCS.account.boundry;

import com.UpkeepAccService.UCCS.account.entity.AccountOrder;
import com.UpkeepAccService.UCCS.events.entity.AccountInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.UUID;

@Path("account")
public class AccountResource {

    @Inject
    AccountCommandService commandService;

    @Inject
    AccountQueryService queryService;

    @Context
    UriInfo uriInfo;

    @POST
    @Path("/register")
    public ResponseEntity register(JsonObject account) {
        final String username = account.getString("username", null);
        final String email = account.getString("email", null);
        final String password = account.getString("password", null);

        if (username == null || email == null || password == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        final UUID accountId = UUID.randomUUID();
        commandService.registerAccount(new AccountInfo(accountId, email, username, password));

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GET
    @Path("/login")
    public ResponseEntity login(@RequestBody JsonObject account) {
        final AccountOrder order = queryService.getAccount();
        return null;

    }

}
