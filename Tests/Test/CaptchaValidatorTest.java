package Test;

import org.junit.jupiter.api.Test;
import server.CaptchaValidator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by admin on 4/3/2017.
 */
class CaptchaValidatorTest {
    @Test
    void isValidate_expectedCaptchaIsNull_ReturnFalse() {
        CaptchaValidator cv = new CaptchaValidator(new MockHttpRequest());

        assertFalse(cv.isValidate("12345", null));
    }
    @Test
    void isValidate_receivedCaptchaNull_ReturnFalse() {
        CaptchaValidator cv = new CaptchaValidator(new MockHttpRequest());

        assertFalse(cv.isValidate(null, "12345"));
    }
    @Test
    void isValidate_receivedCaptcha_Equals_ExpectedCaptcha_ReturnTrue() {
        CaptchaValidator cv = new CaptchaValidator(new MockHttpRequest());

        assertTrue(cv.isValidate("12345", "12345"));
    }

    @Test
    void isValidate_receivedCaptcha_DifferentFrom_ExpectedCaptcha_ReturnFalse() {
        CaptchaValidator cv = new CaptchaValidator(new MockHttpRequest());

        assertFalse(cv.isValidate("12345", "123"));
    }
    @Test
    void isValidate_receivedCaptcha_isUpperCase_ReturnFalse() {
        CaptchaValidator cv = new CaptchaValidator(new MockHttpRequest());

        assertFalse(cv.isValidate("ABCD", "abcd"));
    }
    @Test
    void isValidate_expectedCaptcha_isUpperCase_ReturnFalse() {
        CaptchaValidator cv = new CaptchaValidator(new MockHttpRequest());

        assertFalse(cv.isValidate("abcd", "ABCD"));
    }
    @Test
    void isValidate_expectedCaptcha_someCharIsUpperCase_ReturnFalse() {
        CaptchaValidator cv = new CaptchaValidator(new MockHttpRequest());

        assertFalse(cv.isValidate("abcd", "aBCb"));
    }
    @Test
    void isValidate_receivedCaptcha_someCharIsUpperCase_ReturnFalse() {
        CaptchaValidator cv = new CaptchaValidator(new MockHttpRequest());

        // htaphuong - change test case
        assertTrue(cv.isValidate("aBCd", "abcd"));
    }
}