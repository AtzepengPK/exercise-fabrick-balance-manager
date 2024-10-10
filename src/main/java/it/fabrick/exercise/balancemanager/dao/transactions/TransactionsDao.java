package it.fabrick.exercise.balancemanager.dao.transactions;

import it.fabrick.exercise.balancemanager.utils.Constants;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = Constants.DaoRoutes.TRANSACTIONS, collectionResourceRel = "transactions")
public interface TransactionsDao extends CrudRepository<TransactionEntity, Long>, PagingAndSortingRepository<TransactionEntity, Long> {
}
