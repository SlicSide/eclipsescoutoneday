package org.eclipse.scout.apps.contacts.client.person;

import org.eclipse.scout.apps.contacts.client.common.AbstractDirtyFormHandler;
import org.eclipse.scout.apps.contacts.client.common.CountryLookupCall;
import org.eclipse.scout.apps.contacts.client.person.PersonForm.MainBox.CancelButton;
import org.eclipse.scout.apps.contacts.client.person.PersonForm.MainBox.DetailsBox;
import org.eclipse.scout.apps.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox;
import org.eclipse.scout.apps.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox.AddressBox;
import org.eclipse.scout.apps.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox.AddressBox.LocationBox.CityField;
import org.eclipse.scout.apps.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox.AddressBox.LocationBox.CountryField;
import org.eclipse.scout.apps.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox.EmailField;
import org.eclipse.scout.apps.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox.MobileField;
import org.eclipse.scout.apps.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox.PhoneField;
import org.eclipse.scout.apps.contacts.client.person.PersonForm.MainBox.DetailsBox.NotesBox;
import org.eclipse.scout.apps.contacts.client.person.PersonForm.MainBox.DetailsBox.NotesBox.NotesField;
import org.eclipse.scout.apps.contacts.client.person.PersonForm.MainBox.DetailsBox.WorkBox;
import org.eclipse.scout.apps.contacts.client.person.PersonForm.MainBox.DetailsBox.WorkBox.EmailWorkField;
import org.eclipse.scout.apps.contacts.client.person.PersonForm.MainBox.DetailsBox.WorkBox.OrganizationField;
import org.eclipse.scout.apps.contacts.client.person.PersonForm.MainBox.DetailsBox.WorkBox.PhoneWorkField;
import org.eclipse.scout.apps.contacts.client.person.PersonForm.MainBox.DetailsBox.WorkBox.PositionField;
import org.eclipse.scout.apps.contacts.client.person.PersonForm.MainBox.GeneralBox;
import org.eclipse.scout.apps.contacts.client.person.PersonForm.MainBox.GeneralBox.*;
import org.eclipse.scout.apps.contacts.client.person.PersonForm.MainBox.OkButton;
import org.eclipse.scout.apps.contacts.shared.Icons;
import org.eclipse.scout.apps.contacts.shared.person.GenderCodeType;
import org.eclipse.scout.apps.contacts.shared.person.IPersonService;
import org.eclipse.scout.apps.contacts.shared.person.PersonFormData;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractDateField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.imagefield.AbstractImageField;
import org.eclipse.scout.rt.client.ui.form.fields.radiobuttongroup.AbstractRadioButtonGroup;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

import java.util.regex.Pattern;

@ClassId("e7c36951-1434-471f-963c-9ac65b76360e")
@FormData(value = PersonFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class PersonForm extends AbstractForm {

  private String personId;

  @FormData
  public String getPersonId() {
    return personId;
  }

  @FormData
  public void setPersonId(String personId) {
    this.personId = personId;
  }

  @Override
  public Object computeExclusiveKey() {
    return getPersonId();
  }

  @Override
  protected int getConfiguredDisplayHint() {
    return IForm.DISPLAY_HINT_VIEW;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Person");
  }

  public void startModify() {
    startInternalExclusive(new ModifyHandler());
  }

  public void startNew() {
    startInternal(new NewHandler());
  }

  public AddressBox getAddressBox() {
    return getFieldByClass(AddressBox.class);
  }

  public CancelButton getCancelButton() {
    return getFieldByClass(CancelButton.class);
  }

  public PictureUrlField getPictureUrlField() {
    return getFieldByClass(PictureUrlField.class);
  }

  public NotesBox getNotesBox() {
    return getFieldByClass(NotesBox.class);
  }

  public NotesField getNotesField() {
    return getFieldByClass(NotesField.class);
  }

  public OrganizationField getOrganizationField() {
    return getFieldByClass(OrganizationField.class);
  }

  public ContactInfoBox getPersonDetailsBox() {
    return getFieldByClass(ContactInfoBox.class);
  }

  public DateOfBirthField getDateOfBirthField() {
    return getFieldByClass(DateOfBirthField.class);
  }

  public DetailsBox getDetailsBox() {
    return getFieldByClass(DetailsBox.class);
  }

  public EmailField getEmailField() {
    return getFieldByClass(EmailField.class);
  }

  public EmailWorkField getEmailWorkField() {
    return getFieldByClass(EmailWorkField.class);
  }

  public FirstNameField getFirstNameField() {
    return getFieldByClass(FirstNameField.class);
  }

  public GenderGroup getGenderGroup() {
    return getFieldByClass(GenderGroup.class);
  }

  public GeneralBox getGeneralBox() {
    return getFieldByClass(GeneralBox.class);
  }

  public LastNameField getLastNameField() {
    return getFieldByClass(LastNameField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public MobileField getMobileField() {
    return getFieldByClass(MobileField.class);
  }

  public OkButton getOkButton() {
    return getFieldByClass(OkButton.class);
  }

  public PhoneField getPhoneField() {
    return getFieldByClass(PhoneField.class);
  }

  public PhoneWorkField getPhoneWorkField() {
    return getFieldByClass(PhoneWorkField.class);
  }

  public PictureField getPictureField() {
    return getFieldByClass(PictureField.class);
  }

  public PositionField getPositionField() {
    return getFieldByClass(PositionField.class);
  }

  public WorkBox getWorkBox() {
    return getFieldByClass(WorkBox.class);
  }

  protected String calculateSubTitle() {
    return StringUtility.join(" ",
      getFirstNameField().getValue(),
      getLastNameField().getValue());
  }

  @Order(10)
  @ClassId("8a654a4a-4e4a-4aea-add6-832b41f4b95d")
  public class MainBox extends AbstractGroupBox {
    @Order(10)
    @ClassId("3c3a7f91-f8c9-4b87-bbb8-7c32beb3edeb")
    public class GeneralBox extends AbstractGroupBox {
      @Order(10)
      @ClassId("617ffd40-0d69-4d02-b4f8-90c28c68c6ce")
      public class PictureUrlField extends AbstractStringField {

        @Override
        protected boolean getConfiguredVisible() {
          return false;
        }
      }

      @Order(20)
      @ClassId("6366a23e-f8ba-4b50-b814-202e63daffc8")
      public class PictureField extends AbstractImageField {

        @Override
        protected Class<PictureUrlField> getConfiguredMasterField() {
          return PictureUrlField.class;
        }

        @Override
        protected void execChangedMasterValue(Object newMasterValue) {
          updateImage((String) newMasterValue);
        }

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected int getConfiguredGridH() {
          return 5;
        }

        @Override
        protected boolean getConfiguredAutoFit() {
          return true;
        }

        @Override
        protected String getConfiguredImageId() {
          return Icons.PersonSolid;
        }

        protected void updateImage(String url) {
          setImageUrl(url);
        }
      }

      @Order(30)
      @ClassId("359be835-439f-456e-9b0d-c832b034a298")
      public class FirstNameField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("FirstName");
        }
      }

      @Order(40)
      @ClassId("8679ade5-21fb-470e-8f00-13bd15199101")
      public class LastNameField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("LastName");
        }
      }

      @Order(50)
      @ClassId("7c602360-9daa-44b8-abb6-94ccf9b9db59")
      public class DateOfBirthField extends AbstractDateField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("DateOfBirth");
        }
      }

      @Order(60)
      @ClassId("b9d0593e-3938-4f97-bdca-fdb6a1ce1d77")
      public class GenderGroup extends AbstractRadioButtonGroup<String> {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Gender");
        }

        @Override
        protected Class<? extends ICodeType<?, String>> getConfiguredCodeType() {
          return GenderCodeType.class;
        }
      }
    }

    @Order(20)
    @ClassId("440b856a-c3f2-4bb0-9187-446a7b3bdd43")
    public class DetailsBox extends AbstractTabBox {

      @Order(10)
      @ClassId("be5ba3ce-77fc-49ea-a7df-85e92e8acb1c")
      public class ContactInfoBox extends AbstractGroupBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("ContactInfo");
        }

        @Order(10)
        @ClassId("0f6ec4b3-0dec-40db-a9b1-b5dd2f84db59")
        public class AddressBox extends AbstractGroupBox {
          @Override
          protected boolean getConfiguredBorderVisible() {
            return false;
          }

          @Override
          protected int getConfiguredGridH() { // <1>
            return 3;
          }

          @Override
          protected int getConfiguredGridW() { // <1>
            return 1;
          }

          @Override
          protected int getConfiguredGridColumnCount() { // <2>
            return 1;
          }
          //end::addressBox[]

          public StreetField getStreetField() {
            return getFieldByClass(StreetField.class);
          }

          public LocationBox getLocationBox() {
            return getFieldByClass(LocationBox.class);
          }

          public CityField getCityField() {
            return getFieldByClass(CityField.class);
          }

          public CountryField getCountryField() {
            return getFieldByClass(CountryField.class);
          }

          @Order(10)
          @ClassId("8bc1b9dd-6adf-4a48-8ed7-a7c0f0367987")
          public class StreetField extends AbstractStringField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("Street");
            }
          }

          @Order(20)
          @ClassId("08c8e8f0-b6fc-4503-b9a0-ae03481ff5b6")
          public class LocationBox extends AbstractSequenceBox {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("Location");
            }

            @Override
            protected boolean getConfiguredAutoCheckFromTo() {
              return false;
            }

            @Order(1000)
            @ClassId("9df5daba-9b77-442d-aadd-18d5d1bd59e2")
            public class CityField extends AbstractStringField {
              @Override
              protected String getConfiguredLabel() {
                return TEXTS.get("City");
              }

              @Override
              protected byte getConfiguredLabelPosition() {
                return LABEL_POSITION_ON_FIELD;
              }
            }

            @Order(20)
            @ClassId("ed60b3a6-c3aa-4cd3-b82e-341427f744a3")
            public class CountryField extends AbstractSmartField<String> {
              @Override
              protected String getConfiguredLabel() {
                return TEXTS.get("Country");
              }

              @Override
              protected byte getConfiguredLabelPosition() {
                return LABEL_POSITION_ON_FIELD;
              }

              @Override
              protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
                return CountryLookupCall.class;
              }
            }


          }

        }

        @Order(20)
        @ClassId("136a3c0c-91bf-427c-8020-507bfd391098")
        public class PhoneField extends AbstractStringField {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Phone");
          }
        }

        @Order(30)
        @ClassId("7dc64c60-5713-4376-a3e0-41c0a8e2b503")
        public class MobileField extends AbstractStringField {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Mobile");
          }
        }

        // tag::email[]
        @Order(40)
        @ClassId("5f9d9363-8e57-4151-b281-7d401e64702c")
        public class EmailField extends AbstractStringField {

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
        // end::email[]
        // tag::layout[]
      }


      @Order(20)
      @ClassId("965d910d-6016-40d2-a1ef-7653ba483966")
      public class WorkBox extends AbstractGroupBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Work");
        }

        @Order(10)
        @ClassId("156f883a-f7ae-443b-be6e-26dcd58b7351")
        public class PositionField extends AbstractStringField {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Position");
          }
        }

        @Order(20)
        @ClassId("1e61c03d-bffb-4a2c-a9e1-40ebb32f69ab")
        public class OrganizationField extends AbstractStringField {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Organization");
          }
        }

        @Order(30)
        @ClassId("2a0b13d0-330b-458f-a53d-7f37f803c853")
        public class PhoneWorkField extends AbstractStringField {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Phone");
          }
        }

        @Order(40)
        @ClassId("0fceebb4-5d28-4c62-9509-cf57a6e89c95")
        public class EmailWorkField extends AbstractStringField {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("E-Mail");
          }
        }


      }

      @Order(30)
      @ClassId("85fe25ea-fed2-4d31-bc9a-5ef7791f14c8")
      public class NotesBox extends AbstractGroupBox {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Notes");
        }

        @Order(10)
        @ClassId("ce791f14-fca6-4f11-8476-89cbf905eb2e")
        public class NotesField extends AbstractStringField {

          @Override
          protected int getConfiguredGridH() {
            return 4;
          }

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected boolean getConfiguredMultilineText() {
            return true;
          }
        }
      }
    }


    @Order(30)
    @ClassId("20a6bb04-a994-4602-8240-a6951cfef091")
    public class OkButton extends AbstractOkButton {

    }

    @Order(40)
    @ClassId("23453df3-ef17-4bc3-a205-f2cae3882f74")
    public class CancelButton extends AbstractCancelButton {

    }
  }

  public class NewHandler extends AbstractFormHandler {
    @Override
    protected void execStore() {
      IPersonService service = BEANS.get(IPersonService.class);
      PersonFormData formData = new PersonFormData();
      exportFormData(formData);
      formData = service.create(formData);
      importFormData(formData);
    }
  }

  public class ModifyHandler extends AbstractFormHandler {
    @Override
    protected void execLoad() {
      IPersonService service = BEANS.get(IPersonService.class);
      PersonFormData formData = new PersonFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);

      getForm().setSubTitle(calculateSubTitle());
    }

    @Override
    protected void execStore() {
      IPersonService service = BEANS.get(IPersonService.class);
      PersonFormData formData = new PersonFormData();
      exportFormData(formData);
      service.store(formData);
    }
  }

  public class ModifyDirtyHandler extends AbstractDirtyFormHandler {

    @Override
    protected void execLoad() {
      IPersonService service = BEANS.get(IPersonService.class);
      PersonFormData formData = new PersonFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);

      getForm().setSubTitle(calculateSubTitle());
    }

    @Override
    protected void execStore() {
      IPersonService service = BEANS.get(IPersonService.class);
      PersonFormData formData = new PersonFormData();
      exportFormData(formData);
      service.store(formData);
    }

    @Override
    protected void execDirtyStatusChanged(boolean dirty) {
      getForm().setSubTitle(calculateSubTitle());
    }

    @Override
    protected boolean getConfiguredOpenExclusive() {
      return true;
    }
  }

  //This new handler is used in the application:
  public class NewDirtyHandler extends AbstractDirtyFormHandler {

    @Override
    protected void execStore() {
      IPersonService service = BEANS.get(IPersonService.class);
      PersonFormData formData = new PersonFormData();
      exportFormData(formData);
      formData = service.create(formData);
      importFormData(formData);
    }

    @Override
    protected void execDirtyStatusChanged(boolean dirty) {
      getForm().setSubTitle(calculateSubTitle());
    }
  }
}

