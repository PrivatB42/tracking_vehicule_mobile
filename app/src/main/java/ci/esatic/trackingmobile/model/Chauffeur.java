package ci.esatic.trackingmobile.model;

import java.util.Date;

public class Chauffeur {
    private Integer id;
    private String nom;
    private String prenom;
    private Date date_naissance;
    private String numero_piece_identite;
    private String telephone;
    private String adresse;
    private Integer salaire;
    private String username;
    private String password;
    private String token;
    private Date createdAt;
    private Date updatedAt;

    public Chauffeur() {
    }

    @Override
    public String toString() {
        return "Chauffeur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", date_naissance=" + date_naissance +
                ", numero_piece_identite='" + numero_piece_identite + '\'' +
                ", telephone='" + telephone + '\'' +
                ", adresse='" + adresse + '\'' +
                ", salaire=" + salaire +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accessToken='" + token + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public Chauffeur(Integer id, String nom, String prenom, Date date_naissance, String numero_piece_identite, String telephone, String adresse, Integer salaire, String username, String password, String token, Date createdAt, Date updatedAt) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.numero_piece_identite = numero_piece_identite;
        this.telephone = telephone;
        this.adresse = adresse;
        this.salaire = salaire;
        this.username = username;
        this.password = password;
        this.token = token;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getNumero_piece_identite() {
        return numero_piece_identite;
    }

    public void setNumero_piece_identite(String numero_piece_identite) {
        this.numero_piece_identite = numero_piece_identite;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Integer getSalaire() {
        return salaire;
    }

    public void setSalaire(Integer salaire) {
        this.salaire = salaire;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
