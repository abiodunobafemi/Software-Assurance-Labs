import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class VulnerableExample {

    public static void main(String[] args) throws Exception {
        String username, password;
        Scanner input = new Scanner(System.in);

        System.out.println("Enter username:");
        username = input.nextLine();

        System.out.println("Enter password:");
        password = input.nextLine();

        if (password.equals("admin123")) {
            System.out.println("Access granted.");
        } else {
            System.out.println("Access denied.");
            return;
        }

        System.out.println("Enter file path to read:");
        String filePath = input.nextLine();

        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();

        // Simulate user lookup
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "root");
        Statement stmt = conn.createStatement();

        System.out.println("Enter search username:");
        String search = input.nextLine();

        String query = "SELECT * FROM user_accounts WHERE username = '" + search + "'";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            System.out.println("User found: " + rs.getString("username"));
        }

        conn.close();
    }
}
