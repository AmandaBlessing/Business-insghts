package org.insight;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Handler;
import org.insight.Databases.Business;
import org.insight.Databases.Client;
import org.insight.Databases.Director;
import org.insight.Databases.Results;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;

import java.util.*;


public class Controller {


    public static final Handler home = context -> {
        HashMap<String, List<Client>> clients = new HashMap<>();
        clients.put("clients", Business.getClientList());
        context.render("/index.html", clients);
    };
    public static final Handler add_client = context -> {
        String companyName = context.formParam("companyName");
        Client client = new Client(companyName);
        int numberOfDirectors = Integer.parseInt(context.formParam("numberDirectors"));
        for(int i = 1; i <= numberOfDirectors; i++){
            String directorName = context.formParam("directorName"+i);
            String directorSurname = context.formParam("directorSurname"+i);
            String email = context.formParam("directorEmail"+i);
            client.addDirector(new Director(directorName, directorSurname, email));
        }
        Business.addClient(client);
        context.redirect(Routes.HOME);

    };

    private static void sendEmail(String to, String subject, String body) throws EmailException {
        // Set your email configuration (you need to replace these with your own values)
        String host = "mail.stocktally.co.za";
        String username = "businessinsights@stocktally.co.za";
        String password = "CBcZ}3Dz$*?_";
        int port = 587;
        SimpleEmail email = new SimpleEmail();

        // Set the SMTP server details
        email.setHostName(host);
        email.setSmtpPort(port); // Set your SMTP port
        email.setAuthenticator(new DefaultAuthenticator(username, password));
        email.setStartTLSEnabled(false); // Use TLS

        // Set the sender and recipient
        email.setFrom(username);
        email.addTo(to);

        // Set email subject and content
        email.setSubject(subject);
        email.setMsg(body);

        // Send the email
        email.send();

        System.out.println("Email sent successfully!");
    }

    public  static  final Handler test_action= ctx -> {
        String[] topic = {"purpose", "sustainability", "performance", "conformance"};
        Results result = new Results();
        String email = ctx.formParam("email");
        String company = ctx.formParam("companyName");
        Director director = Business.getClientByCompanyName(company).getDirectorByEmail(email);
        int j = 1;
        for(int i = 0; i < 4; i++){
            List<Integer> results = new ArrayList<>();
            while(true){
                try {
                    int answer = Integer.parseInt(ctx.formParam(topic[i] + j));
                    results.add(answer);
                    j++;
                }
                catch (Exception e){
                    director.addToMap(topic[i], results );
                    break;
                }
            }
        }
    };
    public  static  final Handler get_clients= ctx -> {
        ObjectMapper ob = new ObjectMapper();
        ctx.json(ob.writeValueAsString(Business.getClientList()));

    };

    public static final Handler get_clients_by_company = context -> {
        String company = context.pathParam("company");
        context.json(Business.getClientByCompanyName(company));

    };

    public static final Handler send_email = context ->{
        String sampleEmail = "mrkphughes@gmail.com";
//        String hash = context.pathParam("hashcode");
        String ipAddress = getLocalIPv4Address(); // Replace with your method to get the public IP
        String url = "http://" + ipAddress + ":7000/questions";
        sendEmail(sampleEmail, "Business Insights Questions","Please find link to questions, please complete all questions "+ url);

    };
    public static final Handler questions = context ->{

        context.render("/question.html");
    };

    public static final Handler directors_by_company = context ->{
        Client  client = Business.getClientByCompanyName(context.pathParam("companyName"));
        HashMap<String, List<Director>> directors = new HashMap<>();
        directors.put("directors", client.getDirectorList());
        context.header("Access-Control-Allow-Origin","*");
        context.json(client.getDirectorList());
//        context.render("/index.html", directors);
    };



    public static final Handler addnewclient = context ->{

        context.render("/addclient.html");
    };
    public static String getLocalIPv4Address() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress.getHostAddress().contains(":")) {
                        // IPv6 address, skip
                        continue;
                    }
                    return inetAddress.getHostAddress();
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "localhost";
    }

    public static void main(String[] args) throws EmailException {
        sendEmail("mrkphughes@gmail.com", "Test", "hello this is a test");
    }


}
