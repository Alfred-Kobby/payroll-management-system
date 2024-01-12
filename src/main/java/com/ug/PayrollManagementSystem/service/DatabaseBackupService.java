package com.ug.PayrollManagementSystem.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Slf4j
@Service
public class DatabaseBackupService {

    // Backup settings
    private static final String BACKUP_FILE_PATH = "/Users/aternor/Documents/Personal/payroll-management-system/backup.sql";

    @Transactional
    public String backupDatabase() {
        try {
            // Build the mysqldump command
            String[] command = {"mysqldump", "-h", "localhost", "-P", "3306", "-u", "root", "payroll"};

            // Execute the command and capture the output
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            // Save the output to a file
            try (PrintWriter writer = new PrintWriter(new FileWriter(BACKUP_FILE_PATH));
                 BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {

                // Read from the process's input stream and write to the file
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.println(line);
                }
            } catch (IOException e) {
                log.error("An exception occurred: {}", e);
            }

            // Wait for the process to finish
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                log.info("Database backup completed successfully. Backup saved to: {}", BACKUP_FILE_PATH);
                return "redirect:/employees?success";
            } else {
                log.info("Error: Database backup failed.");
                return "redirect:/employees?error";
            }

        } catch (IOException | InterruptedException e) {
            log.error("An exception occurred: {}", e);
            return "redirect:/employees?error";
        }
    }
}