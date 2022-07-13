package neostudy.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class EmploymentDTO {

    @ApiModelProperty(notes = "Занятость", example = "EMPLOYED")
    private EmploymentStatus employmentStatus;

    @ApiModelProperty(notes = "ИНН", example = "852456")
    private String employerINN;

    @ApiModelProperty(notes = "ЗП", example = "100000")
    private BigDecimal salary;

    @ApiModelProperty(notes = "Должность", example = "MANAGER")
    private Position position;

    @ApiModelProperty(notes = "Общий трудовой стаж", example = "15")
    private Integer workExperienceTotal;

    @ApiModelProperty(notes = "Стаж на текущем месте работы", example = "12")
    private Integer workExperienceCurrent;
}
