
public class PlanEpLog extends Compte {
	
	public PlanEpLog(Client client,String codeagence, float solde, boolean da) {
		super(client,codeagence,solde,da);
	}

	public void calculFrais() {
		fa=(float) (25f+(this.solde*0.025));
	}
	
	
}
