package org.vosiievska.bicycle.service.workshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vosiievska.bicycle.service.domain.valueobject.BookingId;
import org.vosiievska.bicycle.service.domain.valueobject.ClientId;
import org.vosiievska.bicycle.service.domain.valueobject.RepairServiceId;
import org.vosiievska.bicycle.service.workshop.dto.WorkshopApprovalRequest;
import org.vosiievska.bicycle.service.workshop.entity.WorkshopApprovalResponse;
import org.vosiievska.bicycle.service.workshop.valueobject.BookingApprovalId;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = IGNORE,
    imports = {BookingApprovalId.class, BookingId.class, ClientId.class, RepairServiceId.class}
)
public interface WorkshopApprovalResponseMapper {

  @Mapping(target = "id", expression = "java(new BookingApprovalId(request.getId()))")
  @Mapping(target = "bookingId", expression = "java(new BookingId(request.getBookingId()))")
  @Mapping(target = "clientId", expression = "java(new ClientId(request.getClientId()))")
  @Mapping(target = "repairServiceId", expression = "java(new RepairServiceId(request.getRepairServiceId()))")
  WorkshopApprovalResponse workshopApprovalRequestToWorkshop(WorkshopApprovalRequest request);
}
