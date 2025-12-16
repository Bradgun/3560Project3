package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AppModel {

    //used to notify the UI when model data changes
    private final PropertyChangeSupport propSupport = new PropertyChangeSupport(this);

    private String outputText = "";
    private String statusText = "Ready";
    private boolean busy = false;
    private String errorText = null;

    //lets the UI listen for model changes
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propSupport.addPropertyChangeListener(listener);
    }

    //getters
    public String getOutputText() {
        return outputText;
    }

    public String getStatusText() {
        return statusText;
    }

    public boolean isBusy() {
        return busy;
    }

    public String getErrorText() {
        return errorText;
    }

    //setters
    public void setOutputText(String updatedText) {
        String initialText = this.outputText;
        this.outputText = updatedText;
        propSupport.firePropertyChange("outputText", initialText, updatedText);
    }

    public void setStatusText(String updatedStatusText) {
        String initialStatusText = this.statusText;
        this.statusText = updatedStatusText;
        propSupport.firePropertyChange("statusText", initialStatusText, updatedStatusText);
    }

    public void setBusy(boolean updatedBusy) {
        boolean initialBusy = this.busy;
        this.busy = updatedBusy;
        propSupport.firePropertyChange("busy", initialBusy, updatedBusy);
    }

    public void setErrorText(String updatedErrorText) {
        String initialErrorText = this.errorText;
        this.errorText = updatedErrorText;
        propSupport.firePropertyChange("errorText", initialErrorText, updatedErrorText);
    }
}
