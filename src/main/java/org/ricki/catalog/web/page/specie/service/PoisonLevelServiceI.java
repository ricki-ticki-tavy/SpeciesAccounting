package org.ricki.catalog.web.page.specie.service;

import org.ricki.catalog.service.base.BaseNamedEntityFIlteredSelectorService;
import org.ricki.catalog.web.page.specie.entity.PoisonLevel;
import org.ricki.catalog.web.page.styles.entity.UserWebStyle;
import org.ricki.catalog.web.page.styles.service.SystemStyleEnum;

import java.util.Map;

public interface PoisonLevelServiceI extends BaseNamedEntityFIlteredSelectorService<PoisonLevel> {
  void initSystemActions(Map<SystemStyleEnum, UserWebStyle> systemStyles);
}
