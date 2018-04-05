package org.ricki.catalog.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.ricki.catalog.entity.SystemSetting;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SystemSettingDao {

  @Inject
  SessionFactory sessionFactory;

  public SystemSetting getSystemSetting(String owner, String name) {
    Session session = sessionFactory.getCurrentSession();
    return (SystemSetting) session.createQuery("from SystemSetting where name=:name and owner=:owner")
            .setParameter("name", name)
            .setParameter("owner", owner)
            .uniqueResult();
  }

  public void saveParameter(String owner, String name, String value) {
    Session session = sessionFactory.getCurrentSession();
    SystemSetting parameter = getSystemSetting(owner, name);
    if (parameter == null) {
      parameter = new SystemSetting();
      parameter.setOwner(owner);
      parameter.setName(name);
    }
    if (value.length() <= 128) {
      parameter.setShortData(null);
      parameter.setLongData(value.getBytes());
    } else {
      parameter.setLongData(null);
      parameter.setShortData(value);
    }

    session.saveOrUpdate(parameter);
  }
}
