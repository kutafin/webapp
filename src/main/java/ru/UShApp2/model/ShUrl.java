package ru.UShApp2.model;


import javax.persistence.*;

@Entity
@Table(name = "shorturl")
public class ShUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUrl")
    private int idUrl;
    @Column(name = "url")
    private String url;
    @Column(name = "shortUrl")
    private String shortUrl;
    @Column(name = "description")
    private String description;
    @Column(name = "views")
    private int views;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JoinColumn(name = "id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @JoinColumn(name = "idTag", referencedColumnName = "idTag")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Tag tag;

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public ShUrl() {
    }

    public ShUrl(int idUrl, String url, String shortUrl, String description, int views, User user, Tag tag) {
        this.idUrl = idUrl;
        this.url = url;
        this.shortUrl = shortUrl;
        this.description = description;
        this.views = views;
        this.user = user;
        this.tag = tag;
    }

    public int getIdUrl() {
        return idUrl;
    }

    public void setIdUrl(int idUrl) {
        this.idUrl = idUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShortUrl() {
        return "USh/" + String.valueOf(idUrl);
    }

    public void setShortUrl(String shortUrl) {
        String s = shortUrl.replaceAll("Ush/", "");
        this.shortUrl = s;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}

