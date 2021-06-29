import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.regex.*;

@SuppressWarnings("resource")
public class BanqueTDB {
	
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ssZ");
	
	// Liste d'agence
	Map<String, Agence> agences  = new HashMap<String, Agence>();
	
	// Liste de clients
	Map<String, Client> clients  = new HashMap<String, Client>();
	
	// Liste de comptes
	Map<String, Compte> comptes  = new HashMap<String, Compte>();
	

	public boolean repeterMethode(String action) {
		boolean retour=true;
		System.out.println("Voulez-vous "+action+" ? oui/non");
		boolean repok=false; // br = 1 réponse acceptable
		
		while (repok==false) {	
			
			Scanner sc1=new Scanner(System.in);
			String rep=sc1.nextLine();
			if (rep.equals("oui")) {
				retour=true;
				}
			else if (rep.equals("non")) {
				retour=false;
			}
			if (repok==false) System.out.println("Réponse incorrecte, merci de répondre par oui ou non.");
			}
		return retour;
	}

		// Menus
		//
	public String menuAdmin() {
	
		System.out.println("Tapez le nombre correspondant à l'action souhaitée");
		System.out.println("1-Créer ou activer/désactiver une agence");
		System.out.println("2-Créer ou Désactiver un(e) client(e) / Changer l'adresse d'un client ou ses informations");
		System.out.println("3-Créer ou désactiver un compte bancaire");
		System.out.println("4-Opérations (retrait, dépôt, virement, consultation de la liste des opérations)"); // consulter comptes  // dépot / retrait / virement // consulter opérations  // désactivation
		System.out.println("5-Recherche de client (Nom, Numéro de compte, identifiant client)"); // désactivation
		System.out.println("6-Afficher la liste des comptes d’un(e) client(e) (identifiant client)");
		System.out.println("7-Imprimer les infos d'un(e) client(e) (identifiant client)");
		System.out.println("8-Changer de login utilisateur ou quitter le programme");
		
		// Scanner entrée, choix case
		int br0=0;
		String choix="";
		while (br0==0) {	
			Scanner sc1=new Scanner(System.in);	
			choix=sc1.nextLine(); 
			
			if (choix.equals("1")) br0=1;
			if (choix.equals("2")) br0=1;
			if (choix.equals("3")) br0=1;
			if (choix.equals("4")) br0=1;
			if (choix.equals("5")) br0=1;
			if (choix.equals("6")) br0=1;
			if (choix.equals("7")) br0=1;
			if (choix.equals("8")) br0=1;
			if (br0==0) System.out.println("Réponse incorrecte, merci de répondre de 1 à 8 ");
			}
		return choix;
		}

	
	public String menuConseiller() {
	System.out.println("Tapez le nombre correspondant à l'action souhaitée");
	System.out.println("2-Créer un client / Changer l'adresse d'un client ou ses informations");
	System.out.println("3-Créer un compte bancaire");
	System.out.println("4-Opérations (retrait, dépôt, virement, consultation de la liste des opérations)"); // consulter comptes  // dépot / retrait / virement // consulter opérations
	System.out.println("5-Recherche de client (Nom, Numéro de compte ou identifiant client)"); // modif domicile et infos client
	System.out.println("6-Afficher la liste des comptes d’un client par identifiant client");
	System.out.println("7-Imprimer les infos client par identifiant client");
	System.out.println("8-Changer de login utilisateur ou quitter le programme");
	
	
	// Scanner entrée, choix case
	int br0=0;
	String choix="";
	while (br0==0) {	
	Scanner sc1=new Scanner(System.in);	
	choix=sc1.nextLine(); 
	
		if (choix.equals("2")) br0=1;
		if (choix.equals("3")) br0=1;
		if (choix.equals("4")) br0=1;
		if (choix.equals("5")) br0=1;
		if (choix.equals("6")) br0=1;
		if (choix.equals("7")) br0=1;
		if (choix.equals("8")) br0=1;
		if (br0==0) System.out.println("Réponse incorrecte, merci de répondre 2 à 8 ");
		}
	return choix;
}

	
	public String menuClient() {
	System.out.println("Tapez le nombre correspondant à l'action souhaitée");
	System.out.println("4-Opérations (retrait, dépôt, virement, consultation de la liste des opérations)"); // consulter comptes  // dépot / retrait / virement // consulter opérations
	System.out.println("6-Afficher la liste de vos comptes"); // this client
	System.out.println("7-Imprimer vos infos client"); // méthode imprim check admin conseiller ou client
	System.out.println("8-Changer de login utilisateur ou quitter le programme");
	
	// Scanner entrée, choix case
	int br0=0;
	String choix="";
	while (br0==0) {	
	Scanner sc1=new Scanner(System.in);	
	choix=sc1.nextLine(); 
	
		if (choix.equals("4")) br0=1;				
		if (choix.equals("6")) br0=1;
		if (choix.equals("7")) br0=1;				
		if (choix.equals("8")) br0=1;
		if (br0==0) System.out.println("Réponse incorrecte, merci de répondre 4, 6, 7 ou 8 ");
		}
	return choix;
}
	
	

	public String analyseLogin() {
		
		String typecompte = null;
		
		boolean loginok=false;
		
		while (loginok==false) {
		
		String login="";
		System.out.println("Merci d'entrer votre login.");
		Scanner sc=new Scanner(System.in);	
		login=sc.nextLine(); 
		
		Pattern admin = Pattern.compile("ADM\\d\\d") ;  
		Matcher matcher_a = admin.matcher(login);
		boolean isadmin = matcher_a.matches();
		if (isadmin==true) {
			loginok=true;
			typecompte= "admin";
			}
		
		Pattern conseiller = Pattern.compile("CO\\d\\d\\d\\d") ;  
		Matcher matcher_c = conseiller.matcher(login);
		boolean isconseiller = matcher_c.matches();
		if (isconseiller==true) {
			loginok=true;
			typecompte= "conseiller";
			}
		
		Pattern client = Pattern.compile("\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d");
		Matcher matcher_client = client.matcher(login);
		boolean isclient = matcher_client.matches();
		if (isclient==true) {
			if (comptes.containsKey(login)) {
				loginok=true;
				typecompte= "client";
			}
		}
		if (loginok==false)System.out.println("Compte non trouvé, veuillez réessayer");
		
		}
		return typecompte;
	}
	
	
	public void afficherRetourMenuPrincipal() {
		System.out.println("");
		System.out.println("Retour au menu principal");
		System.out.println("");
	}
	
	
	public void afficherRetourMenuPrecedent() {
		System.out.println("");
		System.out.println("Retour au menu précédent");
		System.out.println("");
	}
	
	
		// Création
		//
	public void creationAgence() {
		
		boolean nextagence=true;
		while (nextagence==true) {
		
			System.out.println("Tapez le nom de l'agence");
			Scanner sc=new Scanner(System.in);	
			String nom=sc.nextLine();
	
			System.out.println("Tapez l'adresse de l'agence");
			Scanner sc1=new Scanner(System.in);	
			String adresse=sc1.nextLine();
	
			Agence agence = new Agence(nom, adresse);
					
			String nvid	 = Agence.getRandomIdAgence();
			while (clients.containsKey(nvid)==true) {
				nvid = Agence.getRandomIdAgence();
				}
			agence.setCodeagence(nvid);
			agences.put(nvid,agence);
			
			System.out.println("Agence "+nom+" créée et activée, son code d'agence est : "+nvid+".");
			
			System.out.println("");
			System.out.println("Voulez-vous créer une autre agence ? oui/non");
			int br1=0; // br = 1 réponse acceptable
			
			while (br1==0) {	
				
				Scanner sc11=new Scanner(System.in);
				String rep = sc11.nextLine();
				if (rep.equals("oui")) {
					nextagence=true;
					br1=1;
					}
				else if (rep.equals("non")) {
					nextagence=false;
					br1=1;
					}
				if (br1==0) System.out.println("Réponse incorrecte, merci de répondre par oui ou non.");
				}
		}
		}
	
	
	
	public void creationClient() {
						
		System.out.println("Tapez le nom du client");
		Scanner sc1=new Scanner(System.in);	
		String nom=sc1.nextLine();

		nom=nom.toUpperCase ();
		
		System.out.println("Tapez le prénom du client");
		Scanner sc2=new Scanner(System.in);	
		String prenom=sc2.nextLine();
				
		System.out.println("Tapez l'adresse email du client");
		Scanner sc3=new Scanner(System.in);	
		String email=sc3.nextLine();
		
		System.out.println("Tapez la date de naissance du client au format jj/mm/aaaa");
		Scanner sc4=new Scanner(System.in);	
		String ddn=sc4.nextLine();

		Client client = new Client(nom, prenom, email, ddn );
		client.IsActive(true);
		
		String nvid = Client.getRandomIdClient();
		while (clients.containsKey(nvid)==true) {
			nvid = Client.getRandomIdClient();
		}
		client.setIdclient(nvid);
		clients.put(nvid,client);
		System.out.println("Client(e) "+nom+" "+prenom+" créé(e) et activé(e), son id client est : "+nvid+".");
	
	}
	
	

	public void creationCompte() {
		
		String rep="";
		String codeagence=null;
		
		String idclient = renseignerIdClient();
		if (idclient=="break") return; 
		
		if (clients.get(idclient).getNumcomptes().size()==3) {
			System.out.println("Le client "+idclient+" possède déjà le maximum de 3 comptes.");
			return;
		}
	
		boolean agenceexiste=false;
		while (agenceexiste==false) {
			System.out.println("Tapez le code agence du compte");
			Scanner sc2=new Scanner(System.in);	
			codeagence=sc2.nextLine();

			if (agences.containsKey(codeagence)==true) agenceexiste=true;
			else 	{
				System.out.println("Cet id agence est incorrect ou n'existe pas."); 
				System.out.println("Voulez-vous réessayer ?");
				int br=0; // br = 1 réponse acceptable
				
				while (br==0) {	
					Scanner sc11=new Scanner(System.in);
					rep=sc11.nextLine();

					if (rep.equals("oui")) {
							br=1;
							}
					else if (rep.equals("non")) {
						br=1;
						return;
						}
					if (br==0) System.out.println("Réponse incorrecte, merci de répondre par oui ou non.");
					}
				}
			}
		
		System.out.println("Tapez le solde initial du compte format xxx,xx");
		Scanner sc21=new Scanner(System.in);	
		float solde=sc21.nextFloat();

		boolean da = false;
		
		System.out.println("Le découvert est-il autorisé ? oui/non");
		int br=0; // br = 1 réponse acceptable
		
		while (br==0) {	
			
			Scanner sc11=new Scanner(System.in);
			rep=sc11.nextLine();

			if (rep.equals("oui")) {
				da=true;
				br=1;
				}
			else if (rep.equals("non")) {
				da=false;
				br=1;
				}
			if (br==0) System.out.println("Réponse incorrecte, merci de répondre par oui ou non.");
			}
		
		System.out.println("Tapez 1 pour Compte Courant, 2 pour PEL, 3 pour Livret A");
		Scanner sc=new Scanner(System.in);	
		String choix=sc.nextLine(); 

		int br2=0;
		
		while (br2==0) {	
			if (choix.equals("1")) br2=1;				
			if (choix.equals("2")) br2=1;
			if (choix.equals("3")) br2=1;
			if (br2==0) System.out.println("Réponse incorrecte, merci de répondre par 1, 2 ou 3");
			}
		
		String nvid = Compte.GetRandomNumAcc();
		while (comptes.containsKey(nvid)==true) {
			nvid = Compte.GetRandomNumAcc();
			}
		
		nvid.toString();

		switch (choix) {
			case "1":
				CompteCourant compteCC = new CompteCourant(clients.get(idclient), codeagence, solde, da);
				compteCC.setActive(true);
				compteCC.setNmcompte(nvid);
				comptes.put(nvid, compteCC);
				clients.get(idclient).ajNumCompte(nvid);
				System.out.println("Compte courant "+nvid+" créé(e) et activé(e), son id client est : "+nvid+".");
				break;
			case "2": 
				PlanEpLog comptePEL = new PlanEpLog(clients.get(idclient), codeagence, solde, da);
				comptePEL.setActive(true);
				comptePEL.setNmcompte(nvid);
				comptes.put(nvid, comptePEL);
				clients.get(idclient).ajNumCompte(nvid);
				System.out.println("Compte PEL "+nvid+" créé(e) et activé(e), son id client est : "+nvid+".");
				break;
			case "3": 
				LivretA compteLivretA = new LivretA(clients.get(idclient), codeagence, solde, da);
				compteLivretA.setActive(true);
				compteLivretA.setNmcompte(nvid);
				comptes.put(nvid, compteLivretA);
				clients.get(idclient).ajNumCompte(nvid);
				System.out.println("Compte Livret A "+nvid+" créé(e) et activé(e), son id client est : "+nvid+".");
				break;
		}
	}

	
		// Renseignements
		//
	public boolean verifCompteActif(String numcpt) {
		boolean retour = true;
		if (comptes.get(numcpt).isActive()==false) {
			retour=false;
			System.out.println("Le compte "+numcpt+" est désactivé.");
			}
		return retour;
	}
	
	
	public String renseignerIdClient() {
		
		String idclient=null;
		
		boolean clientexiste=false;
		while (clientexiste==false) {
		
		System.out.println("Tapez l'id du client");
		Scanner sc1=new Scanner(System.in);	
		idclient=sc1.nextLine();

		if (clients.containsKey(idclient)==true) clientexiste=true;
		else 	{
			System.out.println("Cet id client est incorrect ou n'existe pas.");
			System.out.println("Voulez-vous réessayer ?");
			int br=0; // br = 1 réponse acceptable
			
			while (br==0) {	
				Scanner sc11=new Scanner(System.in);
				String rep=sc11.nextLine();

				if (rep.equals("oui")) {
						br=1;
						}
				else if (rep.equals("non")) {
					br=1;
					return "break";
					}
				if (br==0) System.out.println("Réponse incorrecte, merci de répondre par oui ou non.");
				}
			}
		}
		return idclient;
	}
	
	
	public String renseignerCompte() {
		
		boolean cptexiste=false;
		String retour=null;
		while (cptexiste==false) {
				
		System.out.println("Indiquez le numéro de compte demandé (13 chiffres)");
		Scanner sc1=new Scanner(System.in);	
		String numcpt=sc1.nextLine();
		if (comptes.containsKey(numcpt)==true) retour = numcpt;
		
		else if (comptes.containsKey(numcpt)==false) {
			System.out.println("Numéro de compte non trouvé ou incorrect");
			System.out.println("Voulez-vous réessayer ? oui/non");
			int br=0; // br = 1 réponse acceptable
			while (br==0) {
				
				String rep="";

				Scanner sc11=new Scanner(System.in);
				rep = sc11.nextLine();
	
				if (rep.equals("oui")) {
					br=1;
				}
				else if (rep.equals("non")) {
					br=1;
					return retour;
					}
				if (br==0) System.out.println("Réponse incorrecte, merci de répondre par oui ou non.");
				}
			}
		}
	return retour;
	}
	
	
	public LocalDateTime renseignerDateDebut() {
		
		 System.out.println("Tapez l'année de la date de début souhaitée Ex 2015");
		Scanner sc1=new Scanner(System.in);	
		int annee=sc1.nextInt();
		System.out.println("Tapez le mois de la date souhaitée. 1 à 12");
		int mois=sc1.nextInt();
		System.out.println("Tapez le jour de la date souhaitée. 1 à 31");
		int jour=sc1.nextInt();
		
		LocalDateTime datedebut = LocalDateTime.of(annee, mois, jour, 0, 0, 0);
			  
			  return datedebut;
		}
	
	
	public LocalDateTime renseignerDateFin() {
		
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
	
	
	@SuppressWarnings("unused")
	public double renseignerMontant() {
	
	double montant = 0;
	String montantstring=null;
	boolean montantok=false;
	while (montantok=false) {		
		System.out.println("Indiquez le montant du virement au format xxxx,xx");
		
		Scanner sc11=new Scanner(System.in);	
		if(sc11.hasNextDouble()) {
			montant = sc11.nextDouble();
			montantok=true;
			}
		else if (sc11.hasNextFloat()) {
			montant = sc11.nextFloat();
			montantok=true;
			}
		else if (sc11.hasNextInt()) {
			montant = sc11.nextInt();
			montantok=true;
		}
		else if (sc11.hasNextLine()) {
			montantstring= sc11.nextLine();
			montant = Double.parseDouble(montantstring);
			montantok=true;
			}
		else System.out.println("Montant Incorrect.");
	}
return montant;
}

		// Gestion (activation, changement adresse..)
		//
	public void gererActiAgence() {
		
		String codeagence=null;
		String rep=null;
		
		boolean nextactiv=true;
		while(nextactiv==true) {
		
		boolean agenceexiste=false;
		while (agenceexiste==false) {
			System.out.println("Tapez le code agence du compte");
			Scanner sc2=new Scanner(System.in);	
			codeagence=sc2.nextLine();

			if (agences.containsKey(codeagence)==true) agenceexiste=true;
			else 	{
				System.out.println("Cet id agence est incorrect ou n'existe pas."); 
				System.out.println("Voulez-vous réessayer ?");
				int br=0; // br = 1 réponse acceptable
				
				while (br==0) {	
					Scanner sc11=new Scanner(System.in);
					rep=sc11.nextLine();

					if (rep.equals("oui")) {
							br=1;
							}
					else if (rep.equals("non")) {
						br=1;
						return;
						}
					if (br==0) System.out.println("Réponse incorrecte, merci de répondre par oui ou non.");
					}
				}
			}
		if (agences.get(codeagence).getActive()==true) {
			System.out.println("L'agence code : "+codeagence+" est active.");
			System.out.println("Voulez-vous la désactiver ?");
			int br=0; // br = 1 réponse acceptable
			
			while (br==0) {	
				Scanner sc11=new Scanner(System.in);
				rep=sc11.nextLine();

				if (rep.equals("oui")) {
						br=1;
						agences.get(codeagence).setActive(false);
						System.out.println("L'agence code : "+codeagence+" est désactivée.");
						}
				else if (rep.equals("non")) {
					br=1;
					}
				if (br==0) System.out.println("Réponse incorrecte, merci de répondre par oui ou non.");
				}
			
		}
		if (agences.get(codeagence).getActive()==false) {
			System.out.println("L'agence code : "+codeagence+" est désormais désactivée.");
			System.out.println("Voulez-vous l'activer ?");
			int br=0; // br = 1 réponse acceptable
			
			while (br==0) {	
				Scanner sc11=new Scanner(System.in);
				rep=sc11.nextLine();

				if (rep.equals("oui")) {
						br=1;
						agences.get(codeagence).setActive(true);
						System.out.println("L'agence code : "+codeagence+" est désormais active.");
						}
				else if (rep.equals("non")) {
					br=1;
					}
				if (br==0) System.out.println("Réponse incorrecte, merci de répondre par oui ou non.");
				}
			
		}
		System.out.println("Voulez-vous activer/désactiver une autre agence ? oui/non");
		int br1=0; // br = 1 réponse acceptable
		
		while (br1==0) {	
			
			Scanner sc1=new Scanner(System.in);
			rep=sc1.nextLine();
			if (rep.equals("oui")) {
				nextactiv=true;
				br1=1;
				}
			else if (rep.equals("non")) {
				nextactiv=false;
				br1=1;
				}
			if (br1==0) System.out.println("Réponse incorrecte, merci de répondre par oui ou non.");
			}
		}
	}
	
	
	public void gestionClientAdmin() {
		
		boolean nextgestclient=true;
		while (nextgestclient==true) {
		
			System.out.println("Tapez le nombre correspondant à l'action souhaitée");
			System.out.println("1 - Créer un client");
			System.out.println("2 - Modifier l'adresse un client");
			System.out.println("3 - Modifier les informations un client");
			System.out.println("4 - Désactiver un client");
			System.out.println("5 - Revenir au menu principal");
			
			int br0=0;
			String choix="";
			while (br0==0) {	
				Scanner sc1=new Scanner(System.in);	
				choix=sc1.nextLine(); 
				
				if (choix.equals("1")) br0=1;
				if (choix.equals("2")) br0=1;
				if (choix.equals("3")) br0=1;
				if (choix.equals("4")) br0=1;
				if (choix.equals("5")) br0=1;
				if (br0==0) System.out.println("Réponse incorrecte, merci de répondre de 1 à 5 ");
				}
			
			switch (choix) {
				case "1":
					boolean nextcreaclient=true;
					while (nextcreaclient==true) {
						creationClient();
						nextcreaclient=repeterMethode("créer un(e) autre client(e)");
						}
				break;
				case "2":
					boolean nextchgadresse=true;
					while (nextchgadresse==true) {
						// Methode chg adresse
						nextchgadresse=repeterMethode("changer l'adresse d'un(e) autre client(e)");
					}
				break;
				case "3":
					boolean nextmodifclient=true;
					while (nextmodifclient==true) {
						// Methode changement info
						nextmodifclient=repeterMethode("changer les informations d'un(e) autre client(e)");
					}
				break;
				case "4":
					boolean nextdesaccpt=true;
					while (nextdesaccpt==true) {
						// gerer acti compte
						nextdesaccpt=repeterMethode("activer/désactiver un autre compte");
					}
				break;
				case "5":
					nextgestclient=false;
					this.afficherRetourMenuPrincipal();
					break;
				}
			}
		}
	
	
	public void gestionClientConseiller() {
		
		boolean nextgestclient=true;
		while (nextgestclient==true) {
		
			System.out.println("Tapez le nombre correspondant à l'action souhaitée");
			System.out.println("1 - Créer un client");
			System.out.println("2 - Modifier l'adresse un client");
			System.out.println("3 - Modifier les informations un client");
			System.out.println("4 - Revenir au menu principal");
			
			int br0=0;
			String choix="";
			while (br0==0) {	
				Scanner sc1=new Scanner(System.in);	
				choix=sc1.nextLine(); 
				
				if (choix.equals("1")) br0=1;
				if (choix.equals("2")) br0=1;
				if (choix.equals("3")) br0=1;
				if (choix.equals("4")) br0=1;
				if (choix.equals("5")) br0=1;
				if (br0==0) System.out.println("Réponse incorrecte, merci de répondre de 1 à 4.");
				}
			
			switch (choix) {
				case "1":
					boolean nextcreaclient=true;
					while (nextcreaclient==true) {
						creationClient();
						nextcreaclient=repeterMethode("créer un(e) autre client(e)");
						}
				break;
				
				case "2":
					boolean nextchgadresse=true;
					while (nextchgadresse==true) {
						// Methode chg adresse
						nextchgadresse=repeterMethode("changer l'adresse d'un(e) autre client(e)");
					}
				break;
				
				case "3":
					boolean nextmodifclient=true;
					while (nextmodifclient==true) {
						// Methode changement info
						nextmodifclient=repeterMethode("changer les informations d'un(e) autre client(e)");
					}
				break;

				case "4":
					nextgestclient=false;
					this.afficherRetourMenuPrincipal();
					break;
				}
			}
		}
		
	
	public void changementAdresseClient() {
		
		String idclient=renseignerIdClient();
		if (idclient=="break") return;
		String email = clients.get(idclient).getEmail();
		
		System.out.println("L'email actuel du compte "+idclient+" est "+email+". Veuillez saisir le nouvel e-mail, "
				+ "si vous ne voulez pas le changer, appuyez sur Entrée en laissant le champ vide.");
		
		Scanner sc1=new Scanner(System.in);
		String nvmail=sc1.nextLine();
		if (nvmail=="") nvmail=email;
		
		clients.get(idclient).setEmail(nvmail);
		System.out.println("Le nouvel email du compte "+idclient+" est "+nvmail+".");
	}
		
	
	public void changementInfosClient() {
		
	}
	
	
		// Opérations
	// Méthode choix n°4

	public void operationsCompte(String login) {
		
		boolean nextcpt=true;
		while (nextcpt==true) {
		
			String numcpt=this.renseignerCompte();
			if (numcpt == null) nextcpt=false;
			
			Compte recup = this.comptes.get(numcpt);
	
			recup.getClient().impInfosClient();
			recup.impInfosCompte();
			
			boolean nextoper=true;
			while (nextoper==true) {
			
				System.out.println("1-Effectuer un dépôt"); 
				System.out.println("2-Effectuer un retrait"); 
				System.out.println("3-Effectuer un virement");
				System.out.println("4-Consulter les opérations du compte intégralement ou sur une période donnée");
				System.out.println("5-Changer de compte ou retour au menu principal");
				if (login=="admin") System.out.println("6-Activer/Désactiver le compte");
				
				
				// Scanner entrée, choix case
				int br0=0;
				String choix="";
				while (br0==0) {	
				Scanner sc1=new Scanner(System.in);	
				choix=sc1.nextLine(); 
			
				if (choix.equals("1")) br0=1;
				if (choix.equals("2")) br0=1;
				if (choix.equals("3")) br0=1;
				if (choix.equals("4")) br0=1;
				if (choix.equals("5")) br0=1;
				if (choix.equals("6")) {
					if (login=="admin") {System.out.println("5-Activer/Désactiver le compte");
					br0=1;
					}
					if (login!="admin") br0=0;
				}

				if (br0==0 && login=="admin") System.out.println("Réponse incorrecte, merci de répondre 1 à 6 ");
				if (br0==0 && login!="admin") System.out.println("Réponse incorrecte, merci de répondre 1 à 5 ");
					}
				
				switch (choix) {
				
				case "1":
					
					boolean nextdepot=true;
					while (nextdepot==true) {
				
						depot(numcpt);
						nextdepot=repeterMethode("réaliser un autre dépôt");
					}
					afficherRetourMenuPrecedent();
					break;
				
				case "2":
					
					boolean nextretrait=true;
					while (nextretrait==true) {
				
						retrait(numcpt);
						nextretrait=repeterMethode("réaliser un autre retrait");
					}
					afficherRetourMenuPrecedent();
					break;
				
				case "3":
					
					boolean nextvirement=true;
					while (nextvirement==true) {
				
						virement(numcpt);
						nextvirement=repeterMethode("réaliser un autre virement");
					}
					afficherRetourMenuPrecedent();
				break;
				
					case "4":
						
						boolean nextconsultope=true;
						while (nextconsultope==true) {
					
							afficherListeOperationsDates(numcpt);
							nextconsultope=repeterMethode("consulter d'autres dates");
							}
						afficherRetourMenuPrecedent();
						break;
						
					case "5":
						
						System.out.println("Pour consulter un autre compte tapez 1, pour revenir au menu principal tapez 2");
						int br7=0; // br = 1 réponse acceptable
						
						while (br7==0) {	
							
							Scanner sc1=new Scanner(System.in);
							String rep=sc1.nextLine();
							if (rep.equals("1")) {
								System.out.println("Retour au menu des opérations du compte");
								nextoper=false;
								break;
								}
							else if (rep.equals("2")) {
								nextoper=false;
								nextcpt=false;
								br7=1;
								}
							if (br7==0) System.out.println("Réponse incorrecte, merci de répondre par oui ou non.");
							}
						
						System.out.println("Retour au menu des opérations du compte");
						nextoper=false;
						break;
						
					case "6":
						
						if (comptes.get(numcpt).isActive()==true) {
							System.out.println("Le compte : "+numcpt+" est actif.");
							System.out.println("Voulez-vous le désactiver ?");
							int br=0; // br = 1 réponse acceptable
							
							while (br==0) {	
								Scanner sc11=new Scanner(System.in);
								String rep=sc11.nextLine();

								if (rep.equals("oui")) {
										br=1;
										agences.get(numcpt).setActive(false);
										System.out.println("Le compte: "+numcpt+" est désactivé.");
										}
								else if (rep.equals("non")) {
									br=1;
									}
								if (br==0) System.out.println("Réponse incorrecte, merci de répondre par oui ou non.");
								}
							
						}
						if (agences.get(numcpt).getActive()==false) {
							System.out.println("Le compte : "+numcpt+" est désormais désactivé.");
							System.out.println("Voulez-vous l'activer ?");
							int br=0; // br = 1 réponse acceptable
							
							while (br==0) {	
								Scanner sc11=new Scanner(System.in);
								String rep=sc11.nextLine();

								if (rep.equals("oui")) {
										br=1;
										agences.get(numcpt).setActive(true);
										System.out.println("Le compte : "+numcpt+" est désormais actif.");
										}
								else if (rep.equals("non")) {
									br=1;
									}
								if (br==0) System.out.println("Réponse incorrecte, merci de répondre par oui ou non.");
							}
						}
					}
				}
			}
		}
	
	
	
	public void depot(String numcpt) {
		
		double montantdepot=0;
		
		montantdepot=renseignerMontant();
			
		comptes.get(numcpt).setSolde((comptes.get(numcpt).getSolde()+montantdepot));
		
		String desc="Depôt de "+montantdepot+" effectué au compte "+numcpt+".";
		System.out.println(desc);
		
		LocalDateTime actu = LocalDateTime.now(); 
		
		comptes.get(numcpt).ajoutOperation(actu, desc);
		
	}
	
	
	
	@SuppressWarnings("unused")
	public void retrait(String numcpt) {
		
		double montantretrait=0;
		
		boolean retraitok=true;
		while (retraitok=true) {
			
			montantretrait=renseignerMontant();
			// Verif découvert
			System.out.println("Compte Emetteur");
			numcpt=this.renseignerCompte();
			
			if (comptes.get(numcpt).getDA()==false) {
				if ((comptes.get(numcpt).getSolde())-montantretrait<0){
					System.out.println("Retrait impossible, le compte émetteur passerait à découvert et il n'y est pas autorisé");
					retraitok=false;
					}
				}
			}
			
			comptes.get(numcpt).setSolde((comptes.get(numcpt).getSolde()-montantretrait));
			LocalDateTime actu = LocalDateTime.now(); 
			String desc="Retrait de "+montantretrait+" effectué au compte "+numcpt+".";
			comptes.get(numcpt).ajoutOperation(actu, desc);
			System.out.println(desc);
	}

	
	
	@SuppressWarnings("unused")
	public void virement(String numcpt) {
		
		String destinataire=null;
		double montantvirement=0;
		
		boolean virementok=true;
		while (virementok=true) {
			
			montantvirement=renseignerMontant();
			
			// Verif découvert
			System.out.println("Compte Emetteur");
			numcpt=this.renseignerCompte();
			
			
			if (comptes.get(numcpt).getDA()==false) {
				if ((comptes.get(numcpt).getSolde())-montantvirement<0){
					System.out.println("Virement impossible, le compte émetteur passerait à découvert et il n'y est pas autorisé");
					virementok=false;
					}
				}
			}
			
			System.out.println("Compte Destinataire");
			
			destinataire=this.renseignerCompte();
			
			comptes.get(numcpt).setSolde((comptes.get(numcpt).getSolde()-montantvirement));
			comptes.get(destinataire).setSolde((comptes.get(destinataire).getSolde()+montantvirement));
			System.out.println("Virement de "+montantvirement+" effectué du compte "+numcpt+" au compte "+destinataire+".");
			
			LocalDateTime actu = LocalDateTime.now(); 
			
			comptes.get(numcpt).ajoutOperation(actu,"Virement de "+montantvirement+" envoyé au compte "+destinataire+".");
			comptes.get(destinataire).ajoutOperation(actu,"Virement de "+montantvirement+" reçu du compte "+numcpt+".");
		}
	
	
	
	public void choixImpListeOperations(String numcpt) {
		
		boolean nextlist=true;
		while (nextlist==true) { 
			
			System.out.println("Pour imprimer la liste entière des opérations, tapez 1.");
			System.out.println("Pour imprimer une liste d'une date à une autre, tapez 2.");
			int br2=0; // br = 1 réponse acceptable
			
			while (br2==0) {	
				
				Scanner sc1=new Scanner(System.in);
				String rep=sc1.nextLine();
				if (rep.equals("1")) {
					afficherListeOperationsFull(numcpt);
					br2=1;
					}
				else if (rep.equals("2")) {
					afficherListeOperationsDates(numcpt);
					br2=1;
					}
				if (br2==0) System.out.println("Réponse incorrecte, merci de répondre par 1 ou 2.");
				}

		System.out.println("Voulez-vous imprimer une autre liste ? oui/non");
		int br21=0; // br = 1 réponse acceptable
		
		while (br21==0) {	
			
			Scanner sc1=new Scanner(System.in);
			String rep=sc1.nextLine();
			if (rep.equals("oui")) {
				nextlist=true;
				br21=1;
				}
			else if (rep.equals("non")) {
				nextlist=false;
				br21=1;
				}
			if (br21==0) System.out.println("Réponse incorrecte, merci de répondre par oui ou non.");
			}
		}
		
	}
	
	
	
	public void afficherListeOperationsFull(String numcpt) {
		Map<LocalDateTime, String> operations=comptes.get(numcpt).getOperations();
		
		//Traversing map  
	    for(Map.Entry<LocalDateTime, String> entry: operations.entrySet()){    
	    	
	    	LocalDateTime date = LocalDateTime.now();  date=entry.getKey();  
	    	String desc=entry.getValue();  
	    	
	        System.out.println(date+desc);  
	    	}    
		}    
		
	
	
	public void afficherListeOperationsDates(String numcpt) {
	TreeMap<LocalDateTime, String> operations=comptes.get(numcpt).getOperations();
	
	//Méthode demande date 2x
	LocalDateTime ddebut = renseignerDateDebut();
	LocalDateTime dfin = renseignerDateFin();
	
	operations.subMap(ddebut, dfin);
	//Traversing map  
    for(Map.Entry<LocalDateTime, String> entry: operations.entrySet()){  
    	
    	LocalDateTime date=entry.getKey();  
        String desc=entry.getValue();  
        
        System.out.println(date+desc);  
    	}    
}
	
	// Méthode choix n°5
	
	
	public	void rechClient() { 
			
			System.out.println("Recherche Client, par ordre de priorité");
			System.out.println("Si vous disposez de l'id client, tapez 1.");
			System.out.println("Si vous disposez d'un numéro de compte, tapez 2.");
			System.out.println("Si vous disposez uniquement du nom du client, tapez 3.");
			
			Scanner sc=new Scanner(System.in);	
			String choix=sc.nextLine(); 
			sc.close();
			int br=0;
			
			while (br==0) {	
				if (choix.equals("1")) br=1;				
				if (choix.equals("2")) br=1;
				if (choix.equals("3")) br=1;
				if (br==0) System.out.println("Réponse incorrecte, merci de répondre par 1, 2 ou 3");
				}
			
			switch(choix) {
			
			case "1":
				System.out.println("Tapez l'id du client");
				Scanner sc1=new Scanner(System.in);	
				String idclient=sc1.nextLine();
				if (clients.containsKey(idclient)==true) {
					clients.get(idclient).impInfosClient();
				}
				else System.out.println("idclient non trouvé ou incorrect.");
				break;
				
			case "2":
				System.out.println("Indiquez le numéro de compte (13 chiffres)");
				Scanner sc3=new Scanner(System.in);	
				String numcpt=sc3.nextLine();
				if ((comptes.containsKey(numcpt))==false) {
					System.out.println("numéro de compte non trouvé ou incorrect.");
					break;
					}
				comptes.get(numcpt).getClient().impInfosClient();
				break;			
				
			case "3":
				System.out.println("Indiquez le Nom du client");
				Scanner sc31=new Scanner(System.in);	
				String nom=sc31.nextLine();
				nom=nom.toUpperCase ();
				
				ArrayList<Client> recherche = new ArrayList<Client>();
				
				for (Map.Entry<String, Client> me : clients.entrySet()) { 				
		            if (nom==me.getValue().getNom()) recherche.add(me.getValue());
				}
				System.out.println("Liste des clients pour le nom : "+nom);
				for (Client i : recherche) {
			        i.impInfosClient();
			      }
				break;		
			}
		}	

	// Méthode choix n°6
	
	
	public void affListeCompte() {
		System.out.println("Indiquez l'id du client (2 lettres 6 chiffres)");
		Scanner sc=new Scanner(System.in);	
		String idcli=sc.nextLine();

		for (String i : clients.get(idcli).getNumcomptes()) {
			System.out.print("Compte : ");
	        System.out.println(i);
		}
		return;
	}
	
	
	// Méthode choix n°7
	public static String dateActuellePourImpression() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(new Date());
	}
	
	
	public void writeListeComptesClient() throws IOException {
		
		PrintStream out = new PrintStream(System.out);
		
		System.out.println("Indiquez l'id du client (2 lettres 6 chiffres)");
		Scanner sc=new Scanner(System.in);	
		String idcli=sc.nextLine();

		String filename="Fiche_client "+idcli+" "+dateActuellePourImpression()+ ".txt";
				//ouvrir le fichier
		File file = new File("b:\\fiches clients\\"+filename); 		// Penser à créer le répertoire avant 
									//(vous lui donner le nom que vous voulez)
		
		PrintStream fichier = new PrintStream(file);
		System.setOut(fichier);
		
		System.out.println("                               Fiche client");
		System.out.println("Numéro client : "+clients.get(idcli).idclient);
		System.out.println("Nom : "+clients.get(idcli).nom);
		System.out.println("Prénom : "+clients.get(idcli).prenom);
		System.out.println("Date de naissance : "+clients.get(idcli).ddn);
		System.out.println("_______________________________________________");
		System.out.println("Liste de comptes");
		System.out.println("_______________________________________________");
		System.out.println("Numéro de compte                Solde");
		System.out.println("_______________________________________________");
		
		ArrayList<String> imp =(clients.get(idcli).getNumcomptes());
		
		for (String i : imp) {
			System.out.println(i+"                  "+comptes.get(i).getSolde()+" euros"+"              "+comptes.get(i).Soldesmiley());
			}

		System.setOut(out);
	}
	
	
	public boolean quitterProgramme() {
	 
	 boolean continuer=true;
	 System.out.println("Êtes vous sûr(e) de vouloir quitter le programme ? oui/non");
		int br7=0; // br = 1 réponse acceptable
		
		while (br7==0) {	
			
			Scanner sc1=new Scanner(System.in);
			String rep=sc1.nextLine();
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
		return continuer;
	}
 }
