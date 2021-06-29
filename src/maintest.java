import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

public class maintest {
	
public static LocalDateTime obtenirDateFin() {
		
		System.out.println("Tapez l'année de la date de fin souhaitée Ex 2015");
		Scanner sc1=new Scanner(System.in);	
		int annee=sc1.nextInt();
		System.out.println("Tapez le mois de la date souhaitée. 1 à 12");
		int mois=sc1.nextInt();
		System.out.println("Tapez le jour de la date souhaitée. 1 à 31");
		int jour=sc1.nextInt();
		
		LocalDateTime datefin = LocalDateTime.of(annee, mois, jour, 23, 59, 59);
			  
		return datefin;
		}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		obtenirDateFin();

	}
}
