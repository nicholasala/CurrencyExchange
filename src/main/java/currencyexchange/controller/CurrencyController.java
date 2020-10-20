package currencyexchange.controller;

import currencyexchange.converter.Converter;
import currencyexchange.exception.CurrencyNotFoundException;
import currencyexchange.model.CurrencyType;
import currencyexchange.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

//Rest controller per l'esposizione del servizio di conversione

@RestController
public class CurrencyController {
    @Autowired
    private Converter converter;

    @RequestMapping(method=GET, value="/convert")
    public Result convert(@RequestParam(value="cost") double cost,
                           @RequestParam(value="from") String from,
                           @RequestParam(value="to") String to){
        // Prima versione
//        ConversionStrategy strategy;
//
//        //INFO la logica è bene tenerla in classi separate non nel controller
//        // Il controller deve verificare la correttezza della richiesta, inoltrarla all'interno
//        // e in base alla risposta ricevuta dall'interno (solitamente oggetto costruito correttamente o null)
//        // rispondere in modo adeguato (200 ok, 404 not found, ecc...)
//
//        if(from == CurrencyType.BTC && to == CurrencyType.EUR)
//            strategy = new BTCEURStrategy();
//
//        else if(from == CurrencyType.BTC && to == CurrencyType.USD)
//            strategy = new BTCUSDStrategy();
//
//        else if(from == CurrencyType.EUR && to == CurrencyType.BTC)
//            strategy = new EURBTCStrategy();
//
//        else if(from == CurrencyType.EUR && to == CurrencyType.USD)
//            strategy = new EURUSDStrategy();
//
//        else if(from == CurrencyType.USD && to == CurrencyType.BTC)
//            strategy = new USDBTCStrategy();
//
//        else if(from == CurrencyType.USD && to == CurrencyType.EUR)
//            strategy = new USDEURStrategy();
//
//        else
//            return new Result(cost);
//
//        return new ConverterWithStrategy(strategy).apply(cost, currencyService.getCurrencyById(1L));

        //Seconda versione
        Result res = converter.convert(cost, from, to);

        if(res == null)
            throw new CurrencyNotFoundException(from, to);

        return res;
    }

    @RequestMapping(method=GET, value="/values")
    public CurrencyType[] values(){
        return CurrencyType.values();
    }
}