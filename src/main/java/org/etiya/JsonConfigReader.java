package org.etiya;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

    public class JsonConfigReader {
        private JsonNode config;

        public JsonConfigReader(String filePath) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                config = objectMapper.readTree(new File(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public String getProperty(String key) {
            return config.path(key).asText();
        }
    }


