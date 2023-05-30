import java.sql.*;

class Resume {
    private String name;
    private String email;
    private String phone;
    private String summary;

    public Resume(String name, String email, String phone, String summary) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.summary = summary;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getSummary() {
        return summary;
    }

    public void saveToDatabase(Connection connection) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO resumes (name, email, phone, summary) VALUES (?, ?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, phone);
            statement.setString(4, summary);
            statement.executeUpdate();
            System.out.println("Resume saved to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

public class ResumeBuilder {
    private Connection connection;

    public ResumeBuilder() {
        try {
            // Connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/resume_builders", "root", "Pr@k@&h19");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createResume(String name, String email, String phone, String summary) {
        Resume resume = new Resume(name, email, phone, summary);
        resume.saveToDatabase(connection);
    }

    public static void main(String[] args) {
        ResumeBuilder resumeBuilder = new ResumeBuilder();

        // Create a sample resume
        String name = "John Doe";
        String email = "john.doe@example.com";
        String phone = "1234567890";
        String summary = "Experienced software developer with expertise in Java programming.";

        // Save the resume to the database
        resumeBuilder.createResume(name, email, phone, summary);
    }
}
