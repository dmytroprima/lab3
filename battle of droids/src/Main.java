import com.Droid.Droid;
import com.Droid.Wizard;
import com.Droid.Fighter;
import com.Droid.Tank;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class Main {

    static Random rnd = new Random();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {
        while (true) {
            System.out.print("1 - TO BATTLE!; 0 - exit: ");
            int choice = scanner.nextInt();
            if (choice == 1) {battle();}
            else if (choice == 0) {break;}
        }
    }

    static void battle() throws FileNotFoundException {
        System.out.print("1 - Duel; 2 - Team VS Team: ");
        int choice = scanner.nextInt();
        List<Droid> team1 = new ArrayList<>();
        List<Droid> team2 = new ArrayList<>();
        if (choice==1){
            System.out.print("Choose first droid: 1 - Tank, 2 - Fighter, 3 - Wizard: ");
            choice = scanner.nextInt();
            System.out.print("Choose name of first droid: ");
            String name = scanner.next();
            team1.add(createDroid(choice, name));

            System.out.print("Choose second droid: 1 - Tank, 2 - Fighter, 3 - Wizard: ");
            choice = scanner.nextInt();
            System.out.print("Choose name of second droid: ");
            name = scanner.next();
            team2.add(createDroid(choice, name));

        } else if (choice == 2) {
            team1.add(createDroid(1, "Tank1"));
            team1.add(createDroid(2, "Fighter1"));
            team1.add(createDroid(3, "Wizard1"));

            team2.add(createDroid(1, "Tank2"));
            team2.add(createDroid(2, "Fighter2"));
            team2.add(createDroid(3, "Wizard2"));

        }

        System.out.print("Enter file name: ");
        String nameFile = scanner.next();
        PrintStream myStream = new PrintStream(nameFile);

        while (true) {
            Droid droid1 = foundAliveDroid(team1);
            Droid droid2 = foundAliveDroid(team2);
            if (droid1.isAlive() && droid2.isAlive()) {
                System.out.println(droid1.getName()+ " attacks " + droid2.getName());
                beatDroid(droid2, droid1, myStream);
                if (droid2.getHealth() != 0) {
                    System.out.println(droid2.getName()+ " attacks " + droid1.getName());
                    beatDroid(droid1, droid2, myStream);
                }
            }
            else
                continue;

            List<List<Droid>> teams = Arrays.asList(team1, team2);
            for (List<Droid> team : teams) {
                int teamhp = 0;
                for (Droid droid : team) {
                    teamhp += droid.getHealth();
                }
                if (teamhp <= 0) {
                    for (Droid droid : team) {
                        System.out.print(droid.getName() + " ");
                    }
                    System.out.println("IS DEAD!");
                    return;
                }
            }
        }
    }

    private static void beatDroid(Droid droid1, Droid droid2, PrintStream myStream) {
        droid2.dealDamage(droid1);
        System.out.print(droid1.getName() + " was damaged by " + droid2.getName() + "; ");
        System.out.println(droid1.getName() + " health = " + droid1.getHealth());
        myStream.println(droid1.getName() + " was damaged by " + droid2.getName() + "; ");
        myStream.println(droid1.getName() + " health = " + droid1.getHealth());
    }

    static Droid createDroid(int choice, String name) {
        Droid droid = new Droid(name, 0, 0);
        if (choice == 1) {
            droid = new Tank(name, 220, 22);
        } else if (choice == 2) {
            droid = new Fighter(name, 140, 28);
        } else if (choice == 3) {
            droid = new Wizard(name, 80, 15);
        }
        return droid;
    }

    static Droid foundAliveDroid(List<Droid> team){
        List<Droid> locallist =  new ArrayList<>();
        for (Droid droid:team){
            if (droid.getHealth()>0){
              locallist.add(droid);
            }
        }
        return locallist.get(rnd.nextInt(locallist.size()));
    }
}