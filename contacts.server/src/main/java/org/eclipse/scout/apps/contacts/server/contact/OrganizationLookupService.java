package org.eclipse.scout.apps.contacts.server.contact;

import org.eclipse.scout.apps.contacts.server.sql.SQLs;
import org.eclipse.scout.apps.contacts.shared.contact.IOrganizationLookupService;
import org.eclipse.scout.rt.server.jdbc.lookup.AbstractSqlLookupService;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;

import java.util.List;

public class OrganizationLookupService extends AbstractSqlLookupService<String> implements IOrganizationLookupService {
  @Override
  protected String getConfiguredSqlSelect() {
    return SQLs.ORGANIZATION_LOOKUP;
  }
}
