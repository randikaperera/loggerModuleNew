package com.cellcard.wso2.logger;

import java.io.ByteArrayOutputStream;
import org.apache.axiom.om.OMException;
import org.apache.axiom.soap.SOAPBody;
import org.apache.axis2.AxisFault;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.synapse.MessageContext;
import org.apache.synapse.commons.json.JsonUtil;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class LoggerMediator extends AbstractMediator {

	private static final Logger LOGGER = Logger.getLogger("LOGGER_CELLCARD");
	private static final Logger CARBON_LOGGER = Logger.getLogger(LoggerMediator.class);

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
		org.apache.axis2.context.MessageContext axis2Context = getAxis2Context(context);
		String dynamicLogString = new String();
		LogFields logFields = new LogFields();
		
		logFields.setVasAppFormate(getSynapseValue("VASApp", context));
		logFields.setUser(getSynapseValue("user", context));
		logFields.setAccountId(getSynapseValue("account_id", context));
		logFields.setSourceAccountId(getSynapseValue("source_account_id", context));
		logFields.setTargetAccountId(getSynapseValue("target_account_id", context));
		logFields.setSaleId(getSynapseValue("sale_id", context));
		logFields.setRequestId(getSynapseValue("request_id", context));
		logFields.setServerHost(getSynapseValue("server_host", context));
		logFields.setClientIp(getSynapseValue("client_ip", context));
		logFields.setMethodName(getSynapseValue("method_name", context));
		logFields.setRequestPlan(getSynapseValue("request_plan", context));
		logFields.setServiceName(getSynapseValue("service_name", context));
		logFields.setChannel(getSynapseValue("channel", context));
		logFields.setPurchaseFee(getSynapseValue("purchase_fee", context));
		logFields.setUuid(getSynapseValue("uuid", context));
		logFields.setStep(getSynapseValue("step", context));
		logFields.setNei(getSynapseValue("nei", context));
		logFields.setApi(getSynapseValue("api", context));
		logFields.setAction(getSynapseValue("action", context));
		logFields.setResult(getSynapseValue("result", context));
		logFields.setErrorCode(getSynapseValue("error_code", context));
		logFields.setErrorMsg(getSynapseValue("error_msg", context));
		logFields.setTransactionPlan(getSynapseValue("transaction_id", context));
		logFields.setTransactionPlan(getSynapseValue("tariff_plan_cospid", context));
		logFields.setClassOfServiceid(getSynapseValue("class_of_serviceid", context));
		logFields.setOldPlan(getSynapseValue("old_plan", context));
		// new items
		logFields.setLogLevel(getSynapseValue("log_level", context));
		logFields.setContextPath(getSynapseValue("REST_API_CONTEXT", context));
		logFields.setSequenceName(getSynapseValue("sequence_name", context));
		logFields.setLogPoint(getSynapseValue("log_message", context));
		logFields.setLogMessage(getSynapseValue("log_point", context));
		logFields.setErrorMessage(getSynapseValue("error_message", context));
		logFields.setErrorReason(getSynapseValue("error_reason", context));

		if (includeMessageBody(context) | includeErrorBody(context)) {
			logFields.setMessageBody(getBody(context, axis2Context).toString());
			dynamicLogString = logFields.getContextPath() + "," + logFields.getSequenceName() + ","
					+ logFields.getLogPoint() + "," + logFields.getLogMessage() + "," + logFields.getMessageBody();

		}

		if (includeErrorBody(context)) {
			logFields.setErrorReason(getSynapseValue("errorReason", context));
			dynamicLogString = logFields.getContextPath() + "," + logFields.getSequenceName() + ","
					+ logFields.getLogPoint() + "," + logFields.getLogMessage() + "," + logFields.getMessageBody() + ","
					+ logFields.getErrorReason();

			if (isUnhandledError(context)) {
				logFields.setOriginatingErrorMessage(getSynapseValue("ERROR_MESSAGE", context));
				logFields.setOriginatingErrorDetail(getSynapseValue("ERROR_DETAIL", context));
				
				dynamicLogString = logFields.getContextPath() + "," + logFields.getSequenceName() + ","
						+ logFields.getLogPoint() + "," + logFields.getLogMessage() + "," + logFields.getMessageBody()
						+ "," + logFields.getErrorReason() + "," + logFields.getOriginatingErrorMessage() + ","
						+ logFields.getOriginatingErrorDetail();
			}
		}
		return dynamicLogString+", VASApp = " + logFields.getVasAppFormate() + " , user = " + logFields.getUser() + " , account_id = " + logFields.getAccountId()
		+ " , source_account_id = " + logFields.getSourceAccountId() + " , target_account_id = " + logFields.getTargetAccountId()
		+ " , sale_id = " + logFields.getSaleId() + " , request_id = " + logFields.getRequestId() + " , server_host = " + logFields.getServerHost()
		+ " , client_ip = " + logFields.getClientIp() + " , method_name = " + logFields.getMethodName() + " , request_plan = " + logFields.getRequestPlan()
		+ " , service_name = " + logFields.getServiceName() + " , channel = " + logFields.getChannel() + " , purchase_fee = " + logFields.getPurchaseFee() + " , uuid = " 
		+ logFields.getUuid() + " , step = " + logFields.getStep() + " , nei = " + logFields.getNei() + " , api = " + logFields.getApi() + " , action = " + logFields.getAction() 
		+ " , result = " + logFields.getResult() + " , error_code = " + logFields.getErrorCode() + " , error_msg = " + logFields.getErrorMsg() + " , transaction_id = "
		+ logFields.getTransactionPlan() + " , tariff_plan_cospid = " + logFields.getTariffPlanCospid() + " , class_of_serviceid = "
		+ logFields.getClassOfServiceid() + " , old_plan = " + logFields.getOldPlan();

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
		} catch (RuntimeException e) { 
			String sequence = (null != context) ? getSynapseValue("sequenceName", context) : "unknown sequence";
			CARBON_LOGGER.warn("getSoapBody: Request made to log message body from " + sequence
					+ " when no message body exists, returning null instead");
			return null;
		}
	}

	private String getJsonBody(MessageContext context) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			JsonUtil.writeAsJson(context.getEnvelope().getBody().getFirstElement(), outputStream);
		} catch (AxisFault | OMException e) {
			String sequence = (null != context) ? getSynapseValue("sequenceName", context) : "unknown sequence";
			CARBON_LOGGER.warn("getJsonBody: Request made to log message body from " + sequence
					+ " when no message body exists, returning null instead");
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
		return ((level.equals(Level.INFO) || level.equals(Level.TRACE) || level.equals(Level.WARN))
				& isTrue(getSynapseValue("includeMessageBody", context)));
	}

	private boolean isUnhandledError(MessageContext context) {
		return isTrue(getSynapseValue("unhandledError", context));
	}

	private boolean includeErrorBody(MessageContext context) {
		Level level = getLogLevel(context);
		return ((level.equals(Level.DEBUG) || level.equals(Level.ERROR) || level.equals(Level.FATAL))
				& isTrueOrEmpty(getSynapseValue("includeMessageBody", context)));
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

	private org.apache.axis2.context.MessageContext getAxis2Context(MessageContext context) {
		return ((Axis2MessageContext) context).getAxis2MessageContext();
	}
}
