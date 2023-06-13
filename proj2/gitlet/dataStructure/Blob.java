package gitlet.dataStructure;

/** A Blob represents a regular file created by user.
 * @author jason
 */
public class Blob extends LooseObject{
    private String type;
    private byte[] content;
    //private String content;

    public Blob(String path) {}

    @Override
    public void writeLooseObject() {}

    @Override
    public String computeHash() {
        return "";
    }

    @Override
    public String toString() {
        return "";
    }

}
