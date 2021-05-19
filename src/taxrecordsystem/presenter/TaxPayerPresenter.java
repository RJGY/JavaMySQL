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
     
     private void populateAllTextFields() {//private-helper

        view.displayRecord(currentEntry);
        view.displayMaxAndCurrent(numberOfEntries, currentEntryIndex);
    }

   // handles call when previousButton is clicked
   public void showPrevious() {
      currentEntryIndex--;
      // wrap around
      if ( currentEntryIndex < 0 )
         currentEntryIndex = numberOfEntries - 1;
      currentEntry = results.get( currentEntryIndex );
      populateAllTextFields();
   }

   // handles call when nextButton is clicked
   public void showNext() {
      currentEntryIndex++;
      // wrap around
      if ( currentEntryIndex >= numberOfEntries )
         currentEntryIndex = 0;
      currentEntry = results.get( currentEntryIndex );
      populateAllTextFields();
   }

   // handles call when queryButton is clicked
   public void performQueryById(int id) {


     results = model.getPatientsByID( id );

      numberOfEntries = results.size();
      if ( numberOfEntries != 0 ) {
         currentEntryIndex = 0;
         currentEntry = results.get( currentEntryIndex );
         populateAllTextFields();
         view.setBrowsing(true);

      }
      else
        view.displayMessage("Not found");
   }
    public void performQueryByName(String n) {


     results = model.getPatientsByName(n);

      numberOfEntries = results.size();
      if ( numberOfEntries != 0 ) {
         currentEntryIndex = 0;
         currentEntry = results.get( currentEntryIndex );
         populateAllTextFields();
         view.setBrowsing(true);

      }
      else
        view.displayMessage("Not found");
   }
   // handles call when browseButton is clicked
   public void browse() {
      try {
         results = model.getAllPatients();

         numberOfEntries = results.size();
         if(numberOfEntries ==0)
             view.displayMessage("No records to browse");
         if ( numberOfEntries != 0 ) {
            currentEntryIndex = 0;
            currentEntry = results.get( currentEntryIndex );
            populateAllTextFields();
            view.setBrowsing(true);
         }
      }

      catch ( Exception e ) {
         e.printStackTrace();
      }
   }

   // handles call when insertButton is clicked
   public void insertNewEntry(String name,String address,int age,String nok) {

       int result = model.addPatient(name,address, age,nok );
      if ( result == 1 )
          view.displayMessage("Patient added");
      else
          view.displayMessage("Patient not added");

      //browse();
   }

   // handles window closure
   public void close() {
      model.close();
   }
}
