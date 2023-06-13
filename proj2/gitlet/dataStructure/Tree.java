package gitlet.dataStructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

/** A Tree class represents a directory
 *  There's a use of Iterator Pattern.
 *  Implements Iterable interface to make Tree iterable, which is
 *  useful when iterate through Tree object to find a file or something.
 *
 *  @author jason
 */
public class Tree extends LooseObject implements Iterable<FileD> {
    private String type;
    private TreeMap<String, FileD> objTreeMap;

    public Tree(ArrayList<String> fileNames) {}

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

    public void addObj(String fileName) {}

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