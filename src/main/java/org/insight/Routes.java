package org.insight;

import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.get;


public class Routes {

    public static final String ADD_ClIENT = "/addclient";
    public static final String SUBMIT_TEST = "/submit.test";
    public static final String CLIENTS = "/getclients";
    public static final String CLIENTS_BY_COMPANY = "/getclients/{company}";
    public  static final String ADDNEW ="/addnewclient";
    public static final String HOME = "/";
    public static final String CHART = "/chart";

    public static final String SEND_EMAIL = "/email.action";
    public static final String QUESTIONS = "/questions";
    public static final String DIRECTORS_BY_COMPANY = "/client/directors/{companyName}";





    public static void configure(Server server) {
        server.routes(() -> {
            get(CHART, context -> {context.render("test.html");});
            get(ADDNEW, Controller.addnewclient);
            get(HOME, Controller.home);
            post(SUBMIT_TEST, Controller.test_action);
            post(ADD_ClIENT, Controller.add_client );
            get(CLIENTS, Controller.get_clients);
            get(CLIENTS_BY_COMPANY, Controller.get_clients_by_company);
            post(SEND_EMAIL, Controller.send_email);
            get(QUESTIONS, Controller.questions);
            get(DIRECTORS_BY_COMPANY, Controller.directors_by_company);

        });
    }
}
