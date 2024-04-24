package org.example.model;

import org.example.model.AmountsReceived;

import java.util.ArrayList;

public class Transaction {
    private String txid;
    private Boolean from_green_address;
    private Integer time;
    private Integer confirmations;
    private ArrayList<AmountsReceived> amounts_received;
    private ArrayList<String> senders;
    private Double confidence;
    private String propagated_by_nodes;

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public Boolean getFrom_green_address() {
        return from_green_address;
    }

    public void setFrom_green_address(Boolean from_green_address) {
        this.from_green_address = from_green_address;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(Integer confirmations) {
        this.confirmations = confirmations;
    }

    public ArrayList<AmountsReceived> getAmounts_received() {
        return amounts_received;
    }

    public void setAmounts_received(ArrayList<AmountsReceived> amounts_received) {
        this.amounts_received = amounts_received;
    }

    public ArrayList<String> getSenders() {
        return senders;
    }

    public void setSenders(ArrayList<String> senders) {
        this.senders = senders;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public String getPropagated_by_nodes() {
        return propagated_by_nodes;
    }

    public void setPropagated_by_nodes(String propagated_by_nodes) {
        this.propagated_by_nodes = propagated_by_nodes;
    }
}
