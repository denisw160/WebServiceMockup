package me.wirries.webservicemockup.config;

import me.wirries.webservicemockup.AbstractApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import springfox.documentation.spring.web.plugins.Docket;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Testcase for {@link SwaggerConfig}.
 *
 * @author denisw
 * @version 1.0
 * @since 26.03.20
 */
class SwaggerConfigTest extends AbstractApplicationTests {

    @Autowired
    private SwaggerConfig swaggerConfig;

    @Test
    void api() {
        assertNotNull(swaggerConfig);
        Docket api = swaggerConfig.api();
        assertNotNull(api);
    }

}
