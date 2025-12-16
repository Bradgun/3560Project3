package model.repository;
import model.domain.SaveWritingSession;
import java.util.List;

public interface SessionRepository {

    //saves the current writing session
    void save(SaveWritingSession session) throws Exception;

    //loads every previous saves
    List<SaveWritingSession> loadSessions() throws Exception;
}
