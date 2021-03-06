package de.sb.messenger.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.sb.messenger.persistence.Person.Group;

public class DocumentEntityTest extends EntityTest {

	public EntityManager entityManager;
	public Validator validator;
	Set<ConstraintViolation<Document>> constrainViolations;

	@Before
	public void setUpBefore() throws Exception {

		// entity manager object creates entity transaction instance
		entityManager = this.getEntityManagerFactory().createEntityManager();
		validator = this.getEntityValidatorFactory().getValidator();
	}

	@Test
	public void testConstrains() throws NoSuchAlgorithmException, SQLException {
		// valid entity
		String s = "some content";
		byte[] content = s.getBytes();
		Document doc = new Document("someType", content);

		// non valid document - an empty content type
		Document docNV = new Document("", content);

		constrainViolations = validator.validate(doc);
		assertEquals(constrainViolations.size(), 0);
		// clean up the set
		constrainViolations.clear();

		constrainViolations = validator.validate(docNV);
		assertEquals(constrainViolations.size(), 1);

	}

	@Test
	public void testLifeCycle() throws NoSuchAlgorithmException, SQLException {
		// create entity
		String s = "some content";
		byte[] content = s.getBytes();
		Document doc = new Document("someType", content);
		
		// // add to the DB
		entityManager.getTransaction().begin();
		entityManager.persist(doc);
		entityManager.getTransaction().commit();
		this.getWasteBasket().add(doc.getIdentiy());
		entityManager.getTransaction().begin();

		doc = entityManager.find(Document.class, doc.getIdentiy());
		assertEquals(doc.getContentType(), "someType");
		String cont = new String(doc.getContent());
		assertEquals(cont, "some content");

		// remove person from DB
		entityManager.remove(doc);
		entityManager.getTransaction().commit();
		// check if it's deleted , find for getter , Reference for setter
		entityManager.find(Document.class, doc.getIdentiy());
		assertNull(doc);

	}

	@After
	public void tearDownAfter() throws Exception {
		entityManager.clear();
		entityManager.close();
		EM_FACTORY.close();

	}

}
