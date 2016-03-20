

public class MongoMVC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MongoView theView = new MongoView();
		MongoModel theModel = new MongoModel();
		MongoControl theControl = new MongoControl(theView, theModel);
		
		theView.setVisible(true);
	}
}
