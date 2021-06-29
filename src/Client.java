import java.util.ArrayList;

public class Client {

	String idclient;
	String nom;
	String prenom;
	String ddn;
	String email;
	
	ArrayList<String> numcomptes = new ArrayList<String>();
	
	public Client(String nom, String prenom, String ddn, String email) {
		
		this.nom=nom;
		this.prenom=prenom;
		this.ddn=ddn;
		this.email=email;
	}	
	
	public void impInfosClient() {
		System.out.println("Client "+nom+" "+prenom+". Id client : "+idclient+". Néé(e) le : "+ddn+". Email : "+email+".");
	}
	
	static String getRandomIdClient()  { 
  
        // chose a Character random from this String 
        String AlphaString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";      

        // create StringBuffer size of AlphaNumericString 
        StringBuilder sa = new StringBuilder(2); 
  
        for (int i = 0; i < 2; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (char)(AlphaString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sa.append(AlphaString.charAt(index)); 
        } 
        
        String NumericString = "0123456789"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(6); 
  
        for (int i = 0; i < 6; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(NumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(NumericString.charAt(index)); 
        } 
        String a =sa.toString(); 
        String b =sb.toString();
        
        return a+b;
    }  

	public String getIdclient() {
		return idclient;
	}

	public void setIdclient(String idclient) {
		this.idclient = idclient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDdn() {
		return ddn;
	}

	public void setDdn(String ddn) {
		this.ddn = ddn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<String> getNumcomptes() {
		return numcomptes;
	}

	public void setNumcomptes(ArrayList<String> numcomptes) {
		this.numcomptes = numcomptes;
	}

	public void ajNumCompte(String nvcpt) {
		 this.numcomptes.add(nvcpt);
	}

	public void IsActive(boolean b) {
		// TODO Auto-generated method stub
		
	}
	



}

