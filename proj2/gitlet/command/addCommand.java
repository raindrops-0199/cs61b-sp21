package gitlet.command;

import gitlet.dataStructure.Index;
import gitlet.dataStructure.LooseObject;
import gitlet.dataStructure.ObjectFactory;
import gitlet.dataStructure.ObjectType;
import gitlet.utils.Logger;

import java.io.IOException;

public class addCommand implements Command{
    private String fileName;
    @Override
    public void setParameter(String[] args) {
        fileName = args[0];
    }

    @Override
    public void execute() throws IOException {
        String[] blobArg = {fileName};
        LooseObject blob = ObjectFactory.create(ObjectType.BLOB, blobArg);
        blob.writeLooseObject();
        Index.add(fileName, blob.getHash());

        // log
        Logger logger = Logger.getInstance();
        logger.writeLog("add " + fileName);
    }
}
