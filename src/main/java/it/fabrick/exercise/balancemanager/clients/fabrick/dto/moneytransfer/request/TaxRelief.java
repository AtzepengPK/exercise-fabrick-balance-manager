package it.fabrick.exercise.balancemanager.clients.fabrick.dto.moneytransfer.request;

public record TaxRelief(
	boolean isCondoUpgrade,
	String creditorFiscalCode,
	LegalPersonBeneficiary legalPersonBeneficiary,
	String taxReliefId,
	String beneficiaryType,
	NaturalPersonBeneficiary naturalPersonBeneficiary
) {
}
