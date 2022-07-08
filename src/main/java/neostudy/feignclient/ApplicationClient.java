package neostudy.feignclient;

import neostudy.dto.LoanApplicationRequestDTO;
import neostudy.dto.LoanOfferDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "app-service-client", url = "${services.application.url}")
public interface ApplicationClient {

    @PostMapping("/application")
    List<LoanOfferDTO> getLoanOfferList(@RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO);

    @PutMapping("/application/offer")
    ResponseEntity<?> confirmLoanOffer(@RequestBody LoanOfferDTO loanOfferDTO);
}
