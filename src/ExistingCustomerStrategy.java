import java.util.Date;

import javax.swing.JOptionPane;

public class ExistingCustomerStrategy extends UserStrategy {

	@Override
	public void menuStart() {
		// TODO Auto-generated method stub

		boolean loop = true, loop2 = true;
		boolean cont = false;
		boolean found = false;
		Customer customer = null;
	    while(loop)
	    {
	    Object customerID = JOptionPane.showInputDialog(f, "Enter Customer ID:");
	    
	    for (Customer aCustomer: customerCollection.getCustomerList()){
	    	
	    	if(aCustomer.getCustomerID().equals(customerID))//search customer list for matching customer ID
	    	{
	    		found = true;
	    		customer = aCustomer;
	    	}					    	
	    }
	    
	    if(found == false)
	    {
	    	int reply  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
	    	if (reply == JOptionPane.YES_OPTION) {
	    		loop = true;
	    	}
	    	else if(reply == JOptionPane.NO_OPTION)
	    	{
	    		f.dispose();
	    		loop = false;
	    		loop2 = false;
	    		menuStart();
	    	}
	    }
	    else
	    {
	    	loop = false;
	    }
	   
	    }
	    
	    while(loop2)
	    {
	    	Object customerPassword = JOptionPane.showInputDialog(f, "Enter Customer Password;");
	    	
	    	   if(!customer.getPassword().equals(customerPassword))//check if custoemr password is correct
			    {
			    	int reply  = JOptionPane.showConfirmDialog(null, null, "Incorrect password. Try again?", JOptionPane.YES_NO_OPTION);
			    	if (reply == JOptionPane.YES_OPTION) {
			    		
			    	}
			    	else if(reply == JOptionPane.NO_OPTION){
			    		f.dispose();
			    		loop2 = false;
			    		menuStart();
			    	}
			    }
	    	   else
	    	   {
	    		   loop2 =false;
	    		   cont = true;
	    	   }
	    }
	    	
	    if(cont)
	    {
		f.dispose();
	    	loop = false;
	    	customer(customer);				    
	    }				    
	
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void makeLodgement(CustomerAccount acc, Customer customer) {

		double balance = 0;
				
		
		//IF ATM LOGIN PASS THEN
		if(acc instanceof CustomerCurrentAccount && AccountTransaction.CheckATMLogin(customer, f, acc) == true)
				{
			String balanceTest = JOptionPane.showInputDialog(f, "Enter amount you wish to lodge:");
			if(AccountTransaction.isNumeric(balanceTest))
			{
				
				balance = Double.parseDouble(balanceTest);	
				acc.setBalance(acc.getBalance() + balance);
				
			}
			else
			{
				customer(customer);
				JOptionPane.showMessageDialog(f, "You must enter a numerical value!" ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
			}			
			
			
			AccountTransaction transaction = new AccountTransaction((new Date()).toString(), "Lodgement", balance);
			acc.getTransactionList().add(transaction);
			
		 JOptionPane.showMessageDialog(f, balance + euro + " added do you account!" ,"Lodgement",  JOptionPane.INFORMATION_MESSAGE);
		 JOptionPane.showMessageDialog(f, "New balance = " + acc.getBalance() + euro ,"Lodgement",  JOptionPane.INFORMATION_MESSAGE);
		}

		
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void withdrawMoney(CustomerAccount cacc, Customer cust) {

		double withdraw = 0;


		if (cacc instanceof CustomerCurrentAccount && AccountTransaction.CheckATMLogin(cust, f, cacc) == true) {

			String balanceTest = JOptionPane.showInputDialog(f, "Enter amount you wish to withdraw (max 500):");
			
			if (AccountTransaction.isNumeric(balanceTest)) {
				withdraw = Double.parseDouble(balanceTest);

			} else {
				customer(cust);
				JOptionPane.showMessageDialog(f, "You must enter a numerical value!", "Oops!",
						JOptionPane.INFORMATION_MESSAGE);
			}
			if (withdraw > 500) {
				JOptionPane.showMessageDialog(f, "500 is the maximum you can withdraw at a time.", "Oops!",
						JOptionPane.INFORMATION_MESSAGE);
				withdraw = 0;
			}
			if (withdraw > cacc.getBalance()) {
				JOptionPane.showMessageDialog(f, "Insufficient funds.", "Oops!", JOptionPane.INFORMATION_MESSAGE);
				withdraw = 0;
			}

			cacc.setBalance(cacc.getBalance() - withdraw);

			AccountTransaction transaction = new AccountTransaction((new Date()).toString(), "Withdraw", withdraw);
			cacc.getTransactionList().add(transaction);

			JOptionPane.showMessageDialog(f, withdraw + euro + " withdrawn.", "Withdraw",
					JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(f, "New balance = " + cacc.getBalance() + euro, "Withdraw",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}
}
