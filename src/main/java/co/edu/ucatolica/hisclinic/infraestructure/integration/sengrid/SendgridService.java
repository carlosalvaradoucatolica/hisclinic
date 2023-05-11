package co.edu.ucatolica.hisclinic.infraestructure.integration.sengrid;

import co.edu.ucatolica.hisclinic.infraestructure.config.sengrid.SengridConfig;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class SendgridService {
    private final SengridConfig sengridConfig;

    public String sendEmailVerification(String email, String customerName, String confirmationCode){

        Email from = new Email("cralvarado92@ucatolica.edu.co");
        from.setName("Hisclinic");
        String subject = "";
        Email to = new Email(email);

        Content content = new Content("text/html", "I'm replacing the <strong>body tag</strong>");

        Mail mail = new Mail(from, subject, to, content);
        mail.personalization.get(0).addDynamicTemplateData("confirmationCode",confirmationCode);
        mail.personalization.get(0).addDynamicTemplateData("customerName",customerName);
        mail.setTemplateId("d-b382ffb40fe348b8922f19b8ea9337e2");

        SendGrid sg = sengridConfig.getSendGrid();
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
            return request.toString();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }

    }
}
