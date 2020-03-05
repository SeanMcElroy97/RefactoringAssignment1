import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
	
	
	
	
}
