Webserweb over WebSockets protocol
WebSockets as a transport protocol for WebServices offers a better alternative compared to SOAP and REST.
The Client server can exchange the messages at high frequency and with low latency over the websockets. 
This example shows generic framework WebServices Java implementation using the websockets.
Components:
A typical WebService include: POJO Request, POJO Response, Server Processor Component, and Client Processor Component. 
Any webservice should implements the "ServiceAgreementIF" interface which include the definitions of these Components.
Processing Modes: Synchronous, Asynchronous or One-way. 
	Synchronous: Send Request, and the caller thread forced to Wait the Response[block]. The Return Response will resume the caller thread  via the call back mechanism.
	 Implements ServiceAgreementSyncIF. See ServiceAgreedWS1Sync as atemplate
	Asynchronous: basically,  non-blocking event-driven model. Send, and let the Response make the callback for the Instance Object [processClientResp] without blocking. The calling thread will be free after calling the webservice.
	 The Response will be processed by a new thread grabed from the thread pool.  
	 Implements ServiceAgreementASyncrsIF. See ServiceAgreedWS1Asyncr as a template.
	One-way: Send and forget It.
	 The caller is not inrested in reposne or the server execution  works as event processor. 
	 Implements ServiceAgreementOneWayIF. See ServiceAgrredWS1OneWay as a template.
Optimum Usage for Client Sessions:
The Client Component of the framework, maps and stores any new Client created Session with its associated URL.
Any new Client Request will use the same Client Session as long as the Given URL found in the storage mapper.
This means for the same URL one Session in client side will handle all the webService using this URL.
Multiple WebServices can use the same URL and share the same session from Client Side as well as from the Server Side.
The Use Cases inclkudes different/multiple webServices targting the same URL.
Annotations:
The Classes-Components for processing the Server side webServices are defined in the "ServiceServerAnnotation" and should be attached to
the "ServiceAgreementIF" Implementations. The Use Cases Examples Illustrate the typical use for these annotations within the implemention of "ServiceAgreementIF".

Thread Pool:
Asynchronous mode, releases the client caller thread [No-Blocking] when the client initiates the service. 
Upon receiving the Response from the Server, the frame ork assigned a new thread to resume the client working with the incoming response.
The Thread pool resources are used to resume the client response work for Asynchronous mode is defined in Instance "threadPoolExecutorCli". 
The Thread Pool "threadPoolExecutorCli" is configured in "WiredInstancesCli" Instance.

Json Text over the network: 
Json Jackson Library is used to convert the Request and the Response from Text to Object and vice versa. 
The Default Request and Response Json conversion can be controlled/Override through the Annotation provided in the Request and Response classes.

Environment:
Eclipse (Luna Service Release 2 (4.4.2)), jdk8_u60ae, Reference Implementation tyrus(Client API), jackson-mapper, and Jboss/WildFly-8.2.0.Final
The pom includes the versions of these Libraries.

Tested Cases:
WildFly/Client to WildFly/Server and Standalone Java Client t Application to WildFly/Server.

Properties:
Java logging API is in place to capture the in/out Messages for both sides.  The Log Names are specified in: 
	"/websockets-ws/src/main/resources/cliUseCases.properties" --  Client Logging
	"/websockets-ws/src/main/resources/wsws.properties"        --  Server logging
Please, take a moment to adjust these properties prior to any test/run.

Use Cases:
Use Cases WebServices ws1 [com.shdp.websockets.webservice.usecases.ws1] includes a templates for the 3 modes [Synchronous, Asynchronous and One-way]:
"UseCases.java" [com.shdp.websockets.webservice.usecase] is the main client caller for all cases.

Employee WebServices Use Case Step by Step [com.shdp.websockets.webservice.usecases.employeews]:
- Client needs the Employees List along with their Annual Income
- The Domain Object is defined in "Employee.java"
- The Client Request is defined in "ReqEmp.java" 
- The Server processing Request Class is defined in "ProcessServerReqEmp.java" [Define in the annoation]
- The Server Response back to Client is defined in "RespEmp.java" 
- The Response Json text, for security response should not display any Income as a plain readable numbers
- The Response Annotation inject a new Json Converter "jsonConverter=EmpRespJsonConvert.class" to Supersede the default converter
- The EmpRespJsonConvert class defines convertToPOJO and convertToJsonStr as well. It Removes the plain income numbers and replce with base64 for sake of Converter Override Demo.
- The Client Processing Response Class is defined in "ProcessClientRespEmp.java". This class implements 
- Let us preferred the Asynchronous mode for this WebServices.
- All the elements of the agreement glued together in "ServiceAgreedEmpASyncr.java" wich implements "ServiceAgreementASyncrsIF"
- The Annoation "ServiceServerAnnotation" is injected in ServiceAgreedEmpASyncr. 
- The design is uing annoation [Req,Resp,and Server-Procesor] to process the server side from the service.
- The "UseCases.java" invokes the Client Request using a new instance for ServiceAgreedEmpASyncr [method makeOneASyncrEmpWS in UseCases Component]
- For Stress and Conureency purposes, another thread pool is defined in the "WiredInstancesCli" with Instance name=threadPoolExecutorTstCli
- The "threadPoolExecutorTstCli" is used by Client side to push the Conureency level for testing purpose
- After any run, Look at the Client and Server logs to verify the Sessions vs URL, and Request Response corrleation via the identfier "reqRespLinkStrId". 

If you have Question please don’t hesitated to send e-mail to "safwatdeif@gmail.com"
Thanks,
Safwat Hanna Deif


 

 