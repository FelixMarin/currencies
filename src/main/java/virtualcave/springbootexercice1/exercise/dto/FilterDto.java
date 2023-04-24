package virtualcave.springbootexercice1.exercise.dto;

import lombok.*;
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
