package gomining.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gomining.test.dto.ActivityCreateDto;
import gomining.test.dto.ActivityUpdateDto;
import gomining.test.dto.mapper.ActivityMapper;
import gomining.test.entity.Activity;
import gomining.test.service.ActivityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Activities", description = "Here you can CRUD an activity")
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/activities")
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping("/{id}")
    public ResponseEntity<Activity>  getActivity(@PathVariable("id") String id) {

        return ResponseEntity.ok(this.activityService.getOne(id));

    }
    @GetMapping
    public ResponseEntity<?>  getAllActivities(Pageable pageable) {

        return ResponseEntity.ok(this.activityService.getAll(pageable));
    
    }
    @PostMapping
    public ResponseEntity<Activity> createActivity(@RequestBody ActivityCreateDto activitycCreateDto) {
   
        return ResponseEntity.status(201).body(this.activityService.createActivity(ActivityMapper.toActivity(activitycCreateDto)));
     
    }
    @PutMapping
    public ResponseEntity<?>  updateActivity(@RequestBody ActivityUpdateDto activityUpdateDto) {
        
        return ResponseEntity.ok(this.activityService.update(ActivityMapper.updateToActivity(activityUpdateDto)));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>  deleteActivity(@PathVariable("id") String id) {
  
        return ResponseEntity.ok(this.activityService.deleteActivityFromAllStudents(id));
   
    }

}
