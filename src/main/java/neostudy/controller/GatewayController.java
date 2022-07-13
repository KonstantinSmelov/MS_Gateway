package neostudy.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neostudy.dto.FinishRegistrationRequestDTO;
import neostudy.dto.LoanApplicationRequestDTO;
import neostudy.dto.LoanOfferDTO;
import neostudy.feignclient.ApplicationClient;
import neostudy.feignclient.DealClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class GatewayController {

    private final ApplicationClient applicationClient;
    private final DealClient dealClient;

    @ApiOperation(value = "1 - Запрос кредитных предложений")
    @PostMapping("/application")
    public ResponseEntity<List<LoanOfferDTO>> getLoanOffers(@ApiParam(value = "Предварительные данные для расчётов") @RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO) {
        log.debug("/application -> получено LoanApplicationRequestDTO {}", loanApplicationRequestDTO);
        return ResponseEntity.ok(applicationClient.getLoanOfferList(loanApplicationRequestDTO));
    }

    @ApiOperation(value = "2 - Выбор подходящего предложения")
    @PutMapping("/application/offer")
    public ResponseEntity<?> confirmOffer(@ApiParam(value = "Выбраное подходящее предложение") @RequestBody LoanOfferDTO loanOfferDTO) {
        log.debug("/application/offer -> получено LoanOfferDTO {}", loanOfferDTO);
        return ResponseEntity.ok(applicationClient.confirmLoanOffer(loanOfferDTO));
    }

    @ApiOperation(value = "3 - Детализованный расчёт предложения")
    @PutMapping("/calculate/{applicationId}")
    public ResponseEntity<?> choosingApplication(@ApiParam(value = "Номер кредитного предложения") @PathVariable Long applicationId, @RequestBody FinishRegistrationRequestDTO finishRegistrationRequestDTO) {
        log.debug("(/calculate/{}): приняли FinishRegistrationRequestDTO: {}", applicationId, finishRegistrationRequestDTO);
        dealClient.choosingApplication(applicationId, finishRegistrationRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "4 - Запрос на отправку документов")
    @PostMapping("/document/{applicationId}/send")
    public ResponseEntity<?> sendDocs(@ApiParam(value = "Номер кредитного предложения") @PathVariable Long applicationId) {
        dealClient.sendDocs(applicationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "5 - Запрос на подписание документов")
    @PostMapping("/document/{applicationId}/sign")
    public ResponseEntity<?> singDocs(@ApiParam(value = "Номер кредитного предложения") @PathVariable Long applicationId) {
        dealClient.singDocs(applicationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "6 - Подписание документов")
    @PostMapping("/document/{applicationId}/code")
    public ResponseEntity<?> receiveSesCode(@ApiParam(value = "Номер кредитного предложения") @PathVariable Long applicationId, @ApiParam(value = "Простая электронная подпись", example = "1234") @RequestBody Integer sesCode) {
        dealClient.receiveSesCode(applicationId, sesCode);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
