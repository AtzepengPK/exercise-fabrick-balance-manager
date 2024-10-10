package it.fabrick.exercise.balancemanager.utils.mappers;

import it.fabrick.exercise.balancemanager.clients.fabrick.dto.transaction.TransactionFabrick;
import it.fabrick.exercise.balancemanager.dao.transactions.TransactionEntity;
import it.fabrick.exercise.balancemanager.dto.transactions.DtoTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionsMapper {
	TransactionsMapper INSTANCE = Mappers.getMapper(TransactionsMapper.class);

	TransactionEntity recordToEntity(TransactionFabrick entity);

	DtoTransaction entityToDto(TransactionEntity entity);

	DtoTransaction recordToDto(TransactionFabrick entity);
}
