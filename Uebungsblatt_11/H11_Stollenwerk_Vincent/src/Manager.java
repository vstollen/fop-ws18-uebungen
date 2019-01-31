import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;

public class Manager {
	private static MainFrame mainFrame = null;
	public String studentFile = "Studenten.txt";
	public ArrayList<Student> students = new ArrayList<Student>();
	public ArrayList<Student> filteredStudents = null;
	public String filterStudents = "";
	public String profFile = "Professoren.txt";
	public ArrayList<Professor> profs = new ArrayList<Professor>();
	public ArrayList<Professor> filteredProfs = null;
	public String filterProfs = "";
	public String moduleFile = "Module.txt";
	public ArrayList<Module> modules = new ArrayList<Module>();
	public ArrayList<Module> filteredModules = null;
	public String filterModules = "";

	static Manager inst = null;

	public static Manager createManagerInst(MainFrame mF) {
		if (mainFrame == null)
			mainFrame = mF;
		if (inst == null)
			inst = new Manager();
		return inst;
	}

	private Manager() {
		String infoMessage = null;
		try {
			readStudent(studentFile);
		} catch (FileNotFoundException e) {
			infoMessage = "Es gibt keine Datei(en) namens: Studenten.txt";
		}
		try {
			readProf(profFile);
		} catch (FileNotFoundException e) {
			if (infoMessage == null)
				infoMessage = "Es gibt keine Datei(en) namens: Professoren.txt";
			else
				infoMessage = infoMessage + ", Professoren.txt";
		}
		try {
			readModule(moduleFile);
		} catch (FileNotFoundException e) {
			if (infoMessage == null)
				infoMessage = "Es gibt keine Datei namens: Module.txt\n Die Tabelle wird leer gelassen!";
			else
				infoMessage = infoMessage + ", Module.txt\n Entsprechende Tabellen werden leer gelassen!";
		}
		if (infoMessage != null)
			JOptionPane.showMessageDialog(null, infoMessage, "Information", JOptionPane.INFORMATION_MESSAGE);
	}

	@SuppressWarnings("resource")
	public void readStudent(String fileName) throws FileNotFoundException {
		String outString = "";
		ArrayList<String> lines = new ArrayList<String>();
		Scanner sc = new Scanner(new FileInputStream(new File(fileName)), "UTF-8");
		while (sc.hasNextLine()) {
			lines.add(sc.nextLine());
		}
		String[] data;
		for (String info : lines) {
			data = info.split("\t");
			if (getStudent(data[5]) == null) {
				students.add(new Student(data));
				mainFrame.studentModel.addRow(data);
			} else {
				if (!outString.isEmpty())
					outString = outString + ", ";
				outString = outString + data[5];
			}
		}
		mainFrame.resizeColumnWidth(mainFrame.studentTable);
		if (!outString.isEmpty())
			JOptionPane.showMessageDialog(null,
					"Daten von Student(en) mit Matrikelnummer(n) " + outString
							+ " waren schon vorhanden. Sie wurden daher nicht ersetzt!",
					"Information", JOptionPane.INFORMATION_MESSAGE);
	}

	@SuppressWarnings("resource")
	public void readProf(String fileName) throws FileNotFoundException {
		String outString = "";
		ArrayList<String> lines = new ArrayList<String>();
		Scanner sc = new Scanner(new FileInputStream(new File(fileName)), "UTF-8");
		while (sc.hasNextLine()) {
			lines.add(sc.nextLine());
		}
		String[] data;
		for (String info : lines) {
			data = info.split("\t");
			if (getProf(data[5]) == null) {
				profs.add(new Professor(data));
				mainFrame.profModel.addRow(data);
			} else {
				if (!outString.isEmpty())
					outString = outString + ", ";
				outString = outString + data[5];
			}
		}
		mainFrame.resizeColumnWidth(mainFrame.profTable);
		if (!outString.isEmpty())
			JOptionPane.showMessageDialog(null,
					"Daten von Professor(en) mit Kürzel(n) " + outString
							+ " waren schon vorhanden. Sie wurden daher nicht ersetzt!",
					"Information", JOptionPane.INFORMATION_MESSAGE);
	}

	@SuppressWarnings("resource")
	public void readModule(String fileName) throws FileNotFoundException {
		String outString = "";
		ArrayList<String> lines = new ArrayList<String>();
		Module m;
		Scanner sc = new Scanner(new FileInputStream(new File(fileName)), "UTF-8");
		while (sc.hasNextLine()) {
			lines.add(sc.nextLine());
		}
		String[] data;
		for (String info : lines) {
			data = info.split("\t");
			if (getModule(data[1]) == null) {
				m = new Module(data, this);
				if (getProf(m.profID) == null) {
					JOptionPane.showMessageDialog(null,
							"Der Professor mit Kürzel " + m.profID + " existiert nicht. Das Modul mit Modulnummer "
									+ m.nr + " konnte nicht erstellt werden.",
							"Information", JOptionPane.INFORMATION_MESSAGE);
				} else {
					modules.add(m);
					mainFrame.moduleModel.addRow(m.data);
				}
			} else {
				if (!outString.isEmpty())
					outString = outString + ", ";
				outString = outString + data[1];
			}
		}
		mainFrame.resizeColumnWidth(mainFrame.moduleTable);
		if (!outString.isEmpty())
			JOptionPane.showMessageDialog(null,
					"Daten von Modul(en) mit Modulnummer(n) " + outString
							+ " waren schon vorhanden. Sie wurden daher nicht ersetzt!",
					"Information", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * @param name
	 * @return all students who's fore- or surname contains name
	 */
	public ArrayList<Student> searchStudentName(String name) {

		ArrayList<Student> results = new ArrayList<>();

		for (Student student : students) {
			if (student.name.contains(name) || student.famName.contains(name)) {
				results.add(student);
			}
		}

		if (results.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Es gibt keinen Studenten mit dem Teil - Namen: " + name);
		}

		return results;
	}

	/**
	 * @param name
	 * @return all professors who's fore- or surname contains name
	 */
	public ArrayList<Professor> searchProfName(String name) {

		ArrayList<Professor> results = new ArrayList<>();

		for (Professor prof : profs) {
			if (prof.name.contains(name) || prof.famName.contains(name)) {
				results.add(prof);
			}
		}

		if (results.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Es gibt keinen Professor mit dem Teil - Namen: " + name);
		}

		return results;
	}

	/**
	 * @param name
	 * @return all modules who's name contains name
	 */
	public ArrayList<Module> searchModuleName(String name) {

		ArrayList<Module> results = new ArrayList<>();

		for (Module module : modules) {
			if (module.name.contains(name)) {
				results.add(module);
			}
		}

		if (results.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Es gibt kein Modul mit dem Teil - Namen: " + name);
		}

		return results;
	}

	public Student getStudent(String studID) {
		for (Student s : students)
			if (s.studID.equals(studID))
				return s;
		return null;
	}

	public Professor getProf(String staffID) {
		for (Professor p : profs)
			if (p.staffID.equals(staffID))
				return p;
		return null;
	}

	public Module getModule(String nr) {
		for (Module m : modules)
			if (m.nr.equals(nr))
				return m;
		return null;
	}

	/**
	 * Displays new student data.
	 * @param list Students to be displayed
	 */
	public void showStudents(ArrayList<Student> list) {

		mainFrame.studentModel.getDataVector().removeAllElements();
		mainFrame.studentModel.fireTableDataChanged();

		for (Student student : list) {
			mainFrame.studentModel.addRow(student.data);
		}


		filteredStudents = list.size() < students.size() ? list : null;

		mainFrame.resizeColumnWidth(mainFrame.studentTable);
	}

	/**
	 * Displays new professor data
	 * @param list Professors to be displayed
	 */
	public void showProfs(ArrayList<Professor> list) {

		mainFrame.profModel.getDataVector().removeAllElements();
		mainFrame.profModel.fireTableDataChanged();

		for (Professor prof : list) {
			mainFrame.profModel.addRow(prof.data);
		}

		filteredProfs = list.size() < profs.size() ? list : null;

		mainFrame.resizeColumnWidth(mainFrame.profTable);
	}

	/**
	 * Displays new Module data
	 * @param list Modules to be displayed
	 */
	public void showModules(ArrayList<Module> list) {

		mainFrame.moduleModel.getDataVector().removeAllElements();
		mainFrame.moduleModel.fireTableDataChanged();

		for (Module module : list) {
			mainFrame.moduleModel.addRow(module.data);
		}

		filteredModules = list.size() < modules.size() ? list : null;

		mainFrame.resizeColumnWidth(mainFrame.moduleTable);
	}

	public ArrayList<Module> getStudentModules(String studID) {
		ArrayList<Module> out = new ArrayList<Module>();
		for (Module m : modules) {
			if (m.participants.contains(studID))
				out.add(m);
		}
		return out;
	}

	public ArrayList<Module> getProfModules(String staffID) {
		ArrayList<Module> out = new ArrayList<Module>();
		for (Module m : modules) {
			if (m.profID.equals(staffID))
				out.add(m);
		}
		return out;
	}

	public void writeStudent(String fileName) throws IOException {
		FileWriter fw = new FileWriter(fileName);
		BufferedWriter bw = new BufferedWriter(fw);

		for (int i = 0; i < students.size(); i++) {
			bw.write(students.get(i).getString());
			if (i < students.size() - 1)
				bw.newLine();
		}
		bw.close();
	}

	public void writeProf(String fileName) throws IOException {
		FileWriter fw = new FileWriter(fileName);
		BufferedWriter bw = new BufferedWriter(fw);

		for (int i = 0; i < profs.size(); i++) {
			bw.write(profs.get(i).getString());
			if (i < profs.size() - 1)
				bw.newLine();
		}
		bw.close();
	}

	public void writeModule(String fileName) throws IOException {
		FileWriter fw = new FileWriter(fileName);
		BufferedWriter bw = new BufferedWriter(fw);

		for (int i = 0; i < modules.size(); i++) {
			bw.write(modules.get(i).getString());
			if (i < modules.size() - 1)
				bw.newLine();
		}
		bw.close();
	}

	public void writeJoin(String fileName) throws IOException {
		FileWriter fw = new FileWriter(fileName);
		BufferedWriter bw = new BufferedWriter(fw);
		for (int i = 0; i < mainFrame.joinModel.getRowCount(); i++) {
			String row = "";
			for (int j = 0; j < mainFrame.joinModel.getColumnCount(); j++) {
				row = row + mainFrame.joinModel.getValueAt(i, j);
				if (j < mainFrame.joinModel.getColumnCount() - 1)
					row = row + "\t";
			}
			bw.write(row);
			if (i < mainFrame.joinModel.getRowCount() - 1)
				bw.newLine();
		}
		bw.close();
	}
}