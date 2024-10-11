package it.fabrick.exercise.balancemanager.dao.transactions;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsDao extends CrudRepository<TransactionEntity, Long>, PagingAndSortingRepository<TransactionEntity, Long> {
}
