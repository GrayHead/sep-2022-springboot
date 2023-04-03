package ua.com.owu.sep2022springboot.dao.mongo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.owu.sep2022springboot.models.mongomodels.Client;

public interface ClientMongoDAO extends MongoRepository<Client, ObjectId> {
}
