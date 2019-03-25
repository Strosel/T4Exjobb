#include "SigCLib.h"

extern "C" void _LookUpEmbed_C(const char *label, void **ppthis, void *pobj);

void *sigclib_get_thisptr(const char *label)
{
  void *pthis = NULL;
  _LookUpEmbed_C(label, &pthis, 0);
  return pthis;
}


