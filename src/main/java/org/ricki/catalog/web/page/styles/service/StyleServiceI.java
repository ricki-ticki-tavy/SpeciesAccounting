package org.ricki.catalog.web.page.styles.service;

import org.ricki.catalog.service.base.BaseNamedEntityService;
import org.ricki.catalog.web.page.styles.entity.UserWebStyle;

import java.util.Map;

public interface StyleServiceI extends BaseNamedEntityService<UserWebStyle> {
  Map<SystemStyleEnum, UserWebStyle> initSystemStyles();
}
