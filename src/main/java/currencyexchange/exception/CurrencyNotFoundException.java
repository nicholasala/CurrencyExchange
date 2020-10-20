package currencyexchange.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Conversion not found")
public class CurrencyNotFoundException extends RuntimeException {

        public CurrencyNotFoundException(String from, String to){
            super("Conversion from "+from+" to "+to+" not found");
        }
}
