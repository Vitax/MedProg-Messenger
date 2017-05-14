package de.sb.messenger.persistence;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.sb.messenger.persistence.Person.Group;

public class PersonEntityTest extends EntityTest {

	public EntityManager entityManager;
	public Validator validator;
	Set<ConstraintViolation<Person>> constrainViolations;

	@Before
	public void setUpBefore() throws Exception {

		// entity manager object creates entity transaction instance
		entityManager = this.getEntityManagerFactory().createEntityManager();
		validator = this.getEntityValidatorFactory().getValidator();
	}

	@SuppressWarnings({ "deprecation", "static-access" })
	@Test
	public void testConstrains() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// valid entity
		Person person = new Person("test@gmail.com", new Document());
		person.getName().setGiven("John");
		person.getName().setFamily("Smith");
		person.getAddress().setStreet("Falkenbergerstr. 1");
		person.getAddress().setPostcode("12345");
		person.getAddress().setCity("Berlin");
		person.setGroup(Group.USER);
		byte[] hash = person.passwordHash("password");
		person.setPasswordHash(hash);

		// non-valid entity
		Person personNV = new Person( "testgmail.com",new Document()); // @missing
		personNV.getName().setGiven(""); // empty
		personNV.getName().setFamily(""); // empty
		personNV.getAddress().setStreet("Falkenbergerstr. 1");
		personNV.getAddress().setPostcode("12345678910111213141516"); // longer
																		// then
																		// 15
																		// char
		personNV.getAddress().setCity(""); // empty two errors city size and regex match 
		personNV.setGroup(Group.USER);
		
		constrainViolations = validator.validate(person);
		assertEquals(0, constrainViolations.size());
		// clean up the set
		constrainViolations.clear();
		constrainViolations = validator.validate(personNV);
		
		assertEquals(7, constrainViolations.size());

	}

	@SuppressWarnings("static-access")
	//@Test
	public void testLifeCycle() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// create entity
		Person person = new Person( "test@gmail.com",new Document());
		person.getName().setGiven("John");
		person.getName().setFamily("Smith");
		person.getAddress().setStreet("Falkenbergerstr. 1");
		person.getAddress().setPostcode("12345");
		person.getAddress().setCity("Berlin");
		person.setGroup(Group.USER);
		byte[] hash = person.passwordHash("password");
		person.setPasswordHash(hash);

		// // add to the DB
		entityManager.getTransaction().begin();
		entityManager.persist(person);
		
		entityManager.getTransaction().commit();
		this.getWasteBasket().add(person.getIdentiy());
		entityManager.getTransaction().begin();
		person = entityManager.find(Person.class, person.getIdentiy());
		assertEquals(person.getName().getGiven(), "John");
		assertEquals(person.getName().getFamily(), "Smith");
		assertEquals(person.getAddress().getCity(), "Berlin");
		// remove person from DB
		entityManager.remove(person);
		entityManager.getTransaction().commit();
		// check if it's deleted , find for getter , Reference for setter
		entityManager.find(Person.class, person.getIdentiy());
		assertNull(person);

	}

	@After
	public void tearDownAfter() throws Exception {
		entityManager.clear();
		entityManager.close();
	}

}