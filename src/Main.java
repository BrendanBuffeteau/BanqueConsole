import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	// https://stackoverflow.com/questions/42341447/how-to-iterate-the-start-date-to-end-date-in-java

	// https://www.codeflow.site/fr/article/java__how-to-compare-dates-in-java
	


	// méthode consult op par dates / intégralité
	// dépot / virement / retrait >>> inscription base des opérations du compte
	
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		// Login = Expression régulière ADM** par ex
		
		BanqueTDB banquetdb = new BanqueTDB();

		boolean continuer=true;
		
		while (continuer==true) {
			
			String rep=null;
			
			boolean unlog=false;
			while (unlog==false) {
			
			String login=banquetdb.analyseLogin();
			
			String choix="";
			
			switch(login) {		
			case "admin":
				choix=banquetdb.menuAdmin();
				break;
			case "conseiller":
				choix=banquetdb.menuConseiller();
				break;
			case "client":
				choix=banquetdb.menuClient();
				break;
			}
			
			switch (choix) {
			
			case "1": 
				
				boolean nextope=true;
				while (nextope==true) { 
					
					System.out.println("Pour créer une agence tapez 1, pour activer/désactiver une agence tapez 2");
					int br2=0; // br = 1 réponse acceptable
					
					while (br2==0) {	
						
						Scanner sc1=new Scanner(System.in);
						rep=sc1.nextLine();
						if (rep.equals("1")) {
							banquetdb.creationAgence();
							br2=1;
							}
						else if (rep.equals("2")) {
							banquetdb.gererActiAgence();
							br2=1;
							}
						if (br2==0) System.out.println("Réponse incorrecte, merci de répondre par 1 ou 2.");
						}
	
				System.out.println("Voulez-vous créer/activer/désactiver une autre agence ? oui/non");
				int br21=0; // br = 1 réponse acceptable
				
				while (br21==0) {	
					
					Scanner sc1=new Scanner(System.in);
					rep=sc1.nextLine();
					if (rep.equals("oui")) {
						nextope=true;
						br21=1;
						}
					else if (rep.equals("non")) {
						nextope=false;
						br21=1;
						}
					if (br21==0) System.out.println("Réponse incorrecte, merci de répondre par oui ou non.");
					}
				}
				
				System.out.println("");
				System.out.println("Retour au menu principal");
				System.out.println("");
				break;
			
			case "2":
				
				boolean nextclient=true;
				while (nextclient==true) {
					
					banquetdb.creationClient();
					
					System.out.println("Voulez-vous créer une autre client ? oui/non");
					int br2=0; // br = 1 réponse acceptable
					
					while (br2==0) {	
						
						Scanner sc1=new Scanner(System.in);
						rep=sc1.nextLine();
						if (rep.equals("oui")) {
							nextclient=true;
							br2=1;
							}
						else if (rep.equals("non")) {
							nextclient=false;
							br2=1;
							}
						if (br2==0) System.out.println("Réponse incorrecte, merci de répondre par oui ou non.");
						}
						
						}
				System.out.println("");
				System.out.println("Retour au menu principal");
				System.out.println("");
				break;
				
			case "3": 
				
				boolean nextcompte=true;
				while (nextcompte==true) {
					//Création
					banquetdb.creationCompte();
					
					System.out.println("Voulez-vous créer une autre compte ? oui/non");
					int br3=0; // br = 1 réponse acceptable
					
					while (br3==0) {	
						
						Scanner sc1=new Scanner(System.in);
						rep=sc1.nextLine();
						if (rep.equals("oui")) {
							nextcompte=true;
							br3=1;
							}
						else if (rep.equals("non")) {
							nextcompte=false;
							br3=1;
							}
						if (br3==0) System.out.println("Réponse incorrecte, merci de répondre par oui ou non.");
						}
						
						}
				System.out.println("");
				System.out.println("Retour au menu principal");
				System.out.println("");
				break;
			
			case "4":
				
				boolean nextrechcompte=true;
				while (nextrechcompte==true) {
					
					banquetdb.operationsCompte(login);
					
					System.out.println("Voulez-vous rechercher une autre compte ? oui/non");
					int br10=0; // br = 1 réponse acceptable
					
					while (br10==0) {	
						
						Scanner sc1=new Scanner(System.in);
						rep=sc1.nextLine();
						if (rep.equals("oui")) {
							nextcompte=true;
							br10=1;
							}
						else if (rep.equals("non")) {
							nextrechcompte=false;
							br10=1;
						}
						if (br10==0) System.out.println("Réponse incorrecte, merci de répondre par oui ou non.");
						}
				}
				System.out.println("");
				System.out.println("Retour au menu principal");
				System.out.println("");
				break;
				
		
			
			case "5": // 5-Recherche de client (Nom, Numéro de compte, identifiant de client)
				
				boolean rechnextcli=true;
				while (rechnextcli==true) {
					banquetdb.rechClient();
				
				System.out.println("Voulez-vous chercher un autre client ? oui/non");
				int br5=0; // br = 1 réponse acceptable
				
				while (br5==0) {	
					
					Scanner sc1=new Scanner(System.in);
					rep=sc1.nextLine();
					if (rep.equals("oui")) {
						rechnextcli=true;
						br5=1;
						}
					else if (rep.equals("non")) {
						rechnextcli=false;
						br5=1;
						}
					if (br5==0) System.out.println("Réponse incorrecte, merci de répondre par oui ou non.");
					}
				}
					
			System.out.println("");
			System.out.println("Retour au menu principal");
			System.out.println("");
			break;
				
			case "6": // 6 - Afficher la liste des comptes d’un client (identifiant client)
				
			boolean nextlistecompte=true;
			while (nextlistecompte==true) {
				
				banquetdb.affListeCompte();
				
				System.out.println("Voulez-vous une autre liste de comptes ? oui/non");
				int br6=0; // br = 1 réponse acceptable
				
				while (br6==0) {	
					
					Scanner sc1=new Scanner(System.in);
					rep=sc1.nextLine();
					if (rep.equals("oui")) {
						nextlistecompte=true;
						br6=1;
						}
					else if (rep.equals("non")) {
						nextlistecompte=false;
						br6=1;
						}
					if (br6==0) System.out.println("Réponse incorrecte, merci de répondre par oui ou non.");
					}
					
					}
			System.out.println("");
			System.out.println("Retour au menu principal");
			System.out.println("");
			break;
				
			case "7": //7-Imprimer les infos client (identifiant client)
				
				boolean nextimpressionlistecompte=true;
				while (nextimpressionlistecompte==true) {
					
					banquetdb.writeListeComptesClient();
					System.out.println("Voulez-vous imprimer une autre liste de comptes ? oui/non");
					int br7=0; // br = 1 réponse acceptable
					
					while (br7==0) {	
						
						Scanner sc1=new Scanner(System.in);
						rep=sc1.nextLine();
						if (rep.equals("oui")) {
							nextimpressionlistecompte=true;
							br7=1;
							}
						else if (rep.equals("non")) {
							nextimpressionlistecompte=false;
							br7=1;
							}
						if (br7==0) System.out.println("Réponse incorrecte, merci de répondre par oui ou non.");
						}
						
						}
				System.out.println("");
				System.out.println("Retour au menu principal");
				System.out.println("");
				break;
				
			case "8": 
				System.out.println("Êtes vous sûr(e) de vouloir quitter le programme ? oui/non");
				int br7=0; // br = 1 réponse acceptable
				
				while (br7==0) {	
					
					Scanner sc1=new Scanner(System.in);
					rep=sc1.nextLine();
					if (rep.equals("oui")) {
						System.out.println("Fin du programme.");
						continuer=false;
						break;
						}
					else if (rep.equals("non")) {
						break;
						}
					if (br7==0) System.out.println("Réponse incorrecte, merci de répondre par oui ou non.");
					}
				}
			}
		}
	}
}

