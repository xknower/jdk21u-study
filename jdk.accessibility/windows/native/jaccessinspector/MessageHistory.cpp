#include <windows.h>   // includes basic windows functionality
#include <iterator>
#include "MessageHistory.h"

size_t MessageHistory::sm_MaxMessages = 1000;

void MessageHistory::AddMessage(const char * message) {
    if ( ( NULL == message ) || ( 0 == message [0] ) ) {
        return;
    }

    if ( m_Messages.size() >= sm_MaxMessages ) {
        // Remove the oldest message
        m_Messages.pop_front();
    }

    m_Messages.push_back(std::string (message) );
    m_CurrentPosition = m_Messages.end();
    -- m_CurrentPosition;
}

const char * MessageHistory::GetFirstMessage() {
    if ( m_Messages.empty() ) {
        return "";
    }

    m_CurrentPosition = m_Messages.begin();
    return (*m_CurrentPosition).c_str();
}

const char * MessageHistory::GetPreviousMessage() {
    if ( m_Messages.empty() ) {
        return "";
    }

    if ( m_CurrentPosition != m_Messages.begin() ) {
        -- m_CurrentPosition;
    }

    return (*m_CurrentPosition).c_str();
}

const char * MessageHistory::GetNextMessage() {
    if ( m_Messages.empty() ) {
        return "";
    }

    ++ m_CurrentPosition;
    if ( m_CurrentPosition == m_Messages.end() ) {
        -- m_CurrentPosition;
    }

    return (*m_CurrentPosition).c_str();
}

const char * MessageHistory::GetLastMessage()
{
    if ( m_Messages.empty() ) {
        return "";
    }

    m_CurrentPosition = m_Messages.end();
    -- m_CurrentPosition;
    return (*m_CurrentPosition).c_str();
}

BOOL MessageHistory::IsFirstMessage() {
    if ( m_Messages.empty() ) {
        return FALSE;
    }
    if ( m_CurrentPosition == m_Messages.begin() ) {
        return TRUE;
    }
    return FALSE;
}

BOOL MessageHistory::IsLastMessage() {
    if ( m_Messages.empty() ) {
        return FALSE;
    }
    stringlist::const_iterator itTest = m_Messages.end();
    -- itTest;
    if ( itTest == m_CurrentPosition ) {
        return TRUE;
    }
    return FALSE;
}

size_t MessageHistory::GetMessageCount() {
    size_t ret_val = m_Messages.size();
    return ret_val;
}

const char * MessageHistory::GetCurrentMessage() {
    if ( m_Messages.empty() ) {
        return "";
    }

    return (*m_CurrentPosition).c_str();
}

const char * MessageHistory::GetMessage(const size_t messageIndex) {
    if ( m_Messages.empty() ) {
        return "";
    }

    if ( messageIndex >= m_Messages.size() ) {
        return "";
    }

    stringlist::const_iterator it = m_Messages.begin();
    std::advance(it, messageIndex);
    m_CurrentPosition = it;

    return (*it).c_str();
}

size_t MessageHistory::GetCurrentMessageIndex() {
    if ( m_Messages.empty() ) {
        return 0;
    }

    stringlist::const_iterator itBegin = m_Messages.begin();
    size_t ret_val = std::distance(itBegin, m_CurrentPosition);
    return ret_val;
}

void MessageHistory::clear() {
    m_Messages.clear();
}
