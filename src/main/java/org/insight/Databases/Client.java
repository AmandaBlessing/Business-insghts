package org.insight.Databases;

import java.util.ArrayList;
import java.util.List;

public class Client {
    List<Director> directorList;
    private String companyName;

    public Client(List<Director> directorList, String companyName) {
        this.directorList = directorList;
        this.companyName = companyName;
    }

    public Client(String companyName) {
        this.directorList = new ArrayList<>();
        this.companyName = companyName;
    }

    public void addDirector(Director director){
        this.directorList.add(director);
    }

    public List<Director> getDirectorList() {
        return directorList;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Director getDirectorByEmail(String email){
        return directorList.stream().filter(x -> x.getEmail().equalsIgnoreCase(email)).toList().get(0);
    }
}
