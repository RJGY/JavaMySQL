/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxrecordsystem.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import taxrecordsystem.model.TaxPayer;
import taxrecordsystem.presenter.TaxPayerPresenter;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
    private JLabel ofLabel;
    private JLabel tfnSearchLabel;
    private JLabel lastNameSearchLabel;
    
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
    private JTextField indexTextField;
    private JTextField lengthTextField;
    private JTextField tfnSearchTextField;
    private JTextField lastNameSearchTextField;
    
    // Panels
    private JPanel wrapperPanel;
    private JPanel personalDataPanel;
    private JPanel navigationPanel;
    private JPanel searchPanel;
    
    // Text Area
    private JTextArea displayArea;
    
    // Buttons
    private JButton addButton;
    private JButton browseAllButton;
    private JButton previousButton;
    private JButton nextButton;
    private JButton updateButton;
    private JButton searchTFNButton;
    private JButton searchLastNameButton;
    private JButton exitButton;
    
    // Sub Panels
    private JPanel topRowPersonalDataPanel; // 1 of 4
    private JPanel topMiddleRowPersonalDataPanel; // 2 of 4
    private JPanel bottomMiddleRowPersonalDataPanel; // 3 of 4
    private JPanel bottomRowPersonalDataPanel; // 4 of 4
    
    private JPanel tfnSearchPanel; // 1 of 2
    private JPanel lastNameSearchPanel; // 2 of 2
    
    // no-argument constructor
    public TaxPayerView() {
        super( "Tax Record System" ); 
        setLayout( new FlowLayout( FlowLayout.CENTER, 10, 10 ) );
        setSize( 800, 700 );
        setResizable( false );
        
        
        // When window closes, exit program.
        addWindowListener( 
            new WindowAdapter() {  
                public void windowClosing( WindowEvent evt ) {
                   close();
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
        wrapperPanel.setPreferredSize(new Dimension(775,585));
        wrapperPanel.setBorder( BorderFactory.createTitledBorder(
            "Data Entry, Browse & Query" ) );
        add(wrapperPanel);
        
        // Top data entry panel.
        personalDataPanel = new JPanel();
        personalDataPanel.setLayout( 
            new GridLayout(4, 1) );
        personalDataPanel.setBorder( BorderFactory.createTitledBorder(
            "Personal Data" ) );
        personalDataPanel.setPreferredSize(new Dimension(675,200));
        wrapperPanel.add(personalDataPanel);
        
        // Add Top row panel
        topRowPersonalDataPanel = new JPanel();
        topRowPersonalDataPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        personalDataPanel.add(topRowPersonalDataPanel);
        
        // Add data to top row panel
        tfnLabel = new JLabel();
        tfnLabel.setText("TFN");
        topRowPersonalDataPanel.add(tfnLabel);
        
        tfnTextField = new JTextField(9);
        tfnTextField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                validateInput(e);
            }
        });
        topRowPersonalDataPanel.add(tfnTextField);
        
        // Spacer Box
        topRowPersonalDataPanel.add( Box.createHorizontalStrut( 5 ) );
        
        firstNameLabel = new JLabel();
        firstNameLabel.setText("First Name");
        topRowPersonalDataPanel.add(firstNameLabel);
        
        firstNameTextField = new JTextField(14);
        topRowPersonalDataPanel.add(firstNameTextField);
        
        // Spacer Box
        topRowPersonalDataPanel.add( Box.createHorizontalStrut( 5 ) );
        
        lastNameLabel = new JLabel();
        lastNameLabel.setText("Last Name");
        topRowPersonalDataPanel.add(lastNameLabel);
        
        lastNameTextField = new JTextField(14);
        topRowPersonalDataPanel.add(lastNameTextField);
        
        // Add middle row panel
        topMiddleRowPersonalDataPanel = new JPanel();
        topMiddleRowPersonalDataPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        personalDataPanel.add(topMiddleRowPersonalDataPanel);
        
        // Add data to top middle row panel
        addressLabel = new JLabel();
        addressLabel.setText("Address");
        topMiddleRowPersonalDataPanel.add(addressLabel);
        
        addressTextField = new JTextField(36);
        topMiddleRowPersonalDataPanel.add(addressTextField);
        
        // Spacer Box
        topMiddleRowPersonalDataPanel.add( Box.createHorizontalStrut( 5 ) );
        
        phoneLabel = new JLabel();
        phoneLabel.setText("Phone");
        topMiddleRowPersonalDataPanel.add(phoneLabel);
        
        phoneTextField = new JTextField(10);
        topMiddleRowPersonalDataPanel.add(phoneTextField);
        
        
        // Add middle bottom row panel
        bottomMiddleRowPersonalDataPanel = new JPanel();
        bottomMiddleRowPersonalDataPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        personalDataPanel.add(bottomMiddleRowPersonalDataPanel);
        
        // Add data to bottom middle row panel
        incomeLabel = new JLabel();
        incomeLabel.setText("Income");
        bottomMiddleRowPersonalDataPanel.add(incomeLabel);
        
        incomeTextField = new JTextField(12);
        incomeTextField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                validateInput(e);
                onIncomeUpdate();
            }
        });
        bottomMiddleRowPersonalDataPanel.add(incomeTextField);
        
        // Spacer Box
        bottomMiddleRowPersonalDataPanel.add( Box.createHorizontalStrut( 228 ) );
        
        deductibleLabel = new JLabel();
        deductibleLabel.setText("Deductible");
        bottomMiddleRowPersonalDataPanel.add(deductibleLabel);
        
        deductibleTextField = new JTextField(12);
        deductibleTextField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                validateInput(e);
                onIncomeUpdate();
            }
        });
        bottomMiddleRowPersonalDataPanel.add(deductibleTextField);
        
        // Bottom panel
        bottomRowPersonalDataPanel = new JPanel();
        bottomRowPersonalDataPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        personalDataPanel.add(bottomRowPersonalDataPanel);
        
        taxHeldLabel = new JLabel();
        taxHeldLabel.setText("Tax Held");
        bottomRowPersonalDataPanel.add(taxHeldLabel);
        
        taxHeldTextField = new JTextField(12);
        taxHeldTextField.setEditable(false);
        bottomRowPersonalDataPanel.add(taxHeldTextField);
        
        // Spacer Box
        bottomRowPersonalDataPanel.add( Box.createHorizontalStrut( 205 ) );
        
        // Add data to bottom middle row panel
        taxReturnedLabel = new JLabel();
        taxReturnedLabel.setText("Tax Returned");
        bottomRowPersonalDataPanel.add(taxReturnedLabel);
        
        taxReturnedTextField = new JTextField(12);
        taxReturnedTextField.setEditable(false);
        bottomRowPersonalDataPanel.add(taxReturnedTextField);
        
        // Add display textarea
        displayArea = new JTextArea(8,61);
        displayArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        displayArea.setEditable(false);
        Font textAreaFont = new Font("Courier New", Font.PLAIN, 12);
        displayArea.setFont(textAreaFont);
        displayArea.setPreferredSize(new Dimension(750,140));
        displayArea.setMaximumSize(new Dimension(750,140));
        displayArea.setText("Welcome to the Tax Record System. \n\nTo get started, press the Browse All button. \nTo add a new record, enter in the details " + 
            "into the correct text boxes and press the Add button. \nTo update a record, edit the details of the tax payer while keeping the TFN the same.");
        wrapperPanel.add(displayArea);
        
        // Add add button
        addButton = new JButton();
        addButton.setText("Add");
        addButton.addActionListener((ActionEvent evt) -> {
            insertNewTaxPayer();
        }); 
        wrapperPanel.add(addButton);
        
        // Add panel to hold navigation.
        navigationPanel = new JPanel();
        navigationPanel.setLayout(new BoxLayout(navigationPanel, BoxLayout.X_AXIS));
        navigationPanel.setPreferredSize(new Dimension(750, 40));
        navigationPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        wrapperPanel.add(navigationPanel);
        
        // Spacer Box
        navigationPanel.add( Box.createHorizontalStrut( 80 ) );
        
        // Add buttons and labels to navigation
        browseAllButton = new JButton();
        browseAllButton.setText("Browse All");
        browseAllButton.addActionListener((ActionEvent evt) -> {
            presenter.browse();
        }); 
        navigationPanel.add(browseAllButton);
        
        // Spacer Box
        navigationPanel.add( Box.createHorizontalStrut( 10 ) );
        
        previousButton = new JButton();
        previousButton.setText("<< Previous");
        previousButton.addActionListener((ActionEvent evt) -> {
            presenter.showPrevious();
        }); 
        navigationPanel.add(previousButton);
        
        // Spacer Box
        navigationPanel.add( Box.createHorizontalStrut( 10 ) );
        
        indexTextField = new JTextField(2);
        indexTextField.setPreferredSize(new Dimension(30,30));
        indexTextField.setMaximumSize(indexTextField.getPreferredSize());
        indexTextField.setEditable(false);
        navigationPanel.add(indexTextField);
        
        // Spacer Box
        navigationPanel.add( Box.createHorizontalStrut( 10 ) );
        
        ofLabel = new JLabel();
        ofLabel.setText("of");
        navigationPanel.add(ofLabel);
        
        // Spacer Box
        navigationPanel.add( Box.createHorizontalStrut( 10 ) );
        
        lengthTextField = new JTextField(2);
        lengthTextField.setPreferredSize(new Dimension(30,30));
        lengthTextField.setMaximumSize(lengthTextField.getPreferredSize());
        lengthTextField.setEditable(false);
        navigationPanel.add(lengthTextField);
        
        // Spacer Box
        navigationPanel.add( Box.createHorizontalStrut( 10 ) );
        
        nextButton = new JButton();
        nextButton.setText("Next >>");
        nextButton.addActionListener((ActionEvent evt) -> {
            presenter.showNext();
        }); 
        navigationPanel.add(nextButton);
        
        // Spacer Box
        navigationPanel.add( Box.createHorizontalStrut( 10 ) );
        
        updateButton = new JButton();
        updateButton.setText("Update >>");
        updateButton.addActionListener((ActionEvent evt) -> {
            updateTaxPayer();
        }); 
        navigationPanel.add(updateButton);
        
        // Search Panel
        searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        searchPanel.setPreferredSize(new Dimension(750, 70));
        wrapperPanel.add(searchPanel);
        
        // Sub panel for TFN
        tfnSearchPanel = new JPanel();
        tfnSearchPanel.setBorder( BorderFactory.createTitledBorder(
            "Find entry by TFN" )); 
        searchPanel.add(tfnSearchPanel);
        
        // Data inside TFN sub panel
        tfnSearchLabel = new JLabel();
        tfnSearchLabel.setText("TFN: ");
        tfnSearchPanel.add(tfnSearchLabel);
        
        tfnSearchTextField = new JTextField(10);
        tfnSearchTextField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                validateInput(e);
            }
        });
        tfnSearchPanel.add(tfnSearchTextField);
        
        searchTFNButton = new JButton();
        searchTFNButton.setText("Search");
        searchTFNButton.addActionListener(
            new ActionListener() {
                public void actionPerformed( ActionEvent evt ) {
                    queryButtonActionPerformed( evt );
                } // end method actionPerformed
            } // end anonymous inner class
        ); // end call to addActionListener
        tfnSearchPanel.add(searchTFNButton);
        
        // Sub Panel for Last Name
        lastNameSearchPanel = new JPanel();
        lastNameSearchPanel.setBorder( BorderFactory.createTitledBorder(
            "Find entry by Last Name" )); 
        searchPanel.add(lastNameSearchPanel);
        
        // Data inside last name sub panel
        lastNameSearchLabel = new JLabel();
        lastNameSearchLabel.setText("Last Name: ");
        lastNameSearchPanel.add(lastNameSearchLabel);
        
        lastNameSearchTextField = new JTextField(10);
        lastNameSearchPanel.add(lastNameSearchTextField);
        
        searchLastNameButton = new JButton();
        searchLastNameButton.setText("Search");
        searchLastNameButton.addActionListener(
            new ActionListener() {
                public void actionPerformed( ActionEvent evt ) {
                    queryButtonActionPerformed( evt );
                } // end method actionPerformed
            } // end anonymous inner class
        ); // end call to addActionListener
        lastNameSearchPanel.add(searchLastNameButton);
        
        // Exit Button
        exitButton = new JButton();
        exitButton.setText("Exit");
        exitButton.addActionListener((ActionEvent evt) -> {
            close();
        }); 
        wrapperPanel.add(exitButton);
        
        setBrowsing(false);
        setVisible( true );
    }
    
   
    @Override
    // set the presenter for this view
    public void bind( TaxPayerPresenter tpp) {
        presenter = tpp;
    }

    @Override
    public void setBrowsing(Boolean f) {
        nextButton.setEnabled(f);
        previousButton.setEnabled( f );
    }

    @Override
    public void displayMessage(String m) {
        JOptionPane.showMessageDialog( this, m );
    }

    @Override
    public void displayTaxPayer(TaxPayer p) {
        tfnTextField.setText("" + p.gettFN());
        firstNameTextField.setText("" + p.getFirstName());
        lastNameTextField.setText("" + p.getLastName());
        addressTextField.setText("" + p.getAddress());
        phoneTextField.setText("" + p.getPhone());
        incomeTextField.setText("" + p.getIncome());
        deductibleTextField.setText("" + p.getDeductible());
        taxHeldTextField.setText("" + p.getTaxHeld());
        taxReturnedTextField.setText("" + p.getTaxReturned());
        
    }

    @Override
    public void displayMaxAndCurrent(int m, int c) {
        lengthTextField.setText(""+m);
        indexTextField.setText(""+(c+1));
    }
    
    @Override
    public void displayTextArea(String s) {
        displayArea.setText(String.format("%7s %10s %10s %25s %10s %7s %10s %8s %12s\n", "TFN", "First Name", "Last Name", "Address", "Phone", "Income", "Deductible", "Tax Held", "Tax Returned") + s);
    }
    
    // handles exit button and window close
    private void close() {
        presenter.close();
        System.exit( 0 );
    }
    
    // handles when income or deductible are updated.
    private void onIncomeUpdate() {
        int income = 0;
        int deductible = 0;
        try {
            income = Integer.parseInt(incomeTextField.getText());
            deductible = Integer.parseInt(deductibleTextField.getText());
            taxHeldTextField.setText("" + TaxPayer.computeTax(income));
            taxReturnedTextField.setText("" + TaxPayer.computeTaxReturned(income,deductible));
        } catch (java.lang.NumberFormatException e) {
            
        }
    }
    
    // handles when any integer textfield is editted
    private void validateInput(KeyEvent e) {
        JTextField textField = (JTextField) e.getSource();
        try {
            Integer.parseInt(textField.getText());
        } catch (java.lang.NumberFormatException ex) {
            displayMessage("Input must be a number");
            textField.setText("0");
        }
    }
    
    // handles when insert button is pressed
    private void insertNewTaxPayer() {
        int tfn = Integer.parseInt(tfnTextField.getText());
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String address = addressTextField.getText();
        String phone = phoneTextField.getText();
        int income = Integer.parseInt(incomeTextField.getText());
        int deductible = Integer.parseInt(deductibleTextField.getText());
        
        presenter.insertNewEntry(tfn, firstName, lastName, address, phone, income, deductible);
    }
    
    // handles when update button is pressed
    private void updateTaxPayer() {
        int tfn = Integer.parseInt(tfnTextField.getText());
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String address = addressTextField.getText();
        String phone = phoneTextField.getText();
        int income = Integer.parseInt(incomeTextField.getText());
        int deductible = Integer.parseInt(deductibleTextField.getText());
        
        presenter.updateEntry(tfn, firstName, lastName, address, phone, income, deductible);
    }
    
    // handles call when queryButton is clicked
    private void queryButtonActionPerformed( ActionEvent evt ) {
        if(evt.getSource() == searchTFNButton) {
            if (tfnSearchTextField.getText().length() == 0) {
                displayMessage("TFN requires an input.");
            } else {
                presenter.performQueryByTFN(Integer.parseInt(tfnSearchTextField.getText()));
            }
            
        } else {
            if (lastNameSearchTextField.getText().length() == 0) {
                displayMessage("Last name requires an input.");
            } else {
                presenter.performQueryByName(lastNameSearchTextField.getText());
            }
        }
    } 
}
