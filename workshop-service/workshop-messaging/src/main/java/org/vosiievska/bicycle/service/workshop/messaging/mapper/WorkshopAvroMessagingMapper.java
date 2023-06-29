package org.vosiievska.bicycle.service.workshop.messaging.mapper;

import com.bicycle.service.avro.payment.AvroWorkshopApprovalRequest;
import com.bicycle.service.avro.payment.AvroWorkshopApprovalResponse;
import com.bicycle.service.avro.payment.WorkshopResponseStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vosiievska.bicycle.service.workshop.dto.WorkshopApprovalRequest;
import org.vosiievska.bicycle.service.workshop.event.WorkshopApprovalResponseEvent;

import java.util.List;

@Mapper(
    componentModel = "spring",
    imports = {WorkshopResponseStatus.class}
)
public abstract class WorkshopAvroMessagingMapper {


  public abstract WorkshopApprovalRequest avroWorkshopApprovalRequestToRequest(AvroWorkshopApprovalRequest request);

  public abstract List<WorkshopApprovalRequest> avroWorkshopApprovalRequestToRequestList(
      List<AvroWorkshopApprovalRequest> requests);

  @Mapping(target = "id", expression = "java(UUID.randomUUID().toString())")
  @Mapping(target = "workshopId", expression = "java(getWorkshopId(event))")
  @Mapping(target = "specialistId", expression = "java(getSpecialistId(event))")
  @Mapping(target = "bookingId", source = "event.domain.bookingId.value")
  @Mapping(target = "repairServiceId", source = "event.domain.repairServiceId.value")
  @Mapping(target = "workshopResponseStatus",
      expression = "java(WorkshopResponseStatus.valueOf(event.getDomain().getApprovalStatus().name()))")
  public abstract AvroWorkshopApprovalResponse workshopApprovalEventToAvroWorkshopResponse(
      WorkshopApprovalResponseEvent event);

  protected String getWorkshopId(WorkshopApprovalResponseEvent event) {
    return event.getDomain().getWorkshop() != null ? event.getDomain().getWorkshop().getIdValue().toString() : null;
  }

  protected String getSpecialistId(WorkshopApprovalResponseEvent e) {
    return e.getDomain().getSpecialistId() != null ? e.getDomain().getSpecialistId().getValue().toString() : null;
  }
}
