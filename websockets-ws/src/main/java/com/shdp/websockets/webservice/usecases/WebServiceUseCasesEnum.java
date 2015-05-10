package com.shdp.websockets.webservice.usecases;

import com.shdp.websockets.webservice.ProcessModeEnum;

public enum WebServiceUseCasesEnum {

	
	makeOneRunOneWayWS1(1,"makeOneRunOneWayWS1", ProcessModeEnum.OneWay,true) ,  		//OneWay
	makeOneASyncnrwWS1 (2,"makeOneASyncnrwWS1",ProcessModeEnum.Asynchronous,true),		//Asynchronous
	makeOneSyncWS1 (3,"makeOneSyncWS1", ProcessModeEnum.Synchronous,true) ,			//Synchronous
	makeOneASyncrEmpWS (4,"makeOneASyncrEmpWS", ProcessModeEnum.Asynchronous,true),	//Asynchronous
	makeOneSyncEmpWS (5,"makeOneSyncEmpWS",ProcessModeEnum.Synchronous,true) ,			//Synchronous
	makeOneSyncRtrvFileWS(6,"makeOneSyncRtrvFileWS",ProcessModeEnum.Synchronous,false) 	;		//Synchronous ServiceAgreedRtrvFileSyncWS
	
	private int wsId ;		   
	private String  wsName ;
	private Boolean doIt = false ;
	private ProcessModeEnum  processMode = ProcessModeEnum.Synchronous;
	
	private WebServiceUseCasesEnum (int id,String wsNamei,ProcessModeEnum processModei,  Boolean doBoolean )
	{
		wsId=id ;
		wsName = wsNamei ;
		doIt=doBoolean ;
		processMode= processModei ;
	}
	
	public String getWsName() {
		return wsName;
	}

	public void setWsName(String wsName) {
		this.wsName = wsName;
	}

	

	public int getWsId() {
		return wsId;
	}

	public Boolean getDoIt() {
		return doIt;
	}

	public ProcessModeEnum getProcessMode() {
		return processMode;
	}

}
