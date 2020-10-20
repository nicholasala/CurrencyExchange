package currencyexchange.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

// INFO-TODO dividere le responsabilità
// serve quindi una classe utilizzata per la risposta dalle API esterne, adeguata quindi a ciò che le api hanno previsto
// e una classe invece che sarà utilizzata come entity per la creazione della tabella corrispondente

// INFO-TODO la tabella su db potrebbe essere così formata:
// id | value | from | to
// e dovrebbe essere popolata in modo corretto utilizzando una riga per ogni conversione (non più necessario lo strategy pattern):
// select value from currency where from = "USD" and to = "BTC";
// Ottimizzazioni:
// 1. impostare il db per eseguire una indicizzazione della tabella contenente le valute automatica in modo da rendere l'accesso ai valori più performante
// 2. prevedere l'utilizzo di una mappa tra conversione(stringa from+to) e id(primary key su db) in modo tale da cambiare
// la clausula WHERE nella query: select value where id=id(ottenuto dalla mappa)
// 3. rendere l'id primary key su tabella la concatenazione delle stringhe from e to, trasformare la tabella in questo modo:
// id | value
// In questo modo si rende il db più adatto alle nostre esigenze!
// 4. Mantenere nella mappa in ram il collegamento id -> valore, questa mappa dovrà essere aggiornata (insieme ai dati su db) dal task updater
// In questo modo l'applicativo non dovrà più chiedere al db i valori ma li avrà in ram attraverso un oggetto (thread safe) contenente questo tipo di mappa
// e il db rimarrà come per una persistenza dei dati nel caso di caduta di un nodo o di voler mantenere uno storico

@JsonIgnoreProperties(ignoreUnknown = true)
public class BTCtoEURUSD {
    @JsonProperty("EUR")
    private double EUR;

    @JsonProperty("USD")
    private double USD;

    protected BTCtoEURUSD() { }

    public BTCtoEURUSD(double EUR, double USD){
        this.EUR = EUR;
        this.USD = USD;
    }

    public double getEUR() {
        return EUR;
    }

    public void setEUR(double EUR) {
        this.EUR = EUR;
    }

    public double getUSD() {
        return USD;
    }

    public void setUSD(double USD) {
        this.USD = USD;
    }

    @Override
    public String toString() {
        return "BTCtoEURUSD{" +
                "EUR=" + EUR +
                ", USD=" + USD +
                '}';
    }
}