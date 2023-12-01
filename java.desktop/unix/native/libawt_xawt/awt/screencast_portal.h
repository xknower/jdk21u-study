#ifdef HEADLESS
#error This file should not be included in headless library
#endif

#ifndef _SCREENCAST_PORTAL_H
#define _SCREENCAST_PORTAL_H

#include "gtk_interface.h"

#define PORTAL_TOKEN_TEMPLATE "awtPipewire%lu"
#define PORTAL_REQUEST_TEMPLATE "/org/freedesktop/portal/desktop/" \
                                "request/%s/awtPipewire%lu"

void debug_screencast(const char *__restrict fmt, ...);

int getPipewireFd(const gchar *token,
                  GdkRectangle *affectedBounds,
                  gint affectedBoundsLength);

void portalScreenCastCleanup();

gboolean initXdgDesktopPortal();

void errHandle(GError *error, const gchar *functionName, int lineNum);

struct XdgDesktopPortalApi {
    GDBusConnection *connection;
    GDBusProxy *screenCastProxy;
    gchar *senderName;
    char *screenCastSessionHandle;
};

struct DBusCallbackHelper {
    guint id;
    void *data;
    gboolean isDone;
};

typedef enum {
    RESULT_OK = 0,
    RESULT_ERROR = -1,
    RESULT_DENIED = -11,
    RESULT_OUT_OF_BOUNDS = -12,
} ScreenCastResult;

struct StartHelper {
    const gchar *token;
    ScreenCastResult result;
};

#endif //_SCREENCAST_PORTAL_H
