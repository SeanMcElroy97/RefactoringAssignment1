import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class StrategyAdmin extends UserStrategy {

	@Override
	public void menuStart() {
		// TODO Auto-generated method stub

		String adminUsername = "";
		String adminPassword ="";
		
		boolean cont = false;
	    while(!adminUsername.equals("admin"))
	    {
	    	adminUsername = JOptionPane.showInputDialog(f, "Enter Administrator Username:");

	    if(!adminUsername.equals("admin"))//search admin list for admin with matching admin username
	    {
	    	int reply  = JOptionPane.showConfirmDialog(null, null, "Incorrect Username. Try again?", JOptionPane.YES_NO_OPTION);
	    	
	    	if(reply == JOptionPane.NO_OPTION)
	    	{
	    		f1.dispose();
	    		menuStart();
	    	}
	    }
				    
	    }
	    
	    while(!adminPassword.equals("admin11"))
	    {
	    		adminPassword = JOptionPane.showInputDialog(f, "Enter Administrator Password;");
	    	
	    	   if(!adminPassword.equals("admin11"))//search admin list for admin with matching admin password
			    {
			    	int reply  = JOptionPane.showConfirmDialog(null, null, "Incorrect Password. Try again?", JOptionPane.YES_NO_OPTION);
			    	if(reply == JOptionPane.NO_OPTION){
			    		f1.dispose();
			    		menuStart();
			    	}
			    }
	    }
	    	
	    if(adminUsername.equals("admin") && adminPassword.equals("admin11"))
	    {
		f1.dispose();
	    admin();					    
	    }					    
	
	}

	
	public void bankCharge(Customer customerObj) {
	
		if(customerCollection.customerListIsEmpty(f))
		{
			admin();
		}
		else
			{
		    String customerID = JOptionPane.showInputDialog(f, "Customer ID of Customer You Wish to Apply Charges to:");
		    
		    if(customerCollection.findCustomerBYID(CustomerID) == null)
		    {
		    	int reply  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
		    	if (reply == JOptionPane.YES_OPTION) {
		    		bankCharge(customerObj);
		    	}
		    	else if(reply == JOptionPane.NO_OPTION)
		    	{
		    		f.dispose();		    	
		    		admin();
		    	}
		    }
		    
		    
		    else
		    {
		    	f.dispose();
		    	f = new JFrame("Administrator Menu");
		    	setFrameUI(f);
			
			
			    JComboBox<String> box = new JComboBox<String>();
			    for (String accNum: customerObj.getAllAccountNumbers())
			    {
			     box.addItem(accNum);
			    }
				
			    
			    box.getSelectedItem();
			
			    JPanel boxPanel = new JPanel();
				boxPanel.add(box);
				
				JPanel buttonPanel = new JPanel();
				JButton continueButton = new JButton("Apply Charge");
				JButton returnButton = new JButton("Return");
				buttonPanel.add(continueButton);
				buttonPanel.add(returnButton);
				Container content = f.getContentPane();
				content.setLayout(new GridLayout(2, 1));
				
				content.add(boxPanel);
				content.add(buttonPanel);
				
		
					if(customerCollection.customerListIsEmpty(f))
					{
						admin();
					}
					else
					{
	
						CustomerAccount account = customerObj.getAccountByAccountNumber(box.getSelectedItem().toString());
						
					
											
						continueButton.addActionListener(new ActionListener(  ) {
							public void actionPerformed(ActionEvent ae) {
								
							 	
								
								if(account instanceof CustomerDepositAccount)
								{
								
								
								JOptionPane.showMessageDialog(f, "25" + euro + " deposit account fee aplied."  ,"",  JOptionPane.INFORMATION_MESSAGE);
								account.setBalance(account.getBalance()-25);
								JOptionPane.showMessageDialog(f, "New balance = " + account.getBalance() ,"Success!",  JOptionPane.INFORMATION_MESSAGE);
								}
	
								if(account instanceof CustomerCurrentAccount)
								{
								
								
								JOptionPane.showMessageDialog(f, "15" + euro + " current account fee aplied."  ,"",  JOptionPane.INFORMATION_MESSAGE);
								account.setBalance(account.getBalance()-15);
								JOptionPane.showMessageDialog(f, "New balance = " + account.getBalance() ,"Success!",  JOptionPane.INFORMATION_MESSAGE);
								}
								
								
								f.dispose();				
							admin();				
							}		
					     });
						
						returnButton.addActionListener(new ActionListener(  ) {
							public void actionPerformed(ActionEvent ae) {
								f.dispose();		
								menuStart();				
							}
					     });	
						
							}
		    }
		    }
		    }
	    
	    
	    
	
}
	
	

