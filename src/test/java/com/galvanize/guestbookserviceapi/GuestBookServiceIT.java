package com.galvanize.guestbookserviceapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.guestbookserviceapi.dto.GuestBookDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureRestDocs
public class GuestBookServiceIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getAllGuestBookCommentsTest() throws Exception{

        mockMvc.perform(
                get("/guestbook"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(0))
                .andDo(print());
    }

    @Test
    void putNewGuestBookCommentTest() throws Exception{

        GuestBookDto input1 = new GuestBookDto("Iqbal", "First GuestBook Comment");

        mockMvc.perform(
                post("/guestbook/comment")
                .content(objectMapper.writeValueAsString(input1))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated());

        mockMvc.perform(
                get("/guestbook"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(1))
                .andDo(print())
        .andDo(document("guestBook",responseFields(
                fieldWithPath("[0].name").description("Name of visitor"),
                fieldWithPath("[0].comment").description("visitor comment")
        )));
    }
}
