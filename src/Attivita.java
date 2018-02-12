import java.io.Serializable;

public class Attivita implements Serializable
{
	
	//Attributi
	private String descrizione;
	private Date scadenza;
	private int svolgimento; //percentuale di svolgimento
	private Date completamento;
	
	
	
	public Attivita (String descrizione, Date scadenza)
	{
		this.descrizione=descrizione;
		this.scadenza=scadenza;
		svolgimento=0;
	}
	
	public Attivita (Attivita attivita)
	{
		descrizione=attivita.getDescrizione();
		scadenza=attivita.getScadenza();
		svolgimento=attivita.getSvolgimento();
		completamento=attivita.getCompletamento();
	}
	

	public String getDescrizione()
	{
		return descrizione;
	}
	
	public Date getScadenza()
	{
		return scadenza;
	}
	
	public int getSvolgimento()
	{
		return svolgimento;
	}
	
	public void setScadenza(Date dataScadenza)
	{
		scadenza=dataScadenza;
	}
		
	/**
	 * Overloading
	 * Consente di impostare la percentuale di svolgimento di una attività che viene completata
	 * Se la percentuale è negativa oppure maggiore di 100 il valore 
	 * dell'attributo non viene modificato
	 * Se la percentuale è compreso fra 0 e 100 viene impostato l'attributo svolgimento
	 * Se la percentuale è uguale a 100 la data viene assegnata al parametro completamento in quanto 
	 * l'attività è da considerarsi completata.
	 * @param percentuale percentuale di svolgimento
	 * @param dataCompletamento data di completamento dell'attività
	 */
	public void setSvolgimento(int percentuale, Date dataCompletamento)
	{
		
		if (percentuale>=0 && percentuale <=100)
			svolgimento=percentuale;
		if (percentuale==100)
			completamento=dataCompletamento;
	}
	
	public Date getCompletamento()
	{
		return completamento;
	}
	
	public String toString()
	{
		String risultato;
		risultato= (descrizione + "\nScadenza:"+ getScadenza().toString()+ "\nPercentuale Svolgimento: "+getSvolgimento()+" %"+ "\nCompletamento :");
		if (getCompletamento()!=null)
			risultato+=getCompletamento().toString();
		else
			risultato+="...";
		return risultato;
			
	}
	
	public static void main(String[] args) 
	{
		// debug
		Date data1=new Date(20,3,2018);
		Attivita a1=new Attivita("Analisi iniziale dei requisiti ",data1);
		System.out.println(a1.toString());
		a1.setSvolgimento(50,new Date(10,2,2018));
		System.out.println(a1.toString());
		a1.setSvolgimento(100, new Date(15,2,2018));
		System.out.println(a1.toString());
	}

}
