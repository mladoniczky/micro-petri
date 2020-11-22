package com.mladoniczky.micropetri.net.repository;

import com.mladoniczky.micropetri.petri4j.Net;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetRepository extends MongoRepository<Net, String> {

    Page<Net> findAllByName(String name, Pageable pageable);

}
