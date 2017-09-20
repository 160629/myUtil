package send.receiver;


import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;

public class POP3Help {
    public static Folder getFolder() {
        Properties prop = new Properties();
        prop.setProperty("mail.store.protocol", "pop3");
        prop.setProperty("mail.pop3.host", "pop.163.com");

        Session mailSession = Session.getDefaultInstance(prop, null);
        mailSession.setDebug(false);

        try {
            Store store = mailSession.getStore("pop3");
            store.connect("pop.163.com", "wang90yong@163.com", "wy19890611");
            Folder folder = store.getFolder("inbox");
            folder.open(Folder.READ_WRITE);
            return folder;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}