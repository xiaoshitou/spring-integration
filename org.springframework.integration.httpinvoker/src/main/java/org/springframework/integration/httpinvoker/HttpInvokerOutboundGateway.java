/*
 * Copyright 2002-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.integration.httpinvoker;

import org.springframework.integration.adapter.AbstractRemotingOutboundGateway;
import org.springframework.integration.adapter.MessageHandler;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

/**
 * A MessageHandler adapter for HttpInvoker-based remoting.
 * 
 * @author Mark Fisher
 */
public class HttpInvokerOutboundGateway extends AbstractRemotingOutboundGateway {

	public HttpInvokerOutboundGateway(String url) {
		super(url);
	}


	@Override
	protected MessageHandler createHandlerProxy(String url) {
		HttpInvokerProxyFactoryBean proxyFactory = new HttpInvokerProxyFactoryBean();
		proxyFactory.setServiceInterface(MessageHandler.class);
		proxyFactory.setServiceUrl(url);
		proxyFactory.afterPropertiesSet();
		return (MessageHandler) proxyFactory.getObject();
	}

}
