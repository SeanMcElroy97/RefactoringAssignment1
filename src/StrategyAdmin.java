import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class StrategyAdmin extends UserStrategy {
	
/////////////////return to admin home///////////////////////////////////////////////////////////////////////
	@Override
	public void returnHome(JFrame f) {
		f.dispose();
		admin();
	}
	
	
/////////////////start admin menu///////////////////////////////////////////////////////////////////////
	@Override
	public void userMenuStart() {
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
	    		userMenuStart();
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
			    		userMenuStart();
			    	}
			    }
	    }
	    	
	    if(adminUsername.equals("admin") && adminPassword.equals("admin11"))
	    {
		f1.dispose();
	    admin();					    
	    }					    
	
	}



/////////////////Apply bank charges to a customer/////////////////////////////////////////////////////////////////////////////
	public void bankCharge(Customer customerObj) {
	
		if(customerCollection.customerListIsEmpty(f))
		{
			admin();
		}
		else
			{
		    String customerID = JOptionPane.showInputDialog(f, "Customer ID of Customer You Wish to Apply Charges to:");
		    
		    if(customerCollection.findCustomerBYID(customerID) == null)
		    {
		    	int reply  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
		    	if (reply == JOptionPane.YES_OPTION) {
		    		bankCharge(customerObj);
		    	}
		    	else if(reply == JOptionPane.NO_OPTION)
		    	{
		    		returnHome(f);
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
	
						CustomerAccount customerAcc = customerObj.getAccountByAccountNumber(box.getSelectedItem().toString());
						
					
											
						continueButton.addActionListener(new ActionListener(  ) {
							public void actionPerformed(ActionEvent ae) {
								
							 	
								
								if(customerAcc instanceof CustomerDepositAccount)
								{
								
								
								JOptionPane.showMessageDialog(f, "25" + euro + " deposit account fee aplied."  ,"",  JOptionPane.INFORMATION_MESSAGE);
								customerAcc.setBalance(customerAcc.getBalance()-25);
								JOptionPane.showMessageDialog(f, "New balance = " + customerAcc.getBalance() ,"Success!",  JOptionPane.INFORMATION_MESSAGE);
								}
	
								if(customerAcc instanceof CustomerCurrentAccount)
								{
								
								
								JOptionPane.showMessageDialog(f, "15" + euro + " current account fee aplied."  ,"",  JOptionPane.INFORMATION_MESSAGE);
								customerAcc.setBalance(customerAcc.getBalance()-15);
								JOptionPane.showMessageDialog(f, "New balance = " + customerAcc.getBalance() ,"Success!",  JOptionPane.INFORMATION_MESSAGE);
								}
								
								
								returnHome(f);				
							}		
					     });
						
						returnButton.addActionListener(new ActionListener(  ) {
							public void actionPerformed(ActionEvent ae) {		
								menuStart(f);				
							}
					     });	
						
							}
		    }
		    }
		    }

	
	
//////////////////////Choose customer account to apply interest to////////////////////////////////////////////////////////////////////////
	public void addInterest(Customer customerObj) {

		if (customerCollection.customerListIsEmpty(f)) {
			admin();

		}

		if (!customerCollection.customerListIsEmpty(f)) {
			while (customerObj == null) {
				String customerID = JOptionPane.showInputDialog(f,
						"Customer ID of Customer You Wish to Apply Interest to:");

				customerObj = customerCollection.findCustomerBYID(customerID);

				if (customerObj == null) {
					int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
							JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.NO_OPTION) {
						returnHome(f);
					}
				} else {
					f.dispose();
					f = new JFrame("Administrator Menu");
					setFrameUI(f);

					JComboBox<String> box = new JComboBox<String>();

					for (CustomerAccount cacc: customerObj.getAccounts()) {

						box.addItem(cacc.getNumber());
					}

					box.getSelectedItem();

					JPanel boxPanel = new JPanel();

					JLabel label = new JLabel("Select an account to apply interest to:");
					boxPanel.add(label);
					boxPanel.add(box);
					JPanel buttonPanel = new JPanel();
					JButton continueButton = new JButton("Apply Interest");
					JButton returnButton = new JButton("Return");
					buttonPanel.add(continueButton);
					buttonPanel.add(returnButton);
					Container content = f.getContentPane();
					content.setLayout(new GridLayout(2, 1));

					content.add(boxPanel);
					content.add(buttonPanel);

					if (customerObj.getAccounts().isEmpty()) {
						JOptionPane.showMessageDialog(f,
								"This customer has no accounts! \n The admin must add acounts to this customer.",
								"Oops!", JOptionPane.INFORMATION_MESSAGE);
						returnHome(f);
					} else {

						CustomerAccount cAccount = customerObj.chooseAccount(box.getSelectedItem(), f);

						continueButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {

								double interest = 0;
								String interestString = "";

								while (AccountTransaction.isNumeric(interestString)) {
									interestString = JOptionPane.showInputDialog(f, "Enter interest percentage you wish to apply: \n NOTE: Please enter a numerical value. (with no percentage sign) \n E.g: If you wish to apply 8% interest, enter '8'");
									
									if (AccountTransaction.isNumeric(interestString)) {

										interest = Double.parseDouble(interestString);

										cAccount.setBalance(cAccount.getBalance() + (cAccount.getBalance() * (interest / 100)));

										JOptionPane
												.showMessageDialog(f,
														interest + "% interest applied. \n new balance = "
																+ cAccount.getBalance() + euro,
														"Success!", JOptionPane.INFORMATION_MESSAGE);
									}

									else {
										JOptionPane.showMessageDialog(f, "You must enter a numerical value!", "Oops!",
												JOptionPane.INFORMATION_MESSAGE);
									}

								}

								returnHome(f);
							}
						});

						returnButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {
								menuStart(f);
							}
						});

					}
				}
			}
		}

	}

	
	
/////////////////////////Edit details of a customer///////////////////////////////////////////////////////////////////////
	public void editCustomerDetails(Customer customer) {

		Customer custy = new Customer();

		if (customerCollection.customerListIsEmpty(f)) {
			admin();
		} else {

			while (custy==null) {
				String customerID = JOptionPane.showInputDialog(f, "Enter Customer ID:");

				custy = customerCollection.findCustomerBYID(customerID);

				if (custy == null) {
					int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
							JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.NO_OPTION) {
						f.dispose();
						admin();
					}
				} 
			}

			f.dispose();

			f.dispose();
			f = new JFrame("Administrator Menu");
			setFrameUI(f);

			firstNameLabel = new JLabel("First Name:", SwingConstants.LEFT);
			surnameLabel = new JLabel("Surname:", SwingConstants.LEFT);
			pPPSLabel = new JLabel("PPS Number:", SwingConstants.LEFT);
			dOBLabel = new JLabel("Date of birth", SwingConstants.LEFT);
			customerIDLabel = new JLabel("CustomerID:", SwingConstants.LEFT);
			passwordLabel = new JLabel("Password:", SwingConstants.LEFT);
			firstNameTextField = new JTextField(20);
			surnameTextField = new JTextField(20);
			pPSTextField = new JTextField(20);
			dOBTextField = new JTextField(20);
			customerIDTextField = new JTextField(20);
			passwordTextField = new JTextField(20);

			JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JPanel cancelPanel = new JPanel();

			textPanel.add(firstNameLabel);
			textPanel.add(firstNameTextField);
			textPanel.add(surnameLabel);
			textPanel.add(surnameTextField);
			textPanel.add(pPPSLabel);
			textPanel.add(pPSTextField);
			textPanel.add(dOBLabel);
			textPanel.add(dOBTextField);
			textPanel.add(customerIDLabel);
			textPanel.add(customerIDTextField);
			textPanel.add(passwordLabel);
			textPanel.add(passwordTextField);

			firstNameTextField.setText(custy.getFirstName());
			surnameTextField.setText(custy.getSurname());
			pPSTextField.setText(custy.getPPS());
			dOBTextField.setText(custy.getDOB());
			customerIDTextField.setText(custy.getCustomerID());
			passwordTextField.setText(custy.getPassword());

			JButton saveButton = new JButton("Save");
			JButton cancelButton = new JButton("Exit");

			cancelPanel.add(cancelButton, BorderLayout.SOUTH);
			cancelPanel.add(saveButton, BorderLayout.SOUTH);

			Container content = f.getContentPane();
			content.setLayout(new GridLayout(2, 1));
			content.add(textPanel, BorderLayout.NORTH);
			content.add(cancelPanel, BorderLayout.SOUTH);

			f.setContentPane(content);
			f.setSize(340, 350);
			f.setLocation(200, 200);
			f.setVisible(true);
			f.setResizable(false);

			saveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					customer.setFirstName(firstNameTextField.getText());
					customer.setSurname(surnameTextField.getText());
					customer.setPPS(pPSTextField.getText());
					customer.setDOB(dOBTextField.getText());
					customer.setCustomerID(customerIDTextField.getText());
					customer.setPassword(passwordTextField.getText());

					JOptionPane.showMessageDialog(null, "Changes Saved.");
				}
			});

			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					returnHome(f);
				}
			});
		}
	}


	
////////////////////show all transactions of all customers////////////////////////////////////////////////////////////////
	public void transactionSummaryAllCustomers() {

		f.dispose();
		
		
		f = new JFrame("Summary of Transactions");
		setFrameUI(f);
		
		JLabel label1 = new JLabel("Summary of all transactions: ");
		
		JPanel returnPanel = new JPanel();
		JButton returnButton = new JButton("Return");
		returnPanel.add(returnButton);
		
		JPanel textPanel = new JPanel();
		
		textPanel.setLayout( new BorderLayout() );
		JTextArea textArea = new JTextArea(40, 20);
		textArea.setEditable(false);
		textPanel.add(label1, BorderLayout.NORTH);
		textPanel.add(textArea, BorderLayout.CENTER);
		textPanel.add(returnButton, BorderLayout.SOUTH);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		textPanel.add(scrollPane);
		
	
	for(Customer c: customerCollection.getCustomerList()) {
		for(CustomerAccount ca: c.getAccounts()) {
			ca.displayAllTransactions(textArea);
		}
	}
		
		textPanel.add(textArea);
		content.removeAll();
		
		
		Container content = f.getContentPane();
		content.setLayout(new GridLayout(1, 1));
	//	content.add(label1);
		content.add(textPanel);
		//content.add(returnPanel);
		
		returnButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				returnHome(f);			
			}		
	     });	
	
	}




	
////////////////////Traverse through the customer collection//////////////////////////////////////////////////////////////////////////
	public void navigateCustomerCollection(int positionInCollection) {

		f.dispose();
		
		if(!customerCollection.customerListIsEmpty(f))
		{

		JButton first, previous, next, last, cancel;
		JPanel gridPanel, buttonPanel, cancelPanel;			

		Container content = getContentPane();
		
		content.setLayout(new BorderLayout());
		
		buttonPanel = new JPanel();
		gridPanel = new JPanel(new GridLayout(8, 2));
		cancelPanel = new JPanel();
						
		firstNameLabel = new JLabel("First Name:", SwingConstants.LEFT);
		surnameLabel = new JLabel("Surname:", SwingConstants.LEFT);
		pPPSLabel = new JLabel("PPS Number:", SwingConstants.LEFT);
		dOBLabel = new JLabel("Date of birth", SwingConstants.LEFT);
		customerIDLabel = new JLabel("CustomerID:", SwingConstants.LEFT);
		passwordLabel = new JLabel("Password:", SwingConstants.LEFT);
		firstNameTextField = new JTextField(20);
		surnameTextField = new JTextField(20);
		pPSTextField = new JTextField(20);
		dOBTextField = new JTextField(20);
		customerIDTextField = new JTextField(20);
		passwordTextField = new JTextField(20);
		
		first = new JButton("First");
		previous = new JButton("Previous");
		next = new JButton("Next");
		last = new JButton("Last");
		cancel = new JButton("Cancel");
		
		firstNameTextField.setText(customerCollection.getCustomerList().get(0).getFirstName());
		surnameTextField.setText(customerCollection.getCustomerList().get(0).getSurname());
		pPSTextField.setText(customerCollection.getCustomerList().get(0).getPPS());
		dOBTextField.setText(customerCollection.getCustomerList().get(0).getDOB());
		customerIDTextField.setText(customerCollection.getCustomerList().get(0).getCustomerID());
		passwordTextField.setText(customerCollection.getCustomerList().get(0).getPassword());
		
		firstNameTextField.setEditable(false);
		surnameTextField.setEditable(false);
		pPSTextField.setEditable(false);
		dOBTextField.setEditable(false);
		customerIDTextField.setEditable(false);
		passwordTextField.setEditable(false);
		
		gridPanel.add(firstNameLabel);
		gridPanel.add(firstNameTextField);
		gridPanel.add(surnameLabel);
		gridPanel.add(surnameTextField);
		gridPanel.add(pPPSLabel);
		gridPanel.add(pPSTextField);
		gridPanel.add(dOBLabel);
		gridPanel.add(dOBTextField);
		gridPanel.add(customerIDLabel);
		gridPanel.add(customerIDTextField);
		gridPanel.add(passwordLabel);
		gridPanel.add(passwordTextField);
		
		buttonPanel.add(first);
		buttonPanel.add(previous);
		buttonPanel.add(next);
		buttonPanel.add(last);
		
		cancelPanel.add(cancel);

		content.add(gridPanel, BorderLayout.NORTH);
		content.add(buttonPanel, BorderLayout.CENTER);
		content.add(cancelPanel, BorderLayout.AFTER_LAST_LINE);
		
		
		first.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				customerCollection.navigateListGoToFirst(positionInCollection, firstNameTextField, surnameTextField, pPSTextField, dOBTextField, customerIDTextField, passwordTextField);
			}		
			     });
		
		previous.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				customerCollection.navigateListGoToPrevious(positionInCollection, firstNameTextField, surnameTextField, pPSTextField, dOBTextField, customerIDTextField, passwordTextField);
			}		
			     });
		
		next.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				customerCollection.navigateListToNext(positionInCollection, firstNameTextField, surnameTextField, pPSTextField, dOBTextField, customerIDTextField, passwordTextField);
			}		
			     });
		
		last.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				customerCollection.navigateListToLast(positionInCollection, firstNameTextField, surnameTextField, pPSTextField, dOBTextField, customerIDTextField, passwordTextField);
			}		
			     });
		
		cancel.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {				
				dispose();
				admin();
					}		
			     });			
		setContentPane(content);
		setSize(400, 300);
	       setVisible(true);
			}		
	
	}


	
///////////////////Add a new account to a customer///////////////////////////////////////////////////////////////////////////
	public void addAccountToCustomer(Customer customer) {

		f.dispose();
		
		if(!customerCollection.customerListIsEmpty(f))
		{
//		boolean loop = true;
//		
//		boolean found = false;
		Customer localCustomerOBJ = null;	
	
	    while(localCustomerOBJ == null)
	    {
	    String customerID = JOptionPane.showInputDialog(f, "Customer ID of Customer You Wish to Add an Account to:");
	    
	    customer = customerCollection.findCustomerBYID(customerID);
	    localCustomerOBJ = customer;
	    
	    if(customer == null)
	    {
	    	int reply  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
	    	if(reply == JOptionPane.NO_OPTION)
	    	{
	    		returnHome(f);
	    	}
	    }
	    else
	    {
	    	//a combo box in an dialog box that asks the admin what type of account they wish to create (deposit/current)
		    String[] choices = { "Current Account", "Deposit Account" };
		    String account = (String) JOptionPane.showInputDialog(null, "Please choose account type",
		        "Account Type", JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]); 
		    
		    if(account.equals("Current Account"))
		    {
		    	//create current account
		    	double balance = 0;
		    	String number = String.valueOf("C" + (customerCollection.getCustomerList().indexOf(customer)+1) * 10 + (customer.getAccounts().size()+1));//this simple algorithm generates the account number
		    	ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();
		    	int randomPIN = (int)(Math.random()*9000)+1000;
		           String pin = String.valueOf(randomPIN);
		    
		           ATMCard atm = new ATMCard(randomPIN, true);
		    	
		    	CustomerCurrentAccount current = new CustomerCurrentAccount(atm, number, balance, transactionList);
		    	
		    	customer.getAccounts().add(current);
		    	JOptionPane.showMessageDialog(f, "Account number = " + number +"\n PIN = " + pin  ,"Account created.",  JOptionPane.INFORMATION_MESSAGE);
		    	
		    	returnHome(f);
		    }
		    
		    if(account.equals("Deposit Account"))
		    {
		    	//create deposit account
		    	
		    	double balance = 0, interest = 0;
		    	String number = String.valueOf("D" + (customerCollection.getCustomerList().indexOf(customer)+1) * 10 + (customer.getAccounts().size()+1));//this simple algorithm generates the account number
		    	ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();
		        	
		    	CustomerDepositAccount deposit = new CustomerDepositAccount(interest, number, balance, transactionList);
		    	
		    	customer.getAccounts().add(deposit);
		    	JOptionPane.showMessageDialog(f, "Account number = " + number ,"Account created.",  JOptionPane.INFORMATION_MESSAGE);
		    	
		    	returnHome(f);
		    }
	    
	    }			   
	    }
		}
	
	}


	
//////////////////////Choose a customer to remove////////////////////////////////////////////////////////////////////////	
	public void removeACustomer(Customer customer) {


		if(!customerCollection.customerListIsEmpty(f))
		{
			 {
				    String customerID = JOptionPane.showInputDialog(f, "Customer ID of Customer You Wish to Delete:");
				    
				    if(customerCollection.findCustomerBYID(customerID) == null)
				    {
				    	int reply  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
				    	if(reply == JOptionPane.NO_OPTION)
				    	{
				    		returnHome(f);
				    	}
				    }  
				    else
				    {
				    	if(customer.getAccounts().size()>0)
				    	{
				    		JOptionPane.showMessageDialog(f, "This customer has accounts. \n You must delete a customer's accounts before deleting a customer " ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
				    	}
				    	else
				    	{
				    		customerCollection.getCustomerList().remove(customer);
				    		JOptionPane.showMessageDialog(f, "Customer Deleted " ,"Success.",  JOptionPane.INFORMATION_MESSAGE);
				    	}
				    }
				    
				    
		}}
	
	}


	
///////////////////////Remove an account from a customer///////////////////////////////////////////////////////////////////////
	public void removeACustomerAccount() {



		{
			String customerID = JOptionPane.showInputDialog(f, "Customer ID of Customer from which you wish to delete an account");

			if (customerCollection.findCustomerBYID(customerID) == null) {
				int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.NO_OPTION) {
					returnHome(f);
				}
			} else {
				// Here I would make the user select a an account to delete from a combo box. If
				// the account had a balance of 0 then it would be deleted. (I do not have time
				// to do this)
			}

		}
	}

	
/////////////////////////////////////////////////////////////////////////////////////////////////
}
	
	

