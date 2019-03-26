//This file was generated by the LASAL2 CodeGenerator  -- 
//Please, do not edit this file (it might be overwritten by the next generator run)
//{{LSL_DECLARATION

(*!
<Class
	Name               = "Reader"
	Revision           = "0.0"
	GUID               = "{D0355677-CEDD-4C39-B48C-ACE6993EA3C0}"
	RealtimeTask       = "false"
	CyclicTask         = "true"
	BackgroundTask     = "false"
	Sigmatek           = "false"
	OSInterface        = "false"
	HighPriority       = "false"
	Automatic          = "false"
	UpdateMode         = "Prescan"
	SharedCommandTable = "true"
	Objectsize         = "(442,120)">
	<Channels>
		<Server Name="NextOrderId" GUID="{707D90A7-CF6C-4ABB-8483-9263342CE600}" Visualized="true" Initialize="true" DefValue="1" WriteProtected="true" Retentive="false"/>
		<Server Name="OrderItemId" GUID="{482D3286-3F0E-433D-B15F-D6D91D663E60}" Visualized="true" Initialize="false" WriteProtected="true" Retentive="false"/>
		<Server Name="OrderItemVol" GUID="{7B45D0B6-5D77-4025-884D-292D98D83F1C}" Visualized="true" Initialize="false" WriteProtected="true" Retentive="false"/>
		<Server Name="OrderLoc" GUID="{5B2A6E64-B5C3-4E80-87B8-B1112CD7B08F}" Visualized="true" Initialize="false" WriteProtected="true" Retentive="false"/>
	</Channels>
	<Network Name="Reader">
		<!-- List of Components in this network -->
		<Components>
			<Object
				Name       = "_base"
				GUID       = "{91D67C6F-0367-4693-9CB1-6C21984F97D8}"
				Class      = "_TCPIP_SERVER"
				Position   = "(218,120)"
				Visualized = "true"
				Remotely   = "true">
				<Channels>
					<Server Name="sControl"/>
					<Server Name="SemaName01"/>
					<Server Name="SemaName02"/>
					<Server Name="sError"/>
					<Server Name="TaskName"/>
					<Client Name="cConfig"/>
					<Client Name="cMaxConnections" Value="0"/>
					<Client Name="co_MultiTask"/>
					<Client Name="co_SigCLib"/>
					<Client Name="co_StdLib"/>
					<Client Name="co_Task"/>
					<Client Name="cPort"/>
					<Client Name="cSizeOfTXBuffer"/>
					<Client Name="cTimeout"/>
				</Channels>
			</Object>
		</Components>
		<Comments>
		</Comments>
		<!-- List of Connections in this network -->
		<Connections>
			<Connection Source="this.sControl" Destination="_base.sControl" Vertices="(944,210),(772,210),"/>
			<Connection Source="this.sError" Destination="_base.sError" Vertices="(944,270),(772,270),"/>
			<Connection Source="this.TaskName" Destination="_base.TaskName" Vertices="(944,330),(772,330),"/>
			<Connection Source="this.SemaName01" Destination="_base.SemaName01" Vertices="(944,390),(772,390),"/>
			<Connection Source="this.SemaName02" Destination="_base.SemaName02" Vertices="(944,450),(772,450),"/>
			<Connection Source="_base.cPort" Destination="this.cPort" Vertices="(218,210),(38,210),"/>
		</Connections>
		<!-- Headerfiles -->
		<Options>
		</Options>
	</Network>
</Class>
*)
#pragma using _TCPIP_SERVER

Reader : CLASS
: _TCPIP_SERVER
	TYPE
#pragma pack(push, 1)
	  Item : STRUCT  //! <Type Public="true" Name="Item"/>
	    Id : DINT;
	    Vol : DINT;
	  END_STRUCT;
#pragma pack(pop)
	  Order : STRUCT  //! <Type Public="true" Name="Order"/>
	    Location : DINT;
	    Items : ARRAY [0..255] OF DINT;
	    Volumes : ARRAY [0..255] OF DINT;
	  END_STRUCT;
	END_TYPE
  //Servers:
	NextOrderId 	: SvrCh_UDINT;
	OrderLoc 	: SvrCh_UDINT;
	OrderItemId 	: SvrCh_UDINT;
	OrderItemVol 	: SvrCh_UDINT;
  //Clients:
  //Variables:
		Variable 	: ^void;
		dont 	: UDINT;
		dant 	: UDINT;
  //Functions:
				//! <Function Comment="This Method is called if Data is received (Overwrite it, to create a Request)" Name="Response"/>
	FUNCTION VIRTUAL GLOBAL Response
		VAR_INPUT
			pData 	: ^void;			//! <Variable Comment="Pointer to Data" Name="Response.pData"/>
			udSize 	: UDINT;			//! <Variable Comment="Size of Data" Name="Response.udSize"/>
			dSock 	: DINT;			//! <Variable Comment="Socketnumber" Name="Response.dSock"/>
		END_VAR;
	
	FUNCTION ByteArrToUDint
		VAR_INPUT
			pStart 	: ^void;
			StartIndex 	: DINT;
			Size 	: DINT;
		END_VAR
		VAR_OUTPUT
			udReturn 	: UDINT;
		END_VAR;
	
	FUNCTION UDintToByteArr
		VAR_INPUT
			udInput 	: UDINT;
			index 	: DINT;
		END_VAR
		VAR_OUTPUT
			usReturn 	: USINT;
		END_VAR;
  //Tables:
	FUNCTION @STD
		VAR_OUTPUT
			ret_code	: CONFSTATES;
		END_VAR;
	FUNCTION GLOBAL TAB @CT_;
END_CLASS;

//}}LSL_DECLARATION


FUNCTION GLOBAL TAB Reader::@CT_
0$UINT,
2#0100000000000010$UINT, //TY_READER
0$UINT, 0$UINT, (SIZEOF(::Reader))$UINT, 
4$UINT, 0$UINT, 0$UINT, 
TO_UDINT(1019694778), "Reader", //Class
TO_UDINT(26234884), "_TCPIP_SERVER", 1$UINT, 36$UINT, //Baseclass
//Servers:
(::Reader.NextOrderId.pMeth)$UINT, _CH_SVR$UINT, 2#0000000000000000$UINT, TO_UDINT(1111629890), "NextOrderId", 
(::Reader.OrderLoc.pMeth)$UINT, _CH_SVR$UINT, 2#0000000000000000$UINT, TO_UDINT(2780797080), "OrderLoc", 
(::Reader.OrderItemId.pMeth)$UINT, _CH_SVR$UINT, 2#0000000000000000$UINT, TO_UDINT(2765723994), "OrderItemId", 
(::Reader.OrderItemVol.pMeth)$UINT, _CH_SVR$UINT, 2#0000000000000000$UINT, TO_UDINT(3539779267), "OrderItemVol", 
//Clients:
END_FUNCTION


#define USER_CNT_Reader 10

TYPE
	_LSL_STD_VMETH	: STRUCT
			CmdTable	: CMDMETH;
			UserFcts	: ARRAY[0..USER_CNT_Reader] OF ^Void;
	END_STRUCT;
END_TYPE

FUNCTION Reader::@STD
	VAR_OUTPUT
		ret_code	: CONFSTATES;
	END_VAR
	VAR
		vmt	: _LSL_STD_VMETH;
		nCmdSize	: UINT;
	END_VAR

	ret_code	:= _TCPIP_SERVER::@STD();
	IF ret_code <> C_OK THEN
		RETURN;
	END_IF;
	nCmdSize		:= _TCPIP_SERVER::sControl.pMeth^.nCmds$UINT*SIZEOF(pVoid) + CMDMETH.Init;

	_memcpy((#vmt.CmdTable)$^USINT, _TCPIP_SERVER::sControl.pMeth, nCmdSize);
	vmt.CmdTable.nCmds		:= nSTDCMD + USER_CNT_Reader;
#pragma warning (disable : 74)
	vmt.UserFcts[1]		:= #Response();

#pragma warning (default : 74)
	_TCPIP_SERVER::sControl.pMeth		:= StoreCmd (pCmd := #vmt.CmdTable, SHARED);

	IF _TCPIP_SERVER::sControl.pMeth THEN
		ret_code	:= C_OK;
	ELSE
		ret_code	:= C_OUTOF_NEAR;
		RETURN;
	END_IF;

END_FUNCTION

//{{LSL_IMPLEMENTATION


FUNCTION VIRTUAL GLOBAL Reader::Response
	VAR_INPUT
		pData 	: ^void;
		udSize 	: UDINT;
		dSock 	: DINT;
	END_VAR
  VAR
  	fo : int;
    od : int;
    resp : ARRAY[1..128] OF USINT;
    i : dint;
    deadline : uDINT;
  END_VAR
  
  //Handshake
  fo := to_int(pData^);
  od := to_int((pData+1)^);
  
  if udSize = 2 then
    if fo = 0xf0 then
      if od = 0x0d then
        resp[1] := 0xdd;
        SendData(pData:=#resp[1], udSize:=1, dSock:=dSock, bDirect:=0);
      end_if;
    end_if;
  end_if;
  
  //Order
  TRACE("MAYBE");
  dont := udSize;
  dant := ByteArrToUDint(pStart:=pData, StartIndex:=0, Size:=64);
  if udSize = ByteArrToUDint(pStart:=pData, StartIndex:=0, Size:=64) then
    TRACE("YES");
    
    OrderLoc := ByteArrToUDint(pStart:=pData, StartIndex:=64, Size:=64);
    OrderItemId := ByteArrToUDint(pStart:=pData, StartIndex:=128, Size:=64);
    OrderItemVol := ByteArrToUDint(pStart:=pData, StartIndex:=192, Size:=64);
    //write entire content to file for managing
    
    
    for i := 1 to 64 do
      resp[i] := UDintToByteArr(udInput:=NextOrderId, index:=i-1);
    end_for;
    deadline := 100*60*5;
    for i := 65 to 128 do
      resp[i] := UDintToByteArr(udInput:=deadline, index:=i-65);
    end_for;

    SendData(pData:=#resp[1], udSize:=128, dSock:=dSock, bDirect:=0);
    NextOrderId += 1;
  end_if;


END_FUNCTION


FUNCTION Reader::ByteArrToUDint
	VAR_INPUT
		pStart 	: ^void;
		StartIndex 	: DINT;
		Size 	: DINT;
	END_VAR
	VAR_OUTPUT
		udReturn 	: UDINT;
	END_VAR
  VAR
  	x : UDINT;
    s : UINT;
    i : DINT;
    b : USINT;
    ux : UDINT;
  END_VAR
  
  x := 0;
  s := 0;
  
  for i := 0 to Size-1 do
    b := to_usint((pStart+i+StartIndex)^);
    if b < 0x80 then
      if i > 9 | i = 9 & b > 1 then
        //Overflow
        return;
      end_if;
      ux := (x or to_udint(b) shl s);
      exit;
    end_if;
    x := x or to_udint(b and 0x7f) shl s;
    s += 7;
  end_for;
  
  udReturn := ux;
END_FUNCTION


FUNCTION Reader::UDintToByteArr
	VAR_INPUT
		udInput 	: UDINT;
		index 	: DINT;
	END_VAR
	VAR_OUTPUT
		usReturn 	: USINT;
	END_VAR
  VAR
    x : UDINT;
    i : DINT;
		arr 	: ARRAY[0..63] of usint;
	END_VAR
  
    x := to_udint(udInput)
    i := 0;
    while x >= 0x80 do
      arr[i] := to_usint(x) or 0x80;
      x := x shr 7;
      i += 1;
    end_while;
    arr[i] := to_usint(x);
    
    usReturn := arr[index];
    
END_FUNCTION