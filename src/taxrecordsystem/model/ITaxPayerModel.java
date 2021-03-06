/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxrecordsystem.model;

import java.util.List;

/**
 *
 * @author Alerz
 */
// Interface for easy acces of methods.
public interface ITaxPayerModel {
    public List<TaxPayer> getAllTaxPayers();
    public List<TaxPayer> getTaxPayersByLastName(String name);
    public List<TaxPayer> getTaxPayersByTFN(int tFN);
    public int addTaxPayer(int tFN, String firstName, String lastName, String address, String phone, int income, int deductible);
    public int updateTaxPayer(int tFN, String firstName, String lastName, String address, String phone, int income, int deductible);
    public void close();
}
