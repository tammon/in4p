package de.tammon.dev.in4p.pots.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tammschw on 12/04/15.
 */

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
}
