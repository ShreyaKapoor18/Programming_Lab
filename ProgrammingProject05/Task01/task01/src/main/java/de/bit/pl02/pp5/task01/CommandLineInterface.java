package de.bit.pl02.pp5.task01;
import org.apache.commons.cli.Option; 
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine; 
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter; 
import org.apache.commons.cli.ParseException;
import java.io.*; 
import java.net.*;
import java.util.Scanner; 

public class CommandLineInterface {
	
	public static void main(String[] args) throws IOException {
	Options options = new Options(); 
	Option input = new Option("i", "import", true, "Enter the name of the input file"); 
	Option input2 = new Option("p", "print", false,"If you want to print the entire information" ); 
	Option input3 = new Option("m", "meta", false, "If you want to add meta information"); 
	options.addOption(input); 
	options.addOption(input2); 
	options.addOption(input3); 
	input.setRequired(true);
	CommandLineParser parser = new DefaultParser(); 
	HelpFormatter formatter = new HelpFormatter(); 
	CommandLine cmd;
	try {
	String dir = "/Users/shreyakapoor/Desktop/PP5/"; // directory which you want to start with. 
	cmd = parser.parse(options, args);
	String filename = cmd.getOptionValue("import", ""); 
	System.out.println(filename); 
	String[] arr = filename.split("\\."); 
	String path = dir+ arr[0] + ".meta"; 
	
	if (cmd.hasOption("p") ) 
		{ File metafile =  checkmetafile(cmd, path) ; 
		  if (cmd.hasOption("m"))
			{
				getmeta(metafile); 
			}
	     }
	} 
	
	catch (ParseException e) { 
	System.out.println(e.getMessage()); formatter.printHelp("parsingtest", options);
	System.exit(1);
	return; }
	}
	
	public static void readfile (CommandLine cmd, String path) throws IOException{

		FileReader filer = new FileReader(path);
		BufferedReader buffr = new BufferedReader(filer);
		boolean eof = false;
		while ((!eof) && cmd.hasOption("p"))
		  {
		  String s = buffr.readLine();
		  if(s == null){
		    eof = true;
		       }
		  else{
		    System.out.println(s);
		    boolean link = s.contains("https:");
		  	}
		}
		buffr.close();
		}
	
	public static void getmeta(File newfile) throws IOException {
		/* if a file does not already exist, create a new file
		  if a file exists add contents to that one
	 	*/
		// improvements write in the form of a class in the file.
		Scanner input = new Scanner(System.in); 
		System.out.println("Enter the name of the author: \n"); 
		String Author = input.nextLine(); 
		System.out.println("Enter the title: \n"); 
		String title = input.nextLine(); 
		input.close(); 
		// write to file now 
		FileWriter os = new FileWriter(newfile); 
		os.write("Title:"+ title+"\n");
		os.write("Author:"+ Author+"\n");
		os.close(); 
	
	}
	
	public static File checkmetafile(CommandLine cmd,String path) throws IOException{
		try {
		File oldfile = new File(path); 
		readfile(cmd, path);
		return oldfile; 
			} 
		catch (FileNotFoundException e){ 
		System.out.println("No metadata file found for this filename, making a new file for the metadata"); 
		File newfile = new File(path); 
		newfile.createNewFile(); 
		FileReader filer = new FileReader(newfile); 
		return newfile; 
			}
		}	
}
	 
	
	

