package org.eclipse.scout.apps.contacts.server.contact;

import org.eclipse.scout.apps.contacts.shared.contact.OrganizationLookupCall;
import org.eclipse.scout.rt.server.IServerSession;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;
import org.eclipse.scout.rt.testing.platform.runner.RunWithSubject;
import org.eclipse.scout.rt.testing.server.runner.RunWithServerSession;
import org.eclipse.scout.rt.testing.server.runner.ServerTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWithSubject("anonymous")
@RunWith(ServerTestRunner.class)
@RunWithServerSession(IServerSession.class)
public class OrganizationLookupCallTest {
// TODO [RicoHahn] add test cases

    protected OrganizationLookupCall createLookupCall() {
        return new OrganizationLookupCall();
    }


}
