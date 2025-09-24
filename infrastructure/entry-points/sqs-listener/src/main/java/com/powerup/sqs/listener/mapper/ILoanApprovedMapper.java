package com.powerup.sqs.listener.mapper;

import com.powerup.sqs.listener.dto.LoanApprovedDto;
import com.powerup.model.approvedloan.ApprovedLoan;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ILoanApprovedMapper {
    ApprovedLoan toDomain(LoanApprovedDto dto);
}
