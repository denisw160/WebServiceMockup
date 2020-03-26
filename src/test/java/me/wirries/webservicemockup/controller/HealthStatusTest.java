package me.wirries.webservicemockup.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Testcase for {@link HealthStatus}.
 *
 * @author denisw
 * @version 1.0
 * @since 26.03.20
 */
class HealthStatusTest {

    private HealthStatus healthStatus;

    @BeforeEach
    void setUp() {
        healthStatus = new HealthStatus("1", "UP");
    }

    @Test
    void getVersion() {
        assertEquals("1", healthStatus.getVersion());
    }

    @Test
    void getState() {
        assertEquals("UP", healthStatus.getState());
    }

    @Test
    void testToString() {
        assertNotNull(healthStatus.toString());
    }

}
