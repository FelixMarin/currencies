package virtualcave.springbootexercice1.exercise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@Component
@AllArgsConstructor
@NoArgsConstructor
public class FilterDto implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDate startDate;
    private Long productId;
    private Long brandId;
}
