package dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.DAOVille;
import model.Site;
import model.Ville;

public class DAOVilleJDBC implements DAOVille {

    @Override
    public void insert(Ville t) {
        //pas utile

    }

    @Override
    public Ville selectById(Integer id) {

        Ville v=null;

        try
        (
                Connection connect=Site.getInstance().getConnection();
                PreparedStatement ps=connect.prepareStatement("SELECT * from ville where id=?"); 
        )
        {
            ps.setInt(1, id);
            ResultSet rs= ps.executeQuery();
            while(rs.next()) 
            {
                v = new Ville(rs.getInt("id"),rs.getString("nom"),rs.getDouble("longitude"),rs.getDouble("latitude"));
            }

        }catch (Exception e) {e.printStackTrace();}


        return v;
    }

    @Override
    public List<Ville> selectAll() {

        List<Ville> villes = new ArrayList<Ville>();
        Ville v=null;

        try
        (
                Connection connect=Site.getInstance().getConnection();
                PreparedStatement ps=connect.prepareStatement("SELECT * from ville"); 
        )
        {
            ResultSet rs= ps.executeQuery();
            while(rs.next()) 
            {
                v = new Ville(rs.getInt("id"),rs.getString("nom"),rs.getDouble("longitude"),rs.getDouble("latitude"));
                villes.add(v);
            }

        }catch (Exception e) {e.printStackTrace();}

        return villes;
    }

    @Override
    public void update(Ville t) {
        // pas utile
    }

    @Override
    public void delete(Integer id) {
        // pas utile
    }
    
    public Ville selectByNom(String nom) {

        Ville v=null;

        try
        (
                Connection connect=Site.getInstance().getConnection();
                PreparedStatement ps=connect.prepareStatement("SELECT * from ville where nom=?"); 
        )
        {
            ps.setString(1, nom);
            ResultSet rs= ps.executeQuery();
            while(rs.next()) 
            {
                v = new Ville(rs.getInt("id"),rs.getString("nom"),rs.getDouble("longitude"),rs.getDouble("latitude"));
            }

        }catch (Exception e) {e.printStackTrace();}

        return v;
    }
}
