package model.repository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.domain.SaveWritingSession;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonSessionRepository implements SessionRepository{
    private final File file;
    private final Gson gson = new Gson();

    //different instances can save to different files
    public JsonSessionRepository(String filename) {
        this.file = new File(filename);
    }

    @Override
    public void save(SaveWritingSession session) throws Exception {

        //load sessions to avoid overwriting them
        List<SaveWritingSession> sessions = loadSessions();
        sessions.add(session);

        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(sessions, writer);
        }
    }

    @Override
    public List<SaveWritingSession> loadSessions() throws Exception {

        //file doesn't exist yet
        if (!file.exists()) {
            return new ArrayList<>();
        }

        if (file.length() == 0) {
            return new ArrayList<>();
        }

        //Tells Gson the type we are loading in
        Type listType = new TypeToken<List<SaveWritingSession>>() {}.getType();

        try (FileReader reader = new FileReader(file)) {
            List<SaveWritingSession> sessions = gson.fromJson(reader, listType);

            //return empty list if there are no sessions
            if (sessions == null) {
                return new ArrayList<>();
            }
            return sessions;
        }
    }

}
