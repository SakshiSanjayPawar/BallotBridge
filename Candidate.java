import java.util.Scanner;

public class Candidate {
    String CandidateName;
    String CandidatePhoneNo;
    int CandidateID;
    int NoOfVotes = 0;
    Scanner sc = new Scanner(System.in);
    Scanner sc1 = new Scanner(System.in);
    Scanner sc2 = new Scanner(System.in);
    Scanner sc3 = new Scanner(System.in);
    // array of candidates
    int noOfCandidates = 10;
    public Candidate CandidateList[] = new Candidate[noOfCandidates];

    int hash(int id) {
        int val = id % noOfCandidates;
        if (CandidateList[val] == null)
            return val;
        else {
            while (CandidateList[val] != null) {

                val = (val + 1) % noOfCandidates;
            }
            return val;
        }
    }

    boolean isFull() {
        int x = 0;
        for (int i = 0; i < noOfCandidates; i++) {
            if (CandidateList[i] != null) {
                x++;
            }
        }
        if (x == noOfCandidates)
            return true;
        else
            return false;

    }

    void displayCandidate() {
        System.out.println(this.CandidateID + "." + this.CandidateName);
    }

    void displayCandidateVotes() {
        System.out.println(this.CandidateID + "." + this.CandidateName
                + "   Votes=" + this.NoOfVotes);
    }

    public void getCandidateDetails() {
        System.out.println("Enter no of Candidates for position:- ");
        int pos1Candidates = sc.nextInt();

        System.out.println("Enter details of Candidates:-");

        for (int i = 1; i <= pos1Candidates; i++) {
            if (isFull()) {
                System.out.println("You cannot enter more candidates");
            } else {
                Candidate nn = new Candidate();
                System.out.println("ID of Candidate " + i + ":-");
                nn.CandidateID = sc1.nextInt();
                for (int i1 = 0; i1 < noOfCandidates; i1++) {
                    while (CandidateList[i1] != null && CandidateList[i1].CandidateID == nn.CandidateID) {
                        System.out.println("Already exist        Please reenter ID ");
                        nn.CandidateID = sc1.nextInt();
                    }
                }
                // System.out.println("name and phone no enter");
                // Name should contain some data atleast
                // check 10 digit no
                System.out.println("Name of Candidate " + i + ":-");
                nn.CandidateName = sc1.next();
                // while (nn.CandidateName.length() == 0) {
                // System.out.println("Name of Candidate " + i + ":-");
                // nn.CandidateName = sc1.nextLine();
                // }
                System.out.println("Phone No of Candidate(10 digit) " + i + ":-");
                nn.CandidatePhoneNo = sc2.nextLine();
                while (nn.CandidatePhoneNo.length() != 10) {
                    System.out.println("Please enter valid no.(10 digit) " + i + ":-");
                    nn.CandidatePhoneNo = sc2.nextLine();
                }

                int index = hash(nn.CandidateID);
                CandidateList[index] = nn;

            }

        }
    }

    void AllCandidateDisplay() {
        System.out.println(" Candidate List :-");
        for (int i = 0; i < noOfCandidates; i++) {
            if (CandidateList[i] != null) {
                CandidateList[i].displayCandidate();
            }

        }
    }

    void ResultDisplay() {
        int maxVotes = 0;
        String CandidateElected = "";
        System.out.println("\nResults are as Follows:-");
        for (int i = 0; i < noOfCandidates; i++) {
            if (CandidateList[i] != null) {
                CandidateList[i].displayCandidateVotes();
                if (maxVotes < CandidateList[i].NoOfVotes) {
                    maxVotes = CandidateList[i].NoOfVotes;
                    CandidateElected = CandidateList[i].CandidateName;
                }
            }

        }
        System.out.println("Congratulations! " + CandidateElected + " You are elected by "
                + maxVotes + " Votes.");
    }

    boolean VoteUpdate(int id) {

        int a = id % noOfCandidates;

        if (CandidateList[a] != null && CandidateList[a].CandidateID == id) {

            CandidateList[a].NoOfVotes++;
            return true;

        }

        else {

            a = (a + 1) % noOfCandidates;

            int flag = 0;

            while (CandidateList[a] != null && CandidateList[a].CandidateID != id && flag <= noOfCandidates) {

                a = (a + 1) % noOfCandidates;

                flag++;

            }

            if (flag > noOfCandidates || CandidateList[a] == null) {
                System.out.println("Candidate Does not exist! ");
                return false;
            }

            else {

                CandidateList[a].NoOfVotes++;
                return true;

            }

        }

    }

    void CandidateUpdate(int id) {

        int a = id % noOfCandidates;

        if (CandidateList[a] != null && CandidateList[a].CandidateID == id) {

            System.out.println("Enter Candidate's name which is to be updated:- ");

            CandidateList[a].CandidateName = sc1.nextLine();

            System.out.println("Enter Candidate's Phone number which is to be updated:- ");

            CandidateList[a].CandidateID = sc1.nextInt();

        }

        else {

            a = (a + 1) % noOfCandidates;
            int flag = 0;

            while (CandidateList[a] != null && CandidateList[a].CandidateID != id && flag <= noOfCandidates) {

                a = (a + 1) % noOfCandidates;

                flag++;

            }

            if (flag > noOfCandidates || CandidateList[a] == null)

                System.out.println("Candidate Does not exist! ");

            else {

                System.out.println("Enter Candidate's name which is to be updated:- ");

                CandidateList[a].CandidateName = sc1.nextLine();

                System.out.println("Enter Candidate's Phone number which is to be updated:- ");

                CandidateList[a].CandidateID = sc1.nextInt();

            }

        }

    }

    void CandidateDelete(int id) {
        int a = id % noOfCandidates;
        if (CandidateList[a] != null && CandidateList[a].CandidateID == id) {
            // System.out.println("Found >> Deleting Candidate");
            CandidateList[a] = null;
        } else {
            a = (a + 1) % noOfCandidates;
            int flag = 0;
            while (CandidateList[a] != null && CandidateList[a].CandidateID != id && flag <= noOfCandidates) {
                a = (a + 1) % noOfCandidates;
                flag++;
            }
            if (flag > noOfCandidates || CandidateList[a] == null)
                System.out.println("Candidate Does not exist! ");
            else
                CandidateList[a] = null; // Found >> Deleting Candidate
        }
    }

    public static void main(String[] args) {
        Candidate candidate = new Candidate();
        // candidate.change_CandidateDetails();
        candidate.getCandidateDetails();
        candidate.AllCandidateDisplay();
        candidate.ResultDisplay();

    }

}
