package dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.DAOUtilisateur;
import model.Utilisateur;
import model.Client;
import model.Admin;
import model.Site;

public class DAOClientJDBC implements DAOUtilisateur {


	public void insert(Client c) {

		try (
				Connection connect=Site.getInstance().getConnection();
				PreparedStatement ps = connect.prepareStatement("INSERT INTO client (login, password, adresseMail) VALUES (?,?,?)");
				)
		{
			ps.setString(1,  c.getLogin());
			ps.setString(2,  c.getPassword());
			ps.setString(3,  c.getAdresseMail());

			ps.executeUpdate();
			System.out.println("Client ajouté à la base de données");
		}
		catch(Exception e) {System.out.println("Erreur -> Client non ajouté");}
	}


	public Client selectByAdresseMail(String adresseMail) {
		Client c=null;

		try (
				Connection connect=Site.getInstance().getConnection();
				PreparedStatement ps = connect.prepareStatement("SELECT * FROM client WHERE adresseMail=?");
				)    {
			ps.setString(1,  adresseMail);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				c = new Client(rs.getInt("id"),rs.getString("login"),rs.getString("password"),rs.getString("adresseMail"));
			}
		}
		catch(Exception e) {System.out.println("Erreur -> Vérification de l'adresse mail ne marche pas");}
		return c;
	}
	public List<Utilisateur> selectAll() {
		//nope
		return null;
	}

	public void update(Client c) {
		//plus tard
	}

	public void delete(Integer adresseMail) {
		//plus tard
	}

	public Client checkConnect(String login, String password) {
		Client c = null;
		try (
				Connection connect=Site.getInstance().getConnection();
				PreparedStatement ps = connect.prepareStatement("SELECT * FROM client WHERE login=? and password=?");
				)    {

			ps.setString(1,  login);
			ps.setString(2,  password);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				c = new Client(rs.getInt("id"),rs.getString("login"),rs.getString("password"),rs.getString("adresseMail"));}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erreur -> Connexion impossible");}
		return c;
	}

	@Override
	public Client selectById(Integer id) {
		//Pas recherche de client via l'id ..
		return null;
	}

	public void inscription(String login, String password, String adresseMail) {

		try (
				Connection connect=Site.getInstance().getConnection();
				PreparedStatement ps = connect.prepareStatement("INSERT INTO client (login,password,adresseMail) VALUES (?,?,?)");
				)
		{
			ps.setString(1,  login);
			ps.setString(2,  password);
			ps.setString(3,  adresseMail);

			ps.executeUpdate();
			System.out.println("Client ajouté à la base de données");
		}
		catch(Exception e) {System.out.println("Erreur -> Client non ajouté");}
	}

	public Client checkMail(String mail) {
		Client c = null;
		try (
				Connection connect=Site.getInstance().getConnection();
				PreparedStatement ps = connect.prepareStatement("SELECT * FROM client WHERE adresseMail=?");
				)    {
			ps.setString(1,  mail);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				c = new Client(rs.getInt("id"),rs.getString("login"),rs.getString("password"),rs.getString("adresseMail"));}
		}
		catch(Exception e) {System.out.println("Erreur -> trycatch checkMail DAOClient");}
		return c;
	}


	@Override
	public Client selectByLoginPassword(String login, String password) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void insert(Utilisateur t) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update(Utilisateur t) {
		// TODO Auto-generated method stub
		
	}
}
