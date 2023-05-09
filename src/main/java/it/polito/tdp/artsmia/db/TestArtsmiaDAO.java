package it.polito.tdp.artsmia.db;

import java.util.List;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Collegamento;

public class TestArtsmiaDAO {

	public static void main(String[] args) {

		ArtsmiaDAO dao = new ArtsmiaDAO();
			
		List<ArtObject> objects = dao.listObjects();
		System.out.println(objects.get(0));
		System.out.println(objects.size());
		
		List<Collegamento> coll = dao.trovaCollegamenti();
		System.out.println(coll.size());
		
		
	}

}
