/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxrecordsystem;

import taxrecordsystem.model.TaxPayer;
import taxrecordsystem.model.ITaxPayerModel;
import taxrecordsystem.model.TaxPayerModel;
import taxrecordsystem.presenter.TaxPayerPresenter;
import taxrecordsystem.view.ITaxPayerView;
import taxrecordsystem.view.TaxPayerView;

/**
 *
 * @author Alerz
 */
public class TaxRecordSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //create an instance of PatientModel instance
        ITaxPayerModel tpm=new TaxPayerModel();
        //create an instance of PatientView instance
        ITaxPayerView  tpv=new TaxPayerView();
        // create an instance of PatientPresenter with access to model and view
        TaxPayerPresenter tpp=new TaxPayerPresenter(tpv,tpm);
        // assign view access to PatientPresenter
        tpv.bind(tpp);
    }
    
}
