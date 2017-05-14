package res.server;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.wicket.Request;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.markup.html.image.resource.DynamicImageResource;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;

/**
 * Created by Phuong Huynh on 3/18/2017.
 */
public class CaptchaImage extends NonCachingImage {
    private static final String CAPTCHA_PRODUCER = "captchaProducer";

    // inject via Spring
    @SpringBean
    private DefaultKaptcha captchaProducer;

    // private DefaultKaptcha captchaProducer;
    public CaptchaImage(String id) {
        super(id);

        setImageResource(new DynamicImageResource() {

            public byte[] getImageData() {
                ByteArrayOutputStream os = new ByteArrayOutputStream();

                //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);

                try {
                    BufferedImage bi = getImageCaptchaService();
                    ImageIO.write(bi, "JPEG", os);
                    return os.toByteArray();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            };

            private BufferedImage getImageCaptchaService() {

                Request request = RequestCycle.get().getRequest();
                HttpServletRequest httpRequest = ((WebRequest) request)
                        .getHttpServletRequest();

                String capText = captchaProducer.createText();

                // store the text in the session
                httpRequest.getSession().setAttribute(
                        Constants.KAPTCHA_SESSION_KEY, capText);

                // create the image with the text
                BufferedImage bi = captchaProducer.createImage(capText);

                return bi;

            }
        });

    }



}
