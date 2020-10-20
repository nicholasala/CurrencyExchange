package currencyexchange.converter;

import currencyexchange.db.CurrencyService;
import currencyexchange.model.BTCtoEURUSD;
import currencyexchange.model.Currency;
import currencyexchange.model.Result;
import org.springframework.beans.factory.annotation.Autowired;

//Convertitore instanziato con la strategia da utilizzare per la conversione

public class ConverterWithStrategy {
    private final ConversionStrategy strategy;

    public ConverterWithStrategy(ConversionStrategy strategy){
        this.strategy = strategy;
    }

    public Result apply(double cost, BTCtoEURUSD currency){
        return new Result(strategy.compute(cost, currency));
    }
}
