package server;

import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;

/**
 * Created by Phuong Huynh on 3/18/2017.
 */
public class KaptchaPage extends WebPage {
    private String captchaInput;

    public KaptchaPage(final PageParameters parameters) {

        final CaptchaImage captchaImage = new CaptchaImage("kaptchaImage");
        captchaImage.setOutputMarkupId(true);

        TextField<String> captchaTF = new TextField<String>("captcha",
                new PropertyModel<String>(this, "captchaInput"));
        captchaTF.add(new CaptchaValidator());

        Form<?> form = new Form<Void>("form") {
            @Override
            protected void onSubmit() {

                info("Image words are correct!!!");
            };
        };

        form.add(new AjaxFallbackLink("link") {
            @Override
            public void onClick(final AjaxRequestTarget target) {

                captchaImage.detach();

                if (target != null) {
                    target.addComponent(captchaImage);
                } else {
                    // javascript is disable
                }
            }
        }.add(captchaImage));

        form.add(captchaTF);
        add(form);
        add(new FeedbackPanel("feedback"));
    }

}

