package java;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import res.server.KaptchaPage;


/**
 * Created by Phuong Huynh on 3/18/2017.
 */
public class WicketApplication extends WebApplication {
    @Override
    public Class<? extends Page> getHomePage() {

        return KaptchaPage.class; // return default page
    }

    @Override
    protected void init() {

        super.init();
        addComponentInstantiationListener(new SpringComponentInjector(this));

    }
}
