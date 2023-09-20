package com.oxmt.mini.project.controller;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oxmt.mini.project.model.Machine;
import com.oxmt.mini.project.service.MachineValidator;

/**
 * Please add your description here.
 *
 */
@WebMvcTest( MachineOutlierController.class)
public class MachineOutlierControllerTest
{

    @MockBean
    MachineValidator machineValidator;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void validateEmpty() throws Exception {
        Mockito.when( machineValidator.runOutlierAnalysis(List.of())).thenReturn( List.of());
        mockMvc.perform( MockMvcRequestBuilders
            .post("/api/machine/validate").content( asJsonString( List.of() ) )
                .contentType(MediaType.APPLICATION_JSON)
                .accept( MediaType.APPLICATION_JSON))
            .andDo( MockMvcResultHandlers.print() )
            .andExpect( MockMvcResultMatchers.status().isOk() );
    }

    @Test
    void validateData() throws Exception {

        Mockito.when( machineValidator.runOutlierAnalysis(List.of(
            Machine.builder().age( "30 days" ).build(),
            Machine.builder().age( "31 days" ).build(),
            Machine.builder().age( "32 days" ).build(),
            Machine.builder().age( "30 years" ).build()
        ))).thenReturn( List.of(Machine.builder().age( "30 years" ).build()));
        mockMvc.perform( MockMvcRequestBuilders
                .post("/api/machine/validate").content( asJsonString( List.of(
                    Machine.builder().age( "30 days" ).build(),
                    Machine.builder().age( "31 days" ).build(),
                    Machine.builder().age( "32 days" ).build(),
                    Machine.builder().age( "30 years" ).build()
                ) ) )
                .contentType(MediaType.APPLICATION_JSON).accept( MediaType.APPLICATION_JSON))
            .andDo( MockMvcResultHandlers.print() )
            .andExpect( MockMvcResultMatchers.status().isOk() );
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
