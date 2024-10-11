package it.fabrick.exercise.balancemanager.utils.mappers;

import it.fabrick.exercise.balancemanager.clients.fabrick.dto.balance.Balance;
import it.fabrick.exercise.balancemanager.dto.balance.DtoBalance;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BalanceMapper {
	BalanceMapper INSTANCE = Mappers.getMapper(BalanceMapper.class);

	DtoBalance recordToDto(Balance entity);
}
