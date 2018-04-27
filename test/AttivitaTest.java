import static org.junit.Assert.*;

import org.junit.Test;

public class AttivitaTest 
{


	@Test
	public void testCostruttore() 
	{
		Attivita a1=new Attivita("Attività 1",new Date(12,6,2018));
		Date dataDefault=new Date(12,6,2018);
		assertEquals("Descrizione ", "Attività 1",a1.getDescrizione());
		assertEquals("Scadenza ",dataDefault,a1.getScadenza());
		assertEquals("Percentuale Svolgimento ", 0,a1.getSvolgimento());
		assertNull("Completamento ",a1.getCompletamento());
	}
	
	@Test
	public void testCostruttoreCopia() 
	{
		Attivita a1=new Attivita("Attività 1",new Date(12,6,2018));	
		assertEquals("Costruttore copia ", a1, new Attivita (a1));	
	}
	
	@Test
	public void testGetter() 
	{
		
		Date d1= new Date (12,6,2018);
		Date d2= new Date (2,5,2018);
		Attivita a1=new Attivita("Attività 1",d1);	
		a1.setSvolgimento(50, d2);
		assertEquals("Descrizione: ","Attività 1", a1.getDescrizione());
		assertEquals("Data scadenza: ",new Date(12,6,2018), a1.getScadenza());
		assertEquals("Svolgimento: ",50, a1.getSvolgimento());
		assertNull("Completamento", a1.getCompletamento());
	}
	
	@Test
	public void testSetter() 
	{
		
		Date d1= new Date (12,6,2018);
		Date d2= new Date (2,5,2018);
		Date d3= new Date (10,7,2018);
		
		Attivita a1=new Attivita("Attività 1",d1);	
		a1.setScadenza(d2);
		a1.setSvolgimento(50, d3);
		assertEquals("Descrizione: ","Attività 1", a1.getDescrizione());
		assertEquals("Data scadenza: ",new Date(2,5,2018), a1.getScadenza());
		assertEquals("Svolgimento: ",50, a1.getSvolgimento());
		assertNull("Completamento", a1.getCompletamento());
	}
	
	@Test
	public void testSvolgimentoNegativo() 
	{
		
		Date d1= new Date (12,6,2018);
		Date d2= new Date (2,5,2018);
		
		Attivita a1=new Attivita("Attività 1",d1);	
		a1.setSvolgimento(-10, d2);
		assertEquals("Descrizione: ","Attività 1", a1.getDescrizione());
		assertEquals("Data scadenza: ",new Date(12,6,2018), a1.getScadenza());
		assertEquals("Svolgimento: ",0, a1.getSvolgimento());
		assertNull("Completamento", a1.getCompletamento());
	}
	
	@Test
	public void testSvolgimentoCompleto() 
	{
		
		Date d1= new Date (12,6,2018);
		Date d2= new Date (2,5,2018);
		
		Attivita a1=new Attivita("Attività 1",d1);	
		a1.setSvolgimento(100, d2);
		assertEquals("Descrizione: ","Attività 1", a1.getDescrizione());
		assertEquals("Data scadenza: ",new Date(12,6,2018), a1.getScadenza());
		assertEquals("Svolgimento: ",100, a1.getSvolgimento());
		assertEquals("Completamento: ",new Date(2,5,2018), a1.getCompletamento());
	}
	
	@Test
	public void testSvolgimentoMaggioreDi100() 
	{
		
		Date d1= new Date (12,6,2018);
		Date d2= new Date (2,5,2018);
		
		Attivita a1=new Attivita("Attività 1",d1);	
		a1.setSvolgimento(120, d2);
		assertEquals("Descrizione: ","Attività 1", a1.getDescrizione());
		assertEquals("Data scadenza: ",new Date(12,6,2018), a1.getScadenza());
		assertEquals("Svolgimento: ",0, a1.getSvolgimento());
		assertNull("Completamento", a1.getCompletamento());
	}
	
	@Test
	public void testSvolgimentoDecrementato() 
	{
		
		Date d1= new Date (12,6,2018);
		Date d2= new Date (2,5,2018);
		
		Attivita a1=new Attivita("Attività 1",d1);	
		a1.setSvolgimento(80, d2);
		a1.setSvolgimento(0, d2);
		assertEquals("Descrizione: ","Attività 1", a1.getDescrizione());
		assertEquals("Data scadenza: ",new Date(12,6,2018), a1.getScadenza());
		assertEquals("Svolgimento: ",0, a1.getSvolgimento());
		assertNull("Completamento", a1.getCompletamento());
	}
	
	@Test
	public void tesToStringAttivitaNonCompletata() 
	{
		
		Date d1= new Date (12,6,2018);
		Date d2= new Date (2,5,2018);
		
		Attivita a1=new Attivita("Attività 1",d1);	
		a1.setSvolgimento(50, d2);
		String s="Attività 1\nScadenza:12\\6\\2018\nPercentuale Svolgimento: 50 %\nCompletamento :...";
		assertEquals("Stringa attività ",s, a1.toString());
		
	}
	
	@Test
	public void tesToStringAttivitaCompletata() 
	{
		
		Date d1= new Date (12,6,2018);
		Date d2= new Date (2,5,2018);
		
		Attivita a1=new Attivita("Attività 1",d1);	
		a1.setSvolgimento(100, d2);
		String s="Attività 1\nScadenza:12\\6\\2018\nPercentuale Svolgimento: 100 %\nCompletamento :2\\5\\2018";
		assertEquals("Stringa attività ",s, a1.toString());	
	}
	
	@Test
	public void tesEqualsAttivitaNonCompletata() 
	{
		
		Date d1= new Date (12,6,2018);
		
		Attivita a1=new Attivita("Attività 1",d1);	
		Attivita a2=new Attivita("Attività 1",d1);
		
		assertEquals("Uguaglianza attività non completate ",a1,a2);	
	}
	
	
	@Test
	public void tesEqualsAttivitaCompletata() 
	{
		
		Date d1= new Date (12,6,2018);
		Date d2= new Date (2,5,2018);
		
		Attivita a1=new Attivita("Attività 1",d1);	
		Attivita a2=new Attivita("Attività 1",d1);
		a1.setSvolgimento(100, d2);
		a2.setSvolgimento(100, d2);
		assertEquals("Uguaglianza attività non completate ",a1,a2);	
	}
	
	@Test
	public void tesNotEqualsAttivitaCompletataENonCompletata() 
	{
		
		Date d1= new Date (12,6,2018);
		Date d2= new Date (2,5,2018);
		
		Attivita a1=new Attivita("Attività 1",d1);	
		Attivita a2=new Attivita("Attività 1",d1);
		a1.setSvolgimento(100, d2);
		assertFalse("Uguaglianza attività non completate ",a1.equals(a2));	
	}
	
	
}
