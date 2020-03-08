import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class NewCustomerStrategy extends UserStrategy{

	
public void menuStart(){
//	f.dispose();	
	
	f1 = new JFrame("Create New Customer");
	setFrameUI(f1);
	
	
		Container content = f1.getContentPane();
		content.setLayout(new BorderLayout());
		
		firstNameLabel = new JLabel("First Name:", SwingConstants.RIGHT);
		surnameLabel = new JLabel("Surname:", SwingConstants.RIGHT);
		pPPSLabel = new JLabel("PPS Number:", SwingConstants.RIGHT);
		dOBLabel = new JLabel("Date of birth", SwingConstants.RIGHT);
		firstNameTextField = new JTextField(20);
		surnameTextField = new JTextField(20);
		pPSTextField = new JTextField(20);
		dOBTextField = new JTextField(20);
		JPanel panel = new JPanel(new GridLayout(6, 2));
		panel.add(firstNameLabel);
		panel.add(firstNameTextField);
		panel.add(surnameLabel);
		panel.add(surnameTextField);
		panel.add(pPPSLabel);
		panel.add(pPSTextField);
		panel.add(dOBLabel);
		panel.add(dOBTextField);
			
		panel2 = new JPanel();
		add = new JButton("Add");
		
		 add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				
	PPS = pPSTextField.getText();
	firstName = firstNameTextField.getText();
	surname = surnameTextField.getText();
	DOB = dOBTextField.getText();
	password = "";

	CustomerID = "ID"+PPS ;
	

	
	
	
	
	add.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			f1.dispose();
			
			boolean loop = true;
			while(loop){
			 password = JOptionPane.showInputDialog(f, "Enter 7 character Password;");
			
			 if(password.length() != 7)//Making sure password is 7 characters
			    {
			    	JOptionPane.showMessageDialog(null, null, "Password must be 7 charatcers long", JOptionPane.OK_OPTION);
			    }
			 else
			 {
				 loop = false;
			 }
			}
			
			
			
			
		    ArrayList<CustomerAccount> accounts = new ArrayList<CustomerAccount> ();
					Customer customer = new Customer(PPS, surname, firstName, DOB, CustomerID, password, accounts);
						
					customerCollection.getCustomerList().add(customer);
				
					JOptionPane.showMessageDialog(f, "CustomerID = " + CustomerID +"\n Password = " + password  ,"Customer created.",  JOptionPane.INFORMATION_MESSAGE);
					menuStart();
				f.dispose();
		}
	});	
			}
		});						
		JButton cancel = new JButton("Cancel");					
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f1.dispose();
				menuStart();
			}
		});	
		
		panel2.add(add);
		panel2.add(cancel);
		
		content.add(panel, BorderLayout.CENTER);
		content.add(panel2, BorderLayout.SOUTH);

		f1.setVisible(true);		
}

}
