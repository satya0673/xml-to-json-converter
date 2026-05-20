//
//package com.example.xmltojson;
//
//import com.example.xmltojson.service.XmlToJsonService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.core.io.FileSystemResourceLoader;
//
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.Scanner;
//
//@SpringBootApplication
//public class XmlToJsonApplication implements CommandLineRunner {
//
//    @Autowired
//    private XmlToJsonService xmlToJsonService;
//
//    public static void main(String[] args) {
//        SpringApplication.run(XmlToJsonApplication.class, args);
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("🔹 Enter the path of the XML file:");
//        String xmlFilePath = scanner.nextLine();
//
//        // Step 1: Read and display all values from the XML file
//        if (!Files.exists(Paths.get(xmlFilePath))) {
//            System.out.println("❌ Error: File not found. Please check the path and try again.");
//            return;
//        }
//
//        System.out.println("\n📜 XML File Content:");
//         
//        String xmlContent = new String(Files.readAllBytes(Paths.get(xmlFilePath)));
//        
//        System.out.println(xmlContent);
//        String modifiedXmlContent = xmlContent.replaceFirst("<Salary>.*?</Salary>", "");
//        
//       
//
//        // Step 2: Convert XML to JSON
//        
//        String jsonFilePath = xmlToJsonService.convertXmlToJson(xmlFilePath);
//        System.out.println("\n✅ JSON file created successfully at: " + jsonFilePath);
//    }
//}

//
package com.example.xmltojson;

import com.example.xmltojson.service.XmlToJsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.Map;

@SpringBootApplication
public class XmlToJsonApplication implements CommandLineRunner {

    @Autowired
    private XmlToJsonService xmlToJsonService;

    public static void main(String[] args) {
        SpringApplication.run(XmlToJsonApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("🔹 Enter the path of the XML file:");
        String xmlFilePath = scanner.nextLine();

        // Check if the file exists
        if (!Files.exists(Paths.get(xmlFilePath))) {
            System.out.println("❌ Error: File not found. Please check the path and try again.");
            return;
        }

        // Extract employee details without salary
        List<Map<String, String>> employees = xmlToJsonService.extractEmployeesWithoutSalary(xmlFilePath);

        // Print extracted employee details
        System.out.println("\n👨‍💼 Employee Details (Without Salary):");
        for (Map<String, String> employee : employees) {
            System.out.println(employee);
        }
    }
}

