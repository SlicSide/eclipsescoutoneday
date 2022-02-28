package org.eclipse.scout.apps.contacts.server.security;

import org.eclipse.scout.apps.contacts.shared.security.AccessControlService;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Replace;
import org.eclipse.scout.rt.security.AllPermissionCollection;
import org.eclipse.scout.rt.security.IPermissionCollection;

/**
 * @author RicoHahn
 */
@Replace
public class ServerAccessControlService extends AccessControlService {

  @Override
  protected IPermissionCollection execLoadPermissions(String userId) {
    return BEANS.get(AllPermissionCollection.class);
  }
}
