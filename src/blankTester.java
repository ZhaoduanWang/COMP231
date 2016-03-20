
public class blankTester {

	public static void main(String[] args) {
		SonarPingEmailModel runner = new SonarPingEmailModel("joshua.l99.c@gmail.com,john.orion.ray@gmail.com", 
				"john.orion.ray@gmail.com", "john.orion.ray@gmail.com", "phantom1237");
		runner.sessionInitialize();
		runner.run();
	}

}
