package com.restAPI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by mandeepsingh on 06/07/18.
 */
@Path("myresource")
public class VideosRestAPI {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "hello world";
    }
}
