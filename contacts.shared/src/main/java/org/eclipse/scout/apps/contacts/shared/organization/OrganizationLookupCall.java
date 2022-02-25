package org.eclipse.scout.apps.contacts.shared.organization;

import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.shared.services.lookup.ILookupService;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;

@ClassId("9c7910a8-82a4-47be-a401-c950b8531ce8")
public class OrganizationLookupCall extends LookupCall<String> {
    private static final long serialVersionUID = 1L;

    @Override
    protected Class<? extends ILookupService<String>> getConfiguredService() {
        return IOrganizationLookupService.class;
    }
}
