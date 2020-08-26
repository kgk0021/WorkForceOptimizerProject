package BootStrapperPackage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import CustomizedExceptionsPackage.ImportantDetailsMissingException;
import InMemoryTableDataPackage.InMemoryShiftGroupingTableData;
import TableSchemaPackage.ShiftGroupingTable;

public class BootStrappingOperations {

	private static BootStrappingOperations bootStrappingOperations = new BootStrappingOperations();
	private String user;
	private String password;
	private String driverClassName;
	private String url;

	private BootStrappingOperations() {

	}

	public static BootStrappingOperations getBootStrappingOperations() {
		return bootStrappingOperations;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public void loadDBConnectionDataFromDBConfigurationFile()
			throws FileNotFoundException, IOException, ImportantDetailsMissingException {

		FileReader reader = new FileReader("../Configuration/DBConfigurationFile");

		Properties p = new Properties();

		p.load(reader);
		this.url = p.getProperty("url");
		this.password = p.getProperty("password");
		this.driverClassName = p.getProperty("driverClassName");
		this.user = p.getProperty("user");

		if (url == null)
			throw new ImportantDetailsMissingException("url missing");
		if (user == null)
			throw new ImportantDetailsMissingException("user misisng ");
		if (password == null)
			throw new ImportantDetailsMissingException("password missing");
		if (driverClassName == null)
			throw new ImportantDetailsMissingException("driverClassName missing");

	}

	public void registerDBDriver() throws ClassNotFoundException {

		Class.forName(this.getDriverClassName());

	}

	public void connectLoadTableDataAndDisconnectFromDB() throws SQLException {
		Connection connection = DriverManager.getConnection(this.url, this.user, this.password);

		String sqlQueryString = "SELECT * FROM ?";
		PreparedStatement statement = connection.prepareStatement(sqlQueryString);
		statement.setString(1, ShiftGroupingTable.NAME);
		ResultSet resultSet = statement.executeQuery(sqlQueryString);
		while (resultSet.next()) {
			String parent = resultSet.getString(ShiftGroupingTable.PARENT_CODE);
			String child = resultSet.getString(ShiftGroupingTable.CHILD_CODE);
			InMemoryShiftGroupingTableData.getInMemoryShiftGroupingTableData().setParentOfChildShiftGroupCode(child,
					parent);
		}

		if (resultSet != null)
			resultSet.close();
		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();

	}
}
