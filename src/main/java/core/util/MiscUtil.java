package core.util;

public class MiscUtil {
  public static boolean isNullOrEmpty(String value) {
    return value == null || value.isEmpty();
  }

  public static boolean hasValue(String value) {
    return !isNullOrEmpty(value);
  }
}
