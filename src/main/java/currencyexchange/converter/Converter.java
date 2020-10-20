package currencyexchange.converter;

import currencyexchange.db.CurrencyService;
import currencyexchange.model.CurrenciesCache;
import currencyexchange.model.Currency;
import currencyexchange.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class Converter {
    @Autowired
    private CurrencyService currencyService;
    public volatile static AtomicReference<CurrenciesCache> valuesCache = new AtomicReference<>();

    public Result convert(double cost, String from, String to){
        String convId = (from+to).toUpperCase();
        Currency currency;

        //Se la cache non è ancora stata popolata recuperiamo i valori direttamente da db
        if(valuesCache.get() == null)
            currency = currencyService.getCurrencyById(convId);
        else
            currency = valuesCache.get().getCurrencyById(convId);

        if(currency == null)
            return null;

        return new Result(new BigDecimal(String.valueOf(cost))
                                        .multiply(new BigDecimal(String.valueOf(currency.getValue())))
                                        .setScale(10, RoundingMode.HALF_EVEN)
                                        .doubleValue());
    }
}
