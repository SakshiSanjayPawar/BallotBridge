import java.util.Scanner;

/*
  Welcome to Voter Portal
list of voter done
d=false
Voting process not started yet!
Who are you?
1.Admin
2.Voter
1
Enter credentials for Admin login :-
Enter Adminid(5 length):-
A1234
Enter AdminPassword:-
admin@123

Welcome Admin!

Enter option no u want to deal with:-
1. Candidate List
2.Voter List
3.Display Result
4.Exit
1
Enter no of Candidates for position:-
2
Enter details of Candidates:-
ID of Candidate 1:-
7
Name of Candidate 1:-
ajit
Phone No of Candidate(10 digit) 1:-
7410258963
ID of Candidate 2:-
9
Name of Candidate 2:-
rohan
Phone No of Candidate(10 digit) 2:-
4102587963

Enter option no u want to deal with:-
1. Candidate List
2.Voter List
3.Display Result
4.Exit
4
Who are you?
1.Admin
2.Voter
2
d=true
Enter YOUR ID:
INMH204
Enter the Password:
P204

---Proceding Further---.
voting done

 Candidate List :-
7.ajit
9.rohan
Enter valid Candidate Id to vote=
7
Who are you?
1.Admin
2.Voter
2
d=true
Enter YOUR ID:
INMH114
Enter the Password: 
45
Invalid Credentials
No such Voter 
Who are you?
1.Admin 
2.Voter
1
Enter credentials for Admin login :-
Enter Adminid(5 length):-
A1234
Enter AdminPassword:-
admin@123

Welcome Admin!

Enter option no u want to deal with:-
1. Candidate List
2.Voter List
3.Display Result
4.Exit
3

Results are as Follows:-
7.ajit   Votes=1
9.rohan   Votes=0
Congratulations! ajit You are elected by 1 Votes.

Enter option no u want to deal with:-
1. Candidate List
2.Voter List
3.Display Result
4.Exit
4
 

 */
public class Admin {

    // private admin details
    private String Adminid = "A1234";
    private String AdminPassword = "admin@123";
    VoterTree voterTree = new VoterTree();
    Candidate candidate = new Candidate();

    Admin() {// initialize voter list
        voterTree.VoterList();
        System.out.println("list of voter done");
    }

    public boolean verifyAdmin(String Inputid, String Inputpass) {
        boolean verified = false;
        if (Inputid.equals(Adminid) && Inputpass.equals(AdminPassword)) {
            verified = true;
        }
        return verified;
    }

    // define voting system -candidates and voters
    public boolean getVotingDetails() {
        boolean obtained = false;
        candidate.getCandidateDetails();
        obtained = true;
        return obtained;
    }

    // result
    public void resultDisplay() {
        candidate.ResultDisplay();
    }

    // check voter and update his flag +increase candidate votes
    public boolean checkEligibleVoter() {
        Scanner sc = new Scanner(System.in);
        // System.out.println("P=" + voterTree.voteProcess());
        if (voterTree.voteProcess()) {
            candidate.AllCandidateDisplay();
            System.out.println("Enter valid Candidate Id to vote=");
            int VotedCandidte = sc.nextInt();
            candidate.VoteUpdate(VotedCandidte);
        } else {
            System.out.println("No such Voter ");
        }
        return false;
    }

    // delete Voter
    public void deleteVoter() {
        voterTree.delete();
    }

    // add new voter
    public void addNewVoter() {
        voterTree.add_voters();
    }

    // display list of voted voters
    public void displayVoterList() {
        voterTree.inorder();
    }

    // UPDATE PASSWORD
    public void updateVPassword() {
        voterTree.update_password();
    }

    public void displayCandidate() {

        candidate.AllCandidateDisplay();
    }

    public static void main(String[] args) {
        Admin a = new Admin();
        a.checkEligibleVoter();
        System.out.println("done check");
        // a.updateVPassword();
        // a.displayVoterList();
    }

}
