package gitlet;

import java.io.File;

import static gitlet.utils.Utils.*;

public class Config {
    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));

    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");

    /** the HEAD file in .gitlet directory. */
    public static final File HEAD_FILE = join(GITLET_DIR, "HEAD");

    /** refs directory. */
    public static final File REFS_DIR = join(GITLET_DIR, "refs");

    /** heads directory in refs directory. */
    public static final File HEADS_DIR = join(REFS_DIR, "heads");

    /** logs file in .gitlet directory. */
    public static final File LOG_FILE = join(GITLET_DIR, "logs");

    /** objects directory in .gitlet directory. */
    public static final File OBJ_DIR = join(GITLET_DIR, "objects");
}
