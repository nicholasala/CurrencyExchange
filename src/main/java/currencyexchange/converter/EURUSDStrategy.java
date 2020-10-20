package currencyexchange.converter;

import currencyexchange.model.BTCtoEURUSD;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class EURUSDStrategy extends ConversionStrategy {

    @Override
    public double compute(double cost, BTCtoEURUSD currency) {
        return new BigDecimal(String.valueOf(
                new ConverterWithStrategy(new BTCUSDStrategy()).apply(
                        new ConverterWithStrategy(new EURBTCStrategy()).apply(cost, currency).getAmount(),
                        currency
                ).getAmount()))
                .setScale(scale, RoundingMode.HALF_EVEN)
                .doubleValue();
    }
}
