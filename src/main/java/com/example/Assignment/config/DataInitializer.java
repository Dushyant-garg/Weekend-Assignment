package com.example.Assignment.config;

import com.example.Assignment.entity.Project;
import com.example.Assignment.entity.Task;
import com.example.Assignment.entity.Users;
import com.example.Assignment.repository.ProjectRepository;
import com.example.Assignment.repository.TaskRepository;
import com.example.Assignment.repository.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadData(UsersRepository usersRepository,
                                      ProjectRepository projectRepository,
                                      TaskRepository taskRepository) {
        return args -> {
            // Create Users
            Users manager = new Users();
            manager.setUsername("manager1");
            manager.setPassword("pass123");

            Users user1 = new Users();
            user1.setUsername("dev1");
            user1.setPassword("pass123");

            Users user2 = new Users();
            user2.setUsername("dev2");
            user2.setPassword("pass123");

            Users user3 = new Users();
            user3.setUsername("qa1");
            user3.setPassword("pass123");

            usersRepository.saveAll(Arrays.asList(manager, user1, user2, user3));

            // Create Project
            Project project = new Project();
            project.setName("AI Chatbot");
            project.setScope("Develop an AI-based customer service chatbot");
            project.setDeadline(LocalDate.of(2025, 12, 31));
            project.setManager(manager);
            project.setTeam(List.of(user1, user2, user3));

            projectRepository.save(project);

            // Create Tasks
            Task task1 = new Task();
            task1.setName("Design Conversation Flow");
            task1.setDescription("Design the complete chatbot dialog flow.");
            task1.setDeadline(LocalDate.of(2025, 10, 01));
            task1.setProject(project);
            task1.setManager(manager);
            task1.setAssignedTo(List.of(user1));

            Task task2 = new Task();
            task2.setName("Implement NLP Integration");
            task2.setDescription("Integrate the chatbot with NLP engine (e.g., Dialogflow).");
            task2.setDeadline(LocalDate.of(2025, 10, 15));
            task2.setProject(project);
            task2.setManager(manager);
            task2.setAssignedTo(List.of(user2));

            Task task3 = new Task();
            task3.setName("Test Conversation Scenarios");
            task3.setDescription("Test various conversation flows and edge cases.");
            task3.setDeadline(LocalDate.of(2025, 11, 05));
            task3.setProject(project);
            task3.setManager(manager);
            task3.setAssignedTo(List.of(user3));

            taskRepository.saveAll(List.of(task1, task2, task3));
        };
    }
}


//package com.example.Assignment.config;
//
//import com.example.Assignment.entity.Project;
//import com.example.Assignment.entity.Users;
//import com.example.Assignment.repository.ProjectRepository;
//import com.example.Assignment.repository.UsersRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//
//@Configuration
//public class DataInitializer {
//
//    @Bean
//    public CommandLineRunner loadData(UsersRepository usersRepository, ProjectRepository projectRepository) {
//        return args -> {
//            // Create Users
//            Users manager = new Users();
//            manager.setUsername("manager1");
//            manager.setPassword("pass123");
//
//            Users user1 = new Users();
//            user1.setUsername("dev1");
//            user1.setPassword("pass123");
//
//            Users user2 = new Users();
//            user2.setUsername("dev2");
//            user2.setPassword("pass123");
//
//            Users user3 = new Users();
//            user3.setUsername("qa1");
//            user3.setPassword("pass123");
//
//            usersRepository.saveAll(Arrays.asList(manager, user1, user2, user3));
//
//            // Create Project
//            Project project = new Project();
//            project.setName("AI Chatbot");
//            project.setScope("Develop an AI-based customer service chatbot");
//            project.setDeadline(LocalDate.of(2025, 12, 31));
//            project.setManager(manager);
//            project.setTeam(List.of(user1, user2, user3));
//
//            projectRepository.save(project);
//        };
//    }
//}
//
