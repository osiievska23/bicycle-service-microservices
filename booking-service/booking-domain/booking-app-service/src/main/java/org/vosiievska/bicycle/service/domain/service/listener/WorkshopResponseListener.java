package org.vosiievska.bicycle.service.domain.service.listener;

import org.vosiievska.bicycle.service.domain.service.dto.response.WorkshopResponse;

/**
 * Primary port according to the Hexagonal Architecture
 */
public interface WorkshopResponseListener {

  void bookingApproved(WorkshopResponse workshopResponse);

  void bookingDeclined(WorkshopResponse workshopResponse);
}
