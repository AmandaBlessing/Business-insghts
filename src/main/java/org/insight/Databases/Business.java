package org.insight.Databases;

import java.util.ArrayList;
import java.util.List;

public class Business {
    private static List<Client> clientList = new ArrayList<>();



    public static void addClient(Client client){
        clientList.add(client);
    }

    public  static List<Client> getClientList() {
        return clientList;
    }

    public static Client getClientByCompanyName(String name){
        return clientList.stream().filter(x -> x.getCompanyName().equalsIgnoreCase(name)).toList().get(0);
    }


}
