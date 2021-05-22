package taxrecordsystem.model;



// TaxPayer class 
public class TaxPayer
{
    private int tFN; //TFN number
    private String firstName;//taxpayer first name
    private String lastName;
    private String address;
    private String phone;

    private int income;
    private int deductible;

    private int taxHeld = 0;
    private int taxReturned = 0;

    //default constructor
    public TaxPayer()
    {
        this(0,"","","","",0,0);
    }

    // parameterised constructor
    public TaxPayer(int tFN, String firstName,String lastName, String address, String phone, int income, int deductible)
    {
        this.tFN = tFN;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.income = income;
        this.deductible = deductible;

        this.setTaxHeld();
        this.setTaxReturned();
    }

    //get, set methods for TFN, firstName etc

    public int gettFN() {
        return tFN;
    }

    public void settFN(int tFN) {
        this.tFN = tFN;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getDeductible() {
        return deductible;
    }

    public void setDeductible(int deductible) {
        this.deductible = deductible;
    }
    
    public int getTaxHeld() {
        return taxHeld;
    }
    
    public void setTaxHeld() {
        this.taxHeld = computeTax(income);
    }
    
    public int getTaxReturned() {
        return taxReturned;
    }
    
    public void setTaxReturned() {
        int actualTax = computeTax(income-deductible);
        this.taxReturned = (taxHeld-actualTax);
    }
    
    // Static methods for helpers.
    public static int computeTax(int income) 
    {
        // Tax rates
        final double TAX_RATE1 = 0.19;
        final double TAX_RATE2 = 0.325;
        final double TAX_RATE3 = 0.37;
        final double TAX_RATE4 = 0.47;
        
        // Threshold incomes
        final int THR1 = 18200;
        final int THR2 = 37000;
        final int THR3 = 87000;
        final int THR4 = 180000;

        int tax = 0;

        if(income <= THR1) {
            tax = 0;
        } else if(income <= THR2) {
            tax = (int) Math.round((income - THR1) * TAX_RATE1);
        } else if(income <= THR3) {
            tax = (int) Math.round(3572+(income - THR2) * TAX_RATE2);
        } else if(income < THR4) {
            tax = (int) Math.round(19822+(income - THR3) * TAX_RATE3);
        } else {
            tax = (int) Math.round(54232+(income - THR4) * TAX_RATE4);
        }

        return tax;
    } // end of method
    
    // Static methods for helpers.
    public static int computeTaxReturned(int income, int deductible) {
        return computeTax(income) - computeTax(income - deductible);
    }
    
    @Override
    public String toString() {
        //int tFN, String firstName,String lastName, String address, String phone, int income, int deductible
        return String.format("%7s %10s %10s %25s %10s %7s %10s %8s %12s\n", tFN, firstName, lastName, address, phone, income, deductible, taxHeld, taxReturned);
    }

}//end of class