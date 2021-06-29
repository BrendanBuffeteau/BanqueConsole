
public class LivretA extends Compte{
	
	public void calculFrais() {
		fa=(float) (25f+(this.solde*0.1));
	}
	
	public LivretA(Client client,String codeagence, float solde, boolean da) {
		super(client,codeagence,solde,da);
		}
	}


