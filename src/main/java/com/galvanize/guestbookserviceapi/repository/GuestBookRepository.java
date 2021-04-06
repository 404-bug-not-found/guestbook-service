package com.galvanize.guestbookserviceapi.repository;

import com.galvanize.guestbookserviceapi.entity.GuestBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestBookRepository extends JpaRepository<GuestBookEntity, Integer> {

    GuestBookEntity findByName(String name);
}
