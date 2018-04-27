import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

import fileTxt.FileException;
import fileTxt.TextFile;

public class ProgettoTest 
{

	@Test
	public void testCostruttore()
	{
		Progetto p1=new Progetto("progetto 1");
		Attivita[] elencoAttivita= {};
		
		assertTrue(p1.getDenominazione().compareTo("progetto 1")==0);	
		assertArrayEquals(elencoAttivita, p1.elencaAttivita());
	}
	
	@Test
	public void testCostruttoreCopia() throws MaxNumeroAttivitaRaggiunto
	{
		Progetto p1=new Progetto("progetto 1");
		p1.creaAttivita("Attivit‡ 1", new Date(18,6,2018));
		p1.creaAttivita("Attivit‡ 2", new Date(10,7,2018));
		
		assertEquals(p1, new Progetto(p1));
	}

	@Test
	public void testCreaAttivita() throws MaxNumeroAttivitaRaggiunto
	{
		Progetto p1=new Progetto("progetto 1");
		p1.creaAttivita("Attivit‡ 1", new Date(18,6,2018));
		p1.creaAttivita("Attivit‡ 2", new Date(10,7,2018));
		Attivita a1=new Attivita ("Attivit‡ 1", new Date(18,6,2018));
		
		Attivita a2=new Attivita ("Attivit‡ 2", new Date(10,7,2018));
		Attivita[] elencoAttivita= {a1,a2};
		assertArrayEquals(p1.elencaAttivita(), elencoAttivita);
	}
	

	@Test (expected=MaxNumeroAttivitaRaggiunto.class)
	public void testEccezione() throws MaxNumeroAttivitaRaggiunto
	{
		Progetto p1=new Progetto("progetto 1");
		p1.creaAttivita("Attivit‡ 1", new Date(18,6,2018));
		p1.creaAttivita("Attivit‡ 2", new Date(10,7,2018));
		p1.creaAttivita("Attivit‡ 1", new Date(18,6,2018));
		p1.creaAttivita("Attivit‡ 2", new Date(10,7,2018));
		p1.creaAttivita("Attivit‡ 1", new Date(18,6,2018));
		p1.creaAttivita("Attivit‡ 2", new Date(10,7,2018));
		p1.creaAttivita("Attivit‡ 1", new Date(18,6,2018));
		p1.creaAttivita("Attivit‡ 2", new Date(10,7,2018));
		p1.creaAttivita("Attivit‡ 1", new Date(18,6,2018));
		p1.creaAttivita("Attivit‡ 2", new Date(10,7,2018));
		p1.creaAttivita("Attivit‡ 1", new Date(18,6,2018));
		p1.creaAttivita("Attivit‡ 2", new Date(10,7,2018));
		p1.creaAttivita("Attivit‡ 1", new Date(18,6,2018));
		p1.creaAttivita("Attivit‡ 2", new Date(10,7,2018));
		p1.creaAttivita("Attivit‡ 1", new Date(18,6,2018));
		p1.creaAttivita("Attivit‡ 2", new Date(10,7,2018));
		p1.creaAttivita("Attivit‡ 1", new Date(18,6,2018));
		p1.creaAttivita("Attivit‡ 2", new Date(10,7,2018));
		p1.creaAttivita("Attivit‡ 1", new Date(18,6,2018));
		p1.creaAttivita("Attivit‡ 2", new Date(10,7,2018));
		p1.creaAttivita("Attivit‡ 1", new Date(18,6,2018));
		p1.creaAttivita("Attivit‡ 2", new Date(10,7,2018));
		
	}	
	
	@Test 
	public void testEliminaAttivitaInTesta() throws MaxNumeroAttivitaRaggiunto, AttivitaNonPresente
	{
		Progetto p1=new Progetto("progetto 1");
		p1.creaAttivita("Attivit‡ 1", new Date(18,6,2018));
		p1.creaAttivita("Attivit‡ 2", new Date(10,7,2018));
		p1.creaAttivita("Attivit‡ 3", new Date(18,6,2018));
		p1.creaAttivita("Attivit‡ 4", new Date(10,7,2018));
		p1.creaAttivita("Attivit‡ 5", new Date(10,7,2018));
		
		Attivita a1=new Attivita ("Attivit‡ 1", new Date(18,6,2018));
		Attivita a2=new Attivita ("Attivit‡ 2", new Date(10,7,2018));
		Attivita a3=new Attivita ("Attivit‡ 3", new Date(18,6,2018));
		Attivita a4=new Attivita ("Attivit‡ 4", new Date(10,7,2018));
		Attivita a5=new Attivita ("Attivit‡ 5", new Date(10,7,2018));
		
		p1.eliminaAttivita("Attivit‡ 1");
		
		Attivita[] elencoAttivita= {a2,a3,a4,a5};
		assertArrayEquals(elencoAttivita, p1.elencaAttivita());
		
	}	
	
	
	@Test 
	public void testEliminaAttivita() throws MaxNumeroAttivitaRaggiunto, AttivitaNonPresente
	{
		Progetto p1=new Progetto("progetto 1");
		p1.creaAttivita("Attivit‡ 1", new Date(18,6,2018));
		p1.creaAttivita("Attivit‡ 2", new Date(10,7,2018));
		p1.creaAttivita("Attivit‡ 3", new Date(18,6,2018));
		p1.creaAttivita("Attivit‡ 4", new Date(10,7,2018));
		p1.creaAttivita("Attivit‡ 5", new Date(10,7,2018));
		
		Attivita a1=new Attivita ("Attivit‡ 1", new Date(18,6,2018));
		Attivita a2=new Attivita ("Attivit‡ 2", new Date(10,7,2018));
		Attivita a3=new Attivita ("Attivit‡ 3", new Date(18,6,2018));
		Attivita a4=new Attivita ("Attivit‡ 4", new Date(10,7,2018));
		Attivita a5=new Attivita ("Attivit‡ 5", new Date(10,7,2018));
		
		p1.eliminaAttivita("Attivit‡ 3");
		
		Attivita[] elencoAttivita= {a1,a2,a4,a5};
		assertArrayEquals(elencoAttivita, p1.elencaAttivita());
		
	}	
	

	@Test 
	public void testEliminaAttivitaInCoda() throws MaxNumeroAttivitaRaggiunto, AttivitaNonPresente
	{
		Progetto p1=new Progetto("progetto 1");
		p1.creaAttivita("Attivit‡ 1", new Date(18,6,2018));
		p1.creaAttivita("Attivit‡ 2", new Date(10,7,2018));
		p1.creaAttivita("Attivit‡ 3", new Date(18,6,2018));
		p1.creaAttivita("Attivit‡ 4", new Date(10,7,2018));
		p1.creaAttivita("Attivit‡ 5", new Date(10,7,2018));
		
		Attivita a1=new Attivita ("Attivit‡ 1", new Date(18,6,2018));
		Attivita a2=new Attivita ("Attivit‡ 2", new Date(10,7,2018));
		Attivita a3=new Attivita ("Attivit‡ 3", new Date(18,6,2018));
		Attivita a4=new Attivita ("Attivit‡ 4", new Date(10,7,2018));
		Attivita a5=new Attivita ("Attivit‡ 5", new Date(10,7,2018));
		
		p1.eliminaAttivita("Attivit‡ 5");
		
		Attivita[] elencoAttivita= {a1,a2,a3,a4};
		assertArrayEquals(elencoAttivita, p1.elencaAttivita());
		
	}
	
	
	@Test (expected=AttivitaNonPresente.class)
	public void testEliminaAttivitaInesistente() throws MaxNumeroAttivitaRaggiunto, AttivitaNonPresente
	{
		Progetto p1=new Progetto("progetto 1");
		p1.creaAttivita("Attivit‡ 1", new Date(18,6,2018));
		p1.creaAttivita("Attivit‡ 2", new Date(10,7,2018));
		
		p1.eliminaAttivita("Prova");
		
	}
	
	@Test 
	public void testAggiungiAttivita() throws MaxNumeroAttivitaRaggiunto, AttivitaNonPresente
	{
		Progetto p1=new Progetto("progetto 1");
		p1.creaAttivita("Attivit‡ 1", new Date(18,6,2018));
		p1.creaAttivita("Attivit‡ 2", new Date(10,7,2018));
		p1.creaAttivita("Attivit‡ 3", new Date(18,6,2018));
		p1.creaAttivita("Attivit‡ 4", new Date(10,7,2018));
		p1.creaAttivita("Attivit‡ 5", new Date(10,7,2018));
		
		Attivita a1=new Attivita ("Attivit‡ 1", new Date(18,6,2018));
		Attivita a2=new Attivita ("Attivit‡ 2", new Date(10,7,2018));
		Attivita a3=new Attivita ("Attivit‡ 3", new Date(18,6,2018));
		Attivita a4=new Attivita ("Attivit‡ 4", new Date(10,7,2018));
		Attivita a5=new Attivita ("Attivit‡ 5", new Date(10,7,2018));
		
		Attivita a6=new Attivita ("Attivit‡ 6", new Date(12,6,2018));
		
		p1.eliminaAttivita("Attivit‡ 3");
		p1.creaAttivita("Attivit‡ 6", new Date(12,6,2018));
		Attivita[] elencoAttivita= {a1,a2,a6,a4,a5};
		assertArrayEquals(elencoAttivita, p1.elencaAttivita());
		
	}	
	
	@Test 
	public void testAggiornaScadenza() throws MaxNumeroAttivitaRaggiunto, AttivitaNonPresente
	{
		Progetto p1=new Progetto("progetto 1");
		p1.creaAttivita("Attivit‡ 1", new Date(12,6,2018));
		
		Date d1= new Date(10,7,2018);
		
		p1.aggiornaAttivita("Attivit‡ 1", d1);
		
		assertEquals(d1, p1.elencaAttivita()[0].getScadenza());
	}	
	
	@Test (expected=AttivitaNonPresente.class)
	public void testAggiornaAttivitaNonPresente() throws MaxNumeroAttivitaRaggiunto, AttivitaNonPresente
	{
		Progetto p1=new Progetto("progetto 1");
		p1.creaAttivita("Attivit‡ 1", new Date(12,6,2018));
		
		Date d1= new Date(10,7,2018);
		
		p1.aggiornaAttivita("Prova", d1);	
	}
	
	@Test 
	public void testAggiornaPercentualeSvolgimento() throws MaxNumeroAttivitaRaggiunto, AttivitaNonPresente
	{
		Progetto p1=new Progetto("progetto 1");
		p1.creaAttivita("Attivit‡ 1", new Date(12,6,2018));
		
		p1.aggiornaAttivita("Attivit‡ 1", 50);
		
		assertEquals(50, p1.elencaAttivita()[0].getSvolgimento());
		assertNull(p1.elencaAttivita()[0].getCompletamento());
	}
	
	@Test 
	public void testCompletaAttivita() throws MaxNumeroAttivitaRaggiunto, AttivitaNonPresente
	{
		Progetto p1=new Progetto("progetto 1");
		p1.creaAttivita("Attivit‡ 1", new Date(12,6,2018));
		
		
		p1.aggiornaAttivita("Attivit‡ 1", 100);
		
		GregorianCalendar gc=new GregorianCalendar();
		Date dataOdierna=new Date(gc.get(Calendar.DAY_OF_MONTH),(int)gc.get(Calendar.MONTH)+1,gc.get(Calendar.YEAR));
		assertEquals(100, p1.elencaAttivita()[0].getSvolgimento());
		assertEquals(dataOdierna,p1.elencaAttivita()[0].getCompletamento());
	}
	
	@Test (expected=AttivitaNonPresente.class)
	public void testCompletaAttivitaNonPresente() throws MaxNumeroAttivitaRaggiunto, AttivitaNonPresente
	{
		Progetto p1=new Progetto("progetto 1");
		p1.creaAttivita("Attivit‡ 1", new Date(12,6,2018));
		p1.aggiornaAttivita("Prova", 100);

	}
	
	@Test 
	public void testElencaAttivit‡InScadenza() throws MaxNumeroAttivitaRaggiunto, AttivitaNonPresente
	{
		Progetto p1=new Progetto("progetto 1");
		p1.creaAttivita("Attivit‡ 1", new Date(12,6,2018));
		p1.creaAttivita("Attivit‡ 2", new Date(12,7,2018));
		p1.creaAttivita("Attivit‡ 3", new Date(12,8,2018));
		p1.creaAttivita("Attivit‡ 4", new Date(12,10,2018));
		p1.creaAttivita("Attivit‡ 5", new Date(12,11,2018));
		
		Attivita a1=new Attivita ("Attivit‡ 1", new Date(12,6,2018));
		Attivita a2=new Attivita ("Attivit‡ 2", new Date(12,7,2018));
		Attivita a3=new Attivita ("Attivit‡ 3", new Date(12,8,2018));
		Attivita a4=new Attivita ("Attivit‡ 4", new Date(12,10,2018));
		Attivita a5=new Attivita ("Attivit‡ 5", new Date(12,11,2018));
		
		Date d1= new Date(12,9,2018);
		
		Attivita[] elencoAttivita= {a1,a2,a3};
		assertArrayEquals(elencoAttivita, p1.elencaAttivitaInScadenza(d1));
		
	}
	
	
	@Test 
	public void testElencaAttivit‡InScadenzaConCompletamento() throws MaxNumeroAttivitaRaggiunto, AttivitaNonPresente
	{
		Progetto p1=new Progetto("progetto 1");
		p1.creaAttivita("Attivit‡ 1", new Date(12,6,2018));
		p1.creaAttivita("Attivit‡ 2", new Date(12,7,2018));
		p1.creaAttivita("Attivit‡ 3", new Date(12,8,2018));
		p1.creaAttivita("Attivit‡ 4", new Date(12,10,2018));
		p1.creaAttivita("Attivit‡ 5", new Date(12,11,2018));
		
		Attivita a1=new Attivita ("Attivit‡ 1", new Date(12,6,2018));
		Attivita a2=new Attivita ("Attivit‡ 2", new Date(12,7,2018));
		Attivita a3=new Attivita ("Attivit‡ 3", new Date(12,8,2018));
		Attivita a4=new Attivita ("Attivit‡ 4", new Date(12,10,2018));
		Attivita a5=new Attivita ("Attivit‡ 5", new Date(12,11,2018));
		
		Date d1= new Date(12,9,2018);
		p1.aggiornaAttivita("Attivit‡ 2", 100);
		Attivita[] elencoAttivita= {a1,a3};
		assertArrayEquals(elencoAttivita, p1.elencaAttivitaInScadenza(d1));
		
	}
	
	@Test 
	public void testElencaAttivit‡Completate() throws MaxNumeroAttivitaRaggiunto, AttivitaNonPresente
	{
		Progetto p1=new Progetto("progetto 1");
		p1.creaAttivita("Attivit‡ 1", new Date(12,6,2018));
		p1.creaAttivita("Attivit‡ 2", new Date(12,7,2018));
		p1.creaAttivita("Attivit‡ 3", new Date(12,8,2018));
		p1.creaAttivita("Attivit‡ 4", new Date(12,10,2018));
		p1.creaAttivita("Attivit‡ 5", new Date(12,11,2018));
		
		Attivita a1=new Attivita ("Attivit‡ 1", new Date(12,6,2018));
		Attivita a2=new Attivita ("Attivit‡ 2", new Date(12,7,2018));
		Attivita a3=new Attivita ("Attivit‡ 3", new Date(12,8,2018));
		Attivita a4=new Attivita ("Attivit‡ 4", new Date(12,10,2018));
		Attivita a5=new Attivita ("Attivit‡ 5", new Date(12,11,2018));
		
		GregorianCalendar gc=new GregorianCalendar();
		Date dataOdierna=new Date(gc.get(Calendar.DAY_OF_MONTH),(int)(gc.get(Calendar.MONTH))+1,gc.get(Calendar.YEAR));
		
		a2.setSvolgimento(100, dataOdierna);
		a3.setSvolgimento(100, dataOdierna);
		p1.aggiornaAttivita("Attivit‡ 2", 100);
		p1.aggiornaAttivita("Attivit‡ 3", 100);
		Attivita[] elencoAttivita= {a2,a3};
		assertArrayEquals(elencoAttivita, p1.elencaAttivitaCompletate());
		
	}
	
	@Test 
	public void testEsportaAttivit‡SuFile() throws MaxNumeroAttivitaRaggiunto, AttivitaNonPresente, IOException, FileException
	{
		Progetto p1=new Progetto("progetto 1");
		
		
		p1.creaAttivita("a1", new Date(12,6,2018));
		p1.creaAttivita("a2", new Date(12,7,2018));
		p1.creaAttivita("a3", new Date(12,8,2018));
		p1.creaAttivita("a4", new Date(12,10,2018));
		p1.creaAttivita("a5", new Date(12,11,2018));
		p1.aggiornaAttivita("a2", 100);
		p1.aggiornaAttivita("a3", 100);
		p1.esportaAttivitaSuFile("stampeProgetti\\p1.txt");
		
		GregorianCalendar gc=new GregorianCalendar();
		Date dataOdierna=new Date(gc.get(Calendar.DAY_OF_MONTH),(int)(gc.get(Calendar.MONTH))+1,gc.get(Calendar.YEAR));
		TextFile file=new TextFile("stampeProgetti\\p1.txt",'R');
	
		String stringaAttesa="a1;12\\6\\2018;0;no;"+
		"a2;12\\7\\2018;100;"+dataOdierna+
				";a3;12\\8\\2018;100;"+dataOdierna+
				";a4;12\\10\\2018;0;no;"+
				"a5;12\\11\\2018;0;no;";
		
		String stringaLettaDaFile="";
		try
		{
			while (true)
			{
				stringaLettaDaFile+=file.fromFile();
			}
		}
		catch (FileException e)
		{
			
		}
		
		
		assertEquals(stringaAttesa, stringaLettaDaFile);
		file.close();
	}
	
	@Test (expected=IOException.class)
	public void testEccezionePercorsoFile() throws MaxNumeroAttivitaRaggiunto, AttivitaNonPresente, IOException, FileException
	{
		Progetto p1=new Progetto("progetto 1");
		p1.creaAttivita("Attivit‡ 1", new Date(12,6,2018));
		p1.creaAttivita("Attivit‡ 2", new Date(12,7,2018));
		p1.creaAttivita("Attivit‡ 3", new Date(12,8,2018));
		p1.creaAttivita("Attivit‡ 4", new Date(12,10,2018));
		p1.creaAttivita("Attivit‡ 5", new Date(12,11,2018));
		p1.aggiornaAttivita("Attivit‡ 2", 100);
		p1.aggiornaAttivita("Attivit‡ 3", 100);
		p1.esportaAttivitaSuFile("Z:\\stampeProgetti\\p1.txt");
	}
	
	@Test 
	public void testEsportaAttivit‡CompletateSuFile() throws MaxNumeroAttivitaRaggiunto, AttivitaNonPresente, IOException, FileException
	{
		Progetto p1=new Progetto("progetto 1");
		p1.creaAttivita("a1", new Date(12,6,2018));
		p1.creaAttivita("a2", new Date(12,7,2018));
		p1.creaAttivita("a3", new Date(12,8,2018));
		p1.creaAttivita("a4", new Date(12,10,2018));
		p1.creaAttivita("a5", new Date(12,11,2018));
		p1.aggiornaAttivita("a2", 100);
		p1.aggiornaAttivita("a3", 100);
		p1.esportaAttivitaCompletateSuFile("stampeProgetti\\p1_completate.txt");
	
		GregorianCalendar gc=new GregorianCalendar();
		Date dataOdierna=new Date(gc.get(Calendar.DAY_OF_MONTH),(int)(gc.get(Calendar.MONTH))+1,gc.get(Calendar.YEAR));
		TextFile file=new TextFile("stampeProgetti\\p1_completate.txt",'R');
	
		String stringaAttesa="a2;12\\7\\2018;100;"+dataOdierna+
				";a3;12\\8\\2018;100;"+dataOdierna+";";
				
		
		String stringaLettaDaFile="";
		try
		{
			while (true)
			{
				stringaLettaDaFile+=file.fromFile();
			}
		}
		catch (FileException e)
		{
			
		}
		
		
		assertEquals(stringaAttesa, stringaLettaDaFile);
		file.close();
	
	
	}
	
	@Test (expected=IOException.class)
	public void testEccezionePercorsoFileAttivitaComletate() throws MaxNumeroAttivitaRaggiunto, AttivitaNonPresente, IOException, FileException
	{
		Progetto p1=new Progetto("progetto 1");
		p1.creaAttivita("Attivit‡ 1", new Date(12,6,2018));
		p1.creaAttivita("Attivit‡ 2", new Date(12,7,2018));
		p1.creaAttivita("Attivit‡ 3", new Date(12,8,2018));
		p1.creaAttivita("Attivit‡ 4", new Date(12,10,2018));
		p1.creaAttivita("Attivit‡ 5", new Date(12,11,2018));
		p1.aggiornaAttivita("Attivit‡ 2", 100);
		p1.aggiornaAttivita("Attivit‡ 3", 100);
		p1.esportaAttivitaCompletateSuFile("Z:\\stampeProgetti\\p1_completate.txt");
	}
	
	@Test 
	public void testEsportaAttivit‡InScadenzaSuFile() throws MaxNumeroAttivitaRaggiunto, AttivitaNonPresente, IOException, FileException
	{
		Progetto p1=new Progetto("progetto 1");
		p1.creaAttivita("a1", new Date(12,6,2018));
		p1.creaAttivita("a2", new Date(12,7,2018));
		p1.creaAttivita("a3", new Date(12,8,2018));
		p1.creaAttivita("a4", new Date(12,10,2018));
		p1.creaAttivita("a5", new Date(12,11,2018));
		
		Date d1=new Date(12,9,2018);
		p1.aggiornaAttivita("a2", 100);
	
		p1.esportaAttivitaInScadenzaSuFile(d1,"stampeProgetti\\p1_In_Scadenza_12_9_2018.txt");
	
		
		GregorianCalendar gc=new GregorianCalendar();
		Date dataOdierna=new Date(gc.get(Calendar.DAY_OF_MONTH),(int)(gc.get(Calendar.MONTH))+1,gc.get(Calendar.YEAR));
		TextFile file=new TextFile("stampeProgetti\\p1_In_Scadenza_12_9_2018.txt",'R');
	
		String stringaAttesa="a1;12\\6\\2018;0;a3;12\\8\\2018;0;";
				
		
		String stringaLettaDaFile="";
		try
		{
			while (true)
			{
				stringaLettaDaFile+=file.fromFile();
			}
		}
		catch (FileException e)
		{
			
		}
		
		
		assertEquals(stringaAttesa, stringaLettaDaFile);
		file.close();
	
	
	
	
	}
	
	@Test (expected=IOException.class)
	public void testEccezioneEsportaAttivit‡InScadenzaSuFile() throws MaxNumeroAttivitaRaggiunto, AttivitaNonPresente, IOException, FileException
	{
		Progetto p1=new Progetto("progetto 1");
		p1.creaAttivita("Attivit‡ 1", new Date(12,6,2018));
		p1.creaAttivita("Attivit‡ 2", new Date(12,7,2018));
		p1.creaAttivita("Attivit‡ 3", new Date(12,8,2018));
		p1.creaAttivita("Attivit‡ 4", new Date(12,10,2018));
		p1.creaAttivita("Attivit‡ 5", new Date(12,11,2018));
		
		Date d1=new Date(12,9,2018);
		p1.aggiornaAttivita("Attivit‡ 2", 100);
	
		p1.esportaAttivitaInScadenzaSuFile(d1,"Z:\\stampeProgetti\\p1_In_Scadenza_12_9_2018.txt");
	}
	
	
	
	
	
	
	
	
}
