#include <windows.h>   // includes basic windows functionality
#include <stdio.h>
#include <commctrl.h>
#include <jni.h>
#include "jaccesswalkerResource.h"
#include "AccessBridgeCalls.h"
#include "AccessBridgeCallbacks.h"
#include "AccessBridgeDebug.h"

#include <windows.h>
#include <stdio.h>
#include <stdlib.h>
#include <io.h>
#include <direct.h>
#include <process.h>

#include <time.h>

extern FILE *file;

#define null NULL
#define JACCESSWALKER_LOG "jaccesswalker.log"

/**
 * A node in the jaccesswalker tree
 */
class AccessibleNode {

    HWND baseHWND;
    HTREEITEM treeNodeParent;
    long vmID;
    AccessibleContext ac;
    AccessibleNode *parentNode;
    char accessibleName[MAX_STRING_SIZE];
    char accessibleRole[SHORT_STRING_SIZE];

public:
    AccessibleNode(long vmID, AccessibleContext context,
                   AccessibleNode *parent, HWND hWnd,
                   HTREEITEM parentTreeNodeItem);
    ~AccessibleNode();
    void setAccessibleName(char *name);
    void setAccessibleRole(char *role);
    BOOL displayAPIWindow();  // bring up an Accessibility API detail window
};


/**
 * The main application class
 */
class Jaccesswalker {

public:
    Jaccesswalker(int nCmdShow);
    BOOL InitWindow(int windowMode);
    char *getAccessibleInfo( long vmID, AccessibleContext ac, char *buffer,
                             int bufsize );
    void exitjaccesswalker(HWND hWnd);
    void buildAccessibilityTree();
    void addComponentNodes( long vmID, AccessibleContext context,
                            AccessibleNode *parent, HWND hWnd,
                            HTREEITEM treeNodeParent, HWND treeWnd );
};

char *getTimeAndDate();

void displayAndLogText(char *buffer, ...);

LRESULT CALLBACK WinProc (HWND, UINT, WPARAM, LPARAM);

void debugString(char *msg, ...);

LRESULT CALLBACK jaccesswalkerWindowProc( HWND hDlg, UINT message, UINT wParam,
                                          LONG lParam );

BOOL CALLBACK EnumWndProc(HWND hWnd, LPARAM lParam);
BOOL CALLBACK EnumChildProc(HWND hwnd, LPARAM lParam);

HWND CreateATreeView(HWND hwndParent);

LRESULT CALLBACK AccessInfoWindowProc( HWND hWnd, UINT message, UINT wParam,
                                       LONG lParam );

char *getAccessibleInfo( long vmID, AccessibleContext ac, char *buffer,
                         int bufsize );
