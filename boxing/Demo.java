package boxing;

public class Demo {

	public static void main(String[] args) {


		//1. Create boxers:
		Boxer tyson = new Boxer("Tyson Fury", 117);
		Boxer wladimir = new Boxer("Wladimir Klitschko", 112);
		Boxer alexander = new Boxer("Alexander Povetkin", 103);
		Boxer deontay = new Boxer("Deontay Wilder", 98);
		Boxer kubrat = new Boxer("Kubrat Pulev", 112);
		Boxer luis = new Boxer("Luis Ortiz", 100);
		Boxer bermane = new Boxer("Bermane Stiverne", 112);
		Boxer vyacheslav = new Boxer("Vyacheslav Glazkov", 102);

		//2. Create competition:
		Competition worldChmpionship = new Competition("World Professional Boxing Championship", "World Champion 2016", "16.09.2016");
		
		//3. Create category:
		Category heavyweight = new Category("heavyweight", 150, 90.7, worldChmpionship);

		//4. Add category to the competition:
		worldChmpionship.addCategory(heavyweight);
		
		//5. Add boxers to the category:
		heavyweight.addCompetitor(tyson);
		heavyweight.addCompetitor(wladimir);
		heavyweight.addCompetitor(alexander);
		heavyweight.addCompetitor(deontay);
		heavyweight.addCompetitor(kubrat);
		heavyweight.addCompetitor(tyson);
		heavyweight.addCompetitor(luis);
		heavyweight.addCompetitor(bermane);
		heavyweight.addCompetitor(vyacheslav);
		System.out.println();
		heavyweight.showCompetitors();
		System.out.println();
		
		
		
		//heavyweight.resolveCategory();
		//6. Resolve the competition:
		worldChmpionship.resolveCompetition();
		
		System.out.println();
		heavyweight.showCompetitors();
		System.out.println();
		
		
		Competition worldChmpionship2 = new Competition("World Professional Boxing Championship", "World Champion 2017", "16.09.2017");
		Category heavyweight2 = new Category("heavyweight", 150, 90.7, worldChmpionship2);
		heavyweight2.addCompetitor(tyson);
		heavyweight2.addCompetitor(wladimir);
		heavyweight2.addCompetitor(alexander);
		heavyweight2.addCompetitor(deontay);
		heavyweight2.addCompetitor(kubrat);
		heavyweight2.addCompetitor(tyson);
		heavyweight2.addCompetitor(luis);
		heavyweight2.addCompetitor(bermane);
		heavyweight2.addCompetitor(vyacheslav);
		worldChmpionship2.addCategory(heavyweight2);
		worldChmpionship2.resolveCompetition();
		
		
		System.out.println();
		heavyweight.showCompetitors();
		System.out.println();
	}

}
