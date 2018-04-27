import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class ToDoInterfacciaTest 
{

	//------------test da eseguire una vlta poi commentare altrimenti provoca il fallimento del test case
/*	@Test
	public void testElencaProgetti() 
	{
		String[] elencoProgetti= {};
		
		assertArrayEquals(elencoProgetti,ToDoInterfaccia.elencaProgetti());
	}
*/	
	@Test
	public void testCreaProgetto() 
	{
		Progetto p1= new Progetto("p1");		
		assertEquals(p1,ToDoInterfaccia.creaProgetto("p1"));
	}
	

	@Test
	public void testSalvaProgetto() throws ClassNotFoundException, IOException 
	{
		final String workingDir = System.getProperty("user.dir")+"\\elencoProgetti\\"; //directory del progetto corrente
		Progetto p1=ToDoInterfaccia.creaProgetto("p1");
		
		ToDoInterfaccia.salvaProgetto(p1);
		File filesPresenti=new File(workingDir);
		assertEquals("p1.bin",filesPresenti.list()[0]);
	}

	@Test
	public void testCaricaProgetto() throws ClassNotFoundException, IOException 
	{
		Progetto p1= new Progetto("p1");
		assertEquals(p1,ToDoInterfaccia.caricaProgetto("p1"));
	}
	
	//-----------------test che fallisce--------------
	@Test (expected=progettoNonPresente.class)
	public void testEccezioneCaricaProgettoNonPresente() throws ClassNotFoundException, IOException 
	{
		Progetto p= ToDoInterfaccia.caricaProgetto("p");
	}
	

	
	@Test
	public void testElencaProgetti() throws ClassNotFoundException, IOException 
	{
		Progetto p3= new Progetto("p3");
		ToDoInterfaccia.salvaProgetto(p3);
		String[] elencoProgetti= {"p1","p3"};
		
		assertArrayEquals(elencoProgetti,ToDoInterfaccia.elencaProgetti());

	}
	
	
	@Test
	public void testEliminaProgetto() throws ClassNotFoundException, IOException, progettoNonPresente 
	{
		ToDoInterfaccia.eliminaProgetto("p1");
		String[] elencoProgetti= {"p3"};
		assertArrayEquals(elencoProgetti,ToDoInterfaccia.elencaProgetti());

	}
	

	@Test (expected=progettoNonPresente.class)
	public void testEccezioneEliminaProgettoNonPresente() throws ClassNotFoundException, IOException, progettoNonPresente 
	{
		ToDoInterfaccia.eliminaProgetto("p5");
	}
	

}
