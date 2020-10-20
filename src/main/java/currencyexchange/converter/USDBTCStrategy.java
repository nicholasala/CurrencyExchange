package currencyexchange.converter;

import currencyexchange.model.BTCtoEURUSD;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class USDBTCStrategy extends ConversionStrategy {

    @Override
    public double compute(double cost, BTCtoEURUSD currency) {
        return new BigDecimal(String.valueOf(cost))
                .setScale(scale, RoundingMode.HALF_EVEN)
                .divide(new BigDecimal(String.valueOf(currency.getUSD())), RoundingMode.HALF_EVEN)
                .doubleValue();
    }
}
