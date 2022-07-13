package neostudy.exception;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandIer {

    @ExceptionHandler(FeignException.FeignClientException.class)
    public ResponseEntity<String> scoringInvalid(FeignException.FeignClientException e) {

        if(e.status() == HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS.value()) {
            return new ResponseEntity<>(e.getMessage() + "\nСКОРИНГ НЕ ПРОЙДЕН!!", HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
        }

        if (e.status() == HttpStatus.CONFLICT.value()) {
            return new ResponseEntity<>(e.getMessage() + "\nПРЕСКОРИНГ НЕ ПРОЙДЕН!!", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(e.getMessage(), HttpStatus.LOCKED);
    }
}
