#include <string.h>
#include <stdlib.h>

#include "jni.h"
#include "manifest_info.h"
#include "JarFacade.h"

typedef struct {
    jarAttribute* head;
    jarAttribute* tail;
} iterationContext;

static void
doAttribute(const char* name, const char* value, void* user_data) {
    iterationContext* context = (iterationContext*) user_data;

    jarAttribute* attribute = (jarAttribute*)malloc(sizeof(jarAttribute));
    if (attribute != NULL) {
        attribute->name = strdup(name);
        if (attribute->name == NULL) {
            free(attribute);
        } else {
            char *begin = (char *)value;
            char *end;
            size_t value_len;

            /* skip any leading white space */
            while (*begin == ' ') {
                begin++;
            }

            /* skip any trailing white space */
            end = &begin[strlen(begin)];
            while (end > begin && end[-1] == ' ') {
                end--;
            }

            if (begin == end) {
                /* no value so skip this attribute */
                free(attribute->name);
                free(attribute);
                return;
            }

            value_len = (size_t)(end - begin);
            attribute->value = malloc(value_len + 1);
            if (attribute->value == NULL) {
                free(attribute->name);
                free(attribute);
            } else {
                /* save the value without leading or trailing whitespace */
                strncpy(attribute->value, begin, value_len);
                attribute->value[value_len] = '\0';
                attribute->next = NULL;
                if (context->head == NULL) {
                    context->head = attribute;
                } else {
                    context->tail->next = attribute;
                }
                context->tail = attribute;
            }
        }

    }
}

/*
 * Return a list of attributes from the main section of the given JAR
 * file. Returns NULL if there is an error or there aren't any attributes.
 */
jarAttribute*
readAttributes(const char* jarfile)
{
    int rc;
    iterationContext context = { NULL, NULL };

    rc = JLI_ManifestIterate(jarfile, doAttribute, (void*)&context);

    if (rc == 0) {
        return context.head;
    } else {
        freeAttributes(context.head);
        return NULL;
    }
}


/*
 * Free a list of attributes
 */
void
freeAttributes(jarAttribute* head) {
    while (head != NULL) {
        jarAttribute* next = (jarAttribute*)head->next;
        free(head->name);
        free(head->value);
        free(head);
        head = next;
    }
}

/*
 * Get the value of an attribute in an attribute list. Returns NULL
 * if attribute not found.
 */
char*
getAttribute(const jarAttribute* attributes, const char* name) {
    while (attributes != NULL) {
        if (strcasecmp(attributes->name, name) == 0) {
            return attributes->value;
        }
        attributes = (jarAttribute*)attributes->next;
    }
    return NULL;
}
