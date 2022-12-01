package ru.javawebinar.topjava;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javawebinar.topjava.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ResourceControllerTest extends AbstractControllerTest {

    @Test
    void testCss() throws Exception {
        perform(MockMvcRequestBuilders.get("/resources/css/style.css"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("text/css;charset=UTF-8"));
    }
}
