package ci.esatic.trackingmobile;

public class RegistrationModelMaint {

    /*@SerializedName("billno")*/
    private String numero_facture;

    /*@SerializedName("litre")*/
    private String saisie_reparation;

    /*@SerializedName("amount")*/
    private Float montant;

    public RegistrationModelMaint(String numero_facture, String saisie_reparation, Float montant) {
        this.numero_facture = numero_facture;
        this.saisie_reparation = saisie_reparation;
        this.montant = montant;
    }

    @Override
    public String toString() {
        return "RegistrationModelMaint{" +
                "numero_facture='" + numero_facture + '\'' +
                ", saisie_reparation='" + saisie_reparation + '\'' +
                ", montant=" + montant +
                '}';
    }
}
