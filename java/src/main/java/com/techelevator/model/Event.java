package com.techelevator.model;

import java.time.LocalDateTime;
import java.util.List;

public class Event {
    private int eventId;
    private String title;
    private String description;
    private String companyName;
    private String venueName;
    private String venueAddress;
    private String ticketPrice;
    private String eventLink;
    private String emailAddress;
    private String imageUrl;
    private String contentWarnings;
    private String status;
    private List<String> maskingPolicy;
    private List<String> accessibilityOptions;
    private String otherAccessibility;
    private int createdBy;
    private boolean favorited;
    private List<LocalDateTime> eventDates;

    // Getters and setters

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getVenueAddress() {
        return venueAddress;
    }

    public void setVenueAddress(String venueAddress) {
        this.venueAddress = venueAddress;
    }

    public String getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getEventLink() {
        return eventLink;
    }

    public void setEventLink(String eventLink) {
        this.eventLink = eventLink;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContentWarnings() {
        return contentWarnings;
    }

    public void setContentWarnings(String contentWarnings) {
        this.contentWarnings = contentWarnings;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getMaskingPolicy() {
        return maskingPolicy;
    }

    public void setMaskingPolicy(List<String> maskingPolicy) {
        this.maskingPolicy = maskingPolicy;
    }

    public List<String> getAccessibilityOptions() {
        return accessibilityOptions;
    }

    public void setAccessibilityOptions(List<String> accessibilityOptions) {
        this.accessibilityOptions = accessibilityOptions;
    }

    public String getOtherAccessibility() {
        return otherAccessibility;
    }

    public void setOtherAccessibility(String otherAccessibility) {
        this.otherAccessibility = otherAccessibility;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public List<LocalDateTime> getEventDates() {
        return eventDates;
    }

    public void setEventDates(List<LocalDateTime> eventDates) {
        this.eventDates = eventDates;
    }
}
