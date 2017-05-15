package de.sb.messenger.persistence;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Before;
import org.junit.Test;

public class cleanTest extends EntityTest{
	public EntityManager entityManager;
	public Validator validator;
	Set<ConstraintViolation<Person>> constrainViolations;

	@Before
	public void setUpBefore() throws Exception {

		// entity manager object creates entity transaction instance
		entityManager = this.getEntityManagerFactory().createEntityManager();
		validator = this.getEntityValidatorFactory().getValidator();
	}
	
	@Test
	public void testLifeCycle() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(entityManager.find(BaseEntity.class, (long)59));
		transaction.commit();
//		transaction.begin();
//		entityManager.remove(entityManager.find(Document.class, (long)55));
		transaction.commit();
	}
}
