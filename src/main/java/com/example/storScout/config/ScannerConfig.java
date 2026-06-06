package com.example.storScout.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class ScannerConfig {

    @Bean
    public Scanner getCommandLineScanner() {
        return new Scanner(System.in);
    }
}
