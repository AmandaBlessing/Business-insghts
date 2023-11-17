package org.insight.Databases;

import java.util.ArrayList;
import java.util.List;

public class Results {

    List<Integer> purpose;
    List<Integer> sustainability;
    List<Integer> conformance;
    List<Integer> performance;

    public Results() {
        this.purpose = new ArrayList<>();
        this.sustainability = new ArrayList<>();
        this.conformance = new ArrayList<>();
        this.performance = new ArrayList<>();
    }

    public void setPurpose(List<Integer> purpose) {
        this.purpose = purpose;
    }

    public void setSustainability(List<Integer> sustainability) {
        this.sustainability = sustainability;
    }

    public void setConformance(List<Integer> conformance) {
        this.conformance = conformance;
    }

    public void setPerformance(List<Integer> performance) {
        this.performance = performance;
    }

    public List<Integer> getPurpose() {
        return purpose;
    }

    public List<Integer> getSustainability() {
        return sustainability;
    }

    public List<Integer> getConformance() {
        return conformance;
    }

    public List<Integer> getPerformance() {
        return performance;
    }
}
