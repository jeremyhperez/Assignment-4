package com.coderscampus.assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class StudentDateParser {

	public static void main(String[] args) {

		try {
			BufferedReader br = new BufferedReader(new FileReader("student-master-list.csv"));
			int linecount = 0;
			while (br.readLine() != null) {
				;
				linecount++;
			}
			User[] users = new User[linecount];
			br.close();
			BufferedReader br2 = new BufferedReader(new FileReader("student-master-list.csv"));
			String line = null;
			int index = 0;
			while ((line = br2.readLine()) != null) {
				String[] userData = line.split(",");
				if (userData.length == 4) {
					String id = userData[0];
					String name = userData[1];
					String course = userData[2];
					String grade = userData[3];
					users[index] = new User(id, name, course, grade);
					index++;
				} else {
					System.out.println("Skipping invalid line: " + line);
				}
			}
			br2.close();
			Arrays.sort(users, new Comparator<User>() {

				@Override
				public int compare(User user1, User user2) {
					return user1.getCourse().compareTo(user2.getCourse());
				}
			});
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
