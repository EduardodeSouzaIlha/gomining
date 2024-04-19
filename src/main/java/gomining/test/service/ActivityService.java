package gomining.test.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gomining.test.entity.Activity;
import gomining.test.exception.PersonalizedException;
import gomining.test.repository.ActivityRepository;

@Service
public class ActivityService {
    
    @Autowired
    private ActivityRepository activityRepository;

    public Activity createActivity(Activity activity) {
        
        activityRepository.findStudentByTitle(activity.getTitle()).orElseThrow(()-> new  PersonalizedException("Activity title already exists"));
        
        activity = activityRepository.save(activity);
        return activity; 
    }

    public Activity update(Activity activity){

        getOne(activity.getId());
 
        activity = activityRepository.save(activity);
        return activity; 
    }

    @Transactional(readOnly = true)
    public Activity getOne(String id){

        return activityRepository.findActivityById(id).orElseThrow(()->new  PersonalizedException("Activity id do not exist"));
    
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

}
