package it.unitn.disi.entities.locations;

import java.util.HashMap;

public class LocationContainer {

	private final Regione[] regioni;
	private final HashMap<Integer, Regione> regioniHash;
	private final HashMap<Integer, Provincia> provinceHash;
	private final HashMap<Integer, Comune> comuniHash;

	public LocationContainer(Regione[] regioni, HashMap<Integer, Regione> regioniHash, HashMap<Integer, Provincia> provinceHash, HashMap<Integer, Comune> comuniHash) {
		this.regioni = regioni;
		this.regioniHash = regioniHash;
		this.provinceHash = provinceHash;
		this.comuniHash = comuniHash;
	}

	// <editor-fold defaultstate="collapsed" desc="Getters custom">
	public Regione getRegione(int idRegione) {
		return regioniHash.get(idRegione);
	}

	public Provincia getProvincia(int idProvincia) {
		return provinceHash.get(idProvincia);
	}

	public Comune getComune(int idComune) {
		return comuniHash.get(idComune);
	}

	public Regione[] getRegioni() {
		return regioni;
	}

	public Provincia[] getProvinceByIdRegione(int idRegione) {
		return getRegione(idRegione).getProvince();
	}

	public Comune[] getComuniByIdProvincia(int idProvincia) {
		return getProvincia(idProvincia).getComuni();
	}
	// </editor-fold>

}

/*
<%
	ServletContext sc = request.getServletContext();
	sc.getAttribute("attributeName");
%>

${applicationScope['attributeNames']}
 */
