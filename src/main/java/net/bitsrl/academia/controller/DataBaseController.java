package net.bitsrl.academia.controller;

import java.util.Collection;
import java.util.Scanner;
import net.bitsrl.academia.database.InMemoryRepositoryAgent;
import net.bitsrl.academia.database.RepositoryAgent;
import net.bitsrl.academia.model.Agent;

public class DataBaseController {
    private RepositoryAgent repAgent = new InMemoryRepositoryAgent();
    Scanner userInput = new Scanner(System.in);

    private void controlAgent() {
        System.out.print("--AGENT MENU--\n0.Go Back\n1.Read Alls\n2.Read by LastName\n3.Create\n4.Update\n5.Delete" +
                "\nScegli Numero: ");
        int num = userInput.nextInt();
        switch (num) {
            case 0: //Go Back
                start();
                break;
            case 1: // Read Alls
                System.out.println("-READ ALLS-");
                Collection<Agent> agents = repAgent.getAll();
//                for(Agent a : agents){
//                    System.out.println(a);
//                }
//                agents.forEach(a -> System.out.println(a));
                agents.forEach(System.out::println);
                System.out.println();
                break;
            case 2: //Read by LastName
                System.out.println("-READ BY LASTNAME-");
                System.out.print("Inserisci il cognome o parte di esso: ");
                String lastNameLike = userInput.next();
                Collection<Agent> agents1 = repAgent.getByLastnameLike(lastNameLike);
                agents1.forEach(System.out::println);
                break;
            case 3: //Create
                System.out.println("-CREATE-");
                System.out.print("ID: ");
                int inputid = userInput.nextInt();
                System.out.print("Name: ");
                String inputName = userInput.next();
                System.out.print("LastName: ");
                String inputLastName = userInput.next();
                repAgent.create(new Agent(inputid, inputName, inputLastName));
                System.out.println();
                break;
            case 4: //Update
                System.out.println("-UPDATE-");
                break;
            case 5: //Delete
                System.out.println("-DELETE-");
                break;
            default: //Default
                System.out.println("-DEFAULT-");
                break;
        }
        start();
    }

    private void controlCourse() {
    }

    public void start() {
        System.out.println("Inserisci un numero per accedere al menu richiesto");
        System.out.print("1.Agente\n2.Corso\nInserisci numero: ");
        int num = userInput.nextInt();
        switch (num) {
            case 1: //Agent
                controlAgent();
                break;
            case 2://Course
                controlCourse();
                break;
            default:
                System.out.println("DEFINIRE");
                break;
        }
        start();
    }
}