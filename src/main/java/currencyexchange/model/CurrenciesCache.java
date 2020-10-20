package currencyexchange.model;

import java.util.HashMap;
import java.util.List;

public class CurrenciesCache {
    private final HashMap<String, Double> values;

    public CurrenciesCache(List<Currency> currencies){
        values = new HashMap<>();

        for(Currency c : currencies)
            values.put(c.getConvID(), c.getValue());
    }

    public Currency getCurrencyById(String id){
        if(values.containsKey(id))
            return new Currency(id, values.get(id));

        return null;
    }
}
