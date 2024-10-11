package it.fabrick.exercise.balancemanager.transactions;

import it.fabrick.exercise.balancemanager.dao.transactions.TransactionEntity;
import it.fabrick.exercise.balancemanager.dao.transactions.TransactionsDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class TransactionDaoTest {

	@Autowired
	private TransactionsDao transactionsDao;

	@Test
	public void testSaveTransaction() {
		TransactionEntity entity = new TransactionEntity();
		entity.setTransactionId("123");
		transactionsDao.save(entity);

		Optional<TransactionEntity> found = transactionsDao.findById(123L);
		assertTrue(found.isPresent());
	}
}
