package metier;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.ResultSet;
import java.util.Collection;


import model.InfoVote;

public interface Ivote {
	
	//public InfoVote findOne(String iden, int i)throws Exception;
	public Collection<InfoVote> findAll(int i)throws Exception;
	public void add(InfoVote vote);
	//public void update(InfoVote vote);
	public void delete(int id);
	public void envoyerC2(InfoVote vote)throws Exception;
	public InfoVote chiffrer(InfoVote vote)throws Exception;
	public InfoVote dechiffrerC2(InfoVote vote)throws Exception;
	public InfoVote dechiffrerC1(InfoVote vote)throws Exception;
	public boolean rech1(String iden) throws Exception;
	public boolean rech2(InfoVote v) throws Exception;

} 
