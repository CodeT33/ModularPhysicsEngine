import java.util.*;

class UnitConvertingCall {
    double value;
    String startUnit;
    String endUnit;

    @Override
    public String toString() {
        return "UnitConvertingCall{" + "value=" + value + ", startUnit=" + startUnit + ", endUnit=" + endUnit + "}";
    }
}

class VariableCall {
    String varName;
    double value;
    String valueVarName = "empty";

    @Override
    public String toString() {
        return "VariableCall{" + "varName='" + varName + "\'" + ", value=" + value + ", valueVarName=" + valueVarName + "}";
    }
}

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


