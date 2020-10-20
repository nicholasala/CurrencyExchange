package currencyexchange.updater;

import currencyexchange.converter.Converter;
import currencyexchange.db.CurrencyService;
import currencyexchange.model.BTCtoEURUSD;
import currencyexchange.model.CurrenciesCache;
import currencyexchange.model.Currency;
import currencyexchange.model.CurrencyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

//Scheduled task per l'aggiornamento periodico del db
// La prima fase (ipotizzando che le tabelle vengano ricreate ad ogni avvio dell'applicativo) salva i valori
// La seconda fase aggiorna i valori
// Entrambe le fasi prevedono il salvataggio in cache dei valori

@Component
public class CurrencyUpdater {
    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private APIConsumer consumer;

    @PostConstruct
    public void init(){
        List<Currency> currencies = getActualCurrencies();

        currencyService.storeValues(currencies);
        cacheCurrencies(currencies);
    }

    @Scheduled(initialDelay = 10000, fixedDelay = 10000)
    public void update(){
        List<Currency> currencies = getActualCurrencies();

        currencyService.updateValues(currencies);
        cacheCurrencies(currencies);
    }

    private List<Currency> getActualCurrencies(){
        BTCtoEURUSD btcVal = consumer.getCurrency(CurrencyType.BTC, new CurrencyType[]{CurrencyType.EUR, CurrencyType.USD});
        ArrayList<Currency> currencies = new ArrayList<>();

        //Salvo una currency per ogni conversione possibile
        currencies.add(new Currency("BTCEUR", btcVal.getEUR()));
        currencies.add(new Currency("BTCUSD", btcVal.getUSD()));
        currencies.add(new Currency("EURBTC", 1 / btcVal.getEUR()));
        currencies.add(new Currency("EURUSD", (1 / btcVal.getEUR()) * btcVal.getUSD()));
        currencies.add(new Currency("USDBTC", 1 / btcVal.getUSD()));
        currencies.add(new Currency("USDEUR", (1 / btcVal.getUSD()) * btcVal.getEUR()));

        return currencies;
    }

    private void cacheCurrencies(List<Currency> currencies){
        Converter.valuesCache.set(new CurrenciesCache(currencies));
    }
}