//This file was generated by the LASAL2 CodeGenerator  -- 
//Please, do not edit this file (it might be overwritten by the next generator run)
typedef struct SvrCh__MMStateSvr_PTofCls__SIG_LSL_CMultimasterState 
  {
    CHMETH *pMeth;
    _MMStateSvr dData;
    SVRDSC *pDsc;
  } SvrCh__MMStateSvr_PTofCls__SIG_LSL_CMultimasterState;

typedef struct CltCh__MMStateSvr_PTofCls__SIG_LSL_CMultimasterState 
  {
    struct SvrCh__MMStateSvr_PTofCls__SIG_LSL_CMultimasterState *pCh;
    _MMStateSvr dData;
    SVRCHCMD *pCmd;
  } CltCh__MMStateSvr_PTofCls__SIG_LSL_CMultimasterState;

typedef struct SvrCh_DINT 
  {
    CHMETH *pMeth;
    DINT dData;
    SVRDSC *pDsc;
  } SvrCh_DINT;

typedef struct CltCh_DINT 
  {
    struct SvrCh_DINT *pCh;
    DINT dData;
    SVRCHCMD *pCmd;
  } CltCh_DINT;

typedef struct SvrCh_HDINT 
  {
    CHMETH *pMeth;
    HDINT dData;
    SVRDSC *pDsc;
  } SvrCh_HDINT;

typedef struct CltCh_HDINT 
  {
    struct SvrCh_HDINT *pCh;
    HDINT dData;
    SVRCHCMD *pCmd;
  } CltCh_HDINT;

typedef struct SvrCh_UDINT 
  {
    CHMETH *pMeth;
    UDINT dData;
    SVRDSC *pDsc;
  } SvrCh_UDINT;

typedef struct CltCh_UDINT 
  {
    struct SvrCh_UDINT *pCh;
    UDINT dData;
    SVRCHCMD *pCmd;
  } CltCh_UDINT;

typedef struct SvrChCmd_DINT 
  {
    CMDMETH *pMeth;
    DINT dData;
    SVRDSC *pDsc;
  } SvrChCmd_DINT;

typedef struct CltChCmd__CheckSum 
  {
    struct SvrChCmd_DINT *pCh;
    DINT dData;
    _CheckSum *pCmd;
  } CltChCmd__CheckSum;

typedef struct CltChCmd__MultiTask 
  {
    struct SvrChCmd_DINT *pCh;
    DINT dData;
    _MultiTask *pCmd;
  } CltChCmd__MultiTask;

typedef struct CltChCmd__StdLib 
  {
    struct SvrChCmd_DINT *pCh;
    DINT dData;
    _StdLib *pCmd;
  } CltChCmd__StdLib;

typedef struct CltChCmd__TaskObjectControl 
  {
    struct SvrChCmd_DINT *pCh;
    DINT dData;
    _TaskObjectControl *pCmd;
  } CltChCmd__TaskObjectControl;

typedef struct SvrChCmd__FSM_TCP_USER 
  {
    CMDMETH *pMeth;
    _FSM_TCP_USER dData;
    SVRDSC *pDsc;
  } SvrChCmd__FSM_TCP_USER;

typedef struct CltChCmd__TCPIP_CLIENT 
  {
    struct SvrChCmd__FSM_TCP_USER *pCh;
    _FSM_TCP_USER dData;
    _TCPIP_CLIENT *pCmd;
  } CltChCmd__TCPIP_CLIENT;

typedef struct CltChCmd_ASCII_BIN 
  {
    struct SvrChCmd_DINT *pCh;
    DINT dData;
    ASCII_BIN *pCmd;
  } CltChCmd_ASCII_BIN;

typedef struct SvrChCmd_UDINT 
  {
    CMDMETH *pMeth;
    UDINT dData;
    SVRDSC *pDsc;
  } SvrChCmd_UDINT;

typedef struct CltChCmd_CriticalSection 
  {
    struct SvrChCmd_UDINT *pCh;
    UDINT dData;
    CriticalSection *pCmd;
  } CltChCmd_CriticalSection;

typedef struct CltChCmd_DataManager 
  {
    struct SvrChCmd_DINT *pCh;
    DINT dData;
    DataManager *pCmd;
  } CltChCmd_DataManager;

typedef struct CltChCmd_DataManagerFIFO 
  {
    struct SvrChCmd_UDINT *pCh;
    UDINT dData;
    DataManagerFIFO *pCmd;
  } CltChCmd_DataManagerFIFO;

typedef struct CltChCmd_DataManagerPriority 
  {
    struct SvrChCmd_UDINT *pCh;
    UDINT dData;
    DataManagerPriority *pCmd;
  } CltChCmd_DataManagerPriority;

typedef struct CltChCmd_DINT 
  {
    struct SvrChCmd_DINT *pCh;
    DINT dData;
    SVRCHCMD *pCmd;
  } CltChCmd_DINT;

typedef struct CltChCmd_GetTaskHandle 
  {
    struct SvrChCmd_DINT *pCh;
    DINT dData;
    GetTaskHandle *pCmd;
  } CltChCmd_GetTaskHandle;

typedef struct CltChCmd_MerkerEx 
  {
    struct SvrChCmd_UDINT *pCh;
    UDINT dData;
    MerkerEx *pCmd;
  } CltChCmd_MerkerEx;

typedef struct CltChCmd_Ram 
  {
    struct SvrChCmd_DINT *pCh;
    DINT dData;
    Ram *pCmd;
  } CltChCmd_Ram;

typedef struct CltChCmd_RamRingBuffer 
  {
    struct SvrChCmd_UDINT *pCh;
    UDINT dData;
    RamRingBuffer *pCmd;
  } CltChCmd_RamRingBuffer;

typedef struct CltChCmd_SigCLib 
  {
    struct SvrChCmd_DINT *pCh;
    DINT dData;
    SigCLib *pCmd;
  } CltChCmd_SigCLib;

typedef struct CltChCmd_String 
  {
    struct SvrChCmd_UDINT *pCh;
    UDINT dData;
    String *pCmd;
  } CltChCmd_String;

typedef struct CltChCmd_StringInternal 
  {
    struct SvrChCmd_UDINT *pCh;
    UDINT dData;
    StringInternal *pCmd;
  } CltChCmd_StringInternal;

typedef struct CltChCmd_System 
  {
    struct SvrChCmd_DINT *pCh;
    DINT dData;
    System *pCmd;
  } CltChCmd_System;

typedef struct CltChCmd_TCPCommunication 
  {
    struct SvrChCmd_DINT *pCh;
    DINT dData;
    TCPCommunication *pCmd;
  } CltChCmd_TCPCommunication;

typedef struct CltChCmd_TCPCommunicationLogFilter 
  {
    struct SvrChCmd_DINT *pCh;
    DINT dData;
    TCPCommunicationLogFilter *pCmd;
  } CltChCmd_TCPCommunicationLogFilter;

typedef struct SvrCh_BDINT 
  {
    CHMETH *pMeth;
    BDINT dData;
    SVRDSC *pDsc;
  } SvrCh_BDINT;

typedef struct SvrCh_DM_CPU_Types 
  {
    CHMETH *pMeth;
    DM_CPU_Types dData;
    SVRDSC *pDsc;
  } SvrCh_DM_CPU_Types;

typedef struct SvrCh_t_e_ConnectionType_PTofCls_TCPCommunication 
  {
    CHMETH *pMeth;
    t_e_ConnectionType dData;
    SVRDSC *pDsc;
  } SvrCh_t_e_ConnectionType_PTofCls_TCPCommunication;

typedef struct SvrCh_TIME_LSL 
  {
    CHMETH *pMeth;
    TIME_LSL dData;
    SVRDSC *pDsc;
  } SvrCh_TIME_LSL;

typedef struct SvrChCmd_DATE_LSL 
  {
    CMDMETH *pMeth;
    DATE_LSL dData;
    SVRDSC *pDsc;
  } SvrChCmd_DATE_LSL;

typedef struct SvrChCmd_IO_State 
  {
    CMDMETH *pMeth;
    IO_State dData;
    SVRDSC *pDsc;
  } SvrChCmd_IO_State;
