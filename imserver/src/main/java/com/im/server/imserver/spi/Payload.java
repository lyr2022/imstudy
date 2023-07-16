package com.im.server.imserver.spi;

import java.util.Map;

/**
 * 数据内容
 * 
 * @author Administrator
 *
 */
public class Payload {

	private Header head = null;
	private Map<String, Object> body = null;

	public Header getHead() {
		return head;
	}

	public void setHead(Header head) {
		this.head = head;
	}

	public Map<String, Object> getBody() {
		return body;
	}

	public void setBody(Map<String, Object> body) {
		this.body = body;
	}

	public static class Header {
		private String id;
		private String type;
		private String from;
		private String biz;
		private String to;
		private String date;
		private String priority;
		private String fappCode;//消息发送者appCode
        private String tappCode;//消息接收者appCode

		private String ver;
		private String appVer;
		private String appCode;
		private String userId;
		private String sessionId;
		private String deviceType;

		public String getAppCode() {
			return appCode;
		}

		public void setAppCode(String appCode) {
			this.appCode = appCode;
		}

		public String getDeviceType() {
			return deviceType;
		}

		public void setDeviceType(String deviceType) {
			this.deviceType = deviceType;
		}

		public String getUserId() {
			return userId;
		}

		public String getSessionId() {
			return sessionId;
		}

		public void setSessionId(String sessionId) {
			this.sessionId = sessionId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getFrom() {
			return from;
		}

		public void setFrom(String from) {
			this.from = from;
		}

		public String getBiz() {
			return biz;
		}

		public void setBiz(String biz) {
			this.biz = biz;
		}

		public String getTo() {
			return to;
		}

		public void setTo(String to) {
			this.to = to;
		}

		public String getVer() {
			return ver;
		}

		public void setVer(String ver) {
			this.ver = ver;
		}
		
		public String getAppVer() {
			return appVer;
		}

		public void setAppVer(String appVer) {
			this.appVer = appVer;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getPriority() {
			return priority;
		}

		public void setPriority(String priority) {
			this.priority = priority;
		}
		
		public String getFappCode() {
            return fappCode;
        }

        public void setFappCode(String fappCode) {
            this.fappCode = fappCode;
        }

        public String getTappCode() {
            return tappCode;
        }

        public void setTappCode(String tappCode) {
            this.tappCode = tappCode;
        }

        @Override
        public String toString() {
            return "Header [id=" + id + ", type=" + type + ", from=" + from + ", biz=" + biz + ", to=" + to + ", ver="
                    + ver + ", appVer=" + appVer + ", date=" + date + ", priority=" + priority + ", fappCode=" + fappCode + ", tappCode="
                    + tappCode + "]";
        }
        
        
        
	}

	@Override
	public String toString() {
		return "Packet [head=" + head + ", body=" + body + "]";
	}
}
