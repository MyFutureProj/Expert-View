package com.camelcase.repository;

import com.camelcase.entity.StreamData;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ravi Panchal
 */
@Repository
public interface StreamDataRepository extends MongoRepository<StreamData, ObjectId> {
}
