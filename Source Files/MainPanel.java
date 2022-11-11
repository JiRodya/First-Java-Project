package cop2800_final;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {

    private OptionPanel options;
    private InformationPanel information;
    private SavingAccount account;

    public MainPanel(SavingAccount sa) {

        //layout
        setLayout(new BorderLayout());

        //initialize elements 
        account = new SavingAccount(sa.getBalance(), sa.getAnnualInterestRate());
        options = new OptionPanel();
        information = new InformationPanel();

        //SETTING PREFERRED SIZES
        information.setPreferredSize(new Dimension(350, 150));
        options.setPreferredSize(new Dimension(150, 100));

        // BdLayout=new JPanel(new BorderLayout());
        //add to border layout
        add(options, BorderLayout.WEST);
        add(information);
    }

    public class OptionPanel extends JPanel {

        private JRadioButton depositOption, withdrawOption, monthlyProcessOption;
        private JButton okButton;
        private JPanel grid;

        private ButtonGroup bg; //to group radiobuttons

        public OptionPanel() {
            //layout
            setLayout(new BorderLayout());

            //initialize elements
            grid = new JPanel(new GridLayout(3, 1));
            depositOption = new JRadioButton("Deposit");
            withdrawOption = new JRadioButton("Withdraw");
            monthlyProcessOption = new JRadioButton("Monthly Process");
            okButton = new JButton("OK");

            //listeners
            okButton.addActionListener(new okButtonListener());
            //button size
            okButton.setPreferredSize(new Dimension(10, 30));

            //initialize group button and add elements to it
            bg = new ButtonGroup();
            bg.add(depositOption);
            bg.add(withdrawOption);
            bg.add(monthlyProcessOption);

            //border 
            setBorder(BorderFactory.createTitledBorder("Account Managment"));

            //add elements to grid panel
            grid.add(depositOption);
            grid.add(withdrawOption);
            grid.add(monthlyProcessOption);
            //grid.add(okButton);

            //add elements to this panel using border panel layout
            add(grid, BorderLayout.WEST);
            add(okButton, BorderLayout.SOUTH);

        }

        public class okButtonListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = 0;

              //OPTION FOR BALANCE
                    if (depositOption.isSelected()) {
                        try {
                            //--normal add
                            amount = Double.parseDouble(JOptionPane.showInputDialog("Deposit Amount: "));
                            account.deposit(amount);
                        } //if textfield is blank or non-numbers are added
                        catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Input Not Valid!", "Invalid Argument", JOptionPane.ERROR_MESSAGE);
                        } catch (IllegalArgumentException ex) {
                            JOptionPane.showMessageDialog(null, "Account is not Active!", "Activation", JOptionPane.ERROR_MESSAGE);
                        } catch (Exception ex) {
                            //JUST GO BACK TO MAIN MENU
                        }

                    } //OPTION FOR WITHDRAW
                    else if (withdrawOption.isSelected()) {

                        try {
                            //-normal add
                            amount = Double.parseDouble(JOptionPane.showInputDialog("Withdraw Amount: "));
                            account.withdraw(amount);
                        } //-if amount to withdraw is bigger than balance
                        catch (ArithmeticException ex) {
                            JOptionPane.showMessageDialog(null, "Not allowed. This will set Balance to negative value!", "Invalid Argument", JOptionPane.ERROR_MESSAGE);
                        } //-if textfield is empty or non-number are added
                        catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Input Not Valid!", "Invalid Argument", JOptionPane.ERROR_MESSAGE);
                        } //if account  is not active
                        catch (IllegalArgumentException ex) {
                            JOptionPane.showMessageDialog(null, "Account is not Active!", "Invalid Argument", JOptionPane.ERROR_MESSAGE);
                        } catch (Exception ex) {
                            //JUST GO BACK TO MAIN MENU
                        }

                    } //OPTION FOR MONTHLY PROCESS
                    else if (monthlyProcessOption.isSelected()) {
                        account.monthlyProcess();
                    }
                    else
                        JOptionPane.showMessageDialog(null, "No option was selected", "Invalid Argument", JOptionPane.ERROR_MESSAGE);

                    updateInformationPanel();//--UPDATE INFORMATION SHOWED
                
            }

        }
    }

    public class InformationPanel extends JPanel {

        private NameLabel names;
        private Data data;
       

        public InformationPanel() {
            //set layout
            setLayout(new BorderLayout());

            //initialize
            names = new NameLabel();
            data = new Data();
            names.setPreferredSize(new Dimension(200, 10));
            data.setPreferredSize(new Dimension(100, 10));

            // border
            setBorder(BorderFactory.createTitledBorder("Account's Current Information"));
            //add components
            add(names, BorderLayout.WEST);
            add(data, BorderLayout.EAST);
        }

        public class NameLabel extends JPanel {

            private JLabel balanceLabel, annualRateBalance, withdrawNumber;

            public NameLabel() {
                //grid
                setLayout(new GridLayout(3, 1));

                //initialize elements
                balanceLabel = new JLabel("Current Balance");
                annualRateBalance = new JLabel("Current Annual Rate");

                withdrawNumber = new JLabel("Current Amount of Withdrawals");
                //set border
                //--no border in this case

                //add elements
                add(balanceLabel);
                add(annualRateBalance);
                add(withdrawNumber);

            }
        }

        public class Data extends JPanel {

            private JLabel balanceInformation, annualRateInformation, withdrawNumberInformation;

            public Data() {
                //grid
                setLayout(new GridLayout(3, 1));

                //initialize elements
                balanceInformation = new JLabel(Double.toString(account.getBalance()));
                annualRateInformation = new JLabel(Double.toString(account.getAnnualInterestRate()));

                withdrawNumberInformation = new JLabel(Integer.toString(account.getNumWithdrawals()));

                //border-- N/A
                //add elements
                add(balanceInformation);
                add(annualRateInformation);

                add(withdrawNumberInformation);
            }

            public void setBalanceInformation() {
                this.balanceInformation.setText(Double.toString(account.getBalance()));
            }

            public void setAnnualRateInformation() {
                this.annualRateInformation.setText(Double.toString(account.getAnnualInterestRate()));
            }

            public void setWithdrawNumberInformation() {
                this.withdrawNumberInformation.setText(Integer.toString(account.getNumWithdrawals()));
            }

            public JLabel getBalanceInformation() {
                return balanceInformation;
            }

            public JLabel getAnnualRateInformation() {
                return annualRateInformation;
            }

            public JLabel getWithdrawNumberInformation() {
                return withdrawNumberInformation;
            }
        }

    }

    public void updateInformationPanel() {
        information.data.setBalanceInformation();
        information.data.setAnnualRateInformation();

        information.data.setWithdrawNumberInformation();
    }
}
