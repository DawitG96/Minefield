package game;
import jbook.util.*;
import minefield.Campo;
import minefield.CampoNonSicuro;
import minefield.CampoSicuro;
import minefield.view.CampoView;
public class Console {

	public static void start(String[] args) 
	{
		Campo campo = null;
		int lunghezza=0;
		int altezza=0;
		int mine=0;
		int i=1;
		int difficolta=0;
		String sicuro;
		String stringa;

		
		boolean esplosione=false;
		
		System.out.println("Inizio partita!");
		do {
			sicuro=Input.readString("Vuoi iniziare con SafeStart? (y/n)");
		} while(!sicuro.matches("(n|N|y|Y)"));
		
		while(difficolta==0)
		{
			try
			{
				difficolta=Input.readInt("Quale livello di difficoltà desideri: \n1 - Principiante(9*9 con 10 mine)\n"
						+ "2 - Intermedio(16*16 con 40 mine)\n"
						+ "3 - Esperto(30*16 con 99 mine)\n"
						+ "4 - Personalizzato\n");
			}
			catch(NumberFormatException e) {System.out.println("Inserire un valore corretto: 1, 2, 3 o 4");}
			catch(IllegalArgumentException e) {}
			
			if(difficolta!=1 && difficolta!=2 && difficolta!=3 && difficolta!=4)
				difficolta =0;
		}
		
		
		switch(difficolta)
		{
			case 1:
			{
				System.out.println("Livello Principiante");
				lunghezza = 9;
				altezza = 9;
				mine = 10;
				if(sicuro.matches("(n|N)"))
					campo = new CampoNonSicuro(lunghezza,altezza, mine);
				else
					campo = new CampoSicuro(lunghezza,altezza,mine);
				break;
			}
			case 2:
			{
				System.out.println("Livello Intermedio");
				lunghezza = 16;
				altezza = 16;
				mine = 40;
				if(sicuro.matches("(n|N)"))
					campo = new CampoNonSicuro(lunghezza,altezza, mine);
				else
					campo = new CampoSicuro(lunghezza,altezza,mine);
				break;
			}
			case 3:
			{
				System.out.println("Livello Esperto");
				lunghezza = 30;
				altezza = 16;
				mine = 99;
				if(sicuro.matches("(n|N)"))
					campo = new CampoNonSicuro(lunghezza,altezza, mine);
				else
					campo = new CampoSicuro(lunghezza,altezza,mine);
				break;
			}
			case 4:
			{
				System.out.println("Livello Personalizzato");
				while(lunghezza==0)
				{
					try
					{
						lunghezza = Input.readInt("Inserisci quante righe vuoi: ");
					}
					catch(NumberFormatException e) {System.out.println("Deve essere un intero");}
					catch(IllegalArgumentException e) {}
					if(lunghezza<=0)
					{
						System.out.println("Inserisci un intero positivo!");
						lunghezza=0;
					}
				}
				
				
				while(altezza==0)
				{
					try
					{
						altezza = Input.readInt("Inserisci quante colonne vuoi: ");
					}
					catch(NumberFormatException e) {System.out.println("Deve essere un intero");}
					catch(IllegalArgumentException e) {}
					if(altezza<=0)
					{
						System.out.println("Inserisci un intero positivo!");
						altezza=0;
					}
				}
				while(campo==null)
				{
					while(mine==0 )
					{
						try
						{
							mine = Input.readInt("Quante mine vuoi che ci siano: ");
						}
						catch(NumberFormatException e) {System.out.println("Deve essere un intero");}
						catch(IllegalArgumentException e) {}
						if(mine<=0)
						{
							System.out.println("Inserisci un intero positivo!");
							mine=0;
						}
					}
					try
					{
						if(sicuro.matches("(n|N)"))
							campo = new CampoNonSicuro(lunghezza,altezza, mine);
						else
							campo = new CampoSicuro(lunghezza,altezza,mine);
					}
					catch(java.lang.IllegalArgumentException e) {
						System.out.println("Numero mine inadeguato! Inserire numero minore");
						mine=0;
					}
				}
				break;
			}
		}
		/*
		 * FIN QUI FUNZIONA, maybe
		 * TODO Madonna troia, se invii uno spazoio si fotte riga 177
		 * 
		 */
		while(campo.numeroCelleCoperte()!=0 && esplosione==false)
		{
			String scopri = new String();
			stringa=CampoView.getString(campo);
			System.out.println(stringa);
			System.out.println("Celle coperte: " + campo.numeroCelleCoperte() + "\nMine totali = " + mine + "\nCelle Pericolose: " + campo.numeroCellePericolose());
			int x=-1, y=-1;
			try
			{
				x= Input.readInt("Inserisci numero riga: ");
				y= Input.readInt("Inserisci numero colonna: ");
			}
			catch(NumberFormatException b)
			{
				System.out.println("Le coordinate devono essere intere");
			}
			if(i==2)
			{
				do
				{
					scopri=Input.readString("Vuoi scoprire(s) o segnalare pericolosa(p) la cella?");
				} while(!scopri.equals("s") && !scopri.equals("p"));
			}
			if(i==1 || scopri.equals("s"))
			{
				campo.scopriCella(x, y);
				i++;
			}
			else
				campo.aggiungiCellaPericolose(x, y);
			
			if(campo.minaEsplosa(x, y))
			{
				esplosione=true;
			}
			
		}
		if(esplosione)
			System.out.println("Hai perso!!");
		else
			System.out.println("Hai vinto!!");
		
	}

}
