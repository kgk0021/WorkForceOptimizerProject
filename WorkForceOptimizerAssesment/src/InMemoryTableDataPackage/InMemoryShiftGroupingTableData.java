package InMemoryTableDataPackage;

import java.util.HashMap;

public class InMemoryShiftGroupingTableData {

	private HashMap<String, String> parentChildShiftGroupCodeData = null;
	private static InMemoryShiftGroupingTableData inMemoryShiftGroupingTableData = new InMemoryShiftGroupingTableData();

	public static InMemoryShiftGroupingTableData getInMemoryShiftGroupingTableData() {
		return inMemoryShiftGroupingTableData;
	}

	private InMemoryShiftGroupingTableData() {
		this.parentChildShiftGroupCodeData = new HashMap<String, String>();
	}

	public String getParentOfChildShiftGroupCode(String childShiftGroupCode) {

		return parentChildShiftGroupCodeData.get(childShiftGroupCode);
	}

	public void setParentOfChildShiftGroupCode(String childShiftGroupCode, String parentShiftGroupCode) {

		parentChildShiftGroupCodeData.put(childShiftGroupCode, parentShiftGroupCode);
	}
}
