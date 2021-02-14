package by.vss.exam.repository.json;

import by.vss.exam.constant.ConstantHolder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.ResourceBundle;

public final class UniversalJsonReaderWriter<T> {
    private final ResourceBundle bundle = ResourceBundle.getBundle(ConstantHolder.BOT_PROPERTIES);
    private final ObjectMapper mapper;
    private final String jsonPath;
    private final Class<T> clazz;

    @JsonDeserialize(as = HashMap.class)
    private HashMap<Long, T> entityMap;

    public UniversalJsonReaderWriter(String jsonPath, Class<T> clazz) {
        mapper = new ObjectMapper();
        entityMap = new HashMap<>();
        this.jsonPath = jsonPath;
        this.clazz = clazz;
    }

    public void addAllToFile(HashMap<Long, T> entity) {
        try {
            final Path path = getPath();
            String string = mapper.writeValueAsString(entity);
            Files.writeString(path, string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<Long, T> getAllFromFile() {
        String jsonString;
        try {
            final Path path = getPath();
            jsonString = Files.readString(path);
            entityMap = mapper.readValue(jsonString, TypeFactory.defaultInstance().constructMapType(HashMap.class, Long.class, clazz));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entityMap;
    }

    private Path getPath() {
        return Path.of(bundle.getString(jsonPath));
    }
}
