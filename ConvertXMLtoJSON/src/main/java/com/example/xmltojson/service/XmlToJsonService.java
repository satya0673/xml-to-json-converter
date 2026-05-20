//
//package com.example.xmltojson.service;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.dataformat.xml.XmlMapper;
//import org.springframework.stereotype.Service;
//
//import java.io.File;
//import java.io.IOException;
//
//@Service
//public class XmlToJsonService {
//
//    private static final String OUTPUT_DIRECTORY = "output/";
//
//    public String convertXmlToJson(String xmlFilePath) {
//        try {
//            // Ensure output directory exists
//            File dir = new File(OUTPUT_DIRECTORY);
//            if (!dir.exists()) {
//                dir.mkdirs();
//            }
//
//            // Read XML file
//            File xFile=new File(xmlFilePath);
//            File xmlFile = new File(xmlFilePath);
//            XmlMapper xmlMapper = new XmlMapper();
//            
//            JsonNode jsonNode = xmlMapper.readTree(xmlFile);
//
//            // Convert to JSON format
//            ObjectMapper jsonMapper = new ObjectMapper();
//            String jsonContent = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
//
//            // Define JSON file path
//            String jsonFileName = xmlFile.getName().replace(".xml", ".json");
//            String jsonFilePath = OUTPUT_DIRECTORY + jsonFileName;
//
//            // Write JSON file
//            jsonMapper.writeValue(new File(jsonFilePath), jsonNode);
//
//            System.out.println("✅ JSON file saved at: " + new File(jsonFilePath).getAbsolutePath());
//            return new File(jsonFilePath).getAbsolutePath(); // Return full path
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "Error converting XML to JSON!";
//        }
//    }
//}
//
package com.example.xmltojson.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class XmlToJsonService {

    public List<Map<String, String>> extractEmployeesWithoutSalary(String xmlFilePath) {
        List<Map<String, String>> employeeList = new ArrayList<>();
        try {
            // Read XML file and parse it
            File xmlFile = new File(xmlFilePath);
            XmlMapper xmlMapper = new XmlMapper();
            JsonNode rootNode = xmlMapper.readTree(xmlFile);

            // Navigate to employee list
            JsonNode employees = rootNode.path("employee");
            if (employees.isArray()) {
                for (JsonNode employeeNode : employees) {
                    Map<String, String> employeeDetails = new LinkedHashMap<>();
                    employeeNode.fields().forEachRemaining(entry -> {
                        if (!entry.getKey().equalsIgnoreCase("salary")) { // Skip salary
                            employeeDetails.put(entry.getKey(), entry.getValue().asText());
                        }
                    });
                    employeeList.add(employeeDetails);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeeList;
    }
}

