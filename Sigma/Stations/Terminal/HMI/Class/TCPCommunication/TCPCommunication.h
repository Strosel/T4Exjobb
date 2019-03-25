// generic defines
#define     OS_STARTVERSION_SALAMANDER  16#9000

// defines for creating the parallel task
#define     TCPCom_TaskName         "TCPCom_"                    // name for the parallel task of the class
#define     TCPCom_TaskPrio         11                           // priority of the parallel task
#define     TCPCom_StackSize        (16#F000 OR 16#80000000)     // 30000 Byte
#define     TCPCom_TaskDelay        1                            // delay for the parallel task
#define     TCPCom_TaskDelay_Off    100        

// setup for the communication
#define     TCPCom_Send_Acknowledge               // activate define, if a acknowledge for a received package should be send
#define     TCPCom_TimeOutCheckBeforeSending      // activate define, to check if the timeout exceeded before sending the package
#define     TCPCom_DefSendTimeout         10      // default timeout for sending a package
#define     TCPCom_MissingAliveError      3       // Connection rebuild will be triggered after x missing alive signals
#define     TCPCom_AliveSignalTime        1000ms  // default value for the interval of the Alive Signal


// defines for the memory handling
#define     TCPCom_EntrySize          (2 * 1024 * 1024)   // 2MB: size of the receive and send buffer for one element
#define     TCPCom_MemoryMark         9                   // memory mark for debugging in the visualisation
#define     TCPCom_LogEntries         5000                // maximum number of entries in the logbuffer


// configuration for stack
//#define     TCPCom_CheckStack       // activate this define, to check the actual size of the stack

// configuration heartbeat error detection
#define     TCPCom_Runtime            1000        // set the heartbeat error value 
#define     TCPCom_Runtime_TraceMsg               // runtime error trace message

#define     CMD_GETIPOverStation      1000


// defines for SetParameter() method
#define     TCPCom_ParaWR_ComPort               0
#define     TCPCom_ParaWR_MissingAliveError    10
#define     TCPCom_ParaWR_AliveSignalTime      11
#define     TCPCOM_ParaWR_TaskPriority         12

// defines for ReadParameter() method
#define     TCPCom_ParaRD_ComPort               0
#define     TCPCom_ParaRD_MissingAliveError    10
#define     TCPCom_ParaRD_AliveSignalTime      11
#define     TCPCom_ParaRD_TaskPriority         12
