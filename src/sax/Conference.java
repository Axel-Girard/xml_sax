package sax;

import java.util.ArrayList;

public class Conference {
	private int tri[];
	private ArrayList<Integer> annees;
	private ArrayList<String> acronymes;
	private String titre;
	
	Conference(String titre){
		this.titre = titre;
		annees = new ArrayList<Integer>();
		acronymes = new ArrayList<String>();
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
	public void setcronymes(ArrayList<String> acronymes) {
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
			System.out.println(acronymes.get(tri[i]));
		}
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
