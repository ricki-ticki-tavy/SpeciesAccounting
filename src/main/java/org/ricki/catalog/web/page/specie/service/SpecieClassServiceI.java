package org.ricki.catalog.web.page.specie.service;

import org.ricki.catalog.service.base.BaseNamedEntityFIlteredSelectorService;
import org.ricki.catalog.web.page.specie.entity.SpecieClass;
import org.ricki.catalog.web.page.styles.entity.UserWebStyle;
import org.ricki.catalog.web.page.styles.service.SystemStyleEnum;

import java.util.Map;

public interface SpecieClassServiceI extends BaseNamedEntityFIlteredSelectorService<SpecieClass> {
  void initSystemActions(Map<SystemStyleEnum, UserWebStyle> systemStyles);
}
