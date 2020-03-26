package me.wirries.webservicemockup.config;

import me.wirries.webservicemockup.AbstractApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Testcase for {@link SecurityConfiguration}.
 *
 * @author denisw
 * @version 1.0
 * @since 26.03.20
 */
class SecurityConfigurationTest extends AbstractApplicationTests {

    @Autowired
    private SecurityConfiguration securityConfiguration;

    @Test
    void configure() {
        assertNotNull(securityConfiguration);
    }

}
