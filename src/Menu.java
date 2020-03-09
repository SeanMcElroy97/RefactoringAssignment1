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
	String password, PPS,firstName,surname,DOB,CustomerID;
	private Customer customer = null;
	private CustomerAccount acc = new CustomerAccount();
	JFrame f, f1;
	JLabel firstNameLabel, surnameLabel, pPPSLabel, dOBLabel, customerIDLabel, passwordLabel;
	JTextField firstNameTextField, surnameTextField, pPSTextField, dOBTextField, customerIDTextField, passwordTextField;
	Container content;
	Customer customer1;
		
	String euro = "\u20ac";
	
	JPanel panel2;
	JButton add;		
		
	 CustomerCollection customerCollection = CustomerCollection.getInstance();
	
	
	
	
	


/////////////////Method that starts menu again//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
	public void returnToMenu() {
			menuStart(f);				
	     }


	
////////////Method that takes a JFrame object. Then sets ui for it in the GUI window////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	public void setFrameUI(JFrame fr) {
			fr.setSize(400, 300);
			fr.setLocation(200, 200);
			fr.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) { System.exit(0); }
			});
		}


	
	
	
	
//////////////////////////////main method (where program starts)/////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
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
			if(oldFrame instanceof JFrame) {
			oldFrame.dispose();
			}
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
						f.dispose();
						userStrategy = new NewCustomerStrategy();
						userStrategy.userMenuStart();
						}
					
					
					//------------------------------------------------------------------------------------------------------------------
					
					//if user select ADMIN----------------------------------------------------------------------------------------------
					if(user.equals("Administrator")	)
					{
						userStrategy = new StrategyAdmin();
						userStrategy.userMenuStart();
					}
					//----------------------------------------------------------------------------------------------------------------
					
					
					
					//if user selects CUSTOMER ---------------------------------------------------------------------------------------- 
					if(user.equals("Customer")	)
					{
						userStrategy = new ExistingCustomerStrategy();
						userStrategy.userMenuStart();
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
		
		JPanel deleteCustomerAccountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton deleteCustomerAccountBTN = new JButton("Delete Account");
		deleteCustomerAccountBTN.setPreferredSize(new Dimension(250, 20));	
		deleteCustomerAccountPanel.add(deleteCustomerAccountBTN);
		
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
				((StrategyAdmin) userStrategy).navigateCustomerCollection(position);
				}
		});
		
		accountButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				((StrategyAdmin) userStrategy).addAccountToCustomer(customer); 
			}
	     });		

		deleteCustomer.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				((StrategyAdmin) userStrategy).removeACustomer(customer);
			}
	     });		
		
		deleteCustomerAccountBTN.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				((StrategyAdmin) userStrategy).removeACustomerAccount();
			}
			
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
					((ExistingCustomerStrategy) userStrategy).makeLodgement(acc, customerOBJ);
					}	
		     });
			
			withdrawButton.addActionListener(new ActionListener(  ) {
				public void actionPerformed(ActionEvent ae) {
					((ExistingCustomerStrategy) userStrategy).withdrawMoney(acc, customerOBJ);
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

