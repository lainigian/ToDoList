
public class Attivita
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
	 * Consente di impostare la percentuale di svolgimento di una attività.
	 * Se la percentuale è negativa oppure maggiore o uguale a 100 il valore 
	 * dell'attributo non viene modificato
	 * @param percentuale percentuale di svolgimento
	 */
	public void setSvolgimento(int percentuale)
	{
		if (percentuale>=0 && percentuale <100)
			svolgimento=percentuale;	
	}
	
	/**
	 * Overloading
	 * Consente di impostare la percentuale di svolgimento di una attività che viene completata
	 * Se la percentuale è negativa oppure maggiore di 100 il valore 
	 * dell'attributo non viene modificato
	 * Se la percentuale è < di 100 viene impostato l'attributo svolgimento
	 * Se la percentuale è uguale a 100 viene impostato il valore percentuale e la data viene 
	 * assegnata al parametro completamento in quanto l'attività è da considerarsi completata
	 * @param percentuale percentuale di svolgimento
	 * @param dataCompletamento data di completamento dell'attività
	 */
	public void setSvolgimento(int percentuale, Date dataCompletamento)
	{
		if (percentuale==100)
		{
			svolgimento=percentuale;	
			completamento=dataCompletamento;
		}
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
	}
	
	

}
