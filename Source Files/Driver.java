//package cop2800_final;


import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;

public class Driver {

    private PrintWriter output;
    private FileWriter write;
    private SavingAccount sa;
    private String option;
    Scanner sc;

    public Driver() throws FileNotFoundException, IOException {
        write = new FileWriter("data.txt", true);
        output = new PrintWriter(write);
        sc=new Scanner(System.in);
    }

    public static void main(String args[]) throws FileNotFoundException, IOException {
        Driver driver;

        //initialize initial elements
        driver = new Driver();
        driver.sa = new SavingAccount(1000, 0.2);
        driver.option = "";//--used in switch

        //menu system setup with do-while
        do {
            menu(); //--in charge of display menu options using menu() method
            updateCurrentInformation(driver.sa,driver); //--in charge of display information about the account
            driver.option = driver.sc.nextLine();//--option holder

            switch (driver.option) {
                case "1": //Deposit option is selected 
                    Deposit(driver.sa,driver);
                    break;
                case "2"://withdraw option is selected
                    Withdraw(driver.sa,driver);
                    break;
                case "3"://monthly process option is selected
                    monthlyProcess(driver.sa,driver);
                    break;
                case "0": // exit option is selected
                    System.out.println("See you soon!");
                    break;
                default://any other no valid option is entered
                    System.out.println("Not a valid option");
            }
        } while (!driver.option.equals("0")); //END OF DO-WHILE
        driver.output.close();
    }

    public static void Deposit(SavingAccount S,Driver driver) {
        
        double amount;
        System.out.println("Enter Deposit Amount");
        try {
            amount = driver.sc.nextDouble();//--hold amount to deposit
            driver.output.println("+ $"+amount);
            S.deposit(amount);//--add amount to account balance
            driver.sc.nextLine();//--empty token (needed for exceptions canner handling)
        } catch (InputMismatchException e) {
            //this is in case any other thing that's not a number is entered
            System.out.println("Input not valid, amount must be a number");
            driver.output.println("Input not valid, amount must be a number");

        } catch (IllegalArgumentException e) {
            //if account is not Active
            System.out.println("Account is not Active!");
            driver.output.println("Account is not Active!");
        }
    }//END OF DEPOSIT

    public static void Withdraw(SavingAccount S,Driver driver) {
        
        double amount;
        System.out.println("Enter the amount to withdraw: ");
        try {
            amount = driver.sc.nextDouble();//--hold amount to withdraw
            driver.output.println("- $"+amount);
            S.withdraw(amount);//--withdraw amount from balance
            driver.sc.nextLine();//--empty token (needed for exceptions canner handling)
        } catch (ArithmeticException e) {
            //if withdraw makes account negative
            System.out.println("Not allowed. This will set Balance to negative value!");
            driver.output.println("Not allowed. This will set Balance to negative value!");
        } catch (InputMismatchException e) {
            //if something but a number is entered
            System.out.println("Input not valid, amount must be a number");
            driver.output.println("Input not valid, amount must be a number");
        } catch (IllegalArgumentException e) {
            //if accouunt is not active
            System.out.println("Account is not Active!");
            driver.output.println("Account is not Active!");
        }

    }//END OF WITHDRAW

    public static void monthlyProcess(SavingAccount S, Driver driver) {
        try {
            S.monthlyProcess();//--method in saving account class
            System.out.println("Monthly Process done");
            driver.output.println("Monthly Process done");
        } catch (IllegalArgumentException e) {
            //if account is not active
            System.out.println("Account is not Active!");
            driver.output.println("Account is not Active!");
        }
    }//END OF MONTHLY PROCESS

    public static void menu() {
        //provides a menu for each loop iteration in main
        System.out.println("");
        System.out.println("Choose an option: ");
        System.out.println("1- Perform a deposite");
        System.out.println("2- Perform a withdraw");
        System.out.println("3- Carry out the monthly Process");
        System.out.println("0- Exit");
    } //END OF MENU

    public static void updateCurrentInformation(SavingAccount sa,Driver driver) {
        //allows to update information about the account balance, annual rate and current number of withdraw
        //also displays this information
      //
        System.out.println("");
        String outPut = String.format("Current Information: "
                + "|Balance %1$s| |Annual Rate %2$s| |Amount of Withdrawals %3$s|", Double.toString(sa.getBalance()), Double.toString(sa.getAnnualInterestRate()), Integer.toString(sa.getNumWithdrawals()),"\n");
        System.out.println(outPut);
        driver.output.println(outPut);
        
    }//END OF UPDATE CURRENT INFORMATION
    
   
}
