package core.domain.write.model;

import java.util.Objects;


public abstract class StringValueObject implements ValueObject {

  private String value;

  public StringValueObject(String value) {
    this.value = value;
  }

  public final String value() {
    return value;
  }

  @Override
  public final String toString() {
    return value();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    StringValueObject that = (StringValueObject) o;

    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return value != null ? value.hashCode() : 0;
  }
}
