/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxrecordsystem.presenter;

import java.util.List;
import taxrecordsystem.view.ITaxPayerView;
import taxrecordsystem.model.TaxPayer;
import taxrecordsystem.model.ITaxPayerModel;
/**
 *
 * @author Alerz
 */
public class TaxPayerPresenter {
    private ITaxPayerView view;
    private ITaxPayerModel model;
    private List< TaxPayer > results;
    private int currentEntryIndex;
    private int numberOfEntries;
    private TaxPayer currentEntry;
    
    public TaxPayerPresenter(ITaxPayerView ipv, ITaxPayerModel ipm) {
        view = ipv;
        model = ipm;
        currentEntryIndex = 0;
        numberOfEntries = 0;
        results = null;
        currentEntry = null;
    }
    
    // private-helper
    private void populateAllTextFields() {
        view.displayTaxPayer(currentEntry);
        view.displayMaxAndCurrent(numberOfEntries, currentEntryIndex);
    }

    // handles call when previousButton is clicked
    public void showPrevious() {
        currentEntryIndex--;
        // wrap around
        if ( currentEntryIndex < 0 ) {
           currentEntryIndex = numberOfEntries - 1;
        }
        currentEntry = results.get( currentEntryIndex );
        populateAllTextFields();
    }

    // handles call when nextButton is clicked
    public void showNext() {
        currentEntryIndex++;
        // wrap around
        if ( currentEntryIndex >= numberOfEntries ) {
           currentEntryIndex = 0;
        }
        currentEntry = results.get( currentEntryIndex );
        populateAllTextFields();
    }

    // handles call when queryButton is clicked
    public void performQueryByTFN(int tFN) {
        results = model.getTaxPayersByTFN( tFN );

        numberOfEntries = results.size();
        switch (numberOfEntries) {
            case 0:
                view.displayMessage("Not found");
                break;
            case 1:
                currentEntryIndex = 0;
                currentEntry = results.get( currentEntryIndex );
                populateAllTextFields();
                view.setBrowsing(false);
                view.displayTextArea(results.get(currentEntryIndex).toString());
                break;
            default:
                currentEntryIndex = 0;
                currentEntry = results.get( currentEntryIndex );
                populateAllTextFields();
                view.setBrowsing(true);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < numberOfEntries; i++) {
                    sb.append(results.get(i).toString());
                }
                view.displayTextArea(sb.toString());
                break;
        }
    }
    public void performQueryByName(String lastName) {
        results = model.getTaxPayersByLastName(lastName);

        numberOfEntries = results.size();
        switch (numberOfEntries) {
            case 0:
                view.displayMessage("Not found");
                break;
            case 1:
                currentEntryIndex = 0;
                currentEntry = results.get( currentEntryIndex );
                populateAllTextFields();
                view.setBrowsing(false);
                view.displayTextArea(results.get(currentEntryIndex).toString());
                break;
            default:
                currentEntryIndex = 0;
                currentEntry = results.get( currentEntryIndex );
                populateAllTextFields();
                view.setBrowsing(true);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < numberOfEntries; i++) {
                    sb.append(results.get(i).toString());
                }
                view.displayTextArea(sb.toString());
                break;
        }
   }
   // handles call when browseButton is clicked
   public void browse() {
        try {
            results = model.getAllTaxPayers();

            numberOfEntries = results.size();
            if(numberOfEntries == 0) {
                view.displayMessage("No records to browse"); 
            } else {
                currentEntryIndex = 0;
                currentEntry = results.get( currentEntryIndex );
                populateAllTextFields();
                view.setBrowsing(true);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < numberOfEntries; i++) {
                    sb.append(results.get(i).toString());
                }
                view.displayTextArea(sb.toString());
            }
        }

        catch ( Exception e ) {
            e.printStackTrace();
        }
   }

    // handles call when insertButton is clicked
    public void insertNewEntry(int tFN, String firstName, String lastName, String address, String phone, int income, int deductible) {
        int result = model.addTaxPayer(tFN, firstName, lastName, address, phone, income, deductible);
        if ( result == 1 ) {
            view.displayMessage("Tax Payer added");
        } else {
            view.displayMessage("Tax Payer not added");
        }

        browse();
    }
    
    public void updateEntry(int tFN, String firstName, String lastName, String address, String phone, int income, int deductible) {
        int result = model.updateTaxPayer(tFN, firstName, lastName, address, phone, income, deductible);
        if ( result == 1 ) {
            view.displayMessage("Tax Payer updated");
        } else {
            view.displayMessage("Tax Payer update unsuccessful");
        }
        
        browse();
    }

    // handles window closure
    public void close() {
        model.close();
    }
}
