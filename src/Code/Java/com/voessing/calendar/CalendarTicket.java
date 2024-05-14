package com.voessing.calendar;

import java.util.Date;
import org.openntf.domino.Document;
import org.openntf.domino.Item;

public class CalendarTicket {
    private String ticketDocumentUnid;
    private String ticketUid;
    private String title;
    private Type type;
    private State state;
    private String requester;

    private Date startDate;
    private Date endDate;
    private int startPhase;
    private int endPhase;

    private int duration;

    public static enum State {
        APPROVED, CREATED, CANCELLED, DENIED;
    }

    public static enum Type {
        MOBILE_WORK, VACATION, SEPECIAL_VACATION, FLEX_DAY;
    }

    public CalendarTicket(Document ticket){
        setTicketDocumentUnid(ticket.getUniversalID());
        setTicketUid(ticket.getItemValueString("Uid"));
        setTitle(ticket.getItemValueString("title"));
        setType(ticket.getItemValueString("typ"));
        setState(ticket.getItemValueString("status"));
        setRequester(ticket.getItemValueString("owner"));

        setStartDate(ticket.getFirstItem("anfangsdatum"));
        setEndDate(ticket.getFirstItem("enddatum"));
        setStartPhase(ticket.getItemValueInteger("anfangslage"));
        setEndPhase(ticket.getItemValueInteger("endlage"));

        setDuration(ticket.getItemValueInteger("anzahlTage"));
    }

    public String getTicketDocumentUnid() {
        return ticketDocumentUnid;
    }

    public void setTicketDocumentUnid(String ticketDocumentUnid) {
        if(ticketDocumentUnid == null || ticketDocumentUnid.isEmpty()){
            throw new IllegalArgumentException("ticketDocumentUnid must not be null or empty");
        }

        this.ticketDocumentUnid = ticketDocumentUnid;
    }

    public String getTicketUid() {
        return ticketUid;
    }

    public void setTicketUid(String ticketUid) {
        if(ticketUid == null || ticketUid.isEmpty()){
            throw new IllegalArgumentException("ticketUid must not be null or empty");
        }

        this.ticketUid = ticketUid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title == null || title.isEmpty()){
            throw new IllegalArgumentException("title must not be null or empty");
        }

        this.title = title;
    }

    public Type getType() {
        return type;
    }

    public void setType(String typeStr) {
        switch(typeStr){
            case "MW":
                this.type = Type.MOBILE_WORK;
                break;
            case "EU":
                this.type = Type.VACATION;
                break;
            case "SU":
                this.type = Type.SEPECIAL_VACATION;
                break;
            case "GT":
                this.type = Type.FLEX_DAY;
                break;
            default:
                throw new IllegalArgumentException("Unknown type: " + typeStr);
        }
    }

    public State getState() {
        return state;
    }

    public void setState(String stateStr) {
        switch(stateStr){
            case "G":
                this.state = State.APPROVED;
                break;
            case "E":
                this.state = State.CREATED;
                break;
            case "S":
                this.state = State.CANCELLED;
                break;
            case "A":
                this.state = State.DENIED;
                break;
            default:
                throw new IllegalArgumentException("Unknown state: " + stateStr);
        }
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        if(requester == null || requester.isEmpty()){
            throw new IllegalArgumentException("requester must not be null or empty");
        }

        this.requester = requester;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Item startDate) {
        if(requester == null || requester.isEmpty()){
            throw new IllegalArgumentException("requester must not be null or empty");
        }

        this.startDate = startDate.getDateTimeValue().toJavaDate();
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Item endDate) {
        if(requester == null || requester.isEmpty()){
            throw new IllegalArgumentException("requester must not be null or empty");
        }

        this.endDate = endDate.getDateTimeValue().toJavaDate();
    }

    public int getStartPhase() {
        return startPhase;
    }

    public void setStartPhase(int startPhase) {
        if(startPhase < 0 || startPhase > 2){
            throw new IllegalArgumentException("startPhase must be between 0 and 2");
        }
            
        this.startPhase = startPhase;
    }

    public int getEndPhase() {
        return endPhase;
    }

    public void setEndPhase(int endPhase) {
        if(startPhase < 0 || startPhase > 2){
            throw new IllegalArgumentException("startPhase must be between 0 and 2");
        }

        this.endPhase = endPhase;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        if(duration < 0){
            throw new IllegalArgumentException("duration must be greater than 0");
        }
        
        this.duration = duration;
    }

    @Override 
    public String toString(){
        return "CalendarTicket [ticketDocumentUnid=" + ticketDocumentUnid + ", ticketUid=" + ticketUid + ", title=" + title + ", type=" + type + ", state=" + state + ", requester=" + requester + ", startDate=" + startDate + ", endDate=" + endDate + ", startPhase=" + startPhase + ", endPhase=" + endPhase + ", duration=" + duration + "]";
    }

}