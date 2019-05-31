package core.infrastracture.converter;

import core.domain.write.model.User.UserRole;
import org.sql2o.converters.Converter;
import org.sql2o.converters.ConverterException;

public class UserRoleConverter implements Converter<UserRole> {

  @Override
  public UserRole convert(Object val) throws ConverterException {
    return val instanceof String ? UserRole.valueOf((String) val) : null;
  }

  @Override
  public Object toDatabaseParam(UserRole val) {
    return val == null ? null : val.toString();
  }
}
