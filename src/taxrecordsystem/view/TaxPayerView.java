/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxrecordsystem.view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import taxrecordsystem.model.TaxPayer;
import taxrecordsystem.presenter.TaxPayerPresenter;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

/**
 *
 * @author Alerz
 */
public class TaxPayerView extends javax.swing.JFrame implements ITaxPayerView {
    
    // the presenter for this view
    private TaxPayerPresenter presenter;
    
    // GUI Variables
    // Labels
    private JLabel titleLabel;
    private JLabel tfnLabel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel addressLabel;
    private JLabel phoneLabel;
    private JLabel incomeLabel;
    private JLabel deductibleLabel;
    private JLabel taxHeldLabel;
    private JLabel taxReturnedLabel;
    
    // Text Fields
    private JTextField tfnTextField;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField addressTextField;
    private JTextField phoneTextField;
    private JTextField incomeTextField;
    private JTextField deductibleTextField;
    private JTextField taxHeldTextField;
    private JTextField taxReturnedTextField;
    
    // Panels
    private JPanel wrapperPanel;
    private JPanel personalDataPanel;
    
    
    // Sub Panels
    private JPanel topRowPersonalDataPanel; // 1 of 4
    private JPanel topMiddleRowPersonalDataPanel; // 2 of 4
    private JPanel bottomMiddleRowPeronalDataPanel; // 3 of 4
    private JPanel bottomRowPeronalDataPanel; // 4 of 4
    
    // no-argument constructor
    public TaxPayerView() {
        super( "Tax Record System" ); 
        setLayout( new FlowLayout( FlowLayout.CENTER, 10, 10 ) );
        setSize( 800, 650 );
        setResizable( false );
        setVisible( true );
        
        // When window closes, exit program.
        addWindowListener( 
            new WindowAdapter() {  
                public void windowClosing( WindowEvent evt ) {
                   presenter.close();
                   System.exit( 0 );
                } // end method windowClosing
            } // end anonymous inner class
        ); // end call to addWindowListener
        
        // Title.
        titleLabel = new JLabel();
        titleLabel.setText("Tax Record System");
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 20));
        titleLabel.setForeground(Color.BLUE);
        add(titleLabel);
        
        // Wrapper Panel which holds all elements in it except for the title.
        wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new FlowLayout( FlowLayout.CENTER, 10, 10));
        wrapperPanel.setPreferredSize(new Dimension(700,500));
        wrapperPanel.setBorder( BorderFactory.createTitledBorder(
            "Data Entry, Browse & Query" ) );
        add(wrapperPanel);
        
        // Top data entry panel.
        personalDataPanel = new JPanel();
        personalDataPanel.setLayout( 
            new GridLayout(4, 1) );
        personalDataPanel.setBorder( BorderFactory.createTitledBorder(
            "Personal Data" ) );
        personalDataPanel.setPreferredSize(new Dimension(650,200));
        wrapperPanel.add(personalDataPanel);
        
        // Add Top row panel
        topRowPersonalDataPanel = new JPanel();
        topRowPersonalDataPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        topRowPersonalDataPanel.setPreferredSize(new Dimension(500,40));
        personalDataPanel.add(topRowPersonalDataPanel);
        
        // Add data to top row panel
        tfnLabel = new JLabel();
        tfnLabel.setText("TFN");
        topRowPersonalDataPanel.add(tfnLabel);
        
        //tfnTextField = new JTextField(8);
        //topRowPersonalDataPanel.add(tfnTextField);
        
        firstNameLabel = new JLabel();
        firstNameLabel.setText("First Name");
        topRowPersonalDataPanel.add(firstNameLabel);
        //
        //firstNameTextField = new JTextField(15);
        //topRowPersonalDataPanel.add(firstNameTextField);
        //
        //lastNameLabel = new JLabel();
        //lastNameLabel.setText("Last Name");
        //topRowPersonalDataPanel.add(lastNameLabel);
        //
        //lastNameTextField = new JTextField(15);
        //topRowPersonalDataPanel.add(lastNameTextField);
        
    }
    
   
    @Override
    // set the presenter for this view
    public void bind( TaxPayerPresenter tpp) {
        presenter = tpp;
    }

    @Override
    public void setBrowsing(Boolean f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void displayMessage(String m) {
        JOptionPane.showMessageDialog( this, m );
    }

    @Override
    public void displayRecord(TaxPayer p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void displayMaxAndCurrent(int m, int c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
