public class A2test {

	public static Assignment2 a2;
	
	public static int connectDB = 1;
	public static int insertCountry = 2;
	public static int getPlayersCount = 3;
	public static int getPlayerInfo = 4;
	public static int chgStadiumLocation = 5;
	public static int deleteCountry = 6;
	public static int listPlayers = 7;
	public static int updateValues = 8;
	public static int query7 = 9;
	public static int updateDB = 10;
	
	
	public static int disconnectDB = 99;
	
	public static void doTest(int testCaseNum,int testNum, String param1, String param2, String param3, String expected) {
		System.out.println("***********************************************************");
		System.out.println("Running Test "+testNum+" With Params: "+param1+"-"+param2+"-"+param3);
		
		// Connect
		if ( testNum == connectDB ) {
			if(a2.connectDB(param1,param2,param3))
				System.out.println("Success");
			else
				System.out.println("Failure");
		// Disconnect
		} else if ( testNum == disconnectDB ) {
			if(a2.disconnectDB())
				System.out.println("Success");
			else
				System.out.println("Failure");
		// insertCountry
		} else if (testNum == insertCountry) {
			if(a2.insertCountry(Integer.parseInt(param1), param2, param3))
				System.out.println("Success");
			else
				System.out.println("Failure");
		// getPlayersCount
		} else if (testNum == getPlayersCount) {
			if( a2.getPlayersCount( Integer.parseInt(param1) ) == 3)
				System.out.println("Success");
			else
				System.out.println("Failure");
		// getPlayerInfo
		} else if (testNum == getPlayerInfo) {
			if( (a2.getPlayerInfo(Integer.parseInt(param1))).equals("Vanessa:Archer:CF:14") )
				System.out.println("Success");
			else
				System.out.println("Failure");
		// chgStadiumLocation
		} else if (testNum == chgStadiumLocation) {
			if( (a2.chgStadiumLocation(Integer.parseInt(param1), param2) ) )
				System.out.println("Success");
			else
				System.out.println("Failure");
		// deleteCountry
		} else if (testNum == deleteCountry) {
			if( (a2.deleteCountry(Integer.parseInt(param1)) ) )
				System.out.println("Success");
			else
				System.out.println("Failure");
		// listPlayers
		} else if (testNum == listPlayers) {
			String a = (a2.listPlayers(param1));								
			if( a.equals("Tomasz:Stockton:RCM:22:Gambia#Roger:Arcadi:LM:8:Finland#Sophie:Schlee:LF:9:Equatorial Guinea") )
				System.out.println("Success");
			else
				System.out.println("Failure");
		// updateValues
		} else if (testNum == updateValues) {
			if( (a2.updateValues(param1,Integer.parseInt(param2) ) ) )
				System.out.println("Success");
			else
				System.out.println("Failure");
		// query7
		} else if (testNum == query7) {
			String a = a2.query7();
			if( a.equals("Ghana:Victor:134") )
				System.out.println("Success");
			else
				System.out.println("Failure");
		// updateDB
		} else if (testNum == updateDB) {
			if( a2.updateDB() )
				System.out.println("Success");
			else
				System.out.println("Failure");
		} else {
			System.out.println("Why the **** are you here?");
		}
	
		System.out.println("***********************************************************\n");
	}
	
	public static void main(String[] args) {
		a2 = new Assignment2();
		String url = args[0];
		String username = args[1];
		String password = args[2];
		int testCaseNum = 0;
		
		doTest(testCaseNum++,connectDB,url,username,password,"");
		doTest(testCaseNum++,insertCountry,"29339","uoftworld","papalegis","");
		doTest(testCaseNum++,getPlayersCount,"10","","","");
		doTest(testCaseNum++,getPlayerInfo,"33","","","");
		doTest(testCaseNum++,chgStadiumLocation,"1","Dubaiii","","");
		doTest(testCaseNum++,deleteCountry,"1","","","");
		doTest(testCaseNum++,listPlayers,"CHARlZARD","","","");
		doTest(testCaseNum++,updateValues,"Germany","30","","");	
		doTest(testCaseNum++,query7,"","","","");	
		doTest(testCaseNum++,updateDB,"","","","");			
		doTest(testCaseNum++,disconnectDB,"","","","");
	}
}