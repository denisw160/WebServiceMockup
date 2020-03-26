package me.wirries.webservicemockup.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * This the rest controller for handle the incoming rest data.
 */
@RestController
@Api(value = "REST API")
@RequestMapping("/api")
public class RestServiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestServiceController.class);

    @Value("${app.version}")
    private String version;

    private final ArrayList<Object> objects = new ArrayList<>();

    /**
     * Shows the current health status.
     *
     * @return health status
     */
    @ApiOperation(
            value = "Shows the current health status",
            notes = "Return the state of the application."
    )
    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public HealthStatus health() {
        return new HealthStatus(version, "UP");
    }

    /**
     * Send the json object to the server. The object will stored
     * in memory of the server.
     *
     * @param o object from client
     */
    @ApiOperation(
            value = "Stores the object on the server",
            notes = "Send the json object to the server. The object will stored in memory of the server."
    )
    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public void store(Object o) {
        objects.add(o);
        LOGGER.info("Receiving object: {}", o);
        LOGGER.info("{} objects received", objects.size());
    }

    /**
     * Receive the objects previously send to the server.
     *
     * @return Array of objects
     */
    @ApiOperation(
            value = "Return the objects from the server",
            notes = "Receive the objects previously send to the server."
    )
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object[] list() {
        return objects.toArray();
    }

    /**
     * Remove all objects from memory.
     */
    @ApiOperation(
            value = "Clear the memory",
            notes = "Remove all objects from memory."
    )
    @RequestMapping(value = "/clean", method = RequestMethod.GET)
    public void clean() {
        objects.clear();
        LOGGER.info("List of received objects cleaned");
    }

}
