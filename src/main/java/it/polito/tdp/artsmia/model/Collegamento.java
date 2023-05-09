package it.polito.tdp.artsmia.model;

public class Collegamento {
	
	public ArtObject o1;
	public ArtObject o2;
	public int count;
	
	public Collegamento(ArtObject o1, ArtObject o2, int count) {
		super();
		this.o1 = o1;
		this.o2 = o2;
		this.count = count;
	}

	public ArtObject getO1() {
		return o1;
	}

	public void setO1(ArtObject o1) {
		this.o1 = o1;
	}

	public ArtObject getO2() {
		return o2;
	}

	public void setO2(ArtObject o2) {
		this.o2 = o2;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Collegamento [o1=" + o1 + ", o2=" + o2 + ", count=" + count + "]";
	}
	
	

	
}
