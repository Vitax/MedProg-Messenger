package de.sb.messenger.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.sb.messenger.persistence.Person.Group;

public class MessegeEntityTest extends EntityTest {

	public EntityManager entityManager;
	public Validator validator;
	Set<ConstraintViolation<Message>> constrainViolations;

	@Before
	public void setUpBefore() throws Exception {

		// entity manager object creates entity transaction instance
		entityManager = this.getEntityManagerFactory().createEntityManager();
		validator = this.getEntityValidatorFactory().getValidator();
	}

	@SuppressWarnings("static-access")
	@Test
	public void testConstrains() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// valid entity
		Person person = new Person("test@gmail.com", new Document("image/png", null));
		person.getName().setGiven("John");
		person.getName().setFamily("Smith");
		person.getAddress().setStreet("Falkenbergerstr. 1");
		person.getAddress().setPostcode("12345");
		person.getAddress().setCity("Berlin");
		person.setGroup(Group.USER);
		byte[] hash = person.passwordHash("password");
		person.setPasswordHash(hash);

		// BaseEntity
		BaseEntity baseEntity = new BaseEntity();
		Message message = new Message(person, baseEntity, "Hi there!");
		// a non-valid entity
		Message messageNV = new Message(person, baseEntity, "");

		constrainViolations = validator.validate(message);
		assertEquals(constrainViolations.size(), 0);
		// clean up the set
		constrainViolations.clear();

		constrainViolations = validator.validate(messageNV);
		assertEquals(constrainViolations.size(), 1);

	}

	@SuppressWarnings("static-access")
	@Test
	public void testLifeCycle() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// create entity
		Person person = new Person("test@gmail.com", new Document("image/png", null));
		person.getName().setGiven("John");
		person.getName().setFamily("Smith");
		person.getAddress().setStreet("Falkenbergerstr. 1");
		person.getAddress().setPostcode("12345");
		person.getAddress().setCity("Berlin");
		person.setGroup(Group.USER);
		byte[] hash = person.passwordHash("password");
		person.setPasswordHash(hash);


		BaseEntity baseEntity = new BaseEntity();
		Message message = new Message(person, baseEntity, "Hi there!");
		// // add to the DB
		entityManager.getTransaction().begin();
		entityManager.persist(message);
		entityManager.getTransaction().commit();
		this.getWasteBasket().add(person.getIdentiy());
		entityManager.getTransaction().begin();

		message = entityManager.find(Message.class, message.getIdentiy());
		assertEquals(message.getAuthor(), person);
		assertEquals(message.getBody(), "Hi there!");
		assertEquals(message.getIdentiy(), baseEntity);
		
		// remove person from DB
		entityManager.remove(message);
		entityManager.getTransaction().commit();
		// check if it's deleted , find for getter , Reference for setter
		entityManager.find(Message.class, message.getIdentiy());
		assertNull(message);

	}

	@After
	public void tearDownAfter() throws Exception {
		entityManager.clear();
		entityManager.close();

	}
}


