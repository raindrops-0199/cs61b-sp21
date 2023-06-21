# Gitlet Design Document

# 1 .gitlet directory structure 

```
.
├── HEAD // current HEAD pointer "ref: refs/heads/main" since there            // won't have detached HEAD state,the content won't be SHA-1
├── index // Staging Area
├── logs // operation log (don't have to implement at this proj)
│   ├── HEAD
│   └── refs
│       └── heads
│           ├── dev
│           └── main
├── objects // loose objects
│   ├── a5  // first two num of SHA-1
│   │   ├── 3f92b3b43a820039d923956c7fa1ed097ecd1c // rest SHA-1
│   │   └── 57d1d6a6a241320ba181a957f74ef04c330d42
│   ├── a8
│   │   └── b089d838d9870ccee7453194e3de7626234b8b
└── refs
    └── heads
        ├── dev // hash of head
        └── main
```

# 2 Classes and Data Structures

## 2.1 Code structure

```
.
├── Main.java
├── Makefile
├── Repository.java  // which interact with main and commands
├── command   // implementation of commands
│   ├── Command.java
│   ├── initCommand.java
|   └── ...Command.java
├── dataStructure  // implementation of data structures
│   ├── Blob.java
│   ├── Commit.java
│   ├── FileD.java
│   ├── Index.java
│   ├── LooseObject.java
│   ├── ObjectFactory.java
│   └── Tree.java
├── sentinel
└── utils  // util functions
    ├── DumpObj.java
    ├── Dumpable.java
    ├── GitletException.java
    └── Utils.java
```

## 2.2 Classes

### LooseObject

abstract class. When deserialized, object should be deserialized to LooseObject and can directly use related method or check type.

```java
public enum ObjectType implements Serializable {
    COMMIT("commit"), BLOB("blob"), TREE("tree");

    private final String represent;
    
    ObjectType(String name) {}

    public String getRepresent() {}
}
```



```java
public abstract class LooseObject implements Serializable{
    protected ObjectType type;
    protected String hash;
    
    public ObjectType getType() {}
    public String getHash() {}
    
    public void writeLooseObject()；
    public abstract String computeHash();
    public abstract String toString();
    
}
```

### Commit

```java
public class Commit extends LooseObject {
	private String treeHash;
    private final ArrayList<String> parentHash;
    private String date;
    private String message;
    
    public Commit(String date) {}
    
    public void setMessage(String message) {}
    public void setTreeHash(String treeHash) {}
    public void addParentHash(String hash) {}
    
    @Override
    public String computeHash() {}    
    @Override
    public String toString() {}
    
    public String toLog() {}
    
    private String lines2String(List<String> lines)
}
```

### Blob

```java
public class Blob extends LooseObject {
    private byte[] content;
    //private String content;
    private final String path;
    
    public Blob(String path) {}
    
    @Override
    public String computeHash() {}    
    @Override
    public String toString() {}
    
    public String getPath() {}
}
```

### Tree 

In this project tree object isn't necessary, as there whon't deal with subdirectories. But I still implement tree object to make it more general and similar to real git.

```java
public class FileD implements Serializable{
    private String mode;
    private String type;
    private String fileHash;
    private String fileName;
    private String path;
    
    public FileD(String path, String fileHash) {}
    public FileD(Blob blob) {}    
    
    public String getDetail() {}
    public String getMode() {}
    public String getTpype() {}
    public String getFileHash() {}
    public String getFileName() {}
    public String toString() {}
    
    private String computeMode(Path filePath) {}
    private static int convertPermissionsToMode {}
}
```

```java
public class Tree extends LooseObject {
    private TreeMap<String, FileD> objTreeMap;
    
    public Tree(){}
    // public Tree(ArrayList<String> fileNames) {}
    
    @Override
    public String computeHash() {}    
    @Override
    public String toString() {}
    
    public void addObj(String filePath, String fileHash) {}
    public void addObj(Blob blob) {}
}
```

### Stage

```java
// reprensent index file in .gitlet directory
public class Index implements Stage{
    // stage content
    private static Tree stage;
    private static final String path = ".gitlet/index";
    
    private static void readIndex() {}    
    private static void writeIndex() {}
    
    public static Tree getAddition() {}    
    public static Tree getRemoval() {}    
    public static Tree getModified() {}    
    public static Tree getUntracked() {}
    
    // add a file to index. A file may be in a new folder, which 
    // needs to make a new Tree object.
    public static void add() {}
    // remove a file from index.
    public static void remove() {}
}
```

### Repository

```java
public class Repository {
    
    public static void <command_name>() {}
}
```

#  3 Commands Implementation

## init

create .gitlet directory and create all necessary files and directories in .gitlet directory. Default branch is master.

## add

if already in stage area: 

- if hash is same: do nothing
- not same: overwrite previous hash and make a new blob

else:

- add hash to stage area and make a new blob.

remember: two different file name may have same content making them have same hash. In this case, they refer to one blob. So when making a blob, if the same blob already exists, don't have to make a new one.

## commit

Make a new commit with message and author, date, etc. Save the index Tree into objects and get its hash.

Update HEAD hash and branch hash

## rm

- file not tracked in current commit and staged: remove it from staging area.
- file tracked: remove it from directory and staging area.

## log

from HEAD-branch hash, iterate over all commit to first commit, following the first parent commit links.

## global-log

travel through all objects, if is commit, print it out.

## find

travel through all objects, if is commit and has same commit message, print it out.

## status

1. Get the files of stage by index file, and compare with the last committed tree
   - More files: stage for addition
   - Less files: stage for removal
2. Compare with the working directory again
   - Files in stage but not staged: modified but not staged
   - Files not in stage: untracked files

## checkout

1. Find HEAD-branch commit and find tree then find the needed file. Overwrite the current one. 

2. Find commit with given id then get the tree then find the file. Overwrite the current one.

3. Find the branch hash and find all files in that commit. Take them to working derectory and overwrite the versions of the files that are already there if they exist. Delete files that are tracked in the current branch but are not present in the checked-out branch. Untracked files which won'e be overwritten remains here. Staged for addition or removal are cleaned.

   Change HEAD to new branch.

## branch

Create a new branch thich point to current commit. Add it to refs/heads folder. Do not switch branch.

## rm-branch

Remove branch. Don't change any commits.

## reset

Checkout to that commit and moves the current branch’s head to that commit node.

## merge

split point: latest *common ancestor*

How to find: using a BFS to find all ancestors of current branch(commit), puts them into a set. Then starts another BFS from another branch node, and determines if the visited node is in the set. If it is, then it's the common ancestor. The first one found is the latest.

- if given branch is in the set: do nothing
- if latest common ancestor is current branch: fast forward(checkout)
- else: merge

# 4 Persistence

# 5 Design Patterns

Try to use some design patterns in this project

## 5.1 Simple Factory Pattern

Use a factoey to create objects. Hides the details from clint.

### ObjectFactory

A factory that create LooseObjects. Can be used by Repository class.

```java
public class ObjectFactory {
    public static Commit createCommit(...) {}
    
    public static Blob createBlob(String path) {} 
    
    public static Tree createTree(List<String> fileNames) {}
}
```

## 5.2 Iterator Pattern

Make Tree object iterable so the clint can easily iterate over it to find files or so.

Add Iterable<> and add a private Iterator class to Tree class.

```java
public class Tree extends LooseObject implements Iterable<FileD> {
    // new things
    public Iterator<FileD> iterator() {
        return new TreeIterator();
    }
    
    private class TreeIterator implements Iterator<FileD> {
        private Iterator<String> nameIter;
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
```

## 5.3 Command Pattern

Implement each command as a command class that implements Command interface. This can make it easy for Repository class to execute a command. This can also make adding command easier.

### command interface

```java
public interface Command {
    void setParameter(String[] restArgs);
    void execute();
}
```

### ConcreteCommand

Then implement every command as a class that implements Command interface

for example:

```java
public class initCommand implements Command {
    String[] args;
    @Override
    public void setParameter(String[] restArgs) {}
    @Override
    public void execute() {}
}
```

### Invoker

In `Repository`class add these thing:

```java
public class Repository {
    Command command;
    
    public void setCommand(String command, String[] args) {
        ... //make a command
        this.command = newCommand;
        this.command.setParameter(args);
    }
    
    // or setCommand(Command command, String[] args) if using enum
    
    public void invoke() {
        command.execute();
    }
}
```

Thinking about using an `enum` in main to represent `String command` to `command class` so we don't have to use a lot of `if else` in the code.

## 5.4 Singleton Pattern

### Logger

```java
public class Logger {
    private String path = ".gitlet/logs";
    private String content = "";
    
    private Logger() {}
    
    private static class LoggerHolder {
        private static final Logger instance = new Logger();
    }
    
    public static Logger getInstance() {
        return LoggerHolder.instance;
    }
    
    public void writeLog() {}
    public void write2File() {}
    public void readFromFile() {}
}
```





> Author: jason
>
> Last edited at: 2023.6.18
