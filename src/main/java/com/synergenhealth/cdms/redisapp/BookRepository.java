package com.synergenhealth.cdms.redisapp;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    @Cacheable
    public Book getByIsbn(String isbn);

    public Book getByTitle(String title);
}
