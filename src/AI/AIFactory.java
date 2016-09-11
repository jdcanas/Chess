package AI;

public class AIFactory {

	private static AIFactory instance = null;
	private AbstractAI activeAI;
	
	private AIFactory() {}
	
	public static AIFactory getInstance() {
		if(instance == null){
			instance = new AIFactory();
		}
		return instance;
	}
	
	public void setActiveAI(AbstractAI AI) {
		activeAI = AI;
	}
	
	public AbstractAI getActiveAI() {
		return activeAI;
	}
	
	
}
