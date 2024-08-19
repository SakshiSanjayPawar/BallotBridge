import java.util.*;

public class VotingBooth {
    VotingBooth() {
        // Admin admin1 = new Admin();
    }

    Admin admin = new Admin();
    Scanner sc1 = new Scanner(System.in);
    Scanner sc2 = new Scanner(System.in);
    boolean checkAdmin = false, dataEntry = false;
    String PassEntered = "";

    // admin login
    public boolean AdminLogin() {
        System.out.println("Enter credentials for Admin login :-");

        do {
            String IdEntered = "";

            while (IdEntered.length() != 5) {
                System.out.println("Enter Adminid(5 length):-");
                IdEntered = sc1.nextLine();
            }

            System.out.println("Enter AdminPassword:-");
            PassEntered = sc2.nextLine();

            checkAdmin = admin.verifyAdmin(IdEntered, PassEntered);

        } while (!checkAdmin || PassEntered == null);
        return checkAdmin;
    }

    // get voting details from admin
    public void VotingData() {
        if (checkAdmin) {
            System.out.println("Please fill below Details:- ");
            dataEntry = admin.getVotingDetails();
            // System.out.println("ds=" + dataEntry);
            dataEntry = true;
            // System.out.println("dstatus=" + dataEntry);
        } else {
            System.out.println("No data entered yet by admin");
        }
    }

    // update vote
    public boolean Voting() {
        // return admin.checkEligibleVoter();
        System.out.println("d=" + dataEntry);
        if (dataEntry) {
            return admin.checkEligibleVoter();
        } else {
            System.out.println("Voting process not started yet!");
            return false;
        }
    }

    // result display
    public void declareResult() {
        admin.resultDisplay();
    }

    // add Voter
    public void addVoter() {
        admin.addNewVoter();
    }

    // delete Voter
    public void deleteVoter() {
        admin.deleteVoter();
    }

    // display list
    public void displayVotingList() {
        admin.displayVoterList();
    }

    // update voter password
    public void updateVoterPassword() {
        admin.updateVPassword();
    }

    // update Candidate Data
    public void updateCandidateData() {
        admin.getVotingDetails();

        dataEntry = true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nWelcome to Voter Portal");
        VotingBooth votingBooth = new VotingBooth();
        boolean resultDeclared = false, votingStarted = false;
        votingBooth.Voting();
        try {
            while (!resultDeclared) {
                System.out.println("Who are you?\n1.Admin \n2.Voter");
                int choice = sc.nextInt();

                if (choice == 1 && votingBooth.AdminLogin()) { // if admin
                    {
                        System.out.println("\nWelcome Admin!");
                        int adminInstruction = 0;

                        while (adminInstruction != 4) {
                            System.out.println("\nEnter option no u want to deal with:-\n1. Candidate List" +
                                    "\n2.Voter List\n3.Display Result\n4.Exit");
                            adminInstruction = sc.nextInt();
                            switch (adminInstruction) {
                                case 1:
                                // get voting data
                                {
                                    if (votingStarted) {// NOT allowed to update after voting starts
                                        System.out.println("Voting  started can't update now!");
                                    } else {
                                        votingBooth.updateCandidateData();
                                    }

                                }

                                    break;
                                case 2: {
                                    // voter list can be edited anytime
                                    System.out.println("Enter what u want to do:-" +
                                            "\n1.Add Voters\n2.Delete Voter\n" +
                                            "3.Display List of Voters with voting status\n4.Update password\n5.Exit ");
                                    int c = sc.nextInt();
                                    switch (c) {
                                        case 1: {
                                            votingBooth.addVoter();
                                        }
                                            break;
                                        case 2: {
                                            System.out.println(" No display for no such Id");
                                            votingBooth.deleteVoter();
                                        }
                                            break;
                                        case 3: {
                                            votingBooth.displayVotingList();
                                        }
                                            break;
                                        case 4: {
                                            System.out.println("---Fetching data----");
                                            votingBooth.updateVoterPassword();
                                        }
                                            break;
                                        case 5: {
                                            main(args);
                                        }
                                            break;
                                        default: {
                                            System.out.println("enter valid option");
                                        }
                                    }

                                }
                                    break;
                                case 3:
                                // get result if voting done
                                {
                                    if (votingStarted) {
                                        votingBooth.declareResult();
                                    } else {
                                        System.out.println("Voting not started!");
                                    }
                                    resultDeclared = true;
                                }
                                    break;
                                case 4: {

                                }
                                    break;

                                default: {
                                    System.out.println("Enter valid option");
                                }
                            }
                        }
                    }

                } else if (choice == 2) { // if voter
                    votingStarted = true;
                    votingBooth.Voting();

                }

            }

        } catch (

        InputMismatchException e) {
            System.out.println("\nPlease Enter valid Input according to given Instruction. ");
            main(args);
        } catch (Exception e) {
            System.out.println("\nSorry there is some error in System \n.Please TRY LATER.");
            main(args);
        }

    }

}