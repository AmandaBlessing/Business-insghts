package org.insight;

import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.get;


public class Routes {

    public static final String ADD_ClIENT = "/addclient";
    public static final String SUBMIT_TEST = "/submit.test";
    public static final String CLIENTS = "/getclients";
    public static final String CLIENTS_BY_COMPANY = "/getclients/{company}";
    public  static final String TREND_ANALYSIS ="/trend-analysis";



    public static void configure(Server server) {
        server.routes(() -> {
            post(SUBMIT_TEST, Controller.test_action);
            post(ADD_ClIENT, Controller.add_client );
            get(CLIENTS, Controller.get_clients);
            get(CLIENTS_BY_COMPANY, Controller.get_clients_by_company);

        });
    }
}
