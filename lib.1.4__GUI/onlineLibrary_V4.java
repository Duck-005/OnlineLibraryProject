package onlineLibraryProject.online_Library_V4;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;

public class onlineLibrary_V4 {
    final private String databaseFilePath = "database.csv";
    final private String issuedDatabaseFilePath = "issuedDatabase.csv";
    final ArrayList<ArrayList<String>> database, issuedDatabase;
    String[] col = {"ID", "Name", "Author(s)", "Language", "Pub. Year", "Genre"};

    DefaultTableModel availableBooksModel, issuedBooksModel;

    public onlineLibrary_V4() {
        database = getData(databaseFilePath);
        issuedDatabase = getData(issuedDatabaseFilePath);
    }

    public ArrayList<ArrayList<String>> getData(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            ArrayList<String> list = new ArrayList<>();
            String line;

            reader.readLine();
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }

            ArrayList<ArrayList<String>> data = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) data.add(new ArrayList<>());

            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < 6; j++) {
                    data.get(i).add(list.get(i).split(",")[j]);
                }
            }

            reader.close();
            return data;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    JTable dbSetup(String db){
        Object[][] data;
        ArrayList<ArrayList<String>> dataArrayList;

        if(db.equals("A")) dataArrayList = database;
        else dataArrayList = issuedDatabase;

        data = new Object[dataArrayList.size()][6];

        for(int i = 0; i < dataArrayList.size(); i++){
            for (int j = 0; j < 6; j++){
                data[i][j] = dataArrayList.get(i).get(j);
            }
        }

        if(db.equals("A")) return new JTable(availableBooksModel = new DefaultTableModel(data, col));
        else return new JTable(issuedBooksModel = new DefaultTableModel(data, col));
    }

    private void saveFiles(String filePath, ArrayList<ArrayList<String>> data) {
        ArrayList<String> line = new ArrayList<>();
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("id,name,author,language,year,genre\n");
            for (ArrayList<String> datum : data) {
                for (int j = 0; j < 6; j++) {
                    line.add(datum.get(j));
                }
                String rowString = String.join(",", line);
                writer.write(rowString + "\n");
                line.clear();
            }
        } catch (IOException e) {
            System.err.println("Error writing to database" + e.getMessage());
        }
    }

    public void sendSaveRequest() {
        saveFiles(databaseFilePath, database);
        saveFiles(issuedDatabaseFilePath, issuedDatabase);
    }
}
