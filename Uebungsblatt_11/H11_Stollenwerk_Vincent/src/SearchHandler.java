import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class SearchHandler implements ActionListener {
	MainFrame mF;

	public SearchHandler(MainFrame mF) {
		this.mF = mF;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String query = mF.searchField.getText();

		if (query.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Search field is empty.");
			return;
		}

		Manager manager = Manager.createManagerInst(mF);

		if (mF.searchStudentRadioButton.isSelected()) {
			ArrayList<Student> queryResults = manager.searchStudentWithCategory(mF.filterCategory.getSelectedIndex(), query);

			manager.filterStudents = query;
			manager.filterCategoryStudents = mF.filterCategory.getSelectedIndex();
			manager.showStudents(queryResults);
		} else if (mF.searchProfRadioButton.isSelected()) {
			ArrayList<Professor> queryResults = manager.searchProfWithCategory(mF.filterCategory.getSelectedIndex(), query);

			manager.filterProfs = query;
			manager.filterCategoryProfs = mF.filterCategory.getSelectedIndex();
			manager.showProfs(queryResults);
		} else if (mF.searchModuleRadioButton.isSelected()) {
			ArrayList<Module> queryResults = manager.searchModuleWithCategory(mF.filterCategory.getSelectedIndex(), query);

			manager.filterModules = query;
			manager.filterCategoryModules = mF.filterCategory.getSelectedIndex();
			manager.showModules(queryResults);
		}
	}
}
