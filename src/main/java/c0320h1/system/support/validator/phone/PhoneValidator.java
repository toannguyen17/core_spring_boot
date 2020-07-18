package c0320h1.system.support.validator.phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {
	@Override
	public void initialize(Phone phone) {
	}

	@Override
	public boolean isValid(String phone, ConstraintValidatorContext ctx) {
		if(phone == null){
			return false;
		}

		if (phone.matches("^\\+?(84|0)((86|96|97|98|32|33|34|35|36|37|38|39|89|90|93|70|79|77|76|78|88|91|94|83|84|85|81|82|92|56|58|99|59)([0-9]{7}))$"))
			return true;

		return false;
	}
}
