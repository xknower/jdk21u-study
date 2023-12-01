#ifndef __MessageHistory_H__
#define __MessageHistory_H__

#include <list>
#include <string>

class MessageHistory
{
public:
    static size_t sm_MaxMessages;
private:
    typedef std::list< std::string > stringlist;
    stringlist m_Messages;
    stringlist::const_iterator m_CurrentPosition;

public:
    void AddMessage(const char * message);
    const char * GetFirstMessage();
    const char * GetPreviousMessage();
    const char * GetNextMessage();
    const char * GetLastMessage();
    const char * GetCurrentMessage();
    const char * GetMessage(const size_t messageIndex);
    size_t GetCurrentMessageIndex();
    BOOL IsFirstMessage();
    BOOL IsLastMessage();
    size_t GetMessageCount();
    void clear();
};
#endif
