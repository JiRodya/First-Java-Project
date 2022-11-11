
//package cop2800_final;

public abstract class BankAccount {

    //elements decalaration
    private double Balance, annualInterestRate, monthlyServiceCharges;
    private int numMonthDeposit, numWithdrawals;

    //constructor
    public BankAccount(double balance, double annualInterestRate) {
        this.Balance = balance;
        this.annualInterestRate = annualInterestRate;
        this.monthlyServiceCharges = 0.0;
        this.numMonthDeposit = 0;
        this.numWithdrawals = 0;
    }

    //Deposit Method
    public void deposit(double amount) {
        this.setBalance(this.getBalance() + amount);
        numMonthDeposit++;
    }

    //Withdraw Method
    public void withdraw(double amount) {
        this.setBalance(this.getBalance() - amount);
        numWithdrawals++;
    }

    //Interest Calculator Method
    public void calcInterest() {
        double monthlyInterestRate = (getAnnualInterestRate() / 12);
        double monthlyInterest = getBalance() * monthlyInterestRate;
        setBalance(getBalance() + monthlyInterest);
    }

    //monthly Process method
    public void monthlyProcess() {
        setBalance(this.getBalance() - getMonthlyServiceCharges());
        calcInterest();
        setNumWithdrawals(0);
        setNumMonthDeposit(0);
        setMonthlyServiceCharges(0);
    }

    /**
     * @return the Balance
     */
    public double getBalance() {
        return Balance;
    }

    /**
     * @param Balance the Balance to set
     */
    public void setBalance(double Balance) {
        this.Balance = Balance;
    }

    /**
     * @return the annualInterestRate
     */
    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    /**
     * @param annualInterestRate the annualInterestRate to set
     */
    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    /**
     * @return the monthlyServiceCharges
     */
    public double getMonthlyServiceCharges() {
        return monthlyServiceCharges;
    }

    /**
     * @param monthlyServiceCharges the monthlyServiceCharges to set
     */
    public void setMonthlyServiceCharges(double monthlyServiceCharges) {
        this.monthlyServiceCharges = monthlyServiceCharges;
    }

    /**
     * @return the numMonthDeposit
     */
    public int getNumMonthDeposit() {
        return numMonthDeposit;
    }

    /**
     * @param numMonthDeposit the numMonthDeposit to set
     */
    private void setNumMonthDeposit(int numMonthDeposit) {
        this.numMonthDeposit = numMonthDeposit;
    }

    /**
     * @return the numWithdrawals
     */
    public int getNumWithdrawals() {
        return numWithdrawals;
    }

    /**
     * @param numWithdrawals the numWithdrawals to set
     */
    private void setNumWithdrawals(int numWithdrawals) {
        this.numWithdrawals = numWithdrawals;
    }

}
