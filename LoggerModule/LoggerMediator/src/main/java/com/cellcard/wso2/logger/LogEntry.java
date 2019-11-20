package com.cellcard.wso2.logger;

public class LogEntry {
	
	String appName=null;
	//String result=null;
	String actionResult=null;
    //String errorCode=null;
    String errorMsg=null;
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
    String errorMessage=null;
    String transactionPlan=null;
    String tariffPlanCospid=null;
    String class_of_serviceid=null;
    String old_plan=null;
	//new items
    String logLevel=null;
    String contextPath=null;
    String sequenceName=null;
    String logPoint=null;
    String logMessage=null;
    String messageBody=null;
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getActionResult() {
		return actionResult;
	}
	public void setActionResult(String actionResult) {
		this.actionResult = actionResult;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getVasAppFormate() {
		return vasAppFormate;
	}
	public void setVasAppFormate(String vasAppFormate) {
		this.vasAppFormate = vasAppFormate;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getSourceAccountId() {
		return sourceAccountId;
	}
	public void setSourceAccountId(String sourceAccountId) {
		this.sourceAccountId = sourceAccountId;
	}
	public String getTargetAccountId() {
		return targetAccountId;
	}
	public void setTargetAccountId(String targetAccountId) {
		this.targetAccountId = targetAccountId;
	}
	public String getSaleId() {
		return saleId;
	}
	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getServerHost() {
		return serverHost;
	}
	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getRequestPlan() {
		return requestPlan;
	}
	public void setRequestPlan(String requestPlan) {
		this.requestPlan = requestPlan;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getPurchaseFee() {
		return purchaseFee;
	}
	public void setPurchaseFee(String purchaseFee) {
		this.purchaseFee = purchaseFee;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public String getNei() {
		return nei;
	}
	public void setNei(String nei) {
		this.nei = nei;
	}
	public String getApi() {
		return api;
	}
	public void setApi(String api) {
		this.api = api;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getTransactionPlan() {
		return transactionPlan;
	}
	public void setTransactionPlan(String transactionPlan) {
		this.transactionPlan = transactionPlan;
	}
	public String getTariffPlanCospid() {
		return tariffPlanCospid;
	}
	public void setTariffPlanCospid(String tariffPlanCospid) {
		this.tariffPlanCospid = tariffPlanCospid;
	}
	public String getClass_of_serviceid() {
		return class_of_serviceid;
	}
	public void setClass_of_serviceid(String class_of_serviceid) {
		this.class_of_serviceid = class_of_serviceid;
	}
	public String getOld_plan() {
		return old_plan;
	}
	public void setOld_plan(String old_plan) {
		this.old_plan = old_plan;
	}
	public String getLogLevel() {
		return logLevel;
	}
	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}
	public String getContextPath() {
		return contextPath;
	}
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	public String getSequenceName() {
		return sequenceName;
	}
	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}
	public String getLogPoint() {
		return logPoint;
	}
	public void setLogPoint(String logPoint) {
		this.logPoint = logPoint;
	}
	public String getLogMessage() {
		return logMessage;
	}
	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}
	public String getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
    
    

   /* private String platform;
    private String loggerVersion;
    private String correlationId;
    private String capability;
    private String integrationReference;
    private String integrationVersion;
    private String messageType;
    private String clientMessageId;
    private String businessId;
    private String origin;
    private String sequenceName;
    private String httpMethod;
    private String resource;
    private String metaTransportInUrl;
    private String contentType;
    private String context;
    private Integer statusCode;
    private String message;
    private String registryPath;
    private String errorReason;
    private String requestBody;
    private Object body;
    private String originatingErrorMessage;
    private String originatingErrorDetail;
    private String uniVerseInfo;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getLoggerVersion() {
        return loggerVersion;
    }

    public void setLoggerVersion(String loggerVersion) {
        this.loggerVersion = loggerVersion;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getCapability() {
        return capability;
    }

    public void setCapability(String capability) {
        this.capability = capability;
    }

    public String getIntegrationReference() {
        return integrationReference;
    }

    public void setIntegrationReference(String integrationReference) {
        this.integrationReference = integrationReference;
    }

    public String getIntegrationVersion() {
        return integrationVersion;
    }

    public void setIntegrationVersion(String integrationVersion) {
        this.integrationVersion = integrationVersion;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getClientMessageId() {
        return clientMessageId;
    }

    public void setClientMessageId(String clientMessageId) {
        this.clientMessageId = clientMessageId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getMetaTransportInUrl() {
        return metaTransportInUrl;
    }

    public void setMetaTransportInUrl(String metaTransportInUrl) {
        this.metaTransportInUrl = metaTransportInUrl;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRegistryPath() {
        return registryPath;
    }

    public void setRegistryPath(String registryPath) {
        this.registryPath = registryPath;
    }
    
    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public Object getBody() {
        return this.body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public void setOriginatingErrorMessage(String originatingErrorMessage) {
        this.originatingErrorMessage = originatingErrorMessage;
    }

    public void setOriginatingErrorDetail(String originatingErrorDetail) {
        this.originatingErrorDetail = originatingErrorDetail;
    }

    public void setUniVerseInfo(String uniVerseInfo) {
        this.uniVerseInfo = uniVerseInfo;
    }*/
	
	
	
	

}