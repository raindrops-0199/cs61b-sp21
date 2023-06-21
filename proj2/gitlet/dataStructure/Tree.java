package gitlet.dataStructure;

import gitlet.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * A Tree class represents a directory
 * <p>
 * There's a use of Iterator Pattern.
 * <p>
 * Implements Iterable interface to make Tree iterable, which is
 * useful when iterate through Tree object to find a file or something.
 *
 * @author jason
 * TODO
 */
public class Tree extends LooseObject implements Iterable<FileD> {
    private final TreeMap<String, FileD> objTreeMap = new TreeMap<>();

    // public Tree(ArrayList<String> fileNames) {}

    public Tree(){
        this.type = ObjectType.TREE;

    }    @Override
    public String computeHash() {
        return Utils.sha1(toString());
    }

    /**
     * Convert contents of objTreeMap to String.
     * Convert in order of file name;
     * @return contents of objTreeMap
     * TODO
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (FileD fd : objTreeMap.values()) {
            stringBuilder.append(fd.toString());
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    /**
     * Add blob or tree to objTreeMap.
     * @param filePath path of file/tree(directory)
     * @param fileHash hash of file/tree
     * @throws IOException IOException
     */
    public void addObj(String filePath, String fileHash) throws IOException {
        FileD fd = new FileD(filePath, fileHash);
        objTreeMap.put(fd.getFileName(), fd);
    }

    /**
     * Add blob to objTreeMap
     * @param blob a Blob object
     * @throws IOException IOException
     */
    public void addObj(Blob blob) throws IOException {
        FileD fd = new FileD(blob);
        objTreeMap.put(fd.getFileName(), fd);
    }

    public Iterator<FileD> iterator() {
        return new TreeIterator();
    }

    private class TreeIterator implements Iterator<FileD> {
        private final Iterator<String> nameIter;
        public TreeIterator() {
            nameIter = objTreeMap.keySet().iterator();
        }
        @Override
        public boolean hasNext() {
            return nameIter.hasNext();
        }
        @Override
        public FileD next() {
            String name = nameIter.next();
            return objTreeMap.get(name);
        }
    }
}