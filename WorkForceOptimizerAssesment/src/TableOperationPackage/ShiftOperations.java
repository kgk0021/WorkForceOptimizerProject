package TableOperationPackage;

import java.sql.*;

import InMemoryTableDataPackage.InMemoryShiftGroupingTableData;

public class ShiftOperations {

	private static ShiftOperations shiftOperations = new ShiftOperations();

	public static ShiftOperations getShiftOperations() {
		return shiftOperations;
	}

	private ShiftOperations() {

	}

	public boolean doesShiftbelongToGroupHierarchy(String shiftCode, String shiftGroupCode) throws SQLException {

		String parent = null;
		do
			parent = InMemoryShiftGroupingTableData.getInMemoryShiftGroupingTableData()
					.getParentOfChildShiftGroupCode(shiftCode);
		while (parent != null && !(shiftCode = parent).equals(shiftGroupCode));

		return parent != null && parent.equals(shiftGroupCode);

	}

}
