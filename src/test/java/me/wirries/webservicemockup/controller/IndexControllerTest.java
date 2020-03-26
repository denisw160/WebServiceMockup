package me.wirries.webservicemockup.controller;

import me.wirries.webservicemockup.AbstractApplicationTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Testcase for {@link IndexController}.
 *
 * @author denisw
 * @version 1.0
 * @since 26.03.20
 */
class IndexControllerTest extends AbstractApplicationTests {

    @Autowired
    private IndexController indexController;

    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        response = mock(HttpServletResponse.class);
    }

    @Test
    void indexRedirect() {
        indexController.indexRedirect(response);
        verify(response).setHeader("Location", "/swagger-ui.html");
        verify(response).setStatus(HttpStatus.FOUND.value());
    }

    @Test
    void swaggerRedirect() {
        indexController.swaggerRedirect(response);
        verify(response).setHeader("Location", "/swagger-ui.html");
        verify(response).setStatus(HttpStatus.FOUND.value());
    }

}
