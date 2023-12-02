#include "util.h"
#include "transport.h"
#include "debugDispatch.h"
#include "VirtualMachineImpl.h"
#include "ReferenceTypeImpl.h"
#include "ClassTypeImpl.h"
#include "InterfaceTypeImpl.h"
#include "ArrayTypeImpl.h"
#include "FieldImpl.h"
#include "MethodImpl.h"
#include "ModuleReferenceImpl.h"
#include "ObjectReferenceImpl.h"
#include "StringReferenceImpl.h"
#include "ThreadReferenceImpl.h"
#include "ThreadGroupReferenceImpl.h"
#include "ClassLoaderReferenceImpl.h"
#include "ClassObjectReferenceImpl.h"
#include "ArrayReferenceImpl.h"
#include "EventRequestImpl.h"
#include "StackFrameImpl.h"

static CommandSet **cmdSetsArray;

void
debugDispatch_initialize(void)
{
    /*
     * Create the level-one (CommandSet) dispatch table.
     * Zero the table so that unknown CommandSets do not
     * cause random errors.
     */
    cmdSetsArray = jvmtiAllocate((JDWP_HIGHEST_COMMAND_SET+1) * sizeof(CommandSet *));

    if (cmdSetsArray == NULL) {
        EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"command set array");
    }

    (void)memset(cmdSetsArray, 0, (JDWP_HIGHEST_COMMAND_SET+1) * sizeof(CommandSet *));

    /*
     * Create the level-two (Command) dispatch tables to the
     * corresponding slots in the CommandSet dispatch table..
     */
    cmdSetsArray[JDWP_COMMAND_SET(VirtualMachine)] = &VirtualMachine_CmdSet;
    cmdSetsArray[JDWP_COMMAND_SET(ReferenceType)] = &ReferenceType_CmdSet;
    cmdSetsArray[JDWP_COMMAND_SET(ClassType)] = &ClassType_CmdSet;
    cmdSetsArray[JDWP_COMMAND_SET(InterfaceType)] = &InterfaceType_CmdSet;
    cmdSetsArray[JDWP_COMMAND_SET(ArrayType)] = &ArrayType_CmdSet;

    cmdSetsArray[JDWP_COMMAND_SET(Field)] = &Field_CmdSet;
    cmdSetsArray[JDWP_COMMAND_SET(Method)] = &Method_CmdSet;
    cmdSetsArray[JDWP_COMMAND_SET(ObjectReference)] = &ObjectReference_CmdSet;
    cmdSetsArray[JDWP_COMMAND_SET(StringReference)] = &StringReference_CmdSet;
    cmdSetsArray[JDWP_COMMAND_SET(ThreadReference)] = &ThreadReference_CmdSet;
    cmdSetsArray[JDWP_COMMAND_SET(ThreadGroupReference)] = &ThreadGroupReference_CmdSet;
    cmdSetsArray[JDWP_COMMAND_SET(ClassLoaderReference)] = &ClassLoaderReference_CmdSet;
    cmdSetsArray[JDWP_COMMAND_SET(ArrayReference)] = &ArrayReference_CmdSet;
    cmdSetsArray[JDWP_COMMAND_SET(EventRequest)] = &EventRequest_CmdSet;
    cmdSetsArray[JDWP_COMMAND_SET(StackFrame)] = &StackFrame_CmdSet;
    cmdSetsArray[JDWP_COMMAND_SET(ClassObjectReference)] = &ClassObjectReference_CmdSet;
    cmdSetsArray[JDWP_COMMAND_SET(ModuleReference)] = &ModuleReference_CmdSet;
}

void
debugDispatch_reset(void)
{
}

CommandHandler
debugDispatch_getHandler(int cmdSetNum, int cmdNum, const char **cmdSetName_p, const char **cmdName_p)
{
    CommandSet *cmd_set;
    *cmdSetName_p = "<Invalid CommandSet>";
    *cmdName_p = "<Unknown Command>";

    if (cmdSetNum > JDWP_HIGHEST_COMMAND_SET) {
        return NULL;
    }

    cmd_set = cmdSetsArray[cmdSetNum];
    if (cmd_set == NULL) {
      return NULL;
    }

    *cmdSetName_p = cmd_set->cmd_set_name;
    if (cmdNum > cmd_set->num_cmds) {
        *cmdName_p = "<Invalid Command>";
        return NULL;
    } else {
        *cmdName_p = cmd_set->cmds[cmdNum - 1].cmd_name;
        return cmd_set->cmds[cmdNum - 1].cmd_handler;
    }
}
