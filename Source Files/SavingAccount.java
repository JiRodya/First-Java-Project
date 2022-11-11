
package cop2800_final;

import java.util.InputMismatchException;


public class SavingAccount extends BankAccount{
    private boolean status;
    
    public SavingAccount(double balance, double annualInterestRate) {
        super(balance, annualInterestRate);
    }
    public boolean isActive(){
        status=true;
        if(this.getBalance()<25){
            status=false;
        }
        return status;
    }
    @Override
    public void withdraw(double amount){
        if(isActive()){
            if(amount>this.getBalance())
                throw new ArithmeticException();
            super.withdraw(amount);
            
        }
        else 
            throw new IllegalArgumentException();
    }//END OF WITHDRAW
    
    @Override
    public void deposit(double amount){
        if(isActive())
            super.deposit(amount);
        else if((this.getBalance()+amount)>25)
            super.deposit(amount);
        else
            throw new IllegalArgumentException();
       
    }
    @Override
    public void monthlyProcess(){
        if(this.getNumWithdrawals()>4)
            super.setMonthlyServiceCharges(this.getMonthlyServiceCharges()+this.getNumWithdrawals()-4 );
        //if(this.isActive())
        super.monthlyProcess();
        //else
            //throw new IllegalArgumentException();
    }
}
