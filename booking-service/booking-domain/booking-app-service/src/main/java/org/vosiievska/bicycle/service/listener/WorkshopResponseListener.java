package org.vosiievska.bicycle.service.listener;

import org.vosiievska.bicycle.service.dto.response.WorkshopResponse;

/**
 * Primary port according to the Hexagonal Architecture
 */
public interface WorkshopResponseListener {

  void bookingApproved(WorkshopResponse workshopResponse);

  void bookingDeclined(WorkshopResponse workshopResponse);
}
