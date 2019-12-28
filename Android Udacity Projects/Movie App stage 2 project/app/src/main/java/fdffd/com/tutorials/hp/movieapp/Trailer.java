package fdffd.com.tutorials.hp.movieapp;

public class Trailer {
    public String text;
    public String url;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Trailer(String text, String url) {
        this.text = text;
        this.url = url;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
