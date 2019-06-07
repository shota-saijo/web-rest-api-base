package core.domain.write.model;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public abstract class AbstractEntity<I extends Id> implements Entity<I> {

  private UserId createdBy;

  private LocalDateTime createdAt;

  private UserId updatedBy;

  private LocalDateTime updatedAt;

  public void created(UserId by) {
    LocalDateTime now = LocalDateTime.now();
    createdBy = by;
    createdAt = now;
    updatedBy = by;
    updatedAt = now;
  }

  public void updated(UserId by) {
    LocalDateTime now = LocalDateTime.now();
    updatedBy = by;
    updatedAt = now;
  }

  @Override
  public boolean isNew() {
    return createdAt.isEqual(updatedAt);
  }
}
