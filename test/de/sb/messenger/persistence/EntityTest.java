package de.sb.messenger.persistence;

import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class EntityTest {

	protected static EntityManagerFactory EM_FACTORY;
	protected static ValidatorFactory VALIDATOR_FACTORY;
	private Set<Long> wasteBasket = new HashSet<>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		// entity manager object creates entity transaction instance
		EM_FACTORY = Persistence.createEntityManagerFactory("messenger");
		VALIDATOR_FACTORY = Validation.buildDefaultValidatorFactory();
	}
	// creates a persistent unit "messenger"
	public EntityManagerFactory getEntityManagerFactory() {

		return EM_FACTORY;

	}
	
	public ValidatorFactory getEntityValidatorFactory(){
		return VALIDATOR_FACTORY;
		
	}
	
	public Set<Long> getWasteBasket(){
		return wasteBasket;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EM_FACTORY.close();

	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
