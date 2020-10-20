package currencyexchange.db;

import currencyexchange.model.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Service per l'interazione con il db

@Service
public class CurrencyService {
    @Autowired
    private CurrencyRepository cr;

    public void storeValues(List<Currency> currencies){
        for(Currency c : currencies)
            cr.save(c);
    }

    public void updateValues(List<Currency> currencies){
        for(Currency c : currencies)
            cr.updateCurrency(c.getConvID(), c.getValue());
    }

    public Currency getCurrencyById(String id){
        if(cr.findById(id).isPresent())
            return cr.findById(id).get();
        else
            return null;
    }
}
