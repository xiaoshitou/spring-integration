/*
 * Copyright 2002-2010 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package org.springframework.integration.handler;

import org.springframework.integration.Message;
import org.springframework.integration.MessageHandlingException;
import org.springframework.scripting.ScriptSource;

/**
 * Base {@link MessageProcessor} for scripting implementations to extend.
 * 
 * @author Mark Fisher
 * @since 2.0
 */
public abstract class AbstractScriptExecutingMessageProcessor<T> implements MessageProcessor<T> {

	/**
	 * Executes the script and returns the result.
	 */
	public final T processMessage(Message<?> message) {
		try {
			return this.executeScript(getScriptSource(message), message);
		}
		catch (Exception e) {
			throw new MessageHandlingException(message, "failed to execute script", e);
		}
	}


	/**
	 * Subclasses must implement this method to create a script source, optionally using the message to locate or
	 * create the script.
	 * 
	 * @param message the message being processed
	 * @return a ScriptSource to use to create a script
	 */
	protected abstract ScriptSource getScriptSource(Message<?> message);

	/**
	 * Subclasses must implement this method. In doing so, the execution context for the script should be populated with
	 * the Message's 'payload' and 'headers' as variables.
	 */
	protected abstract T executeScript(ScriptSource scriptSource, Message<?> message) throws Exception;

}
