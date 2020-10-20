package currencyexchange.db;

import currencyexchange.model.Currency;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CurrencyRepository extends CrudRepository<Currency, String> {

    @Transactional
    @Modifying
    @Query("update #{#entityName} set value= :value where id= :id")
    void updateCurrency(@Param("id") String id, @Param("value") double value);

//    @Query("select :value from #{#entityName} c where id= :id")
//    double getCurrencyById(@Param("id") long id, @Param("value") CurrencyType value);
//
//    @Query("select EUR from #{#entityName} c where id= :id")
//    double getEURCurrency(@Param("id") long id);
//
//    @Query("select USD from #{#entityName} c where id= :id")
//    double getUSDCurrency(@Param("id") long id);

}
