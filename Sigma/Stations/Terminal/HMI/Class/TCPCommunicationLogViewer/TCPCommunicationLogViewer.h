
#define TCPCommLogViewer_Tab_Left            5
#define TCPCommLogViewer_Tab_Type            60
#define TCPCommLogViewer_Tab_CMD             40
#define TCPCommLogViewer_Tab_SourcID         80 
#define TCPCommLogViewer_Tab_DestID          100
#define TCPCommLogViewer_Tab_DestCMD         70
#define TCPCommLogViewer_Tab_Prio            30
#define TCPCommLogViewer_Tab_Length          70
#define TCPCommLogViewer_Tab_JobID           90
#define TCPCommLogViewer_Tab_Timeout         70
#define TCPCommLogViewer_Tab_TimeStamp       90
#define TCPCommLogViewer_Tab_Callback        110
#define TCPCommLogViewer_Tab_ObjectName      300

#define TCPCommLogViewer_Tab_Left_Bar           50
#define TCPCommLogViewer_Tab_Left_ColorCurse    10
#define TCPCommLogViewer_Tab_Right_Bar          50
#define TCPCommLogViewer_Tab_Right_ColorCurse   10


#define TCPCommLogViewer_TabSize    (TCPCommLogViewer_Tab_Left + TCPCommLogViewer_TAB_Type + TCPCommLogViewer_TAB_CMD + TCPCommLogViewer_TAB_SourcID + TCPCommLogViewer_TAB_DestID + TCPCommLogViewer_TAB_DestCMD + TCPCommLogViewer_TAB_Prio + TCPCommLogViewer_TAB_Length + TCPCommLogViewer_TAB_JobID + TCPCommLogViewer_TAB_Timeout + TCPCommLogViewer_TAB_TimeStamp + TCPCommLogViewer_TAB_Callback + TCPCommLogViewer_TAB_ObjectName)

#define TCPCommLogViewer_EnableUSBExport


#define TCPCommLogViewer_ExportExtension     ".xls"
#ifdef TCPCommLogViewer_EnableUSBExport
  #define TCPCommLogViewer_ExportPath     "E:\LOG_CC"
#else
  #define TCPCommLogViewer_ExportPath     "C:\LOG_CC"
#endif

#define TCPCommLogViewer_Bar_Color_Start   RED
#define TCPCommLogViewer_Bar_Color_Stop    GREEN
#define TCPCommLogViewer_Bar_Color_Bg      LIGHTGRAY
