package org.eclipse.scout.apps.contacts.shared.contact;

import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.lookup.ILookupService;

@TunnelToServer
public interface IOrganizationLookupService extends ILookupService<String> {

}
