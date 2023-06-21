package gitlet.dataStructure;

import java.io.Serializable;

/**
 * An enum to represent three kind of LooseObjects
 */
public enum ObjectType implements Serializable {
    COMMIT("commit"), BLOB("blob"), TREE("tree");

    private final String represent;

    ObjectType(String name) {
        this.represent = name;
    }

    public String getRepresent() {
        return represent;
    }
}
