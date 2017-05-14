package de.sb.messenger.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.sb.messenger.persistence.Person.Group;

public class MessageEntityTest extends EntityTest {

	public EntityManager entityManager;
	public Validator validator;
	Set<ConstraintViolation<Message>> constrainViolations;

	@Before
	public void setUpBefore() throws Exception {

		// entity manager object creates entity transaction instance
		entityManager = this.getEntityManagerFactory().createEntityManager();
		validator = this.getEntityValidatorFactory().getValidator();
	}

	@Test
	public void testConstrains() {
//		// valid entity
//		Person person = new Person(Group.USER, "test@gmail.com");
//		person.getName().setGiven("John");
//		person.getName().setFamily("Smith");
//		person.getAddress().setStreet("Falkenbergerstr. 1");
//		person.getAddress().setPostcode("12345");
//		person.getAddress().setCity("Berlin");
//		person.setGroup(Group.USER);
//
//		// BaseEntity
//		BaseEntity baseEntity = new BaseEntity();
//		Message message = new Message(person, baseEntity, "Hi there!");
//		// a non-valid entity
//		Message messageNV = new Message(person, baseEntity, "");
//
//		constrainViolations = validator.validate(message);
//		assertEquals(constrainViolations.size(), 0);
//		// clean up the set
//		constrainViolations.clear();
//
//		constrainViolations = validator.validate(messageNV);
//		assertEquals(constrainViolations.size(), 1);

	}

	@Test
	public void testLifeCycle() {
//		// create entity
//		Person person = new Person(Group.USER, "test@gmail.com");
//		person.getName().setGiven("John");
//		person.getName().setFamily("Smith");
//		person.getAddress().setStreet("Falkenbergerstr. 1");
//		person.getAddress().setPostcode("12345");
//		person.getAddress().setCity("Berlin");
//		person.setGroup(Group.USER);
//
//		BaseEntity baseEntity = new BaseEntity();
//		Message message = new Message(person, baseEntity, "Hi there!");
//		// // add to the DB
//		entityManager.getTransaction().begin();
//		entityManager.persist(message);
//		entityManager.getTransaction().commit();
//		this.getWasteBasket().add(person.getIdentiy());
//		entityManager.getTransaction().begin();
//
//		message = entityManager.find(Message.class, message.getIdentiy());
//		assertEquals(message.getAuthor(), person);
//		assertEquals(message.getBody(), "Hi there!");
//		assertEquals(message.getIdentiy(), baseEntity);
		
		// remove person from DB
//		entityManager.remove(message);
//		entityManager.getTransaction().commit();
//		// check if it's deleted , find for getter , Reference for setter
//		entityManager.find(Message.class, message.getIdentiy());
//		assertNull(message);

	}

	@After
	public void tearDownAfter() throws Exception {
		entityManager.clear();
		entityManager.close();
		EM_FACTORY.close();

	}
}
