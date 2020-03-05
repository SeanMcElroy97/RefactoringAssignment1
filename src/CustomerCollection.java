import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CustomerCollection {
	
	private  ArrayList<Customer> customerList;
	
	

public CustomerCollection() {
		super();
		customerList = new ArrayList<Customer>();
	}



	public Customer findCustomerBYID(String id) {
		   for (Customer aCustomer: customerList){
		    	
		    	if(aCustomer.getCustomerID().equals(id))
		    	{
		    		return aCustomer;
		    	}					    	
		    }
		   return null;
	}
	
	public boolean customerListIsEmpty(JFrame f){
		if(customerList.isEmpty()) {
		JOptionPane.showMessageDialog(f, "There are no customers yet!"  ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
		f.dispose();
		return true;
		}
		return false;
	}

	

	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}



	public void setCustomerList(ArrayList<Customer> customerList) {
		this.customerList = customerList;
	}
	
	
	public void navigateListGoToFirst(int position, JTextField firstNameTextField, JTextField surnameTextField, JTextField pPSTextField, JTextField dOBTextField, JTextField customerIDTextField, JTextField passwordTextField) {
		position = 0;
		firstNameTextField.setText(customerList.get(0).getFirstName());
		surnameTextField.setText(customerList.get(0).getSurname());
		pPSTextField.setText(customerList.get(0).getPPS());
		dOBTextField.setText(customerList.get(0).getDOB());
		customerIDTextField.setText(customerList.get(0).getCustomerID());
		passwordTextField.setText(customerList.get(0).getPassword());
	}
	
	public void navigateListGoToPrevious(int position, JTextField firstNameTextField, JTextField surnameTextField, JTextField pPSTextField, JTextField dOBTextField, JTextField customerIDTextField, JTextField passwordTextField) {
		if(position < 1)
		{
			//don't do anything
		}
		else
		{
			position = position - 1;
			
		firstNameTextField.setText(customerList.get(position).getFirstName());
		surnameTextField.setText(customerList.get(position).getSurname());
		pPSTextField.setText(customerList.get(position).getPPS());
		dOBTextField.setText(customerList.get(position).getDOB());
		customerIDTextField.setText(customerList.get(position).getCustomerID());
		passwordTextField.setText(customerList.get(position).getPassword());
		}
	}

	
	public void navigateListToNext(int position, JTextField firstNameTextField, JTextField surnameTextField, JTextField pPSTextField, JTextField dOBTextField, JTextField customerIDTextField, JTextField passwordTextField) {
		if(position == customerList.size()-1)
		{
			//don't do anything
		}
		else
		{
			position = position + 1;
			
		firstNameTextField.setText(customerList.get(position).getFirstName());
		surnameTextField.setText(customerList.get(position).getSurname());
		pPSTextField.setText(customerList.get(position).getPPS());
		dOBTextField.setText(customerList.get(position).getDOB());
		customerIDTextField.setText(customerList.get(position).getCustomerID());
		passwordTextField.setText(customerList.get(position).getPassword());
		}	
	}
	
	public void navigateListToLast(int position, JTextField firstNameTextField, JTextField surnameTextField, JTextField pPSTextField, JTextField dOBTextField, JTextField customerIDTextField, JTextField passwordTextField) {
		position = customerList.size() - 1;
		
		firstNameTextField.setText(customerList.get(position).getFirstName());
		surnameTextField.setText(customerList.get(position).getSurname());
		pPSTextField.setText(customerList.get(position).getPPS());
		dOBTextField.setText(customerList.get(position).getDOB());
		customerIDTextField.setText(customerList.get(position).getCustomerID());
		passwordTextField.setText(customerList.get(position).getPassword());
	}
	
}
