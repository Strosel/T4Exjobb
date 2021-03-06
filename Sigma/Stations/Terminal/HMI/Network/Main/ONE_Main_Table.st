//This file was generated by the LASAL2 CodeGenerator  -- 
//Please, do not edit this file (it might be overwritten by the next generator run)
#define OBJECTS_CONFIG

//Define part of class _TCPIP_SERVER
#ifndef OS_VERSION_MIN_SALAMANDER
    #define OS_VERSION_MIN_SALAMANDER          0x9000
  #endif
  #define TCP_SVR_NO_ERROR                          0

  #define TCP_SVR_ERR_INVALID_SOCKET            -1


  #define TCP_SVR_ERR_SET_PARA_INVALID_CMD     -10
  #define TCP_SVR_ERR_SET_PARA_INVALID_SUB_CMD -11

  #define TCP_SVR_ERR_TASK_ALREADY_EXIST       -12


  // - RETURN VALUES PRIVATE METHOD Read_AP_TaskPeriod
  #define TCP_SVR_ERR_TASK_TIME_NO_APP            -31
  #define TCP_SVR_ERR_TASK_TIME_WRONG_TASK_TYPE   -33
  #define TCP_SVR_ERR_TASK_TIME_OBJ_NOT_FOUND     -35

  // - RETURN VALUES PRIVATE METHOD Read_LSLOBJ_CRC32
  #define TCP_SVR_ERR_GET_LSLOBJ_NAME             -20
  #define TCP_SVR_ERR_INVALID_POINTER             -21

  // - RETURN VALUES PRIVATE METHOD AP_TaskCreate
  #define TCP_SVR_ERR_TASK_CREATE                 -40

  // - RETURN VALUES PRIVATE METHOD AP_SemaCreate
  #define TCP_SVR_ERR_SEMA_CREATE                 -50
  #define TCP_SVR_ERR_SEMA_INVALID_IDX            -51
  #define TCP_SVR_ERR_SEMA_INVALID_NAME           -52

  #define TCP_SVR_SEND_BUFFER_EMPTY               -6
  #define TCP_SVR_ERR_SEND_BUFFER_FATAL_ERROR     -7

  #define TCP_SVR_SEND_BUFFER_FULL      -4

  #define TCP_SVR_SOCK_INFO_CONNECT                 1
  #define TCP_SVR_SOCK_INFO_DISCONNECT              0

  #define TCP_SVR_ERR_GROUP_OS                      0
  #define TCP_SVR_ERR_GROUP_AP                      1

  #define TCP_SVR_ERR_CODE_AP_TIMEOUT              -1

  #ifndef INIT_FIRSTSCAN
    #define INIT_FIRSTSCAN                         12
  #endif

  #ifndef AP_TASK_PRIORITY_DEFAULT
    #define AP_TASK_PRIORITY_DEFAULT               14
  #endif

  #define TCP_SVR_ERR_MAIN_INIT                   -90

  #define TCP_SVR_ERR_MAX_CONN                    -80

  #ifndef KEEPALIVE_INTERVAL_DEFAULT
    #define KEEPALIVE_INTERVAL_DEFAULT           7200
  #endif

  #ifndef KEEPALIVE_RETRYTIME_DEFAULT
    #define KEEPALIVE_RETRYTIME_DEFAULT            75
  #endif

  #ifndef KEEPALIVE_RETRYCNT_DEFUALT
    #define KEEPALIVE_RETRYCNT_DEFUALT             10
  #endif

  #ifndef CLOSE_SOCKET_TYPE_DEFAULT
    #define CLOSE_SOCKET_TYPE_DEFAULT               0
  #endif
#include "..\..\Class\_TCPIP_SERVER\SalamanderDelay.h"
#include <.\lsl_st_mt.h>


FUNCTION GLOBAL TAB ONE_Main
-1$UINT, 0$UINT, 
0$UINT, 0$UINT,

7$UDINT,
TO_UDINT(916194998), "_CheckSum", 1$UINT, 0$UINT, 1$UINT,
TO_UDINT(26234884), "_TCPIP_SERVER", 1$UINT, 36$UINT, 0$UINT,
TO_UDINT(2225119864), "MerkerEx", 1$UINT, 22$UINT, 3$UINT,
TO_UDINT(1019694778), "Reader", 0$UINT, 0$UINT, 1$UINT,
TO_UDINT(1850111279), "String", 1$UINT, 10$UINT, 3$UINT,
TO_UDINT(2898603620), "StringInternal", 1$UINT, 82$UINT, 0$UINT,
TO_UDINT(3232495061), "VirtualBaseInit", 1$UINT, 3$UINT, 0$UINT,

1$UINT, TO_UDINT(1273150669), "READER1\_BASE\CHECKSUM", 


1$UINT, TO_UDINT(1955192523), "READER1\_BASE\STRSEMANAME01\MERKEREX0", 
1$UINT, TO_UDINT(3983259850), "READER1\_BASE\STRSEMANAME02\MERKEREX0", 
1$UINT, TO_UDINT(2995372235), "READER1\_BASE\STRTASKNAME\MERKEREX0", 

_NO_ATTR, TO_UDINT(2824106603), "READER1", 

1$UINT, TO_UDINT(1682723921), "READER1\_BASE\STRSEMANAME01", 
1$UINT, TO_UDINT(4249159147), "READER1\_BASE\STRSEMANAME02", 
1$UINT, TO_UDINT(383104622), "READER1\_BASE\STRTASKNAME", 



//Internal connections
0$UDINT,
7$UDINT,
TO_UDINT(4), "CheckSum", TO_UDINT(0),
TO_UDINT(4), "StrTaskName", TO_UDINT(7),
TO_UDINT(4), "StrSemaName01", TO_UDINT(5),
TO_UDINT(4), "StrSemaName02", TO_UDINT(6),
TO_UDINT(5), "MerkerEx0", TO_UDINT(1),
TO_UDINT(6), "MerkerEx0", TO_UDINT(2),
TO_UDINT(7), "MerkerEx0", TO_UDINT(3),
0$UDINT,
3$UDINT,
TO_UDINT(5), "DataBuffer", TO_UDINT(1), "m_udLength",
TO_UDINT(6), "DataBuffer", TO_UDINT(2), "m_udLength",
TO_UDINT(7), "DataBuffer", TO_UDINT(3), "m_udLength",

//Initialization values
0$UDINT,
9$UDINT,
TO_UDINT(1), "ThreadSafe", TO_UDINT(0),//|Main.StrSemaName01\_base\Reader1\MerkerEx0.ThreadSafe;
TO_UDINT(2), "ThreadSafe", TO_UDINT(0),//|Main.StrSemaName02\_base\Reader1\MerkerEx0.ThreadSafe;
TO_UDINT(3), "ThreadSafe", TO_UDINT(0),//|Main.StrTaskName\_base\Reader1\MerkerEx0.ThreadSafe;
TO_UDINT(4), "NextOrderId", TO_UDINT(1),//|Main.Reader1.NextOrderId;
TO_UDINT(4), "cPort", TO_UDINT(8080),//|Main.Reader1.cPort;
TO_UDINT(4), "cMaxConnections", TO_UDINT(0),//|Main.Reader1.cMaxConnections;
TO_UDINT(5), "SingleRealloc", TO_UDINT(1),//|Main._base\Reader1\StrSemaName01.SingleRealloc;
TO_UDINT(6), "SingleRealloc", TO_UDINT(1),//|Main._base\Reader1\StrSemaName02.SingleRealloc;
TO_UDINT(7), "SingleRealloc", TO_UDINT(1),//|Main._base\Reader1\StrTaskName.SingleRealloc;

//Symbolic initialization values
0$UDINT,
0$UDINT,

//Initialization of string values
0$UDINT,
0$UDINT,

//Configuration of tasks (RealTime, Cyclic, Background)
0$UDINT,
0$UDINT,

0$UDINT,
1$UDINT,
TO_UDINT(4), (10)$UDINT, 4194303$DINT, 	//READER1

0$UDINT,
0$UDINT,

//External connections
0$UDINT,
0$UDINT,

//Magic internal connections
0$UDINT,
0$UDINT,

//Configuration of timeslots of tasks (RealTime, Cyclic)
#ifdef __LSLLDR_TIMESLOTS__
0$UDINT,
0$UDINT,

0$UDINT,
0$UDINT,

#endif //__LSLLDR_TIMESLOTS__

//MultiMaster connections
#ifdef __LSLLDR_MULTIMASTER__
0$UDINT,
0$UDINT,
#endif //__LSLLDR_MULTIMASTER__

//Configuration for task ID (RealTime, CyclicTime)
#ifdef __LSLLDR_TASKID__
"\\\\TASKS2  "
0$UDINT,
0$UDINT,

0$UDINT,
1$UDINT,
(0)$UDINT, 	//READER1

0$UDINT,
0$UDINT,

#endif //__LSLLDR_TASKID__

END_FUNCTION