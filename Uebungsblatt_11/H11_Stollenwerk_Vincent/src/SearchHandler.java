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
			ArrayList<Student> queryResults = manager.searchStudentName(query);

			manager.filterStudents = query;
			manager.showStudents(queryResults);
		} else if (mF.searchProfRadioButton.isSelected()) {
			ArrayList<Professor> queryResults = manager.searchProfName(query);

			manager.filterProfs = query;
			manager.showProfs(queryResults);
		} else if (mF.searchModuleRadioButton.isSelected()) {
			ArrayList<Module> queryResults = manager.searchModuleName(query);

			manager.filterModules = query;
			manager.showModules(queryResults);
		}
	}
}
