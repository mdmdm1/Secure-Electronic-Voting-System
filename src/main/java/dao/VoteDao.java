package dao;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import metier.Ivote;
import model.InfoVote;
import securite.Rsa;

public class VoteDao implements Ivote{
	Rsa Al1= new Rsa();
	Rsa Al2= new Rsa();
	public static String PrC1="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIseRFlw0AmUmot9zYZ2SDq87YGjMm6BTjiMZRGMfx5TRDucNmxy28dIz8/8u50RESCngP26B5qYr6SlVgsXzgvt2FByvZXuc1wgCel7O86Zc3/waYzuwFi0F68l31DVBnQG2qxacpLqG2n4sPbwzaz/l1phGI6rpA0Yu3SSpQqdAgMBAAECgYBM4AnW/t8HHEYWZQtIjV42jyYTqBH7PJrz5qkG/INWR58B7b2pSgMm6ZZkkX0/OyoiyE0gaEdBZ7RPoEme0aW64z6gRZCND1HSg8gsudfWDGKtrxPR8Q4gkgOHwe/i0pFWpQ+b7WOR0TTHG5xwN1PTvMxYb+q1N3GNuJx0dg1ihQJBAL15H8jjIO0L/+NskwKLds3KUuMeH8TLK/TkbpmNtCp+5L0UZU+kr9oWeMlzLoHJ65RwnjRA1G2h3fdZXgN7obcCQQC79vhAmxgZlIgdvzckWIxNPh/JDEekoX0iIr91sZv4XS4hZvtZN0ovaHZ+etL1/OJVf6rKeI7b/izEqpJx8KZLAkAcD5Y/gDPeJ4rMOcZD+DeuHqrL7Cg0Uwq9iB9BKpVmPcjOn/ipJPDOUcmtvBtFKYx0PYGQp5FOc0yWV2vvBB5dAkAnKpcgHzEuY1zZlfaPhEP39HH7GqvzChNhAYyToaa2YfQy0ZqeRH8Y0dOfF0jOKRugZTHZbrWV8aiycjiBtXtxAkEAnKtMbaZouN01q67o86UOchvQ+guNQH3EMPHEgIjdPT1re0g8NyPO3dx/Ih6pve4TZfFXxsJpXisxRZpx6QC62A==";   
    public static String PuC1="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCLHkRZcNAJlJqLfc2Gdkg6vO2BozJugU44jGURjH8eU0Q7nDZsctvHSM/P/LudEREgp4D9ugeamK+kpVYLF84L7dhQcr2V7nNcIAnpezvOmXN/8GmM7sBYtBevJd9Q1QZ0BtqsWnKS6htp+LD28M2s/5daYRiOq6QNGLt0kqUKnQIDAQAB";
    public static String PrC2="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIlqxoXn3IogZJMYZ5AiqJTZC9uyWVesekGyDH9BE3vELTiyGa+kcB4WLeppIymPq1uisPLOxNLVs1UaK4KdSmemDkDPvAyYO5mqqCU6T85b/5F3GowZo8cPhjiEtdGDdigLvVXFp66yl+mGYn0VnOObLvrNECMkdCAhoxfirr2HAgMBAAECgYARlfOQAWss1hZo6K+gN39BhUFKPVnHqY//xkxpIKfgp+ttnQVCgBXFE+uOWyQf1n4EfR6OBD4WCTkpA+9biMuTpesm63gW73BHg2TOedtF0cAv+ctEmAwmsU2RTuFOTad3YdRhaHN02oNb/A24zA4cbUV/dkRz3xuHiTggho6w+QJBAM3JbnAydZ2DFshBSYkJActtqr1sCVqUnrtNj6CoOd5z3w8FwvkLc4CXizFcec8jv9bCwcl5fSZYgpmxGFecHGUCQQCq8pi52yLSVOovhlTxG9T9DMwElCg8F0jiNVg0KIn4pnBiRplWH1nADQ0Q70cDeiHc90PbZEfPjCK2YbjE4KV7AkEAq1gVBIZ/N+92gLcbq2Y8gE2H5MCX6TvmDr694F8yD+8awz9g4EyQl3mXBDqjkb2Kdh8ji8YEVnFMDPxP8GKjoQJAQx/7hI9dvwWE56Bx3jM1kR/jRLEAk4f5IIod1m1MsAx6+LwMHds1nMoDJXEvi1f70A5v4MghwoeeYctBiF1F3QJAJFBL6Pbq0r/4vaUcb/ty7mjlrHU72tu9ClyKtbHhFrSM6g7qs0Oo4mrHWaTJV+j7IRe2Kyv7hiTjYT9SiqSFfA==";
    public static String PuC2="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCJasaF59yKIGSTGGeQIqiU2QvbsllXrHpBsgx/QRN7xC04shmvpHAeFi3qaSMpj6tborDyzsTS1bNVGiuCnUpnpg5Az7wMmDuZqqglOk/OW/+RdxqMGaPHD4Y4hLXRg3YoC71VxaeuspfphmJ9FZzjmy76zRAjJHQgIaMX4q69hwIDAQAB";
    
	
   @Override
	public boolean rech1(String iden) throws Exception{
	   Collection<InfoVote> infoVotes=findAll(0);
	   for(InfoVote i : infoVotes) {
		   if(i.getIden().equals(iden))
			   return true;
	   }
	   return false ;
		
	}
   @Override
   public boolean rech2(InfoVote v) throws Exception{
	   Collection<InfoVote> infoVotes=findAll(1);
	   for(InfoVote i : infoVotes) {
		   if(i.getIden().equals(v.getIden()))
			   return true;
	   }
	   return false ;
		
	}
	
	/*@Override
	public InfoVote findOne(String iden,int i)throws Exception {
		Al1.initfromString(PrC1, PuC1);
		String k=Al1.encrypt(iden);
		Connection conn =DataBase.getInstance().getConnection();
		
		if(i==0){
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM infovotec1 WHERE iden = ?");
		stmt.setString(1, k);
		ResultSet result = stmt.executeQuery();
		if (result.next()) {
			String nom = result.getString("nom");
			String prenom = result.getString("prenom");
			String date = result.getString("date");
			String bulletinvote = result.getString("bulletinvote");
			String iden1=result.getString("iden");
			int id= Integer.parseInt(result.getString("id"));
			return new InfoVote(id, iden1, nom, prenom,date, bulletinvote);
			}else
				return null;
		}else{
		PreparedStatement stmt = (PreparedStatement) conn.prepareStatement("SELECT * FROM infovotec2 WHERE iden = ?");
		stmt.setString(1, k);
		ResultSet result = stmt.executeQuery();
		if (result.next()) {
			String nom = result.getString("nom");
			String prenom = result.getString("prenom");
			String date = result.getString("date");
			String bulletinvote = result.getString("bulletinvote");
			String iden1=result.getString("iden");
			int id= Integer.parseInt(result.getString("id"));
			return new InfoVote(id, iden1, nom, prenom,date, bulletinvote);
			}else
				return null;
			}
		
	}*/

	@Override
	public Collection<InfoVote> findAll(int i) throws Exception{
		// TODO Auto-generated method stub
		Collection<InfoVote> infoVotes = new ArrayList<InfoVote>();
		if(i==0) {
			try {

				Connection conn = DataBase.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM infovotec1");
				ResultSet result = stmt.executeQuery();
				while(result.next()) {
					int id = result.getInt("id");
					String nom = result.getString("nom");
					String prenom = result.getString("prenom");
					String bulletinvote = result.getString("bulletinvote");
					String date = result.getString("date");
					String iden = result.getString("iden");
					InfoVote infoVote = new InfoVote(id, iden, nom, prenom,date, bulletinvote);
					infoVote=dechiffrerC1(infoVote);
					infoVotes.add(infoVote);
				}
			} catch (SQLException ex) {
				System.out.println("Erreur SQL : " + ex.getMessage());
			}
		}else if(i==1) {
			try {

				Connection conn = DataBase.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM infovotec2");
				ResultSet result = stmt.executeQuery();
				while(result.next()) {
					int id = result.getInt("id"); 
					String nom = result.getString("nom");
					String prenom = result.getString("prenom");
					String bulletinvote = result.getString("bulletinvote");
					String date = result.getString("date");
					String iden = result.getString("iden");
					InfoVote infoVote = new InfoVote(id, iden, nom, prenom,date, bulletinvote);
					infoVote=dechiffrerC2(infoVote);
					infoVotes.add(infoVote);
				}
			} catch (SQLException ex) {
				System.out.println("Erreur SQL : " + ex.getMessage());
			}
			
		}
		return infoVotes;
		
	}

	@Override
	public void add(InfoVote vote) {
		// TODO Auto-generated method stub
			try {

				Connection conn = DataBase.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement("INSERT INTO infovotec1 VALUES (NULL, ?, ?, ?, ?, ?)");
				stmt.setString(1, vote.getIden());
				stmt.setString(2, vote.getNom());
				stmt.setString(3, vote.getPrenom());
				stmt.setString(4, vote.getDate());
				stmt.setString(5, vote.getBulletinvote());
				stmt.execute();
			} catch (SQLException ex) {
				System.out.println("Erreur SQL : " + ex.getMessage());
			}
			
		
	}

	@Override
	public void delete(int id1){
		// TODO Auto-generated method stub
		try {
		Connection conn = DataBase.getInstance().getConnection();
		PreparedStatement stmt = conn.prepareStatement("DELETE FROM infovotec1 WHERE id = ?");
		stmt.setInt(1, id1);
		stmt.executeUpdate();
		}catch (SQLException ex) {
			System.out.println("Erreur SQL : " + ex.getMessage());
		}
		
		
		
	}

	@Override
	public void envoyerC2(InfoVote vote)throws Exception {
		// TODO Auto-generated method stub
		try {
			Connection conn = DataBase.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO infovotec2 VALUES (NULL, ?, ?, ?, ?, ?)");
			stmt.setString(1, vote.getIden());
			stmt.setString(2, vote.getNom());
			stmt.setString(3, vote.getPrenom());
			stmt.setString(4, vote.getDate());
			stmt.setString(5, vote.getBulletinvote());
			stmt.execute();
			
		} catch (SQLException ex) {
			System.out.println("Erreur SQL : " + ex.getMessage());
		}
	}
	

	@Override
	public InfoVote chiffrer(InfoVote vote)throws Exception {
		// TODO Auto-generated method stub
		Al1.initfromString(PrC1, PuC1);
		Al2.initfromString(PrC2, PuC2);
		vote.setBulletinvote(Al2.encrypt(vote.getBulletinvote()));
		vote.setPrenom(Al1.encrypt(vote.getPrenom()));
		vote.setNom(Al1.encrypt(vote.getNom()));
		vote.setDate(Al1.encrypt(vote.getDate()));
		vote.setIden(Al1.encrypt(vote.getIden()));
		return vote;
	}

	@Override
	public InfoVote dechiffrerC1(InfoVote vote)throws Exception {
		// TODO Auto-generated method stub
		Al1.initfromString(PrC1, PuC1);
		vote.setPrenom(Al1.decrypt(vote.getPrenom()));
		vote.setNom(Al1.decrypt(vote.getNom()));
		vote.setDate(Al1.decrypt(vote.getDate()));
		vote.setIden(Al1.decrypt(vote.getIden()));
		return vote;
	}
	public InfoVote dechiffrerC2(InfoVote vote)throws Exception {
		// TODO Auto-generated method stub
		Al2.initfromString(PrC2, PuC2);
		vote.setBulletinvote(Al2.decrypt(vote.getBulletinvote()));
		return vote;
	}
	/*public static void main(String[]args) {
		//fhjsnfkd
		try {
			InfoVote vote = new InfoVote();
			vote = findOne("fhjsnfkd",0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/ 
}
