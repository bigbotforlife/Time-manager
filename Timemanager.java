import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Timemanager {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int dayhour = 24;
        String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        int[] freeTimePerDay = new int[daysOfWeek.length];

        System.out.println("Hello! This program will help you calculate your free time and help you manage time in the week.");
        System.out.println("You will be inputting your activities into the days of the week and you will get a report on how free you will be!");
        System.out.println("When you are done inputting all of your activies, or if you do not have any activies on the day, please input:\n\"Start next day\"");
        System.out.println("We will be starting on Sunday.");
        for (int i = 0; i < daysOfWeek.length; i++) {
            System.out.println("\nLets see what you have on " + daysOfWeek[i] + ":");
            int dailyHours = dayhour;

            while (true) {
				System.out.print("How many hours do you sleep? ");
					int sleeptime = scan.nextInt(); scan.nextLine();
							        dailyHours -= sleeptime;


							        if (sleeptime >= 14) {
							            System.out.print("I know you do not sleep that long. How long do you actually sleep? ");
							            sleeptime = scan.nextInt(); scan.nextLine();
			            }
                System.out.print("Name an activity that you have on " + daysOfWeek[i] + ": ");
                String activity = scan.nextLine().toLowerCase();

                if (activity.equals("start next day")) {
                    break;
                }

                System.out.print("How many hours will that take? ");
                int time = scan.nextInt(); scan.nextLine();

                dailyHours -= time;
                if (dailyHours < 0) {
                    System.out.println("Your values go over the amount of time in the day, restart with proper values.");
                    dailyHours = dayhour;
                    continue;
                }
                if (dailyHours == 0) {
                    System.out.println("Good luck! You're going to be busy the whole day.");
                } else {
                    System.out.println("Ok, that leaves " + dailyHours + " hours left on " + daysOfWeek[i] + " for free time.");
                }
            }

            freeTimePerDay[i] = dailyHours;
            dayhour = 24;
}
System.out.println("\nSummary of your free time for the week:");
for (int i = 0; i < daysOfWeek.length; i++) {
    System.out.println(daysOfWeek[i] + ": " + freeTimePerDay[i] + " hours of free time.");
}
 System.out.print("\nDo you feel like you have too much free time? (yes/no): ");
        String tooMuchFreeTime = scan.nextLine().toLowerCase();

        if (tooMuchFreeTime.equals("yes")) {
            System.out.println("\nHere are some ideas to pass your free time:");
            System.out.println("- Take up a hobby like painting, cooking, or playing an instrument.");
            System.out.println("- Read books or listen to podcasts.");
            System.out.println("- Exercise or go for a walk to stay active.");
            System.out.println("- Learn a new skill online, like coding or photography.");
            System.out.println("- Volunteer for a cause you care about.");
        } else {
            System.out.println("\nEnjoy your well-managed free time!");
        }
        System.out.print("\nDo you feel like you are too busy? (yes/no): ");
        String tooBusy = scan.nextLine().toLowerCase();
         if (tooBusy.equals("yes")) {
	               System.out.println("\nHere are some ways to make your schedule less busy:");
	               System.out.println("- Prioritize tasks and focus on the most important ones.");
	               System.out.println("- Learn to say 'no' to commitments that add unnecessary stress.");
	               System.out.println("- Delegate tasks where possible.");
	               System.out.println("- Break large tasks into smaller, manageable chunks.");
	               System.out.println("- Schedule breaks and downtime to recharge.");
	           } else {
	               System.out.println("\nGreat! It seems like your schedule is well-balanced.");
        }
}
}
