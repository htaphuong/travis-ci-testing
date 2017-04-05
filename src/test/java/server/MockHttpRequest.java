package java.server;

import server.CaptchaRequester;

/**
 * Created by admin on 4/3/2017.
 */
public class MockHttpRequest implements CaptchaRequester {

    public String request() {
        return "";
    }
}
