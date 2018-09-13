//Carl Phillips
package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

import model.ListCar;
import org.eclipse.persistence.jpa.PersistenceProvider;

public class ListCarHelper {

	static EntityManagerFactory emfactory = new PersistenceProvider().createEntityManagerFactory("Cars", null);
	
	public void insertItem(ListCar lc) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(lc);
		em.getTransaction().commit();
		em.close();
		
	}

	public List<ListCar> queryForStoreResults(String selectedMake) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("OnlineCarsListWithJPA");
		EntityManager em = emfactory.createEntityManager();
		List<ListCar> results = null;
		
		try {
			TypedQuery<ListCar> typedQuery = em.createQuery("select lc from ListCar lc where lc.make = :selectedMake", ListCar.class);
			typedQuery.setParameter("Make", selectedMake);
			results = typedQuery.getResultList();
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			em.close();
			emfactory.close();
		}
		
		return results;
	}

	public void cleanUp() {
		emfactory.close();
	}

	public List<ListCar> showAllCars() {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<ListCar> typedQuery = em.createQuery("select lc from ListCar lc", ListCar.class);
		List<ListCar> allItems = typedQuery.getResultList();
		em.close();
		return allItems;
	}
	
	public List<ListCar> searchForCarByMake(String make) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListCar> typedQuery = em.createQuery("select lc from ListCar lc where lc.make = :selectedMake", ListCar.class);
		typedQuery.setParameter("selectedMake", make);

		List<ListCar> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public List<ListCar> searchForCarByModel(String model) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListCar> typedQuery = em.createQuery("select lc from ListCar lc where lc.model = :selectedModel", ListCar.class);
		typedQuery.setParameter("selectedModel", model);

		List<ListCar> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public List<ListCar> searchForCarByYear(int year) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListCar> typedQuery = em.createQuery("select lc from ListCar lc where lc.year = :selectedYear", ListCar.class);
		typedQuery.setParameter("selectedYear", year);
		List<ListCar> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public ListCar searchForCarById(int idToEdit) {
		// TODO Auto-generated method stub

		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ListCar found = em.find(ListCar.class, idToEdit);
		em.close();
		return found;
	}


	public void updateModel(ListCar toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	public void deleteItem(ListCar toDelete) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListCar> typedQuery = em.createQuery("Select lc from ListCar lc where lc.make = :selectedMake and lc.model = :selectedModel and lc.year = :selectedYear", ListCar.class);
		typedQuery.setParameter("selectedMake", toDelete.getMake());
		typedQuery.setParameter("selectedModel", toDelete.getModel());
		typedQuery.setParameter("selectedYear", toDelete.getYear());
		typedQuery.setMaxResults(1);
		ListCar result = typedQuery.getSingleResult();
		em.remove(result);
		
		em.getTransaction().commit();
		em.close();
	}
}
