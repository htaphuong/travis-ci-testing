package server;

import org.apache.wicket.Request;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.protocol.http.WebRequest;

import javax.servlet.http.HttpServletRequest;

public class HttpRequest implements CaptchaRequester {

    public String request() {
        Request request = RequestCycle.get().getRequest();
        HttpServletRequest httpRequest = ((WebRequest) request)
                .getHttpServletRequest();

        return (String) httpRequest.getSession()
                .getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
    }
}
