package boxing;

public class Competition {

	private String name;
	private String title;
	private String date;
	private Category[] categories = new Category[5];
	
	
	public Competition(String name, String title, String date) {
		this.name = name;
		this.title = title;
		this.date = date;
	}

	protected String getName() {
		return name;
	}

	protected String getTitle() {
		return title;
	}

	protected String getDate() {
		return date;
	}

	protected Category[] getCategories() {
		return categories;
	}
	
	public void addCategory(Category newCategory) {
		int counter = 0;
		for (int i = 0; i < this.categories.length; i++) {
			if (this.categories[i] != null && this.categories[i].getName() == newCategory.getName()) {
				System.out.println(this.name + " already has " + newCategory.getName() + ".");
				return;
			}
			if (this.categories[i] == null) {
				this.categories[i] = newCategory;
				return;
			}
			counter++;
		}
		if (counter == this.categories.length) {
			System.out.println("No place for another category in " + this.name + ".");
		}
	}
	
	
	public void resolveCompetition() {
		System.out.println("THE " + this.name.toUpperCase() + " BEGINS!!!");
		int counter = 0;
		for (int i = 0; i < this.categories.length; i++) {
			if (this.categories[i] != null) {
				this.categories[i].resolveCategory();
			} else {
				return;
			}
		}
		if (counter == 0) {
			System.out.println("No categories to resolve in " + this.name + ". Add categories with fighters to perform the competition.");
		}
	}
}
