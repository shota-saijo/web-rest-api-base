package core.domain.write.model;

import java.time.LocalDateTime;

public interface Entity<I extends Id> {

  I getId();

  UserId getCreatedBy();

  LocalDateTime getCreatedAt();

  UserId getUpdatedBy();

  LocalDateTime getUpdatedAt();
}
