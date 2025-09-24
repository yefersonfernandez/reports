package com.powerup.api.mapper;

import com.powerup.api.dto.response.ReportResponseDto;
import com.powerup.model.report.Report;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface IReportMapper {
    ReportResponseDto toReportResponseDto(Report report);
}
