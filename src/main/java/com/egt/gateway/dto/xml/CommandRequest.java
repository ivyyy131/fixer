package com.egt.gateway.dto.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "command")
public class CommandRequest {

    @XmlAttribute(name = "id")
    private String id;

    @XmlElement(name = "get")
    private GetRequest getRequest;

    @XmlElement(name = "history")
    private HistoryRequest historyRequest;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GetRequest getGetRequest() {
        return getRequest;
    }

    public void setGetRequest(GetRequest getRequest) {
        this.getRequest = getRequest;
    }

    public HistoryRequest getHistoryRequest() {
        return historyRequest;
    }

    public void setHistoryRequest(HistoryRequest historyRequest) {
        this.historyRequest = historyRequest;
    }

    public boolean isHistoryCommand() {
        return historyRequest != null;
    }

    public String getConsumer() {
        return isHistoryCommand() ? historyRequest.getConsumer() : getRequest.getConsumer();
    }

    public String getCurrency() {
        return isHistoryCommand() ? historyRequest.getCurrency() : getRequest.getCurrency();
    }

    public int getPeriod() {
        return isHistoryCommand() ? historyRequest.getPeriod() : 0;
    }
}
