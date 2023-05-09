package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Collegamento;



public class ArtsmiaDAO {

	public List<ArtObject> listObjects() {
		
		String sql = "SELECT * from objects";
		List<ArtObject> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				ArtObject artObj = new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
						res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
						res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
						res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title"));
				
				result.add(artObj);
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Collegamento> trovaCollegamenti(){
		
		String sql = "SELECT ex1.object_id AS id1, ex2.object_id AS id2, COUNT(*) AS c "
				+ "FROM exhibition_objects AS ex1, exhibition_objects AS ex2 "
				+ "WHERE ex1.exhibition_id = ex2.exhibition_id AND ex1.object_id > ex2.object_id "
				+ "GROUP BY id1";
		
		List<Collegamento> result = new ArrayList<Collegamento>();
		List<ArtObject> obj = listObjects();
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				int id1 = res.getInt("id1");
				int id2 = res.getInt("id2");
				ArtObject o1 = null;
				ArtObject o2 = null;
				int count = 0;
				
				while(obj.size()>count) {
					
					if(id1 == obj.get(count).getId()) {
						o1 = obj.get(count);
					}
					else if(id2 == obj.get(count).getId()) {
						o2 = obj.get(count);
					}
					count++;
				}
				
				Collegamento coll = new Collegamento(o1, o2, res.getInt("c"));
				
				result.add(coll);
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public Map<Integer,ArtObject> listObjects2() {
		
		String sql = "SELECT * from objects";
		Map<Integer, ArtObject> result = new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				ArtObject artObj = new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
						res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
						res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
						res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title"));
				
				result.put(artObj.getId(), artObj);
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
