package com.galvanize.guestbookserviceapi.controller;

import com.galvanize.guestbookserviceapi.dto.GuestBookDto;
import com.galvanize.guestbookserviceapi.service.GuestBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("guestbook")
public class GuestBookServiceController {

    @Autowired
    private GuestBookService guestBookService;


    @GetMapping
    public List<GuestBookDto> getAllGuestBookEntries(){
        return guestBookService.getAllGuestBookEntries();
    }

    @PostMapping("comment")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewGuestBookComment(@RequestBody GuestBookDto guestBookDto) throws Exception{

        guestBookService.addComment(guestBookDto);

    }


}
