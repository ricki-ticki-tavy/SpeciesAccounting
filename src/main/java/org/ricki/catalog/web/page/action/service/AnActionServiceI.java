package org.ricki.catalog.web.page.action.service;

import org.ricki.catalog.service.base.BaseNamedEntityService;
import org.ricki.catalog.web.page.action.entity.AnAction;
import org.ricki.catalog.web.page.styles.entity.UserWebStyle;
import org.ricki.catalog.web.page.styles.service.SystemStyleEnum;

import java.util.Map;

public interface AnActionServiceI extends BaseNamedEntityService<AnAction> {
  void initSystemActions(Map<SystemStyleEnum, UserWebStyle> systemStyles);
}
