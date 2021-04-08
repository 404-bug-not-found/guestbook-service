package com.galvanize.guestbookserviceapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.guestbookserviceapi.dto.GuestBookDto;
import com.galvanize.guestbookserviceapi.entity.GuestBookEntity;
import com.galvanize.guestbookserviceapi.repository.GuestBookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
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

    @Autowired
    GuestBookRepository guestBookRepository;

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
                .andExpect(status().isCreated())
        .andDo(document("AddComment"));

        mockMvc.perform(
                get("/guestbook"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(1))
                .andDo(print())
        .andDo(document("GuestBook",responseFields(
                fieldWithPath("[0].name").description("Name of visitor"),
                fieldWithPath("[0].comment").description("visitor comment")
        )));
    }

    @Test
    void putDuplicateCommentTest() throws Exception{

        GuestBookDto input1 = new GuestBookDto("Iqbal", "First GuestBook Comment");

        mockMvc.perform(
                post("/guestbook/comment")
                        .content(objectMapper.writeValueAsString(input1))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andDo(print());

        mockMvc.perform(
                post("/guestbook/comment")
                        .content(objectMapper.writeValueAsString(input1))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isConflict())
                .andDo(print());

    }

    @Test
    void putMultipleCommentsTest() throws Exception{

        GuestBookDto input1 = new GuestBookDto("Iqbal", "GuestBook Comment from Iqbal");
        GuestBookDto input2 = new GuestBookDto("Sai", "GuestBook Comment from Sai");

        mockMvc.perform(
                post("/guestbook/comment")
                        .content(objectMapper.writeValueAsString(input1))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andDo(print());

        mockMvc.perform(
                post("/guestbook/comment")
                        .content(objectMapper.writeValueAsString(input2))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andDo(print());

        mockMvc.perform(
                get("/guestbook"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(2))
                .andExpect(jsonPath("[0].name").value("Iqbal"))
                .andExpect(jsonPath("[1].name").value("Sai"))
                .andDo(print());

    }
}
