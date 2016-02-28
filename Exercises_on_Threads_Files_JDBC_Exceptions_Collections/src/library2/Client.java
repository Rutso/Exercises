package library2;

public class Client extends Thread {

	//Fields:
	private String name;
	private Library favoriteLibrary;
	private String favoriteReading;

	//Constructor:
	public Client(String name) {
		this.name = name;
	}

	//Getters:
	public String getClientName() {
		return name;
	}
	
	//Setters:
	public void setLibrary(Library favoriteLibrary) {
		this.favoriteLibrary = favoriteLibrary;
	}
	public void setFavoriteReading(String favoriteReading) {
		this.favoriteReading = favoriteReading;
	}
	
	//Overriden methods:
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
	@Override
	public void run() {
		
		
		
		
		try {
			takeReading();
			sleep(45000);
		} catch (InterruptedException | CantTakeHomeException e) {
			System.out.println(e.getMessage());
			return;
		}
		
		returnReading();
		
		
		
	}

	//Methods:
	public void takeReading() throws CantTakeHomeException {
		this.favoriteLibrary.takeReading(this.favoriteReading, this);
	}
	
	public void returnReading() {
		this.favoriteLibrary.returnReading(this.favoriteReading, this);
	}
	
}
