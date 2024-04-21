package gomining.test.entity;




import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "activity")
public class Activity {
    @Id
    private String id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;

    private Date createdAt;
    private Date modifiedAt;

    public Activity(String title, String description){
        this.title = title;
        this.description = description;
    }
}
