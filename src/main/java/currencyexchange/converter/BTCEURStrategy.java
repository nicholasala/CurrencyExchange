package currencyexchange.converter;

import currencyexchange.model.BTCtoEURUSD;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BTCEURStrategy extends ConversionStrategy {

    @Override
    public double compute(double cost, BTCtoEURUSD currency) {
        return new BigDecimal(String.valueOf(cost))
                .setScale(scale, RoundingMode.HALF_EVEN)
                .multiply(new BigDecimal(String.valueOf(currency.getEUR())))
                .doubleValue();
    }
}