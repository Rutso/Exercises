package musicFestival;

public class MusicFestivalDemo {

	public static void main(String[] args) {

		//1. Create several musicians, bands and songs:
		Musician freddie = new Vocalist("Freddie Mercury");
		Musician john = new Bassist("John Deacon");
		Musician brian = new Guitarist("Brian May");
		Musician roger = new Drummer("Roger Taylor");
		Band queen = new Band("Queen", 4, 5);
		queen.addBandMember(freddie);
		queen.addBandMember(john);
		queen.addBandMember(brian);
		queen.addBandMember(roger);
		Song wwry = new Song("We Will Rock You", "Buddy you'r a boy make a big noise...");
		Song tymd = new Song("Tie Your Mother Down", "Get your party down. Get your pigtail down. Get your heart beatin' baby...");
		queen.addSongToSetlist(wwry);
		queen.addSongToSetlist(tymd);
		
		Musician robert = new Vocalist("Robert Palnt");
		Musician johnPJ = new Bassist("John Paul Jones");
		Musician jimmy = new Guitarist("Jimmy Page");
		Musician johnB = new Drummer("John Bonham");
		Band ledZep = new Band("Led Zeppelin", 4, 5);
		ledZep.addBandMember(robert);
		ledZep.addBandMember(johnPJ);
		ledZep.addBandMember(jimmy);
		ledZep.addBandMember(johnB);
		Song wll = new Song("Whole Lotta Love", "You need coolon', baby, I'm not foolin'...");
		Song rnr = new Song("Rock And Roll", "It's been a long time since I rock and rolled,...");
		ledZep.addSongToSetlist(wll);
		ledZep.addSongToSetlist(rnr);
				
		Musician billy = new Bassist("Billy Cox");
		Musician jimi = new Guitarist("Jimi Hendrix");
		Musician mitch = new Drummer("Mitch Mitchell");
		Band experience = new Band("The Jimi Hendrix Experience", 3, 5);
		experience.addBandMember(billy);
		experience.addBandMember(jimi);
		experience.addBandMember(mitch);
		Song ssb = new Song("Star Spangled Banner", "");
		experience.addSongToSetlist(ssb);
		
		//2. Create festival:
		Festival godsOfRock = new Festival("Gods of Rock Festival", "15.08.2069", "White Lake, New York", 3);
		godsOfRock.getFestivalInfo();
		System.out.println();
		
		//3. Create acts and add them to the festival:
		Act one = new Act(queen, "18:30", "19:30");
		Act two = new Act(ledZep, "20:00", "21:00");
		Act three = new Act(experience, "21:30", "23:00");
		
		godsOfRock.addAct(one);
		godsOfRock.addAct(two);
		godsOfRock.addAct(three);

		godsOfRock.getFestivalInfo();
		System.out.println();
		
		//4. Start the Festival :) !!!
		godsOfRock.startTheFestival();
		
		
		
		
	}

}
