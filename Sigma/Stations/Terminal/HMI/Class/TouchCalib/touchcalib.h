

#define ORDERID_NONE           0xFFFFfffe

#define LSL_TOUCHINFO_ERR_ID                              -1                    // Unknown ID for SYSINFO
#define LSL_TOUCHINFO_ERR_NOT_AVAIL                       -2                    // Information for given ID not available on this CPU
#define LSL_TOUCHINFO_ERR_BUF                             -3                    // Invalid Buffer or Buffer length
#define LSL_TOUCHINFO_ERR_DEVICEID                        -4


// Interface
#define INTERFACE_CALIB                                   "CALIB"


// define struct
//------------------------------------------------------------------------------------
TYPE
    CALIB_TYPE :STRUCT
        calib_getscreenkoords           : pVoid;
        calib_clearscreen               : pVoid;
        calib_metapixel                 : pVoid;
        calib_metatext                  : pVoid;
        calib_calibfromdat              : pVoid;
        calib_calculate                 : pVoid;
        calib_calibtouch                : pVoid;
        calib_writecalibtodat           : pVoid;
        calib_setnewcalibration         : pVoid;
        calib_touchtest                 : pVoid;
        calib_calibtouchex              : pVoid;
        calib_setnewcalibrationex       : pVoid;
        calib_settoucheventcallback     : pVoid;
//version 1.0
        calib_version	                  : udint;
        calib_touchgetcount	            : pVoid;
        calib_getproperty               : pVoid;
        calib_setrawmode                : pVoid;
        calib_clearevents               : pVoid;
        calib_devicesetcalibration      : pVoid;
        calib_devicesetcalibrationex    : pVoid;
        calib_getdeviceid               : pVoid;
        calib_saveorder                 : pVoid;
        calib_setorderid                : pVoid;
    END_STRUCT;
END_TYPE


FUNCTION GLOBAL __cdecl P_TOUCHINFO_GETSCREENKOORDS
VAR_INPUT
    coor0 : ^XYKOORDS;
END_VAR;

FUNCTION GLOBAL __cdecl P_TOUCHINFO_CLEARSCREEN
VAR_INPUT
    framecol : dint;
END_VAR;

FUNCTION GLOBAL __cdecl P_TOUCHINFO_METAPIXEL
VAR_INPUT
    x0 : dint;
    y0 : dint;
    color0 : dint;
    size0  : dint;
END_VAR;

FUNCTION GLOBAL __cdecl P_TOUCHINFO_METATEXT
VAR_INPUT
    txt0 : ^char;
    x0 : dint;
    y0 : dint;
    color0 : dint;
    capt0  : dint;
END_VAR;

FUNCTION GLOBAL __cdecl P_TOUCHINFO_CALIBFROMDAT
VAR_INPUT
    path : ^char;
END_VAR
VAR_OUTPUT
	  iRetval : dint;
END_VAR;

FUNCTION GLOBAL __cdecl P_TOUCHINFO_CALCULATE
;

FUNCTION GLOBAL __cdecl P_TOUCHINFO_CALIBTOUCH
VAR_INPUT
    path : ^char;
END_VAR;

FUNCTION GLOBAL __cdecl P_TOUCHINFO_WRITECALIBTODAT
VAR_INPUT
    path0 : ^char;
    data0 : ^void;
END_VAR
VAR_OUTPUT
	  iRetval : dint;
END_VAR;

FUNCTION GLOBAL __cdecl P_TOUCHINFO_SETNEWCALIBRATION
VAR_INPUT
    newdat0 : ^void;
END_VAR
VAR_OUTPUT
	  iRetval : dint;
END_VAR;

FUNCTION GLOBAL __cdecl P_TOUCHINFO_TOUCHTEST
;

FUNCTION GLOBAL __cdecl P_TOUCHINFO_CALIBTOUCHEX
VAR_INPUT
    path : ^char;
    nbrX : dint;
    nbrY : dint;
END_VAR;

FUNCTION GLOBAL __cdecl P_TOUCHINFO_SETNEWCALIBRATIONEX
VAR_INPUT
    pDimension : ^XYKOORDS;
    gridSoll : ^XYKOORDS;
    gridAct : ^XYKOORDS;
END_VAR
VAR_OUTPUT
	  iRetval : dint;
END_VAR;

FUNCTION GLOBAL __cdecl P_TOUCHINFO_SETTOUCHEVENTCALLBACK
VAR_INPUT
    pfnTouchEventCb : ^void;
END_VAR;

FUNCTION GLOBAL __cdecl P_TOUCHINFO_TOUCHGETCOUNT
VAR_OUTPUT
    uiRetval : udint;
END_VAR;

FUNCTION GLOBAL __cdecl P_TOUCHINFO_GETPROPERTY
VAR_INPUT
    deviceid : udint;
    id : udint;
    buffer : ^void;
    size : udint;
END_VAR
VAR_OUTPUT
    iRetval : dint;
END_VAR;

FUNCTION GLOBAL __cdecl P_TOUCHINFO_SETRAWMODE
VAR_INPUT
    raw : udint;
END_VAR
VAR_OUTPUT
    iRetval : dint;
END_VAR;

FUNCTION GLOBAL __cdecl P_TOUCHINFO_CLEAREVENTS
VAR_OUTPUT
    iRetval : dint;
END_VAR;

FUNCTION GLOBAL __cdecl P_TOUCHINFO_DEVICESETCALIBRATION
VAR_INPUT
    deviceid : udint;
    orderid : dint;
    newdat0 : ^void;
END_VAR
VAR_OUTPUT
    iRetval : dint;
END_VAR;

FUNCTION GLOBAL __cdecl P_TOUCHINFO_DEVICESETCALIBRATIONEX
VAR_INPUT
    deviceid : udint;
    orderid : dint;
    pDimension : ^XYKOORDS;
    gridSoll : ^XYKOORDS;
    gridAct : ^XYKOORDS;
END_VAR
VAR_OUTPUT
    iRetval : dint;
END_VAR;

FUNCTION GLOBAL __cdecl P_TOUCHINFO_GETDEVICEID
VAR_INPUT
    order : dint;
END_VAR
VAR_OUTPUT
    iRetval : dint;
END_VAR;

FUNCTION GLOBAL __cdecl P_TOUCHINFO_SAVEORDER
VAR_OUTPUT
    iRetval : dint;
END_VAR;

FUNCTION GLOBAL __cdecl P_TOUCHINFO_SETORDERID
VAR_INPUT
    deviceid : udint;
    orderid : dint;
END_VAR
VAR_OUTPUT
    iRetval : dint;
END_VAR;



// function prototypes
//------------------------------------------------------------------------------------
#define OS_TOUCHINFO_GETSCREENKOORDS(p1) pCalib^.calib_getscreenkoords $ P_TOUCHINFO_GETSCREENKOORDS(p1)
#define OS_TOUCHINFO_CLEARSCREEN(p1) pCalib^.calib_clearscreen $ P_TOUCHINFO_CLEARSCREEN(p1)
#define OS_TOUCHINFO_METAPIXEL(p1, p2, p3, p4) pCalib^.calib_metapixel $ P_TOUCHINFO_METAPIXEL(p1, p2, p3, p4)
#define OS_TOUCHINFO_METATEXT(p1, p2, p3, p4, p5) pCalib^.calib_metatext $ P_TOUCHINFO_METATEXT(p1, p2, p3, p4, p5)
#define OS_TOUCHINFO_CALIBFROMDAT(p1) pCalib^.calib_calibfromdat $ P_TOUCHINFO_CALIBFROMDAT(p1)
#define OS_TOUCHINFO_CALCULATE() pCalib^.calib_calculate $ P_TOUCHINFO_CALCULATE()
#define OS_TOUCHINFO_CALIBTOUCH(p1) pCalib^.calib_calibtouch $ P_TOUCHINFO_CALIBTOUCH(p1)
#define OS_TOUCHINFO_WRITECALIBTODAT(p1, p2) pCalib^.calib_writecalibtodat $ P_TOUCHINFO_WRITECALIBTODAT(p1, p2)
#define OS_TOUCHINFO_SETNEWCALIBRATION(p1) pCalib^.calib_setnewcalibration $ P_TOUCHINFO_SETNEWCALIBRATION(p1)
#define OS_TOUCHINFO_TOUCHTEST() pCalib^.calib_touchtest $ P_TOUCHINFO_TOUCHTEST()
#define OS_TOUCHINFO_CALIBTOUCHEX(p1, p2, p3) pCalib^.calib_calibtouchex $ P_TOUCHINFO_CALIBTOUCHEX(p1, p2, p3)
#define OS_TOUCHINFO_SETNEWCALIBRATIONEX(p1, p2, p3) pCalib^.calib_setnewcalibrationex $ P_TOUCHINFO_SETNEWCALIBRATIONEX(p1, p2, p3)
#define OS_TOUCHINFO_SETTOUCHEVENTCALLBACK(p1) pCalib^.calib_settoucheventcallback $ P_TOUCHINFO_SETTOUCHEVENTCALLBACK(p1)

#define OS_TOUCHINFO_VERSION pCalib^.calib_version
#define OS_TOUCHINFO_TOUCHGETCOUNT() pCalib^.calib_touchgetcount $ P_TOUCHINFO_TOUCHGETCOUNT()
#define OS_TOUCHINFO_GETPROPERTY(p1, p2, p3, p4) pCalib^.calib_getproperty $ P_TOUCHINFO_GETPROPERTY(p1, p2, p3, p4)
#define OS_TOUCHINFO_SETRAWMODE(p1) pCalib^.calib_setrawmode $ P_TOUCHINFO_SETRAWMODE(p1)
#define OS_TOUCHINFO_CLEAREVENTS() pCalib^.calib_clearevents $ P_TOUCHINFO_CLEAREVENTS()
#define OS_TOUCHINFO_DEVICESETCALIBRATION(p1, p2, p3) pCalib^.calib_devicesetcalibration $ P_TOUCHINFO_DEVICESETCALIBRATION(p1, p2, p3)
#define OS_TOUCHINFO_DEVICESETCALIBRATIONEX(p1, p2, p3, p4, p5) pCalib^.calib_devicesetcalibrationex $ P_TOUCHINFO_DEVICESETCALIBRATIONEX(p1, p2, p3, p4, p5)
#define OS_TOUCHINFO_GETDEVICEID(p1) pCalib^.calib_getdeviceid $ P_TOUCHINFO_GETDEVICEID(p1)
#define OS_TOUCHINFO_SAVEORDER() pCalib^.calib_saveorder $ P_TOUCHINFO_SAVEORDER()
#define OS_TOUCHINFO_SETORDERID(p1, p2) pCalib^.calib_setorderid $ P_TOUCHINFO_SETORDERID(p1, p2)





