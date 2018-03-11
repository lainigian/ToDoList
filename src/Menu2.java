import java.util.Scanner;

public class Menu2 
{

	private String[] elencoVoci;
	int numeroVoci=0;

	public Menu2(String[] elencoVoci)
	{
		numeroVoci=elencoVoci.length;
		this.elencoVoci=new String[numeroVoci];
		
		for (int i = 0; i < numeroVoci; i++) 
			this.elencoVoci[i]=elencoVoci[i];
	}
	
	private void visualizzaMenu()
	{
		for (int i = 0; i < elencoVoci.length; i++) 
			System.out.println(elencoVoci[i]);
	}
	
	
	public int sceltaMenu()
	{
		Scanner tastiera=new Scanner(System.in);
		boolean inputNumerico=true;
		int scelta=-1;

		
		do
		{
			inputNumerico=true;
			visualizzaMenu();
			System.out.println("Scegli:");
			String stringaScelta=tastiera.nextLine();
			//controllo se i caratteri sono tutti numeri
			for (int i = 0; i < stringaScelta.length(); i++) 
			{
				if (!(stringaScelta.charAt(i)>=48 && stringaScelta.charAt(i)<=57))
					inputNumerico=false;
			}
			if (inputNumerico && stringaScelta.length()>0)
				scelta=Integer.parseInt(stringaScelta);
			else
				System.out.println("Input non numerico. Ripetere la scelta");
			if(scelta<0 || scelta>=numeroVoci)
				System.out.println("Input non valido");
			
		}while(scelta<0 || scelta>=numeroVoci);		
		return scelta;
		
	}
	

	
	
}
