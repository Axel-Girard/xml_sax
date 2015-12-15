package sax;

import java.util.ArrayList;

public class Conference {
	private ArrayList<Integer> annees;
	private String titre;
	
	Conference(String titre){
		this.titre = titre;
		annees = new ArrayList<Integer>();
	}
	
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
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
}
