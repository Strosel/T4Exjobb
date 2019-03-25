
// commands for SetConnParameter / SetMainParameter
#define TCPCLT_CMD_SETSOCKOPT         0
#define TCPCLT_CMD_TASKPRIO           1
#define TCPCLT_CMD_CLOSESOCKETTYPE    2
#define TCPCLT_CMD_KEEPALIVEPARA      3

// sub commands for SetConnParameter / SetMainParameter

// sub cmds socket options
#define TCPCLT_SOCKOPT_NAGLE          0
#define TCPCLT_SOCKOPT_KEEPALIVE      1
#define TCPCLT_SOCKOPT_DELAYEDACK     2

// sub cmd task priority
#define TCPCLT_SUB_CMD_TASKPRIO       0

// sub cmds keep alive parameter
#define TCPCLT_KEEPALIVE_KEEPIDLE     0
#define TCPCLT_KEEPALIVE_KEEPINTVL    1
#define TCPCLT_KEEPALIVE_KEEPCNT      2

// sub cmd close socket type
#define TCPCLT_CLOSESOCKETTYPE        0

// commands for GetSetting
#define TCPCLT_GET_SETTING_PORT       0
#define TCPCLT_GET_SETTING_BUFSIZE    1
#define TCPCLT_GET_SETTING_MAXCONN    2
#define TCPCLT_GET_SETTING_CONFIG     3