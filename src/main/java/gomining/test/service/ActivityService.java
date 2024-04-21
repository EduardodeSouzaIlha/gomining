package gomining.test.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gomining.test.entity.Activity;
import gomining.test.entity.Student;
import gomining.test.exception.UniqueViolationException;
import gomining.test.repository.ActivityRepository;

@Service
public class ActivityService {
    
    
    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Activity createActivity(Activity activity) {

        activity.setCreatedAt(new Date());

        if(activityRepository.findStudentByTitle(activity.getTitle()).isPresent()){
           throw new  UniqueViolationException(String.format("Activity {%s} already exists", activity.getTitle()));
        };
        
        activity = activityRepository.save(activity);
        return activity; 
    }

    public Activity update(Activity activity){
        
 
        activity = activityRepository.save(activity);
        return activity; 
    }

    @Transactional(readOnly = true)
    public Activity getOne(String id){

        return activityRepository.findActivityById(id).orElseThrow(()->new  UniqueViolationException(String.format("Activity {%s} do not exist", id)));
    
    }

    @Transactional(readOnly = true)
    public Page<Activity> getAll(Pageable pageable){
        return activityRepository.findAll(pageable);
    }

    public Boolean deleteById(String id){

        getOne(id);

        activityRepository.deleteById(id);
        return true;
    }
    public boolean deleteActivityFromAllStudents(String activityId) {
        if(activityRepository.existsById(activityId)){

            Criteria criteria = Criteria.where("activitiesAndGrades.idActivity").is(activityId);
            
            Update update = new Update().pull("activitiesAndGrades", Query.query(Criteria.where("idActivity").is(activityId)));
            
            mongoTemplate.updateMulti(Query.query(criteria), update, Student.class);
            
            activityRepository.deleteById(activityId);
            
            return true;
        }

        return false;
    }
    
}
