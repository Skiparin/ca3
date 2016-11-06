/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Orvur
 */
import entity.Currency;
import entity.Rate;
import java.io.IOException;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.net.URL;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class XmlReaderDemo extends DefaultHandler {

    ArrayList<Currency> currency = new ArrayList<>();
    ArrayList<Rate> rate = new ArrayList<>();
    String date;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start Document (Saxyukvyulgvuol-event)");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End Document (Sax-eventcheckcheck)");
        EntityManager manager = Persistence.createEntityManagerFactory("pu_development").createEntityManager();
        manager.getTransaction().begin();
        for (int i = 0; i < rate.size(); i++) {
            manager.persist(currency.get(i));
            manager.persist(rate.get(i));
        }
        manager.getTransaction().commit();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.print("Element: " + localName + ": ");
        for (int i = 0; i < attributes.getLength(); i++) {
            System.out.print("[Atribute: NAME: " + attributes.getLocalName(i) + " VALUE: " + attributes.getValue(i) + "] ");
        }
        if(localName.contains("dailyrates")){
            date = attributes.getValue(0);
        }
            Currency c = new Currency();
            c.setCode(attributes.getValue(0));
            c.setDesc(attributes.getValue(1));
            Rate r = new Rate();
            r.setDate(date);
            r.setRate(attributes.getValue(2));
            c.addRate(r);
            r.setCurrency(c);
            currency.add(c);
            rate.add(r);
    }

    public static void main(String[] argv) {
        try {
            XMLReader xr = XMLReaderFactory.createXMLReader();
            xr.setContentHandler(new XmlReaderDemo());
            URL url = new URL("http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=en");
            xr.parse(new InputSource(url.openStream()));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
