import PhysicModules.PM_Blueprint;

import java.util.*;

class ModuleInstance {
    PM_Blueprint module;
    List<String> outputKeys;

    ModuleInstance(PM_Blueprint module, List<String> outputKeys) {
        this.module = module;
        this.outputKeys = outputKeys;
    }
}

class Engine {
    private final List<ModuleInstance> modules = new ArrayList<>();

    public void addModule(PM_Blueprint module, List<String> outputKeys) {
        modules.add(new ModuleInstance(module, outputKeys));
    }

    public void execute() {
        for (ModuleInstance mi : modules) {
            mi.module.compute(mi.outputKeys);
        }
        modules.clear();
    }
}