package H2;

public class Main {
	
	public static void main(String[] args) {
		

		BayerPattern bayerData = new BayerPattern("bayerData1.dat");
		int[][][] split = bayerData.splitColorChannels();
		int[][][] debayer =  BayerPattern.interpolateMissingValues(split);
	
		// Your result will be saved to debayer.png
		Image debayerImage = new Image(debayer);
		debayerImage.saveAsPNG("debayer.png");
		
		System.out.println((debayer[17][6][1] + debayer[0][debayer[0].length-1][2] + debayer[0][17][0]) == 436);
		System.out.println((debayer[34][23][0] + debayer[debayer.length-1][debayer[0].length-1][1] + debayer[100][0][2] == 359));
	}

}
