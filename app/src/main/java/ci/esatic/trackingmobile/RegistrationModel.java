package ci.esatic.trackingmobile;

import com.google.gson.annotations.SerializedName;

public class RegistrationModel {
    /*@SerializedName("billno")*/
    private String numero_facture;

    /*@SerializedName("litre")*/
    private Float nombre_litre;

    /*@SerializedName("amount")*/
    private Float montant;


    public RegistrationModel(String numero_facture, Float nombre_litre, Float montant) {
        this.numero_facture = numero_facture;
        this.nombre_litre = nombre_litre;
        this.montant = montant;
    }

    @Override
    public String toString() {
        return "RegistrationModel{" +
                "numero_facture='" + numero_facture + '\'' +
                ", nombre_litre=" + nombre_litre +
                ", montant=" + montant +
                '}';
    }
}

