/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxrecordsystem.view;
import taxrecordsystem.model.TaxPayer;
import taxrecordsystem.presenter.TaxPayerPresenter;
/**
 *
 * @author Alerz
 */
public interface ITaxPayerView {
    void bind(TaxPayerPresenter p);
    void setBrowsing(Boolean f); 
    void displayMessage(String m); 
    void displayRecord(TaxPayer p); 
    void displayMaxAndCurrent(int m,int c);
}
