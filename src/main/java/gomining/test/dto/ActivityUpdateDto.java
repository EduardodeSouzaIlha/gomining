package gomining.test.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ActivityUpdateDto {
    @Id
    private String id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
}
