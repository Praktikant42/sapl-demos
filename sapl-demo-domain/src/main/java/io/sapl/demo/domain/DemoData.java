package io.sapl.demo.domain;

import java.util.ArrayList;
import java.util.Arrays;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DemoData {
	private static final String DEFAULT_PASS = "password";
	private static final String HRN1 = "123456";
	private static final String HRN2 = "4711";
	private static final String ROLE_DOCTOR = "DOCTOR";
	private static final String ROLE_NURSE = "NURSE";
	private static final String ROLE_VISITOR = "VISITOR";
	private static final String ROLE_ADMIN = "ADMIN";
	private static final String NAME_DOMINIK = "Dominik";
	private static final String NAME_JULIA = "Julia";
	private static final String NAME_PETER = "Peter";
	private static final String NAME_ALINA = "Alina";
	private static final String NAME_THOMAS = "Thomas";
	private static final String NAME_BRIGITTE = "Brigitte";
	private static final String NAME_JANOSCH = "Janosch";
	private static final String NAME_JANINA = "Janina";
	private static final String NAME_LENNY = "Lenny";
	private static final String NAME_KARL = "Karl";
	private static final String NAME_HORST = "Horst";

	public static void loadDemoDataset(UserRepo userRepo, PatientRepo personsRepo, RelationRepo relationRepo) {
		userRepo.save(new User(NAME_DOMINIK, DEFAULT_PASS, false, new ArrayList<>(Arrays.asList(ROLE_VISITOR))));
		userRepo.save(new User(NAME_JULIA, DEFAULT_PASS, false, new ArrayList<>(Arrays.asList(ROLE_DOCTOR))));
		userRepo.save(new User(NAME_PETER, DEFAULT_PASS, false, new ArrayList<>(Arrays.asList(ROLE_DOCTOR))));
		userRepo.save(new User(NAME_ALINA, DEFAULT_PASS, false, new ArrayList<>(Arrays.asList(ROLE_DOCTOR))));
		userRepo.save(new User(NAME_THOMAS, DEFAULT_PASS, false, new ArrayList<>(Arrays.asList(ROLE_NURSE))));
		userRepo.save(new User(NAME_BRIGITTE, DEFAULT_PASS, false, new ArrayList<>(Arrays.asList(ROLE_NURSE))));
		userRepo.save(new User(NAME_JANOSCH, DEFAULT_PASS, false, new ArrayList<>(Arrays.asList(ROLE_NURSE))));
		userRepo.save(new User(NAME_JANINA, DEFAULT_PASS, false, new ArrayList<>(Arrays.asList(ROLE_NURSE))));
		userRepo.save(
				new User(NAME_HORST, DEFAULT_PASS, false, new ArrayList<>(Arrays.asList(ROLE_DOCTOR, ROLE_ADMIN))));

		personsRepo.save(
				new Patient(NAME_LENNY, "sick from working", HRN1, "111111111111", NAME_JULIA, NAME_THOMAS, "H264"));
		personsRepo.save(new Patient(NAME_KARL, "healthy", HRN2, "222222222222", NAME_ALINA, NAME_JANINA, "N333"));

		relationRepo.save(new Relation(NAME_DOMINIK, personsRepo.findByName(NAME_LENNY).getId()));
		relationRepo.save(new Relation(NAME_JULIA, personsRepo.findByName(NAME_KARL).getId()));
		relationRepo.save(new Relation(NAME_ALINA, personsRepo.findByName(NAME_KARL).getId()));
		relationRepo.save(new Relation(NAME_JANOSCH, personsRepo.findByName(NAME_KARL).getId()));
	}
}