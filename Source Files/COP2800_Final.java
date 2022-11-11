
//package cop2800_final;

import javax.swing.*;
import java.awt.*;


public class COP2800_Final extends JFrame {

    //parameters declaration
    private MainPanel mainPanel;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int WINDOW_WIDTH = 500, WINDOW_HEIGHT = 250,
            POSx = screenSize.width / 2, POSy = screenSize.height / 2;

    public COP2800_Final(SavingAccount sa) {

        //Title and creation
        super("Bank Account Manager");

        //size and location
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocation(POSx, POSy);

        //default close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //layout
        setLayout(new BorderLayout());

        //elements initializaiton
        mainPanel = new MainPanel(sa);

        //add elements to frame and make it Visible
        add(mainPanel);
        
        setVisible(true);
    }

    public static void main(String[] args) {
        SavingAccount account;
        COP2800_Final gui;
        

       
        account = new SavingAccount(1000.0, 0.2);
        gui = new COP2800_Final(account);

    }
}
