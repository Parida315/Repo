package objectClasses;

public class NewsLettersFields {
    private String newsletterSubject, senderName, senderEmailAddress,  HTMLContent, plainTextContent;

    public NewsLettersFields() {
    }

    public NewsLettersFields(String newsletterSubject, String senderName, String senderEmailAddress, String HTMLContent, String plainTextContent) {
        this.newsletterSubject = newsletterSubject;
        this.senderName = senderName;
        this.senderEmailAddress = senderEmailAddress;
        this.HTMLContent= HTMLContent;
        this.plainTextContent=plainTextContent;
    }

    public String getPlainTextContent() {
        return plainTextContent;
    }

    public NewsLettersFields(String HTMLContent) {
        this.HTMLContent = HTMLContent;
    }

    public void setHTMLContent(String HTMLContent) {
        this.HTMLContent = HTMLContent;
    }

    public String getNewsletterSubject() {
        return newsletterSubject;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getSenderEmailAddress() {
        return senderEmailAddress;
    }

    public String getHTMLContent() {
        return HTMLContent;
    }
}
