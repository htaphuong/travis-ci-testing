import org.junit.Assert;
import org.junit.Test;
import server.CaptchaValidator;

/**
 * Created by admin on 4/3/2017.
 */
public class CaptchaValidatorTest {

    @Test
    public void isValidate_expectedCaptchaIsNull_ReturnFalse() {
        CaptchaValidator cv = new CaptchaValidator(new MockHttpRequest());

        Assert.assertFalse(cv.isValidate("12345", null));
    }

    @Test
    public void isValidate_receivedCaptchaNull_ReturnFalse() {
        CaptchaValidator cv = new CaptchaValidator(new MockHttpRequest());

        Assert.assertFalse(cv.isValidate(null, "12345"));
    }
    @Test
    public void isValidate_receivedCaptcha_Equals_ExpectedCaptcha_ReturnTrue() {
        CaptchaValidator cv = new CaptchaValidator(new MockHttpRequest());

        Assert.assertTrue(cv.isValidate("12345", "12345"));
    }

    @Test
    public void isValidate_receivedCaptcha_DifferentFrom_ExpectedCaptcha_ReturnFalse() {
        CaptchaValidator cv = new CaptchaValidator(new MockHttpRequest());

        Assert.assertFalse(cv.isValidate("12345", "123"));
    }
    @Test
    public void isValidate_receivedCaptcha_isUpperCase_ReturnFalse() {
        CaptchaValidator cv = new CaptchaValidator(new MockHttpRequest());

        Assert.assertFalse(cv.isValidate("ABCD", "abcd"));
    }
    @Test
    public void isValidate_expectedCaptcha_isUpperCase_ReturnFalse() {
        CaptchaValidator cv = new CaptchaValidator(new MockHttpRequest());

        Assert.assertFalse(cv.isValidate("abcd", "ABCD"));
    }
    @Test
    public void isValidate_expectedCaptcha_someCharIsUpperCase_ReturnFalse() {
        CaptchaValidator cv = new CaptchaValidator(new MockHttpRequest());

        Assert.assertFalse(cv.isValidate("abcd", "aBCb"));
    }
    @Test
    public void isValidate_receivedCaptcha_someCharIsUpperCase_ReturnFalse() {
        CaptchaValidator cv = new CaptchaValidator(new MockHttpRequest());

        Assert.assertFalse(cv.isValidate("aBCd", "abcd"));
    }
}