package org.jsp.hb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.hb.dto.Hospital;

public class HospitalDao {
	EntityManager manager = Persistence.createEntityManagerFactory("dev").createEntityManager();
	EntityTransaction transaction = manager.getTransaction();
	
	
	public Hospital saveHospital(Hospital h) {
		manager.persist(h);
		transaction.begin();
		transaction.commit();
		System.out.println("Saved Successfully!!");
		return h;
	} 
	public Hospital updateHopital(Hospital h) {
		manager.merge(h);
		transaction.begin();
		transaction.commit();
		System.out.println("Updated Successfully!!");
		return h;
	}
	public void deleteHospital(Hospital h) {
		manager.remove(h);
		transaction.begin();
		transaction.commit();
		System.out.println("Deleted Successfully!!");
		
	}
	public Hospital fetchHospById(int id) {
		Hospital h = manager.find(Hospital.class, id);
		return h;
	}
	public List<Hospital> fetchHospByName(String name){
		Query q = manager.createQuery("select h from Hospital h where h.name=?1");
		q.setParameter(1, name);
		List<Hospital> hs = q.getResultList();
		return hs;
	}
	public void viewHosp(Hospital h) {
		System.out.println();
		System.out.println("Hospital Details ");
		System.out.println("ID : " + h.getId());
		System.out.println("Name : " + h.getName());
		System.out.println("Gst " + h.getGst());
		System.out.println("Founder : " + h.getFounder());
		System.out.println("- - - - - - - - - - - - - - - - - -");
	}
	public void viewAllHosp() {
		Query q = manager.createQuery("select h from Hospital h");
		List<Hospital> hs = q.getResultList();
		for(Hospital h: hs) {
			viewHosp(h);
		}
	}

}
