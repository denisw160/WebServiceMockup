package me.wirries.webservicemockup.controller;

import me.wirries.webservicemockup.AbstractApplicationTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Testcase for {@link RestServiceController}.
 *
 * @author denisw
 * @version 1.0
 * @since 26.03.20
 */
class RestServiceControllerTest extends AbstractApplicationTests {

    @Autowired
    private RestServiceController restServiceController;

    @BeforeEach
    void setUp() {
        restServiceController.clean();
        Object[] list = restServiceController.list();
        assertNotNull(list);
        assertEquals(0, list.length);
    }

    @Test
    void health() {
        HealthStatus health = restServiceController.health();
        assertNotNull(health);
        assertEquals("UP", health.getState());
    }

    @Test
    void store() {
        Object[] list = restServiceController.list();
        assertNotNull(list);
        assertEquals(0, list.length);

        restServiceController.store("{\"test\": \"blah\"}");

        list = restServiceController.list();
        assertNotNull(list);
        assertEquals(1, list.length);

        restServiceController.store("{\"test\": Error \"blah\"}");

        list = restServiceController.list();
        assertNotNull(list);
        assertEquals(1, list.length);
    }

    @Test
    void list() {
        Object[] list = restServiceController.list();
        assertNotNull(list);
    }

    @Test
    void clean() {
        restServiceController.clean();
    }

}
