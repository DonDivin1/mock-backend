package com.yourproject.ims.model;

import jakarta.persistence.*;

@Entity
@Table(name = "announcements")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String postedBy;
    private String title;
    private String type;
    private String content;
    private String destination;

    @Column(columnDefinition = "TIMESTAMP")
    private java.time.LocalDateTime date;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPostedBy() { return postedBy; }
    public void setPostedBy(String postedBy) { this.postedBy = postedBy; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public java.time.LocalDateTime getDate() { return date; }
    public void setDate(java.time.LocalDateTime date) { this.date = date; }
}
