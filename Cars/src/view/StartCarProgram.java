//Carl Phillips
package view;

import java.util.List;
import java.util.Scanner;

import controller.ListCarHelper;
import model.ListCar;

public class StartCarProgram {

		static Scanner in = new Scanner(System.in);
		static ListCarHelper lch = new ListCarHelper();

		private static void addACar() {
			// TODO Auto-generated method stub
			System.out.print("Enter the make: ");
			String make = in.nextLine();
			System.out.print("Enter the model: ");
			String model = in.nextLine();
			System.out.print("Enter the year: ");
			int year = in.nextInt();
			
			ListCar toAdd = new ListCar(make, model, year);			
			lch.insertItem(toAdd);
		}

		
		private static void returnACar() {
			// TODO Auto-generated method stub
			System.out.print("Enter the make to delete: ");
			String make = in.nextLine();
			System.out.print("Enter the model to delete: ");
			String model = in.nextLine();
			System.out.print("Enter the year to delete: ");
			int year = in.nextInt();
			
			ListCar toDelete = new ListCar(make, model, year);
			lch.deleteItem(toDelete);
		}

		private static void editAPurchase() {
			// TODO Auto-generated method stub
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by Make");
			System.out.println("2 : Search by Model");
			System.out.println("3 : Search by Year");
			int searchBy = in.nextInt();
			in.nextLine();
			List<ListCar> foundItems = null;
			if (searchBy == 1) {
				System.out.print("Enter the Make: ");
				String make = in.nextLine();
				foundItems = lch.searchForCarByMake(make);
			} else if (searchBy == 2) {
				System.out.print("Enter the model: ");
				String model = in.nextLine();
				foundItems = lch.searchForCarByModel(model);
			} else {
				System.out.print("Enter the year: ");
				int year = in.nextInt();
				foundItems = lch.searchForCarByYear(year);
			}

			if (!foundItems.isEmpty()) {
				System.out.println("Found Results.");
				for (ListCar l : foundItems) {
					System.out.println(l.getId() + " : " + l.toString());
				}
				System.out.print("Which ID to edit: ");
				int idToEdit = in.nextInt();

				ListCar toEdit = lch.searchForCarById(idToEdit);
				System.out.println("Retrieved " + toEdit.getModel() + " from " + toEdit.getMake());
				System.out.println("1 : Update Make");
				System.out.println("2 : Update Model");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {
					System.out.print("New Make: ");
					String newMake = in.nextLine();
					toEdit.setMake(newMake);
				} else if (update == 2) {
					System.out.print("New Model: ");
					String newModel = in.nextLine();
					toEdit.setModel(newModel);
				}

				lch.updateModel(toEdit);

			} else {
				System.out.println("---- No results found");
			}

		}

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			runMenu();

		}

		public static void runMenu() {
			boolean goAgain = true;
			System.out.println("--- List of Bought Cars! ---");
			while (goAgain) {
				System.out.println("*  Select an item:");
				System.out.println("*  1 -- Buy a Car");
				System.out.println("*  2 -- Edit a Purchase");
				System.out.println("*  3 -- Return a Car");
				System.out.println("*  4 -- View the list of Purchases");
				System.out.println("*  5 -- Exit the awesome program");
				System.out.print("*  Your selection: ");
				int selection = in.nextInt();
				in.nextLine();

				if (selection == 1) {
					addACar();
				} else if (selection == 2) {
					editAPurchase();
				} else if (selection == 3) {
					returnACar();
				} else if (selection == 4) {
					viewTheList();
				} else {
					lch.cleanUp();
					System.out.println("   Goodbye!   ");
					goAgain = false;
				}

			}

		}


		private static void viewTheList() {
			// TODO Auto-generated method stub
			List<ListCar> allItems = lch.showAllCars();
			for(ListCar l:allItems) {
				System.out.println(l.returnCarDetails());
			}
		}



	}
