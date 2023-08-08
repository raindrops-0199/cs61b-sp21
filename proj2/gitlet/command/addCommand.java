package gitlet.command;

import gitlet.Config;
import gitlet.dataStructure.*;
import gitlet.utils.Utils;

import java.io.File;
import java.io.IOException;

public class addCommand implements Command{
    private String fileName;
    @Override
    public void setParameter(String[] args) {
        fileName = args[0];
    }

    @Override
    public void execute() throws IOException {
        // make a blob of the file
        File file = Utils.join(Config.CWD, fileName);
        if (!file.exists()) {
            System.err.println("File does not exist.");
            System.exit(0);
        }
        String[] args = {fileName};
        LooseObject stageFile = ObjectFactory.create(ObjectType.BLOB, args);
        stageFile.writeLooseObject();

        // add the blob to stage
        Stage stage = Index.getInstance();
        // if is identical to current commit , this will change nothing. If if different from old version,
        // this will override old version.
        stage.add(fileName, stageFile.getHash());

    }
}
