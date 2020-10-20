package currencyexchange.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Currency {
    @Id
    private String convID;
    private double value;

    public Currency() {}

    public Currency(String convID, double value) {
        this.convID = convID;
        this.value = value;
    }

    public String getConvID() {
        return convID;
    }

    public void setConvID(String convID) {
        this.convID = convID;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}