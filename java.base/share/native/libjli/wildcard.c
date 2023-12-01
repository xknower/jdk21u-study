/*
 * Class-Path Wildcards
 *
 * The syntax for wildcards is a single asterisk. The class path
 * foo/"*", e.g., loads all jar files in the directory named foo.
 * (This requires careful quotation when used in shell scripts.)
 *
 * Only files whose names end in .jar or .JAR are matched.
 * Files whose names end in .zip, or which have a particular
 * magic number, regardless of filename extension, are not
 * matched.
 *
 * Files are considered regardless of whether or not they are
 * "hidden" in the UNIX sense, i.e., have names beginning with '.'.
 *
 * A wildcard only matches jar files, not class files in the same
 * directory.  If you want to load both class files and jar files from
 * a single directory foo then you can say foo:foo/"*", or foo/"*":foo
 * if you want the jar files to take precedence.
 *
 * Subdirectories are not searched recursively, i.e., foo/"*" only
 * looks for jar files in foo, not in foo/bar, foo/baz, etc.
 *
 * Expansion of wildcards is done early, prior to the invocation of a
 * program's main method, rather than late, during the class-loading
 * process itself.  Each element of the input class path containing a
 * wildcard is replaced by the (possibly empty) sequence of elements
 * generated by enumerating the jar files in the named directory.  If
 * the directory foo contains a.jar, b.jar, and c.jar,
 * e.g., then the class path foo/"*" is expanded into
 * foo/a.jar:foo/b.jar:foo/c.jar, and that string would be the value
 * of the system property java.class.path.
 *
 * The order in which the jar files in a directory are enumerated in
 * the expanded class path is not specified and may vary from platform
 * to platform and even from moment to moment on the same machine.  A
 * well-constructed application should not depend upon any particular
 * order.  If a specific order is required then the jar files can be
 * enumerated explicitly in the class path.
 *
 * The CLASSPATH environment variable is not treated any differently
 * from the -classpath (equiv. -cp) command-line option,
 * i.e. wildcards are honored in all these cases.
 *
 * Class-path wildcards are not honored in the Class-Path jar-manifest
 * header.
 *
 * Class-path wildcards are honored not only by the Java launcher but
 * also by most other command-line tools that accept class paths, and
 * in particular by javac and javadoc.
 *
 * Class-path wildcards are not honored in any other kind of path, and
 * especially not in the bootstrap class path, which is a mere
 * artifact of our implementation and not something that developers
 * should use.
 *
 * Classpath wildcards are only expanded in the Java launcher code,
 * supporting the use of wildcards on the command line and in the
 * CLASSPATH environment variable.  We do not support the use of
 * wildcards by applications that embed the JVM.
 */

#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include "java.h"       /* Strictly for PATH_SEPARATOR/FILE_SEPARATOR */
#include "jli_util.h"

#ifdef _WIN32
#include <windows.h>
#else /* Unix */
#include <unistd.h>
#include <dirent.h>
#endif /* Unix */

#if defined(_AIX)
  #define DIR DIR64
  #define dirent dirent64
  #define opendir opendir64
  #define readdir readdir64
  #define closedir closedir64
#endif

static int
exists(const char* filename)
{
#ifdef _WIN32
    return _access(filename, 0) == 0;
#else
    return access(filename, F_OK) == 0;
#endif
}

#define NEW_(TYPE) ((TYPE) JLI_MemAlloc(sizeof(struct TYPE##_)))

/*
 * Wildcard directory iteration.
 * WildcardIterator_for(wildcard) returns an iterator.
 * Each call to that iterator's next() method returns the basename
 * of an entry in the wildcard's directory.  The basename's memory
 * belongs to the iterator.  The caller is responsible for prepending
 * the directory name and file separator, if necessary.
 * When done with the iterator, call the close method to clean up.
 */
typedef struct WildcardIterator_* WildcardIterator;

#ifdef _WIN32
struct WildcardIterator_
{
    HANDLE handle;
    char *firstFile; /* Stupid FindFirstFile...FindNextFile */
};
// since this is used repeatedly we keep it here.
static WIN32_FIND_DATA find_data;
static WildcardIterator
WildcardIterator_for(const char *wildcard)
{
    WildcardIterator it = NEW_(WildcardIterator);
    HANDLE handle = FindFirstFile(wildcard, &find_data);
    if (handle == INVALID_HANDLE_VALUE) {
        JLI_MemFree(it);
        return NULL;
    }
    it->handle = handle;
    it->firstFile = find_data.cFileName;
    return it;
}

static char *
WildcardIterator_next(WildcardIterator it)
{
    if (it->firstFile != NULL) {
        char *firstFile = it->firstFile;
        it->firstFile = NULL;
        return firstFile;
    }
    return FindNextFile(it->handle, &find_data)
        ? find_data.cFileName : NULL;
}

static void
WildcardIterator_close(WildcardIterator it)
{
    if (it) {
        FindClose(it->handle);
        JLI_MemFree(it->firstFile);
        JLI_MemFree(it);
    }
}

#else /* Unix */
struct WildcardIterator_
{
    DIR *dir;
};

static WildcardIterator
WildcardIterator_for(const char *wildcard)
{
    DIR *dir;
    int wildlen = JLI_StrLen(wildcard);
    if (wildlen < 2) {
        dir = opendir(".");
    } else {
        char *dirname = JLI_StringDup(wildcard);
        dirname[wildlen - 1] = '\0';
        dir = opendir(dirname);
        JLI_MemFree(dirname);
    }
    if (dir == NULL)
        return NULL;
    else {
        WildcardIterator it = NEW_(WildcardIterator);
        it->dir = dir;
        return it;
    }
}

static char *
WildcardIterator_next(WildcardIterator it)
{
    struct dirent* dirp = readdir(it->dir);
    return dirp ? dirp->d_name : NULL;
}

static void
WildcardIterator_close(WildcardIterator it)
{
    if (it) {
        closedir(it->dir);
        JLI_MemFree(it);
    }
}
#endif /* Unix */

static int
equal(const char *s1, const char *s2)
{
    return JLI_StrCmp(s1, s2) == 0;
}

static int
isJarFileName(const char *filename)
{
    int len = (int)JLI_StrLen(filename);
    return (len >= 4) &&
        (filename[len - 4] == '.') &&
        (equal(filename + len - 3, "jar") ||
         equal(filename + len - 3, "JAR")) &&
        /* Paranoia: Maybe filename is "DIR:foo.jar" */
        (JLI_StrChr(filename, PATH_SEPARATOR) == NULL);
}

static char *
wildcardConcat(const char *wildcard, const char *basename)
{
    int wildlen = (int)JLI_StrLen(wildcard);
    int baselen = (int)JLI_StrLen(basename);
    char *filename = (char *) JLI_MemAlloc(wildlen + baselen);
    /* Replace the trailing '*' with basename */
    memcpy(filename, wildcard, wildlen-1);
    memcpy(filename+wildlen-1, basename, baselen+1);
    return filename;
}

static JLI_List
wildcardFileList(const char *wildcard)
{
    const char *basename;
    JLI_List fl = JLI_List_new(16);
    WildcardIterator it = WildcardIterator_for(wildcard);

    if (it == NULL)
    {
        JLI_List_free(fl);
        return NULL;
    }

    while ((basename = WildcardIterator_next(it)) != NULL)
        if (isJarFileName(basename))
            JLI_List_add(fl, wildcardConcat(wildcard, basename));
    WildcardIterator_close(it);
    return fl;
}

static int
isWildcard(const char *filename)
{
    int len = (int)JLI_StrLen(filename);
    return (len > 0) &&
        (filename[len - 1] == '*') &&
        (len == 1 || IS_FILE_SEPARATOR(filename[len - 2])) &&
        (! exists(filename));
}

static int
FileList_expandWildcards(JLI_List fl)
{
    size_t i, j;
    int expandedCnt = 0;
    for (i = 0; i < fl->size; i++) {
        if (isWildcard(fl->elements[i])) {
            JLI_List expanded = wildcardFileList(fl->elements[i]);
            if (expanded != NULL && expanded->size > 0) {
                expandedCnt++;
                JLI_MemFree(fl->elements[i]);
                JLI_List_ensureCapacity(fl, fl->size + expanded->size);
                for (j = fl->size - 1; j >= i+1; j--)
                    fl->elements[j+expanded->size-1] = fl->elements[j];
                for (j = 0; j < expanded->size; j++)
                    fl->elements[i+j] = expanded->elements[j];
                i += expanded->size - 1;
                fl->size += expanded->size - 1;
                /* fl expropriates expanded's elements. */
                expanded->size = 0;
            }
            JLI_List_free(expanded);
        }
    }
    return expandedCnt;
}

const char *
JLI_WildcardExpandClasspath(const char *classpath)
{
    const char *expanded;
    JLI_List fl;

    if (JLI_StrChr(classpath, '*') == NULL)
        return classpath;
    fl = JLI_List_split(classpath, PATH_SEPARATOR);
    expanded = FileList_expandWildcards(fl) ?
        JLI_List_join(fl, PATH_SEPARATOR) : classpath;
    JLI_List_free(fl);
    if (getenv(JLDEBUG_ENV_ENTRY) != 0)
        printf("Expanded wildcards:\n"
               "    before: \"%s\"\n"
               "    after : \"%s\"\n",
               classpath, expanded);
    return expanded;
}

#ifdef DEBUG_WILDCARD
static void
FileList_print(JLI_List fl)
{
    size_t i;
    putchar('[');
    for (i = 0; i < fl->size; i++) {
        if (i > 0) printf(", ");
        printf("\"%s\"",fl->elements[i]);
    }
    putchar(']');
}

static void
wildcardExpandArgv(const char ***argv)
{
    int i;
    for (i = 0; (*argv)[i]; i++) {
        if (equal((*argv)[i], "-cp") ||
            equal((*argv)[i], "-classpath")) {
            i++;
            (*argv)[i] = wildcardExpandClasspath((*argv)[i]);
        }
    }
}

static void
debugPrintArgv(char *argv[])
{
    int i;
    putchar('[');
    for (i = 0; argv[i]; i++) {
        if (i > 0) printf(", ");
        printf("\"%s\"", argv[i]);
    }
    printf("]\n");
}

int
main(int argc, char *argv[])
{
    argv[0] = "java";
    wildcardExpandArgv((const char***)&argv);
    debugPrintArgv(argv);
    /* execvp("java", argv); */
    return 0;
}
#endif /* DEBUG_WILDCARD */

/* Cute little perl prototype implementation....

my $sep = ($^O =~ /^(Windows|cygwin)/) ? ";" : ":";

sub expand($) {
  opendir DIR, $_[0] or return $_[0];
  join $sep, map {"$_[0]/$_"} grep {/\.(jar|JAR)$/} readdir DIR;
}

sub munge($) {
  join $sep,
    map {(! -r $_ and s/[\/\\]+\*$//) ? expand $_ : $_} split $sep, $_[0];
}

for (my $i = 0; $i < @ARGV - 1; $i++) {
  $ARGV[$i+1] = munge $ARGV[$i+1] if $ARGV[$i] =~ /^-c(p|lasspath)$/;
}

$ENV{CLASSPATH} = munge $ENV{CLASSPATH} if exists $ENV{CLASSPATH};
@ARGV = ("java", @ARGV);
print "@ARGV\n";
exec @ARGV;

*/
