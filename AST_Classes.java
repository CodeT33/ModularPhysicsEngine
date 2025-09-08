import java.util.*;

class ModuleCall {
    String moduleName;
    Map<String, Object> inputs = new HashMap<>();
    List<String> outputs = new ArrayList<>();

    @Override
    public String toString() {
        return "ModuleCall{" + "moduleName='" + moduleName + "\'" + ", inputs="  + inputs + ", outputs=" + outputs + "}";
    }
}

class OutputCommand {
    List<String> displayVariables = new ArrayList<>();
    List<String> outputs = new ArrayList<>();
    String display;

    @Override
    public String toString() {
        return "OutputModuleCall{" + "outputs=" + outputs + ", display='" + display + "'" + "}";
    }
}
