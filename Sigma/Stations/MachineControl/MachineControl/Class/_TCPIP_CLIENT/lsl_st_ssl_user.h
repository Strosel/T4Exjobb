(*
 * lsl_st_ssl_user.h
 *
 *  Created on: Jan 26, 2017
 *      Author: krimar
 *)

//HFILTER:1
#ifndef _LSL_ST_SSL_USER_H
//HFILTER:
//HFILTER:2
#pragma once
//HFILTER:
#define _LSL_ST_SSL_USER_H

// Interface
#define INTERFACE_SSL_USER      "SSL_USER"

#define SSL_NOT_READY_ERROR			-2000
#define SSL_NOMEM_ERROR				-2001
#define SSL_MAXCONNECTIONS_ERROR	-2002
#define SSL_CONTEXT_ERROR			-2003
#define SSL_CONNECTION_ERROR		-2004
#define SSL_PARAMETER_ERROR			-2005
#define SSL_MISSING_PRIV_KEY_ERROR	-2006
#define SSL_INTERNAL_ERROR			-2007
#define SSL_WRITE_ERROR				-2008
#define SSL_READ_ERROR				-2009
#define SSL_INVALID_CONN_NUM_ERROR  -2010
#define SSL_INVALID_CONNSTATE_ERROR	-2011
#define SSL_IN_PROGRESS_ERROR		-2012
#define SSL_TCP_CONNECTION_ERROR	-2013
#define SSL_TIMEOUT_ERROR			-2014

TYPE
	LSL_SSL_USER    :STRUCT
		udVersion                   : UDINT;
		udSize                      : UDINT;
		ssl_user_create_conn_struct : pVoid;
		ssl_user_connect            : pVoid;
		ssl_user_free_conn          : pVoid;
		ssl_user_write              : pVoid;
		ssl_user_read               : pVoid;
(*
		tcp_user_connect            : pVoid;
		tcp_user_listen             : pVoid;
		tcp_user_accept             : pVoid;
		tcp_user_shutdown           : pVoid;
*)
		END_STRUCT;
END_TYPE

FUNCTION GLOBAL __cdecl P_SSL_USER_CREATE_CONN_STRUCT
VAR_INPUT
	ca_cert_pem_filename   : ^CHAR;
	client_cert_chain_pem_filename   : ^CHAR;
	client_cert_key_pem_filename   : ^CHAR;
	key_decrypt_pwd   : ^CHAR;
	server_name   : ^CHAR;
END_VAR
VAR_OUTPUT
	retval      : DINT;
END_VAR;

FUNCTION GLOBAL __cdecl P_SSL_USER_CONNECT
VAR_INPUT
	connection  : DINT;
	server_name_or_ip_addr   : ^CHAR;
	server_port   : UDINT;
	timeout_ms	: UDINT;
END_VAR
VAR_OUTPUT
	retval      : DINT;
END_VAR;

FUNCTION GLOBAL __cdecl P_SSL_USER_FREE_CONN
VAR_INPUT
	connection  : DINT;
END_VAR
VAR_OUTPUT
	retval      : DINT;
END_VAR;

FUNCTION GLOBAL __cdecl P_SSL_USER_WRITE
VAR_INPUT
	connection  : DINT;
	buffer      :^CHAR;
	num_bytes_to_write      : DINT;
	timeout_ms  : UDINT;
END_VAR
VAR_OUTPUT
	retval      : DINT;
END_VAR;

FUNCTION GLOBAL __cdecl P_SSL_USER_READ
VAR_INPUT
	connection  : DINT;
	buffer      :^CHAR;
	num_bytes_to_read      : DINT;
	timeout_ms  : UDINT;
END_VAR
VAR_OUTPUT
	retval      : DINT;
END_VAR;

(*
FUNCTION GLOBAL __cdecl P_TCP_USER_CONNECT
VAR_INPUT
	socket      : DINT;
	localport   : UDINT;
	IPAddress   : ^CHAR;
	port        : UDINT;
	timeout_ms  : UDINT;
END_VAR
VAR_OUTPUT
	retval      : DINT;
END_VAR;

FUNCTION GLOBAL __cdecl P_TCP_USER_LISTEN
VAR_INPUT
	socket      : DINT;
	localport   : UDINT;
	backlogsize : UDINT;
END_VAR
VAR_OUTPUT
	retval      : DINT;
END_VAR;

FUNCTION GLOBAL __cdecl P_TCP_USER_ACCEPT
VAR_INPUT
	socket      : DINT;
	timeout_ms  : UDINT;
END_VAR
VAR_OUTPUT
	retval      : DINT;
END_VAR;

FUNCTION GLOBAL __cdecl P_TCP_USER_RECV
VAR_INPUT
	socket      : DINT;
	buffer      :^CHAR;
	buflen      : UDINT;
	flags       : UDINT;
	timeout_ms  : UDINT;
END_VAR
VAR_OUTPUT
	retval      : DINT;
END_VAR;

FUNCTION GLOBAL __cdecl P_TCP_USER_SHUTDOWN
VAR_INPUT
	socket      : DINT;
	how         : UDINT;
END_VAR
VAR_OUTPUT
	retval      : DINT;
END_VAR;

*)

#define OS_SSL_USER_VERSION lsl_ssl_user^.udVersion

#define OS_SSL_USER_CREATE_CONN_STRUCT(p1, p2, p3, p4, p5) lsl_ssl_user^.ssl_user_create_conn_struct $ P_SSL_USER_CREATE_CONN_STRUCT(p1, p2, p3, p4, p5)
#define OS_SSL_USER_CONNECT(p1, p2, p3, p4) lsl_ssl_user^.ssl_user_connect $ P_SSL_USER_CONNECT(p1, p2, p3, p4)
#define OS_SSL_USER_FREE_CONN(p1) lsl_ssl_user^.ssl_user_free_conn $ P_SSL_USER_FREE_CONN(p1)
#define OS_SSL_USER_WRITE(p1, p2, p3, p4) lsl_ssl_user^.ssl_user_write $ P_SSL_USER_WRITE(p1, p2, p3, p4)
#define OS_SSL_USER_READ(p1, p2, p3, p4) lsl_ssl_user^.ssl_user_read $ P_SSL_USER_READ(p1, p2, p3, p4)
(*
#define OS_SSL_USER_CONNECT(p1, p2, p3, p4, p5) lsl_ssl_user^.tcp_user_connect $ P_SSL_USER_CONNECT(p1, p2, p3, p4, p5)
#define OS_SSL_USER_LISTEN(p1, p2, p3) lsl_ssl_user^.tcp_user_listen $ P_SSL_USER_LISTEN(p1, p2, p3)
#define OS_SSL_USER_ACCEPT(p1, p2) lsl_ssl_user^.tcp_user_accept $ P_SSL_USER_ACCEPT(p1, p2)
#define OS_SSL_USER_SHUTDOWN(p1, p2) lsl_ssl_user^.tcp_user_shutdown $ P_SSL_USER_NREAD_AVAILABLE(p1)
*)

//HFILTER:1
#endif //ifndef _LSL_ST_SSL_USER_H
//HFILTER:

