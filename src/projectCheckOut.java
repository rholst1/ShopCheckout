import java.util.Scanner;

public class projectCheckOut {

	static String[] checkoutQueue = new String[8];
	static final String EMPTY_SLOT = "-";

	enum Command {
		PUT, PROCESS, RESET, QUIT, UNKNOWN
	}

	public static Command parseCommand(String userInput) {
								
		String commandString = userInput.split(" ")[0];
		switch (commandString) { 
		case "put":
			return Command.PUT;
		case "process":
			return Command.PROCESS;
		case "quit":

			return Command.QUIT;
		case "reset":
			return Command.RESET;

		default:
			return Command.UNKNOWN;
		}
	}

	public static String[] parseArguments(String userInput) {
		String[] commandPlusArguments = userInput.split(" "); //
		String[] arguments = new String[commandPlusArguments.length - 1];
		for (int i = 1; i < commandPlusArguments.length; i++) {
			arguments[i-1] = commandPlusArguments[i];
		}
		return arguments;
	}

	public static void handleCommandPut(String[] namesToAdd) {
		int j = 0;
		
		for (int i = 0; i < checkoutQueue.length; i++) {

			if (checkoutQueue[i] == EMPTY_SLOT) {
			
				if (namesToAdd[j].length() <= 10 && namesToAdd[j].equals(namesToAdd[j].toLowerCase())) {
					checkoutQueue[i] = namesToAdd[j];

				} else if (namesToAdd[j].length() > 10 && namesToAdd[j].equals(namesToAdd[j].toLowerCase())) {
					System.out.println(namesToAdd[j] + " is more then 10 characters. Skipping this person.");
				} else if (namesToAdd[j].length() > 10 && !namesToAdd[j].equals(namesToAdd[j].toLowerCase())) {
					System.out.println(namesToAdd[j] + " is more then 10 characters and not lower case. Skipping this person.");
				} else if (!namesToAdd[j].equals(namesToAdd[j].toLowerCase())) {
					System.out.println(namesToAdd[j] + " is not all lower case. Skipping this person.");
				}
				if (j == namesToAdd.length - 1) {
					return;
				}
				j++;
			} else {

			}

		}

		if (!checkoutQueue[checkoutQueue.length - 1].equals(EMPTY_SLOT)) {
			for (int k = 0; j < namesToAdd.length; j++) { // int k är bara en placeholder för vi måste deklarera en variabel i början av en for-loop.
				if (checkIfFull() == true) {
					System.out.printf("Could not add %s because the queue is full.\n", namesToAdd[j]);
				}
			}
		}
	}
	
	static boolean checkIfFull() {
		if(!checkoutQueue[checkoutQueue.length - 1].equals(EMPTY_SLOT)) {
			return true;
		}
		return false;
	}

	static void handleCommandProcess() {
		for (int i = 1; i < checkoutQueue.length; i++) {
			checkoutQueue[i - 1] = checkoutQueue[i];
		}
		if(checkIfFull() == true)
		checkoutQueue[checkoutQueue.length - 1] = EMPTY_SLOT; // sätter alltid sista elementet till en EMPTY_SLOT

	}

	static void handleCommandReset() {
		resetQueue();
	}

	public static void printQueue() {

		System.out.print("\nCHECKOUT<-|");
		for (int i = 0; i < checkoutQueue.length; i++) {
			System.out.printf("%s|", checkoutQueue[i]);
		}
		System.out.printf("\n" + "*".repeat(33));
		System.out.print("\n" + "Enter your command here:\n" + ">");

	}

	static void resetQueue() {
		for (int i = 0; i < checkoutQueue.length; i++) {
			checkoutQueue[i] = EMPTY_SLOT;
		}
	}

	static void handleCommandQuit() {
		System.out.println("Exiting.");
		System.exit(0);
	}

	static void handleCommandUnknown() {
		System.out.println("Unknown command.");
	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		resetQueue();

		System.out.printf("\nEnter one of the following commands:" + "\n\n"
				+ "'put' <name> <name> \n'process' checkout customer \n'reset' empty the queue \n'quit' exit program");

		printQueue();
		// testing
		while (true) {
				// reset
			String userInput = scanner.nextLine();
			Command command = parseCommand(userInput);
			String[] arguments = parseArguments(userInput); 

			if (command == Command.PUT) {
				handleCommandPut(arguments);
			} else if (command == Command.PROCESS) {
				handleCommandProcess();
			} else if (command == Command.RESET) {
				handleCommandReset();
			} else if (command == Command.QUIT) {
				handleCommandQuit();
			} else if (command == Command.UNKNOWN) {
				handleCommandUnknown();
			}
			printQueue();
		}
	}
}