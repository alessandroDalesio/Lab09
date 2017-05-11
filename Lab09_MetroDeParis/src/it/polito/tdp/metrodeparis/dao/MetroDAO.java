package it.polito.tdp.metrodeparis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javadocmd.simplelatlng.LatLng;


import it.polito.tdp.metrodeparis.model.Connessione;
import it.polito.tdp.metrodeparis.model.Fermata;
import it.polito.tdp.metrodeparis.model.Linea;

public class MetroDAO {

	public List<Fermata> getAllFermate() {

		final String sql = "SELECT id_fermata, nome, coordx, coordy FROM fermata ORDER BY nome ASC";
		List<Fermata> fermate = new ArrayList<Fermata>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Fermata f = new Fermata(rs.getInt("id_Fermata"), rs.getString("nome"), new LatLng(rs.getDouble("coordx"), rs.getDouble("coordy")));
				fermate.add(f);
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

		return fermate;
	}
	
	
	public List<Linea> getAllLinee() {

		final String sql = "SELECT id_linea,nome,velocita,intervallo FROM linea ";
		List<Linea> linee = new ArrayList<Linea>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Linea l = new Linea(rs.getInt("id_linea"), rs.getString("nome"), rs.getDouble("velocita"), rs.getDouble("intervallo"));
				linee.add(l);
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

		return linee;
	}
	
	
	public List<Connessione> getAllConnessione() {

		final String sql = "SELECT id_connessione, id_linea, id_stazP, id_stazA FROM connessione";
		
		List<Connessione> connessioni = new ArrayList<Connessione>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Connessione c = new Connessione(rs.getInt("id_connessione"), rs.getInt("id_linea"), rs.getInt("id_stazP"), rs.getInt("id_stazA"));
				connessioni.add(c);
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

		return connessioni;
	}
	
	
	public List<Fermata> getVicini(Fermata f)
	{
		final String sql = "SELECT distinct id_fermata, nome, coordX, coordY FROM fermata, connessione WHERE connessione.id_stazP=? AND connessione.id_stazA=fermata.id_fermata ";

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, f.getIdFermata());
			
			ResultSet res = st.executeQuery() ;
			
			List<Fermata> list = new ArrayList<>() ;
			
			while(res.next()) {
				Fermata nuova= new Fermata(res.getInt("id_Fermata"), res.getString("nome"), new LatLng(res.getDouble("coordx"), res.getDouble("coordy"))) ;
				list.add(nuova);
			}
			
			res.close();
			conn.close();
			
			return list ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	
	public double getVelocita(Connessione c1, Connessione c2) {

		final String sql = "SELECT distinct connessione.id_connessione, connessione.id_linea, id_stazP, id_stazA, velocita FROM  connessione, linea  WHERE connessione.id_stazP=? AND connessione.id_stazA=? AND connessione.id_linea=linea.id_linea";
		double velox=-1;
		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, c1.getStazA());
			st.setInt(2, c2.getStazP());
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				velox= rs.getDouble("velocita");
			}
			

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

		return velox;
	}
	
	
	
	
	
	

}
