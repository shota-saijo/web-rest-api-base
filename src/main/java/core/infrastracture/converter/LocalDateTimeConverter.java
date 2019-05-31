package core.infrastracture.converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.sql2o.converters.Converter;
import org.sql2o.converters.ConverterException;

public class LocalDateTimeConverter implements Converter<LocalDateTime> {

  @Override
  public LocalDateTime convert(Object val) throws ConverterException {
    return val instanceof Timestamp ? ((Timestamp) val).toLocalDateTime() : null;
  }

  @Override
  public Object toDatabaseParam(LocalDateTime val) {
    return val == null ? null : Timestamp.valueOf(val);
  }
}
