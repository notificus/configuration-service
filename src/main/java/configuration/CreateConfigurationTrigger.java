package configuration;

import org.h2.api.Trigger;
import org.springframework.context.annotation.Profile;

import java.sql.*;

@Profile("test")
public class CreateConfigurationTrigger implements Trigger {
    @Override
    public void init(Connection conn, String schemaName, String triggerName, String tableName, boolean before, int type) throws SQLException {

    }

    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {
        System.out.println("Triggered");
        int generatedKey;

        String cip = newRow[0].toString();
        //System.out.println(cip);

        PreparedStatement insertEmailConfigurationEntityStmt = null;
        PreparedStatement insertEmailConfigurationEntityEmails = null;
        PreparedStatement insertConfigurationEntity = null;

        String emailConfigurationEntitySQL =
                "INSERT INTO email_configuration_entity (email_configuration_entity_enabled) VALUES (TRUE)";
        String emailConfigurationEntityEmailsSQL =
                "INSERT INTO email_configuration_entity_emails VALUES (?, ?)";
        String configurationEntitySQL =
                "INSERT INTO configuration_entity VALUES (?, ?)";

        try {
            insertEmailConfigurationEntityStmt = conn.prepareStatement(emailConfigurationEntitySQL,
                    Statement.RETURN_GENERATED_KEYS);
            insertEmailConfigurationEntityEmails = conn.prepareStatement(emailConfigurationEntityEmailsSQL);
            insertConfigurationEntity = conn.prepareStatement(configurationEntitySQL);

            if (insertEmailConfigurationEntityStmt.executeUpdate() == 0) {
                throw new SQLException("Creating email configuration entity failed");
            }

            try (ResultSet generatedKeys = insertEmailConfigurationEntityStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedKey = generatedKeys.getInt("email_configuration_entity_id");
                    //System.out.println(generatedKey);
                } else {
                    throw new SQLException("Creating email configuration entity failed");
                }
            }

            insertEmailConfigurationEntityEmails.setInt(1, generatedKey);
            insertEmailConfigurationEntityEmails.setString(2, cip + "@usherbrooke.ca");
            if (insertEmailConfigurationEntityEmails.executeUpdate() == 0) {
                throw new SQLException("Cannot insert new user email address");
            }

            insertConfigurationEntity.setString(1, cip);
            insertConfigurationEntity.setInt(2, generatedKey);
            if (insertConfigurationEntity.executeUpdate() == 0) {
                throw new SQLException("Could not create configuration entity");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public void remove() throws SQLException {

    }
}
