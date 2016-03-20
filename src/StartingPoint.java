
public class StartingPoint {
	public static void main(String [] args){
		SonarPingModel model = new SonarPingModel("COM4");	
		SonarPingView view = new SonarPingView();
		SonarPingController controller = new SonarPingController(model, view,null);
	}
}
