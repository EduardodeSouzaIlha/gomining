package gomining.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        try{
            return ResponseEntity.ok(this.activityService.getAll(pageable));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping
    public ResponseEntity<?> createActivity(@RequestBody Activity activity) {
        try{
            return ResponseEntity.status(201).body(this.activityService.createActivity(activity));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }
    @PutMapping
    public ResponseEntity<?>  updateActivity(@RequestBody Activity activity) {
        try{
            return ResponseEntity.ok(this.activityService.update(activity));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>  deleteActivity(@PathVariable("id") String id) {
        try{
            return ResponseEntity.ok(this.activityService.deleteById(id));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
