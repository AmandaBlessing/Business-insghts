package org.insight;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.insight.Databases.Business;
import org.insight.Databases.Client;
import org.insight.Databases.Director;
import org.insight.Databases.Results;

import java.io.FileWriter;

import java.io.IOException;
import java.util.*;

import java.util.*;


public class Controller {

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

    };
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

    public static final Handler product_action = context -> {
        String manufacturer = context.formParamAsClass("manufacturer", String.class)
                .check(Objects::nonNull, "Manufacturer is required")
                .get();
        String manufactureDateTime = context.formParamAsClass("manufactureDateTime", String.class)
                .check(Objects::nonNull, "manufactureDateTime is required")
                .get();
        String expiryDate = context.formParamAsClass("expiryDate", String.class)
                .check(Objects::nonNull, "Manufacturer is required")
                .get();
        String productDescription = context.formParamAsClass("productDescription", String.class)
                .check(Objects::nonNull, "manufactureDateTime is required")
                .get();
        String locationManufactured = context.formParamAsClass("locationManufactured", String.class)
                .check(Objects::nonNull, "Manufacturer is required")
                .get();
        String batchNumber = context.formParamAsClass("batchNumber", String.class)
                .check(Objects::nonNull, "manufactureDateTime is required")
                .get();



    };

    public static final Handler verfification = context ->{

        String hash = context.pathParam("hashcode");
        System.out.println(hash);
        Map<String, Object> viewModel;
        boolean verified = false;

    };




    public static final Handler trendAnalysisHandler = context ->{

        context.render("/TrendAnalysis.html", Map.of("businessData", "businessData"));
    };


}
