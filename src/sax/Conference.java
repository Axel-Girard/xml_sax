package sax;

import java.util.ArrayList;

public class Conference {
	private int tri[];
	private ArrayList<Integer> annees;
	private ArrayList<String> acronymes;
	private ArrayList<String> noms;
	private ArrayList<String> lieu;
	private ArrayList<String> statistiques;
	private ArrayList<Integer> nbArticles;
	private String titre;
	
	Conference(String titre){
		this.titre = titre;
		annees = new ArrayList<Integer>();
		acronymes = new ArrayList<String>();
		noms = new ArrayList<String>();
		lieu = new ArrayList<String>();
		statistiques = new ArrayList<String>();
		nbArticles = new ArrayList<Integer>();
	}

	// ----- ANNEES ------
	public ArrayList<Integer> getAnnees() {
		return annees;
	}
	public void setAnnees(ArrayList<Integer> annees) {
		this.annees = annees;
	}
	public void addAnnees(Integer...integers){
		for(Integer integer: integers){
			this.annees.add(integer);
		}
	}
	public void removeAnnees(Integer...integers){
		for(Integer integer: integers){
			this.annees.remove(integer);
		}
	}
	// ----- ACRONYMES ------
	public ArrayList<String> getAcronymes() {
		return acronymes;
	}
	public void setAcronymes(ArrayList<String> acronymes) {
		this.acronymes = acronymes;
	}
	public void addAcronymes(String...strings){
		for(String string: strings){
			this.acronymes.add(string);
		}
	}
	public void removeAcronymes(String...strings){
		for(String string: strings){
			this.acronymes.remove(string);
		}
	}
	// ----- NOMS ------
	public ArrayList<String> getNoms() {
		return noms;
	}
	public void setNoms(ArrayList<String> noms) {
		this.noms = noms;
	}
	public void addNoms(String...strings){
		for(String string: strings){
			this.noms.add(string);
		}
	}
	public void removeNoms(String...strings){
		for(String string: strings){
			this.noms.remove(string);
		}
	}
	// ----- NOMS ------
	public ArrayList<String> getLieu() {
		return lieu;
	}
	public void setLieu(ArrayList<String> lieu) {
		this.lieu = lieu;
	}
	public void addLieu(String...strings){
		for(String string: strings){
			this.lieu.add(string);
		}
	}
	public void removeLieu(String...strings){
		for(String string: strings){
			this.lieu.remove(string);
		}
	}
	// ----- STATS ------
	public ArrayList<String> getStatistique() {
		return statistiques;
	}
	public void setStatistique(ArrayList<String> statistique) {
		this.statistiques = statistique;
	}
	public void addStatistique(String...strings){
		for(String string: strings){
			this.statistiques.add(string);
		}
	}
	public void removeStatistique(String...strings){
		for(String string: strings){
			this.statistiques.remove(string);
		}
	}
	// ----- ANNEES ------
	public ArrayList<Integer> getNbArticles() {
		return nbArticles;
	}
	public void setNbArticles(ArrayList<Integer> nbArticles) {
		this.nbArticles = nbArticles;
	}
	public void addNbArticles(Integer...integers){
		for(Integer integer: integers){
			this.nbArticles.add(integer);
		}
	}
	public void removeNbArticles(Integer...integers){
		for(Integer integer: integers){
			this.nbArticles.remove(integer);
		}
	}
	// ----- TITRE ------
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}

	// ----- TRI -----
	public ArrayList<Integer> getSortedAnnees(){
		ArrayList<Integer> sorted = new ArrayList<Integer>();
		sortAnnees();
		for(int i = 0; i < tri.length; i++){
			sorted.add(annees.get(tri[i]));
			System.out.print(statistiques.get(tri[i]) + " ");
		}
		System.out.println();
		return sorted;
	}
	public void sortAnnees(){
		tri = new int[annees.size()];
		for(int i = 0; i < tri.length; i++){
			tri[i] = i;
		}
		int tmp;
		for(int i = 0; i < annees.size() - 1; i++){
			for(int j = 0; j < annees.size() - 1 - i; j++){
				if(annees.get(tri[j]) > annees.get(tri[j+1])){
					tmp = tri[j];
					tri[j]= tri[j+1];
					tri[j+1] = tmp;
				}
			}
		}
		sortAnneesIdentique();
	}
	public void sortAnneesIdentique(){
		int tmp;
		for(int i = 0; i < annees.size() - 1; i++){
			if(annees.get(tri[i]).equals(annees.get(tri[i+1]))){
				if(acronymes.get(tri[i]).compareTo(acronymes.get(tri[i+1])) > 0){
					tmp = tri[i];
					tri[i]= tri[i+1];
					tri[i+1] = tmp;
				}
			}
		}
	}
}
