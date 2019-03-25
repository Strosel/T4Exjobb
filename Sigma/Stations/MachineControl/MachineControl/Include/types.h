//This file was generated by the LASAL2 CodeGenerator  -- 
//Please, do not edit this file (it might be overwritten by the next generator run)
TYPE
  _FSM_TCP_USER :  //! <Type Comment="stepping switch for TCP/IP connection" Name="_FSM_TCP_USER"/>
  (
    _STATE_INIT_SERVER,
    _STATE_IDLE,  //! <Type Comment="allocate main socket" Name="_FSM_TCP_USER._STATE_IDLE"/>
    _STATE_MAIN_SOCK,  //! <Type Comment="listen for an incoming connection" Name="_FSM_TCP_USER._STATE_MAIN_SOCK"/>
    _STATE_LISTEN,  //! <Type Comment="allocate the send/receive socket" Name="_FSM_TCP_USER._STATE_LISTEN"/>
    _STATE_ACCEPT,  //! <Type Comment="general send and receive state" Name="_FSM_TCP_USER._STATE_ACCEPT"/>
    _STATE_CONNECT,  //! <Type Comment="close main socket" Name="_FSM_TCP_USER._STATE_CONNECT"/>
    _STATE_RECV,  //! <Type Comment="close send/receive socket" Name="_FSM_TCP_USER._STATE_RECV"/>
    _STATE_SHUTDOWN,  //! <Type Comment="ERROR state" Name="_FSM_TCP_USER._STATE_SHUTDOWN"/>
    _STATE_CLOSE_MAIN_SOCK,
    _STATE_CLOSE_SOCK,
    _STATE_ERROR,
    _STATE_ERROR_ALLOCATING_MEMORY,
    _STATE_ERROR_CREATING_MUTEX,
    _STATE_ERROR_CREATING_TASK,
    _STATE_SEND,
    _STATE_READ_RINGBUFFER,
    _STATE_MAIN_SOCK_OPT,
    _STATE_DEL_CONNECTION,
    _STATE_CONN_SOCK_OPT
  )$UDINT;
  DM_CPU_Types :
  (
    CPUNotIdentified,
    IPC_Text_CPU,
    IPC_800_CPU,
    IPC_1024_CPU,
    PCD_CPU,
    DSE_CPU,
    DCP_CPU,
    DCC080_CPU,
    DCP645_CPU,
    AK500_CPU,
    TSTROM_CPU,
    NoCPU_11,
    NoCPU_12,
    NoCPU_13,
    NoCPU_14,
    NoCPU_15,
    ELAN_CPU,
    ELAN800_CPU,
    WINPC_CPU,
    TERMINAL_CPU,
    DTC281_CPU,
    ELAN_TEXT_CPU,
    ELAN_800_CPU,
    ETT321_CPU,
    HERZ_CPU,
    ETT261_CPU,
    ETT431_CPU,
    WIN_PC98_CPU,
    WINPC_NT_CPU,
    WINIPC_98_CPU,
    WINIPC_NT_CPU,
    IPC_320_CPU,
    C_IPC_CPU,
    NoCPU_33,
    RK500_CPU,
    CCL721_CPU,
    CCL911_CPU,
    BDF2000_CPU,
    StandardPC_CPU,
    CCL081CET_CPU,
    CCL081_CPU,
    CCL722_CPU,
    DCL642_CPU,
    Teachbox_CPU,
    DTC081_CPU,
    DTC081_IP_CPU,
    ETV_CPU,
    HZS511_CPU,
    AUW11X_CPU,
    BDF2000_USB_CPU,
    DTC101_CPU,
    HZS515_CPU,
    CCP511_CPU,
    ETVEDGE_CPU,
    ARM_IMX6_CPU:=65537
  )$UDINT;
  IO_FLAG : BINT  //! <Type Comment="Status Flag f�r IO Daten" Name="IO_FLAG"/>
  [
    1 WrongHW,  //! <Type Comment="Falsche Hardware verbunden" Name="IO_FLAG.WrongHW"/>
    2 NoHW,  //! <Type Comment="Keine Hardware verbunden" Name="IO_FLAG.NoHW"/>
    3 NoCalibration,  //! <Type Comment="Keine Kalibrierungsdaten im Modul-EEPROM" Name="IO_FLAG.NoCalibration"/>
    4 ParaChkWrong,  //! <Type Comment="Die Parameter-Checksumme ist falsch" Name="IO_FLAG.ParaChkWrong"/>
    5 PhysicHiLimit,  //! <Type Comment="IO ist am oberen physikalischen Limit" Name="IO_FLAG.PhysicHiLimit"/>
    6 PhysicLoLimit,  //! <Type Comment="IO ist am unteren physikalischen Limit" Name="IO_FLAG.PhysicLoLimit"/>
    7 Invert,  //! <Type Comment="Daten sind invertiert" Name="IO_FLAG.Invert"/>
    14 OnDummyMode,  //! <Type Comment="1 = Objekt ist im Dummymodus (nicht refreshed)" Name="IO_FLAG.OnDummyMode"/>
    15 NotConnected,  //! <Type Comment="1 = Objekt ist nicht verbunden" Name="IO_FLAG.NotConnected"/>
    16 PhysicAccessOff,  //! <Type Comment="1 = kein physikalischer Zugriff erlaubt" Name="IO_FLAG.PhysicAccessOff"/>
  ];
#pragma pack(push, 1)
  IO_State : STRUCT
    uiIO_Flags : IO_FLAG;
    uiChNo : UINT;
  END_STRUCT;
#pragma pack(pop)
END_TYPE
