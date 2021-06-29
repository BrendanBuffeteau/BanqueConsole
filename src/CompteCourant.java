
public class CompteCourant extends Compte {
	
	public void calculFrais() {
		fa=25f;
	}
	public CompteCourant(Client client,String codeagence, float solde, boolean da) {
		super(client,codeagence,solde,da);
		}
	
}
