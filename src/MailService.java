public class MailService {
    //Creating Asychronous method
    public void sendEmail() {
        System.out.println("Email sending is in progress.");
        prepareEmailContent();
        System.out.println(" Email is sent to the customer.");
    }

    private void prepareEmailContent() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
