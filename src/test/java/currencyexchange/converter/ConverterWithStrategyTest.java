package currencyexchange.converter;

import currencyexchange.model.BTCtoEURUSD;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConverterWithStrategyTest {

    private final static BTCtoEURUSD currency = new BTCtoEURUSD(123.4782, 234.5671);

    @Test
    public void btcToEurTest(){
        ConverterWithStrategy c = new ConverterWithStrategy(new BTCEURStrategy());

        assertEquals(123.4782, c.apply(1, currency).getAmount(), 0.0);
        assertEquals(370.4346, c.apply(3, currency).getAmount(), 0.0);
        assertEquals(864.3474, c.apply(7, currency).getAmount(),0.0);
    }

    @Test
    public void btcToUsdTest(){
        ConverterWithStrategy c = new ConverterWithStrategy(new BTCUSDStrategy());

        assertEquals(234.5671, c.apply(1, currency).getAmount(), 0.0);
        assertEquals(703.7013, c.apply(3, currency).getAmount(), 0.0);
        assertEquals(1641.9697, c.apply(7, currency).getAmount(), 0.0);
    }

    @Test
    public void eurToBtcTest(){
        ConverterWithStrategy c = new ConverterWithStrategy(new EURBTCStrategy());

        assertEquals(0.008099, c.apply(1, currency).getAmount(), 0.0);
        assertEquals(0.024296, c.apply(3, currency).getAmount(), 0.0);
        assertEquals(1.862677, c.apply(230, currency).getAmount(), 0.0);
    }

    @Test
    public void eurToUsdTest(){
        ConverterWithStrategy c = new ConverterWithStrategy(new EURUSDStrategy());

        assertEquals(1.899759, c.apply(1, currency).getAmount(), 0.0);
        assertEquals( 5.699042, c.apply(3, currency).getAmount(),0.0);
        assertEquals(436.922742, c.apply(230, currency).getAmount(),0.0);
    }

    @Test
    public void usdToBtcTest(){
        ConverterWithStrategy c = new ConverterWithStrategy(new USDBTCStrategy());

        assertEquals(0.004263, c.apply(1, currency).getAmount(), 0.0);
        assertEquals(0.012790, c.apply(3, currency).getAmount(), 0.0);
        assertEquals(0.980530, c.apply(230, currency).getAmount(), 0.0);
    }

    @Test
    public void usdToEurTest(){
        ConverterWithStrategy c = new ConverterWithStrategy(new USDEURStrategy());

        assertEquals(0.526388, c.apply(1, currency).getAmount(), 0.0);
        assertEquals( 1.579286, c.apply(3, currency).getAmount(),0.0);
        assertEquals(121.074079, c.apply(230, currency).getAmount(),0.0);
    }
}