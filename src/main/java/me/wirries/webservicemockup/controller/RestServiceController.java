package me.wirries.webservicemockup.controller;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

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
     * @param json object from client
     */
    @ApiOperation(
            value = "Stores the object on the server",
            notes = "Send the json object to the server. The object will stored in memory of the server."
    )
    @RequestMapping(value = "/store", method = RequestMethod.POST, consumes = "application/json")
    public void store(@RequestBody String json) {
        LOGGER.info("Receiving object: {}", json);

        boolean valid = isValid(json);
        LOGGER.info("JSON Object is valid: {}", valid);
        if (valid) {
            JsonNode parse = parse(json);
            objects.add(parse);
            LOGGER.info("{} objects received", objects.size());
        }
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

    /**
     * Check if the json string is valid.
     *
     * @param json json
     * @return TRUE if valid
     */
    private boolean isValid(final String json) {
        boolean valid = false;
        try {
            final JsonParser parser = new ObjectMapper().getFactory().createParser(json);
            //noinspection StatementWithEmptyBody
            while (parser.nextToken() != null) {
                // do nothing
            }
            valid = true;
        } catch (JsonParseException jpe) {
            LOGGER.error("Json is not valid: {}", jpe.getMessage());
        } catch (IOException ioe) {
            LOGGER.error("Json could not read", ioe);
        }

        return valid;
    }

    /**
     * Parse the string in a json node.
     *
     * @param json json as string
     * @return {{@link JsonNode}}
     */
    private JsonNode parse(String json) {
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);

        try {
            JsonNode rootNode = mapper.readTree(json);

            System.out.println("Json object has fields: ");
            Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
            while (fieldsIterator.hasNext()) {
                Map.Entry<String, JsonNode> field = fieldsIterator.next();
                System.out.println("Key: " + field.getKey() + "\t\tValue:" + field.getValue());
            }

            return rootNode;
        } catch (Exception e) {
            LOGGER.error("Json could not converted", e);
            return null;
        }
    }

}
