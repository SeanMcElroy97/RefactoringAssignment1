import java.awt.*;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.text.MaskFormatter;
import java.util.ArrayList;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Menu extends JFrame{
	
	UserStrategy userStrategy;
	
    private int position = 0;
	String password;
	private Customer customer = null;
	private CustomerAccount acc = new CustomerAccount();
	JFrame f, f1;
	 JLabel firstNameLabel, surnameLabel, pPPSLabel, dOBLabel;
	 JTextField firstNameTextField, surnameTextField, pPSTextField, dOBTextField;
		JLabel customerIDLabel, passwordLabel;
		JTextField customerIDTextField, passwordTextField;
	Container content;
		Customer customer1;
		
		String euro = "\u20ac";
		//another test

	 JPanel panel2;
		JButton add;
		String 	PPS,firstName,surname,DOB,CustomerID;
		
		
		CustomerCollection customerCollection = new CustomerCollection();
		
		
	public void returnToMenu() {
			menuStart(f);				
	     }
		
	public void setFrameUI(JFrame fr) {
			fr.setSize(400, 300);
			fr.setLocation(200, 200);
			fr.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) { System.exit(0); }
			});
		}
	
	
	
	
	
	
	public static void main(String[] args)

	{
		Menu driver = new Menu();
		driver.menuStart(new JFrame());
	}
	
	
/////////////////////////////menu Start method///////////////////////////////////////////////////////////////////////////////////////////////////////
	public void menuStart(JFrame oldFrame)
	{
		   /*The menuStart method asks the user if they are a new customer, an existing customer or an admin. It will then start the create customer process
		  if they are a new customer, or will ask them to log in if they are an existing customer or admin.*/
			oldFrame.dispose();
			f = new JFrame("User Type");
			setFrameUI(f);

			JPanel userTypePanel = new JPanel();
			final ButtonGroup userType = new ButtonGroup();
			JRadioButton radioButton;
			userTypePanel.add(radioButton = new JRadioButton("Existing Customer"));
			radioButton.setActionCommand("Customer");
			userType.add(radioButton);
			
			userTypePanel.add(radioButton = new JRadioButton("Administrator"));
			radioButton.setActionCommand("Administrator");
			userType.add(radioButton);
			
			userTypePanel.add(radioButton = new JRadioButton("New Customer"));
			radioButton.setActionCommand("New Customer");
			userType.add(radioButton);

			JPanel continuePanel = new JPanel();
			JButton continueButton = new JButton("Continue");
			continuePanel.add(continueButton);

			Container content = f.getContentPane();
			content.setLayout(new GridLayout(2, 1));
			content.add(userTypePanel);
			content.add(continuePanel);



			continueButton.addActionListener(new ActionListener(  ) {
				public void actionPerformed(ActionEvent ae) {
					String user = userType.getSelection().getActionCommand(  );
					
					//if user selects NEW CUSTOMER--------------------------------------------------------------------------------------
					if(user.equals("New Customer"))
					{
						userStrategy = new NewCustomerStrategy();
						userStrategy.menuStart();
						}
					
					
					//------------------------------------------------------------------------------------------------------------------
					
					//if user select ADMIN----------------------------------------------------------------------------------------------
					if(user.equals("Administrator")	)
					{
						userStrategy = new StrategyAdmin();
						userStrategy.menuStart();
					}
					//----------------------------------------------------------------------------------------------------------------
					
					
					
					//if user selects CUSTOMER ---------------------------------------------------------------------------------------- 
					if(user.equals("Customer")	)
					{
						userStrategy = new ExistingCustomerStrategy();
						userStrategy.menuStart();
					}
					//-----------------------------------------------------------------------------------------------------------------------
				}
			});f.setVisible(true);	
	}
	

//////////////////////////////////////Admin method////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void admin()
	{
		dispose();
		
		f = new JFrame("Administrator Menu");
		setFrameUI(f);
		
		JPanel deleteCustomerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton deleteCustomer = new JButton("Delete Customer");	
		deleteCustomer.setPreferredSize(new Dimension(250, 20));
		deleteCustomerPanel.add(deleteCustomer);
		
		JPanel deleteAccountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton deleteAccount = new JButton("Delete Account");
		deleteAccount.setPreferredSize(new Dimension(250, 20));	
		deleteAccountPanel.add(deleteAccount);
		
		JPanel bankChargesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton bankChargesButton = new JButton("Apply Bank Charges");
		bankChargesButton.setPreferredSize(new Dimension(250, 20));	
		bankChargesPanel.add(bankChargesButton);
		
		JPanel interestPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton interestButton = new JButton("Apply Interest");
		interestPanel.add(interestButton);
		interestButton.setPreferredSize(new Dimension(250, 20));
		
		JPanel editCustomerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton editCustomerButton = new JButton("Edit existing Customer");
		editCustomerPanel.add(editCustomerButton);
		editCustomerButton.setPreferredSize(new Dimension(250, 20));
		
		JPanel navigatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton navigateButton = new JButton("Navigate Customer Collection");
		navigatePanel.add(navigateButton);
		navigateButton.setPreferredSize(new Dimension(250, 20));
		
		JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton summaryButton = new JButton("Display Summary Of All Accounts");
		summaryPanel.add(summaryButton);
		summaryButton.setPreferredSize(new Dimension(250, 20));
		
		JPanel accountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton accountButton = new JButton("Add an Account to a Customer");
		accountPanel.add(accountButton);
		accountButton.setPreferredSize(new Dimension(250, 20));
		
		JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton returnButton = new JButton("Exit Admin Menu");
		returnPanel.add(returnButton);

		JLabel label1 = new JLabel("Please select an option");
		
		content = f.getContentPane();
		content.setLayout(new GridLayout(9, 1));
		content.add(label1);
		content.add(accountPanel);
		content.add(bankChargesPanel);
		content.add(interestPanel);
		content.add(editCustomerPanel);
		content.add(navigatePanel);
		content.add(summaryPanel);	
		content.add(deleteCustomerPanel);
		content.add(returnPanel);
		
		
		
		//All methods options an admin user can do
		
		bankChargesButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				((StrategyAdmin) userStrategy).bankCharge(customer);
			}		
	     });
		
		interestButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				((StrategyAdmin) userStrategy).addInterest(customer);
			}	
	     });
		
		editCustomerButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent e) {
				((StrategyAdmin) userStrategy).editCustomerDetails(customer);
			}
			
		});
		
		summaryButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				((StrategyAdmin) userStrategy).transactionSummaryAllCustomers();
			}	
	     });
		
		navigateButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
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
						customerCollection.navigateListGoToFirst(position, firstNameTextField, surnameTextField, pPSTextField, dOBTextField, customerIDTextField, passwordTextField);
					}		
					     });
				
				previous.addActionListener(new ActionListener(  ) {
					public void actionPerformed(ActionEvent ae) {
						customerCollection.navigateListGoToPrevious(position, firstNameTextField, surnameTextField, pPSTextField, dOBTextField, customerIDTextField, passwordTextField);
					}		
					     });
				
				next.addActionListener(new ActionListener(  ) {
					public void actionPerformed(ActionEvent ae) {
						customerCollection.navigateListToNext(position, firstNameTextField, surnameTextField, pPSTextField, dOBTextField, customerIDTextField, passwordTextField);
					}		
					     });
				
				last.addActionListener(new ActionListener(  ) {
					public void actionPerformed(ActionEvent ae) {
						customerCollection.navigateListToLast(position, firstNameTextField, surnameTextField, pPSTextField, dOBTextField, customerIDTextField, passwordTextField);
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
		});
		
		accountButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();
				
				if(!customerCollection.customerListIsEmpty(f))
				{
				boolean loop = true;
				
				boolean found = false;
			
			    while(loop)
			    {
			    Object customerID = JOptionPane.showInputDialog(f, "Customer ID of Customer You Wish to Add an Account to:");
			    
			    customer = customerCollection.findCustomerBYID(CustomerID);
			    
			    if(customer == null)
			    {
			    	int reply  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
			    	if (reply == JOptionPane.YES_OPTION) {
			    		loop = true;
			    	}
			    	else if(reply == JOptionPane.NO_OPTION)
			    	{
			    		f.dispose();
			    		loop = false;
			    	
			    		admin();
			    	}
			    }
			    else
			    {
			    	loop = false;
			    	//a combo box in an dialog box that asks the admin what type of account they wish to create (deposit/current)
				    String[] choices = { "Current Account", "Deposit Account" };
				    String account = (String) JOptionPane.showInputDialog(null, "Please choose account type",
				        "Account Type", JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]); 
				    
				    if(account.equals("Current Account"))
				    {
				    	//create current account
				    	boolean valid = true;
				    	double balance = 0;
				    	String number = String.valueOf("C" + (customerCollection.getCustomerList().indexOf(customer)+1) * 10 + (customer.getAccounts().size()+1));//this simple algorithm generates the account number
				    	ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();
				    	int randomPIN = (int)(Math.random()*9000)+1000;
				           String pin = String.valueOf(randomPIN);
				    
				           ATMCard atm = new ATMCard(randomPIN, valid);
				    	
				    	CustomerCurrentAccount current = new CustomerCurrentAccount(atm, number, balance, transactionList);
				    	
				    	customer.getAccounts().add(current);
				    	JOptionPane.showMessageDialog(f, "Account number = " + number +"\n PIN = " + pin  ,"Account created.",  JOptionPane.INFORMATION_MESSAGE);
				    	
				    	f.dispose();
				    	admin();
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
				    	
				    	f.dispose();
				    	admin();//
				    }
			    
			    }			   
			    }
				}
			}
	     });		

		deleteCustomer.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				boolean found = true, loop = true;
				
				if(!customerCollection.customerListIsEmpty(f))
				{
					 {
						    Object customerID = JOptionPane.showInputDialog(f, "Customer ID of Customer You Wish to Delete:");
						    
						    if(customerCollection.findCustomerBYID(CustomerID) == null)
						    {
						    	int reply  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
						    	if (reply == JOptionPane.YES_OPTION) {
						    		loop = true;
						    	}
						    	else if(reply == JOptionPane.NO_OPTION)
						    	{
						    		f.dispose();
						    		loop = false;
						    		
						    		admin();
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
	     });		
		
		deleteAccount.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
	boolean found = true, loop = true;
				
				
				
				
					 {
						    Object customerID = JOptionPane.showInputDialog(f, "Customer ID of Customer from which you wish to delete an account");
						    
						    						    
						    if(customerCollection.findCustomerBYID(CustomerID) == null)
						    {
						    	int reply  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
						    	if (reply == JOptionPane.YES_OPTION) {
						    		loop = true;
						    	}
						    	else if(reply == JOptionPane.NO_OPTION)
						    	{
						    		f.dispose();
						    		loop = false;
						    	
						    		admin();
						    	}
						    }  
						    else
						    {
						    	//Here I would make the user select a an account to delete from a combo box. If the account had a balance of 0 then it would be deleted. (I do not have time to do this)
						    }
						    
						    
				}}
			
	     });		
		
		
		returnButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				returnToMenu();				
			}
	     });		
	}
	
	
///////////////////////Customer method////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void customer(Customer customerOBJ)
	{	
		f = new JFrame("Customer Menu");
		setFrameUI(f);
		
		if(customerOBJ.getAccounts().size() == 0)
			{
				JOptionPane.showMessageDialog(f, "This customer does not have any accounts yet. \n An admin must create an account for this customer \n for them to be able to use customer functionality. " ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
				returnToMenu();
			}
		else
			{
			//Customer has account
			JPanel buttonPanel = new JPanel();
			JPanel boxPanel = new JPanel();
			JPanel labelPanel = new JPanel();
			
			JLabel label = new JLabel("Select Account:");
			labelPanel.add(label);
			
			JButton returnButton = new JButton("Return");
			buttonPanel.add(returnButton);
			JButton continueButton = new JButton("Continue");
			buttonPanel.add(continueButton);
			
			JComboBox<String> box = new JComboBox<String>(customerOBJ.getAllAccountNumbers());

		    
		    for (CustomerAccount ca: customerOBJ.getAccounts()) {
		    	
		    	if(ca.getNumber() == box.getSelectedItem()) {
		    		acc = ca;
		    	}
		    }
		    
	
		    
			boxPanel.add(box);
			content = f.getContentPane();
			content.setLayout(new GridLayout(3, 1));
			content.add(labelPanel);
			content.add(boxPanel);
			content.add(buttonPanel);
			
			returnButton.addActionListener(new ActionListener(  ) {
				public void actionPerformed(ActionEvent ae) {
					returnToMenu();			
				}		
		     });
			
			continueButton.addActionListener(new ActionListener(  ) {
				public void actionPerformed(ActionEvent ae) {
					
			f.dispose();
			
			f = new JFrame("Customer Menu");
			setFrameUI(f);
			
			JPanel statementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JButton statementButton = new JButton("Display Bank Statement");
			statementButton.setPreferredSize(new Dimension(250, 20));
			
			statementPanel.add(statementButton);
			
			JPanel lodgementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JButton lodgementButton = new JButton("Lodge money into account");
			lodgementPanel.add(lodgementButton);
			lodgementButton.setPreferredSize(new Dimension(250, 20));
			
			JPanel withdrawalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JButton withdrawButton = new JButton("Withdraw money from account");
			withdrawalPanel.add(withdrawButton);
			withdrawButton.setPreferredSize(new Dimension(250, 20));
			
			JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			JButton returnButton = new JButton("Exit Customer Menu");
			returnPanel.add(returnButton);
	
			JLabel label1 = new JLabel("Please select an option");
			
			content = f.getContentPane();
			content.setLayout(new GridLayout(5, 1));
			content.add(label1);
			content.add(statementPanel);
			content.add(lodgementPanel);
			content.add(withdrawalPanel);
			content.add(returnPanel);
			
			statementButton.addActionListener(new ActionListener(  ) {
				public void actionPerformed(ActionEvent ae) {
					f.dispose();
					f = new JFrame("Customer Menu");
					setFrameUI(f);
					
					JLabel label1 = new JLabel("Summary of account transactions: ");
					
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
					
					for (int i = 0; i < acc.getTransactionList().size(); i ++)
					{
						textArea.append(acc.getTransactionList().get(i).toString());
						
					}
					
					textPanel.add(textArea);
					content.removeAll();
					
					
					Container content = f.getContentPane();
					content.setLayout(new GridLayout(1, 1));
					content.add(label1);
					content.add(textPanel);
					content.add(returnPanel);
					
					returnButton.addActionListener(new ActionListener(  ) {
						public void actionPerformed(ActionEvent ae) {
							f.dispose();			
						customer(customerOBJ);				
						}		
				     });										
				}	
				
				
		     });
			
			lodgementButton.addActionListener(new ActionListener(  ) {
				public void actionPerformed(ActionEvent ae) {
				boolean loop = true;
				double balance = 0;
				boolean loginSuccess=false;
	
				if(acc instanceof CustomerCurrentAccount)
				{
					loginSuccess = AccountTransaction.CheckATMLogin(customerOBJ, f, acc);
					
					
				}	
						
				
				//IF ATM LOGIN PASS THEN
				if(loginSuccess == true)
						{
					String balanceTest = JOptionPane.showInputDialog(f, "Enter amount you wish to lodge:");//the AccountTransaction.isNumeric method tests to see if the string entered was numeric. 
					if(AccountTransaction.isNumeric(balanceTest))
					{
						
						balance = Double.parseDouble(balanceTest);
						loop = false;
						
						
					}
					else
					{
						customer(customerOBJ);
						JOptionPane.showMessageDialog(f, "You must enter a numerical value!" ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
					}
					
				
				
				 acc.setBalance(acc.getBalance() + balance);
					double amount = balance;
					
					
					
					
					AccountTransaction transaction = new AccountTransaction((new Date()).toString(), "Lodgement", amount);
					acc.getTransactionList().add(transaction);
					
				 JOptionPane.showMessageDialog(f, balance + euro + " added do you account!" ,"Lodgement",  JOptionPane.INFORMATION_MESSAGE);
				 JOptionPane.showMessageDialog(f, "New balance = " + acc.getBalance() + euro ,"Lodgement",  JOptionPane.INFORMATION_MESSAGE);
				}
	
				}	
		     });
			
			withdrawButton.addActionListener(new ActionListener(  ) {
				public void actionPerformed(ActionEvent ae) {
					boolean loop = true;
					boolean on = true;
					double withdraw = 0;
					boolean ATMCorrectLogin = false;
	
					if(acc instanceof CustomerCurrentAccount)
					{
						ATMCorrectLogin = AccountTransaction.CheckATMLogin(customerOBJ, f, acc);
					}	
			
					    	
					    	
					    
						
						
					if(ATMCorrectLogin == true)
							{
						String balanceTest = JOptionPane.showInputDialog(f, "Enter amount you wish to withdraw (max 500):");//the AccountTransaction.isNumeric method tests to see if the string entered was numeric. 
						if(AccountTransaction.isNumeric(balanceTest))
						{
							
							withdraw = Double.parseDouble(balanceTest);
							loop = false;				
							
						}
						else
						{
							customer(customerOBJ);
							JOptionPane.showMessageDialog(f, "You must enter a numerical value!" ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
						}
						if(withdraw > 500)
						{
							JOptionPane.showMessageDialog(f, "500 is the maximum you can withdraw at a time." ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
							withdraw = 0;
						}
						if(withdraw > acc.getBalance())
						{
							JOptionPane.showMessageDialog(f, "Insufficient funds." ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
							withdraw = 0;					
						}
					
					
					 acc.setBalance(acc.getBalance()-withdraw);
			
						AccountTransaction transaction = new AccountTransaction((new Date()).toString(), "Withdraw", withdraw);
						acc.getTransactionList().add(transaction);
					 
					 
						
					 JOptionPane.showMessageDialog(f, withdraw + euro + " withdrawn." ,"Withdraw",  JOptionPane.INFORMATION_MESSAGE);
					 JOptionPane.showMessageDialog(f, "New balance = " + acc.getBalance() + euro ,"Withdraw",  JOptionPane.INFORMATION_MESSAGE);
					}
					 
						
						
				}	
		     });
			
			returnButton.addActionListener(new ActionListener(  ) {
				public void actionPerformed(ActionEvent ae) {
					returnToMenu();			
				}
		     });		}		
		     });
		}
	}

}

