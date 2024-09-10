package com.coderscampus.assignment4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
//
//public class StudentDateParser {
//
//	public static void main(String[] args) {
//
//		try {
//			BufferedReader br = new BufferedReader(new FileReader("student-master-list.csv"));
//			int linecount = 0;
//			br.readLine();
//			while (br.readLine() != null) {
//				;
//				linecount++;
//			}
//			br.close();
//			User[] users = new User[linecount];
//			BufferedReader br2 = new BufferedReader(new FileReader("student-master-list.csv"));
//			String line = null;
//			br2.readLine();
//			int index = 0;
//			while ((line = br2.readLine()) != null) {
//				String[] userData = line.split(",");
//				if (userData.length == 4) {
//					String id = userData[0];
//					String name = userData[1];
//					String course = userData[2];
//					String grade = userData[3];
//					users[index] = new User(id, name, course, grade);
//					index++;
//				}
//			}
//			br2.close();
//			Arrays.sort(users, new Comparator<User>() {
//
//				@Override
//				public int compare(User user1, User user2) {
//					int courseComparison = user1.getCourse().compareTo(user2.getCourse());
//					if (courseComparison == 0) {
//						int grade1 = Integer.parseInt(user1.getGrade());
//						int grade2 = Integer.parseInt(user2.getGrade());
//						return Integer.compare(grade2, grade1);
//					}
//					// return user1.getCourse().compareTo(user2.getCourse());
//					return courseComparison;
//				}
//			});
//			try {
//				BufferedWriter course1Writer = new BufferedWriter(new FileWriter("course1.csv"));
//				BufferedWriter course2Writer = new BufferedWriter(new FileWriter("course2.csv"));
//				BufferedWriter course3Writer = new BufferedWriter(new FileWriter("course3.csv"));
//				String header = "Student ID,Student Name,Course,Grade\n";
//				course1Writer.write(header);
//				course2Writer.write(header);
//				course3Writer.write(header);
//				for (User user : users) {
//					String userData = user.getId() + "," + user.getName() + "," + user.getCourse() + ","
//							+ user.getGrade() + "\n";
//					if (user.getCourse().startsWith("APMTH")) {
//						course1Writer.write(userData);
//					} else if (user.getCourse().startsWith("COMPSCI")) {
//						course2Writer.write(userData);
//					} else if (user.getCourse().startsWith("STAT")) {
//						course3Writer.write(userData);
//					}
//				}
//				course1Writer.close();
//				course2Writer.close();
//				course3Writer.close();
//				for (User user : users) {
//				    System.out.println(user.getCourse() + " - " + user.getGrade()); // Debugging output
//				}
//			} finally {
//				
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//}

//import java.io.*;
//import java.util.Arrays;
//import java.util.Comparator;

public class StudentDateParser {

	public static void main(String[] args) {

		try {
			BufferedReader br = new BufferedReader(new FileReader("student-master-list.csv"));
			int linecount = 0;
			br.readLine(); // Skip header
			while (br.readLine() != null) {
				linecount++;
			}
			br.close();

			User[] users = new User[linecount];
			BufferedReader br2 = new BufferedReader(new FileReader("student-master-list.csv"));
			br2.readLine(); // Skip header again
			String line = null;
			int index = 0;

			// Fill users array
			while ((line = br2.readLine()) != null) {
				String[] userData = line.split(",");
				if (userData.length == 4) {
					String id = userData[0];
					String name = userData[1];
					String course = userData[2];
					String grade = userData[3];
					users[index] = new User(id, name, course, grade);
					index++;
				}
			}
			br2.close();

			// Create 3 arrays for each course
			User[] course1 = new User[linecount];
			User[] course2 = new User[linecount];
			User[] course3 = new User[linecount];

			// Indexes to keep track of how many students are added to each course array
			int course1Index = 0;
			int course2Index = 0;
			int course3Index = 0;

			// Separate users by course
			for (User user : users) {
				if (user.getCourse().startsWith("APMTH")) {
					course1[course1Index++] = user;
				} else if (user.getCourse().startsWith("COMPSCI")) {
					course2[course2Index++] = user;
				} else if (user.getCourse().startsWith("STAT")) {
					course3[course3Index++] = user;
				}
			}
			// this part I got help from Chatgpt
			course1 = Arrays.copyOf(course1, course1Index);
			course2 = Arrays.copyOf(course2, course2Index);
			course3 = Arrays.copyOf(course3, course3Index);

			Comparator<User> gradeComparator = (u1, u2) -> Integer.compare(Integer.parseInt(u2.getGrade()),
					Integer.parseInt(u1.getGrade()));
			Arrays.sort(course1, gradeComparator);
			Arrays.sort(course2, gradeComparator);
			Arrays.sort(course3, gradeComparator);

			writeToFile("course1.csv", course1);
			writeToFile("course2.csv", course2);
			writeToFile("course3.csv", course3);
			// end of help from Chatgpt
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void writeToFile(String fileName, User[] users) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		String header = "Student ID,Student Name,Course,Grade\n";
		writer.write(header);
		for (User user : users) {
			String userData = user.getId() + "," + user.getName() + "," + user.getCourse() + "," + user.getGrade()
					+ "\n";
			writer.write(userData);
		}
		writer.close();
	}
}