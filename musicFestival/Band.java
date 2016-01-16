package musicFestival;

public class Band {

	private String name;
	private Musician[] bandMembers;
	private int freePlacesForBandMembers;
	private Song[] setList;
	private int freePlacesOnTheSetList;


	public Band(String name, int numberOfBandMembers, int songsToBePerformed) {
		this.name = name;
		
		if (numberOfBandMembers > 0) {
			this.freePlacesForBandMembers = numberOfBandMembers;
			this.bandMembers = new Musician[numberOfBandMembers];
		} else {
			this.freePlacesForBandMembers = 4;
			this.bandMembers = new Musician[numberOfBandMembers];
		}
		
		if (songsToBePerformed > 0) {
			this.freePlacesOnTheSetList = songsToBePerformed;
			this.setList = new Song[songsToBePerformed];
		} else {
			this.freePlacesOnTheSetList = 5;
			this.setList = new Song[songsToBePerformed];
		}
		
		
		
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public Musician[] getBandMembers() {
		return this.bandMembers;
	}
		
	public Song[] getSetList() {
		return this.setList;
	}

	public void addBandMember(Musician newBandMember) {
		if (newBandMember != null) {
			if (this.freePlacesForBandMembers > 0) {
				this.bandMembers[this.bandMembers.length - this.freePlacesForBandMembers] = newBandMember;
				this.bandMembers[this.bandMembers.length - this.freePlacesForBandMembers].setBand(this);
				this.freePlacesForBandMembers--;
				return;
			} else {
				System.out.println(this.name + " has no free place for a new band member. Sorry, " + newBandMember.getName() + ".");
			}
		} else {
			System.out.println("Null musician proposed for member of " + this.name + ".");
		}
	}
	
	public void addSongToSetlist(Song newSong) {
		if (newSong != null) {
			if (this.freePlacesOnTheSetList > 0) {
				this.setList[this.setList.length - this.freePlacesOnTheSetList] = newSong;
				this.freePlacesOnTheSetList--;
				return;
			} else {
				System.out.println(this.name + " has no free place for a new song in the setlist.");
			}
		} else {
			System.out.println("Null song proposed for the setlist of " + this.name + ".");
		}
	}
	
	public void displayBandInfo() {
		System.out.println("BAND: " + this.name);
		for (int i = 0; i < this.bandMembers.length; i++ ) {
			if (this.bandMembers[i] != null) {
				System.out.println(this.bandMembers[i].getName() + ": " + this.bandMembers[i].getInstrument());
			} else {
				return;
			}
		}
	}
	
	public void perform() {
		
		System.out.println("Please, welcome on the stage " + this.name + "!!!");
		for (int i = 0; i < this.setList.length; i++) {
			if (this.setList[i] != null) {
				System.out.println("Song " + (i+1) + ": " + this.setList[i].getName() + ".");
				for (int j = 0; j < this.bandMembers.length; j++) {
					if (this.bandMembers[j] != null) {
						this.bandMembers[j].play();
						if (this.bandMembers[j] instanceof Vocalist) {
							System.out.println(this.setList[i].getLyrics());
						}
					} else {
						break;
					}
				
				}
				System.out.println();
			} else {
				break;
			}
			
		}

		
	}
	
	
	
}
