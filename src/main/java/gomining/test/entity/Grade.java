package gomining.test.entity;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;




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
public class Grade {
    
    @Min(value = 0, message = "A nota mínima deve ser 0")
    @Max(value = 10, message = "A nota máxima deve ser 10")
    private int grade;

}
