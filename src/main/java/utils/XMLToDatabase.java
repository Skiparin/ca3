/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.Currency;
import entity.Rate;
import java.io.IOException;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.net.URL;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Orvur
 */
public class XMLToDatabase extends DefaultHandler {

    ArrayList<Currency> currency = new ArrayList<>();
    ArrayList<Rate> rate = new ArrayList<>();

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start Document (Sax-event)");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End Document (Sax-event)");
        
        EntityManager manager = Persistence.createEntityManagerFactory("pu_development").createEntityManager();
        manager.getTransaction().begin();
        for (int i = 0; i < rate.size(); i++) {
            manager.persist(currency.get(i));
            System.out.println(currency.get(i));
            manager.persist(rate.get(i));
            System.out.println(rate.get(i));
        }
        manager.getTransaction().commit();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.print("Element: " + localName + ": ");
        for (int i = 0; i < attributes.getLength(); i++) {
            System.out.print("[Atribute: NAME: " + attributes.getLocalName(i) + " VALUE: " + attributes.getValue(i) + "] ");
        }
        System.out.println("");

        for (int i = 4; i < attributes.getLength();) {
            Currency c = new Currency(attributes.getValue(i), attributes.getValue(i + 1));
            Rate r = new Rate(attributes.getValue(i + 2), attributes.getValue(i + 3));
            System.out.print("hey");
            c.addRate(r);
            currency.add(c);
            rate.add(r);
            i += 4;
        }
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
