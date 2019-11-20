package com.cellcard.wso2.logger;


//import static org.apache.axis2.context.MessageContext.TRANSPORT_HEADERS;

import java.io.ByteArrayOutputStream;
/*import java.io.IOException;
import java.io.StringReader;*/
/*import java.util.Map;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.axiom.om.OMElement;*/
import org.apache.axiom.om.OMException;
import org.apache.axiom.soap.SOAPBody;
import org.apache.axis2.AxisFault;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.synapse.MessageContext;
import org.apache.synapse.commons.json.JsonUtil;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.mediators.AbstractMediator;
//import org.w3c.dom.Document;
//import org.w3c.dom.Node;
/*import org.xml.sax.InputSource;
import org.xml.sax.SAXException;*/

import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public class LoggerMediator extends AbstractMediator { 

	//private static final String VERSION = "1.0";
    private static final Logger LOGGER = Logger.getLogger("LOGGER_V2");
    private static final Logger CARBON_LOGGER = Logger.getLogger(LoggerMediator.class);
    
    String vasAppFormate=null;
    String user =null;
    String accountId=null;
    String sourceAccountId=null;
    String targetAccountId=null;
    String saleId=null;
    String requestId=null;
    String serverHost=null;
    String clientIp=null;
    String methodName=null;
    String requestPlan=null;
    String serviceName=null;
    String channel=null;
    String purchaseFee=null;
    String uuid=null;
    String step=null;
    String nei=null;
    String api=null;
    String action=null;
    String result=null;
    String errorCode=null;
    String errorMsg=null;
    String transactionPlan=null;
    String tariffPlanCospid=null;
    String classOfServiceid=null;
    String oldPlan=null;
	//new items
    String logLevel=null;
    String contextPath=null;
    String sequenceName=null;
    String logPoint=null;
    String logMessage=null;
    String errorMessage=null;
    String errorReason=null;
    String messageBody=null;
    String originatingErrorMessage=null;
    String originatingErrorDetail=null;
    String customLog=null;
  	String logEntry=null;
  	String commonLog=null;
    

    @Override
    public boolean mediate(MessageContext context) {
        LOGGER.log(getLogLevel(context), buildLog(context));
        return true;
    }

    private Level getLogLevel(MessageContext context) {
        String logLevel = getSynapseValue("logLevel", context);

        if (null != logLevel && logLevel.trim().length() > 0)
            return Level.toLevel(logLevel);
        else
            return Level.INFO;
    }

    private String buildLog(MessageContext context) {
    	populateLogVariables(context);
    	
    	return formatLogMessage();
    	/*++++++++++++++++++++++++
        GsonBuilder gsonbuilder = new GsonBuilder();
        gsonbuilder.disableHtmlEscaping();
        Gson gson = gsonbuilder.create();

        String businessId = null;
        String capability = null;
        String correlationId = null;
        String clientMessageId = null;
        String messageType = null;
        String integrationReference = null;
        String integrationVersion = null;
++++++++++++++++*/
       //.apache.axis2.context.MessageContext axis2Context = getAxis2Context(context);

       // Document document;
        //Node node = null;

      //  try {
        //    document = getTransportValueAsXml(METADATA, axis2Context);
          //  node = document.getFirstChild();
        //} catch (IOException | NullPointerException | SAXException e) {
           // String sequence = (null != context) ? getSynapseValue("sequenceName", context) : "Unknown sequence";
         //   CARBON_LOGGER.warn(sequence + ": MetaData header was not found");
       // }

        //if (null != node) {
        /*++++
            correlationId = "Default";
            clientMessageId = "Default";
            businessId = "Default";
            capability = "Default";
            messageType = "Default";
            integrationReference = "Default";
            integrationVersion = "Default";+++++++*/
       // }
/*++++++++
        LogEntry logEntry = new LogEntry();

        logEntry.setPlatform("WSO2");
        logEntry.setLoggerVersion(VERSION);
        logEntry.setCorrelationId(correlationId);
        logEntry.setCapability(capability);

        if (integrationReference == null || "".equals(integrationReference)) {
            logEntry.setIntegrationReference(getSynapseValue("integrationReference", context));
        } else {
            logEntry.setIntegrationReference(integrationReference);
        }

        if (integrationVersion == null || "".equals(integrationVersion)) {
            logEntry.setIntegrationVersion(getSynapseValue("integrationVersion", context));
        } else {
            logEntry.setIntegrationVersion(integrationVersion);
        }

        
        logEntry.setMessageType(messageType);
        logEntry.setClientMessageId(clientMessageId);
        logEntry.setBusinessId(businessId);
        logEntry.setOrigin(getTransportValue("X-Forwarded-For", axis2Context));
        logEntry.setSequenceName(getSynapseValue("sequenceName", context));
        logEntry.setHttpMethod(getAxisValue("HTTP_METHOD", axis2Context));
        logEntry.setResource(getAxisValue("REST_URL_POSTFIX", axis2Context));
        logEntry.setMetaTransportInUrl(getAxisValue("TransportInURL", axis2Context));
        logEntry.setContentType(getAxisValue("messageType", axis2Context));
        logEntry.setContext(getSynapseValue("REST_API_CONTEXT", context));
        logEntry.setStatusCode(getAxisValueAsNumber("HTTP_SC", axis2Context));
        logEntry.setMessage(getSynapseValue("logMessage", context));
        logEntry.setUniVerseInfo(getSynapseValue("uniVerseInfo", context));
        logEntry.setRegistryPath(getSynapseValue("registryPath", context));
+++++++++++++*/
        

      
       // return gson.toJson(logEntry);
    }

    private Object getBody(MessageContext context, org.apache.axis2.context.MessageContext axis2Context) {
        if (isMessageType(getAxisValue("messageType", axis2Context), "application/json")) {
            return getJsonBody(context);
        } 
        if (isMessageType(getAxisValue("messageType", axis2Context), "application/xml")) {
            return getSoapBody(context);
        } 
        return null;
    }

    private boolean isMessageType(String messageType, String comparator) {
        return null != messageType && messageType.toLowerCase().contains(comparator);
    }
    
    private Object getSoapBody(MessageContext context) {
        try {
            SOAPBody body = context.getEnvelope().getBody();
            return body.getFirstElement().toString();
        } catch (RuntimeException e) { // NOSONAR
            String sequence = (null != context) ? getSynapseValue("sequenceName", context) : "unknown sequence";
            CARBON_LOGGER.warn("getSoapBody: Request made to log message body from " + sequence + " when no message body exists, returning null instead");
            return null;
        }
    }

    private String getJsonBody(MessageContext context) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {        
            JsonUtil.writeAsJson(context.getEnvelope().getBody().getFirstElement(), outputStream);            
        } catch (AxisFault | OMException e) { // NOSONAR
            String sequence = (null != context) ? getSynapseValue("sequenceName", context) : "unknown sequence";
            CARBON_LOGGER.warn("getJsonBody: Request made to log message body from " + sequence + " when no message body exists, returning null instead");
            return null;
        }
        
        JsonElement jsonElement = new Gson().fromJson(outputStream.toString(), JsonElement.class);
        if (jsonElement.isJsonNull()) {
            return null;
        } else {
            return new Gson().toJson(jsonElement);
        }
    }
    
    private boolean includeMessageBody(MessageContext context) {
        Level level = getLogLevel(context);
        return ((level.equals(Level.INFO) || level.equals(Level.TRACE) || level.equals(Level.WARN)) & isTrue(getSynapseValue("includeMessageBody", context)));
    }

    private boolean isUnhandledError(MessageContext context) {
        return isTrue(getSynapseValue("unhandledError", context));
    }

    private boolean includeErrorBody(MessageContext context) {
        Level level = getLogLevel(context);
        return ((level.equals(Level.DEBUG) || level.equals(Level.ERROR) || level.equals(Level.FATAL)) & isTrueOrEmpty(getSynapseValue("includeMessageBody", context)));
    }

    private boolean isTrue(String value) {
        return null != value && "true".equalsIgnoreCase(value);
    }

    private boolean isTrueOrEmpty(String value) {
        return null == value || value.isEmpty() || "true".equalsIgnoreCase(value);
    }

    private String getSynapseValue(String key, MessageContext context) {
        return (String) context.getProperty(key);
    }

    private String getAxisValue(String key, org.apache.axis2.context.MessageContext axis2Context) {
        return (String) axis2Context.getLocalProperty(key);
    }

   /* private Integer getAxisValueAsNumber(String key, org.apache.axis2.context.MessageContext axis2Context) {
        Object prop = axis2Context.getLocalProperty(key);
        try {
            if (prop instanceof String)
                return Integer.valueOf((String) prop);

            return (Integer) prop;

        } catch (NumberFormatException e) {
            CARBON_LOGGER.warn("getAxisValueAsNumber: Caught exception when attempting to parse HTTP_SC. Non-numeric value supplied.\n {}", e);
            return null;
        }
    }*/

   // private Document getTransportValueAsXml(String key, org.apache.axis2.context.MessageContext axis2Context) throws SAXException, IOException {
   //     return createDocumentBuilder().parse(toInputSource(getTransportValue(key, axis2Context)));
    //}

    /*private String getTransportValue(String key, org.apache.axis2.context.MessageContext axis2Context) {
        
    	Object transportValue = getTransportProperties(axis2Context).get(key);
    	if (transportValue instanceof OMElement) {
    	   return ((OMElement) transportValue).toString();
    	}
        return (String) transportValue;
    }*/

    /*private static DocumentBuilder createDocumentBuilder() {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    private static InputSource toInputSource(String xml) {
        return new InputSource(new StringReader(xml));
    }*/

   /* public static XPath createXPath() {
        XPathFactory factory = XPathFactory.newInstance();
        return factory.newXPath();
    }

    public static String query(String xPath, Node node) {
        try {
            return createXPath().compile(xPath).evaluate(node);
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }*/

    private org.apache.axis2.context.MessageContext getAxis2Context(MessageContext context) {
        return ((Axis2MessageContext) context).getAxis2MessageContext();
    }

    /*@SuppressWarnings("rawtypes")
    private Map getTransportProperties(org.apache.axis2.context.MessageContext axis2Context) {
        return (Map) axis2Context.getProperty(TRANSPORT_HEADERS);
    }*/
    
    private void populateLogVariables(MessageContext context) {

    	//LogEntry logEntry = new LogEntry();
    	/*logEntry.setVasAppFormate((String)context.getProperty("VASApp"));
    	logEntry.setUser((String)context.getProperty("user"));
    	logEntry.setAccountId((String)context.getProperty("account_id"));
    	logEntry.setSourceAccountId((String)context.getProperty("source_account_id"));
    	logEntry.setTargetAccountId((String)context.getProperty("target_account_id"));
    	logEntry.setSaleId((String)context.getProperty("sale_id"));
    	logEntry.setRequestId((String)context.getProperty("request_id"));
    	logEntry.setServerHost((String)context.getProperty("server_host"));
    	logEntry.setClientIp((String)context.getProperty("client_ip"));
    	logEntry.setMethodName((String)context.getProperty("method_name"));
    	logEntry.setRequestPlan((String)context.getProperty("request_plan"));
    	logEntry.setServiceName((String)context.getProperty("service_name"));
    	logEntry.setChannel((String)context.getProperty("channel"));
    	logEntry.setPurchaseFee((String)context.getProperty("purchase_fee"));
    	logEntry.setUuid((String)context.getProperty("uuid"));
    	logEntry.setStep((String)context.getProperty("step"));
    	logEntry.setNei((String)context.getProperty("nei"));
    	logEntry.setApi((String)context.getProperty("api"));
    	logEntry.setAction((String)context.getProperty("action"));
    	logEntry.setResult((String)context.getProperty("result"));
    	logEntry.setErrorCode((String)context.getProperty("error_code"));
    	logEntry.setErrorMsg((String)context.getProperty("error_msg"));
    	logEntry.setTransactionPlan((String)context.getProperty("transaction_id"));
    	logEntry.setTariffPlanCospid((String)context.getProperty("tariff_plan_cospid"));
    	logEntry.setClass_of_serviceid((String)context.getProperty("class_of_serviceid"));
        logEntry.setOld_plan((String)context.getProperty("old_plan"));
        //new entries
    	logEntry.setLogLevel((String)context.getProperty("log_level"));
    	logEntry.setContextPath((String)context.getProperty("context_path"));
    	logEntry.setSequenceName((String)context.getProperty("sequence_name"));
    	logEntry.setLogMessage((String)context.getProperty("log_message"));
    	logEntry.setLogPoint((String)context.getProperty("log_point"));
    	logEntry.setErrorMessage((String)context.getProperty("error_message"));
    	logEntry.setMessageBody((String)context.getProperty("message_body"));
    	
    	*/
    	org.apache.axis2.context.MessageContext axis2Context = getAxis2Context(context);
    	

    	  vasAppFormate=getSynapseValue("VASApp", context);
          user =getSynapseValue("user", context);
          accountId=getSynapseValue("account_id", context);
          sourceAccountId=getSynapseValue("source_account_id", context);
          targetAccountId=getSynapseValue("target_account_id", context);
          saleId=getSynapseValue("sale_id", context);
          requestId=getSynapseValue("request_id", context);
          serverHost=getSynapseValue("server_host", context);
          clientIp=getSynapseValue("client_ip", context);
          methodName=getSynapseValue("method_name", context);
          requestPlan=getSynapseValue("request_plan", context);
          serviceName=getSynapseValue("service_name", context);
          channel=getSynapseValue("channel", context);
          purchaseFee=getSynapseValue("purchase_fee", context);
          uuid=getSynapseValue("uuid", context);
          step=getSynapseValue("step", context);
          nei=getSynapseValue("nei", context);
          api=getSynapseValue("api", context);
          action=getSynapseValue("action", context);
          result=getSynapseValue("result", context);
          errorCode=getSynapseValue("error_code", context);
          errorMsg=getSynapseValue("error_msg", context);
          transactionPlan=getSynapseValue("transaction_id", context);
          tariffPlanCospid=getSynapseValue("tariff_plan_cospid", context);
          classOfServiceid=getSynapseValue("class_of_serviceid", context);
          oldPlan=getSynapseValue("old_plan", context);
      	//new items
          logLevel=getSynapseValue("log_level", context);
          contextPath=getSynapseValue("context_path", context);
          sequenceName=getSynapseValue("sequence_name", context);
          logPoint=getSynapseValue("log_message", context);
          logMessage=getSynapseValue("log_point", context);
          errorMessage=getSynapseValue("error_message", context);
          //messageBody=getSynapseValue("message_body", context);
          errorReason=getSynapseValue("error_reason", context);
          
          //StringBuilder logEntryBuilder=new StringBuilder();
      	
      			
      
      
          
          if (includeMessageBody(context) | includeErrorBody(context)) {
          	messageBody=getBody(context, axis2Context).toString();
          	customLog=contextPath+","+sequenceName+","+logPoint+","+logMessage+","+messageBody;
          }

          if (includeErrorBody(context)) {
             errorReason=getSynapseValue("errorReason", context);
              //logEntry.setRequestBody(getSynapseValue("requestBody", context));
             customLog=contextPath+" , "+sequenceName+" , "+logPoint+" , "+logMessage+" , "+errorReason;
              if (isUnhandledError(context)) {
                  originatingErrorMessage=getSynapseValue("ERROR_MESSAGE", context);
                  originatingErrorDetail=getSynapseValue("ERROR_DETAIL", context);
                  customLog=contextPath+" , "+sequenceName+" , "+logPoint+" , "+logMessage+" , "+errorReason+" , "+originatingErrorMessage+" , "+originatingErrorDetail;
              }
          }


        
    }
    
    private String formatLogMessage() {
	 commonLog=", VASApp = "+vasAppFormate+" , user = "+user+" , account_id = "+accountId+" , source_account_id = "+sourceAccountId+" , target_account_id = "+targetAccountId+" , sale_id = "+saleId+" , request_id = "+requestId+" , server_host = "+serverHost+" , client_ip = "+
          		
      	clientIp+" , method_name = "+methodName+" , request_plan = "+requestPlan+" , service_name = "+serviceName+" , channel = "+channel+" , purchase_fee = "+purchaseFee+" , uuid = "+uuid+
      	" , step = "+step+" , nei = "+nei+" , api = "+api+" , action = "+
      	action+" , result = "+result+" , error_code = "+errorCode+" , error_msg = "+errorMsg+" , transaction_id = "+transactionPlan+" , tariff_plan_cospid = "+tariffPlanCospid+" , class_of_serviceid = "+classOfServiceid+" , old_plan = "+oldPlan;

    	return customLog+commonLog;
    
    }
}


