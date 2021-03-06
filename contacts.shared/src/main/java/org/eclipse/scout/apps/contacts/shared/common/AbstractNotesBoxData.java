package org.eclipse.scout.apps.contacts.shared.common;

import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractFormFieldData;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;

import javax.annotation.Generated;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications recommended.
 */
@ClassId("ea38d806-a38b-4511-bbda-ecb2fe694e70-formdata")
@Generated(value = "org.eclipse.scout.apps.contacts.client.common.AbstractNotesBox", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public abstract class AbstractNotesBoxData extends AbstractFormFieldData {
  private static final long serialVersionUID = 1L;

  public Notes getNotes() {
    return getFieldByClass(Notes.class);
  }

  @ClassId("ce791f14-fca6-4f11-8476-89cbf905eb2e-formdata")
  public static class Notes extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;
  }
}
