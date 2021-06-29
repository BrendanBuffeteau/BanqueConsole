
public class Agence {
	String codeagence;
	String nom;
	String adresse;
	boolean active;
	
	public Agence(String nom, String adresse) {
	this.nom=nom;
	this.adresse=adresse;
	active=true;
	}
	
	static String getRandomIdAgence()  { 
	  
	        String NumericString = "0123456789"; 
	  
	        // create StringBuffer size of AlphaNumericString 
	        StringBuilder sb = new StringBuilder(3); 
	  
	        for (int i = 0; i < 3; i++) { 
	  
	            // generate a random number between 
	            // 0 to AlphaNumericString variable length 
	            int index 
	                = (int)(NumericString.length() 
	                        * Math.random()); 
	  
	            // add Character one by one in end of sb 
	            sb.append(NumericString.charAt(index)); 
	        	} 
	        return sb.toString();
	    }
	
	public String getCodeagence() {
		return codeagence;
	}

	public void setCodeagence(String nvid) {
		this.codeagence = nvid;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
