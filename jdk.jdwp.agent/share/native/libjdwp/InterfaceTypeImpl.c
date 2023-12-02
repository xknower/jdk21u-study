#include "util.h"
#include "InterfaceTypeImpl.h"
#include "inStream.h"
#include "outStream.h"

static jboolean
invokeStatic(PacketInputStream *in, PacketOutputStream *out)
{
    return sharedInvoke(in, out);
}

Command InterfaceType_Commands[] = {
    {invokeStatic, "InvokeMethod"}
};

DEBUG_DISPATCH_DEFINE_CMDSET(InterfaceType)
