package pe2;

import java.util.*;


public abstract class Hospital {
	
	
	ArrayList <Physician>  physicians = new ArrayList <Physician>();
	ArrayList <Administrator> administrators = new ArrayList <Administrator>();
	PhysicianAdministrator ImmunologyAdmin;
	PhysicianAdministrator DermatologyAdmin;
	PhysicianAdministrator NeurologyAdmin;
	ArrayList <PhysicianAdministrator> PhysicianAdministrator = new ArrayList <PhysicianAdministrator>();
	ArrayList <Volunteer> volunteers = new ArrayList <Volunteer>();
	ArrayList<Patient> patients = new ArrayList<Patient>();
	private Director director;
	int physIndex = 0;
	int volIndex = 0 ;
	
	/**
	 * 
	 * @param director
	 * This is the overloaded constructor for class hospital which accepts a director and makes a new hospital.
	 */
	
	public Hospital (Director director) {
		this.director = director; 
	}
	
	/**
	 * This is the getHospDirector method.
	 * @return the hospitals director
	 */
	
	public Director getHospDirector() {
		return director;
		
	}
	
	/**
	 * This is the addAdministrator method. It sets a PhysicianAdministrator for each speciality. It cant accept
	 * more than one PhysicianAdministrator for each speciality and as a result the hospital cant have more than
	 * three PhysicianAdministrator.
	 * @param PhysicianAdministrator
	 * @return true if the PhysicianAdministrator is successfully set and false if not because there is already a
	 * PhysicianAdministrator for the speciallity 
	 */
	
	
	public boolean addAdministrator(PhysicianAdministrator physicianAdministrator) {
		
		if (administrators.size() < 3) {
			administrators.add(physicianAdministrator);
						
			if (physicianAdministrator.SpecialtyType.equals("Immunology")) {
				this.ImmunologyAdmin = physicianAdministrator;
			}else if (physicianAdministrator.SpecialtyType.equals("Dermatology")) {
				this.DermatologyAdmin = physicianAdministrator;
			}else if (physicianAdministrator.SpecialtyType.equals("Neurology")) {
				this.NeurologyAdmin = physicianAdministrator;
			}else {
				throw new IllegalArgumentException();
			}
			
			return true;
		}else {
			return false;
		}
	}

	/**
	 * The hirePhysician method hires new physicians. If physicianAdministrator with the same speciality has 
	 * less than 25 and the physician doesnt alredy exist, the physician gets hired. 
	 * @param physician
	 * @return true if successfully hired and false if not 
	 */
	
	public boolean hirePhysician (Physician physician) {
		 
		boolean finalBoolean = true  ; 
		
			
			if (physician.SpecialtyType.equals("Immunology")) {
				if (ImmunologyAdmin.physicianList.contains(physician)) {
					finalBoolean = false;
				}else {
					ImmunologyAdmin.physicianList.add(physician);
					this.physicians.add(physician);
					finalBoolean = true;
				}
				 
				 
			}else if (physician.SpecialtyType.equals("Dermatology")) {
			if (DermatologyAdmin.physicianList.contains(physician)) {
				finalBoolean = false;
			}else {
				DermatologyAdmin.physicianList.add(physician);
				this.physicians.add(physician);
				finalBoolean = true;
			}
					
			
				
			}else if (physician.SpecialtyType.equals("Neurology")) {
				if (NeurologyAdmin.physicianList.contains(physician)) {
					finalBoolean = false;
				}else {
					NeurologyAdmin.physicianList.add(physician);
					this.physicians.add(physician);
					finalBoolean = true;
				}
			}
			
			
			
			
			return finalBoolean;
		
		
		
	}
	
	/**
	 * Thid is the extractAllPhysicianDetails which returns a list of all the Physicians.
	 * @return list of all the physicians
	 */
	
	public ArrayList <Physician> extractAllPhysicianDetails(){
		return this.physicians;
	}
	
	/**
	 * This is the resignPhysician method which resigns physicians, set their patients and volunteer to their 
	 * next doctor based on first come, first serve. If the hospital has only one doctor of one speciallity, 
	 * it doesnt let them resign and throws NoSpecialtyException. 
	 * @param physician
	 * @throws NoSpecialtyException
	 */
	
	
	public void resignPhysician (Physician physician) throws NoSpecialtyException{
		
		ArrayList <Patient> duplicate = new ArrayList<Patient>();
		ArrayList<Volunteer> duplicateV = new ArrayList<Volunteer>();
		

	 if (physician.SpecialtyType == "Immunology") {
			
		 if(ImmunologyAdmin.physicianList.size()>1) {
			 
			 duplicate = physician.patientList();
			 duplicateV = physician.extractValunterDetail();
			ImmunologyAdmin.physicianList.remove(physician);
			this.physicians.remove(physician);
			physician = null;
			
			for (int j = 0 ; j < duplicate.size(); j++) {
				for (int i = 0 ; i < physicians.size();i++) {
				if (physicians.get(i).patients.size()< 8) {
				 physicians.get(i).addNewPatient(duplicate.get(j));
				
					 break;
				 }continue;
			 } 
		}
			for (int j = 0 ; j < duplicateV.size(); j++) {
				for (int i = 0 ; i < physicians.size();i++) {
				if (physicians.get(i).volunteers.size()< 5) {
				 physicians.get(i).addNewVolunteer(duplicateV.get(j));
				
					 break;
				 }continue;
			 } 
		}
	
			 
		 }else {
			 throw new NoSpecialtyException();
		 }
			
			
		}else
		
		if(physician.SpecialtyType == "Dermatology") {
			
			if (DermatologyAdmin.physicianList.size()>1) {
				duplicate = physician.patientList();
				duplicateV = physician.extractValunterDetail();
			DermatologyAdmin.physicianList.remove(physician);
		
			this.physicians.remove(physician);
			physician = null;
			
			
			 
			for (int j = 0 ; j < duplicate.size(); j++) {
				for (int i = 0 ; i < physicians.size();i++) {
				if (physicians.get(i).patients.size()< 8) {
				 physicians.get(i).addNewPatient(duplicate.get(j));
					 break;
				 }continue;
			 } 
		}
			for (int j = 0 ; j < duplicateV.size(); j++) {
				for (int i = 0 ; i < physicians.size();i++) {
				if (physicians.get(i).volunteers.size()< 5) {
				 physicians.get(i).addNewVolunteer(duplicateV.get(j));
				
					 break;
				 }continue;
			 } 
		}
	
			 

			}else {
				 throw new NoSpecialtyException();
			}
	
	}
		else if(physician.SpecialtyType == "Neurology") {
			
			if (NeurologyAdmin.physicianList.size() > 1) {
				duplicate = physician.patientList();
				duplicateV = physician.extractValunterDetail();
			NeurologyAdmin.physicianList.remove(physician);
		this.physicians.remove(physician);
			physician = null;
			
			
			
			
					for (int j = 0 ; j < duplicate.size(); j++) {
						for (int i = 0 ; i < physicians.size();i++) {
						if (physicians.get(i).patients.size()< 8) {
						 physicians.get(i).addNewPatient(duplicate.get(j));
						
							 break;
						 }continue;
					 } 
				}
					for (int j = 0 ; j < duplicateV.size(); j++) {
						for (int i = 0 ; i < physicians.size();i++) {
						if (physicians.get(i).volunteers.size()< 5) {
						 physicians.get(i).addNewVolunteer(duplicateV.get(j));
						
							 break;
						 }continue;
					 } 
				}
			
			}else {
				 throw new NoSpecialtyException();
			}
			}
			
		}
	
	
	
		
		
		

/**
 * 	The hireVolunteer method hires new volunteer if they already dont exist and if the number of volunteer is less 
 *  than 150. This method also sets new Volunteers to physicians based on first come first surve method. 
 * 
 * @param volunteer
 * @return true if the volunteer is successfully fired and false otherwise
 */
	
public boolean hireVolunteer (Volunteer volunteer) {
		
		boolean flag = false; 
		
			 if (this.volunteers.contains(volunteer)) { 
				 flag = true;
				 return false;
			 }
			 
		
		if (volunteers.size() < 150 && flag == false) {
			volunteers.add(volunteer);
			

			
			for (int i = 0 ; i <physicians.size();i++) {
				if (physicians.get(i).volunteers.size()!=5) {
					physicians.get(i).addNewVolunteer(volunteer);
					break;
				}
			}
			return true;
			
		}else {
			return false;
		}
	}


/**
 * ResignVolunteer is a mehod that resignes volunteers successfuly if there is at least one volunteer left
 * for each physician after resignation. If there wont be any volunteers left it throws NoVolunteersException.
 * @param volunteer
 * @return true if resigned successfully and false otherwise
 * @throws NoVolunteersException
 */
public boolean resignVolunteer(Volunteer volunteer) throws NoVolunteersException {
	
	int index=0;
	for (int i = 0 ; i < this.physicians.size(); i++) {
		if (physicians.get(i).volunteers.contains(volunteer)) {
			if (physicians.get(index).volunteers.size()< 2 ) {
				throw new NoVolunteersException();
			}
		}
	}
	
	this.volunteers.remove(volunteer);
	
	for (int i = 0 ; i < this.physicians.size(); i++) {
		if (physicians.get(i).volunteers.contains(volunteer)) {
			physicians.get(i).volunteers.remove(volunteer);
			
		}
	}
	
	 return true;
	

}

/**
 * This method gives a full list of all the patients in the Hospital
 * @return a list of all patients 
 */
	public ArrayList<Patient> extractAllPatientDetails() {
	return this.patients;
	
	}
	

	/**
	 * The admitPatient methods admit new patients to the hospital. If the hospital is already filled up and have
	 * 500 patients, it cant admit a new one and it throws NoSpaceException.
	 * @param patient
	 * @return if the patient is admitted successfuly or not 
	 * @throws NoSpaceException
	 */
	
	public boolean admitPatient(Patient patient) throws NoSpaceException{
		
		int max = this.physicians.size()*8;
		
		if (patients.size() == 500 ) {
			throw new NoSpaceException();
			
			
		}else if (patients.size() == max) {
			
			throw new NoSpaceException();
			
			}
		
		
		else if (!patients.contains(patient) ){
			patients.add(patient);
			
			if (this.physicians.get(physIndex).patients.size() != 8) {
			this.physicians.get(physIndex).addNewPatient(patient);
			patient.physician = this.physicians.get(physIndex);
			return true;
			
			
			}else if (physIndex  < physicians.size()-1 && 
					this.physicians.get(physIndex).patients.size()==8) {
				
				physIndex++;
				this.physicians.get(physIndex).addNewPatient(patient);
				patient.physician = this.physicians.get(physIndex);
				return true;
			}
			 
			
		}
		return false;
	}
	
	/**
	 * DischargePatient is a method that discharge patients. It removes the patient from all the records 
	 * including physician list and all patients list 
	 * @param patient
	 * @return return true if successfully discharged and false if not 
	 */
	
	
	public boolean dischargePatient (Patient patient) {
		
	if (this.patients.contains(patient)) {
		
		for (int i = 0 ; i < physicians.size() ; i++) {
			if (physicians.get(i).patients.contains(patient)) {
				physicians.get(i).patients.remove(patient);
				break;
			}
		}

		this.patients.remove(patient);
		patient = null ;
		
		return true;
		}else {
			return false;
			}
	}
	
	/**
	 * This methods gives back a list of all the volunteers 
	 * @return a list of all volunteers 
	 */
	public ArrayList <Volunteer> extractAllVolunteerDetails(){
		return this.volunteers;
	}


	
	
	
}

/**
 * This is the person class. Each person should have a first name, last name, age, gender, address. It contains 
 * methods that set each of the attributes 
 * @author Mehregan Mesgari
 *
 */


abstract class Person{
	
	String firstName;
	String lastName;
	int age; 
	String gender; 
	String address;
	
	/**
	 * This is the default constructor for class person that sets all the attriobbutes to default 
	 */
	
	public Person() {
		this.firstName =  " ";
		this.lastName = " "; 
		this.age = 0;
		this.gender = " ";
		this.address = " ";
	}
	
	
	/**
	 * This is the setFirstName which set the firstName of the person
	 * @param firstName
	 */
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * This is the setAge which set the age of the person
	 * @param age
	 */
	
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * This is the setLastName which set the lastName of the person
	 * @param lastName
	 */
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * This is the setGender which set the gender of the person
	 * @param gender
	 */
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/**
	 * This is the setAddress which set the address of the person
	 * @param address
	 */
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * This is the getName method which returns the first name of the person
	 * @return string of the firstname 
	 */
	
	public String getName() {
		return (this.firstName+", "+this.lastName);
	}
	
	/**
	 * This is the getGender method which returns the gender of the person
	 * @return string of the firstname 
	 */
	
	public String getGender() {
		return this.gender;
	}
	
	/**
	 * This is the getAge method which returns the age of the person
	 * @return the age 
	 */
	
	public int getAge() {
		return this.age;
	}
	
	/**
	 * This is the getAddress method which returns the address of the person
	 * @return string of the address 
	 */
	
	public String getAddress() {
		return this.address;
	}
	
}


/** 
 * This is the patient class which extends person class. Each  patients has a patientID and a physician besides
 * all person attributes. 
 * @author Mehregan Mesgari
 *
 */

abstract class Patient extends Person implements Comparable<Patient>{
	
	int patiendID;
	static int pnumber = 1000;
	Physician physician; 
	
	/***
	 * This is the overriden constructor for class patient. It accepts all the attributes of a patient and make 
	 * a new patient
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param gender
	 * @param address
	 */
	
	
	 public Patient(String firstName,String lastName,int age,String gender, String address ) {
		
		this.firstName =  firstName;
		this.lastName = lastName; 
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.patiendID = pnumber;
		pnumber++;
		
		
	}

/***
 * This method returns each persons patient ID 
 * @return patient ID 
 */
	public int patiendID() {
		return this.patiendID;
	
		}
	
/***
 * This is the compare to method which checks if a patient already exists in patient list or not 
 * @return 1 if already exists and 0 if not 	
 */

	@Override
	public int compareTo(Patient other) {
		if (this.firstName.equals(other.firstName)   &&
				this.lastName.equals(other.lastName)  &&
				this.age == other.age &&
				this.gender.equals(other.gender) &&
				this.address.equals(other.address)
				) {return 1;
		}else {return 0;}	
	}
	
	
	/***
	 * The toString method returns all the information of a patient
	 * @return String of all the patients information
	 */
	public String toString() {
		return "Patient ["+this.patiendID+", [" + this.firstName+", "+this.lastName+", "+this.age+", "+
	this.gender+", "+this.address+"]]";


	}
	
	/***
	 * getPatientID method returns the patients ID 
	 * @return the patient ID 
	 */
	
	
	public int getPatientID() {
		return this.patiendID;
	}
	
	/***
	 * When getAssignedPhysician method is caled, the physician assigned to patient is accessed and returned
	 * @return assigned physician 
	 */
	
	
	public Physician getAssignedPhysician() {
		return this.physician; 
	}
	
	
	/***
	 * When clearPatientRecord method is called, all the infromation of the patient is nullified. If the patient
	 * is already assigned to a physician it returns false because the information already exxists in another file
	 * @return true if successfully cleared and false other wise
	 */
	
	public boolean clearPatientRecord() {
		if ( this.physician == null) {
			this.firstName = null;
			this.address = null;
			this.age = 0;
			this.lastName = null;
			
			return true;
		}else {
			
			return false;
		}
	}
	
	
	/***
	 * This method sets a physician to the patient
	 * @param physician
	 */
	
	public void setAssignedPhysician(Physician physician){
		this.physician =  physician;
	}
	

}


/***
 * This is the employee class which is a subclass of person. Each employee have one employeeID more than person.
 * @author Mehregan Mesgari 
 *
 */

abstract class Employee extends Person {
	
	int employeeID;
	static int number =100;
	
	
	/***
	 * This is the overiden constructor for class Employee which sets all the attributes to the employee and give them 
	 * an employee number. 
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param gender
	 * @param address
	 */
	
public Employee(String firstName,String lastName,int age,String gender, String address ) {
		
		this.firstName =  firstName;
		this.lastName = lastName; 
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.employeeID = number; 
		number ++;
		
	}


/***
 * The getEmployeeID methods gives the employeeID  
 * @return employeeID
 */

	public int getEmployeeID() {
		return this.employeeID;
		
	}
}


/***
 * This is the class Volunteer which extends class employee. It has all the attrubutes of the employee. Each voluunteer
 * is set to a physician. 
 * @author Mehregan Mesgari 
 *
 */

abstract class Volunteer extends Employee{

	ArrayList <Volunteer>  volunteers = new ArrayList <Volunteer>();
	
	
	/***
	 * This is the overiden constructor for class Volunteer which sets all the attributes to the Volunteer and give them 
	 * an employee number. 
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param gender
	 * @param address
	 */
	
	public Volunteer(String firstName, String lastName, int age, String gender, String address) {
		super(firstName, lastName, age, gender, address);
	
	}
	
	/***
	 * This methods sets a physician for a volunteer and adds them to the physicians list
	 * @param physician
	 */
	
	public void addVolunteer(Volunteer physician) {
		volunteers.add(physician);
	}
	
	/***
	 * the compareTo method compares two differnt volunteers together and shows if they are euql or not 
	 * @param Volunteer
	 * @return 1 if equal and 0 if not 
	 */
	
	public int compareTo(Volunteer other) {
		if (this.firstName.equals(other.firstName)   &&
				this.lastName.equals(other.lastName)  &&
				this.age == other.age &&
				this.gender.equals(other.gender) &&
				this.address.equals(other.address)
				) {
			return 1;
		}else {return 0;}	
	}
	
	/***
	 * To string method returns the information of the volunteer in a full string 
	 * @return string of informaion
	 */
	
	public String toString() {
		return "Volunteer [["+ this.employeeID +",["+this.firstName+", "+this.lastName+", "+this.age
			+", "+this.gender+", "+this.address+"]]]"	;
		
	}
}

/***
 * SalariedEmployee class extends employee. All salaried emoloyees have a salary amount. 
 * @author Mehregan Mesgari 
 *
 */


abstract class salariedEmployee extends Employee implements Comparable<salariedEmployee>{
	
	double salary; 
	public salariedEmployee(String firstName, String lastName, int age, String gender, String address) {
		super(firstName, lastName, age, gender, address);
	
	}
	
	/***
	 * Set salary methods sets the salaries of the paid employees
	 * @param salary
	 */
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	/***
	 * Get salary methods returns the salary of a paid employee
	 * @return amount of salary
	 */
	
	public double getSalary() {
		return this.salary;
	}
	
	/***
	 * This is the compare to method which checks if a salariedEmoloyee already exists in salariedEmoloyee list or not 
	 * @return 1 if already exists and 0 if not 	
	 */

	@Override
	public int compareTo(salariedEmployee other) {
		if (this.firstName.equals(other.firstName)   &&
				this.lastName.equals(other.lastName)  &&
				this.age == other.age &&
				this.gender.equals(other.gender) &&
				this.address.equals(other.address)
				) {
			return 1;
		}else {return 0;}	
	}

	
}

/***
 * Physician class extends the salariedEmployee class. All the physicians have a patients list, a volunteer list 
 * and they have a specialType.
 * @author Mehregan Mesgari
 *
 */

abstract class Physician extends salariedEmployee {
	
	String SpecialtyType;
	ArrayList <Patient> patients = new ArrayList <Patient>();
	ArrayList <Volunteer> volunteers = new ArrayList <Volunteer>();
	
	
	/***
	 * This is the obverriden method for physician class and it sets all the attributes to the physican.
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param gender
	 * @param address
	 */

	public Physician(String firstName, String lastName, int age, String gender, String address) {
		super(firstName, lastName, age, gender, address);
	
	}
	
	/***
	 * setSpecialty method sets the speciality of the physican and throws IllegalArgumentException if the specialyType 
	 * doesnt exist 
	 * @param SpecialtyType
	 */
	
	public void setSpecialty (String SpecialtyType) {
		if (SpecialtyType.equals("Immunology")) {
			this.SpecialtyType = SpecialtyType;
		}else if (SpecialtyType.equals("Dermatology")) {
			this.SpecialtyType = SpecialtyType;
		}else if (SpecialtyType.equals("Neurology")) {
			this.SpecialtyType = SpecialtyType;
		}else {
			throw new IllegalArgumentException();
		}
	}
	
	
	/***
	 * getSpecialty returns speciality type of a physician
	 * @return the speciality
	 */
	
	public String getSpecialty() {
		return this.SpecialtyType;
	}
	
	
	/***
	 * toString method returns the information of a physician
	 * @return physicians information
	 */
	
	public String toString() {
		return "Physician [[["+this.employeeID+",["+this.firstName+", "+this.lastName+", "+this.age
				+", "+this.gender+", "+this.address+"]], "+this.salary+"]]";
				
	}
	
	/***
	 * addNewPatient adds a newly admitted patient to the physicinas list
	 * @param patient
	 */
	
	public void addNewPatient(Patient patient) {
		this.patients.add(patient);
	}
	
	/***
	 * addNewPatient adds a new volunteer to the volunteers list
	 * @param volunteer
	 */
	
	public void addNewVolunteer(Volunteer volunteer) {
		this.volunteers.add(volunteer);
	}
	
	/***
	 * This method returns a list of patients assigned to a physican
	 * @return a list of patients
	 */
		
	public ArrayList <Patient> patientList(){
		return this.patients;
	}
	
	/***
	 * This method returns a list of patients assigned to a physican
	 * @return a list of patients
	 */

	public ArrayList <Patient> extractPatientDetail(){
		return this.patients;
	}
	
	/***
	 * This method returns a list of volunteers assigned to a physican
	 * @return a list of volunteers
	 */
	
	public  ArrayList <Volunteer> extractValunterDetail(){
		return this.volunteers;
	}
	
	/***
	 * This method assignes the physican to the volunteer
	 * @param employee
	 * @return true if volunteer is succesfully added and false otherwise
	 */
	
	public boolean assignVolunteer(Employee employee) {
		if (this.volunteers.size()<5) {
		this.volunteers.add((Volunteer) employee);
		return true;
		}else {
			return false;
		}
	}
	
	
	/***
	 * hasMaxVolunteers method returns true if the physician has max number of volunteer and false other wise 
	 * @return if the physican hasMaxVolunteers 
	 */
	
	public boolean hasMaxVolunteers() {
		if (this.volunteers.size() == 5) {
			return true;
		}else {
			return false;
		}
	}
	
	/***
	 * hasMaximumpatient method returns true if the physician has max number of patients and false other wise 
	 * @return if the physican hasMaximumpatient 
	 */
	
	public boolean hasMaximumpatient() {
		if (this.patients.size() == 8) {
			return true;
		}else {
			return false;
		}
	}	
	
}

/**
 * The Administrator class extends salariedEmployee.
 * @author Mehregan Mesgari 
 *
 */


abstract class Administrator extends salariedEmployee{
	
	
	ArrayList <Administrator> administrators = new ArrayList <Administrator>();

	public Administrator(String firstName, String lastName, int age, String gender, String address) {
		super(firstName, lastName, age, gender, address);
		
	}	
	
}

/**
 * The Director class extends Administrator. The dirrectot has a list of administrators.
 * @author Mehregan Mesgari 
 *
 */

abstract class Director extends Administrator{
	
	ArrayList <Administrator> administrators = new ArrayList <Administrator>();
	
	public Director(String firstName, String lastName, int age, String gender, String address) {
		super(firstName, lastName, age, gender, address);
	
	}
	
	/***
	 * assignAdministrator adds a new administrator to the directors list if the limit is not reached already 
	 * @param salariedEmployee
	 * @return if adminestrator is set succesfully
	 */
	
	public boolean assignAdministrator(Administrator salariedEmployee) {
		if (administrators.size() < 3) {
			administrators.add(salariedEmployee);
	return  true;
		}else {
			return false;
		}
		
	}
	
	/**
	 * This method returns a list of physician administrators
	 * @return a list of physcian Administrators
	 */
	
	
	public ArrayList<Administrator> extractPhysicianAdmins() {
		return this.administrators;
	}
	
	
}

/**
 * This is the PhysicianAdministrator class which extends Administrator. It has a list of physicians and a speciality 
 * type 
 * @author Mehregan Mesgari
 *
 */


abstract class PhysicianAdministrator extends Administrator{
	
	String SpecialtyType;
	
	ArrayList<Physician> physicianList = new ArrayList <Physician>();
	
	public PhysicianAdministrator(String firstName, String lastName, int age, String gender, String address) {
		super(firstName, lastName, age, gender, address);
		
	}
	
	/**
	 * setAdminSpecialtyType methods sets the speciality type of the administrator and throws a IllegalArgumentException
	 * if the type doesnt exist 
	 * @param SpecialtyType
	 */
	
	public void setAdminSpecialtyType (String SpecialtyType) {
		if (SpecialtyType.equals("Immunology")) {
			this.SpecialtyType = SpecialtyType;
		}else if (SpecialtyType.equals("Dermatology")) {
			this.SpecialtyType = SpecialtyType;
		}else if (SpecialtyType.equals("Neurology")) {
			this.SpecialtyType = SpecialtyType;
		}else {
			throw new IllegalArgumentException();
		}
	
		}
	
	
	/**
	 * addPhysician methods adds a physician to the physicianList
	 * @param physician
	 */

	
		public void addPhysician (Physician physician) {
			if (this.physicianList.size() <25) {
			this.physicianList.add(physician);
		}}
		
	
	/**
	 * getAdminSpecialtyType returns administrators speciality
	 * @return SpecialtyType
	 */
		
	
	public String getAdminSpecialtyType () {
		return this.SpecialtyType;
	}
	
	/**
	 * toString method returns all the information of the PhysicianAdministrator in a string format
	 * @return PhysicianAdministrator information
	 */
	
	public String toString() {
		return "PhysicianAdministrator [[["+this.employeeID+",["+this.firstName+", "+this.lastName+", "
				+this.age+", "+this.gender+", "+this.address+"]], "+this.salary+"], "+this.SpecialtyType+"]"; 
		
	
	}
	
	
	/***
	 * extractPhysician method returns a list of physicians assigned to the administrator 
	 * @return list of physicians
	 */
	
	public ArrayList<Physician> extractPhysician(){
		return physicianList;
	}
	
}

/***
 * NoSpecialtyException is an exception that is thrown when a physician wants to resign but there is no other physician 
 * with the speciality and because of it they cant resign
 * @author Mehregan Mesgari
 *
 */


@SuppressWarnings("serial")
class NoSpecialtyException extends Exception{
	public NoSpecialtyException() {
		super();
	}
	public NoSpecialtyException(String message) {
		super(message);
	}
	
}



/***
 * NoVolunteersException happens when the volunteer is the only one assigned to the physican and 
 * they cant resign because of that
 * @author Mehregan Mesgari 
 *
 */

@SuppressWarnings("serial")
class NoVolunteersException extends Exception{
	public NoVolunteersException() {
		super();
	}
	public NoVolunteersException(String message) {
		super(message);
	}
	
}


/***
 * NoSpaceException happens when the hospital is filled up and ther is no space for a new patient
 * @author Mehregan Mesgari 
 *
 */

@SuppressWarnings("serial")
class NoSpaceException extends Exception{
	public NoSpaceException() {
		super();
	}
	public NoSpaceException(String message) {
		super(message);
	}
	
}







