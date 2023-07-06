package org.jsp.hb.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.jsp.hb.dao.BranchDao;
import org.jsp.hb.dao.HospitalDao;
import org.jsp.hb.dto.Branch;
import org.jsp.hb.dto.Hospital;

public class TestController {
	static HospitalDao dao = new HospitalDao();
	static BranchDao dao1 = new BranchDao();
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		boolean b = true;
		
		while(b) {
			System.out.println();
			System.out.println("1, Save Hospital");
			System.out.println("2, Update Hospital");
			System.out.println("3, Delete Hospital By ID ");
			System.out.println("4, Fetch Hospital By ID");
			System.out.println("5, Fetch Hopital By Name ");
			System.out.println();
			System.out.println("6, Save Branch ");
			System.out.println("7, Update Branch ");
			System.out.println("8, Delete Branch By Id ");
			System.out.println("9, Fetch Branch By ID ");
			System.out.println("10 Fetch Branch By Hopital ID");
			System.out.println();
			System.out.println("11, View All Hospitals");
			System.out.println("12 View All Branches");
			System.out.println();
			System.out.println("0, Close!!");
			System.out.println();
			System.out.print("Choice : ");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				saveH();
				break;
			case 2:
				updateH();
				break;
			case 3:
				deleteH();
				break;
				
			case 4:
				fetchHospById();
				break;
			case 5:
				fetchHospByName();
				break;
			case 6:
				saveB();
				break;
				
			case 7:
				updateB();
				break;
			case 8:
				deleteB();
				break;
			case 9:
				fetchBranchByID();
				break;
			case 10:
				fetchBranchByHosp();
				break;
			case 11:
				viewH();
				break;
			case 12:
				viewB();
				break;
			case 0:
				b = false;
				System.out.println("Finished");
				break;

			default:
				break;
			}
		}
		
		
	}

	public static void saveH() {
		System.out.println("Enter Hospital Name, GST, Founder");
		String name = sc.next();
		int gst = sc.nextInt();
		String founder = sc.next();
		Hospital h = new Hospital();
		h.setName(name);
		h.setGst(gst);
		h.setFounder(founder);
		dao.saveHospital(h);
		
	}
	public static void updateH() {
		dao.viewAllHosp();
		System.out.println("Enter Hopital ID : ");
		int id = sc.nextInt();
		Hospital h = dao.fetchHospById(id);
		System.out.println("Enter Hospital Name, GSt, Founder");
		String name = sc.next();
		int gst = sc.nextInt();
		String founder = sc.next();
		h.setName(name);
		h.setGst(gst);
		h.setFounder(founder);
		dao.updateHopital(h);
	
	}
	public static void deleteH() {
		dao.viewAllHosp();
		System.out.println("Enter Hospital ID : ");
		int id = sc.nextInt();
		Hospital h = dao.fetchHospById(id);
		dao.deleteHospital(h);
	}
	public static void fetchHospById() {
		System.out.println("Enter Hospital ID : ");
		int id = sc.nextInt();
		Hospital h = dao.fetchHospById(id);
		dao.viewHosp(h);
	}
	public static void fetchHospByName() {
		System.out.println("Enter Hospital Name ; ");
		String name = sc.next();
		List<Hospital> hs = dao.fetchHospByName(name);
		for(Hospital h : hs) {
			dao.viewHosp(h);
		}
	}
	public static void saveB() {
		System.out.println("Enter Branch Name, Phone, Email");
		String name = sc.next();
		long phone = sc.nextLong();
		String email = sc.next();
		Branch b = new Branch();
		b.setName(name);
		b.setPhone(phone);
		b.setEmail(email);
		dao.viewAllHosp();
		System.out.println("Enter Hospital ID : ");
		int id = sc.nextInt();
		Hospital h = dao.fetchHospById(id);
		b.setH(h);
		h.setBranches(new ArrayList<Branch>(Arrays.asList(b)));
		dao.saveHospital(h);
		
				
	}
	public static void updateB() {
		dao1.viewAllBranches();
		System.out.println("Enter Branch ID : ");
		int id = sc.nextInt();
		Branch b = dao1.fetchBranchById(id);
		System.out.println("Enter Branch Name, Phone, Email ");
		String name = sc.next();
		long phone = sc.nextLong();
		String email = sc.next();
		b.setEmail(email);
		b.setName(name);
		b.setPhone(phone);
		dao1.updateBranch(b);
	}
	public static void deleteB() {
		dao1.viewAllBranches();
		System.out.println("Enter Branch Id");
		int id = sc.nextInt();
		Branch b = dao1.fetchBranchById(id);
		dao1.deleteBranch(b);
	}
	public static void fetchBranchByID() {
		System.out.println("Enter Branch Id :");
		int id = sc.nextInt();
		Branch b = dao1.fetchBranchById(id);
		dao1.viewBranch(b);
	}
	public static void fetchBranchByHosp() {
		System.out.println("Enter Hospital ID : ");
		int id = sc.nextInt();
		List<Branch> bs = dao1.fetchBranchByHospitalId(id);
		for(Branch b : bs) {
			dao1.viewBranch(b);
		}
		
	}
	public static void viewH() {
		dao.viewAllHosp();
	}
	public static void viewB() {
		dao1.viewAllBranches();
	}

}
