import java.util.*;

class Voter {

    Voter left = null, right = null;

    String voterID, voterPassword;

    boolean Vote_status = false;

    void display() {

        System.out.println("Id " + this.voterID + "\tPassword: " + this.voterPassword + "\tFlag= " + this.Vote_status);

    }

}

public class VoterTree {

    Scanner sc = new Scanner(System.in);

    Voter root = null; // first node of tree

    void add_voters(String ID, String pass_word) {

        Voter temp = new Voter();

        temp.voterID = ID;

        temp.voterPassword = pass_word;

        if (root == null) { // if the root node is empty

            root = temp;

        } else {

            Voter ptr = root;

            while (ptr != null) {

                if (temp.voterID.compareTo(ptr.voterID) < 0) {

                    if (ptr.left == null) {

                        ptr.left = temp;

                        break;

                    } else {

                        ptr = ptr.left;

                    }

                } else if (temp.voterID.compareTo(ptr.voterID) > 0) {

                    if (ptr.right == null) {

                        ptr.right = temp;

                        break;

                    } else {

                        ptr = ptr.right;

                    }

                } else {

                    System.out.println("Already present");

                    break;

                }

            }

        }
    }

    void VoterList() {

        add_voters("INMH101", "P101");
        add_voters("INKA103", "P103");
        add_voters("INMH103", "P103");
        add_voters("INMH102", "P102");
        add_voters("INMH1502", "P1502");
        add_voters("INUP105", "P105");
        add_voters("INUP1105", "P1105");
        add_voters("INRJ105", "P105");
        add_voters("INKA105", "P105");
        add_voters("INMH114", "P114");
        add_voters("INMH204", "P204");
        add_voters("INKA904", "P904");

    }

    // method to add voter in BST

    void add_voters() {

        char ch;

        do {

            Voter temp = new Voter();

            System.out.println("Enter voterId: ");

            temp.voterID = sc.nextLine();

            System.out.println("Enter password: ");

            temp.voterPassword = sc.nextLine();

            if (root == null) { // if the root node is empty

                root = temp;

            } else {

                Voter ptr = root;

                while (ptr != null) {

                    if (temp.voterID.compareTo(ptr.voterID) < 0) {

                        if (ptr.left == null) {

                            ptr.left = temp;

                            break;

                        } else {

                            ptr = ptr.left;

                        }

                    } else if (temp.voterID.compareTo(ptr.voterID) > 0) {

                        if (ptr.right == null) {

                            ptr.right = temp;

                            break;

                        } else {

                            ptr = ptr.right;

                        }

                    } else {

                        System.out.println("Already present");

                        break;

                    }

                }

            }

            System.out.print("\nDo you want to add more voters ?\nPress 'Y' to continue\nPress 'N' to exit :");

            ch = sc.nextLine().charAt(0);

            System.out.println("");

        } while (ch != 'N' && ch != 'n');

    }

    // method to display the BST

    public void inorder() {

        inorder(this.root);

    }

    private void inorder(Voter node) {

        if (node != null) {

            inorder(node.left);

            node.display();

            inorder(node.right);

        }

    }

    // method to search

    boolean search(boolean flag) {

        System.out.println("Enter YOUR ID: ");
        String Search_id = sc.nextLine();
        System.out.println("Enter the Password: ");
        String Search_password = sc.nextLine();
        // System.out.println("not while");
        Voter ptr = root;
        while (ptr != null) {
            // System.out.println("in while");
            if (Search_id.equals(ptr.voterID) && Search_password.equals(ptr.voterPassword)) { // if word is similar to
                // root node
                System.out.println("\n---Proceding Further---.");
                if (flag) {
                    ptr.Vote_status = true;
                    System.out.println("voting done\n");
                    return true;
                }
                // return ptr;
            } else if (Search_id.compareTo(ptr.voterID) < 0) {
                ptr = ptr.left; // if voter is present at left side
            } else {
                ptr = ptr.right; // if voteris present at right side
            }
        }
        System.out.println("Invalid Credentials"); // if voter is not present
        return false;
    }

    // voting
    boolean voteProcess() {
        VoterTree tree = new VoterTree(); // object of BinarySearchTree class
        tree.VoterList();
        return tree.search(true); // calling search function
    }

    // method to update
    void update_password() {
        System.out.println("Enter the ID to update: ");
        String Search_id = sc.nextLine();
        Voter ptr = root;
        while (ptr != null) {
            if (Search_id.equals(ptr.voterID)) {
                System.out.println("Enter its new Password: ");
                ptr.voterPassword = sc.nextLine();
                return;
            } else if (Search_id.compareTo(ptr.voterID) < 0) {
                ptr = ptr.left;
            } else {
                ptr = ptr.right;
            }
        }
        System.out.println("ID Not Found");
    }

    // method to delete
    public void delete() {
        System.out.print("Enter the ID to delete: ");
        String key = sc.nextLine();
        this.root = delete(this.root, key);
    }

    private Voter delete(Voter root, String key) {

        /* Base Case: If the tree is empty */

        if (root == null)
            return root;

        /* Otherwise, recur down the tree */

        if (key.compareTo(root.voterID) < 0)
            root.left = delete(root.left, key);

        else if (key.compareTo(root.voterID) > 0)
            root.right = delete(root.right, key);

        // if key is same as root's key, then This is the node to be deleted

        else {

            // node with only one child or no child

            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // node with two children: Get the inorder successor (smallest in the right
            // subtree)

            Voter temp = getSuccessor(root.right);
            root.voterID = temp.voterID;
            root.voterPassword = temp.voterPassword;

            // Delete the inorder successor

            root.right = delete(root.right, root.voterID);

        }

        return root;

    }

    private Voter getSuccessor(Voter node) {
        while (node.left != null) {
            node.voterID = node.left.voterID;
            node.voterPassword = node.left.voterPassword;
            node = node.left;
        }
        return node;
    }

    public static void main(String[] args) {

        VoterTree tree = new VoterTree(); // object of BinarySearchTree class
        tree.VoterList();
        tree.search(true);
        tree.inorder();

        // Scanner sc = new Scanner(System.in);

        // int ch;

        // do {

        // System.out.println("\t\tMENU\n*************************************\nEnter 1
        // to create a dictionary\n" +
        // "Enter 2 to search a word\n" +

        // "Enter 3 to update dictionary\n" +

        // "Enter 4 to display the dictionary\n" +

        // "Enter 5 to delete a word\n" +

        // "Enter 0 to quit.\n*********************************************");

        // System.out.println("Enter the choice: ");

        // ch = sc.nextInt();

        // sc.nextLine();

        // if (ch == 0)

        // break;

        // switch (ch) {

        // case 1:

        // tree.add_voters(); // calling create function

        // break;

        // case 2: {
        // // Voter Search;
        // // Search = tree.search(); // calling search function

        // // if (Search != null) {

        // // System.out.println(Search.voterID);

        // // System.out.println(Search.Vote_status);

        // // tree.update_Vote_status(Search);

        // // System.out.println(Search.Vote_status);

        // }

        // break;

        // case 3:

        // tree.update_password(); // calling update function

        // break;

        // case 4:

        // tree.inorder(); // calling inorder function

        // break;

        // case 5:

        // tree.delete(); // calling delete function

        // break;

        // default:

        // System.out.println("Invalid Option");

        // break;

        // }

        // } while (ch != 0);

    }

}
