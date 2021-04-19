import CrossAssembler.*;
import CrossAssembler.Backend.*;
import CrossAssembler.Core.*;
import CrossAssembler.Frontend.*;

public class cma {

    private static final String helpMessage = "Usage: java cma [ Options ] <file>.asm\n\n" + "where options are:\n\n"
            + "Short version  Long version    Meaning\n"
            + "-h             -help           Print the usage of the program.\n"
            + "-v             -verbose        Verbose during the execution of the program.\n"
            + "-b             -banner         Print the banner of the program.\n"
            + "-l             -listing         Generate a listing of the assembly file.\n";

    private static final String bannerMessage = "Cm Cross-Assembler - Developed by Team 1.";

    public static void main(String[] args) {
        String fileName = null;
        boolean listing = false;
        boolean verbose = false;

        if (args.length > 0) {

            if (args.length == 1) {

                // if user enters help or -h
                if (args[0].equals("-h") || args[0].equals("-help")) {
                    System.out.println(helpMessage);
                    System.exit(0);
                }
                // if user enters -banner or -b
                else if (args[0].equals("-b") || args[0].equals("-banner")) {
                    System.out.println(bannerMessage);
                    System.exit(0);
                }
                // to generate only .exe
                else if (args[0].contains(".asm")) {
                    fileName = args[0];
                } else {
                    System.out.println("Invalid arguments");
                    System.exit(0);
                }
            }

            // if the user enters an option and a file name
            else if (args.length > 1) {

                for (int i = 0; i < args.length; i++) {

                    // if user enters help or -h
                    if (args[i].equals("-h") || args[i].equals("-help")) {
                        System.out.println(helpMessage);
                    }
                    // if user enters -banner or -b
                    else if (args[i].equals("-b") || args[i].equals("-banner")) {
                        System.out.println(bannerMessage);
                    }
                    // if user enters file
                    else if(args[i].contains(".asm")){
                        fileName = args[i];
                    }
                    else if (args[i].equals("-l") || args[i].equals("-listing")) {
                        listing = true;
                    }
                    else if (args[i].equals("-v") || args[i].equals("-verbose")) {
                        verbose = true;
                    }
                }
            }
            CrossAssembler crossAssembler = new CrossAssembler(fileName);

            if(listing && verbose){
                crossAssembler.assemble("-l -v");
            }
            else if(verbose){
                crossAssembler.assemble("-v");
            }
            else if(listing){
                crossAssembler.assemble("-l");
            }
        }
    }
}
