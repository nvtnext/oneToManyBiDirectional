package org.jsp.hb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.hb.dto.Branch;

public class BranchDao {
	EntityManager manager = Persistence.createEntityManagerFactory("dev").createEntityManager();
	EntityTransaction transaction = manager.getTransaction();
	
	public Branch updateBranch(Branch b) {
		manager.merge(b);
		transaction.begin();
		transaction.commit();
		System.out.println("Updated Successfully!!");
		return b;
		
	}
	public void deleteBranch(Branch b) {
		b.setH(null);
		manager.remove(b);
		transaction.begin();
		transaction.commit();
		System.err.println("Deleted!!");
		
	}
	public Branch fetchBranchById(int id) {
		Branch b = manager.find(Branch.class, id);
		return b;
				
	}
	public List<Branch> fetchBranchByHospitalId(int id){
		Query q = manager.createQuery("select h.branches from Hospital h where h.id=?1");
		q.setParameter(1, id);
		List<Branch> bs = q.getResultList();
		return bs;
	}
	public void viewBranch(Branch b) {
		System.out.println();
		System.out.println("Branch Details ");
		System.out.println("Hospital ID : " + b.getH().getId());
		System.out.println("ID : " + b.getId());
		System.out.println("NAme : " + b.getName());
		System.out.println("Phone : " + b.getPhone());
		System.out.println("Email : " + b.getEmail());
		System.out.println("- - - - - - - - - - - - - - - -");
	}
	public void viewAllBranches() {
		Query q = manager.createQuery("select b from Branch b ");
		List<Branch> bs = q.getResultList();
		for(Branch b : bs){
			viewBranch(b);
		}
	}
			

}
