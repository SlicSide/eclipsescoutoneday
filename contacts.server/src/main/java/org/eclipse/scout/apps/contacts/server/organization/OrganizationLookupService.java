package org.eclipse.scout.apps.contacts.server.organization;

import org.eclipse.scout.apps.contacts.server.sql.SQLs;
import org.eclipse.scout.apps.contacts.shared.organization.IOrganizationLookupService;
import org.eclipse.scout.rt.server.jdbc.lookup.AbstractSqlLookupService;

public class OrganizationLookupService extends AbstractSqlLookupService<String> implements IOrganizationLookupService {
  @Override
  protected String getConfiguredSqlSelect() {
    return SQLs.ORGANIZATION_LOOKUP;
  }
}
