package gomining.test.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import gomining.test.entity.Activity;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String>{
    Optional<Activity> findActivityById(String activityId);
    Optional<Activity> findStudentByTitle(String title);


}
