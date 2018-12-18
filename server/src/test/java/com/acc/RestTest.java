package com.acc;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void postAndGet() throws Exception {
        String json = "{\n" +
                " \"dateTime\": \"2007-12-03T10:15:30\", \n" +
                " \"amount\": \"5000\", \n" +
                " \"office\": \"Офис 9\" \n" +
                "}";
        String expectJson = "{\"officeStatResponsesList\":[{\"numberOfPayments\":1," +
                "\"totalAmount\":7.5,\"totalCommission\":5000.0,\"office\":null}]," +
                "\"dataStatResponseList\":[{\"numberOfPayments\":1,\"totalAmount\":7.5," +
                "\"totalCommission\":5000.0,\"date\":\"2007-12-03\"}]}";

        this.mockMvc.perform(post("/pay")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))

                .andExpect(jsonPath("$.commission", Matchers.is(7.5)))
                .andExpect(status().isOk())
                .andDo(print());

        this.mockMvc.perform(get("/stat")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectJson)))
                .andDo(print());
    }

}
