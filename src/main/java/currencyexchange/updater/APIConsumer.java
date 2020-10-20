package currencyexchange.updater;

import currencyexchange.model.CurrencyType;
import currencyexchange.model.BTCtoEURUSD;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

//Rest template per la connessione alle api di cryptocompare e il recupero dei dati necessari attraverso la classe BTCtoEURUSD

@Component
public class APIConsumer {
    private static final String APIKEY = "key";
    private static final String URL = "https://min-api.cryptocompare.com/data/price?fsym={from}&tsyms={to}&api_key={apikey}";
    private RestTemplate restTemplate = new RestTemplate();

    public BTCtoEURUSD getCurrency(CurrencyType from, CurrencyType[] to){
        return restTemplate.getForObject(URL, BTCtoEURUSD.class, from, to, APIKEY);
    }
}
