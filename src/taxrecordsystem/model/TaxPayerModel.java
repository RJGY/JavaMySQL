/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxrecordsystem.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alerz
 */
public class TaxPayerModel implements ITaxPayerModel {
    
    private static final String URL = "jdbc:mysql://localhost/taxdb";

    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";
    
    private Connection connection = null; // manages connection
    private PreparedStatement selectAllTaxPayers = null;
    private PreparedStatement selectTaxPayersByLastName = null;
    private PreparedStatement selectTaxPayersByTFN = null;
    private PreparedStatement insertNewTaxPayer = null;
    private PreparedStatement updateTaxPayer = null;
    
    // Constructor
    public TaxPayerModel() {
        try
      {
         connection =
            DriverManager.getConnection( URL,USERNAME,PASSWORD );

         // create query that selects all entries in the table
         selectAllTaxPayers =
            connection.prepareStatement( "SELECT * FROM TaxPayers" );

         // create query that selects entries with a specific name
         selectTaxPayersByLastName = connection.prepareStatement(
            "SELECT * FROM TaxPayers WHERE lastName = ?" );
         // create query that selects entirs with a specific id
         selectTaxPayersByTFN = connection.prepareStatement(
            "SELECT * FROM TaxPayers WHERE tFN = ?" );
         // create insert that adds a new entry into the database
         insertNewTaxPayer = connection.prepareStatement(
            "INSERT INTO TaxPayers " +
            "( tFN, firstName, lastName, address, phone, income, deductible, taxHeld, taxReturned ) " +
            "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? )" );
         updateTaxPayer = connection.prepareStatement(
            "UPDATE TaxPayers " +
            "SET firstName = ?, lastName = ?, address = ?, phone = ?, income = ?, deductible = ?, taxHeld = ?, taxReturned = ? " + 
            "WHERE tFN = ?");
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
         System.exit( 1 );
      } // end catch
    }

    @Override
    public List<TaxPayer> getAllTaxPayers() {
        List<TaxPayer> results = new ArrayList<TaxPayer>();
        ResultSet resultSet = null;

        try {
            // executeQuery returns ResultSet containing matching entries
            resultSet = selectAllTaxPayers.executeQuery();

            while ( resultSet.next() ) {
             
                results.add( new TaxPayer(
                    resultSet.getInt( "tFN" ),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("address"),
                    resultSet.getString("phone"),
                    resultSet.getInt("income"),
                    resultSet.getInt("deductible")
                ) );
            } // end while
        } // end try
        catch ( SQLException sqlException )
        {
           sqlException.printStackTrace();
        } // end catch
        finally {
           try {
              resultSet.close();
           } // end try
           catch ( SQLException sqlException ) {
              sqlException.printStackTrace();
              close();
           } // end catch
        } // end finally

        return results;
    }

    @Override
    public List<TaxPayer> getTaxPayersByLastName(String lastName) {
        List< TaxPayer > results = new ArrayList<TaxPayer>();
        ResultSet resultSet = null;

        try
        {
            selectTaxPayersByLastName.setString( 1, lastName ); // specify last name

            // executeQuery returns ResultSet containing matching entries
            resultSet = selectTaxPayersByLastName.executeQuery();

            while ( resultSet.next() )
            {
                results.add( new TaxPayer(
                    resultSet.getInt( "tFN" ),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("address"),
                    resultSet.getString("phone"),
                    resultSet.getInt("income"),
                    resultSet.getInt("deductible")
                ) );
           } // end while
        } // end try
        catch ( SQLException sqlException )
        {
           sqlException.printStackTrace();
        } // end catch
        finally
        {
            try {
                resultSet.close();
            } // end try
            catch ( SQLException sqlException ) {
                sqlException.printStackTrace();
                close();
            } // end catch
        } // end finally

        return results;
    }

    @Override
    public List<TaxPayer> getTaxPayersByTFN(int tFN) {
        List< TaxPayer > results = null;
        ResultSet resultSet = null;

        try {
            selectTaxPayersByTFN.setInt( 1, tFN ); // specify id

            // executeQuery returns ResultSet containing matching entries
            resultSet = selectTaxPayersByTFN.executeQuery();

            results = new ArrayList< TaxPayer >();

            while ( resultSet.next() ) {
                results.add( new TaxPayer(
                   resultSet.getInt( "tFN" ),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("address"),
                    resultSet.getString("phone"),
                    resultSet.getInt("income"),
                    resultSet.getInt("deductible")
               ) );
            } // end while
        } // end try
        catch ( SQLException sqlException ) {
           sqlException.printStackTrace();
        } // end catch
        finally {
              try {
                 resultSet.close();
              } // end try
              catch ( SQLException sqlException ) {
                 sqlException.printStackTrace();
                 close();
              } // end catch
        } // end finally

        return results;
    }

    @Override
    public int addTaxPayer(int tFN, String firstName, String lastName, String address, String phone, int income, int deductible) {
        int result = 0;

        // set parameters, then execute insertNewPatient
        try {
           insertNewTaxPayer.setInt( 1, tFN );
           insertNewTaxPayer.setString( 2, firstName );
           insertNewTaxPayer.setString( 3, lastName );
           insertNewTaxPayer.setString( 4, address );
           insertNewTaxPayer.setString( 5, phone );
           insertNewTaxPayer.setInt( 6, income );
           insertNewTaxPayer.setInt( 7, deductible );
           insertNewTaxPayer.setInt( 8, TaxPayer.computeTax(income) );
           insertNewTaxPayer.setInt( 9, TaxPayer.computeTaxReturned(income,deductible) );

           // insert the new entry; returns # of rows updated
           result = insertNewTaxPayer.executeUpdate();
        } // end try
        catch ( SQLException sqlException ) {
           sqlException.printStackTrace();
        } // end catch

        return result;
    }
    
    @Override
    public int updateTaxPayer(int tFN, String firstName, String lastName, String address, String phone, int income, int deductible) {
        int result = 0;
        
        // set parameters, then execute insertNewPatient
        try {
           updateTaxPayer.setInt( 9, tFN );
           updateTaxPayer.setString( 1, firstName );
           updateTaxPayer.setString( 2, lastName );
           updateTaxPayer.setString( 3, address );
           updateTaxPayer.setString( 4, phone );
           updateTaxPayer.setInt( 5, income );
           updateTaxPayer.setInt( 6, deductible );
           updateTaxPayer.setInt( 7, TaxPayer.computeTax(income) );
           updateTaxPayer.setInt( 8, TaxPayer.computeTaxReturned(income,deductible) );

           // insert the new entry; returns # of rows updated
           result = updateTaxPayer.executeUpdate();
        } // end try
        catch ( SQLException sqlException ) {
           sqlException.printStackTrace();
        } // end catch
        
        return result;
    }

    @Override
    public void close() {
        try {
           connection.close();
        } // end try
        catch ( SQLException sqlException ) {
           sqlException.printStackTrace();
        } // end catch
    }
    
}
