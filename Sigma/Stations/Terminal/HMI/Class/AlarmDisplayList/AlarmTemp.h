// Eigenschaften des UDCs
//----------------------------------------------------------------------------------------------------------------------------
#define   AlarmTemp_VariableNo                6           // Anzahl der Variablen, die dem UDC mitgegeben werden

// Konfigurationen der Zahlen
//------------------------------------------------------
#define   AlarmTemp_ConfigAlarmNo             16#105
#define   AlarmTemp_ConfigCycleNo             16#800


// Eigenschaften für die Texte
//------------------------------------------------------
#define   AlarmTemp_AttrStandard	            T_SOLID OR T_COPY OR T_PROP
#define   AlarmTemp_AttrTimeOn  	            T_SOLID OR T_COPY OR T_PROP OR T_CENTERBOUND OR T_UPBOUND
#define   AlarmTemp_AttrTimeOff 	            T_SOLID OR T_COPY OR T_PROP OR T_CENTERBOUND OR T_DOWNBOUND
#define   AlarmTemp_AttrAlarmNo		            T_CENTERBOUND OR T_MIDBOUND
#define   AlarmTemp_AttrTime 		              T_CENTERBOUND OR T_MIDBOUND
#define   AlarmTemp_AttrCycleNo		            T_CENTERBOUND OR T_MIDBOUND
#define   AlarmTemp_AttrText	                T_SOLID OR T_COPY OR T_PROP OR T_LEFTBOUND OR T_MIDBOUND

#define   AlarmTemp_SelectedColor             WHITE



// Einstellungen zum Bestimmen der Texte aus LSE
//----------------------------------------------------------------------------------------------------------------------------
#define   AlarmTemp_Textlist                  "AlarmTemporary"

#define   AlarmTemp_TextAlarmNo               1
#define   AlarmTemp_TextAlarmTimeOn           2
#define   AlarmTemp_TextAlarmTimeOff          3
#define   AlarmTemp_TextParameter             4
#define   AlarmTemp_TextDescription           5




