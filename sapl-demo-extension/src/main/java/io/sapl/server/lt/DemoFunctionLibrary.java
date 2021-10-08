package io.sapl.server.lt;

import org.springframework.stereotype.Component;

import io.sapl.api.functions.Function;
import io.sapl.api.functions.FunctionLibrary;
import io.sapl.api.interpreter.Val;
import io.sapl.api.validation.Array;
import io.sapl.api.validation.Number;
import io.sapl.api.validation.Text;

/**
 * This is a small custom function library for illustrating how to implement
 * such libraries.
 */
/*
 * The @Component annotation makes sure, that the PIP is created in the Spring
 * application context when the component scan is performed.
 */
@Component
/*
 * The @FunctionLibrary is used by the PDP to identify the Beans, which to
 * import. The annotation is also used when manually instantiating a PDP
 * infrastructure. The 'name' field is optional. If left empty, the name will be
 * the class name. The name determines how the functions can be addressed in
 * policies. The semantics are similar to Java packages. In policies the
 * 'import' statement can be used to provide short hand access to functions.
 * 
 * The 'description' field can be used to add some documentation. This is used
 * to automatically generate documentation pages in the PDP servers with a
 * graphical front-end. It has no impact on the evaluation of policies at
 * runtime.
 */
@FunctionLibrary(name = "simple", description = "This function library contains two simple functions for demo purposes.")
public class DemoFunctionLibrary {

	/**
	 * Functions in SAPL are always mapping a number of {@code Val} parameters to a
	 * single {@code Val} output. There is no such thing as a {@code void} function
	 * in SAPL, as SAPL does not allow for side effects within the PDP.
	 * 
	 * This example function calculates the length of an array or a string.
	 * 
	 * The function takes a single parameter. The parameter is annotated with
	 * {@code @Text} and {@code @Array}. This way, the PDPs function context is
	 * instructed to validate the type of the parameter before invoking the method
	 * implementing the function. If any annotation from the
	 * {@code io.sapl.api.validation} package present, this means that the function
	 * only accepts parameters of the indicated types. Thus any off the annotated
	 * types is legal and all other parameter types will result in a policy
	 * evaluation error.
	 * 
	 * @param parameter a JSON string or array
	 * @return the length of the string or the number of elements in the array
	 */
	@Function
	public Val length(@Text @Array Val parameter) {
		if (parameter.isArray())
			return Val.of(parameter.get().size());

		return Val.of(parameter.get().asText().length());
	}

	@Function
	public Val append(@Text @Number Val... parameters) {
		var builder = new StringBuilder();
		for (var parameter : parameters) {
			if (parameter.isTextual()) {
				builder.append(parameter.get().asText());
			} else if (parameter.isNumber()) {
				builder.append(parameter.get().asInt());
			}
		}
		return Val.of(builder.toString());
	}

}
