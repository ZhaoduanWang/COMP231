/*import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.video.Video;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgproc.Imgproc;
 */
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailTest {
	public static void main(String [] args){
		/*System.out.println("Hello");

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		VideoCapture cap = new VideoCapture(0);


		Mat m = new Mat(5, 10, CvType.CV_8UC1, new Scalar(0));
		    System.out.println("OpenCV Mat: " + m);
		    Mat mr1 = m.row(1);
		    mr1.setTo(new Scalar(1));
		    Mat mc5 = m.col(5);
		    mc5.setTo(new Scalar(5));
		    System.out.println("OpenCV Mat data:\n" + m.dump());*/

		// Recipient's email ID needs to be mentioned.
		String to = "tpotm9@gmail.com, dannywang0619@gmail.com";

		// Sender's email ID needs to be mentioned
		String from = "john.orion.ray@gmail.com";
		final String username = "john.orion.ray@gmail.com";//change accordingly
		final String password = "phantom1237";//change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject("Testing Subject");

			// Now set the actual message
			message.setText("Hello, this is a test for joshuas work");

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
