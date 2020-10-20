package currencyexchange.converter;

import currencyexchange.model.BTCtoEURUSD;

//Classe astratta rappresentante la strategia di conversione

public abstract class ConversionStrategy {
    protected final int scale = 6;

    public abstract double compute(double cost, BTCtoEURUSD currency);
}
