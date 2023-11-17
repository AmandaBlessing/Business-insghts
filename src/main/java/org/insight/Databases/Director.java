package org.insight.Databases;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Director {
    private final String name;
    private final String lastname;
    private final String email;
    private  boolean isTestDone = false;
    private Results results;

    private Map<String, List<Integer>> resultsMap = new HashMap<>();

    public Director(String name,String lastname,String email) {
        this.results = new Results();
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getLastname() {
        return lastname;
    }

    public boolean isTestDone() {
        return isTestDone;
    }

    public void setTestDone(boolean testDone) {
        isTestDone = testDone;
    }

    public Results getResults() {
        return results;
    }

    public void addToMap(String key, List<Integer> results){
        resultsMap.put(key, results);
    }

    public void setResults(Results results) {
        this.setTestDone(true);
        this.results = results;
    }

    public String getName() {
        return name;
    }
}
