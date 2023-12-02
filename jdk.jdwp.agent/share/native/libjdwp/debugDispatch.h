#ifndef JDWP_DEBUGDISPATCH_H
#define JDWP_DEBUGDISPATCH_H

#include "vm_interface.h"

/*
 * Type of all command handler functions. First argument is the
 * input stream. Second argument is the output sent back to the
 * originator, but only if JNI_TRUE is returned. If JNI_FALSE
 * is returned, no reply is made.
 */
struct PacketInputStream;
struct PacketOutputStream;

typedef jboolean (*CommandHandler)(struct PacketInputStream *,
                                  struct PacketOutputStream *);
void debugDispatch_initialize(void);
void debugDispatch_reset(void);
CommandHandler debugDispatch_getHandler(int cmdSetNum, int cmdNum,
                                        const char **cmdSetName_p, const char **cmdName_p);

typedef struct Command {
    CommandHandler cmd_handler;
    const char *cmd_name;
} Command;

typedef struct CommandSet {
    const int num_cmds;
    const char *cmd_set_name;
    const Command *cmds;
} CommandSet;

#define DEBUG_DISPATCH_DEFINE_CMDSET(_name)     \
CommandSet _name##_CmdSet = {                   \
    sizeof(_name##_Commands) / sizeof(Command), \
    #_name,                                     \
    _name##_Commands                            \
};

#endif
