package minefield.view;

import minefield.Campo;

public class CampoView {
	public static String getString(Campo campo)
	{
		String stringa = new String("\t");
		
		for(int i=0;i<campo.colonne;i++)
			stringa+=i+"\t";
		stringa+="\n";
		for(int x=0; x<campo.righe; x++)
		{
			stringa+=x+"\t";
			for(int y=0; y<campo.colonne; y++)
			{
				if(campo.cellaPericolosa(x, y))
					stringa+="[p]\t";
				else if(campo.cellaCoperta(x, y))
					stringa+="[-]\t";
				else if(campo.essereMina(x, y))
					stringa+="[X]\t";
				else if(!campo.cellaCoperta(x, y))
					stringa+="["+ campo.numeroMineIntorno(x, y)+"]\t";
			}
			stringa+="\n";
		}
		return stringa;
	}
}
