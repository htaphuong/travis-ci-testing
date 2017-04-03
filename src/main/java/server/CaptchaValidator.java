package server;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.AbstractValidator;

public class CaptchaValidator extends AbstractValidator<String> {


    private static final long serialVersionUID = 1L;
    private final CaptchaRequester requester;
    private String INVALID_CODE = "captcha.invalid";

    public CaptchaValidator(CaptchaRequester requester){
        this.requester = requester;
    }
    public void onValidate(IValidatable validatable) {
        String kaptchaReceived = (String) validatable.getValue();

        String kaptchaExpected = this.requester.request();

        if (!isValidate(kaptchaReceived, kaptchaExpected)) {
            error(validatable);
        }
    }

    public boolean isValidate(String kaptchaReceived, String kaptchaExpected) {
        if (kaptchaReceived == null
                || !kaptchaReceived.equalsIgnoreCase(kaptchaExpected) || !kaptchaExpected.equals(kaptchaReceived)) {
            return false;
        }
        return true;
    }

    // validate on numm value as well
    @Override
    public boolean validateOnNullValue() {

        return true;

    }

    @Override
    protected String resourceKey() {
        return INVALID_CODE;
    }
}

