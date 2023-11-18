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
//        String email = ctx.formParam("email");
//        String company = ctx.pathParam("company");
//        Director director = Business.getClientByCompanyName(company).getDirectorByEmail(email);
        AiAnalyst ai = new AiAnalyst();
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
//                    director.addToMap(topic[i], results );
                    break;
                }
            }
        }
        String response = ai.sendRequest("You are a business analyst tasked with analyzing results of the following questions There is an up-to-date board charter in place approprate to the life-stage of the organisation and its current structure.\n" +
                "There is an up-to-date delegation of authority policy in place, which is effectively monitored." +
                "The managing director's report effectively communicates status, identifies issues, and clearly indicates a linkage to the business plan." +
                "Board packs are structured and used effectively to guide the board to make decisions and hold those authorised to make decisions accountable." +
                "The board calendar is up-to-date and relevant for the timing requirements of the enterprise and its strategic focus.\n" +
                "All board meeting dates (and committee dates if applicable) for the year are scheduled in directors' diaries and align with the board calendar." +
                "The organisation has an up-to-date strategy (identifying the companies purpose and medium-term vision) and a business plan (identifying the necessary next steps) that guides its focus and direction.\n" +
                "The board ensures a strategic focus in every board meeting to ensure that board papers relevant to the theme agreed upon for that meeting are prepared and that necessary key strategic issues are addressed proactively.\n" +
                "There is clarity regarding the organisation's culture, values, and beliefs." +
                "The culture and values of the organisation are demonstrated by the board, setting the appropriate tone for the rest of the organisation to follow." +
                "The board's conduct is characterised by trust, respect, candour, professionalism, accountability, diligence and commitment." +
                "The organisation has developed a culture of accountability for performance." +
                "Where the board has committee structures in place (complete as appopriate)" +
                "The board committees (where appropriate) have approved terms of reference." +
                "The board committees (where appropriate) have an agreed work plan for the year and are making good progress against that plan. these answers will be a number between 0 and 4, 4 being Full in place and 0 being not at all", "please provide Strong areas and areas of improvement based on this results list [0,4,2,2,2,3,1,4,4,3,2,1,0,2] an provide a summary of how the company is doing in terms of purpose");
        System.out.println(response);
        ctx.json(response);
    };

    public  static  final Handler get_clients= ctx -> {
        ObjectMapper ob = new ObjectMapper();
        ctx.json(ob.writeValueAsString(Business.getClientList()));

    };
    public  static  final Handler dashboard= ctx -> {
        ctx.render("dashboard.html");

    };

    public static final Handler get_clients_by_company = context -> {
        String company = context.pathParam("company");
        context.json(Business.getClientByCompanyName(company));

    };

    public static final Handler send_email = context ->{

        String email = context.formParam("email");
        String ipAddress = getLocalIPv4Address(); // Replace with your method to get the public IP
        String url = "http://" + ipAddress + ":7000/questions"+"?emailId="+email;
        sendEmail(email, "Business Insights Questions","Please find link to questions, please complete all questions "+ url);

    };
    public static final Handler questions = context ->{

        context.render("/question.html");
    };

    public static final Handler directors_by_company = context ->{
        Client client= Business.getClientByCompanyName(context.pathParam("companyName"));
        context.json(client.getDirectorList());
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
