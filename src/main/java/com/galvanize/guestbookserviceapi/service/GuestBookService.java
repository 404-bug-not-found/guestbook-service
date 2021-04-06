package com.galvanize.guestbookserviceapi.service;

import com.galvanize.guestbookserviceapi.dto.GuestBookDto;
import com.galvanize.guestbookserviceapi.entity.GuestBookEntity;
import com.galvanize.guestbookserviceapi.exception.CommentExistException;
import com.galvanize.guestbookserviceapi.repository.GuestBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GuestBookService {

    @Autowired
    private GuestBookRepository guestBookRepository;

    public void addComment(GuestBookDto guestBookDto) throws CommentExistException {
        Optional<GuestBookEntity> commentExists = guestBookRepository.findAll()
                .stream()
                .filter(familyEntity -> familyEntity.getName().equals(guestBookDto.getName()))
                .findAny();

        if (commentExists.isPresent()) {

            throw new CommentExistException();

        } else {
            guestBookRepository.save(new GuestBookEntity(guestBookDto.getName(), guestBookDto.getComment()));
        }
    }

    public List<GuestBookDto> getAllGuestBookEntries() {
        return guestBookRepository.findAll()
                .stream()
                .map(guestBookEntity -> {
                    return new GuestBookDto(
                            guestBookEntity.getName(),
                            guestBookEntity.getComment()
                    );
                })
                .collect(Collectors.toList());
    }
}
