package org.ricki.catalog.service.base;

import org.ricki.catalog.entity.abstracts.BaseNamedEntity;

/**
 * @param <E>
 * @Depricated
 */
public abstract class BaseNamedServiceDao<E extends BaseNamedEntity> {
//  @Inject
//  SessionFactory sessionFactory;
//
//  public UserWebStyle findByName(String name) {
//    Session session = sessionFactory.getCurrentSession();
//    List<UserWebStyle> userWebStyleList = session
//            .createQuery("from UserWebStyle where name=:name")
//            .setParameter("name", name)
//            .list();
//    if (userWebStyleList != null && userWebStyleList.size() > 0) {
//      return userWebStyleList.get(0);
//    } else {
//      return null;
//    }
//  }


}
