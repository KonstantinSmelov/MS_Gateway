package neostudy.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class FinishRegistrationRequestDTO {

    @ApiModelProperty(notes = "Пол", example = "MALE")
    private Gender gender;

    @ApiModelProperty(notes = "Дата выдачи паспорта", example = "2022-06-06")
    private LocalDate passportIssueDate;

    @ApiModelProperty(notes = "Отделение, которое выдало паспорт", example = "68 о/м")
    private String passportIssueBranch;

    @ApiModelProperty(notes = "Семейный статус", example = "MARRIED")
    private MaritalStatus maritalStatus;

    @ApiModelProperty(notes = "Кол-во иждивенцев", example = "1")
    private Integer dependentAmount;

    @ApiModelProperty(notes = "Трудоустройство")
    private EmploymentDTO employment;

    @ApiModelProperty(notes = "Номер счёта", example = "14785236914785256")
    private String account;

}
