import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public abstract class Compte {

	String nmcompte;
	Client client;
	double solde;
	float fa;
	boolean da;
	String codeagence;
	boolean active;
	
	TreeMap<LocalDateTime, String> operations  = new TreeMap<>();
	
	public void ajoutOperation(LocalDateTime actu, String desc) {
		this.operations.put(actu, desc);
	}
	
	public TreeMap<LocalDateTime, String> getOperations() {
		return operations;
	}

	public boolean isDa() {
		return da;
	}

	public void setDa(boolean da) {
		this.da = da;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Compte(Client client, String codeagence, float solde, boolean da) {
		
		this.client=client;
		this.solde=solde;
		this.codeagence=codeagence;
		this.da=da;
		active=true;
	}
	
	public void impInfosCompte() {
		System.out.print("Compte : "+nmcompte+". Code agence : "+codeagence+". Solde : "+solde+". Découvert autorisé :  ");
		if (this.da==true) System.out.println("Oui.");
		if (this.da==false) System.out.println("Non.");
	}
	
	static String GetRandomNumAcc()  { 
		  
        String NumericString = "0123456789"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(13); 
        
 
        for (int i = 0; i < 13; i++) { 
  
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
	
	public String Soldesmiley() {
		String smiley=":-)";
		if ((this.getSolde()) <0 ) smiley=":-(";
		return smiley;	
	}

	public void calculFrais() {
	}

	public String getCodeagence() {
		return codeagence;
	}
	public void setCodeagence(String codeagence) {
		this.codeagence = codeagence;
	}
	public String getNmcompte() {
		return nmcompte;
	}
	public void setNmcompte(String nmcompte) {
		this.nmcompte = nmcompte;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public float getFa() {
		return fa;
	}

	public boolean getDA() {
		return da;
	}

	

	
}
