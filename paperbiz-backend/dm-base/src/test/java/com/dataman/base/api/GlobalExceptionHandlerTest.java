package com.dataman.base.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dataman.base.exception.DmException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

class GlobalExceptionHandlerTest {

    @RestController
    static class Dummy {
        @GetMapping("/business")
        String business() {
            throw new DmException(300, "Unit Name is mandatory.");
        }

        @GetMapping("/boom")
        String boom() {
            throw new IllegalStateException("db down");
        }
    }

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new Dummy()).setControllerAdvice(new GlobalExceptionHandler()).build();
    }

    @Test
    void businessErrorIs400WithErrorNumberAndMessage() throws Exception {
        mvc.perform(get("/business")).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorNumber").value(300))
                .andExpect(jsonPath("$.message").value("Unit Name is mandatory."));
    }

    @Test
    void otherErrorIs500WithMessage() throws Exception {
        mvc.perform(get("/boom")).andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.errorNumber").value(0))
                .andExpect(jsonPath("$.message").value("db down"));
    }
}
