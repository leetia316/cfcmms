package com.jfok.cfcmms.share.module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfok.cfcmms.share.TreeNodeRecord;

public class ModuleGridNavigateTreeControl implements Serializable {

	private static final long serialVersionUID = 1L;
	private Boolean unionFilter = false; // 是否联合所有的filter
	private Map<String, TreeNodeRecord> allFilterTreeNodeMap; // 如果有导航树，存放导航树里面选中的值
	private TreeNodeRecord lastSelectedTreeNodeRecord; // 如果有导航树，存放导航树里面
																											// 最后一个选中的树的值
	
  private static final String[] DAXIE = {"①","②","③","④","⑤","⑥","⑦","⑧"};
	
	public ModuleGridNavigateTreeControl() {
		allFilterTreeNodeMap = new HashMap<String, TreeNodeRecord>();
	}

	/**
	 * 用户单击了某一个treeitem以后，来执行这里的操作
	 * 
	 * @param treeId
	 * @param treeName
	 * @param nodeRecord
	 */
//	public void filterTreeExecute(ModuleControl control, String treeId, String treeName,
//			TreeNodeRecord nodeRecord) {
//		nodeRecord.setTitle(treeName + ":" + nodeRecord.getTitle());
//		lastSelectedTreeNodeRecord = nodeRecord;
//		allFilterTreeNodeMap.put(treeId, nodeRecord);
//		control.navigateTreeItemChanged();
//	}

	// 取得筛选字段的描述
	public String getTreeFilterText() {
		if (lastSelectedTreeNodeRecord == null)
			return null;
		if (unionFilter) {
			String result = "";
			int i = 0;
			for (String treeId : allFilterTreeNodeMap.keySet())
				if (allFilterTreeNodeMap.get(treeId).getFieldname() != null)
					result = result + DAXIE[i++]+ allFilterTreeNodeMap.get(treeId).getText() + "; ";
			return result;
		} else if (lastSelectedTreeNodeRecord.getFieldname() != null)
			return lastSelectedTreeNodeRecord.getText();
		else
			return null;

	}

	// 取得筛选字段的描述,生成list,用于export 导出时，加在表头上面
	public List<String> getTreeFilterTextList() {
		List<String> result = new ArrayList<String>();
		if (lastSelectedTreeNodeRecord != null) {
			if (unionFilter) {
				for (String treeId : allFilterTreeNodeMap.keySet())
					if (allFilterTreeNodeMap.get(treeId).getFieldname() != null)
						result.add(allFilterTreeNodeMap.get(treeId).getText());
			} else if (lastSelectedTreeNodeRecord.getFieldname() != null)
				result.add(lastSelectedTreeNodeRecord.getText());
		}
		return result;
	}

	@Override
	public String toString() {
		return "ModuleGridNavigateTreeControl [unionFilter=" + unionFilter + ", allFilterTreeNodeMap="
				+ allFilterTreeNodeMap + ", lastSelectedTreeNodeRecord=" + lastSelectedTreeNodeRecord + "]";
	}

	public Boolean getUnionFilter() {
		return unionFilter;
	}

	public void setUnionFilter(Boolean unionFilter) {
		this.unionFilter = unionFilter;
	}

	public Map<String, TreeNodeRecord> getAllFilterTreeNodeMap() {
		return allFilterTreeNodeMap;
	}

	public void setAllFilterTreeNodeMap(Map<String, TreeNodeRecord> allFilterTreeNodeMap) {
		this.allFilterTreeNodeMap = allFilterTreeNodeMap;
	}

	public TreeNodeRecord getLastSelectedTreeNodeRecord() {
		return lastSelectedTreeNodeRecord;
	}

	public void setLastSelectedTreeNodeRecord(TreeNodeRecord lastSelectedTreeNodeRecord) {
		this.lastSelectedTreeNodeRecord = lastSelectedTreeNodeRecord;
	}

}
