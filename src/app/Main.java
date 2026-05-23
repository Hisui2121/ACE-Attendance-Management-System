package app;

import java.util.ArrayList;

import dao.StudentDAO;
import database.DBInitialize;
import model.Student;

import ui.ConsoleUI;


public class Main {
	public static void main(String[] args) {

        System.out.println("Starting System...");

        // INITIALIZE DATABASE
        DBInitialize.initialize();
        ConsoleUI ui = new ConsoleUI();

        ui.start();
    }
}