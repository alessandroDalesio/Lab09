package it.polito.tdp.metrodeparis.model;

public class Connessione {

	private int id;
	private int linea;
	private int stazP;
	private int stazA;
	
	
	public Connessione(int id, int linea, int stazP, int stazA) {
		super();
		this.id = id;
		this.linea = linea;
		this.stazP = stazP;
		this.stazA = stazA;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the linea
	 */
	public int getLinea() {
		return linea;
	}


	/**
	 * @param linea the linea to set
	 */
	public void setLinea(int linea) {
		this.linea = linea;
	}


	/**
	 * @return the stazP
	 */
	public int getStazP() {
		return stazP;
	}


	/**
	 * @param stazP the stazP to set
	 */
	public void setStazP(int stazP) {
		this.stazP = stazP;
	}


	/**
	 * @return the stazA
	 */
	public int getStazA() {
		return stazA;
	}


	/**
	 * @param stazA the stazA to set
	 */
	public void setStazA(int stazA) {
		this.stazA = stazA;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Connessione [id=" + id + ", linea=" + linea + ", stazP=" + stazP + ", stazA=" + stazA + "]";
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Connessione other = (Connessione) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
