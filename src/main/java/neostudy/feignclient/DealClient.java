package neostudy.feignclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import neostudy.dto.FinishRegistrationRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@FeignClient(name = "deal-service-client", url = "${services.deal.url}")
public interface DealClient {

    @PutMapping("deal/calculate/{applicationId}")
    ResponseEntity<?> choosingApplication(@PathVariable Long applicationId, @RequestBody FinishRegistrationRequestDTO finishRegistrationRequestDTO);

    @PostMapping("deal/document/{applicationId}/send")
    ResponseEntity<?> sendDocs(@PathVariable Long applicationId);

    @PostMapping("deal/document/{applicationId}/sign")
    ResponseEntity<?> singDocs(@PathVariable Long applicationId);

    @PostMapping("deal/document/{applicationId}/code")
    ResponseEntity<?> receiveSesCode(@PathVariable Long applicationId, @RequestBody Integer sesCode);

//    @PostMapping("/application/{applicationId}")
//    ResponseEntity<?> setAppDeny(@PathVariable Long applicationId);
}
