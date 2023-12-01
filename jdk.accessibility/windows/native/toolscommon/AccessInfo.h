typedef void (WINAPI * LogStringCallbackFP) (const char * lpString);
extern LogStringCallbackFP g_LogStringCallback;

#define LINE_BUFSIZE 1024
#define LARGE_BUFSIZE 5120
#define HUGE_BUFSIZE 20480

/*
 * returns formatted date and time
 */
char *getTimeAndDate();

/*
 * displays a message in a dialog and writes the message to a logfile
 */
void displayAndLog(HWND hDlg, int nIDDlgItem, FILE *logfile, char *msg, ...);

/*
 * writes a text string to a logfile
 */
void logString(FILE *logfile, const char *msg, ...);

/**
 * returns accessibility information for an AccessibleContext
 */
char *getAccessibleInfo(long vmID, AccessibleContext ac, char *buffer, int bufsize);

/**
 * returns accessibility information at the specified coordinates in an AccessibleContext
 */
char *getAccessibleInfo(long vmID, AccessibleContext ac, int x, int y, char *buffer, int bufsize);
