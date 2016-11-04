/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Orvur
 */
@Entity
public class Currency implements Serializable {

    @Id
    private String code;
    private String desc;

    @OneToMany(mappedBy = "currency")
    private List<Rate> rates = new ArrayList();

    public Currency() {
    }

    public Currency(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    
    public void addRate(Rate rate){
        if(rates == null){
            rates = new ArrayList<Rate>();
            rates.add(rate);
        }else{
            rates.add(rate);
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(ArrayList<Rate> rates) {
        this.rates = rates;
    }

    
    
    
    public String getId() {
        return code;
    }

    public void setId(String id) {
        this.code = code;
    }
    
}
