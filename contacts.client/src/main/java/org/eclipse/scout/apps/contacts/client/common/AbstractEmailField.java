package org.eclipse.scout.apps.contacts.client.common;

import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.text.TEXTS;

import java.util.regex.Pattern;

public abstract class AbstractEmailField extends AbstractStringField {

  // end::email[]
  // http://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
  // tag::email[]
  private static final String EMAIL_PATTERN = // <1>
    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
      "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

  @Override
  protected String getConfiguredLabel() {
    return TEXTS.get("Email");
  }

  @Override // <2>
  protected int getConfiguredMaxLength() {
    return 64;
  }

  @Override // <3>
  protected String execValidateValue(String rawValue) {
    if (rawValue != null && !Pattern.matches(EMAIL_PATTERN, rawValue)) {
      throw new VetoException(TEXTS.get("BadEmailAddress")); // <4>
    }

    return rawValue; // <5>
  }
}
