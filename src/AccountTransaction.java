import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AccountTransaction {

	String date;
	String type;
	double amount;
	
	//Blank constructor
	public AccountTransaction()
	{
		this.date = "";
		this.type = "";	
		this.amount = 0;
		
	}
	
	//Constructor with details
	public AccountTransaction(String date, String type, double amount)
	{
		this.date = date;
		this.type = type;
		
		
		this.amount = amount;
		
	}
	
	//Accessor methods
	public String getDate()
	{
		return this.date;
	}
	
	public String getType()
	{
		return this.type;
	}

	public double getAmount()
	{
		return this.amount;
	}

	//mutator methods
	public void setDate(String date)
	{
		this.date = date;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public void setAmount(double amount)
	{
		this.amount = amount;
	}
	
	public String toString()
	{
		return  "\n" + "Date = " + this.date + "\n"
				+ "Type = " + this.type + "\n"
				+ "Amount = " + this.amount + "\n";
			
	}
	
	public static boolean isNumeric(String str)  // a method that tests if a string is numeric
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}


	public static boolean CheckATMLogin(Customer customerOBJ, JFrame f, CustomerAccount acc) {
		boolean loop = true;
		boolean on = true;
		double balance = 0;

		if(acc instanceof CustomerCurrentAccount)
		{
			int count = 3;
			int checkPin = ((CustomerCurrentAccount) acc).getAtm().getPin();
			//loop = true;
			
			while(loop)
			{
				if(count == 0)
				{
					JOptionPane.showMessageDialog(f, "Pin entered incorrectly 3 times. ATM card locked."  ,"Pin",  JOptionPane.INFORMATION_MESSAGE);
					((CustomerCurrentAccount) acc).getAtm().setValid(false);
//					customer(c); 
					
					on = false;
					return false;
				}
				
				String Pin = JOptionPane.showInputDialog(f, "Enter 4 digit PIN;");
				int i = Integer.parseInt(Pin);
				
			   if(on)
			   {
				if(checkPin == i)
				{
					
					JOptionPane.showMessageDialog(f, "Pin entry successful" ,  "Pin", JOptionPane.INFORMATION_MESSAGE);
					return true;
				}
				else
				{
					count --;
					JOptionPane.showMessageDialog(f, "Incorrect pin. " + count + " attempts remaining."  ,"Pin",  JOptionPane.INFORMATION_MESSAGE);					
				}
			   }
			}
		}
				return false;
	}

	
}
