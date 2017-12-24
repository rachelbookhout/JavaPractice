
/**
 * Write a description of WhatCountriesExport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhatCountriesExport {
   
    public String countryInfo(CSVParser parser, String country) {
        // returns string about the country + " " + exports + "" + export value 
        for (CSVRecord record: parser) {
            String countryName = record.get("Country");
            if(countryName.contains(country)) {
                String export = record.get("Exports");
                String value = record.get("Value (dollars)");
                String info =  "" + country + " " + export + " " + value;
                return info;
            }
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportitem1, String exportitem2){
     // prints the names of all countries have have both epoxrtITem 1 and 2 as export items
       for (CSVRecord record: parser) {
            String exports = record.get("Exports");
            if(exports.contains(exportitem1) && exports.contains(exportitem2)) {
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    
    public int numberOfExporters (CSVParser parser, String exportitem) {
    // return number of countries that export exportiitem
    int count = 0;
     for (CSVRecord record: parser) {
            String exports = record.get("Exports");
            if(exports.contains(exportitem)) {
                count++;
            }
        }
     System.out.println(count);
    return count;
    }
    
    
    public void bigExporters(CSVParser parser, String amount) {
       // prints the name of the countries that an amount longer than our current
       for (CSVRecord record: parser) {
            String money = record.get("Value (dollars)");
            if(amount.length() < money.length()) {
                String countryName = record.get("Country");
                System.out.println(countryName);
            }
        }
    }
    
    
    public void Tester(){
       FileResource fr = new FileResource();
       CSVParser parser = fr.getCSVParser();
       countryInfo(parser, "Germany");
       parser = fr.getCSVParser();
       listExportersTwoProducts(parser,"gold", "diamonds");
       parser = fr.getCSVParser();
       numberOfExporters(parser,"gold");
       parser = fr.getCSVParser();
       // method 5
       bigExporters(parser,"$999,999,999");

    }
}
