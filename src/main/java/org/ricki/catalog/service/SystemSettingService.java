package org.ricki.catalog.service;

import com.vaadin.spring.annotation.UIScope;
import org.ricki.catalog.dao.SystemSettingDao;
import org.ricki.catalog.entity.SystemSetting;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Transactional
@UIScope
public class SystemSettingService {

  @Inject
  SystemSettingDao systemSettingDao;

  public String loadParameter(String owner, String name, String defaultValue) {
    SystemSetting parameter = systemSettingDao.getSystemSetting(owner, name);
    String value = defaultValue;
    if (parameter != null) {
      if (parameter.getLongData() != null && parameter.getLongData().length > 0) {
        return new String(parameter.getLongData());
      } else {
        if (parameter.getShortData() != null) {
          return parameter.getShortData();
        }
      }
    }
    return defaultValue;
  }

  public void saveParameter(String owner, String name, String value) {
    systemSettingDao.saveParameter(owner, name, value);
  }
}
