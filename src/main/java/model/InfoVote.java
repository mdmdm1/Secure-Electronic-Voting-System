package model;

public class InfoVote {
	private int id;
	private String iden;
	private String nom;
	private String prenom;
	private String date;
	private String bulletinvote;
	public InfoVote() {}
	public InfoVote(int id, String iden,String nom, String prenom, String date, String bulletinvote) {
		super();
		this.id = id;
		this.iden = iden;
		this.prenom = prenom;
		this.nom=nom;
		this.date = date;
		this.bulletinvote = bulletinvote;
	}
	public InfoVote(String iden,String nom, String prenom, String date, String bulletinvote) {
		super();
		this.iden = iden;
		this.prenom = prenom;
		this.nom=nom;
		this.date = date;
		this.bulletinvote = bulletinvote;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIden() {
		return iden;
	}
	public void setIden(String iden) {
		this.iden = iden;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getBulletinvote() {
		return bulletinvote;
	}
	public void setBulletinvote(String bulletinvote) {
		this.bulletinvote = bulletinvote;
	}
	
	
}
