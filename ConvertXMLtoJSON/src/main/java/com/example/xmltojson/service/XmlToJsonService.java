
package com.example.xmltojson.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

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

